package scapper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class loadImages {
	public static class  loadImg{
		ArrayList<ImageIcon> picList = new ArrayList<ImageIcon>();
		ArrayList<String> fileNames = new ArrayList<String>();
		JFrame jp1;
		JPanel pane1;
		JPanel pane2;
		loadImg()
		{
			loadList();
			printList();
		}//end constructor loadImg()
		
		void loadList(){
			int num = 1;
			String file = "img"+ num+".jpg";
			
			while(num <=5){
				file = "img"+ num+".jpg";
				ImageIcon iconPic = new ImageIcon("C:\\Users\\Jason\\workspace\\scapper\\imgs\\" + file);
				this.picList.add(iconPic);
				num++;	
				this.fileNames.add(file);
			}//end while(num <=5)	
		}//end void loadList()
		
		void printList() {
			this.jp1 = new JFrame("Display images");
			this.pane1 = new JPanel();
			this.pane2 = new JPanel();
			this.jp1.setSize(600, 700);
			this.pane1.setLayout(new BoxLayout(pane1,BoxLayout.Y_AXIS));
			this.pane2.setLayout(new BoxLayout(pane2, BoxLayout.X_AXIS));
			this.pane1.setSize(200,25);
			this.pane2.setSize(200,100);
			this.pane1.setMaximumSize(pane1.getPreferredSize());
			this.pane2.setMaximumSize(pane2.getPreferredSize());
			this.jp1.add(pane1);
			
			JComboBox combo = new JComboBox();
			combo.setSize(100, 100);
			this.pane1.add(combo);
			combo.addItemListener(itemListener);

			for(String i: this.fileNames){
				combo.addItem(i);
			}//end for(String i: this.fileNames)

			this.jp1.add(this.pane2);
			this.jp1.setVisible(true);
			this.jp1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}//end void printList() 


		ItemListener itemListener = new ItemListener(){
			public void itemStateChanged(ItemEvent itemEvent){
				loadImage((String)itemEvent.getItem());
			}//end public void itemStateChanged(ItemEvent itemEvent)
		};//end ItemListener
		
		void loadImage(String pic){
			JLabel picLabel = new JLabel();
			this.pane2.removeAll();
			
			for(String i: this.fileNames){
				if(pic.equals(i)){
					int index = this.fileNames.indexOf(pic);
					Image protoPic = this.picList.get(index).getImage();
					BufferedImage myPic = new BufferedImage(protoPic.getWidth(null),protoPic.getHeight(null),BufferedImage.TYPE_INT_ARGB);
					Graphics g = myPic.createGraphics();
					g.drawImage(protoPic, 0, 0, 291, 516, null);
					picLabel = new JLabel(new ImageIcon(myPic));
					picLabel.setSize(291, 516);
					this.pane2.add(picLabel);
					this.pane2.revalidate();
					this.pane2.repaint();
				}//end if(pic.equals(i))
			}//end for(String i: this.fileNames)
		}//end void loadImage(String pic)
	}//end public static class  loadImg
}//end public class loadImages
