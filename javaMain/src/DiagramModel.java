/**
 * Authors: Jeffrey Flynn, Jeffrey Cutcher, David Jachimowicz
 * Date:
 * Description:
 */

import java.util.ArrayList;
import java.util.HashMap;
import javax.lang.model.SourceVersion;

public class DiagramModel {
    
    
    private HashMap<String, UMLClass> diagram;
    /*
    * Relationships between classes will be represented by 2-element arrays containing
    * references to two UMLClass objects. If this doesn't work out we could always
    * use strings.
    */
    private ArrayList<UMLClass[]> relationships;

    /**
     * Creates a new DiagramModel by initializing the HashMap<String, UMLClass> containing
     * the diagram data.
     */
    public DiagramModel() { // DONE
        diagram = new HashMap<String, UMLClass>();
    }

    /**
     * Adds a class to the class diagram, checking to make sure the name does not contain invalid
     * characters or exists elsewhere in the diagram.
     * @param name The name for the new class.
     * @return False if name is invalid, true if name is valid. 
     */
    public boolean addClass(String name) { // JEFF
        return false; // temporary return statement
    }

    /**
     * Deletes a class from the class diagram, returns false if name does not exist in class diagram.
     * @param name The name of the class to delete.
     * @return False if the name does not exist in the class diagram, true if it does.
     */
    public boolean deleteClass(String name) { // JEFFREY
        return false; // temporary return statement
    }

    /**
     * 
     */
    public boolean renameClass(String oldName, String newName) { // DAVID
        return false; // temporary return statement
        /* keep in mind that there is no easy way to rename keys in HashMap. Will likely
         * need to create new key-value pair and then delete the old one. Make sure reference
         * to UMLClass object stays the same.
        */
        
    }
    
    // this method also checks existence of classes
    public boolean addRelationship(String from, String to) { // JEFF
        return false; // temporary return statement
    }

    // this method also checks existence of classes
    public boolean deleteRelationship(String from, String to) { // JEFF
        return false; // temporary return statement
    }

    // this method also checks existence of class and validity of attName
    public boolean addAttribute(String className, String attName) { // JEFFREY
        return false; // temporary return statement
    }

    // this method also checks existence of class and attribute
    public boolean deleteAttribute(String className, String attName) { // DAVID
        return false; // temporary return statement
    }


    // this method also checks existence of class and attribute, and validity of new attribute name
    public boolean renameAttribute(String className, String oldName, String newName) { // JEFFREY
        return false; // temporary return statement
    }

    public HashMap<String, UMLClass> getClasses() { // DONE
        return diagram;
    }

    public UMLClass getClass(String name) { // JEFF
        return new UMLClass("Temp"); // temporary return
    }


    /**
     * Checks to make sure the name of a class does not contain any invalid
     * characters. Valid characters are all letters and numbers.
     * @param name The name to check.
     * @return True if name does not contain any invalid characters, false if it does.
     */
    public static boolean checkClassName(String name) { // DAVID???
        return true; // temporary return statement
    }

    /**
     * Checks to make sure the name of an attribute does not contain any invalid characters.
     * Valid characters are all letters and numbers, as well as parentheses ( ).
     * @param name The name to check.
     * @return True if name does not contain any invalid characters, false if it does.
     */
    public static boolean checkAttName(String name) { // DAVID???
        return true; // temporary return statement
    }

    // uses sourceversion to check name
    public static boolean checkNameSourceVersion(String name) { // DAVID
        return true; // temporary return
    }

    /**
     * Checks that a class by the name passed in exists in the diagram.
     * @param name The class name to check for.
     * @return True if class by passed in name exists in diagram, false if not.
     */
    public boolean classExists(String name) { // DAVID
        return false; // temporary return statement
        // HashMap.containsKey("...")
    }

    public boolean attributeExists(String className, String attName) { // DAVID
        return false; // temporary return statement
        // will need a for loop
    }

    public boolean relationshipExists(String from, String to) { // DAVID
        return false; // temporary return statement
        // will need a for loop
    }

    public ArrayList<UMLClass[]> getRelationships() { // DONE
        return relationships;
    }

    /**
     * Prints all the UMLClasses stored in diagram to the console.
     */
    public void listClasses() { // JEFF
        if(diagram.size() > 0) {
            diagram.forEach((name, value) -> System.out.println(value.toString() + "\n"));
        } else {
            System.out.println("The diagram contains no classes.");
        }
    }

    // prints class to console
    public void listClass(String name) { // DAVID?

    }

    // prints relationships to console
    public void listRelationships() { // DAVID?

    }

}
