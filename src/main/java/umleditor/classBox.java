package umleditor;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class classBox {

    private JLabel className;
    
    private JPanel panel = null;
    private JPanel fieldPanel = null;
    private JPanel methodPanel = null;

    private GroupLayout gl_panel;

    
    private ArrayList<String> methods;
    private HashMap<String, ArrayList<String>> params;
    private ArrayList<String> fields;

    public classBox(String name){
        className = new JLabel(name);
        fields = new ArrayList();
        params = new HashMap();
        methods = new ArrayList();
        panel = null;
        fieldPanel = null;
        methodPanel = null;
        initialize();
    }

    public void initialize(){
        panel = new JPanel(new GridLayout(1,1));
		panel.setBorder(new LineBorder(new Color(20, 20, 20), 1));
        panel.add(className);
        methodPanel = new JPanel();
        fieldPanel = new JPanel();
        methodPanel.setLayout(new GridLayout(4,1));
		fieldPanel.setLayout(new GridLayout(4,1));
        panel.add(methodPanel);
        panel.add(fieldPanel);
    }
    
    public String getClassName(){
        return className.getText();
    }
    public JPanel getClassPanel(){
        return panel;
    }

    /**
     * Add Element Functions 
     */

     public void addField(String fieldName){
        JLabel field = new JLabel(fieldName);
		fields.add(fieldName);
		fieldPanel.add(field);
		fieldPanel.repaint();
     }
     public void addMethod(String methodName){
        JLabel method = new JLabel(methodName);
		methods.add(methodName);
		methodPanel.add(method);
		methodPanel.repaint();
     }
     public void addParameter(String parameter,String method){
        if(params.containsKey(method)){
            ArrayList<String> temp = params.get(method);
            temp.add(parameter);
        }
        else{
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(parameter);
            params.put(method,temp);
        }
     }
    /**
     * Remove Element Functions
     */
     public void removeField(){

    }
     public void removeMethod(){

     }
     public void removeParameter(){
         
     }
     /**
      * Rename Element Functions
      */
     public void renameField(){
         
    }
     public void renameMethod(){

     }
     public void renameParameter(){

     }

}
