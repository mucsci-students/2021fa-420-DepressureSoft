package umleditor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class startupGUI {
    
    /**
     * Starts the program launcher GUI which gives the user two options between Console and GUI.
     */
    public static void main(String args[]){
    	if (args.length > 0) {
    		if (args[0].equals("--cli")) {
        		Controller.main(args);
        		System.exit(0);
    		}
    	} else {
    	
	        JFrame frame = new JFrame("UML Program Launcher");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setLocationRelativeTo(null);
	      
	        //Set up the necessary content pane.
	        JPanel pane = new JPanel();
	        frame.setContentPane(pane);
	
	        //Creates text above buttons and edits the appearance. 
	        JLabel question = new JLabel("Which method would you like to use?");
	        question.setAlignmentX(Component.CENTER_ALIGNMENT);
	        pane.add(question);
	
	        //Creates buttons and edits their appearance. 
	        JButton button = new JButton("GUI");
	        button.setAlignmentX(Component.CENTER_ALIGNMENT);
	        pane.add(button);
	
	        JButton button2 = new JButton("Console");
	        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
	        pane.add(button2);
	        
	        //Adds Action Listener to Buttons so when clicked they preform necessary tasks. 
	        button.addActionListener(e -> {
	        	frame.dispose();
			GUI.main(args);
	        });
	        button2.addActionListener(e -> {
	            frame.dispose();
	            UMLInterface.main(args);
	        });
	        frame.pack();
	        frame.setVisible(true);
    	}
    }
}
  
