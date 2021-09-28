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
 *  adding/removing/renaming classes, attributes, and relationships between classes.
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
            System.out.println("Attributes: ");

            /** Prints attributes, prints special message if none */
            if (input.getAttributes().size() == 0)
            {
                System.out.println("There are no attributes in this class.");
            }
            else
            {
                for (int i = 0; i < input.getAttributes().size(); i++)
                {
                    System.out.println(input.getAttributes().get(i));
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
     * Adds an attribute to a class, given that the class exists and that the attribute is not a duplicate.
     * @param className The name of the class to add an attribute to.
     * @param attributeName The name of the attribute to be added.
     */
    public void addAttribute(String className, String attributeName)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);
        if(SourceVersion.isIdentifier(attributeName)){
        if(parentExists)
        {
           boolean attributeExists = parentClass.getAttributes().contains(attributeName); 

           if(!attributeExists)
           {
               parentClass.addAttribute(attributeName);
           }
           else
           {
                System.out.println("The attribute \"" + attributeName + 
                    "\" cannot be added, as it already exists in the parent class \"" + className + "\".");
           }
        }
        else
        {
            System.out.println("The attribute \"" + attributeName + 
                "\" cannot be added, as the parent class \"" + className + "\" does not exist.");  
        }
    }
    else{
        System.out.println(attributeName + "\" is not a proper name.");
    }
    }

    /**
     * Deletes an attribute from a class, given that the class and attribute both exists.
     * @param className The name of the class to add an attribute to.
     * @param attributeName The name of the attribute to be deleted.
     */
    public void deleteAttribute(String className, String attributeName)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);
    
        if(parentExists)
        {
           boolean attributeExists = parentClass.getAttributes().contains(attributeName); 

           if(attributeExists)
           {
               parentClass.removeAttribute(attributeName);
           }
           else
           {
                System.out.println("The attribute \"" + attributeName + 
                    "\" cannot be deleted, as it does not exist in the parent class \"" + className + "\".");
           }
        }
        else
        {
            System.out.println("The attribute \"" + attributeName + 
                "\" cannot be deleted, as the parent class \"" + className + "\" does not exist.");  
        }
    }

    /**
     * Renames an attribute from a class, given that the class exists, the attribute exists, and the new 
     *  attribute name does not exist.
     * @param className The name of the class to add an attribute to.
     * @param oldAttributeName The name of the attribute to be renamed.
     * @param newAttributeName The name that oldAttributeName will be renamed to.
     */
    public void renameAttribute(String className, String oldAttributeName, String newAttributeName)
    {
        UMLClass parentClass = getUML(className);
        boolean parentExists = classExists(className);
        if(SourceVersion.isIdentifier(newAttributeName)){
        if(parentExists)
        {
           boolean oldAttributeExists = parentClass.getAttributes().contains(oldAttributeName); 
           boolean newAttributeExists = parentClass.getAttributes().contains(newAttributeName); 

           if(oldAttributeExists && !newAttributeExists)
           {
               parentClass.renameAttribute(oldAttributeName, newAttributeName);
           }
           else if(!oldAttributeExists)
           {
                System.out.println("The attribute \"" + oldAttributeName + 
                    "\" cannot be renamed, as it does not exist in the parent class \"" + className + "\".");
           }
           else if(newAttributeExists)
           {
                System.out.println("The attribute \"" + oldAttributeName + 
                    "\" cannot be renamed to \"" + newAttributeName + "\", as \"" + newAttributeName + 
                    "\" already exists in the parent class \"" + className + "\".");
           }
        }
        else
        {
            System.out.println("The attribute \"" + oldAttributeName + 
                "\" cannot be renamed, as the parent class \"" + className + "\" does not exist.");  
        }
        }
        else{
            System.out.println(newAttributeName + "\" is not a proper name.");
        }
    }
}
