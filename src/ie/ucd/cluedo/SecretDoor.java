package ie.ucd.cluedo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SecretDoor extends JButton{ //implements ActionListener{
	
	int x;
	int y;
	int col;
	int row;
	private ImageIcon image;
	public SecretDoor(int col, int row) {
		this.x = 21*col;
		this.y = 21*row;
		this.col = col;
		this.row = row;
		this.setBounds(x,y, 21, 21);
		//this.addActionListener(this);
		//this.setBackground(Color.GRAY);
		
		try {
			image = new ImageIcon(ImageIO.read(new File("C:\\Users\\Admin\\Documents\\5th Year\\Software Engineering\\RoomStairs.jpg")));
		} catch (IOException e) {
			System.out.println("Error");
		}
		
		this.setIcon(image);
	}
	
	/*@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("This button was pressed: " + col + "," + row);
		
	}*/

}
