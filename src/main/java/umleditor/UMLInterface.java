package umleditor;
import java.util.Scanner;

/**
 * Public Interface responsible for connecting all programmed methods to the Terminal. 
 * Allows for user to modify diagrams as they please. 
 */
public class UMLInterface {
    public static void main(String[] args){
        DiagramModel holder = new DiagramModel();
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);
        String userEntry = "";
        String userEntry2 = "";
        String userEntry3 = "";
        String userEntry4 = "";
        String userEntry5 = "";
        String userEntry6 = "";

        System.out.println("Welcome to the text UML Diagram creator. To begin please select the action you would like to perform.");
        System.out.println("If you're new please use the 'help' command to see the options avaliable.");
        while(!userEntry.equals("Exit"))
        {
            System.out.println("Please input your command.");
            userEntry = sc.nextLine();
            userEntry = userEntry.trim();
            if(userEntry.equalsIgnoreCase("Add"))
            {
            	userEntry = "add";
                System.out.println("Would you like to add a class, method, field, parameter, or relationship?");
                userEntry2 = sc.nextLine();
                userEntry2 = userEntry2.trim();
                if(userEntry2.equalsIgnoreCase("Class"))
                {
                	userEntry2 = "class";
                    System.out.println("Please enter the name of the class you would like to add.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry2.equalsIgnoreCase("Field"))
                {
                	userEntry2 = "field";
                    System.out.println("Please enter the name of the class you would like to add the field to.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    boolean moreFields = true;
                    while(moreFields)
                    {
                        System.out.println("Please enter the name and the type of the field you would like to add.");
                        userEntry4 = sc.nextLine();
                        userEntry4 = userEntry4.trim();
                        userEntry5 = sc.nextLine();
                        userEntry5 = userEntry5.trim();
                        controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                        System.out.println("Would you like to add another field to the class? 'yes' or 'no'");
                        userEntry4 = sc.nextLine();
                        userEntry4 = userEntry4.trim();
                        if(userEntry4.equalsIgnoreCase("No"))
                        {
                            moreFields = false;
                        }
                    }
                }
                else if(userEntry2.equalsIgnoreCase("Method"))
                {
                	userEntry2 = "method";
                    System.out.println("Please enter the name of the class you would like to add the method to.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                  
                    System.out.println("Please enter the name and the type of the method you would like to add.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                    userEntry5 = sc.nextLine();
                    userEntry5 = userEntry5.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                    
                }
                else if(userEntry2.equalsIgnoreCase("Parameter"))
                {
                	userEntry2 = "parameter";
                    System.out.println("Please enter the name of the class you would like to add the parameter to.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    
                    System.out.println("Please enter the name of the method you would like to add the parameter to.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();

                    System.out.println("Please enter the name and the type of the parameter you would like to add.");
                    userEntry5 = sc.nextLine();
                    userEntry5 = userEntry5.trim();
                    userEntry6 = sc.nextLine();
                    userEntry6.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                    
                }
                else if(userEntry2.equalsIgnoreCase("Relationship"))
                {
                	userEntry2 = "relationship";
                    System.out.println("Please enter the first class within the relationship you would like to add.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    
                    System.out.println("Please enter the second class within the relationship you would like to add.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                    
                    System.out.println("Please enter the type of relationship between the two classes."
                        + "\nCan be either \"aggregation\", \"composition\", \"inheritance\", or \"realization\".");
                    userEntry5 = sc.nextLine();
                    userEntry5 = userEntry5.trim();
                    if (getRelTypeFromString(userEntry5) != null) {
                        controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                    } else {
                        System.out.println("\"" + userEntry5 + "\" is not a valid relationship type.");
                    }
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Are you sure you would like to Exit? Y/N");
                    userEntry = sc.nextLine();
                    if(userEntry.equalsIgnoreCase("Y"))
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Rename"))
            {
            	userEntry = "rename";
                System.out.println("Would you like to rename a class, method, field, or parameter?");
                userEntry2 = sc.nextLine();
                userEntry2 = userEntry2.trim();
                if(userEntry2.equalsIgnoreCase("Class"))
                {
                	userEntry2 = "class";
                    System.out.println("Please enter the name of the class you would like to rename.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    System.out.println("Please enter what you want to rename it to.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry2.equalsIgnoreCase("Field"))
                {
                	userEntry2 = "field";
                    System.out.println("Please enter the name of the class where the field is located.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    System.out.println("Please enter the name of the field you would like to rename.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                    System.out.println("Please enter what you want to rename it to.");
                    userEntry5 = sc.nextLine();
                    userEntry5 = userEntry5.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry2.equalsIgnoreCase("Method"))
                {
                	userEntry2 = "method";
                    System.out.println("Please enter the name of the class where the method is located.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                  
                    System.out.println("Please enter the name of the method you would like to rename.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();

                    System.out.println("Please enter what you want to rename it to.");
                    userEntry5 = sc.nextLine();
                    userEntry5 = userEntry5.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                    
                }
                else if(userEntry2.equalsIgnoreCase("Parameter"))
                {
                	userEntry2 = "parameter";
                    System.out.println("Please enter the name of the class where the parameter is located.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                  
                    System.out.println("Please enter the name of the method where the parameter is located.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();

                    System.out.println("Please enter the name of the parameter you would like to rename.");
                    userEntry5 = sc.nextLine();
                    userEntry5 = userEntry5.trim();

                    System.out.println("Please enter what you want to rename it to.");
                    userEntry6 = sc.nextLine();
                    userEntry6 = userEntry6.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                    
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Are you sure you would like to Exit? Y/N");
                    userEntry = sc.nextLine();
                    if(userEntry.equalsIgnoreCase("Y"))
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Delete"))
            {
            	userEntry = "delete";
                System.out.println("Would you like to delete a class, method, parameter, field, or relationship?");
                userEntry2 = sc.nextLine();
                userEntry2 = userEntry2.trim();
                if(userEntry2.equalsIgnoreCase("Class"))
                {
                	userEntry2 = "class";
                    System.out.println("Please enter the name of the class you would like to remove.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry2.equalsIgnoreCase("Field"))
                {
                	userEntry2 = "field";
                    System.out.println("Please enter the name of the class where the field is located.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    
                    System.out.println("Please enter the name of the field you would like to remove.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry2.equalsIgnoreCase("Method"))
                {
                	userEntry2 = "method";
                    System.out.println("Please enter the name of the class you would like to delete the method from.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                  
                    System.out.println("Please enter the name of the method you would like to remove.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                    
                }
                else if(userEntry2.equalsIgnoreCase("Parameter"))
                {
                	userEntry2 = "parameter";
                    System.out.println("Please enter the name of the class you would like to remove the parameter from.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    
                    System.out.println("Please enter the name of the method you would like to remove the parameter from.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                    System.out.println("Would you like to remove 'one' or 'all' of off the parameters?");
                    userEntry5 = sc.nextLine();
                    userEntry5 = userEntry5.trim();
                    if(userEntry5.equalsIgnoreCase("One")){
                    	userEntry5 = "one";
                        System.out.println("Please enter the name of the parameter you would like to remove.");
                        userEntry6 = sc.nextLine();
                        userEntry6 = userEntry6.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                    }else if(userEntry5.equalsIgnoreCase("All")){
                    	userEntry5 = "all";
                        controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                    }else{
                        System.out.println("Sorry, we don't recognize that command, please try again.");
                    }
                }
                else if(userEntry2.equalsIgnoreCase("Relationship"))
                {
                	userEntry2 = "relationship";
                    System.out.println("Please enter the first class within the relationship you would like to delete.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    System.out.println("Please enter the second class within the relationship you would like to delete.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry.equalsIgnoreCase("Exit"))
                {
                    System.out.println("Are you sure you would like to Exit? Y/N");
                    userEntry = sc.nextLine();
                    if(userEntry.equalsIgnoreCase("Y"))
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("ChangeType"))
            {
            	userEntry = "changeType";
                System.out.println("Please enter the first class within the relationship you want to change the type of.");
                userEntry2 = sc.nextLine().trim();
                System.out.println("Please enter the second class within the relationship you want to change the type of.");
                userEntry3 = sc.nextLine().trim();
                System.out.println("Please enter the relationship type you would like to change this relationship to."
                    + "\nCan be either \"aggregation\", \"composition\", \"inheritance\", or \"realization\".");
                userEntry4 = sc.nextLine().trim();
                if (getRelTypeFromString(userEntry4) != null) {
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                } else {
                    System.out.println("\"" + userEntry4 + "\" is not a valid relationship type.");
                }

            }
            else if(userEntry.equalsIgnoreCase("Display"))
            {
            	userEntry = "display";
                System.out.println("Would you like to display 'one' class, 'all' classes, or their 'relationships'?");
                userEntry2 = sc.nextLine();
                userEntry2 = userEntry2.trim();
                if(userEntry2.equalsIgnoreCase("One"))
                {
                	userEntry2 = "one";
                    System.out.println("Please enter the name of the class you want to display.");
                    userEntry3 = sc.nextLine();
                    userEntry3 = userEntry3.trim();
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry2.equalsIgnoreCase("All"))
                {
                	userEntry2 = "all";
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry2.equalsIgnoreCase("Relationships"))
                {
                	userEntry2 = "relationships";
                    controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
                }
                else if(userEntry2.equalsIgnoreCase("Exit"))
                {
                    break;
                }
                else{
                    System.out.println("Sorry, we don't recognize that command, please try again.");
                }
            }
            else if(userEntry.equalsIgnoreCase("Exit"))
            {
                System.out.println("Are you sure you would like to Exit? Y/N");
                userEntry = sc.nextLine();
                if(userEntry.equalsIgnoreCase("Y"))
                break;
            }
            else if(userEntry.equalsIgnoreCase("Save"))
            {
                System.out.println("Please enter directory to save to. Example: \"/Users/jeff/Desktop/\"");
                userEntry = sc.nextLine();
                System.out.println("Please enter a name for your save file. (The .json extension will be appended automatically)");
                userEntry2 = sc.nextLine();
                holder.save(userEntry, userEntry2);
            }
            else if(userEntry.equalsIgnoreCase("Load")) 
            {
                System.out.println("Are you sure you want to load a new file? All unsaved work will be deleted. (y/n)");
                userEntry = sc.nextLine();
                if(userEntry.substring(0, 1).equalsIgnoreCase("y")) {
                    System.out.println("Please enter location of file to load. Example: \"/Users/jeff/Desktop/save.json\"");
                    userEntry2 = sc.nextLine();
                    holder.load(userEntry2);
                } else if(userEntry.substring(0, 1).equalsIgnoreCase("n")) {
                    // nothing
                } else {
                    System.out.println("Unrecognized command.");
                }
                
            }
            else if(userEntry.equalsIgnoreCase("Help"))
            {
                System.out.println("Command List:");
                System.out.println("Note: 'class', 'method', 'parameter', 'field' and 'relationship' are sub commands within 'add', 'rename', and 'delete' all commands are 1 word.");
                System.out.println("Add - Prompts you to add either a 'class', 'method', 'parameter', 'field', or 'relationship' ");
                System.out.println("Rename - Prompts you to rename either a 'class', 'method', 'parameter', or 'field'");
                System.out.println("Delete - Prompts you to remove either a 'class','method', 'parameter', 'field', or 'relationship'");
                System.out.println("ChangeType – Allows you to change the type of an existing relationship.");
                System.out.println("Display - Gives the option to display either 'one' or 'all' classes.");
                System.out.println("Save - Saves the current existing diagram.");
                System.out.println("Load - Loads a diagram from file.");
                System.out.println("Exit - Allows you to stop the program from running.");
                System.out.println("Help - Displays the possible commands able to be made within the program.");
                // Suggestion: Sample – Loads a sample class diagram, first prompting the user if they would like to save their current work.
                // Suggestion: Clear – Clears the class diagram, first prompting the user if they are sure they want to delete their current unsaved work.
            }
            else
            {
                System.out.println("Sorry, we don't recognize that command, please try again.");
            }
        }
        sc.close();
    }

    /**
     * Helper method that returns a value from the RelationshipType enum that matches the input string.
     * @param input The input string.
     * @reutn The correct RelationshipType enum value if input equals "aggregation", "composition", "inheritance", 
     *  or "realization", null if not.
     */
    private static Relationship.RelationshipType getRelTypeFromString(String input) {
        if (input.equalsIgnoreCase("aggregation")) {
            return Relationship.RelationshipType.AGGREGATION;
        } else if (input.equalsIgnoreCase("composition")) {
            return Relationship.RelationshipType.COMPOSITION;
        } else if (input.equalsIgnoreCase("inheritance")) {
            return Relationship.RelationshipType.INHERITANCE;
        } else if (input.equalsIgnoreCase("realization")) {
            return Relationship.RelationshipType.REALIZATION;
        } else {
            return null;
        }
    }
}
