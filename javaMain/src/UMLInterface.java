import java.util.Scanner;
import java.util.ArrayList;

public class UMLInterface {
    public static void main(String[] args){

        DiagramModel holder = new DiagramModel();
        Scanner sc = new Scanner(System.in);
        String userEntry = "";
        String userEntry2 = "";
        String userEntry3 = "";

        System.out.println("Welcome to the text UML Diagram creator. To begin please select the action you would like to perform.");
        System.out.println("If you're new please use the 'Help' command to see the options avaliable.");
        while(!userEntry.equals("Exit"))
        {
            System.out.println("Please input your command.");
            userEntry = sc.nextLine();
            if(userEntry.equalsIgnoreCase("Add"))
            {
                System.out.println("Would you like to add a class, attribute or relationship?");
                userEntry = sc.nextLine();
                if(userEntry.equalsIgnoreCase("Class"))
                {
                    System.out.println("Please enter the name of the class you would like to add.");
                    userEntry = sc.nextLine();
                    holder.addClass(userEntry);
                }
                else if(userEntry.equalsIgnoreCase("Attribute"))
                {
                    System.out.println("Please enter the name of the class you would like to add the attribute to.");
                    userEntry = sc.nextLine();
                    boolean moreAttributes = true;
                    while(moreAttributes)
                    {
                        System.out.println("Please enter the name of the attribute you would like to add.");
                        userEntry2 = sc.nextLine();
                        holder.getUML(userEntry).addAttribute(userEntry2);
                        System.out.println("Would you like to add another attribute to the class?");
                        userEntry3 = sc.nextLine();
                        if(userEntry3.equalsIgnoreCase("No"))
                        {
                            moreAttributes = false;
                        }
                    }
                }
                else if(userEntry.equalsIgnoreCase("Relationship"))
                {
                    System.out.println("Please enter the first class within the relationship you would like to add.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter the second class within the relationship you would like to add.");
                    userEntry2 = sc.nextLine();
                    holder.addRelationship(userEntry,userEntry2);
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Rename"))
            {
                System.out.println("Would you like to rename a class or attribute?");
                userEntry = sc.nextLine();

                if(userEntry.equalsIgnoreCase("Class"))
                {
                    System.out.println("Please enter the name of the class you would like to rename.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter what you want to rename it to.");
                    userEntry2 = sc.nextLine();
                    holder.renameUMLClass(userEntry,userEntry2);
                }
                else if(userEntry.equalsIgnoreCase("Attribute"))
                {
                    System.out.println("Please enter the name of the class where the attribute is located.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter the name of the attribute you would like to rename.");
                    userEntry2 = sc.nextLine();
                    System.out.println("Please enter what you want to rename it to.");
                    userEntry3 = sc.nextLine();
                    holder.getUML(userEntry).renameAttribute(userEntry2,userEntry3);
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Delete"))
            {
                System.out.println("Would you like to delete a class, attribute or relationship?");
                userEntry = sc.nextLine();
                holder.deleteClass(userEntry);
                if(userEntry.equalsIgnoreCase("Class"))
                {
                    System.out.println("Please enter the name of the class you would like to remove.");
                    userEntry = sc.nextLine();
                    holder.deleteClass(userEntry);
                }
                else if(userEntry.equalsIgnoreCase("Attribute"))
                {
                    System.out.println("Please enter the name of the class where the attribute is located.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter the name of the attribute you would like to remove.");
                    userEntry2 = sc.nextLine();
                    holder.getUML(userEntry).removeAttribute(userEntry2);
                }
                else if(userEntry.equalsIgnoreCase("Relationship"))
                {
                    System.out.println("Please enter the first class within the relationship you would like to delete.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter the second class within the relationship you would like to delete.");
                    userEntry2 = sc.nextLine();
                    holder.deleteRelationship(userEntry,userEntry2);
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Display"))
            {
                System.out.println("Would you like to display 'one' class, 'all' classes, or their 'relationships'?");
                userEntry = sc.nextLine();
                if(userEntry.equalsIgnoreCase("One"))
                {
                    System.out.println("Please enter the name of the class you want to display.");
                    userEntry = sc.nextLine();
                    holder.listClass(userEntry);
                }
                else if(userEntry.equalsIgnoreCase("All"))
                {
                    holder.ListClasses();
                }
                else if(userEntry.equalsIgnoreCase("Relationships"))
                {
                    holder.ListRelationships();
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Exit"))
            {
                break;
            }
            else if(userEntry.equalsIgnoreCase("Save"))
            {

            }
            else if(userEntry.equalsIgnoreCase("Load"))
            {
                
            }
            else if(userEntry.equalsIgnoreCase("Help"))
            {
                System.out.println("Command List:");
                System.out.println("Note: 'Class' 'Attribute' and 'Relationship' are sub commands within 'Add', 'Rename', and 'Delete' all commands are 1 word.");
                System.out.println("Add - Prompts you to add either a 'Class', 'Attribute', or 'Relationship' ");
                System.out.println("Rename - Prompts you to rename either a 'Class' or an 'Attribute'");
                System.out.println("Delete - Prompts you to remove either a 'Class', 'Attribute', or 'Relationship'");
                System.out.println("Display - Gives the option to display either 'One' or 'All' classes.");
                System.out.println("Save - Saves the current existing diagram.");
                System.out.println("Load - Loads a diagram from file.");
                System.out.println("Help - Displays the possible commands able to be made within the program.");
            }
            else
            {
                System.out.println("Sorry, we don't recognize that command, please try again.");
            }
        }
    }
}
