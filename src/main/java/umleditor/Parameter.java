package umleditor;

public class Parameter {

    private String paramName;
    private String paramType;

    /**
     * Creates a new parameter.
     * @param paramName
     * @param paramType
     */
    public Parameter(String paramName, String paramType){
        this.paramName = paramName;
        this.paramType = paramType;
    }

    /**
     * Returns the parameter name.
     * @return 
     */
    public String getParamName(){
        return this.paramName;
    }

    /**
     * Returns the parameter type.
     * @return
     */
    public String getParamType(){
        return this.paramType;
    }
    
    /**
     * Renames a parameter.
     * @param newParamName
     */
    public void renameParameter(String newParamName){
        this.paramName = newParamName;
    }

    /**
     * Renames the parameter type. 
     * @param newType
     */
    public void renameParameterType(String newType){
        this.paramType = newType;
    }
}
