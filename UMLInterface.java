import java.util.Scanner;
import java.util.ArrayList;

public class UMLInterface {
    public static void main(String[] args){

        DiagramModel holder = new DiagramModel();
        Scanner sc = new Scanner(System.in);
        String userEntry = "";

        System.out.println("Welcome to the text UML Diagram creator. To begin please select the action you would like to perform.");
     
        while(!userEntry.equals("Exit")){
            userEntry = sc.nextLine();
            if(userEntry.equalsIgnoreCase("Add Class")){
                System.out.println("Please enter the name of the class you would like to add.");
                userEntry = sc.nextLine();
                holder.addClass(userEntry);
            }
            if(userEntry.equalsIgnoreCase("Delete")){
                //deleteClass(userEntry);
            }
        }
    }
}
