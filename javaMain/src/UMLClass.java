/**
 * Authors: Jeffrey Flynn, Jeffrey Cutcher, David Jachimowicz
 * Date:
 * Description:
 */


import java.util.ArrayList;

public class UMLClass {
    String dName;
    ArrayList<String> attributes = new ArrayList<String>();
    private ArrayList<String> relationship = new ArrayList<String>();

    public UMLClass(String name, ArrayList<String> attributes,ArrayList<String> parent){
        this.dName = name;
        this.attributes = attributes;
        this.relationship = parent;
    }
    //If there is no parent/relation?
    public UMLClass(String name, ArrayList<String> attributes) {
        this.dName = name;
        this.attributes = attributes;
    }
    public void renameClass(String newName){
        this.dName = newName;
    }
    public void addRelationship(String newRelation){

    }
    //
    public void deleteRelationship(String deleteRelation){

    }
    //
    public void addAttribute(String newAttribute){

    }
    //Run a for loop through the arrayList to verify there's an attribute to remove, if not report error.
    public void removeAttribute(String removedAttribute){

    }

    //Run a for loop through the arrayList looking for the matching string. If none report Error.
    public void renameAttribute(String newName){

    }

}
