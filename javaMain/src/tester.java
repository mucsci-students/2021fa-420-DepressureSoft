public class tester {

    public static void main(String[] args){

        DiagramModel holder = new DiagramModel();

            System.out.println("Starting Test");

            System.out.println("Test #1: Adding Elements to the Diagram Model.");
            holder.addClass("Diagram1");
            UMLClass tester1 = holder.getUML("Diagram1");
            tester1.addAttribute("Attribute1");
            tester1.addAttribute("Attribute2");
            tester1.renameAttribute("Attribute2","RenamedAttribute");
            tester1.removeAttribute("Attribute1");
            holder.addClass("Diagram2");
            UMLClass tester2 = holder.getUML("Diagram2");
            tester2.addAttribute("YOUSUCK");
            tester2.addAttribute("YOUSUCK2");
            tester2.addAttribute("YOUSUCK3");
            holder.addClass("Diagram3");
            holder.ListClasses();
            System.out.println("--------------------------------------------------------");

        
            System.out.println("Test #2: Removing Elements from the Diagram Model.");
            holder.deleteClass("Diagram1");
            holder.deleteClass("Diagram2");
            holder.ListClasses();


            System.out.println("----------------------------------------------------------");

            System.out.println("Test #3: Printing Elements from the Diagram Model.");
            holder.listClass("Diagram3");


            System.out.println("----------------------------------------------------------");

            System.out.println("Test #4: Adding Relationships to the Diagram Model.");
            holder.addClass("Diagram4");
            holder.addClass("Diagram5");
            holder.addRelationship("Diagram4","Diagram5");
            holder.addRelationship("Diagram3","Diagram5");
            holder.deleteRelationship("Diagram3","Diagram5");
            holder.ListRelationships();
    }

}

