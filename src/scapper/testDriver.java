package scapper;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import scapper.scapper.Scrape;

public class testDriver {
	public static void main(String args[]) throws Exception{
	new Scrape();


	JFrame jp1 = new JFrame();
	jp1.setSize(500, 700);
	ImageIcon iconPic = new ImageIcon("C:\\Users\\Jason\\workspace\\scapper\\imgs\\img1.jpg");
	Image protoPic = iconPic.getImage();
	BufferedImage myPic = new BufferedImage(protoPic.getWidth(null),protoPic.getHeight(null),BufferedImage.TYPE_INT_ARGB);
	Graphics g = myPic.createGraphics();
	g.drawImage(protoPic, 0, 0, 291, 516, null);
	

	JLabel picLabel = new JLabel(new ImageIcon(myPic));
	picLabel.setSize(291, 516);
	jp1.add(picLabel);
	
	jp1.setVisible(true);
	jp1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		

	}
}


//    C:\\Users\\Jason\\workspace\\scapper\\imgs\\img1.jpg