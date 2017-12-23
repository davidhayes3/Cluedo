/***************************************************************/ 
/* Notebook Class
/* 
/***************************************************************/

package ie.ucd.cluedo;

import javax.swing.*;


@SuppressWarnings("serial")
public class Notebook extends JFrame
{
	
	// Attributes 
	JLabel label = new JLabel();
	String activityLog;
	
	// Notebook constructor
	public Notebook()
	{
		label = new JLabel();
		this.activityLog = "";
		this.setBounds(0, 0, 250, 250);
		this.setLocationRelativeTo(null);
		label.setVerticalAlignment(SwingConstants.TOP);
		this.setSize(500, 500);
		this.setTitle("Your Notebook");
		label.setText(this.activityLog);
		this.add(label);
	}
	
	
	/* Public Methods */
	
	
	// makeEntry() method
	public void makeEntry(String event)
	{	
		this.activityLog += event + "\n";
		label.setText(label.getText() + "<html>" + event +"<br/><html>");
		label.setText(label.getText() +"\n");
	}
	
	// getContents() method
	public String getContents()
	{
		return this.activityLog;
	}
	
	
	// showNotebook() method
	public void showNotebook()
	{
		this.setVisible(true);
	}
	
}