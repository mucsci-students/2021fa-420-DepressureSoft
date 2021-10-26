package umleditor;

/**
 * Parameters of methods in the UML Class diagram. 
 */

public class Parameters {

    private String paramName;
    private String paramType;

    /**
     * Creates a new parameter.
     * @param paramName
     * @param paramType
     */
    public Parameters(String paramName, String paramType){
        this.paramName = paramName;
        this.paramType = paramType;
    }

    /**
     * Returns the name of the parameter.
     * @return
     */
    public String getParamName(){
        return this.paramName;
    }

    /**
     * Returns the type of the parameter,
     * @return
     */
    public String getParamType(){
        return this.paramType;
    }

    /**
     * Renames the parameter.
     * @param newParamName
     */
    public void renameParameter(String newParamName){
        this.paramName = newParamName;
    }

    /**
     * Renames the type of the parameter.
     * @param newPType
     */
    public void renamePType(String newPType){
        this.paramType = newPType;
    }
    
}
