import java.util.ArrayList;
/**
 * Tester for the DiagramModel class. 
 */
public class tester {

    public static void main(String[] args){

        DiagramModel holder = new DiagramModel();

            System.out.println("Starting Test");
            
            System.out.println("Test #1: Adding Elements to the Diagram Model.");

            System.out.println("Expected: diagram1 with attributes attribute1,attribute2, Diagram2, Diagram3 ");

            System.out.println("");

            System.out.println("Actual: ");
            holder.addClass("diagram1");
            holder.getUML("diagram1").addAttribute("attribute1");
            holder.getUML("diagram1").addAttribute("attribute2");
            holder.addClass("diagram2");
            holder.addClass("diagram3");
            holder.ListClasses();
            System.out.println("--------------------------------------------------------");


            System.out.println("Test #2: Removing Elements from the Diagram Model.");

            System.out.println("Expected: diagram1 with attributes attribute1, attribute2");
            
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
            holder.addAttribute("diagram444","attribute123");
            holder.deleteAttribute("diagram1","attribute123");
            holder.renameAttribute("diagram1","attributettt","attributeneww");

            System.out.println("----------------------------------------------------------");

            System.out.println("Test #4: Adding Relationships to the Diagram Model.");

            System.out.println("Expected: Two Diagram3 fails, Diagram 4->5 Succeed");

            System.out.println("");
            
            System.out.println("Actual: ");
            holder.addClass("Diagram4");
            holder.addClass("Diagram5");
            holder.addRelationship("Diagram4","Diagram5");
            holder.addRelationship("Diagram3","Diagram5");
            holder.deleteRelationship("Diagram3","Diagram5");
            holder.ListRelationships();
    }

}

