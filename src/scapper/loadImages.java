package scapper;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;

public class loadImages {
	public static class  loadImg{
		ArrayList<ImageIcon> picList = new ArrayList<ImageIcon>();
		loadImg()
		{

			loadList();
			printList();
			

		}
		void loadList(){
			int num = 1;
			String file = "img"+ num+".jpg";
			
			while(num <=5){
				file = "img"+ num+".jpg";
				ImageIcon iconPic = new ImageIcon("C:\\Users\\Jason\\workspace\\scapper\\imgs\\" + file);
				this.picList.add(iconPic);
				num++;		
			}

			
		}
		void printList(){
	
			
			JFrame jp1 = new JFrame("Display images");
			jp1.setSize(600, 700);
			jp1.setLayout(new BorderLayout());

//			int counter = 0;
//			while(counter < 5){
//				Image protoPic = this.picList.get(counter).getImage();
//				BufferedImage myPic = new BufferedImage(protoPic.getWidth(null),protoPic.getHeight(null),BufferedImage.TYPE_INT_ARGB);
//				Graphics g = myPic.createGraphics();
//				g.drawImage(protoPic, 0, 0, 291, 516, null);
//				JLabel picLabel = new JLabel(new ImageIcon(myPic));
//				picLabel.setSize(291, 516);
//				jp1.add(picLabel);
//				System.out.println(counter);
//				counter++;
//			}
//			
//			jp1.setVisible(true);
//			jp1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
///////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			//ImageIcon iconPic = new ImageIcon("C:\\Users\\Jason\\workspace\\scapper\\imgs\\img1.jpg");
			//Image protoPic = iconPic.getImage();
			Image protoPic = this.picList.get(0).getImage();
			BufferedImage myPic = new BufferedImage(protoPic.getWidth(null),protoPic.getHeight(null),BufferedImage.TYPE_INT_ARGB);
			Graphics g = myPic.createGraphics();
			g.drawImage(protoPic, 0, 0, 291, 516, null);
		
System.out.println("HI");
			JLabel picLabel = new JLabel(new ImageIcon(myPic));
			picLabel.setSize(291, 516);
			
			jp1.add(picLabel);
			jp1.setVisible(true);
			jp1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}

		
	}

}
