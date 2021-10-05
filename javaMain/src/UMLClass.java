/**
 * Authors: Jeffrey Flynn, Jeffrey Cutcher, David Jachimowicz, Alex Balagurak
 * Date: 9/15/21
 */


import java.util.ArrayList;

/**
 * Represents a class in a class diagram, including the name of the class, the class's fields, and 
 *  classes related to this class. 
 */
public class UMLClass {

    /**
     * The name of the class.
     */
    private String dName;
    /**
     * The fields of the class (does not include methods).
     */
    private ArrayList<String> fields = new ArrayList<String>();
    /**
     * Names of the classes that this class is related to.
     */
    private ArrayList<String> relationship = new ArrayList<String>();
    /**
     * The methods of the class.
     */
    private ArrayList<Methods> methods = new ArrayList<Methods>();
    
    /**
     * The parameters of a method in a class.
     */
    private ArrayList<String> parameters = new ArrayList<String>();
    
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
     * Adds a new method to the class representation.
     * @param newMethod
     */
    public void addMethod(Methods newMethod){
        methods.add(newMethod);
    }

    /**
     * Removes an existing method from the class representation.
     * @param removeMethod
     */
    public void removeMethod(Methods removeMethod){
        methods.remove(removeMethod);
    }

    /**
     * Renames a method if it exists in the class representation. 
     * @param currentMethod
     * @param newMethodName
     */
    public void renameMethod(String currentMethod, String newMethodName){
        int index = methods.indexOf(methods.equals(currentMethod));
        methods.get(index).renameMethod(newMethodName);
    }

    /**
     * Adds a new parameter to the class representation.
     * @param pName
     */
    public void addParameter(String pName){
        parameters.add(pName);
    }

    /**
     * Removes an existing parameter from the class representation.
     * @param pName
     */
    public void removeParameters(String pName){
        parameters.remove(pName);
    }

    /**
     * Renames a parameter as long as it exists in the class representation. 
     * @param oldParam
     * @param newParam
     */
    public void renameParameter(String oldParam, String newParam){
        int index = parameters.indexOf(oldParam);
        parameters.get(index) = renameParameter(newParam);
    }

    /**
     * Adds a new field the class representation.
     * @param newField The name of the new field.
     */
    public void addField(String newField){
        fields.add(newField);
    }

    /**
     * Removes an existing field from the class representation.
     * @param removedField The name of the field to delete.
     */
    public void removeField(String removedField){
        fields.remove(removedField);
    }

    /**
     * Renames an existing field in the class representation.
     * @param oldName Name of the field to rename.
     * @param newName New name for the field.
     */
    public void renameField(String oldName, String newName){
        int index = fields.indexOf(oldName);
        fields.set(index, newName);
    }

    /**
     * Returns an ArrayList<String> of all the class representation's fields.
     * @return The fields ArrayList.
     */
    public  ArrayList<String> getFields(){
        return fields;
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
