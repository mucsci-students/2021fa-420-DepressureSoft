/**
 * Authors: Jeffrey Flynn, Jeffrey Cutcher, David Jachimowicz
 * Date:
 * Description:
 */


import java.util.ArrayList;

public class UMLClass {
    private String dName;
    private ArrayList<String> attributes = new ArrayList<String>();
    private ArrayList<String> relationship = new ArrayList<String>();
    
    //If user prompts that the diagram has a name, attributes and relationships. 
 /*   public UMLClass(String name, ArrayList<String> attributes,ArrayList<String> parent){
        this.dName = name;
        this.attributes = attributes;
        this.relationship = parent;
    }

    //If user prompts that the diagram has a name and attributes.
    public UMLClass(String name, ArrayList<String> attributes) {
        this.dName = name;
        this.attributes = attributes;
    }
*/
    //If user prompts that the diagram has a name.
    public UMLClass(String name) {
        this.dName = name;
    }
    
    //User enters a VALID name to change the current diagram into.
    public void renameClass(String newName){
        this.dName = newName;
    }

    //User enters a VALID diagram to add to the relationships
    public void addRelationship(String newRelation){
        relationship.add(newRelation);
    }

    //User enters an EXISTING relationship to be removed from the relationship ArrayList. 
    public void deleteRelationship(String deleteRelation){
            attributes.remove(deleteRelation);
    }
    //User enters a VALID attribute to the ArrayList.
    public void addAttribute(String newAttribute){
        attributes.add(newAttribute);
    }

    //User enters an EXISTING attribute to be removed from the arrayList. 
    public void removeAttribute(String removedAttribute){
        attributes.remove(removedAttribute);
    }

    //Run a for loop through the arrayList looking for the matching string. If none report Error.
    public void renameAttribute(String oldName,String newName){
            attributes.set(attributes.indexOf(oldName),newName);
    }

    //Grabs and returns attribute ArrayList.
    public  ArrayList<String> getAttributes(){
        return attributes;
    }

    //Grabs and returns relationships ArrayList.
    public  ArrayList<String> getRelationships(){
        return relationship;
    }

    //Grabs and returns UMLClass name.
    public  String getName(){
        return dName;
    }

}
