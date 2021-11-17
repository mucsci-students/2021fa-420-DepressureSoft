package umleditor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class startupGUI {
    
    /**
     * Starts the program which gives the user two options between Console and GUI.
     */
    public static void main(String args[]){
    	if(args.length == 0) {
    		GUI.main(args);
    	}
    	else {
    		if(args[0].equals("--cli")) {
    			Controller.main(args);
    		}
    		else if(args[0].equals("--gui")) {
    			GUI.main(args);
    		}
    		else {
    			System.out.println("Invalid flag. Use either --cli to start the command line interface or --gui to start the GUI interface.");
    		}
    	}
    }
}
  
