package umleditor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Arrow extends JPanel{
    
	
    final float THICKNESS = 2;

    boolean right;
    boolean left;
	
	private JPanel sourcePanel;
	private JPanel destPanel;
	private String type;
	private Point sourcePoint;
	private Point destPoint;
	
	public Arrow(JPanel source, JPanel dest, String relType) {
		sourcePanel = source;
		destPanel = dest;
		type = relType;
		sourcePoint = new Point();
		destPoint = new Point();
		
		lineLength();
	}
	
	//Calculates the length of the line between the 2 class boxes
	public void lineLength() {
        int x = destPanel.getX() - sourcePanel.getX();
        int y = destPanel.getY() - sourcePanel.getY();
        System.out.println(x + " " + y);
       
        if(Math.abs(x) < Math.abs(y)) {
        	right = false;
			//if line is pointing up or down
        	if(y < 0) {
        		left = true;
        		sourcePoint.y = sourcePanel.getY();
        		destPoint.y = destPanel.getHeight() + destPanel.getY();
        	}
        	else {
        		left = false;
        		sourcePoint.y =  sourcePanel.getHeight() + sourcePanel.getY();
        		destPoint.y = destPanel.getY();
        	}
        	//sets the x values of the source and destination
        	sourcePoint.x = (sourcePanel.getWidth() / 2) + sourcePanel.getX();
        	destPoint.x = (destPanel.getWidth() / 2) + destPanel.getX();
        }
        //if line is pointing left or right
        else {
        	right = true;
        	if(x < 0) {
        		//left
        		left = true;
        		sourcePoint.x = sourcePanel.getX();
        		destPoint.x = destPanel.getWidth() + destPanel.getX();
        	}
        	else {
        		//right
        		left = false;
        		sourcePoint.x = sourcePanel.getWidth() + sourcePanel.getX();
        		destPoint.x = destPanel.getX();
        	}

        	sourcePoint.y = (sourcePanel.getHeight() / 2) + sourcePanel.getY();
        	destPoint.y = (destPanel.getHeight() / 2) + destPanel.getY();
        }
        Graphics g = GUI.pane.getGraphics();
        drawLine(g);
	}
	
	// Draws the arrow head
	private void drawType(Graphics2D g) {
	    final int MAX_SIZE = 20;
	    final int MIN_SIZE = 10;
	    final BasicStroke SOLIDLINE = new BasicStroke(THICKNESS);

	    int destX = destPoint.x;
	    int destY = destPoint.y;
	    System.out.println(destX + " " + destY);
	    g.setStroke(SOLIDLINE);
	    
		Polygon shape = new Polygon();
		shape.addPoint(destPoint.x, destPoint.y);
		if(!left) {
			if(!right) {
				shape.addPoint(MIN_SIZE + destX, destY - MAX_SIZE);
                System.out.println("test");

				shape.addPoint(destX - MIN_SIZE, destY - MAX_SIZE);
			}
			else {
				shape.addPoint(destX - MAX_SIZE, destY - MIN_SIZE);


				shape.addPoint(destX - MAX_SIZE, MIN_SIZE + destY);
			}
		}
		else {

			if(!right) {
				shape.addPoint(MIN_SIZE + destX, MAX_SIZE + destY);
				shape.addPoint(destX - MIN_SIZE, MAX_SIZE + destY);
			}
			else {
				shape.addPoint(MAX_SIZE + destX, destY - MIN_SIZE);
				shape.addPoint(MAX_SIZE + destX, MIN_SIZE + destY);
			}
		}
		g.draw(shape);
	}
	
	//Draws the line based on the relationship
	public void drawLine(Graphics g) {

		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
        
		int Xos = sourcePoint.x + sourcePanel.getHeight() / 2;
		int Yos = destPoint.y - sourcePanel.getHeight() / 2;
		

		if(type.equals("A")) {
            g2.setColor(Color.BLACK);
		}
        else if(type.equals("R") ){
            g2.setColor(Color.BLUE);
        }
		else if(type.equals("I")) {
            g2.setColor(Color.RED);
		}
		else if(type.equals("C")){
			g2.setColor(Color.GREEN);
		}
		
		//if the relationship is not with the same class
		if(sourcePanel != destPanel) {
			if(!right) {
				g2.draw(new Line2D.Float(sourcePoint.x, sourcePoint.y, sourcePoint.x, (sourcePoint.y + destPoint.y) / 2));
				g2.draw(new Line2D.Float(sourcePoint.x, (sourcePoint.y + destPoint.y) / 2, destPoint.x, (sourcePoint.y + destPoint.y) / 2));
				g2.draw(new Line2D.Float(destPoint.x, (sourcePoint.y + destPoint.y) / 2, destPoint.x, destPoint.y));
			}
			else {
				g2.draw(new Line2D.Float(sourcePoint.x, sourcePoint.y, (sourcePoint.x + destPoint.x) / 2, sourcePoint.y));
				g2.draw(new Line2D.Float((sourcePoint.x + destPoint.x) / 2, sourcePoint.y, (sourcePoint.x + destPoint.x) / 2, destPoint.y));
				g2.draw(new Line2D.Float((sourcePoint.x + destPoint.x) / 2, destPoint.y, destPoint.x, destPoint.y));
			}
		}
		//if it is with the same class
		else {
			g2.draw(new Line2D.Float(sourcePoint.x, sourcePoint.y, Xos, sourcePoint.y));
			g2.draw(new Line2D.Float(Xos, sourcePoint.y, Xos, Yos));
			g2.draw(new Line2D.Float(Xos, Yos, destPoint.x, Yos));
			g2.draw(new Line2D.Float(destPoint.x, Yos, destPoint.x, destPoint.y));
		}
		
		//draw the type for the relationship
		drawType(g2);
	}
}
