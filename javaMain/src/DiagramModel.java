/**
 * Authors:
 * Date:
 * Description:
 */

import java.util.ArrayList;
import javax.lang.model.SourceVersion;
import java.util.HashMap;
import java.util.ListIterator;
// return SourceVersion.isIdentifier(name);


//When taking data grab list of attributes from UMLClass and search through before
/**
 *  boolean ans = ______.contains(_____);
 */
public class DiagramModel {
    private HashMap<String, UMLClass> diagram = new HashMap<String, UMLClass>();
    private ArrayList<UMLClass[]> relationships;

    public void addClass(String name){
        UMLClass holder = new UMLClass(name);
        diagram.put(name, holder);
    }

    /**
     *Takes the String entered and searches the ArrayList for the object to delete.
     */
    public void deleteClass(String entry){
        diagram.remove(entry);
        
    }

    public void Save(){

    }

    public void Load(){

    }

    public void listClass(String className)
    {
        UMLClass input = diagram.get(className);

        System.out.println("Name: " + input.getName());
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

    public void ListClasses(){
        diagram.forEach((k,v) -> listClass(k));
    }

    public void addRelationship(String from, String to)
    {
        UMLClass fromClass = getUML(from);
        UMLClass toClass = getUML(to);

        UMLClass[] arr = new UMLClass[2];
        arr[0] = fromClass;
        fromClass.addRelationship(to);
        toClass.addRelationship(from);
        arr[1] = toClass;
        relationships.add(arr);
    }

    public void deleteRelationship(String from, String to)
    {
        ListIterator<UMLClass[]> iter1 = relationships.listIterator();
        UMLClass[] lookingFor = new UMLClass[2];
        lookingFor[0] = getUML(from);
        lookingFor[1] = getUML(to);

        while(iter1.hasNext()) {
            if(iter1.next().equals(lookingFor)) {
                relationships.remove(iter1.nextIndex() - 1); // need to test if this returns the correct index
            }
        }
    }

    public void ListRelationships()
    {
        ListIterator<UMLClass[]>iterator = relationships.listIterator();
        while (iterator.hasNext())
        {
            UMLClass[] relPair = iterator.next();
            UMLClass from = relPair[0];
            UMLClass to = relPair[1];
            System.out.println("From: " + from + "To: " + to);
        }
    }

    public void ListAttributes(String entry){
        
    }

    //This class is neccessary for adding attributes to already existing diagrams.
    public UMLClass getUML(String name){
        return diagram.get(name);
    }


    public void renameUMLClass(String oldName, String newName)
    {
        UMLClass renamedClass = diagram.get(oldName);
        renamedClass.renameClass(newName);
        diagram.remove(oldName);
        diagram.put(newName, renamedClass);
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

