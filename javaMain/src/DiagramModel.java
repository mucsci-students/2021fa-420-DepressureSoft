/**
 * Authors:
 * Date:
 * Description:
 */

import java.util.Scanner;
import java.util.ArrayList;
import javax.lang.model.SourceVersion;
// return SourceVersion.isIdentifier(name);


//When taking data grab list of attributes from UMLClass and search through before
/**
 *  boolean ans = ______.contains(_____);
 */
public class DiagramModel {
   private ArrayList<UMLClass> diagram = new ArrayList<UMLClass>();

    public void addClass(String name){
         UMLClass holder = new UMLClass(name);
         diagram.add(holder);
    }

    public void deleteClass(String entry){

    }

    private boolean isValid(String name,String where){
        return true;
    }


    public void Save(){

    }

    public void Load(){

    }

    public void ListClass(String entry){

    }

    public void ListClasses(){

    }

    public void ListRelationships(){
        
    }

    public void ListAttributes(String entry){
        
    }
}
