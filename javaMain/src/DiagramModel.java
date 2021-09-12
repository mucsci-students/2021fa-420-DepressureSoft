/**
 * Authors:
 * Date:
 * Description:
 */

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

    /**
     *Takes the String entered and searches the ArrayList for the object to delete.
     */
    public void deleteClass(String entry){
        int i = diagram.size();

        for(int j = 0; j < i; j++){
            UMLClass holder = diagram.get(j);
            if(holder.getName() == entry){
                diagram.remove(j);
                break;
            }
        }
        
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
        int i = diagram.size();

        for(int j = 0; j < i; j++){
            UMLClass holder = diagram.get(j);
            System.out.println(holder.getName());
            for(int k = 0; k < holder.getAttributes().size(); k++){
                System.out.println(holder.getAttributes().get(k));
            }
            
        }
    }

    public void ListRelationships(){
        
    }

    public void ListAttributes(String entry){
        
    }

    //This class is neccessary for adding attributes to already existing diagrams.
    public UMLClass getUML(String name){
        int i = diagram.size();
        for(int j = 0; j < i; j++){
            UMLClass holder = diagram.get(j);
            if(holder.getName() == name){
                return holder;
            }
        }
        return null;
    }
}
