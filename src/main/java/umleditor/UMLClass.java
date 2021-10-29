package umleditor;
/**
 * Authors: Jeffrey Flynn, Jeffrey Cutcher, David Jachimowicz, Alex Balagurak, Jon Brennan
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
    private ArrayList<Field> fields = new ArrayList<Field>();
    /**
     * Names of the classes that this class is related to.
     */
    private ArrayList<String> relationship = new ArrayList<String>();
    /**
     * The methods of the class.
     */
    private ArrayList<Method> methods = new ArrayList<Method>();
  
    /**
     * Main constructor that sets the name of the new class.
     * @param name The name of the new class.
     */
    public UMLClass(String name) {
        this.dName = name;
    }

    public UMLClass(UMLClass other){
        this.dName = other.dName;
        this.fields = new ArrayList(other.fields);
        this.relationship = new ArrayList(other.relationship);
        for(Method m : other.methods){
            Method methodCopy = new Method(m);
            methods.add(methodCopy);
        }
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
     * @param methodName The name of the new method.
     */
    public void addMethod(String methodName, String methodType){
        methods.add(new Method(methodName, methodType));
    }

    /**
     * Removes an existing method from the class representation, if it exists.
     * @param methodName The name of the method to remove.
     */
    public void removeMethod(String methodName){
        for(int index = 0; index < methods.size(); index++) {
            if (methods.get(index).getMethodName().equals(methodName)) {
                methods.remove(index);
            }
        }
    }

    /**
     * Renames a method if it exists in the class representation. 
     * @param currentMethod 
     * @param newMethodName 
     */
    public void renameMethod(String currentMethod, String newMethodName){
        if(getMethod(currentMethod) != null) {
            getMethod(currentMethod).renameMethod(newMethodName);
        }
    }

    /**
     * Returns true if the method called methodName exists in this class.
     * @param methodName The name of the method to look for.
     * @return True if method exists, false if not.
     */
    public boolean methodExists(String methodName) {
        return (getMethod(methodName) != null);
    }

    /**
     * Gets the method called methodName, if it exists.
     * @param methodName the method to get.
     * @return The method called methodName
     */
    public Method getMethod(String methodName) {
        for(int index = 0; index < methods.size(); index++) {
            if (methods.get(index).getMethodName().equals(methodName)) {
                return methods.get(index);
            }
        }
        return null;
    }

    /**
     * Returns an ArrayList<Method> of all the class representation's methods.
     * @return The methods ArrayList.
     */
    public ArrayList<Method> getMethods(){
        return methods;
    }

    public ArrayList<String> getStringMethods(){
       ArrayList<String> holder = new ArrayList<String>();
        for(Method m: methods){
            holder.add(m.getMethodName());
        };
        return holder;
    }
    /**
     * Adds a new parameter to the class representation.
     * @param methodName The name of the method to add the parameter to.
     * @param pName The name of the parameter to add.
     */
    public void addParameter(String methodName, String pName, String pType){
        if (getMethod(methodName) != null) {
            getMethod(methodName).addParameter(pName, pType);
        }
    }

    /**
     * Removes an existing parameter from the class representation.
     * @param methodName The name of the method to remove the parameter from.
     * @param pName The name of the parameter to remove.
     */
    public void removeParameter(String methodName, String pName){
        if (getMethod(methodName) != null) {
            getMethod(methodName).removeParameter(pName);
        }
    }

    /**
     * Removes all the parameters from a method.
     * @param methodName The name of the method to remove all parameters from.
     */
    public void removeAllParameters(String methodName) {
        if (getMethod(methodName) != null) {
            getMethod(methodName).removeAllParameters();
        }
    }

    /**
     * Adds a new field the class representation.
     * @param newField The name of the new field.
     */
    public void addField(String newField, String fieldType){
        fields.add(new Field(newField, fieldType));
    }

    /**
     * Removes an existing field from the class representation.
     * @param removedField The name of the field to delete.
     */
    public void removeField(String removedField){
        for(int index = 0; index < fields.size(); index++) {
            if (fields.get(index).getFieldName().equals(removedField)) {
                fields.remove(index);
            }
        }
    }

    /**
     * Renames an existing field in the class representation.
     * @param oldName Name of the field to rename.
     * @param newName New name for the field.
     */
    public void renameField(String oldName, String newName){
        if(getField(oldName) != null) {
            getField(oldName).renameField(newName);
        }
    }

    public Field getField(String fieldName){
        for(int index = 0; index < fields.size(); index++) {
            if (fields.get(index).getFieldName().equals(fieldName)) {
                return fields.get(index);
            }
        }
        return null;
    }

    public boolean fieldExists(String fieldName) {
        return (getField(fieldName) != null);
    }

    /**
     * Returns an ArrayList<String> of all the class representation's fields.
     * @return The fields ArrayList.
     */
    public  ArrayList<Field> getFields(){
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
