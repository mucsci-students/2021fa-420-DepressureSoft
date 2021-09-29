import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class startupGUI {
    
    /**
     * Starts the program launcher GUI which gives the user two options between Console and GUI.
     */
    public static void main(String args[]){
        JFrame frame = new JFrame("UML Program Launcher");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,110);
        frame.setLocationRelativeTo(null);
      
        //Set up the necessary content pane.
        Container pane = frame.getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        //Creates text above buttons and edits the appearance. 
        JLabel question = new JLabel("Which method would you like to use?");
        question.setAlignmentX(pane.CENTER_ALIGNMENT);
        question.setPreferredSize(new Dimension(20,20));
        pane.add(question);

        //Creates buttons and edits their appearance. 
        JButton button = new JButton("GUI");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button);
        button.setPreferredSize(new Dimension(30,30));
        button.add(Box.createHorizontalGlue());

        JButton button2 = new JButton("Console");
        button2.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(button2);
        button2.setPreferredSize(new Dimension(30,30));
        button2.add(Box.createHorizontalGlue());
        
        //Adds Action Listener to Buttons so when clicked they preform necessary tasks. 
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
               //NOTHING YET
            }
         });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                UMLInterface.main(args);
            }
         });

        //Displays the window.
        frame.setVisible(true);
     }
}
  