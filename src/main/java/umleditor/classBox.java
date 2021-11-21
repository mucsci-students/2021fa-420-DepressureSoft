package umleditor;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

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
        className = new JLabel(name,SwingConstants.CENTER);
        fields = new HashMap();
        params = new HashMap();
        methods = new HashMap();
        panel = null;
        fieldPanel = null;
        methodPanel = null;
        initialize();
    }

    public classBox(classBox other){
        JLabel holder = new JLabel(other.getClassName());
        this.className = holder;

        methods = new HashMap<String, JLabel>();

        Iterator methodIter = other.methods.entrySet().iterator();
        while (methodIter.hasNext()){
            Map.Entry elem = (Map.Entry) methodIter.next();
            String keyCopy = (String) elem.getKey();
            JLabel labelCopy = new JLabel(((JLabel) elem.getValue()).getText());
            methods.put(keyCopy, labelCopy);
            methodPanel.add(labelCopy);
        }
        params = new HashMap<String, ArrayList<String>>();

        Iterator paramIter = other.params.entrySet().iterator();
        while (paramIter.hasNext()){
            Map.Entry elem = (Map.Entry) paramIter.next();
            String keyCopy = (String) elem.getKey();
            ArrayList<String> elemArray = new ArrayList<String>();
            elemArray = ((ArrayList<String>) elem.getValue());
            params.put(keyCopy, elemArray);
        }
    
        fields = new HashMap<String, JLabel>();

        Iterator fieldIter = other.fields.entrySet().iterator();
        while (fieldIter.hasNext()){
            Map.Entry elem = (Map.Entry) fieldIter.next();
            String keyCopy = (String) elem.getKey();
            JLabel labelCopy = new JLabel(((JLabel) elem.getValue()).getText());
            fields.put(keyCopy, labelCopy);
        }
        
        this.panel = other.panel;
        panel.setLocation(other.getClassPanel().getX(), other.getClassPanel().getY());
        this.fieldPanel = other.fieldPanel;
        this.methodPanel = other.methodPanel;
        this.gl_panel = other.gl_panel;

        panel.add(fieldPanel);
        

       // initialize();
    }

    public void initialize(){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    	panel.setBorder(new LineBorder(new Color(20, 20, 20), 1));

        panel.add(className);
        methodPanel = new JPanel();
        fieldPanel = new JPanel();
        methodPanel.setLayout(new GridLayout(4,2));
        methodPanel.setBorder(new LineBorder(new Color(20, 20, 20), 1));
		fieldPanel.setLayout(new GridLayout(4,2));
        fieldPanel.setBorder(new LineBorder(new Color(20, 20, 20), 1));
        panel.add(fieldPanel);
        panel.add(methodPanel);


        MouseInputAdapter movement = new MouseInputAdapter (){
			private int x;
			private int y;
			public void mousePressed(MouseEvent e) {
				this.x = e.getX();
				this.y = e.getY();
			}
			public void mouseDragged(MouseEvent e) {
				panel.setLocation(panel.getX() + (e.getX() - this.x), panel.getY() + (e.getY() - this.y));
                panel.repaint();
                GUI.pane.repaint();
                GUI.redrawArrows();
			}
            public void mouseReleased(MouseEvent e) {
                GUI.redrawArrows();
			}
		};
	
		panel.addMouseListener(movement);
		panel.addMouseMotionListener(movement);
		
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

     public void addField(String fieldName,String fieldT){
        JLabel field = new JLabel(fieldName);
		fields.put(fieldName,field);
		fieldPanel.add(field);
		fieldPanel.repaint();
        fields.get(fieldName).setText(fieldName + " : " + fieldT);
     }

     public void addMethod(String methodName, String methodType){
        JLabel method = new JLabel(methodName);
		methods.put(methodName,method);
		methodPanel.add(method);
		methodPanel.repaint();
        methods.get(methodName).setText(methodType + " : " + methodName);
     }

     public void addParameter(String parameter,String method){

        JLabel temp = methods.get(method);
        String holderv = temp.getText();
        String[] holderv2z = holderv.split(":");

        String param = holderv2z[0]+ ": " + method + "(";

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
        param = param.substring(0,param.length()-2);
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
        JLabel temp = methods.get(method);
        String holderv = temp.getText();
        String[] holderv2v = holderv.split(":");

        String param = holderv2v[0] + ": " + method + "(";

        ArrayList<String> holder = params.get(method);
        holder.remove(removes);
        for(String x : params.get(method)){
            param = param + x + ", ";
        }
        param = param.substring(0,param.length()-2);
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
        String holderv2 = temp.getText();
        String[] holder = holderv2.split(":");

        String newField = newName + ":" + holder[1];
        temp.setText(newField);

        fields.remove(oldName);
        fields.put(newName,temp);
        fieldPanel.repaint();
    }
    
     public void renameMethod(String oldName, String newName){
        JLabel temp = methods.get(oldName);
        methods.put(newName,temp);

        String holderv2 = temp.getText();
        String[] holder = holderv2.split(":");

        String method = holder[0] + ": " + newName;

        if(params.containsKey(oldName)){
            method = method + "(";
            params.put(newName,params.get(oldName));
            for(String x : params.get(oldName)){
                method = method + x + ", ";
            }
            method = method.substring(0,method.length()-2);
            method = method + ")";
            params.remove(oldName);
        }

        temp.setText(method);
        methods.remove(oldName);
        methodPanel.repaint();
     }
    
     public void renameParameter(String oldParameter, String newParameter, String method){

        JLabel temp = methods.get(method);
        String holderv = temp.getText();
        String[] holderv2v = holderv.split(":");

        String param = holderv2v[0] + ": " + method + "(";

        ArrayList<String> holder = params.get(method);
        int holderV2 = holder.indexOf(oldParameter);
        holder.set(holderV2,newParameter);
        for(String x : params.get(method)){
            param = param + x + ", ";
        }
        param = param.substring(0,param.length()-2);
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
