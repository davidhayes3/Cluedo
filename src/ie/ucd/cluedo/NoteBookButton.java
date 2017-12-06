package ie.ucd.cluedo;

import javax.swing.JButton;
import javax.swing.JDialog;

public class NotebookButton extends JButton{
	
	JDialog dialog;
	public NotebookButton(JDialog dialog){
		this.setText("NoteBook");
		this.dialog = dialog;
		this.setBounds(550,150, 100, 50);
	}
	
	public void setDialog(JDialog dialog){
		this.dialog = dialog;
	}
}
