package umleditor;

import java.util.ArrayList;

/**
 * Methods in the UML Class diagram.
 */

public class Method {

    private String methodName;
    private String methodType;
    private ArrayList<Parameter> parameters = new ArrayList<Parameter>();

    /**
     * Creates a new method.
     * @param methodName
     */
    public Method(String methodName, String methodType){
        this.methodName = methodName;
        this.methodType = methodType;
    }

    /**
     * Creates new method based on existing one (copy constructor)
     * @param other - the other method
     */
    public Method(Method other){
        this.methodName = other.methodName;
        this.parameters = new ArrayList(other.parameters);
    }

    /**
     * Returns the name of the method.
     * @return The method name.
     */
    public String getMethodName(){
        return this.methodName;
    }

    /**
     * Return the method return type.
     * @return
     */
    public String getMethodType(){
        return this.methodType;
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
    public void addParameter(String pName, String pType){
        parameters.add(new Parameter(pName, pType));
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
            getParam(oldPName).renameParameter(newPName);
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
        return (getParam(pName) != null);
    }

    /**
     * Returns the parameter of the method.
     * @param pName
     * @return
     */
    public Parameter getParam(String pName){
        for(int index = 0; index < parameters.size(); index++) {
            if (parameters.get(index).getParamName().equals(pName)) {
                return parameters.get(index);
            }
        }
        return null;
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
    public ArrayList<Parameter> getParamList(){
        return parameters;
    }
}
