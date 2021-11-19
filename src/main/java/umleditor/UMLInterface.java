package umleditor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.jline.builtins.Completers.Completer;
import org.jline.console.impl.Builtins.Command;
import org.jline.reader.History;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.MaskingCallback;
import org.jline.reader.ParsedLine;
import org.jline.reader.LineReader.Option;
import org.jline.reader.impl.DefaultParser;

import org.jline.reader.impl.completer.AggregateCompleter;
import org.jline.reader.impl.completer.ArgumentCompleter;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

/**
 * Public Interface responsible for connecting all programmed methods to the Terminal. 
 * Allows for user to modify diagrams as they please. 
 */
public class UMLInterface {

	private LineReader reader;
	private Terminal terminal;
	private ParsedLine parser;

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

	 /**public ArrayList<String> startRead(ArrayList<String> testread) {
		ArrayList<String> result = new ArrayList<String>();
		while(true){
			String line = null;
			line = reader.readLine("Hello");
			line = line.trim();

			ParsedLine parsedLine = reader.getParsedLine();
			String[] temp1 = parsedLine.words().toArray(new String[parsedLine.words().size()]);
			result = new ArrayList<>(Arrays.asList(temp1));
			return result;

			//TabCompletion completer = new TabCompletion();

			//reader = LineReaderBuilder.builder().terminal(terminal).completer(completer).variable(LineReader.MENU_COMPLETE, true).parser(parser).build();
		}
	} */
	
	/**
	 * Gets an array of strings from the user.
	 * @param promptHeader The string to print before each command is entered.
	 * @return An ArrayList<String> of user commands
	 */
    public ArrayList<String> getInput(String promptHeader) {

		try{
			terminal = TerminalBuilder.builder().system(true).build();
			AggregateCompleter comp = new TabCompletion().updateCompleter();
			reader = LineReaderBuilder.builder().terminal(terminal).completer(comp).variable(LineReader.MENU_COMPLETE, true).build();
            	
		}catch(IOException e){
			System.out.println(e);
		}

    	ArrayList<String> result = new ArrayList<String>();
		String line = null;
        //Scanner sc = new Scanner(System.in);
        //System.out.print(promptHeader);
        //String userEntry = "";
        //userEntry += sc.nextLine();
		line = reader.readLine(promptHeader);

        if(line.trim().equalsIgnoreCase("exit")) {
        	boolean exit = yesNoDialog("Are you sure you want to exit?");
        	if(exit) {
        		result.add("exit");
        		return result;
        	} else {
        		return result;
        	}
        }
        else {
        	//result = splitString(userEntry);
			parser = reader.getParsedLine();
			String[] arrayLine = parser.words().toArray(new String[parser.words().size()]);
			result = new ArrayList<String>(Arrays.asList(arrayLine));
			
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
    	for(int i = 0; i < result.size(); i++)
    	{
    		if(result.get(i).equalsIgnoreCase("")) {
    			result.remove(i);
    			i = -1;
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
