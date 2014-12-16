package scapper;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import scapper.scapper.Scraper;

public class urlLoad {
	public static class  loadUrl{
		JFrame jp1;
		JPanel pane1;
		JPanel pane2;
		JPanel pane3;
		int numPages = 1;
		
		//constructor
		loadUrl()
		{
			createFrame();
		}//end constructor loadImg()
		
		//create gui for end users to scrape imgs from /r/funny
		void createFrame(){
			this.jp1 = new JFrame("Display images from the web");
			this.jp1.setLayout(new FlowLayout());
			this.pane1 = new JPanel();
			this.pane2 = new JPanel();
			this.pane3 = new JPanel();
			this.jp1.setSize(900, 700);
			this.pane1.setSize(200,25);
			this.pane2.setSize(200,25);
			this.pane3.setSize(500,500);
			this.pane1.setMaximumSize(this.pane1.getPreferredSize());
			this.pane2.setMaximumSize(this.pane2.getPreferredSize());
			this.pane3.setMaximumSize(this.pane3.getPreferredSize());
			this.jp1.add(this.pane3);
			JLabel pageLabel = new JLabel("Number of pages to scrape");//label
			jp1.add(pageLabel);
			JComboBox pages = new JComboBox();
			pages.setSize(50,50);
			pages.addItem("1");
			pages.addItem("2");
			pages.addItem("3");
			pages.addItem("4");
			pages.addItem("5");
			this.jp1.add(this.pane1);
			this.pane1.add(pages);
			pages.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent itemEvent){
					String intPages = itemEvent.getItem().toString();
					numPages = Integer.parseInt(intPages);
					//System.out.println(numPages);
				}//end public void itemStateChanged(ItemEvent itemEvent)
			});//end pages.addItemListener(new ItemListener())
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
		}//end void createFrame()
		

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
				Scraper test = new Scraper(numPages);//build new Scraper obj
				test.getNumPages();
				test.scrape();//run scraper
				this.listing = test.getList(this.listing);//MyListener given links from scraper
				for(String i: this.listing){
					if(i.endsWith("jpg")!=true && i.endsWith("gif")!=true &&i.endsWith("jpeg")!=true &&i.endsWith("png")!=true) continue;
					this.link+= i + "\n";
					this.combo.addItem(i);
				}//end for(String i: this.listing)
			}//end 	public void actionPerformed(ActionEvent e)
		}//end public class MyListener implements ActionListener
		
		//implementing ItemListener for JComboBox combo
		ItemListener itemListener = new ItemListener(){
			public void itemStateChanged(ItemEvent itemEvent){
				loadImage((String)itemEvent.getItem());
			}//end public void itemStateChanged(ItemEvent itemEvent)
		};//end ItemListener
		
		//implement class ImageComponent for the loadURL(String) method
		class ImageComponent extends JComponent{
			private final BufferedImage img;
			public ImageComponent(URL url) throws IOException{
				img = ImageIO.read(url);
				setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
			}//end public ImageComponent(URL url) throws IOException
			
			@Override protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), this);
			}//end @Override protected void paintComponent(Graphics g)
		}//end class ImageComponent extends JComponent
		
		//load image into pane3 based on user choosing from selection
		void loadImage(String pic){
			this.pane3.removeAll();
			try 
			{
				URL url = new URL(pic);
				ImageComponent img = new ImageComponent(url);
				JScrollPane scrollPane = new JScrollPane(img);
				scrollPane.setPreferredSize(new Dimension(500,500));
				this.pane3.add(scrollPane);
		        this.pane3.revalidate();
		        this.pane3.repaint();	
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
	}//end public static class loadUrl
}//end public class urlLoad
