import java.util.ArrayList;

/**
 * Methods in the UML Class diagram.
 */

public class Method {

    private String methodName;
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

    public void removeAllParameters() {
        parameters.clear();
    }
}
