package umleditor;

/**
 * Fields in the UML Class Diagram.
 */

public class Field {
    
    private String fieldName;
    private String fieldType;

    public Field(String fieldName, String fieldType){
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    public String getFieldName(){
        return this.fieldName;
    }

    public String getFieldType(){
        return this.fieldType;
    }

    public void renameField(String newFieldName){
        this.fieldName = newFieldName;
    }

    public void renameFieldType(String newFieldType){
        this.fieldType = newFieldType;
    }
}
