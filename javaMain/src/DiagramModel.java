/**
 * Authors: Jeffrey Flynn, David Jachimowicz, Jeff Cutcher
 * Date: 9/15/21
 */

import java.util.ArrayList;
import javax.lang.model.SourceVersion;
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
     * ArrayList representing the class relationships within the diagram. In each element, there is a 2-element
     *  array containing references to UMLClass objects that exist in the diagram. Position [0] contains the "parent"
     *  of the relationship while [1] contains the "child."
     */
    private ArrayList<UMLClass[]> relationships = new ArrayList<UMLClass[]>();

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
                UMLClass[] holder = relationships.get(i);
                
                if(holder[0].getName().equals(entry) || holder[1].getName().equals(entry))
                {
                    holder[0].deleteRelationship(holder[1].getName());
                    holder[1].deleteRelationship(holder[0].getName());
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
     */
    public void Save(){

    }

    /**
     * Loads a class diagram from a JSON file formatted using the format specified by the CSCI 420 fall 2021 
     * standardization committee.
     */
    public void Load(){

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
     */
    public void addRelationship(String from, String to)
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
                    UMLClass[] holder = relationships.get(i);

                    if(holder[0].getName().equals(from) && holder[1].getName().equals(to)){
                        relationshipExists = true;
                    }
                    else if(holder[0].getName().equals(to) && holder[1].getName().equals(from))
                    {
                        relationshipExists = true;
                    }
                }

                if(!relationshipExists)
                {
                    UMLClass fromClass = getUML(from);
                    UMLClass toClass = getUML(to);

                    UMLClass[] arr = new UMLClass[2];
                    arr[0] = fromClass;
                    fromClass.addRelationship(to);
                    toClass.addRelationship(from);
                    arr[1] = toClass;
                
                    relationships.add(arr);                    
                }
                // If relationship exists already
                else
                {
                    System.out.println("The relationship between \"" + from + "\" and \"" + to + 
                        "\" cannot be added, as it already exists");
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
                UMLClass[] holder = relationships.get(i);

                if(holder[0].getName().equals(from) && holder[1].getName().equals(to)){
                    relationships.remove(i);
                    relationshipExists = true;
                }
                else if(holder[0].getName().equals(to) && holder[1].getName().equals(from))
                {
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
     * Prints all the relationships in the class diagram.
     */
    public void ListRelationships()
    {
        ListIterator<UMLClass[]>iterator = relationships.listIterator();
      
        while (iterator.hasNext())
        {
            UMLClass[] relPair = iterator.next();
            UMLClass from = relPair[0];
            UMLClass to = relPair[1];
            System.out.println("From: " + from.getName() + " To: " + to.getName());
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




    /**
     * Checks for the existence of traitName in traitType. 
     * Always checks for the existence of the source class.
     * If checking an attribute, checks the source class's attribute list for the existence of that attribute.
     * If checking a relationship, first checks if the destination class exists. If yes, then checks the 
     *      relationship list for the existence of that relationship.
     * 
     * Returns a string depending on the existence of the traitName or classSrcInput.
     * If the desired trait/class DNE, then a string will be returned specifying what does not exist.
     * @param classSrcInput The source class.
     * @param classDestInput The destination class, used only for relationship checking. Can be filled with 
     *      null otherwise.
     * @param traitName The name of the specific attribute to be checked. I left this as traitName because it
     *      will expand to include methods and fields in the future. Right now it's only attributes. Can be
     *      filled with null if not checking traits.
     * @param traitType The type of trait to be checked, i.e. "relationship" or "attribute" (as of now). Can
     *      be filled with null if not checking traits.
     * @return A string specifying what does not exist. If the desired trait/class does exist, "true" will be
     *      returned.
     *      If classSrcInput does not exist: "classSrcDNE"
     *      If classDest does not exist: "classDestDNE"
     *      If traitName does not exist and traitType == "attribute": "attributeDNE"
     */
    public String checkExistence(String classSrcInput, String classDestInput, String traitName, String traitType){
        UMLClass classSrc = diagram.get(classSrcInput);
        UMLClass classDest = diagram.get(classDestInput);
        String doesExist = "true";

        if (classSrc == null)
        {
            /** Updates doesExist if the source class DNE */
            doesExist = "classSrcDNE";
        }
        else if (traitType != null)
        {
            switch (traitType) 
            {
                case "attribute":
                    /** Finds index of desired attribute in source class */
                    int targetAttributeIndex = classSrc.getFields().indexOf(traitName);
                    if (targetAttributeIndex == -1)
                    {
                        doesExist = "attributeDNE";
                    }
                    break;
                
                case "relationship":
                    if (classDest == null)
                    {
                        doesExist = "classDestDNE";
                    }
                    else
                    {
                        // Need to search relationship list for this relationship
                    }
                    break;
                default:
                    break;
                    
            }
        }
        return doesExist;        
    }

    /**
     * Partners with checkExistence to print terminal outputs for when the class/trait DNE.
     * This is in a separate method from checkExistence so that a future isValidName function 
     * can check if a name is a duplicate by using the checkExistence function.  
     * 
     * @param classSrcInput The source class.
     * @param classDestInput The destination class, used only for relationship checking. Can be filled with 
     *      null otherwise.
     * @param traitName The name of the specific attribute to be checked. I left this as traitName because it
     *      will expand to include methods and fields in the future. Right now it's only attributes. Can be
     *      filled with null if not checking traits.
     * @param traitType The type of trait to be checked, i.e. "relationship" or "attribute" (as of now). Can
     *      be filled with null if not checking traits.
     */
    public void checkExistenceCLIOut(String classSrcInput, String classDestInput, String traitName, String traitType)
    {
        String doesExist = checkExistence(classSrcInput, classDestInput, traitName, traitType);

        if (doesExist != "true")
        {
            switch (doesExist)
            {
                case "classSrcDNE":
                    System.out.println("Error: The source class " + classSrcInput + " does not exist.");
                    break;
                case "classDestDNE":
                    System.out.println("Error: The destination class " + classDestInput + " does not exist.");
                    break;
                case "attributeDNE":
                    System.out.println("Error: The attribute " + traitName + " does not exist in the class " 
                    + classSrcInput + ".");
                    break;
                default:
                    break;
            }
        }
    }
}

