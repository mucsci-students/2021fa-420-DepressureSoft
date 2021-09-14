/**
 * Authors:
 * Date:
 * Description:
 */

import java.util.ArrayList;
import javax.lang.model.SourceVersion;
import java.util.HashMap;
// return SourceVersion.isIdentifier(name);


//When taking data grab list of attributes from UMLClass and search through before
/**
 *  boolean ans = ______.contains(_____);
 */
public class DiagramModel {

   private ArrayList<UMLClass> diagramOLD = new ArrayList<UMLClass>();
private HashMap<String, UMLClass> diagram = new HashMap<String, UMLClass>();
    public void addClass(String name){
        UMLClass holder = new UMLClass(name);
        diagram.put(name, holder);
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

    public void listClass(String className)
    {
        if (checkExistence(className, null, null, null) == "true"){
            UMLClass input = diagram.get(className);

            System.out.println("Name: \n" + input.getName());
            System.out.println("Attributes: ");

            /** Prints attributes, prints special message if none */
            if (input.getAttributes().size() == 0)
            {
                System.out.println("There are no attributes in this class.");
            }
            else
            {
                for (int i = 0; i < input.getAttributes().size(); i++)
                {
                    System.out.println(input.getAttributes().get(i));
                }
            }
        }
        else
        {
            checkExistenceCLIOut(className, null, null, null);
        }
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

    public void renameUMLClass(String oldName, String newName)
    {
        String oldNameExists = checkExistence(oldName, null, null, null);
        String newNameExists = checkExistence(newName, null, null, null);
        if (oldNameExists == "true" && newNameExists != "true")
        {
            UMLClass renamedClass = diagram.get(oldName);
            renamedClass.renameClass(newName);
            diagram.remove(oldName);
            diagram.put(newName, renamedClass);
        }
        else if (oldNameExists != "true")
        {
            System.out.println("The class to be renamed, " + oldName + ", does not exist.");
        }
        else 
        {
            System.out.println("The new name \"" + newName + "\" for the class " + 
                oldName + " already exists as a class.");
        }
    }

    public String checkExistence(String classSrcInput, String classDestInput, String traitName, String traitType){
        UMLClass classSrc = diagram.get(classSrcInput);
        UMLClass classDest = diagram.get(classDestInput);
        String doesExist = "true";

        if (classSrc == null)
        {
            /** Updates doesExist if the source class DNE */
            doesExist = "classSrcDNE";
        }
        else if (traitType != null)
        {
            switch (traitType) 
            {
                case "attribute":
                    /** Finds index of desired attribute in source class */
                    int targetAttributeIndex = classSrc.getAttributes().indexOf(traitName);
                    if (targetAttributeIndex == -1)
                    {
                        doesExist = "attributeDNE";
                    }
                    break;
                
                case "relationship":
                    if (classDest == null)
                    {
                        doesExist = "classDestDNE";
                    }
                    else
                    {
                        // Need to search relationship list for this relationship
                    }
                    break;
                default:
                    break;
                    
            }
        }
        return doesExist;        
    }

    /**
     * Partners with checkExistence to print terminal outputs for when the class/trait DNE.
     * This is in a separate method from checkExistence so that a future isValidName function 
     * can check if a name is a duplicate by using the checkExistence function.  
     * 
     * @param classSrcInput
     * @param classDestInput
     * @param traitName
     * @param traitType
     */
    public void checkExistenceCLIOut(String classSrcInput, String classDestInput, String traitName, String traitType)
    {
        String doesExist = checkExistence(classSrcInput, classDestInput, traitName, traitType);

        if (doesExist != "true")
        {
            switch (doesExist)
            {
                case "classSrcDNE":
                    System.out.println("Error: The source class " + classSrcInput + " does not exist.");
                    break;
                case "classDestDNE":
                    System.out.println("Error: The destination class " + classDestInput + " does not exist.");
                    break;
                case "attributeDNE":
                    System.out.println("Error: The attribute " + traitName + " does not exist in the class " 
                    + classSrcInput + ".");
                    break;
                default:
                    break;
            }
        }
    }
}

