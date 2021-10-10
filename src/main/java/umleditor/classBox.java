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
    
    private JPanel panel;
    private JPanel fieldPanel;
    private JPanel methodPanel;

    private GroupLayout gl_panel;

    
    private HashMap<String,JLabel> methods;
    private HashMap<String, ArrayList<String>> params;
    private HashMap<String,JLabel> fields;

    public classBox(String name){
        className = new JLabel(name);
        fields = new HashMap();
        params = new HashMap();
        methods = new HashMap();
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
		fields.put(fieldName,field);
		fieldPanel.add(field);
		fieldPanel.repaint();
     }
     public void addMethod(String methodName){
        JLabel method = new JLabel(methodName);
		methods.put(methodName,method);
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
     public void removeField(String removes){
        fieldPanel.remove(fields.get(removes));
        fields.remove(removes);
        fieldPanel.repaint();
    }
     public void removeMethod(String removes){
        methodPanel.remove(methods.get(removes));
        methods.remove(removes);
        methodPanel.repaint();
     }
     public void removeParameter(String removes, String method){
         ArrayList<String> temp = params.get(method);
         
         for(String t : temp){
             if (t == removes){
                 temp.remove(t);
             }
         }
         methodPanel.repaint();
     }
     /**
      * Rename Element Functions
      */
      public void renameClass(String newName){
          className.setText(newName);
          panel.repaint();
      }
     public void renameField(String oldName, String newName){
        JLabel temp = fields.get(oldName);
        temp.setText(newName);
        fieldPanel.repaint();
    }
     public void renameMethod(String oldName, String newName){
        JLabel temp = methods.get(oldName);
        temp.setText(newName);
        methodPanel.repaint();
     }
     public void renameParameter(String oldParameter, String method, String newParameter){
        ArrayList<String> temp = params.get(method);

        for(String p : temp){
            if(p == oldParameter)
            {
                oldParameter = newParameter;
            }
        }
        methodPanel.repaint();
     }

}
