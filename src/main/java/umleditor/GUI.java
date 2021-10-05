package umleditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class GUI { 
    
    private JFrame frame; 
    private JFrame action;

    JMenuBar menuBar;

    JMenu add,delete,rename, file;
    JMenuItem help,save,load;
    JMenuItem addClass,addRelationship,addField,addMethod,addParameter;
    JMenuItem deleteClass,deleteRelationship,deleteField,deleteMethod,deleteParameter;
    JMenuItem renameClass,renameField,renameMethod,renameParameter;

    JPanel pane,boxPane,actionPane;

    JTextField textBoxClassAdd;
    JTextField className,className2,methodName,fieldName,parameterName,relationType,renamer;

    private DiagramModel model = new DiagramModel();
    private HashMap<String,classBox> boxMap = new HashMap();
    private classBox box;

    public GUI(){

    }
    
    public static void main(String[] args){
        GUI test = new GUI(); 

        test.drawGUI();

    }

    public void drawGUI(){
        frame = new JFrame("UML Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setLocationRelativeTo(null);
        createInterface();
        frame.setJMenuBar(menuBar);
        pane = new JPanel();
        pane.setLayout(new GridBagLayout());

        frame.add(pane);
        frame.setVisible(true);
    }
    /**
     * Intializes Action Listeners and Start Menu
     */
    public void createInterface(){
        menuBar = new JMenuBar();
        help = new JMenuItem("Help");
        help.setMaximumSize(new Dimension(90,30));
        add = new JMenu("Add");
        delete = new JMenu("Delete");
        rename = new JMenu("Rename");
        file = new JMenu("File");

        addClass = new JMenuItem("Class");
        addRelationship = new JMenuItem("Relationship");
        addField = new JMenuItem("Field");
        addMethod = new JMenuItem("Method");
        addParameter = new JMenuItem("Parameter");

        deleteClass = new JMenuItem("Class");
        deleteRelationship = new JMenuItem("Relationship");
        deleteField = new JMenuItem("Field");
        deleteMethod = new JMenuItem("Method");
        deleteParameter = new JMenuItem("Parameter");

        renameClass = new JMenuItem("Class");
        renameField = new JMenuItem("Field");
        renameMethod = new JMenuItem("Method");
        renameParameter = new JMenuItem("Parameter");

        save = new JMenuItem("Save");
        load = new JMenuItem("Load");

        add.add(addClass);
        add.add(addRelationship);
        add.add(addField);
        add.add(addMethod);
        add.add(addParameter);

        delete.add(deleteClass);
        delete.add(deleteRelationship);
        delete.add(deleteField);
        delete.add(deleteMethod);
        delete.add(deleteParameter);

        rename.add(renameClass);
        rename.add(renameField);
        rename.add(renameMethod);
        rename.add(renameParameter);
        //delete.add();

        file.add(save);
        file.add(load);

        menuBar.add(file);
        menuBar.add(add);
        menuBar.add(delete);
        menuBar.add(rename);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(help);
        
        /**
         * Opens the Add Class Window
         */
        addClass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addClassWindow();
                
            }
         });
         /**
          * Opens the Add Relationship Window
          */
         addRelationship.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRelationship();
            }
         });
         /**
          * Opens the Add Field Window
          */
          addField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addField();
            }
         });
         /**
          * Opens the Add Method Window
          */
          addMethod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMethod();
            }
         });
         /**
          * Opens the Add Method Window
          */
          addParameter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addParameter();
            }
         });
        /**
          * Opens the Delete Class Window
          */
          deleteClass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteClass();
            }
         });
        /**
          * Opens the Delete Relationship Window
          */
          deleteRelationship.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRelationship();
            }
         });
        /**
          * Opens the Delete Field Window
          */
          deleteField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteField();
            }
         });
         /**
          * Opens the Delete Method Window
          */
          deleteMethod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteMethod();
            }
         });
        /**
          * Opens the Delete Parameter Window
          */
          deleteParameter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteParameter();
            }
         });
        /**
          * Opens the Rename Class Window
          */
          renameClass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameClass();
            }
         });
        /**
          * Opens the Rename Field Window
          */
          renameField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameField();
            }
         });
        /**
          * Opens the Rename Method Window
          */
          renameMethod.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameMethod();
            }
         });     
        /**
          * Opens the Rename Parameter Window
          */
          renameParameter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameParameter();
            }
         });           
         /**
         * Opens the Help Window
         */
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpWindow();
            }
         });
    }

    /**
     * ALL METHODS BELOW ARE TEMPORARY AND TO BE INSERTED INTO ACTION LISTENERS ABOVE
     * All methods also need a CONTROLLER implementation which is triggered by the Add button.
     */
    public void addClassWindow(){
        action = new JFrame("UML Editor");
        action.setSize(250, 125);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classNameAdd = new JLabel("Enter Class Name: ");
        JButton classAddButton = new JButton("Add");

        actionPane = new JPanel(new FlowLayout());
        className = new JTextField("", 18);

        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addClassAction();
            }
         });

        actionPane.add(classNameAdd);
        actionPane.add(className);
        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);

    }

    public void helpWindow(){
        action = new JFrame("UML Editor");
        action.setSize(650, 400);
        action.setLocationRelativeTo(frame);
        actionPane = new JPanel(new GridLayout(20,0));

        JLabel title = new JLabel("Help Menu", SwingConstants.CENTER);
        JLabel desc = new JLabel("This is the program help menu, below are the included actions you can take to operate this Editor.",SwingConstants.CENTER);

        actionPane.add(title);
        actionPane.add(desc);
        action.add(actionPane);
        action.setVisible(true);
    }

    public void addRelationship(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classN = new JLabel("Enter First Class Name: ");
        JLabel classN2 = new JLabel("Enter Second Class Name: ");
        JLabel relationshipType = new JLabel("Enter Relationship Type: (A, C, I, R)");

        JButton classAddButton = new JButton("Add");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        className2 = new JTextField("", 18);
        relationType = new JTextField("", 18);

        actionPane.add(classN);
        actionPane.add(className);
        actionPane.add(classN2);
        actionPane.add(className2);
        actionPane.add(relationshipType);
        actionPane.add(relationType);
        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void addField(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel fieldLabel = new JLabel("Enter Field Name: ");

        JButton classAddButton = new JButton("Add");

        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFieldAction();
            }
         });

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        fieldName = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(fieldLabel);
        actionPane.add(fieldName);

        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void addMethod(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel methodLabel = new JLabel("Enter Method Name: ");

        JButton classAddButton = new JButton("Add");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        methodName = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(methodLabel);
        actionPane.add(methodName);

        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);
    }
   
    public void addParameter(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel methodLabel = new JLabel("Enter Method Name: ");
        JLabel paramLabel = new JLabel("Enter Parameter Name: ");

        JButton classAddButton = new JButton("Add");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        methodName = new JTextField("", 18);
        parameterName = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(methodLabel);
        actionPane.add(methodName);
        actionPane.add(paramLabel);
        actionPane.add(parameterName);

        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void deleteClass(){
        action = new JFrame("UML Editor");
        action.setSize(250, 125);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classNameDelete = new JLabel("Enter Class Name: ");
        JButton classDeleteButton = new JButton("Delete");

        actionPane = new JPanel(new FlowLayout());
        className = new JTextField("", 18);

        actionPane.add(classNameDelete);
        actionPane.add(className);
        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);

    }

    public void deleteRelationship(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classN = new JLabel("Enter First Class Name: ");
        JLabel classN2 = new JLabel("Enter Second Class Name: ");

        JButton classDeleteButton = new JButton("Delete");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        className2 = new JTextField("", 18);

        actionPane.add(classN);
        actionPane.add(className);
        actionPane.add(classN2);
        actionPane.add(className2);
        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void deleteField(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel fieldLabel = new JLabel("Enter Field Name: ");

        JButton classDeleteButton = new JButton("Delete");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        fieldName = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(fieldLabel);
        actionPane.add(fieldName);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void deleteMethod(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel methodLabel = new JLabel("Enter Method Name: ");

        JButton classDeleteButton = new JButton("Delete");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        methodName = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(methodLabel);
        actionPane.add(methodName);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
    }
   
    public void deleteParameter(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel methodLabel = new JLabel("Enter Method Name: ");
        JLabel paramLabel = new JLabel("Enter Parameter Name: ");

        JButton classDeleteButton = new JButton("Delete");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        methodName = new JTextField("", 18);
        parameterName = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(methodLabel);
        actionPane.add(methodName);
        actionPane.add(paramLabel);
        actionPane.add(parameterName);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void renameClass(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classNameLabel = new JLabel("Enter Class Name: ");
        JLabel classRenameLabel = new JLabel("Enter NEW Class Name: ");
        JButton classAddButton = new JButton("Rename");

        actionPane = new JPanel(new FlowLayout());
        className = new JTextField("", 18);
        className2 = new JTextField("", 18);

        actionPane.add(classNameLabel);
        actionPane.add(className);
        actionPane.add(classRenameLabel);
        actionPane.add(className2);
        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);

    }

    public void renameField(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel fieldLabel = new JLabel("Enter Field Name: ");
        JLabel fieldRenameLabel = new JLabel("Enter NEW Field Name: ");

        JButton classDeleteButton = new JButton("Rename");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        fieldName = new JTextField("", 18);
        renamer = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(fieldLabel);
        actionPane.add(fieldName);
        actionPane.add(fieldRenameLabel);
        actionPane.add(renamer);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void renameMethod(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel methodLabel = new JLabel("Enter Method Name: ");
        JLabel methodRenameLabel = new JLabel("Enter NEW Method Name: ");

        JButton classDeleteButton = new JButton("Delete");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        methodName = new JTextField("", 18);
        renamer = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(methodLabel);
        actionPane.add(methodName);
        actionPane.add(methodRenameLabel);
        actionPane.add(renamer);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void renameParameter(){
        action = new JFrame("UML Editor");
        action.setSize(250, 275);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Enter Class Name: ");
        JLabel methodLabel = new JLabel("Enter Method Name: ");
        JLabel paramLabel = new JLabel("Enter Parameter Name: ");
        JLabel paramRenameLabel = new JLabel("Enter NEW Parameter Name: ");

        JButton classDeleteButton = new JButton("Delete");

        actionPane = new JPanel(new FlowLayout());

        className = new JTextField("", 18);
        methodName = new JTextField("", 18);
        parameterName = new JTextField("", 18);
        renamer = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(className);
        actionPane.add(methodLabel);
        actionPane.add(methodName);
        actionPane.add(paramLabel);
        actionPane.add(parameterName);
        actionPane.add(paramRenameLabel);
        actionPane.add(renamer);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    /**
     * Methods responsible for manipulating GUI Box Element. 
     */
    public void addClassAction(){
        String newClass = className.getText();
        model.addClass(newClass);
        box = new classBox(newClass);
        boxMap.put(newClass,box);
        pane.add(box.getClassPanel());
        frame.add(pane);
        frame.setVisible(true);
        action.dispose();
    }

    public void addFieldAction(){
        String getClass = className.getText();
		String field = fieldName.getText();

        model.addField(getClass, field);
        boxMap.get(getClass).addField(field);
        action.dispose();
    }

    public void addMethodAction(){

    }

    public void addParameterAction(){

    }

    public void deleteClassAction(){

    }
    public void deleteFieldAction(){

    }
    public void deletemethodAction(){

    }
    public void deleteParameterAction(){

    }
    public void renameClassAction(){

    }
    public void renameFieldAction(){

    }
    public void renameMethodAction(){

    }
    public void renameParameterAction(){
        
    }
}
