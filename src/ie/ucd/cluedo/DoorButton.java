package ie.ucd.cluedo;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DoorButton extends JButton implements ActionListener{
	
	int x;
	int y;
	int col;
	int row;
	private ImageIcon image;
	
	public DoorButton(int col, int row) {
		this.x = 21*col;
		this.y = 21*row;
		this.col = col;
		this.row = row;
		this.setBounds(x,y, 21, 21);

		
		try {
			image = new ImageIcon(ImageIO.read(new File("C:\\Users\\Admin\\Documents\\5th Year\\Software Engineering\\RoomDoor.jpg")));
		} catch (IOException e) {
			System.out.println("Error");
		}
		
		this.setIcon(image);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}