import java.util.ArrayList;
/**
 * Tester for the DiagramModel class. 
 */
public class tester {

    public static void main(String[] args){

        DiagramModel holder = new DiagramModel();

            System.out.println("Starting Test");
            
            System.out.println("Test #1: Adding Elements to the Diagram Model.");

            System.out.println("Expected: diagram1 with fields Field1, Field2, Diagram2, Diagram3 ");

            System.out.println("");

            System.out.println("Actual: ");
            holder.addClass("Diagram1");
            holder.getUML("Diagram1").addField("Field1");
            holder.getUML("Diagram1").addField("Field2");
            holder.addClass("Diagram2");
            holder.addClass("Diagram3");
            holder.ListClasses();
            System.out.println("--------------------------------------------------------");


            System.out.println("Test #2: Removing Elements from the Diagram Model.");

            System.out.println("Expected: Diagram1 and Diagram2 deleted, Diagram 3 remains.");
            
            System.out.println();

            System.out.println("Actual: ");
            holder.deleteClass("Diagram1");
            holder.deleteClass("Diagram2");
            holder.ListClasses();


            System.out.println("----------------------------------------------------------");

            System.out.println("Test #3: Elements that don't exist.");

            System.out.println("Expected: Error, objects do not exist. For every attempt.");

            System.out.println("");
            
            System.out.println("Actual: ");
            holder.addField("diagram444","field123");
            holder.deleteField("diagram1","field123");
            holder.renameField("diagram1","fieldttt","fieldneww");

            System.out.println("----------------------------------------------------------");

            System.out.println("Test #4: Adding Relationships to the Diagram Model.");

            System.out.println("Expected: Diagram4 -> Diagram5 succeeds, Diagram3 -> Diagram5 fails.");

            System.out.println("");
            
            System.out.println("Actual: ");
            holder.addClass("Diagram4");
            holder.addClass("Diagram5");
            //holder.addRelationship("Diagram4","Diagram5");
            //holder.addRelationship("Diagram3","Diagram5");
            holder.deleteRelationship("Diagram3","Diagram5");
            holder.ListRelationships();
    }



}

