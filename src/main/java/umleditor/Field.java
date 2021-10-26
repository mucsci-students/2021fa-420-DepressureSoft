package umleditor;

/**
 * Fields in the UML Class Diagram.
 */

public class Field {
    
    private String fieldName;
    private String fieldType;

    /**
     * Creates a new field attribute with a name and a type.
     * @param fieldName
     * @param fieldType
     */
    public Field(String fieldName, String fieldType){
        this.fieldName = fieldName;
        this.fieldType = fieldType;
    }

    /**
     * Returns the name of the field. 
     * @return
     */
    public String getFieldName(){
        return this.fieldName;
    }

    /**
     * Returns the type of the field. 
     * @return
     */
    public String getFieldType(){
        return this.fieldType;
    }

    /**
     * Renames the field if it exists in the class diagram. 
     * @param newFieldName
     */
    public void renameField(String newFieldName){
        this.fieldName = newFieldName;
    }

    /**
     * Renames the field type if it exists in the class diagram.
     * @param newFieldType
     */
    public void renameFieldType(String newFieldType){
        this.fieldType = newFieldType;
    }
}
