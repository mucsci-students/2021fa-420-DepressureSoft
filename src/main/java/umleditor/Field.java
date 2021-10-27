package umleditor;

public class Field {
    
    private String fieldName;

    public Field(String fieldName){
        this.fieldName = fieldName;
    }

    public String getFieldName(){
        return this.fieldName;
    }

    public void renameField(String newFieldName){
        this.fieldName = newFieldName;
    }
}
