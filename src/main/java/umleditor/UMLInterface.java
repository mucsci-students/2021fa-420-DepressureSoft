package umleditor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Public Interface responsible for connecting all programmed methods to the Terminal. 
 * Allows for user to modify diagrams as they please. 
 */
public class UMLInterface {
	
	/**
	 * Displays a "splash screen" to the user.
	 */
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
}
