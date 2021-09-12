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
     
        while(!userEntry.equals("Exit")){
            System.out.println("Please input your command.");
            userEntry = sc.nextLine();
            if(userEntry.equalsIgnoreCase("Add")){
                System.out.println("Would you like to add a class, attribute or relationship?");
                userEntry = sc.nextLine();
                if(userEntry.equalsIgnoreCase("Class")){
                    System.out.println("Please enter the name of the class you would like to add.");
                    userEntry = sc.nextLine();
                    holder.addClass(userEntry);
                }
                else if(userEntry.equalsIgnoreCase("Attribute")){
                    System.out.println("Please enter the name of the class you would like to add the attribute to.");
                    userEntry = sc.nextLine();
                    boolean moreAttributes = true;
                    while(moreAttributes){
                        System.out.println("Please enter the name of the attribute you would like to add.");
                        userEntry2 = sc.nextLine();
                        System.out.println("Would you like to add another attribute to the class?");
                        userEntry3 = sc.nextLine();
                        if(userEntry3.equalsIgnoreCase("No")){
                            moreAttributes = false;
                        }
                    }
                }
                else if(userEntry.equalsIgnoreCase("Relationship")){
                    System.out.println("Please enter the first class within the relationship you would like to add.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter the second class within the relationship you would like to add.");
                    userEntry2 = sc.nextLine();
                }
                else if(userEntry.equalsIgnoreCase("Exit")){
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Rename")){
                System.out.println("Would you like to rename a class or attribute?");
                userEntry = sc.nextLine();

                if(userEntry.equalsIgnoreCase("Class")){
                    System.out.println("Please enter the name of the class you would like to rename.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter what you want to rename it to.");
                    userEntry2 = sc.nextLine();
                }
                else if(userEntry.equalsIgnoreCase("Attribute")){
                    System.out.println("Please enter the name of the class where the attribute is located.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter the name of the attribute you would like to rename.");
                    userEntry2 = sc.nextLine();
                    System.out.println("Please enter what you want to rename it to.");
                    userEntry3 = sc.nextLine();
                }
                else if(userEntry.equalsIgnoreCase("Exit")){
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Delete")){
                System.out.println("Would you like to delete a class, attribute or relationship?");
                userEntry = sc.nextLine();

                if(userEntry.equalsIgnoreCase("Class")){
                    System.out.println("Please enter the name of the class you would like to remove.");
                    userEntry = sc.nextLine();
                }
                else if(userEntry.equalsIgnoreCase("Attribute")){
                    System.out.println("Please enter the name of the class where the attribute is located.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter the name of the attribute you would like to remove.");
                    userEntry2 = sc.nextLine();
                }
                else if(userEntry.equalsIgnoreCase("Relationship")){
                    System.out.println("Please enter the first class within the relationship you would like to delete.");
                    userEntry = sc.nextLine();
                    System.out.println("Please enter the second class within the relationship you would like to delete.");
                    userEntry2 = sc.nextLine();
                }
                else if(userEntry.equalsIgnoreCase("Exit")){
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Display")){
                System.out.println("Would you like to display one class or all of them?");
                userEntry = sc.nextLine();
                if(userEntry.equalsIgnoreCase("One")){
                    System.out.println("Please enter the name of the class you want to display.");
                    userEntry = sc.nextLine();
                }
                else if(userEntry.equalsIgnoreCase("All")){

                }
                else if(userEntry.equalsIgnoreCase("Exit")){
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Exit")){
                break;
            }
            else if(userEntry.equalsIgnoreCase("Save")){

            }
            else if(userEntry.equalsIgnoreCase("Load")){
                
            }
            else{
                System.out.println("Sorry, we don't recognize that command, please try again.");
            }
        }
    }
}
