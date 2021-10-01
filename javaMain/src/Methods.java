import java.util.ArrayList;

/**
 * Methods in the UML Class diagram.
 */

public class Methods {

    private String methodName;
    private ArrayList<String> parameters = new ArrayList<String>();

    /**
     * Creates a new method.
     * @param methodName
     */
    public Methods(String methodName){
        this.methodName = methodName;
    }

    /**
     * Returns the name of the method.
     * @return
     */
    public String getMethodName(){
        return this.methodName;
    }

    /**
     * Rename method if it exists in the class diagram.
     * @param oldMethod
     * @param newMethod
     */
    public void renameMethod(String newMethod){
        this.methodName = newMethod;
    }
}
