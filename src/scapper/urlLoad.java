package scapper;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import scapper.scapper.Scrape.MyListener;
import scapper.scapper.Scraper;

public class urlLoad {
	public static class  loadUrl{
		ArrayList<ImageIcon> picList = new ArrayList<ImageIcon>();
		//ArrayList<String> fileNames = new ArrayList<String>();
		ArrayList<String> urls = new ArrayList<String>();
		JFrame jp1;
		JPanel pane1;
		JPanel pane2;
		JPanel pane3;
		
		//constructor
		loadUrl()
		{
			loadList();
			printList();
		}//end constructor loadImg()
		
		void loadList(){
			Scraper scraping = new Scraper();
			scraping.scapper();
			scraping.setLink();
			this.urls = scraping.getList(this.urls);
			for(String i: this.urls){
				System.out.println(i);
			}
			
		}//end void loadList()
		
		void printList() {
			this.jp1 = new JFrame("Display images from the web");
			this.jp1.setLayout(new FlowLayout());
			this.pane1 = new JPanel();
			this.pane2 = new JPanel();
			this.pane3 = new JPanel();
			this.jp1.setSize(600, 700);
//			this.pane1.setLayout(new BoxLayout(pane1,BoxLayout.Y_AXIS));
//			this.pane2.setLayout(new BoxLayout(pane2, BoxLayout.X_AXIS));
			this.pane1.setSize(200,25);
			this.pane2.setSize(200,25);
			this.pane3.setSize(400,400);
			this.pane1.setMaximumSize(this.pane1.getPreferredSize());
			this.pane2.setMaximumSize(this.pane2.getPreferredSize());
			this.pane3.setMaximumSize(this.pane3.getPreferredSize());
			//this.jp1.add(this.pane2);
			this.jp1.add(this.pane3);
			JLabel scrapeLabel = new JLabel("Click to scrape");//label	
			jp1.add(scrapeLabel);//add label
			JButton scrape = new JButton("Scrape"); //Scrape button made
			this.jp1.add(scrape); //add button to jframe instance	
			
			JComboBox combo = new JComboBox();
			combo.setSize(100, 100);
			this.pane2.add(combo);
			combo.addItemListener(itemListener);

			MyListener listener = new MyListener(combo); //subclass of actionlistener
			scrape.addActionListener(listener);//call MyListener(JTextArea text) constructor

			this.jp1.add(this.pane2);
			JLabel picLabel = new JLabel("Pictures from da internet's");
			this.jp1.add(picLabel);
			this.jp1.add(this.pane3);
			
			this.jp1.setVisible(true);
			this.jp1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}//end void printList() 

		public class MyListener implements ActionListener{
			
			private JComboBox combo;
			String link = "";//Listener instance link
			ArrayList <String> listing = new ArrayList();//ArrayList to house Scraper links
			
			//call MyListener with JComboBox  and set combo

			public MyListener(JComboBox combo){
				this.combo = combo;
			}//end public MyListener(JComboBox combo)
			
			//implement actionlistener on button click event
			public void actionPerformed(ActionEvent e){
				Scraper test = new Scraper();//build new Scraper obj
				test.scapper();//run scraper
				this.listing = test.getList(this.listing);//MyListener given links from scraper

				for(String i: this.listing){
					if(i.endsWith("jpg")!=true && i.endsWith("gif")!=true &&i.endsWith("jpeg")!=true &&i.endsWith("png")!=true) continue;
					this.link+= i + "\n";
					this.combo.addItem(i);
				}//end for(String i: this.listing)
			}//end 	public void actionPerformed(ActionEvent e)
		}//end public class MyListener implements ActionListener
		
		ItemListener itemListener = new ItemListener(){
			public void itemStateChanged(ItemEvent itemEvent){
				loadImage((String)itemEvent.getItem());
			}//end public void itemStateChanged(ItemEvent itemEvent)
		};//end ItemListener
		
		class ImageComponent extends JComponent{
			private final BufferedImage img;
			public ImageComponent(URL url) throws IOException{
				img = ImageIO.read(url);
				setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
//				setPreferredSize(new Dimension(500,500));
			}//end public ImageComponent(URL url) throws IOException
			
			@Override protected void paintComponent(Graphics g){
				
				super.paintComponent(g);
				g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), this);

			}//end @Override protected void paintComponent(Graphics g)
		}//end class ImageComponent extends JComponent
		
		void loadImage(String pic){
			System.out.println("PIC IS " + pic);

			this.pane3.removeAll();

	      

			try 
			{
				
//				URL url = new URL(pic);
//				ImageComponent img = new ImageComponent(url);
//				this.pane3.add(new JScrollPane(img));
//		        this.jp1.revalidate();
//		        this.jp1.repaint();
				

				URL url = new URL(pic);
				ImageComponent img = new ImageComponent(url);
				JScrollPane scrollPane = new JScrollPane(img);
				scrollPane.setPreferredSize(new Dimension(500,500));
				//this.pane3.setPreferredSize(new Dimension(500,500));
				this.pane3.add(scrollPane);
		        this.jp1.revalidate();
		        this.jp1.repaint();
				
				
			} //end try
			catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				System.out.println("MalformedURLException");
			}//end catch (MalformedURLException e) 
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IOException");
			}//end catch (IOException e


		}//end void loadImage(String pic)
	}//end public static class  loadImg
}//end public class loadImages
