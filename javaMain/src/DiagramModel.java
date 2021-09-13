/**
 * Authors:
 * Date:
 * Description:
 */

import java.util.Scanner;
import java.util.ArrayList;
<<<<<<< Updated upstream
import javax.lang.model.SourceVersion;
// return SourceVersion.isIdentifier(name);
=======
import java.util.HashMap;
import javax.lang.model.SourceVersion;
>>>>>>> Stashed changes


<<<<<<< Updated upstream
//When taking data grab list of attributes from UMLClass and search through before
/**
 *  boolean ans = ______.contains(_____);
 */
public class DiagramModel {
   private ArrayList<UMLClass> diagram = new ArrayList<UMLClass>();
=======
    /**
     * Adds a class to the class diagram, checking to make sure the name does not contain invalid
     * characters or exists elsewhere in the diagram.
     * @param name The name for the new class.
     * @return False if name is invalid, true if name is valid.
     */
    public boolean addClass(String name) {
        return false; // temporary return statement
    }
>>>>>>> Stashed changes

    public void addClass(String name){
         UMLClass holder = new UMLClass(name);
         diagram.add(holder);
    }

    public void deleteClass(String entry){

    }

    private boolean isValid(String name,String where){
        return true;
    }


<<<<<<< Updated upstream
    public void Save(){

=======
    // this method also checks existence of class and attribute, and validity of new attribute name
    public boolean renameAttribute(String className, String oldName, String newName) {
        return false; // temporary return statement
>>>>>>> Stashed changes
    }

    public void Load(){

    }

<<<<<<< Updated upstream
    public void ListClass(String entry){
=======
    
    public UMLClass getClass(String name) {
        return new UMLClass("Temp"); // temporary return
    }

    /**
     * Checks to make sure the name of a class does not contain any invalid
     * characters. Valid characters are all letters and numbers.
     * @param name The name to check.
     * @return True if name does not contain any invalid characters, false if it does.
     */
    public static boolean checkClassName(String name) {
        return false; // temporary return statement
    }
>>>>>>> Stashed changes

    }

<<<<<<< Updated upstream
    public void ListClasses(){
=======
    /**
     * Checks name with checkNameSourceVersion
     * @param name
     * @return
     */
    public static boolean checkNameSoureVersion(String name) {
        return false;
    }

    /**
     * Checks that a class by the name passed in exists in the diagram.
     * @param name The class name to check for.
     * @return True if class by passed in name exists in diagram, false if not.
     */
    public boolean classExists(String name) {
        return false; // temporary return statement
        // HashMap.containsKey("...")
    }
>>>>>>> Stashed changes

    }

    public void ListRelationships(){
        
    }

    public void ListAttributes(String entry){
        
    }
<<<<<<< Updated upstream
=======

    /**
     * Prints classes to console.
     */
    public void listClasses() {

    }

    /**
     * Prints a class to the console.
     * @param name
     */
    public void listClass(String name) {

    }

    /**
     * Prints all relationships to the console.
     */
    public void listRelationships() {

    }

>>>>>>> Stashed changes
}
