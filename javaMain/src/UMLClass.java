/**
 * Authors: Jeffrey Flynn, Jeffrey Cutcher, David Jachimowicz
 * Date: 9/15/21
 */


import java.util.ArrayList;

/**
 * Represents a class in a class diagram, including the name of the class, the class's attributes, and 
 *  classes related to this class. 
 */
public class UMLClass {

    /**
     * The name of the class.
     */
    private String dName;
    /**
     * The attributes of the class (which include both fields and methods).
     */
    private ArrayList<String> attributes = new ArrayList<String>();
    /**
     * Names of the classes that this class is related to.
     */
    private ArrayList<String> relationship = new ArrayList<String>();
    
    /**
     * Main constructor that sets the name of the new class.
     * @param name The name of the new class.
     */
    public UMLClass(String name) {
        this.dName = name;
    }
    
    /**
     * Renames the class representation.
     * @param newName The new name for the class representation.
     */
    public void renameClass(String newName){
        this.dName = newName;
    }

    /**
     * Adds a relationship to the class representation.
     * @param newRelation The name of the class that this class will be related to.
     */
    public void addRelationship(String newRelation){
        relationship.add(newRelation);
    }

    /**
     * Deletes a relationship to the class representation.
     * @param deleteRelation The name of the class that this class is related to that will be deleted.
     */
    public void deleteRelationship(String deleteRelation){
            relationship.remove(deleteRelation);
    }

    /**
     * Adds a new attribute the class representation.
     * @param newAttribute The name of the new attribute.
     */
    public void addAttribute(String newAttribute){
        attributes.add(newAttribute);
    }

    /**
     * Removes an existing attribute from the class representation.
     * @param removedAttribute The name of the attribute to delete.
     */
    public void removeAttribute(String removedAttribute){
        attributes.remove(removedAttribute);
    }

    /**
     * Renames an existing attribute in the class representation.
     * @param oldName Name of the attribute to rename.
     * @param newName New name for the attribute.
     */
    public void renameAttribute(String oldName,String newName){
        int index = attributes.indexOf(oldName);
        attributes.set(index, newName);
    }

    /**
     * Returns an ArrayList<String> of all the class representation's attributes.
     * @return The attributes field.
     */
    public  ArrayList<String> getAttributes(){
        return attributes;
    }

    /**
     * Returns an ArrayList<String> of all the classes related to this class.
     * @return The relationship field.
     */
    public  ArrayList<String> getRelationships(){
        return relationship;
    }

    /**
     * Returns the name of the class representation.
     * @return The dName field.
     */
    public  String getName(){
        return dName;
    }

}
