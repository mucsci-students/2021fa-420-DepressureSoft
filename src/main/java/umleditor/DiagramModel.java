package umleditor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Iterator;
import java.util.Map;
import javax.lang.model.SourceVersion;
import java.lang.Integer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Represents a UML class diagram, and allows users to manipulate the diagram by
 *  adding/removing/renaming classes, fields, and relationships between classes.
 */
public class DiagramModel {

    /**
     * HashMap representing the classes in the diagram. Keys are class names while values are UMLClass objects
     *  representing the class.
     */
    private HashMap<String, UMLClass> diagram = new HashMap<String, UMLClass>();
    /**
     * ArrayList of relationship types representing the class relationships within the diagram.
     */
    private ArrayList<Relationship> relationships = new ArrayList<Relationship>();

    public DiagramModel (){

    }

    public DiagramModel (DiagramModel other){
        diagram = new HashMap<String, UMLClass>();
        Iterator diagramIter = other.diagram.entrySet().iterator();
        while(diagramIter.hasNext()){
            Map.Entry element = (Map.Entry) diagramIter.next();
            String keyCopy = (String) element.getKey();
            UMLClass classCopy = new UMLClass((UMLClass) element.getValue());
            diagram.put(keyCopy, classCopy);
        }

        relationships = new ArrayList<Relationship>();
        for(Relationship rel : other.relationships){
            UMLClass fromCopy = diagram.get(rel.getFrom().getName());
            UMLClass toCopy = diagram.get(rel.getTo().getName());
            if(toCopy == null || fromCopy == null)
            {
                return;
            }
            Relationship.RelationshipType typeCopy = rel.getRelationshipType();
            Relationship relCopy = new Relationship(fromCopy, toCopy, typeCopy);
            relationships.add(relCopy);
        }
    }
    /**
     * Adds a new class to the diagram, checking to ensure that a class with the same name does not already exist,
     *  and that the name conforms to the standards set forth in javax.lang.model.SourceVersion.isIdentifier().
     * Is supported as an undoable operation
     * @param name The name of the new class.
     * @return null if action is successful, appropriate error message if not
     */
    public String addClass(String name){
        if(SourceVersion.isIdentifier(name)){
            if(!classExists(name))
            {
                snapshot();
                UMLClass holder = new UMLClass(name);
                diagram.put(name, holder);
                return null;
            }
            else
            {
                return "The class \"" + name + "\" already exists.";
            }
        }
        else{
            return name + "\" is not a proper name.";
        }
    }

    /**
     * Deletes a new class from the diagram, checking that the name entered exists in the diagram.
     * Is supported as an undoable operation
     * @param entry The name of the class to delete.
     * @return null if action is successful, appropriate error message if not
     */
    public String deleteClass(String entry){
        if(classExists(entry))
        {
            snapshot();
            for(int i = 0; i < relationships.size(); i++)
            {
                Relationship holder = relationships.get(i);
                if(holder.getTo().getName().equals(entry) || holder.getFrom().getName().equals(entry))
                {
                    relationships.remove(i);
                }
            }
            diagram.remove(entry);
            return null;
        }
        else
        {
            return "The class \"" + entry + "\" cannot be deleted, as it does not exist";
        }
    }

    
    /**
     * Saves the class diagram to a JSON file using the format specified by the CSCI 420 fall 2021
     * standardization committee.
     * @param directory The path relative to the root directory to save the file to. Must end with "/"
     *  and be a valid path.
     * @param fileName The name of the file. Must not include the .json extension, which is appended
     *  automatically.
     * @return null if action is successful, appropriate error message if not
     */
    public String save(String fileLocation){
        StringBuilder jsonTxt = new StringBuilder();
        jsonTxt.append("{\n  \"classes\": [\n");
        diagram.forEach((k,v) -> jsonTxt.append(jsonTxtClassMaker(v)));
        if (!diagram.isEmpty()) {
            jsonTxt.deleteCharAt(jsonTxt.length() - 2);
        }
        jsonTxt.append("  ],\n  \"relationships\": [\n");
        for(Relationship relationship : relationships) {
            jsonTxt.append("    {\n");
            jsonTxt.append("      \"source\": \"" + relationship.getFrom().getName() + "\",\n");
            jsonTxt.append("      \"destination\": \"" + relationship.getTo().getName() + "\",\n");
            jsonTxt.append("      \"type\": \"" + relationship.getRelationshipTypeString() + "\"\n");
            jsonTxt.append("    },\n");
        }
        if (!relationships.isEmpty()) {
            jsonTxt.deleteCharAt(jsonTxt.length() - 2);
        }
        jsonTxt.append("  ]\n}");
        try {
        	String filePath;
        	if (fileLocation.substring(fileLocation.length() - 5, fileLocation.length()).equals(".json")) {
        		filePath = fileLocation;
        	} else {
        		filePath = fileLocation + ".json";
        	}
            FileWriter fw1 = new FileWriter(filePath);
            fw1.write(jsonTxt.toString());
            fw1.close();
            return null;
        } catch (IOException e) {
            return "An error occurred while trying to write json file:" + e.toString();
        }
    }

    /**
     * Formats the contents of a UMLClass into JSON. Designed to be used with the save method.
     * @param theClass The class to format into JSON.
     * @return A formatted string of the contents of theClass.
     */
    private String jsonTxtClassMaker(UMLClass theClass) {
        StringBuilder result = new StringBuilder();
        result.append("    {\n");
        result.append("      \"name\": \"" + theClass.getName() + "\",\n");
        result.append("      \"x_position\": \"" + theClass.getXPosition() + "\",\n");
        result.append("      \"y_position\": \"" + theClass.getYPosition() + "\",\n");
        result.append("      \"fields\": [\n");
        ArrayList<Field> fields = theClass.getFields();
        for(Field field : fields) {
            result.append("        { \"name\": \"" + field.getFieldName() + "\", \"type\": \"" + field.getFieldType() + "\" },\n");
        }
        if(!fields.isEmpty()) {
            result.deleteCharAt(result.length() - 2);
        }
        result.append("      ],\n");
        result.append("      \"methods\": [\n");
        ArrayList<Method> methods = theClass.getMethods();
        for(Method method : methods) {
            result.append("        {\n");
            result.append("          \"name\": \"" + method.getMethodName() + "\",\n");
            result.append("          \"return_type\": \"" + method.getMethodType() + "\",\n");
            result.append("          \"params\": [\n");
            ArrayList<Parameter> parameters = method.getParamList();
            for(Parameter param : parameters) {
                result.append("            { \"name\": \"" + param.getParamName() + "\", \"type\": \"" + param.getParamType() + "\"},\n");
            }
            if (!parameters.isEmpty()) {
                result.deleteCharAt(result.length() - 2);
            }
            result.append("          ]\n");
            result.append("        },\n");
        }
        if(!methods.isEmpty()) {
            result.deleteCharAt(result.length() - 2);
        }
        result.append("      ]\n");
        result.append("    },\n");
        return result.toString();
    }

    /**
     * Loads a class diagram from a JSON file formatted using the format specified by the CSCI 420 fall 2021
     * standardization committee.
     * @param fileLocation the directory of the json file to load.
     * @return null if action is successful, appropriate error message if not
     */
    public String load(String fileLocation){
        this.diagram.clear();
        this.relationships.clear();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileLocation));
            JSONObject json = (JSONObject) obj;
            JSONArray classList = (JSONArray) json.get("classes");
            Iterator<JSONObject> classIterator = classList.iterator();
            while(classIterator.hasNext()) {
                JSONObject currentClass = classIterator.next();
                String currentClassName = (String) currentClass.get("name");
                this.addClass(currentClassName);
                String x_pos = (String) currentClass.get("x_position");
                String y_pos = (String) currentClass.get("y_position");
                this.getUML(currentClassName).setXPosition(Integer.parseInt(x_pos));
                this.getUML(currentClassName).setYPosition(Integer.parseInt(y_pos));
                JSONArray fieldList = (JSONArray) currentClass.get("fields");
                Iterator<JSONObject> fieldIterator = fieldList.iterator();
                while(fieldIterator.hasNext()) {
                    JSONObject currentField = fieldIterator.next();
                    String currentFieldName = (String) currentField.get("name");
                    String currentFieldType = (String) currentField.get("type");
                    this.addField(currentClassName, currentFieldName, currentFieldType);
                }
                JSONArray methodList = (JSONArray) currentClass.get("methods");
                Iterator<JSONObject> methodIterator = methodList.iterator();
                while(methodIterator.hasNext()) {
                    JSONObject currentMethod = methodIterator.next();
                    String currentMethodName = (String) currentMethod.get("name");
                    String currentMethodType = (String) currentMethod.get("type");
                    this.addMethod(currentClassName, currentMethodName, currentMethodType);
                    JSONArray parameterList = (JSONArray) currentMethod.get("params");
                    Iterator<JSONObject> parameterIterator = parameterList.iterator();
                    while(parameterIterator.hasNext()) {
                        JSONObject currentParameter = parameterIterator.next();
                        String currentParameterName = (String) currentParameter.get("name");
                        String currentParameterType = (String) currentParameter.get("type");
                        this.addParameter(currentClassName, currentMethodName, currentParameterName, currentParameterType);
                    }
                }
            }
            JSONArray relationshipList = (JSONArray) json.get("relationships");
            Iterator<JSONObject> relationshipIterator = relationshipList.iterator();
            while(relationshipIterator.hasNext()) {
                JSONObject currentRelationship = relationshipIterator.next();
                String source = (String) currentRelationship.get("source");
                String destination = (String) currentRelationship.get("destination");
                String type = (String) currentRelationship.get("type");
                Relationship.RelationshipType relType = null;
                if(type.equalsIgnoreCase("aggregation")) {
                    relType = Relationship.RelationshipType.AGGREGATION;
                } else if(type.equalsIgnoreCase("composition")) {
                    relType = Relationship.RelationshipType.COMPOSITION;
                } else if(type.equalsIgnoreCase("inheritance")) {
                    relType = Relationship.RelationshipType.INHERITANCE;
                } else if(type.equalsIgnoreCase("realization")) {
                    relType = Relationship.RelationshipType.REALIZATION;
                } else {
                    throw new Exception("Invalid relationship type in json file in relationship between " +
                        source + ":" + destination + ".");
                }
                this.addRelationship(relType, source, destination);
            }
            return null;
        }
        catch (Exception e) {
            return "An error occurred: \n" + e.toString();
        }
    }

    /**
     * Returns a String representation of a single class.
     * @param className The name of the class to display.
     * @return String representation of the class, or an error message if the class does not exist.
     */
    public String listClass(String className) {
    	StringBuilder bob = new StringBuilder();
        if(classExists(className)) {
            UMLClass input = diagram.get(className);
            bob.append("-----------------------\n");
            bob.append("Name: " + input.getName() + "\n");
            bob.append("-----------------------\n");

            /** Prints fields, prints special message if none */
            if (input.getFields().size() == 0) {
                bob.append("There are no fields in this class.\n");
            } else {
                StringBuilder fieldBuilder = new StringBuilder();
                for (int i = 0; i < input.getFields().size(); i++) {
                    fieldBuilder.append(input.getFields().get(i).getFieldName() + " : "
                    		+ input.getFields().get(i).getFieldType() + "\n");
                }
                bob.append((fieldBuilder.toString()));
            }
            bob.append("-----------------------\n");
            /** Prints methods, prints special message if no methods in UML class */
            if(input.getMethods().size() == 0)
            {
                bob.append("There are no methods in this class.\n");
            }
            else
            {
                for(int i = 0; i < input.getMethods().size(); i++)
                {
                    bob.append(input.getMethods().get(i).getMethodName() + "(");
                        for(int j = 0; j < input.getMethods().get(i).getParamList().size(); j++){
                            if(j != 0){
                                bob.append(", ");
                            }
                            bob.append(input.getMethods().get(i).getParamList().get(j).getParamName() + " : "
                            		+ input.getMethods().get(i).getParamList().get(j).getParamType());
                        }
                    bob.append(") : " + input.getMethods().get(i).getMethodType() + "\n");
                }
            }
            bob.append("-----------------------\n");
        }
        else {
            bob.append("The class \"" + className + "\" cannot be displayed, as it does not exist.\n");
        }
        return bob.toString();
    }

    /**
     * Returns a string representation of the entire class diagram.
     * @return String of entire class diagram.
     */
    public String listClasses(){
    	StringBuilder bob = new StringBuilder();
    	if(diagram.size() == 0) {
    		return "There are no classes in the diagram.";
    	}
    	bob.append("\n");
        diagram.forEach((k,v) -> bob.append(listClass(k) + "\n"));
        return bob.toString();
    }

    /**
     * Prints all the relationships in the class diagram.
     * @return A String representation of the relationships.
     */
    public String listRelationships()
    {
    	StringBuilder bob = new StringBuilder();
        ListIterator<Relationship>iterator = relationships.listIterator();
        if (relationships.isEmpty()) {
            bob.append("There are no relationships to display.");
        }

        while (iterator.hasNext())
        {
            Relationship current = iterator.next();
            bob.append(current.getFrom().getName() + " --" + current.getRelationshipType() + "---> "
            		+ current.getTo().getName() + "\n");
        }
        return bob.toString();
    }

    /**
     * Adds a method to a class diagram as long as class exists and method does not already exist.
     * Is supported as an undoable operation.
     * @param className
     * @param methodName
     * @return null if action is successful, appropriate error message if not.
     */
    public String addMethod(String className, String methodName, String methodType){
        UMLClass parentClass = getUML(className);
        if(SourceVersion.isIdentifier(methodName))
        {
            if(parentClass != null)
            {
                if(!parentClass.methodExists(methodName))
                {
                    snapshot();
                    parentClass.addMethod(methodName, methodType);
                    return null;
                } else if(parentClass.methodExists(methodName)){
                    if(!parentClass.getMethod(methodName).getMethodType().equals(methodType)){
                        snapshot();
                        parentClass.addMethod(methodName, methodType);
                        return null;
                    }else{
                        return ("The method \"" + methodName + "\" cannot be added, as it has the same return type.");
                    }
                }
                else
                {
                        return ("The method \"" + methodName +
                            "\" cannot be added, as it already exists "
                            + "in the parent class \"" + className + "\".");
                }
            }
            else
            {
                return ("The method \"" + methodName +
                    "\" cannot be added, as the parent class \"" + className + "\" does not exist.");
            }
        }
        else
        {
            return ("\"" + methodName + "\" is not a valid method name.");
        }
    }

    /**
     * Removes an existing method from the diagram, as long as class exists.
     * Is supported as an undoable operation
     * @param className
     * @param methodName
     * @return null if action is successful, appropriate error message if not
     */
    public String deleteMethod(String className, String methodName){
        UMLClass parentClass = getUML(className);
        if(parentClass != null)
        {
            if(parentClass.methodExists(methodName))
            {
                snapshot();
                parentClass.removeMethod(methodName);
                return null;
            }
            else
            {
                return ("The method \"" + methodName +
                    "\" cannot be removed, as it does not exist.");
            }
        }
        else
        {
            return ("The method \"" + methodName +
                "\" cannot be removed, as the parent class \"" +
                className + "\" does not exist.");
        }
    }

    /**
     * Checks that the class called name exists in the diagram.
     * @param name The name of the class to check for.
     * @return True if class exists, false if not.
     */
    public boolean classExists(String name) {
      return diagram.containsKey(name);
    }

    /**
     * Checks that the specified field exists in the diagram.
     * @param className The name of the class to look for the field in.
     * @param fieldName The name of the field to look for.
     * @return True if field exists, false if not.
     */
    public boolean fieldExists(String className, String fieldName) {
      UMLClass parentClass = getUML(className);
      if(parentClass != null) {
        return parentClass.fieldExists(fieldName);
      }
      return false;
    }

    /**
     * Returns true if a method exists in the class diagram.
     * @param className The name of the class to search for the method in.
     * @param methodName The name of the method to look for.
     * @return true if method exists, false if it does not exist.
     */
    public boolean methodExists(String className, String methodName){
        UMLClass parentClass = getUML(className);
        if(parentClass != null){
            return parentClass.methodExists(methodName);
        }
        return false;
    }

    /**
     * Returns true if parameter exists.
     * @param className The name of the class to search for the parameter in.
     * @param methodName The name of the method to search for the parameter in.
     * @param parameterName The name of the parameter to look for.
     * @return true if parameter exists, false if not.
     */
    public boolean parameterExists(String className, String methodName, String parameterName) {
        UMLClass parentClass = getUML(className);
        if(parentClass != null) {
          Method parentMethod = parentClass.getMethod(methodName);
          if(parentMethod != null) {
            Parameter theParameter = parentMethod.getParam(parameterName);
            if (theParameter != null) return true;
          }
        }
        return false;
      }

    /**
     * Renames a method as long as it already exists.
     * Is supported as an undoable operation
     * @param className The name of the class the method resides in.
     * @param oldMethodName The name of the method to rename.
     * @param newMethodName The new name for the method.
     * @return null if action is successful, appropriate error message if not.
     */
    public String renameMethod(String className, String oldMethodName, String newMethodName){
        UMLClass parentClass = getUML(className);
        if (SourceVersion.isIdentifier(newMethodName))
        {
            if(parentClass != null){
                if(parentClass.methodExists(oldMethodName)){
                  if(!parentClass.methodExists(newMethodName) || oldMethodName.equals(newMethodName)) {
                    snapshot();
                    parentClass.renameMethod(oldMethodName, newMethodName);
                    return null;
                  }
                  else {
                    return ("A method by the name of " + oldMethodName +
                      " already exists in the specified class.");
                  }
                }
                else
                {
                    return ("The method \"" + oldMethodName +
                        "\" cannot be renamed, as it does not exist.");
                }
            }
            else
            {
                return ("The method \"" + oldMethodName +
                    "\" cannot be renamed, as the parent class \"" + className + "\" does not exist.");
            }
        }
        else
        {
            return ("\"" + newMethodName + "\" is not a valid method name.");
        }
    }

    /**
     * Renames the method return type if the method and class exist.
     * @param className
     * @param methodName
     * @param newMethodType
     */
    public String renameMethodType(String className, String methodName, String newMethodType){
        UMLClass parentClass = getUML(className);
            if(parentClass != null){
                if(parentClass.methodExists(methodName)){
                    snapshot();
                    parentClass.renameMethodType(methodName, newMethodType);
                    return null;
                }
                else
                {
                        return ("The method \"" + methodName +
                            "\" type cannot be renamed, as it does not exist.");
                }
            }
            else
            {
                return ("The method \"" + methodName +
                    "\" type cannot be renamed, as the parent class \"" + className + "\" does not exist.");
            }
    }

    /**
     * Adds parameter to a method if the method and class exist.
     * Is supported as an undoable operation
     * @param className
     * @param methodName
     * @param name
     * @return null if action is successful, appropriate error message if not
     */
    public String addParameter(String className, String methodName, String pName, String pType){
        UMLClass parentClass = getUML(className);
        if(SourceVersion.isIdentifier(pName))
        {
            if(parentClass != null)
            {
                Method parentMethod = parentClass.getMethod(methodName);
                if(parentClass.methodExists(methodName))
                {
                  if(!parameterExists(className, methodName, pName))
                  {
                    snapshot();
                    parentMethod.addParameter(pName, pType);
                    return null;
                  }
                  else
                  {
                    return ("A parameter called " + pName + " already exists in the specified method.");
                  }
                }
                else
                {
                        return ("The parameter(s) \"" + pName +
                            "\" cannot be added, as the method \"" +
                        	methodName + "does not exist.");
                }
            }
            else
            {
                return ("The parameter(s) \"" + pName +
                    "\" cannot be added, as the parent class \"" +
                	className + "\" does not exist.");
            }
        }
        else
        {
            return ("\"" + pName + "\" is not a valid parameter name.");
        }
    }

    /**
     * Removes parameter to a method if the method and class exist.
     * Is supported as an undoable operation
     * @param className
     * @param methodName
     * @param pName
     * @return null if action is successful, appropriate error message if not
     */
    public String deleteParameter(String className, String methodName, String pName) {
        UMLClass parentClass = getUML(className);
        if(parentClass != null){
            Method parentMethod = parentClass.getMethod(methodName);
            if(parentClass.methodExists(methodName)){
                if(parentMethod.parameterExists(pName)){
                    snapshot();
                    parentMethod.removeParameter(pName);
                    return null;
                } else {
                	return ("The parameter \"" + pName + "\" cannot be removed, as it does not exist.");
                }
            }
            else {
                return ("The parameter \"" + pName +
                        "\" cannot be removed, as the method \"" +
                		methodName + "does not exist.");
            }
        }
        else {
            return ("The parameter \"" + pName +
                "\" cannot be removed, as the parent class \"" +
            	className + "\" does not exist.");
        }
    }

    /**
     * Renames parameter if class and method exist.
     * Is supported as an undoable operation
     * @param className
     * @param methodName
     * @param oldPName
     * @param newPName
     * @return null if action is successful, appropriate error message if not
     */
    public String renameParameter(String className, String methodName, String oldPName, String newPName){
        UMLClass parentClass = getUML(className);
        if(SourceVersion.isIdentifier(newPName))
        {
            if(parentClass != null)
            {
                Method parentMethod = parentClass.getMethod(methodName);
                if(parentClass.methodExists(methodName))
                {
                    if(parentMethod.parameterExists(oldPName))
                    {
                        snapshot();
                        parentMethod.renameParameter(oldPName, newPName);
                        return null;
                    }
                    else{
                        return ("The parameter \"" + oldPName +
                            "\" cannot be renamed, as it does not exist.");
                    }
                }
                else{
                    return ("The parameter \"" + oldPName +
                            "\" cannot be renamed, as the method \"" + methodName + "does not exist.");
                }
            }
            else{
                return ("The parameter \"" + oldPName +
                    "\" cannot be renamed, as the parent class \"" + className + "\" does not exist.");
            }
        }
        else
        {
            return ("\"" + newPName + "\" is not a valid parameter name.");
        }
    }

    /**
     * Renames parameter type if the class and method exist.
     * @param className
     * @param methodName
     * @param paramName
     * @param newPType
     */
    public String renameParameterType(String className, String methodName, String paramName, String newPType){
        UMLClass parentClass = getUML(className);
            if(parentClass != null)
            {
                Method parentMethod = parentClass.getMethod(methodName);
                if(parentClass.methodExists(methodName))
                {
                    if(parentMethod.parameterExists(paramName))
                    {
                        snapshot();
                        parentMethod.renameParameterType(paramName, newPType);
                        return null;
                    }
                    else{
                        return ("The parameter \"" + paramName +
                            "\" type cannot be renamed, as it does not exist.");
                    }
                }
                else{
                    return ("The parameter \"" + paramName +
                            "\" type cannot be renamed, as the method \"" + methodName + "does not exist.");
                }
            }
            else{
                return ("The parameter \"" + paramName +
                    "\" type cannot be renamed, as the parent class \"" + className + "\" does not exist.");
            }
    }

    /**
     * Removes all parameters if the method and class exist.
     * Is supported as an undoable operation
     * @param className
     * @param methodName
     * @return null if action is successful, appropriate error message if not
     */
    public String deleteAllParameters(String className, String methodName){
        UMLClass parentClass = getUML(className);
        if(parentClass != null){
            Method parentMethod = parentClass.getMethod(methodName);
            if(parentClass.methodExists(methodName)){
                snapshot();
                parentMethod.removeAllParameters();
                return null;
            }
            else{
                return ("The parameters cannot be removed, as the method \"" + methodName + "does not exist.");
            }
        }
        else{
            return ("The parameters cannot be removed, as the parent class \"" + className + "\" does not exist.");
        }
    }

    /**
     * Checks that a relationship exists between two classes, in either direction.
     * @param from The first class.
     * @param to The second class.
     * @return True if relationship exists, in either direction. False if not.
     */
    public boolean relationshipExists(String from, String to) {
      for(int i = 0; i < relationships.size(); i++){
          Relationship holder = relationships.get(i);
          if(holder.getFrom().getName().equals(from) && holder.getTo().getName().equals(to))
          {
              return true;
          }
          else if(holder.getFrom().getName().equals(to) && holder.getTo().getName().equals(from))
          {
              return true;
          }
      }
      return false;
    }

    /**
     * Returns the specified relationship.
     * @param from The "from" component of the relationship.
     * @param to The "to" component of the relationship.
     * @return The relationship between "from" and "to".
     */
     public Relationship getRelationship(String from, String to) {
       for(int i = 0; i < relationships.size(); i++){
           Relationship holder = relationships.get(i);
           if(holder.getFrom().getName().equals(from) && holder.getTo().getName().equals(to))
           {
               return holder;
           }
       }
       return null;
     }

    /**
     * Adds a class relationship to the diagram, checking to ensure that both classes exist, a relationship
     *  does not already exist between the two classes, and that the relationship is not recursive.
     * Is supported as an undoable operation
     * @param from The "parent" of the relationship.
     * @param to The "child" of the relationship.
     * @param type The type of the relationship. Can be one of AGGREGATION, COMPOSITION, INHERITANCE,
     *  or REALIZATION.
     * @return null if action is successful, appropriate error message if not
     */
    public String addRelationship(Relationship.RelationshipType type, String from, String to)
    {
        boolean fromClassExists = classExists(from);
        boolean toClassExists = classExists(to);

        if(fromClassExists && toClassExists)
        {
                boolean relationshipExists = false;

                // Iterate for relationship existence
                for(int i = 0; i < relationships.size(); i++){
                    Relationship holder = relationships.get(i);

                    if(holder.getFrom().getName().equals(from) && holder.getTo().getName().equals(to))
                    {
                        relationshipExists = true;
                    }
                    else if(holder.getFrom().getName().equals(to) && holder.getTo().getName().equals(from))
                    {
                        relationshipExists = true;
                    }
                }

                if(!relationshipExists)
                {
                    snapshot();
                    UMLClass fromClass = getUML(from);
                    UMLClass toClass = getUML(to);
                    Relationship newRelationship = new Relationship(fromClass, toClass, type);
                    relationships.add(newRelationship);
                    return null;
                }
                // If relationship exists already
                else
                {
                    return ("The relationship between \"" + from + "\" and \"" + to +
                        "\" cannot be added, as a relationship already exists between those classes.");
                }
        }
        // If either class DNE
        else
        {
        	if(!fromClassExists && !toClassExists)
        	{
        		return ("The relationship cannot be added, as both the source class \"" + from +
        				" and destination class \"" + to + " do not exist.");
        	}
            if(!fromClassExists)
            {
                return ("The relationship cannot be added, as the source class \"" + from +
                    "\" does not exist");
            }
            else /*if (!toClassExists)*/
            {
                return ("The relationship cannot be added, as the destination class \"" + to +
                "\" does not exist");
            }
        }
    }

    /**
     * Deletes a class relationship from the diagram, checking to ensure that both classes exist and
     *  a relationship exists between those two classes in the correct order.
     * Is supported as an undoable operation
     * @param from The "parent" of the relationship.
     * @param to The "child" of the relationship.
     */
    public String deleteRelationship(String from, String to)
    {
        boolean fromClassExists = classExists(from);
        boolean toClassExists = classExists(to);

        if(fromClassExists && toClassExists)
        {
            boolean relationshipExists = false;

            // Iterate for relationship existence and deletion
            for(int i = 0; i < relationships.size(); i++){
                Relationship holder = relationships.get(i);
                if(holder.getFrom().getName().equals(from) && holder.getTo().getName().equals(to)){
                    snapshot();
                    relationships.remove(i);
                    relationshipExists = true;
                    return null;
                }
            }
            // If relationship did not exist prior
            if(!relationshipExists)
            {
                return ("The relationship between \"" + from + "\" and \"" + to +
                    "\" cannot be deleted, as it does not exist");
            }
            else
            {
            	return "An unknown error occurred."; // impossible code path
            }
        }
        // If either class DNE
        else
        {
        	if (!fromClassExists && !toClassExists)
        	{
        		return ("Both the source class and destination class do not exist");
        	}
        	else if(!fromClassExists)
            {
                return ("The source class \"" + from + "\" does not exist");
            }
            else /*if(!toClassExists)*/
            {
                return ("The destination class \"" + to + "\" does not exist");
            }
        }
    }

    /**
     * Changes the type of the relationship between class "from" and class "to".
     * Is supported as an undoable operation
     * @param from The "from" end of the relationship.
     * @param to The "to" end of the relationship.
     * @param newType The new type to assign the relationship to.
     * @return null if action is successful, appropriate error message if not
     */
    public String changeRelationshipType(Relationship.RelationshipType newType, String from, String to) {
        boolean fromClassExists = classExists(from);
        boolean toClassExists = classExists(to);

        if(fromClassExists && toClassExists)
        {
            boolean relationshipExists = false;

            // Iterate for relationship existence and deletion
            for(int i = 0; i < relationships.size(); i++){
                Relationship holder = relationships.get(i);

                if(holder.getFrom().getName().equals(from) && holder.getTo().getName().equals(to)){
                    snapshot();
                    holder.setType(newType);
                    relationshipExists = true;
                    return null;
                }
            }
            // If relationship did not exist prior
            if(!relationshipExists)
            {
            	return ("The type of the relationship between \"" + from + "\" and \"" + to +
                    "\" cannot be changed, as the relationship does not exist");
            }
            else
            {
            	return "An unknown error occurred."; // impossible code path
            }
        }
        // If either class DNE
        else
        {
        	if (!fromClassExists && !toClassExists) {
        		return "Both the source class and destination class do not exist";
        	}
        	else if(!fromClassExists)
            {
                return ("The source class \"" + from + "\" does not exist");
            }
            else /*if(!toClassExists)*/
            {
                return ("The destination class \"" + to + "\" does not exist");
            }
        }
    }

    /**
     * Grabs a UMLClass object from the diagram, if one by the specified name exists.
     * @param name The name of the UMLClass to grab.
     * @return The UMLClass object of the specified name.
     */
    public UMLClass getUML(String name){
        return diagram.get(name);
    }

    /**
     * Renames a class in the diagram, checking to ensure that the specified class exists and that the new
     *  class name conforms to the standards set forth in javax.lang.model.SourceVersion.isIdentifier().
     * Is supported as an undoable operation
     * @param oldName The name of the class to rename.
     * @param newName The new name of the specified class.
     * @return null if action is successful, appropriate error message if not
     */
    public String renameUMLClass(String oldName, String newName)
    {
        if(SourceVersion.isIdentifier(newName)){

        	boolean oldClassExists = classExists(oldName);
        	boolean newClassExists = classExists(newName);

		    if(oldClassExists && !newClassExists)
		    {
		        UMLClass renamedClass = diagram.get(oldName);
		        snapshot();
		        renamedClass.renameClass(newName);
		        diagram.remove(oldName);
		        diagram.put(newName, renamedClass);
		        return null;
		    }
		    else
		    {
		        if(!oldClassExists)
		        {
		            return ("The class \"" + oldName + "\" cannot be renamed, as it does not exist.");
		        }
		        else if(newClassExists)
		        {
		            return ("The class \"" + oldName + "\" cannot be renamed to \"" + newName +
		                "\", as \"" + newName + "\" already exists as a class.");
		        }
		    }
        }
        else{
            return (newName + "\" is not a proper name.");
        }
        return "An unknown error occurred.";
    }

    /**
     * Adds a field to a class, given that the class exists and that the field is not a duplicate.
     * Is supported as an undoable operation
     * @param className The name of the class to add a field to.
     * @param fieldName The name of the field to be added.
     */
    public String addField(String className, String fieldName, String fieldType)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);
        if(SourceVersion.isIdentifier(fieldName)){
            if(parentExists)
            {
               if(!parentClass.fieldExists(fieldName))
               {
                   snapshot();
                   parentClass.addField(fieldName, fieldType);
                   return null;
               }
               else
               {
                    return ("The field \"" + fieldName +
                        "\" cannot be added, as it already exists in the parent class \"" + className + "\".");
               }
            }
            else
            {
                return ("The field \"" + fieldName +
                    "\" cannot be added, as the parent class \"" + className + "\" does not exist.");
            }
        }
        else{
            return (fieldName + "\" is not a proper name.");
        }
    }

    /**
     * Deletes a field from a class, given that the class and field both exist.
     * Is supported as an undoable operation
     * @param className The name of the class to delete a field from.
     * @param fieldName The name of the field to be deleted.
     * @return null if action is successful, appropriate error message if not
     */
    public String deleteField(String className, String fieldName)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);

        if(parentExists)
        {
           if(parentClass.fieldExists(fieldName))
           {
               snapshot();
               parentClass.removeField(fieldName);
               return null;
           }
           else
           {
                return ("The field \"" + fieldName +
                    "\" cannot be deleted, as it does not exist in the parent class \"" + className + "\".");
           }
        }
        else
        {
            return ("The field \"" + fieldName +
                "\" cannot be deleted, as the parent class \"" + className + "\" does not exist.");
        }
    }

    /**
     * Renames a field from a class, given that the class exists, the field exists, and the new
     *  field name does not exist.
     * Is supported as an undoable operation
     * @param className The name of the class to add an field to.
     * @param oldFieldName The name of the field to be renamed.
     * @param newFieldName The name that oldFieldName will be renamed to.
     */
    public String renameField(String className, String oldFieldName, String newFieldName)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);
        if(SourceVersion.isIdentifier(newFieldName)){
        if(parentExists)
        {
           if(parentClass.fieldExists(oldFieldName) && !parentClass.fieldExists(newFieldName))
           {
               snapshot();
               parentClass.renameField(oldFieldName, newFieldName);
               return null;
           }
           else if(!parentClass.fieldExists(oldFieldName))
           {
                return ("The field \"" + oldFieldName +
                    "\" cannot be renamed, as it does not exist in the parent class \"" + className + "\".");
           }
           else if(parentClass.fieldExists(newFieldName))
           {
                return ("The field \"" + oldFieldName +
                    "\" cannot be renamed to \"" + newFieldName + "\", as \"" + newFieldName +
                    "\" already exists in the parent class \"" + className + "\".");
           }
        }
        else
        {
            return ("The field \"" + oldFieldName +
                "\" cannot be renamed, as the parent class \"" + className + "\" does not exist.");
        }
        }
        else{
            return (newFieldName + "\" is not a proper name.");
        }
        return "An unknown error occurred.";
    }

    /**
     * Stores the coordinates of the specified class.
     * @param className The name of the class
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return null if operation was successful, appropriate error message if not.
     */
    public String setCoordinates(String className, int x, int y) {
      if(classExists(className)) {
        UMLClass theClass = getUML(className);
        theClass.setXPosition(x);
        theClass.setYPosition(y);
        return null;
      } else {
        return "The specified class " + className + " does not exist on the diagram.";
      }
    }

    /**
     * Renames the field type if it exists in the class.
     * @param className
     * @param fieldName
     * @param newFieldType
     */
    public String renameFieldType(String className, String fieldName, String newFieldType){
        UMLClass parentClass = getUML(className);
            if(parentClass != null){
                if(parentClass.fieldExists(fieldName)){
                    snapshot();
                    parentClass.renameFieldType(fieldName, newFieldType);
                    return null;
                }
                else
                {
                        return ("The field \"" + fieldName +
                            "\" type cannot be renamed, as it does not exist.");
                }
            }
            else
            {
                return ("The field \"" + fieldName +
                    "\" cannot be renamed, as the parent class \"" + className + "\" does not exist.");
            }
    }

    /**
     * Gets the x position of the specified class.
     * @param className The name of the class.
     * @return the x coordinate of the class.
     */
    public int getXPosition(String className) {
      if(classExists(className)) {
        UMLClass theClass = getUML(className);
        return theClass.getXPosition();
      } else {
        return 0;
      }
    }

    /**
     * Gets the y position of the specified class.
     * @param className The name of the class.
     * @return the y coordinate of the class.
     */
    public int getYPosition(String className) {
      if(classExists(className)) {
        UMLClass theClass = getUML(className);
        return theClass.getYPosition();
      } else {
        return 0;
      }
    }

    /**
     * Returns the number of classes currently in the diagram.
     * @return The number of classes in the diagram
     */
    public int numberOfClasses()
    {
    	return diagram.size();
    }

    /**
     * Returns a boolean determined by if any methods are within the diagram.
     * @return True if a method exists, false otherwise
     */
    public boolean methodsPresent()
    {
    	boolean methPres = false;
    	for (UMLClass value : diagram.values())
    	{
    		if (value.getMethods().size() > 0)
    		{
    			methPres = true;
    			return methPres;
    		}
    	}

    	return methPres;
    }

    /**
     * Returns a boolean determined by if any parameters are within the diagram.
     * @return True if a parameter exists, false otherwise
     */
    public boolean paramsPresent()
    {
    	boolean parPres = false;
    	for (UMLClass value : diagram.values())
    	{
    		for (Method meth : value.getMethods())
    		{
	    		if (meth.getParamList().size() > 0)
	    		{
	    			parPres = true;
	    			return parPres;
	    		}
    		}
    	}

    	return parPres;
    }

    /**
     * Returns a boolean determined by if any relationships are within the diagram.
     * @return True if a relationship exists, false otherwise.
     */
    public boolean relationshipsPresent()
    {
    	boolean relPres = false;
    	if (relationships.size() > 0)
    	{
    		relPres = true;
    	}

    	return relPres;
    }

    /**
     * Returns a boolean determined by if any fields are within the diagram.
     * @return True if a field exists, false otherwise
     */
    public boolean fieldsPresent()
    {
    	boolean fieldsPres = false;
    	for (UMLClass value : diagram.values())
    	{
    		if (value.getFields().size() > 0)
    		{
    			fieldsPres = true;
    			return fieldsPres;
    		}
    	}

    	return fieldsPres;
    }

    public HashMap<String, UMLClass> getDiagramCopy() {
      return new HashMap<String, UMLClass>(diagram);
    }

    public ArrayList<Relationship> getRelationshipsCopy() {
      return new ArrayList<Relationship>(relationships);
    }

    /**
     *  Undos the most recent undoable operation.
     * @return Whether or not the undo was successful (false in the case where the undo history is empty)
     */
    public boolean undo()
    {
        ModelHistory history = ModelHistory.getInstance();

        if (history.isUndoHistoryEmpty())
        {
            return false;
        }
        else
        {
            DiagramModel old = history.undo(new DiagramModel(this));
            this.diagram = old.diagram;
            this.relationships = old.relationships;
            return true;
        }
    }

    /**
     *  Redos the most recent undo, restoring the state to what it was.
     * @return Whether or not the redo was successful (false in the case where the redo history is empty)
     */
    public boolean redo()
    {
        ModelHistory history = ModelHistory.getInstance();

        if (history.isRedoHistoryEmpty())
        {
            return false;
        }
        else
        {
            DiagramModel old = history.redo(new DiagramModel(this));
            this.diagram = old.diagram;
            this.relationships = old.relationships;
            return true;
        }
    }

    /**
    * Snapshots the current state of the model to be pushed onto the undo stack.
    */
    private void snapshot()
    {
        ModelHistory history = ModelHistory.getInstance();
        history.snapshotModel(new DiagramModel(this));
    }

    public ArrayList<String> getClassNames(){
        ArrayList<String> listOfKeys
        = new ArrayList<String>(diagram.keySet());

        return listOfKeys;
    }

    /**
     * Returns a boolean based on whether undo can be currently used
     * @return a boolean determined by if undo can be currently used
     */
    public boolean canUndo()
    {
    	ModelHistory history = ModelHistory.getInstance();

    	if (history.isUndoHistoryEmpty())
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }

    /**
     * Returns a boolean based on whether redo can be currently used
     * @return a boolean determined by if redo can be currently used
     */
    public boolean canRedo()
    {
    	ModelHistory history = ModelHistory.getInstance();

    	if (history.isRedoHistoryEmpty())
    	{
    		return false;
    	}
    	else
    	{
    		return true;
    	}
    }

}
