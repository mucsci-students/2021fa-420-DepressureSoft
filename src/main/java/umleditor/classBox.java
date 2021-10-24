package umleditor;

import javax.swing.*;
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
        panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(20, 20, 20), 1));
        panel.add(className);
        methodPanel = new JPanel();
        fieldPanel = new JPanel();
        methodPanel.setLayout(new GridLayout(4,2));
		fieldPanel.setLayout(new GridLayout(4,2));
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
        String param = method + "(";
        if(params.containsKey(method)){
            ArrayList<String> holder = params.get(method);
            String holderV2 = parameter;
            holder.add(holderV2);
            params.put(method,holder);
        }
        else{
            ArrayList<String> holder = new ArrayList<String>();
            String holderV2 = parameter;
            holder.add(holderV2);
            params.put(method,holder);
        }
        
        for(String x : params.get(method)){
            param = param + x + ", ";
        }
        param = param + ")";
        methods.get(method).setText(param);
        methodPanel.repaint();
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
        String param = method + "(";
        ArrayList<String> holder = params.get(method);
        holder.remove(removes);
        for(String x : params.get(method)){
            param = param + x + ", ";
        }
        param = param + ")";
        methods.get(method).setText(param);
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
        fields.remove(oldName);
        fields.put(newName,temp);
        fieldPanel.repaint();
    }
    
     public void renameMethod(String oldName, String newName){
        JLabel temp = methods.get(oldName);
        methods.put(newName,temp);
        String method = newName + "(";
        if(params.containsKey(oldName)){
            params.put(newName,params.get(oldName));
            for(String x : params.get(oldName)){
                method = method + x + ", ";
            }
            method = method + ")";
            params.remove(oldName);
        }

        temp.setText(method);
        methods.remove(oldName);
        methodPanel.repaint();
     }
    
     public void renameParameter(String oldParameter, String newParameter, String method){
        String param = method + "(";
        ArrayList<String> holder = params.get(method);
        int holderV2 = holder.indexOf(oldParameter);
        holder.set(holderV2,newParameter);
        for(String x : params.get(method)){
            param = param + x + ", ";
        }
        param = param + ")";
        methods.get(method).setText(param);
        methodPanel.repaint();
     }


     /**
      * Existence Checkers 
      */
    public boolean duplicateField(String fieldName){
        if(fields.containsKey(fieldName))
        return true;
        else
        return false;
    }
    public boolean duplicateMethod(String methodName){
        if(methods.containsKey(methodName))
        return true;
        else
        return false;
    }
    public boolean duplicateParameter(String method, String parameterName){
        ArrayList<String> holder = params.get(method);
        if(holder != null){
        if(holder.contains(parameterName))
        return true;
        else
        return false;
        }
        else
        return false;
    }
}
