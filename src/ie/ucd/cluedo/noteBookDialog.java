package ie.ucd.cluedo;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class noteBookDialog extends JDialog implements ActionListener{
	
	String playerNotes; 

    
	public noteBookDialog(Frame board, String title) {
		super(board, title);
		this.setSize(300,300);
		this.setVisible(false);
	    JButton Close = new JButton();
		Close.setBounds(150,250, 20, 20);
		Close.addActionListener(this);
		Close.add( new JLabel ("Click button to continue"));  
		this.add(Close); 

	}

	public void changeNoteBook(String playerNotes){
		this.playerNotes = playerNotes;
	}
	
	public void openDialog(){
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
            this.setVisible(false);  
	}

}
