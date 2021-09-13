/**
 * Authors: Jeffrey Flynn, Jeffrey Cutcher, David Jachimowicz
 * Date:
 * Description:
 */

import java.util.ArrayList;

public class UMLClass {
    
    private String className;
    private ArrayList<String> attributes;

    /**
     * Main constructor that creates a new UMLClass object.
     * @param name The name of the class.
     * @precondition Name cannot be blank.
     */
    public UMLClass(String name) {
        this.className = name;
    }

    /**
     * Returns the arrayList<String> that represents the class's attributes.
     * @return The "attributes" field.
     */
    public ArrayList<String> getAttributes() {
        return this.attributes;
    }

    /**
     * Adds a new attribute to the attributes field.
     * @param name The name of the attribute to add.
     * @precondition Name cannot already exist within the class, and cannot be blank.
     */
    public void addAttribute(String name) {

    }

    /**
     * Deletes an attribute from the attributes field.
     * @param name The name of the attribute to delete.
     * @precondition Name must already exist as an attribute.
     */
    public void deleteAttribute(String name) {

    }

    /**
     * Returns the name stored in the className field that represents the name of a class.
     * @return value of className field.
     */
    public String getName() {
        return this.className;
    }

    /**
     * Changes the name stored in the className field that represents the name of a class.
     * @param name The new name for the class.
     * @precondition Name cannot be blank.
     */
    public void reName(String name) {
        this.className = name;
    }
}