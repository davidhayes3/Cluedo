package ie.ucd.cluedo;


import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NotebookDialog extends JDialog implements ActionListener{
	
	String playerNotes; 
	JLabel label;
	
	public NotebookDialog(Frame board, String title) 
	{
		super(board, title);
		this.setSize(300,300);
		
		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout());
		label = new JLabel("label");
		label.setText("Your Notebook");
		pan.add(label);
		this.add(label);
		pan.setVisible(true);
		//this.add(Close); 
		this.setVisible(false);
	}

	public void changeNoteBook(String playerNotes)
	{
		this.playerNotes = playerNotes;
		label.setText("Your Notebook");
		label.setVisible(true);
		this.label.setText(playerNotes);
	}
	

	public void openDialog()
	{
		label.setText("Your Notebook");
		label.setVisible(true);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{ 
            this.setVisible(false);  
	}

}
