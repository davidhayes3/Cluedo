package ie.ucd.cluedo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public class noteBookButton extends JButton implements ActionListener {
	
	JDialog dialog;
	public noteBookButton(JDialog dialog){
		this.addActionListener(this);
		this.setText("NoteBook");
		this.dialog = dialog;
		this.setBounds(550,150, 100, 50);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.setVisible(true);
	}
	
	

}
