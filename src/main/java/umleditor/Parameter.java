package umleditor;

public class Parameter {

    private String paramName;
    private String paramType;

    public Parameter(String paramName, String paramType){
        this.paramName = paramName;
        this.paramType = paramType;
    }

    public String getParamName(){
        return this.paramName;
    }

    public String getParamType(){
        return this.paramType;
    }
    
    public void renameParameter(String newParamName){
        this.paramName = newParamName;
    }

    
}
