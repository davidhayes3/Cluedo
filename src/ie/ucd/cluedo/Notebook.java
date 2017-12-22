package ie.ucd.cluedo;

import java.awt.*; 
import javax.swing.*;

public class Notebook extends JFrame
{
	JLabel label = new JLabel();

	// Notebook attributes
	private String activityLog;
	
	// Notebook constructor
	public Notebook()
	{
		
		this.activityLog = "";
		this.setBounds(0, 0, 250, 250);
		this.setLocationRelativeTo(null);
		label.setVerticalAlignment(SwingConstants.TOP);
		this.setSize(500, 500);
		this.setTitle("Your Notebook");
		label.setText(this.activityLog);

		
		this.add(label);
	}
	
	public void makeEntry(String event)
	{
		this.activityLog += event + "\n";
		label.setText(label.getText() + "<html>" + event +"<br/><html>");
		label.setText(label.getText() +"\n");

	}
	
	public String getContents()
	{
		return this.activityLog;
	}
	
	public void showNoteBook(){
		this.setVisible(true);
	}
}