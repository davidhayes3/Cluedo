package ie.ucd.cluedo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class NoteBookButton extends JButton{
	
	JDialog dialog;
	public NoteBookButton(JDialog dialog){
		this.setText("NoteBook");
		this.dialog = dialog;
		this.setBounds(550,150, 100, 50);
	}

	

}
