package umleditor;

import java.util.ArrayList;

/**
 * Methods in the UML Class diagram.
 */

public class Method {

    private String methodName;
    private String methodType;
    private ArrayList<Parameters> parameters = new ArrayList<Parameters>();

    /**
     * Creates a new method.
     * @param methodName
     */
    public Method(String methodName, String methodType){
        this.methodName = methodName;
        this.methodType = methodType;
    }

    /**
     * Returns the name of the method.
     * @return The method name.
     */
    public String getMethodName(){
        return this.methodName;
    }

    /**
     * Returns the type of the method. 
     * @return
     */
    public String getMethodType(){
        return this.methodType;
    }

    /**
     * Renames/sets the type of a method.
     * @param newMethodType
     */
    public void renameMethodType(String newMethodType){
        this.methodType = newMethodType;
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
        parameters.add(new Parameters(pName, pType));
    }

    /**
     * Removes a parameter from the method.
     * @param pName the name of the parameter to remove.
     */
    public void removeParameter(String pName){
        for(int index = 0; index < parameters.size(); index++) {
            if (parameters.get(index).getParamName().equals(pName)) {
                parameters.remove(index);
            }
        }
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
     * Returns the parameter if it exists.
     * @param pName
     * @return
     */
    public Parameters getParam(String pName){
        for(int index = 0; index < parameters.size(); index++) {
            if (parameters.get(index).getParamName().equals(pName)) {
                return parameters.get(index);
            }
        }
        return null;
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
    public ArrayList<Parameters> getParamList(){
        return parameters;
    }
}
