package umleditor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Public Interface responsible for connecting all programmed methods to the Terminal. 
 * Allows for user to modify diagrams as they please. 
 */
public class UMLInterface {
	
	public void displayWelcome() {
		System.out.println("  +---------------------------------------+");
		System.out.println("  |       UML DIAGRAM CREATOR v.3         |");
		System.out.println("  |                by DepressureSoft      |");
		System.out.println("  |                                       |");
		System.out.println("  |  Type \"help\" for a list of commands.  |");
		System.out.println("  +---------------------------------------+");
	}
	
	/**
	 * Gets an array of strings from the user.
	 * @param promptHeader The string to print before each command is entered.
	 * @return An ArrayList<String> of user commands
	 */
    public ArrayList<String> getInput(String promptHeader) {
    	ArrayList<String> result = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        System.out.print(promptHeader);
        String userEntry = "";
        userEntry += sc.nextLine();
        if(userEntry.trim().equalsIgnoreCase("exit")) {
        	boolean exit = yesNoDialog("Are you sure you want to exit?");
        	if(exit) {
        		result.add("exit");
        		return result;
        	} else {
        		return result;
        	}
        }
        else {
        	result = splitString(userEntry);
        }
        return result;
    }
    
    
    /**
     * Prints out string m.
     * @param m The string to print to the console.
     */
    public void print(String m) {
    	System.out.println(m);
    }
    
    /**
     * Splits a string at the locations of spaces into an ArrayList of substrings.
     * @param input The string to split.
     * @return An ArrayList of substrings from the original string.
     */
    private ArrayList<String> splitString(String input) {
    	ArrayList<String> result = new ArrayList<String>();
    	input = input.trim();
    	int start = 0;
    	int end = 0;
    	for(int i = 0; i < input.length(); i++) {
    		if(input.charAt(i) == ' ' || i == input.length() - 1) {
    			if(i == input.length() - 1) {
    				end = i + 1;
    				result.add(input.substring(start, end));
    			}
    			else {
	    			end = i;
	    			result.add(input.substring(start, end));
	    			start = end + 1;
    			}
    		}
    	}
    	return result;
    }
    
    /**
     * Makes a yes/no "dialog box". Prints message, and then returns true if user inputs "y" or false if users inputs "n"
     * 	or any other unrecognized command.
     * @param message The message for the "dialog box".
     * @return Boolean based on the user's input.
     */
    public static boolean yesNoDialog(String message) {
    	System.out.println(message + " (Y/N)");
    	Scanner sc = new Scanner(System.in);
    	String result;
    	result = sc.nextLine();
    	if(result.equalsIgnoreCase("y")) {
    		return true;
    	} else if(result.equalsIgnoreCase("n")) {
    		return false;
    	} else {
    		return false;
    	}
    }
    
        /*
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
                        System.out.println("Please enter the name of the field you would like to add.");
                        userEntry4 = sc.nextLine();
                        userEntry4 = userEntry4.trim();
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
                  
                    System.out.println("Please enter the name of the method you would like to add.");
                    userEntry4 = sc.nextLine();
                    userEntry4 = userEntry4.trim();
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

                    System.out.println("Please enter the name of the parameter you would like to add.");
                    userEntry5 = sc.nextLine();
                    userEntry5 = userEntry5.trim();
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
                userEntry2 = sc.nextLine();
                System.out.println("Please enter a name for your save file. (The .json extension will be appended automatically)");
                userEntry3 = sc.nextLine();
                controller.command(userEntry, userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
            }
            else if(userEntry.equalsIgnoreCase("Load")) 
            {
                System.out.println("Are you sure you want to load a new file? All unsaved work will be deleted. (y/n)");
                userEntry = sc.nextLine();
                if(userEntry.substring(0, 1).equalsIgnoreCase("y")) {
                    System.out.println("Please enter location of file to load. Example: \"/Users/jeff/Desktop/save.json\"");
                    userEntry2 = sc.nextLine();
                    controller.command("load", userEntry2, userEntry3, userEntry4, userEntry5, userEntry6);
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
    */
}
