package ie.ucd.cluedo;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoteBookDialog extends JDialog implements ActionListener{
	
	String playerNotes; 
	JLabel label;
	
	public NoteBookDialog(Frame board, String title) {
		super(board, title);
		this.setSize(300,300);
		this.setVisible(false);
		
		JPanel pan = new JPanel();
		pan.setLayout(new FlowLayout());
		label = new JLabel("label");
		label.setText("Your Notebook");
		pan.add(label);
		
		this.add(pan);

		//this.add(Close); 
		
	}

	public void changeNoteBook(String playerNotes){
		this.playerNotes = playerNotes;
		this.label.setText(playerNotes);
	}
	
	public void openDialog(){
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
            this.setVisible(false);  
	}

}
