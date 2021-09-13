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
        if(!classExists(name) && checkNameSourceVersion(name) && name.length() > 0) {
            diagram.put(name, new UMLClass(name));
            return true;
        } else {
            return false;
        }
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
    
    /**
     * Adds a relationship between two classes.
     * @param from The "parent" of the relationship, i.e. a superclass.
     * @param to The "child" of the relationship, i.e. a subclass.
     * @precondition from and to cannot contain the same class names, and
     *  a relationship cannot be made between two classes that already have
     *  a relationship.
     * @return True if adding a relationship was successful, false if not.
     */
    public boolean addRelationship(String from, String to) { // JEFF
        boolean classesExist = (classExists(from) && classExists(to));
        boolean notSameClass = !from.equals(to);
        boolean noExistingRelationship =
            !(relationshipExists(from, to) || relationshipExists(to, from));
        if(classesExist && notSameClass && noExistingRelationship) {
            UMLClass[] arr = new UMLClass[2];
            arr[0] = getClass(from);
            arr[1] = getClass(to);
            relationships.add(arr);
            return true;
        } else {
            return false;
        }
            
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
     * Checks to make sure name does not contain any invalid characters.
     * Valid characters are all letters and numbers, as well as parentheses ( ).
     * @param name The name to check.
     * @return True if name does not contain any invalid characters, false if it does.
     */
    public static boolean checkNameSourceVersion(String name) { // DAVID
        return SourceVersion.isIdentifier(name);
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
     * If the desired trait/class does exist, then a string of the value "true" will be returned.
     * @param classSrcInput The source class
     * @param classDestInput The destination class, used only for relationship checking. Can be filled with 
     *      null otherwise
     * @param traitName The name of the specific attribute to be checked. I left this as traitName because it
     *      will expand to include methods and fields in the future. Right now it's only attributes
     * @param traitType The type of trait to be checked, i.e. relationship or attribute (as of now)
     */
    public String checkExistence(String classSrcInput, String classDestInput, String traitName, String traitType){
        UMLClass classSrc = diagram.get(classSrcInput);
        UMLClass classDest = diagram.get(classDestInput);
        String doesExist = "true";

        if (classSrc == null)
        {
            // Updates doesExist if the source class DNE 
            doesExist = "classSrcDNE";
        }
        else if (traitType != null)
        {
            switch (traitType) 
            {
                case "attribute":
                    // Finds index of desired attribute in source class
                    int targetAttributeIndex = classSrc.getAttributes().indexOf(traitName);
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
     * @param classSrcInput
     * @param classDestInput
     * @param traitName
     * @param traitType
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

    public ArrayList<UMLClass[]> getRelationships() { // DONE
        return relationships;
    }

    // prints classes to console
    public void listClasses() { // JEFF

    }

    // prints class to console
    public void listClass(String name) { // DAVID?

    }

    // prints relationships to console
    public void listRelationships() { // DAVID?

    }

}
