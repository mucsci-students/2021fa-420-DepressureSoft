package umleditor;

/**
 * Fields in the UML Class Diagram.
 */

public class Field {
    
    private String fieldName;
    private String fieldType;

    /**
     * Creates a new field. 
     * @param fieldName
     * @param fieldType
     */
    public Field(String fieldName, String fieldType){
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    /**
     * Returns the field name.
     * @return
     */
    public String getFieldName(){
        return this.fieldName;
    }

    /**
     * Returns the field type.
     * @return
     */
    public String getFieldType(){
        return this.fieldType;
    }

    /**
     * Renames the field. 
     * @param newFieldName
     */
    public void renameField(String newFieldName){
        this.fieldName = newFieldName;
    }

    /**
     * Renames the field type.
     * @param newFieldType
     */
    public void renameFieldType(String newFieldType){
        this.fieldType = newFieldType;
    }
}
