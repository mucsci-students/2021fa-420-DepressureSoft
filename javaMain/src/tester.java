public class tester {

    public static void main(String[] args){

        DiagramModel holder = new DiagramModel();

            System.out.println("Starting Test");

            System.out.println("Test #1: Adding Elements to the Diagram Model.");
            holder.addClass("Diagram1");
            UMLClass tester1 = holder.getUML("Diagram1");
            tester1.addAttribute("Attribute1");
            tester1.addAttribute("Attribute2");
            holder.addClass("Diagram2");
            holder.addClass("Diagram3");
            holder.ListClasses();

            System.out.println("Test #2: Removing Elements from the Diagram Model.");
            holder.deleteClass("Diagram1");
            holder.deleteClass("Diagram2");
            holder.ListClasses();

    }

}

