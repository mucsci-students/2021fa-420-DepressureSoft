package umleditor;

import java.util.ArrayList;

/**
 * Methods in the UML Class diagram.
 */

public class Method {

    private String methodName;
    private ArrayList<String> type = new ArrayList<String>();
    private ArrayList<String> parameters = new ArrayList<String>();

    /**
     * Creates a new method.
     * @param methodName
     */
    public Method(String methodName){
        this.methodName = methodName;
    }

    /**
     * Returns the name of the method.
     * @return The method name.
     */
    public String getMethodName(){
        return this.methodName;
    }

    /**
     * Rename method if it exists in the class diagram.
     * @param newName The new name for the method.
     */
    public void renameMethod(String newName){
        this.methodName = newName;
    }

    /**
     * Adds a parameter to the method.
     * @param pName the name of the parameter to add.
     */
    public void addParameter(String pName){
        parameters.add(pName);
    }

    /**
     * Removes a parameter from the method.
     * @param pName the name of the parameter to remove.
     */
    public void removeParameter(String pName){
        parameters.remove(pName);
    }

    /**
     * Renames a parameter in the method.
     * @param oldPName
     * @param newPName
     */
    public void renameParameter(String oldPName, String newPName){
        if(parameterExists(oldPName)){
            int index = parameters.indexOf(oldPName);
            parameters.set(index, newPName);
        }
    }

    /**
     * Removes all of the parameters from a method.
     */
    public void removeAllParameters() {
        parameters.clear();
    }

    /**
     * Checks to see if a paramter exists.
     * @param pName
     * @return
     */
    public boolean parameterExists(String pName){
        return (parameters.contains(pName));
    }

    /**
     * Returns String of the parameters. 
     * @return
     */
    public String getPName(){
        return parameters.toString();
    }

    /**
     * Returns ArrayList of the parameters.
     * @return ArrayList of parameters
     */
    public ArrayList<String> getParamList(){
        return parameters;
    }

    /**
     * Assigns a return type for the method.
     * @param typeName
     */
    public void addType(String typeName){
        type.add(typeName);
    }

    /**
     * Removes an assigned return type from a method. 
     * @param typeName
     */
    public void removeType(String typeName){
        type.remove(typeName);
    }

    /**
     * Changes a method type if the method and type exist in the class.
     * @param oldType
     * @param newType
     */
    public void renameType(String oldType, String newType){
        if (typeExists(oldType)){
            int index = type.indexOf(oldType);
            type.set(index, newType);
        }
    }

    /**
     * Checks to see if the type exists.
     * @param typeName
     * @return
     */
    public boolean typeExists(String typeName){
        return type.contains(typeName);
    }

    /**
     * Returns String of type(s).
     * @return
     */
    public String getTypeName(){
        return type.toString();
    }

    /**
     * Returns ArrayList of the types.
     * @return
     */
    public ArrayList<String> getTypeList(){
        return type;
    }
}
