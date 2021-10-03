package umleditor;
/**
 * Authors: Jeffrey Flynn, David Jachimowicz, Jeff Cutcher
 * Date: 9/15/21
 */

import java.util.ArrayList;
import javax.lang.model.SourceVersion;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

import java.util.HashMap;
import java.util.ListIterator;

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

    /**
     * Adds a new class to the diagram, checking to ensure that a class with the same name does not already exist,
     *  and that the name conforms to the standards set forth in javax.lang.model.SourceVersion.isIdentifier().
     * @param name The name of the new class.
     */
    public void addClass(String name){
        if(SourceVersion.isIdentifier(name)){
            if(!classExists(name))
            {
                UMLClass holder = new UMLClass(name);
                diagram.put(name, holder);  
            }
            else
            {
                System.out.println("The class \"" + name + "\" already exists.");
            }
        }
        else{
            System.out.println(name + "\" is not a proper name.");
        }

    }

    /**
     * Deletes a new class from the diagram, checking that the name entered exists in the diagram.
     * @param entry The name of the class to delete.
     */
    public void deleteClass(String entry){
        if(classExists(entry))
        {
            for(int i = 0; i < relationships.size(); i++)
            {
                Relationship holder = relationships.get(i);
                if(holder.getTo().getName().equals(entry) || holder.getFrom().getName().equals(entry))
                {
                    relationships.remove(i);
                }
            }
            diagram.remove(entry);
        }
        else
        {
            System.out.println("The class \"" + entry + "\" cannot be deleted, as it does not exist");
        }
    }



    /**
     * Saves the class diagram to a JSON file using the format specified by the CSCI 420 fall 2021 
     * standardization committee.
     * @param directory The path relative to the root directory to save the file to. Must end with "/"
     *  and be a valid path.
     * @param fileName The name of the file. Must not include the .json extension, which is appended
     *  automatically.
     */
    public void save(String directory, String fileName){
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
            jsonTxt.append("      \"type\": \"" + relationship.getRelationshipType() + "\"\n");
            jsonTxt.append("    },\n");
        }
        if (!relationships.isEmpty()) {
            jsonTxt.deleteCharAt(jsonTxt.length() - 2);
        }  
        jsonTxt.append("  ]\n}");
        try {
            String filePath = directory + fileName + ".json";
            FileWriter fw1 = new FileWriter(filePath);
            fw1.write(jsonTxt.toString());
            fw1.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        result.append("      \"fields\": [\n");
        ArrayList<String> fields = theClass.getFields();
        for(String field : fields) {
            result.append("        { \"name\": \"" + field + "\", \"type\": \"N/A\" },\n"); 
        }
        if(!fields.isEmpty()) {
            result.deleteCharAt(result.length() - 2);
        }
        result.append("      ],\n");
        result.append("      \"methods\": [\n");
        // methods
        result.append("      ]\n");
        result.append("    },\n");
        return result.toString();
    }

    /**
     * Loads a class diagram from a JSON file formatted using the format specified by the CSCI 420 fall 2021 
     * standardization committee.
     */
    public void load(String prevSave){
        Type a = new TypeToken<HashMap<String, UMLClass>>(){}.getType();
        HashMap<String, UMLClass> loader = new Gson().fromJson(prevSave, a);
        diagram.clear();
        diagram = loader;
    }

    /**
     * Prints out the contents of a single class to the console, checking first to make sure the given
     *  class name exists in the diagram.
     * @param className The name of the class to display.
     */
    public void listClass(String className)
    {
        if(classExists(className))
            {
            UMLClass input = diagram.get(className);

            System.out.println("Name: " + input.getName());
            System.out.println("Fields: ");

            /** Prints fields, prints special message if none */
            if (input.getFields().size() == 0)
            {
                System.out.println("There are no fields in this class.");
            }
            else
            {
                for (int i = 0; i < input.getFields().size(); i++)
                {
                    System.out.println(input.getFields().get(i));
                }
            }
        }
        else
        {
            System.out.println("The class \"" + className + "\" cannot be displayed, as it does not exist");
        }
    }

    /**
     * Prints the contents of every class in the diagram to the console.
     */
    public void ListClasses(){
        diagram.forEach((k,v) -> listClass(k));
    }

    /**
     * Adds a class relationship to the diagram, checking to ensure that both classes exist, a relationship
     *  does not already exist between the two classes, and that the relationship is not recursive.
     * @param from The "parent" of the relationship.
     * @param to The "child" of the relationship.
     * @param type The type of the relationship. Can be one of AGGREGATION, COMPOSITION, INHERITANCE, 
     *  or REALIZATION.
     */
    public void addRelationship(String from, String to, Relationship.RelationshipType type)
    {
        boolean fromClassExists = classExists(from);
        boolean toClassExists = classExists(to);

        if(fromClassExists && toClassExists)
        {
            if(!from.equals(to))
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
                    UMLClass fromClass = getUML(from);
                    UMLClass toClass = getUML(to);

                    Relationship newRelationship = new Relationship(fromClass, toClass, type);
                    relationships.add(newRelationship);
                }
                // If relationship exists already
                else
                {
                    System.out.println("The relationship between \"" + from + "\" and \"" + to + 
                        "\" cannot be added, as a relationship already exists between those classes.");
                }
            }
            // If recursive relationship
            else
            {
                System.out.println("The relationship cannot be added, as the source and destination class are the same.");
            }
        }
        // If either class DNE
        else
        {
            if(!fromClassExists)
            {
                System.out.println("The relationship cannot be added, as the source class \"" + from + 
                    "\" does not exist");
            }
            else if(!toClassExists)
            {
                System.out.println("The relationship cannot be added, as the destination class \"" + to + 
                "\" does not exist");
            }
        }
    }

    /**
     * Deletes a class relationship from the diagram, checking to ensure that both classes exist and
     *  a relationship exists between those two classes in the correct order.
     * @param from The "parent" of the relationship.
     * @param to The "child" of the relationship.
     */
    public void deleteRelationship(String from, String to)
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
                    relationships.remove(i);
                    relationshipExists = true;
                }
            }
            // If relationship did not exist prior
            if(!relationshipExists)
            {
                System.out.println("The relationship between \"" + from + "\" and \"" + to + 
                    "\" cannot be deleted, as it does not exist");
            }
        }
        // If either class DNE
        else
        {
            if(!fromClassExists)
            {
                System.out.println("The source class \"" + from + "\" does not exist");
            }
            else if(!toClassExists)
            {
                System.out.println("The destination class \"" + to + "\" does not exist");
            }
        }
    }

    /**
     * Changes the type of the relationship between class "from" and class "to".
     * @param from The "from" end of the relationship.
     * @param to The "to" end of the relationship.
     * @param newType The new type to assign the relationship to.
     */
    public void changeRelationshipType(String from, String to, Relationship.RelationshipType newType) {
        boolean fromClassExists = classExists(from);
        boolean toClassExists = classExists(to);

        if(fromClassExists && toClassExists)
        {
            boolean relationshipExists = false;

            // Iterate for relationship existence and deletion
            for(int i = 0; i < relationships.size(); i++){
                Relationship holder = relationships.get(i);

                if(holder.getFrom().getName().equals(from) && holder.getTo().getName().equals(to)){
                    holder.setType(newType);
                    relationshipExists = true;
                }
            }
            // If relationship did not exist prior
            if(!relationshipExists)
            {
                System.out.println("The type of the relationship between \"" + from + "\" and \"" + to + 
                    "\" cannot be changed, as the relationship does not exist");
            }
        }
        // If either class DNE
        else
        {
            if(!fromClassExists)
            {
                System.out.println("The source class \"" + from + "\" does not exist");
            }
            else if(!toClassExists)
            {
                System.out.println("The destination class \"" + to + "\" does not exist");
            }
        }
    }
  
    /**
     * Prints all the relationships in the class diagram.
     */
    public void ListRelationships()
    {
        ListIterator<Relationship>iterator = relationships.listIterator();
        if (relationships.isEmpty()) {
            System.out.println("There are no relationships to display.");
        }
      
        while (iterator.hasNext())
        {
            Relationship current = iterator.next();
            UMLClass from = current.getFrom();
            UMLClass to = current.getTo();
            System.out.println("From: " + from.getName() + " To: " + to.getName() + " Type: " + 
                current.getRelationshipType());
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
     * @param oldName The name of the class to rename.
     * @param newName The new name of the specified class.
     */
    public void renameUMLClass(String oldName, String newName)
    {
        if(SourceVersion.isIdentifier(newName)){

        
        boolean oldClassExists = classExists(oldName);
        boolean newClassExists = classExists(newName);

        if(oldClassExists && !newClassExists)
        {
            UMLClass renamedClass = diagram.get(oldName);
            renamedClass.renameClass(newName);
            diagram.remove(oldName);
            diagram.put(newName, renamedClass);
        }
        else
        {
            if(!oldClassExists)
            {
                System.out.println("The class \"" + oldName + "\" cannot be renamed, as it does not exist.");
            }
            else if(newClassExists)
            {
                System.out.println("The class \"" + oldName + "\" cannot be renamed to \"" + newName + 
                    "\", as \"" + newName + "\" already exists as a class.");
            }
        }
        }
        else{
            System.out.println(newName + "\" is not a proper name.");
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
     * Adds a field to a class, given that the class exists and that the field is not a duplicate.
     * @param className The name of the class to add a field to.
     * @param fieldName The name of the field to be added.
     */
    public void addField(String className, String fieldName)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);
        if(SourceVersion.isIdentifier(fieldName)){
        if(parentExists)
        {
           boolean fieldExists = parentClass.getFields().contains(fieldName); 

           if(!fieldExists)
           {
               parentClass.addField(fieldName);
           }
           else
           {
                System.out.println("The field \"" + fieldName + 
                    "\" cannot be added, as it already exists in the parent class \"" + className + "\".");
           }
        }
        else
        {
            System.out.println("The field \"" + fieldName + 
                "\" cannot be added, as the parent class \"" + className + "\" does not exist.");  
        }
    }
    else{
        System.out.println(fieldName + "\" is not a proper name.");
    }
    }

    /**
     * Deletes a field from a class, given that the class and field both exist.
     * @param className The name of the class to delete a field from.
     * @param fieldName The name of the field to be deleted.
     */
    public void deleteField(String className, String fieldName)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);
    
        if(parentExists)
        {
           boolean fieldExists = parentClass.getFields().contains(fieldName); 

           if(fieldExists)
           {
               parentClass.removeField(fieldName);
           }
           else
           {
                System.out.println("The field \"" + fieldName + 
                    "\" cannot be deleted, as it does not exist in the parent class \"" + className + "\".");
           }
        }
        else
        {
            System.out.println("The field \"" + fieldName + 
                "\" cannot be deleted, as the parent class \"" + className + "\" does not exist.");  
        }
    }

    /**
     * Renames a field from a class, given that the class exists, the field exists, and the new 
     *  field name does not exist.
     * @param className The name of the class to add an field to.
     * @param oldFieldName The name of the field to be renamed.
     * @param newFieldName The name that oldFieldName will be renamed to.
     */
    public void renameField(String className, String oldFieldName, String newFieldName)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);
        if(SourceVersion.isIdentifier(newFieldName)){
        if(parentExists)
        {
           boolean oldFieldExists = parentClass.getFields().contains(oldFieldName); 
           boolean newFieldExists = parentClass.getFields().contains(newFieldName); 

           if(oldFieldExists && !newFieldExists)
           {
               parentClass.renameField(oldFieldName, newFieldName);
           }
           else if(!oldFieldExists)
           {
                System.out.println("The field \"" + oldFieldName + 
                    "\" cannot be renamed, as it does not exist in the parent class \"" + className + "\".");
           }
           else if(oldFieldExists)
           {
                System.out.println("The field \"" + oldFieldName + 
                    "\" cannot be renamed to \"" + newFieldName + "\", as \"" + newFieldName + 
                    "\" already exists in the parent class \"" + className + "\".");
           }
        }
        else
        {
            System.out.println("The field \"" + oldFieldName + 
                "\" cannot be renamed, as the parent class \"" + className + "\" does not exist.");  
        }
        }
        else{
            System.out.println(newFieldName + "\" is not a proper name.");
        }
    }

}
