package umleditor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import umleditor.Relationship.RelationshipType;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;

public class GUI { 
    
    private JFrame frame; 
    private JFrame action;

    JMenuBar menuBar;

    JMenu add,delete,rename,file;
    JMenuItem help,save,load, undo,redo;
    JMenuItem addClass,addRelationship,addField,addMethod,addParameter;
    JMenuItem deleteClass,deleteRelationship,deleteField,deleteMethod,deleteParameter;
    JMenuItem renameClass,renameField,renameMethod,renameParameter;

    JPanel pane,boxPane,actionPane;

    JTextField textBoxClassAdd;
    JTextField className,className2,methodName,methodType,fieldName,fieldType,parameterName,parameterType,renamer;

    JComboBox classNames,classNamesX,methodNames,fieldNames,paramNames,relationshipTypes;

    private DiagramModel model = new DiagramModel();
    private HashMap<String,classBox> boxMap = new HashMap();
    private classBox box;

    public GUI(){

    }
    
    public static void main(String[] args){
        GUI test = new GUI(); 
        try {
            UIManager.setLookAndFeel(
            UIManager.getCrossPlatformLookAndFeelClassName());
         } catch (Exception e) { 
      }
         JFrame.setDefaultLookAndFeelDecorated(true);
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
        pane.setLayout(new DragLayout());
        frame.add(pane);
        frame.setVisible(true);
    }
    /**
     * Initializes Action Listeners and Start Menu
     */
    public void createInterface(){
        menuBar = new JMenuBar();
        help = new JMenuItem("Help");
        help.setMaximumSize(new Dimension(90,30));
        add = new JMenu("Add");
        delete = new JMenu("Delete");
        rename = new JMenu("Rename");
        file = new JMenu("File");
        undo = new JMenuItem("<=");
        undo.setMaximumSize(new Dimension(30,30));
        redo = new JMenuItem("=>");
        redo.setMaximumSize(new Dimension(30,30));

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
        menuBar.add(undo);
        menuBar.add(redo);
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
        save.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		saveWindow();
        	}
        });
      updateButtons();
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

        actionPane = new JPanel(new GridLayout(4,1));
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
    
    public void saveWindow() {
    	JFileChooser chooser = new JFileChooser();
    	FileNameExtensionFilter jsonOnly = new FileNameExtensionFilter(".json files", "json");
    	chooser.addChoosableFileFilter(jsonOnly);
    	chooser.setAcceptAllFileFilterUsed(false);
    	chooser.setDialogTitle("Save diagram as JSON");
    	int s = chooser.showSaveDialog(null);
    	if(s == JFileChooser.APPROVE_OPTION) {
    		model.save(chooser.getSelectedFile().getAbsolutePath());
    	}
    }

    public void helpWindow(){
        action = new JFrame("UML Editor");
        action.setSize(650, 400);
        action.setLocationRelativeTo(frame);
        actionPane = new JPanel(new GridLayout(20,0));

        JLabel title = new JLabel("Help Menu", SwingConstants.CENTER);
        JLabel desc = new JLabel("This is the program help menu, below are the included actions you can take to operate this Editor.",SwingConstants.CENTER);
        JLabel desc2 = new JLabel("ADD: Allows you to add either a class, field, parameter, method, or relationship to the diagram.",SwingConstants.LEFT);
        JLabel desc3 = new JLabel("DELETE: Allows you to remove either a class, field, parameter, method, or relationship from the diagram.",SwingConstants.LEFT);
        JLabel desc4 = new JLabel("RENAME: Allows you to rename either a class, field, parameter, or method in the diagram.",SwingConstants.LEFT);
        JLabel desc5 = new JLabel("SAVE: Saves the current Diagram held within the program.",SwingConstants.LEFT);
        JLabel desc6 = new JLabel("LOAD: Loads a diagram from file into the GUI.",SwingConstants.LEFT);

        actionPane.add(title);
        actionPane.add(desc);
        actionPane.add(desc2);
        actionPane.add(desc3);
        actionPane.add(desc4);
        actionPane.add(desc5);
        actionPane.add(desc6);
        action.add(actionPane);
        action.setVisible(true);
    }

    public void addRelationship(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classN = new JLabel("Select First Class: ");
        JLabel classN2 = new JLabel("Select Second Class: ");
        JLabel relationshipType = new JLabel("Select Relationship Type: ");

        relationshipTypes = new JComboBox();
        relationshipTypes.addItem("Aggregation");
        relationshipTypes.addItem("Composition");
        relationshipTypes.addItem("Inheritance");
        relationshipTypes.addItem("Realization");

        JButton classAddButton = new JButton("Add");
        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addRelationshipAction();
            }
         });

        actionPane = new JPanel(new GridLayout(8,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        classNamesX = new JComboBox(model.getClassNames().toArray());
        classNamesX.setMaximumSize(classNamesX.getPreferredSize());

        actionPane.add(classN);
        actionPane.add(classNames);
        actionPane.add(classN2);
        actionPane.add(classNamesX);
        actionPane.add(relationshipType);
        actionPane.add(relationshipTypes);
        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void addField(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel fieldLabel = new JLabel("Enter Field Name: ");
        JLabel fieldTLabel = new JLabel("Enter Field Type: ");
        JButton classAddButton = new JButton("Add");
        

        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFieldAction();
            }
         });

         actionPane = new JPanel(new GridLayout(8,1));
        fieldName = new JTextField("", 18);
        fieldType = new JTextField("", 18);

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setSize(200, classNames.getPreferredSize().height);
        
        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(fieldLabel);
        actionPane.add(fieldName);
        actionPane.add(fieldTLabel);
        actionPane.add(fieldType);

        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void addMethod(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel methodLabel = new JLabel("Enter Method Name: ");
        JLabel methodTLabel = new JLabel("Enter Method Type: ");

        JButton classAddButton = new JButton("Add");

        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMethodAction();
            }
         });

         actionPane = new JPanel(new GridLayout(8,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        methodName = new JTextField("", 18);
        methodType = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(methodLabel);
        actionPane.add(methodName);
        actionPane.add(methodTLabel);
        actionPane.add(methodType);

        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);
    }
   
    public void addParameter(){
        action = new JFrame("UML Editor");
        action.setSize(250, 275);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel methodLabel = new JLabel("Select Method: ");
        JLabel paramLabel = new JLabel("Enter Parameter Name: ");
        JLabel paramTLabel = new JLabel("Enter Parameter Type: ");

        actionPane = new JPanel(new GridLayout(10,1));

        JButton classAddButton = new JButton("Add");
        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addParameterAction();
            }
         });

         classNames = new JComboBox(model.getClassNames().toArray());
         classNames.setMaximumSize(classNames.getPreferredSize());

         methodNames = new JComboBox();
         methodNames.setMaximumSize(classNames.getPreferredSize());
         
         classNames.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String holder = classNames.getSelectedItem().toString();
                methodNames.removeAllItems();
                for(String s: model.getUML(holder).getStringMethods()){
                    methodNames.addItem(s.toString());
                }
                
                
            }
        });   

         
        className = new JTextField("", 18);
        methodName = new JTextField("", 18);
        parameterName = new JTextField("", 18);
        parameterType = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(methodLabel);
        actionPane.add(methodNames);
        actionPane.add(paramLabel);
        actionPane.add(parameterName);
        actionPane.add(paramTLabel);
        actionPane.add(parameterType);


        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);

        if(classNames.getItemCount() >= 1){
        String holder = classNames.getSelectedItem().toString();
        methodNames.removeAllItems();
        for(String s: model.getUML(holder).getStringMethods()){
            methodNames.addItem(s.toString());
        }
    }
    }

    public void deleteClass(){
        action = new JFrame("UML Editor");
        action.setSize(250, 125);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classNameDelete = new JLabel("Select Class: ");
        JButton classDeleteButton = new JButton("Delete");

        classDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteClassAction();
            }
         });

        actionPane = new JPanel(new GridLayout(4,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        actionPane.add(classNameDelete);
        actionPane.add(classNames);
        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);

    }

    public void deleteRelationship(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classN = new JLabel("Select Class: ");
        JLabel classN2 = new JLabel("Select Second Class: ");

        JButton classDeleteButton = new JButton("Delete");
        classDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteRelationshipAction();
            }
         });
        actionPane = new JPanel(new GridLayout(6,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());
        
        classNamesX = new JComboBox(model.getClassNames().toArray());
        classNamesX.setMaximumSize(classNames.getPreferredSize());

        actionPane.add(classN);
        actionPane.add(classNames);
        actionPane.add(classN2);
        actionPane.add(classNamesX);
        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
    }

    public void deleteField(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel fieldLabel = new JLabel("Select Field: ");

        JButton classDeleteButton = new JButton("Delete");
        classDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteFieldAction();
            }
         });

        actionPane = new JPanel(new GridLayout(6,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        fieldNames = new JComboBox();
        fieldNames.setMaximumSize(classNames.getPreferredSize());

        classNames.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String holder = classNames.getSelectedItem().toString();
                fieldNames.removeAllItems();
                System.out.println( model.getUML(holder).getFields().size());
                for(String s: model.getUML(holder).getFields()){
                    fieldNames.addItem(s.toString());
                }
                
                
            }
        });   

        fieldName = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(fieldLabel);
        actionPane.add(fieldNames);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);

        if(classNames.getItemCount() >= 1){
        String holder = classNames.getSelectedItem().toString();
        fieldNames.removeAllItems();
        for(String s: model.getUML(holder).getFields()){
            fieldNames.addItem(s.toString());
        }
        }  
    }

    public void deleteMethod(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel methodLabel = new JLabel("Select Method: ");

        JButton classDeleteButton = new JButton("Delete");
        classDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteMethodAction();
            }
         });

        actionPane = new JPanel(new GridLayout(6,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        methodNames = new JComboBox();
        methodNames.setMaximumSize(classNames.getPreferredSize());
        
        classNames.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               String holder = classNames.getSelectedItem().toString();
               methodNames.removeAllItems();
               for(String s: model.getUML(holder).getStringMethods()){
                   methodNames.addItem(s.toString());
               }
               
               
           }
       });   



        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(methodLabel);
        actionPane.add(methodNames);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);
        if(classNames.getItemCount() >= 1){
        String holder = classNames.getSelectedItem().toString();
        methodNames.removeAllItems();
        for(String s: model.getUML(holder).getStringMethods()){
            methodNames.addItem(s.toString());
        }
        }
    }
   
    public void deleteParameter(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel methodLabel = new JLabel("Select Method: ");
        JLabel paramLabel = new JLabel("Select Parameter: ");

        JButton classDeleteButton = new JButton("Delete");
        classDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteParameterAction();
            }
         });

        actionPane = new JPanel(new GridLayout(8,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        methodNames = new JComboBox();
        methodNames.setMaximumSize(classNames.getPreferredSize());

        paramNames = new JComboBox();
        paramNames.setMaximumSize(classNames.getPreferredSize());
        
        classNames.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               String holder = classNames.getSelectedItem().toString();
               methodNames.removeAllItems();
               for(String s: model.getUML(holder).getStringMethods()){
                   methodNames.addItem(s.toString());
               }
               
               
           }
       });
          
       methodNames.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            String holder = classNames.getSelectedItem().toString();
            String holderV2 = methodNames.getSelectedItem().toString();
            paramNames.removeAllItems();
            for(String s: model.getUML(holder).getMethod(holderV2).getParamList()){
                paramNames.addItem(s.toString());
            }
        }
    });


        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(methodLabel);
        actionPane.add(methodNames);
        actionPane.add(paramLabel);
        actionPane.add(paramNames);

        actionPane.add(classDeleteButton);
        action.add(actionPane);

        action.setVisible(true);

        if(classNames.getItemCount() >= 1){
        String holder = classNames.getSelectedItem().toString();
        methodNames.removeAllItems();
        for(String s: model.getUML(holder).getStringMethods()){
            methodNames.addItem(s.toString());
        }
        }
    }

    public void renameClass(){
        action = new JFrame("UML Editor");
        action.setSize(250, 175);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classNameLabel = new JLabel("Select Class: ");
        JLabel classRenameLabel = new JLabel("Enter NEW Class Name: ");
        JButton classAddButton = new JButton("Rename");

        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameClassAction();
            }
         });

        actionPane = new JPanel(new GridLayout(6,1));
        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        className2 = new JTextField("", 18);

        actionPane.add(classNameLabel);
        actionPane.add(classNames);
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

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel fieldLabel = new JLabel("Select Field: ");
        JLabel fieldRenameLabel = new JLabel("Enter NEW Field Name: ");

        JButton classAddButton = new JButton("Rename");
        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameFieldAction();
            }
         });


        actionPane = new JPanel(new GridLayout(8,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        fieldNames = new JComboBox();
        fieldNames.setMaximumSize(classNames.getPreferredSize());

        classNames.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String holder = classNames.getSelectedItem().toString();
                fieldNames.removeAllItems();
                for(String s: model.getUML(holder).getFields()){
                    fieldNames.addItem(s.toString());
                }
                
                
            }
        });   

        renamer = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(fieldLabel);
        actionPane.add(fieldNames);
        actionPane.add(fieldRenameLabel);
        actionPane.add(renamer);

        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);

        //Grabs box elements when user first opens selection window. 
        if(classNames.getItemCount() >= 1){
            String holder = classNames.getSelectedItem().toString();
            fieldNames.removeAllItems();
            for(String s: model.getUML(holder).getFields()){
                fieldNames.addItem(s.toString());
            }
            }  
    }

    public void renameMethod(){
        action = new JFrame("UML Editor");
        action.setSize(250, 225);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel methodLabel = new JLabel("Select Method: ");
        JLabel methodRenameLabel = new JLabel("Enter NEW Method Name: ");

        JButton classAddButton = new JButton("Rename");
        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameMethodAction();
            }
         });

        actionPane = new JPanel(new GridLayout(8,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        methodNames = new JComboBox();
        methodNames.setMaximumSize(classNames.getPreferredSize());
        
        classNames.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               String holder = classNames.getSelectedItem().toString();
               methodNames.removeAllItems();
               for(String s: model.getUML(holder).getStringMethods()){
                   methodNames.addItem(s.toString());
               }
               
               
           }
       });   

        renamer = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(methodLabel);
        actionPane.add(methodNames);
        actionPane.add(methodRenameLabel);
        actionPane.add(renamer);

        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);

        if(classNames.getItemCount() >= 1){
            String holder = classNames.getSelectedItem().toString();
            methodNames.removeAllItems();
            for(String s: model.getUML(holder).getStringMethods()){
                methodNames.addItem(s.toString());
            }
            }
    }

    public void renameParameter(){
        action = new JFrame("UML Editor");
        action.setSize(250, 275);
        action.setResizable(false);
        action.setLocationRelativeTo(frame);

        JLabel classLabel = new JLabel("Select Class: ");
        JLabel methodLabel = new JLabel("Select Method: ");
        JLabel paramLabel = new JLabel("Select Parameter: ");
        JLabel paramRenameLabel = new JLabel("Enter NEW Parameter Name: ");

        JButton classAddButton = new JButton("Rename");
        classAddButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                renameParameterAction();
            }
         });


        actionPane = new JPanel(new GridLayout(10,1));

        classNames = new JComboBox(model.getClassNames().toArray());
        classNames.setMaximumSize(classNames.getPreferredSize());

        methodNames = new JComboBox();
        methodNames.setMaximumSize(classNames.getPreferredSize());

        paramNames = new JComboBox();
        paramNames.setMaximumSize(classNames.getPreferredSize());
        
        classNames.addItemListener(new ItemListener() {
           @Override
           public void itemStateChanged(ItemEvent e) {
               String holder = classNames.getSelectedItem().toString();
               methodNames.removeAllItems();
               for(String s: model.getUML(holder).getStringMethods()){
                   methodNames.addItem(s.toString());
               }
               
               
           }
       });  

       methodNames.addItemListener(new ItemListener() {
       @Override
       public void itemStateChanged(ItemEvent e) {
            String holder = classNames.getSelectedItem().toString();
            String holderV2 = methodNames.getSelectedItem().toString();
            paramNames.removeAllItems();
            for(String s: model.getUML(holder).getMethod(holderV2).getParamList()){
                paramNames.addItem(s.toString());
            }
        }
    });


        renamer = new JTextField("", 18);

        actionPane.add(classLabel);
        actionPane.add(classNames);
        actionPane.add(methodLabel);
        actionPane.add(methodNames);
        actionPane.add(paramLabel);
        actionPane.add(paramNames);
        actionPane.add(paramRenameLabel);
        actionPane.add(renamer);

        actionPane.add(classAddButton);
        action.add(actionPane);

        action.setVisible(true);

        if(classNames.getItemCount() >= 1){
            String holder = classNames.getSelectedItem().toString();
            methodNames.removeAllItems();
            for(String s: model.getUML(holder).getStringMethods()){
                methodNames.addItem(s.toString());
            }
            }
    }

    /**
     * Methods below are responsible for manipulating GUI Display Boxs and backend data fields. 
     */
    public void addClassAction(){
        String newClass = className.getText();
        if(!duplicateClass(newClass)){
        model.addClass(newClass);
        box = new classBox(newClass);
        boxMap.put(newClass,box);
        pane.add(box.getClassPanel());
        frame.add(pane);
        frame.setVisible(true);
        action.dispose();
        updateButtons();
        }
        else{
            JLabel error = new JLabel("Class already exists.");
            actionPane.add(error);
            actionPane.validate();
        }
    }

    public void addFieldAction(){
        String getClass = classNames.getSelectedItem().toString();
		String field = fieldName.getText();
        String fieldT = fieldType.getText();

        if(!boxMap.get(getClass).duplicateField(field)){
        model.addField(getClass, field);
        box = boxMap.get(getClass);
        box.addField(field,fieldT);
        action.dispose();
        updateButtons();
        frame.validate();
        }
        else{
            JLabel error = new JLabel("Field already exists.");
            actionPane.add(error);
            actionPane.validate();
        }
    }

    public void addMethodAction(){
        String getClass = classNames.getSelectedItem().toString();
		String method = methodName.getText();
        String methodT = methodType.getText();

        if(!boxMap.get(getClass).duplicateMethod(method)){
        model.addMethod(getClass,method);
        box = boxMap.get(getClass);
        box.addMethod(method,methodT);
        action.dispose();
        frame.validate();
        updateButtons();
         }
        else{
        JLabel error = new JLabel("Method already exists.");
        actionPane.add(error);
        actionPane.validate();
        }
    }

    public void addParameterAction(){
        String getClass = classNames.getSelectedItem().toString();
		String method = methodNames.getSelectedItem().toString();
        String parameter = parameterName.getText();
        //String parameterT = parameterType.getText();

        if(!boxMap.get(getClass).duplicateParameter(method,parameter)){
        model.addParameter(getClass,method,parameter);
        box = boxMap.get(getClass);
        box.addParameter(parameter,method);
        action.dispose();
        frame.repaint();
        updateButtons();
        }
        else{
        JLabel error = new JLabel("Parameter already exists.");
        actionPane.add(error);
        actionPane.validate();
        }
    }

    public void addRelationshipAction(){
        String classOne = classNames.getSelectedItem().toString();
        String classTwo = classNamesX.getSelectedItem().toString();
        String relationT = relationshipTypes.getSelectedItem().toString();

        if(relationT.equals("Aggregation")){
            model.addRelationship(classOne,classTwo,RelationshipType.AGGREGATION);
        }
        else if(relationT.equals("Compositon")){
        model.addRelationship(classOne,classTwo,RelationshipType.COMPOSITION);
        }
        else if(relationT.equals("Inheritance")){
        model.addRelationship(classOne,classTwo,RelationshipType.INHERITANCE);
        }
        else if(relationT.equals("Realization")){
        model.addRelationship(classOne,classTwo,RelationshipType.REALIZATION);
        }

        action.dispose();
        updateButtons();
    }
    /**
     * Delete Actions
     */
    public void deleteClassAction(){
        String remClass = classNames.getSelectedItem().toString();

        model.deleteClass(remClass);
        pane.remove(boxMap.get(remClass).getClassPanel());
        boxMap.remove(remClass);
        action.dispose();
        pane.repaint();
        updateButtons();
    }
    public void deleteRelationshipAction(){
        String classOne = classNames.getSelectedItem().toString();
        String classTwo = classNamesX.getSelectedItem().toString();

        model.deleteRelationship(classOne,classTwo);
        action.dispose();
        updateButtons();
    }
    public void deleteFieldAction(){
        String getClass = classNames.getSelectedItem().toString();
		String field = fieldNames.getSelectedItem().toString();

        model.deleteField(getClass, field); 
        box = boxMap.get(getClass);
        box.removeField(field);
        action.dispose();
        updateButtons();
    }
    public void deleteMethodAction(){
        String getClass = classNames.getSelectedItem().toString();
		String method = methodNames.getSelectedItem().toString();

        box = boxMap.get(getClass);
        box.removeMethod(method);
        model.deleteMethod(getClass,method);
        action.dispose();
        updateButtons();
    }
    public void deleteParameterAction(){ 
        String getClass = classNames.getSelectedItem().toString();
        String method = methodNames.getSelectedItem().toString();
        String param = paramNames.getSelectedItem().toString();

       model.deleteParameter(getClass, method, param );
       box = boxMap.get(getClass);
       box.removeParameter(param,method);
       action.dispose();
       updateButtons();
    }
   /**
    * Rename Functions
    */
    public void renameClassAction(){
        String oldClass = classNames.getSelectedItem().toString();
        String newClass = className2.getText();

        if(!duplicateClass(newClass)){
        model.renameUMLClass(oldClass,newClass);
        box = boxMap.get(oldClass);
        box.renameClass(newClass);
        boxMap.remove(newClass);
        boxMap.put(newClass,box);
        action.dispose();
        updateButtons();
        }
        else{

        }
    }
    public void renameFieldAction(){
        String getClass = classNames.getSelectedItem().toString();
		String field = fieldNames.getSelectedItem().toString();
        String newField = renamer.getText();

        if(!boxMap.get(getClass).duplicateField(newField)){
        box = boxMap.get(getClass);
        box.renameField(field,newField);
        model.renameField(getClass,field,newField);
        action.dispose();
        updateButtons();
        }
        else{
        JLabel error = new JLabel("Field already exists.");
        actionPane.add(error);
        actionPane.validate();
         }
    }
    public void renameMethodAction(){
        String getClass = classNames.getSelectedItem().toString();
		String method = methodNames.getSelectedItem().toString();
        String newMethod = renamer.getText();

        if(!boxMap.get(getClass).duplicateMethod(newMethod)){
        box = boxMap.get(getClass);
        box.renameMethod(method,newMethod);
        model.renameMethod(getClass,method,newMethod);
        action.dispose();
        updateButtons();
        }
        else{
            JLabel error = new JLabel("Method already exists.");
            actionPane.add(error);
            actionPane.validate();
        }
    }
    public void renameParameterAction(){
        String getClass = classNames.getSelectedItem().toString();
		String method = methodNames.getSelectedItem().toString();
        String oldParam = paramNames.getSelectedItem().toString();
        String newParam = renamer.getText();

        if(!boxMap.get(getClass).duplicateParameter(method,newParam)){
        model.renameParameter(getClass, method,oldParam,newParam);
        box = boxMap.get(getClass);
        box.renameParameter(oldParam,newParam,method);
        action.dispose();
        updateButtons();
        }
        else{
        JLabel error = new JLabel("Parameter already exists.");
        actionPane.add(error);
        actionPane.validate();
        }
    }
    public void save(){
       // model.save();
    }
    public void load(){
       // model.load();
    }
    public void undoAction() {
    	// undo implementation here
    	updateButtons();
    }
    public void redoAction() {
    	// redo implementation here
    	updateButtons();
    }
    
    /**
     * Updates the availability of buttons based on the conditions of the model.
     * If the conditions of the model prevent a button from functioning properly when clicked, that 
     * button is grayed out and unclickable. 
     */
    public void updateButtons() {
    	//All adds
    	//addRelationship
    	if (model.numberOfClasses() == 0) {
    		addRelationship.setEnabled(false);
    	}
    	else {
    		addRelationship.setEnabled(true);
    	}
    	
    	//addField, addMethod
    	if (model.numberOfClasses() == 0) {
    		addField.setEnabled(false);
    		addMethod.setEnabled(false);
    	}
    	else {
    		addField.setEnabled(true);
    		addMethod.setEnabled(true);
    	}
    	
    	//addParameter
    	if (model.methodsPresent() == false) {
    		addParameter.setEnabled(false);
    	}
    	else {
    		addParameter.setEnabled(true);
    	}
    	
    	//All deletes
    	//deleteClass
    	if (model.numberOfClasses() == 0) {
    		deleteClass.setEnabled(false);
    	}
    	else {
    		deleteClass.setEnabled(true);
    	}
    	
    	//deleteRelationship
    	if (model.relationshipsPresent() == false) {
    		deleteRelationship.setEnabled(false);
    	}
    	else {
    		deleteRelationship.setEnabled(true);
    	}
    	
    	//deleteField
    	if (model.fieldsPresent() == false) {
    		deleteField.setEnabled(false);
    	}
    	else {
    		deleteField.setEnabled(true);
    	}
    	
    	//deleteMethod
    	if (model.methodsPresent() == false) {
    		deleteMethod.setEnabled(false);
    	}
    	else {
    		deleteMethod.setEnabled(true);
    	}
    	
    	//deleteParameter
    	if (model.paramsPresent() == false) {
    		deleteParameter.setEnabled(false);
    	}
    	else {
    		deleteParameter.setEnabled(true);
    	}
    	
    	//All renames
    	//renameClass
    	if (model.numberOfClasses() == 0) {
    		renameClass.setEnabled(false);
    	}
    	else {
    		renameClass.setEnabled(true);
    	}
    	
    	//renameField
    	if (model.fieldsPresent() == false) {
    		renameField.setEnabled(false);
    	}
    	else {
    		renameField.setEnabled(true);
    	}
    	
    	//renameMethod
    	if (model.methodsPresent() == false) {
    		renameMethod.setEnabled(false);
    	}
    	else {
    		renameMethod.setEnabled(true);
    	}
    	
    	//renameParameter
    	if (model.paramsPresent() == false) {
    		renameParameter.setEnabled(false);
    	}
    	else {
    		renameParameter.setEnabled(true);
    	}
    	
    	//Undo-Redo
    	//Undo
    	if (!model.canUndo()) {
    		undo.setEnabled(false);
    	}
    	else {
    		undo.setEnabled(true);
    	}
    	
    	//Redo
    	if (!model.canRedo()) {
    		redo.setEnabled(false);
    	}
    	else {
    		redo.setEnabled(true);
    	}
    }

    public boolean duplicateClass(String className){
        if(boxMap.containsKey(className))
        return true;
        else
        return false;
    }
}
