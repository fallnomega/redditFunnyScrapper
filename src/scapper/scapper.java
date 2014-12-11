package scapper;

import com.jaunt.*; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;


public class scapper {
	//Scrapper class to pull /r/funny image links
	public static class Scraper
	{
		private UserAgent userAgent = new UserAgent();//create userAgent (headless browser)
		private ArrayList<String> list = new ArrayList(); //house all the /r/funny img links
		Scraper(){
		}
		
		void setLink(){
			try{
				this.userAgent.visit("http://www.reddit.com/r/funny/");//visit url
			}
			catch(JauntException e){
				System.out.println("Connection timed out");
			}
		}
		
		ArrayList<String> getList(ArrayList<String> list){
			list = this.list;
			return list;
		}
		
		String getLinks(){
			String page = this.userAgent.doc.innerHTML(); //save HTML to String obj
			int size = page.length();
			int startPos = page.indexOf("thumbnail may-blank \" href=\"")+28;//find index position of first img tag
			int sentinal = 0;
			String link = this.listing(page, startPos,sentinal);
			return link;
		}
		
		String listing(String page, int startPos, int sentinal){
			
			int endPos = page.indexOf('"', startPos); //find end position of index where img link end based on next " char
			String link = page.substring(startPos, endPos);//retrieve img link based on start and end positions
			this.list.add(link);
			int temp = page.indexOf(("thumbnail may-blank \" href=\""), endPos);
			startPos = temp + 28;
			if (sentinal == 25) return link;
			sentinal++;
			this.listing(page, startPos, sentinal);
			return link;
		}
		public void scapper(){
	
			this.setLink();//set the link
			getLinks();

		}
	}
	
	
	//JFrame setup by subclass Scrape
	static class Scrape extends JFrame {
		static private String selectedString(ItemSelectable is) {
		    Object selected[] = is.getSelectedObjects();
		    return ((selected.length == 0) ? "null" : (String) selected[0]);
		  }
	
		Scrape(){
			super("Scaper"); //calls JFrame contractor to set it up along with title
			setLayout(new FlowLayout()); //set layout to flow
			getContentPane().setBackground(Color.gray);//set background color to gray
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //terminate on close
			/*
			 * pack is used when you are using layout managers and you want the JFrame 
			 * to be the smallest size showing all components. If you set the size of 
			 * your JFrame manually ( setSize(), setBounds() ) then you don't need to 
			 * call pack().
			 */
			this.pack();
			
			JLabel scrapeLabel = new JLabel("Click to scrape");//label	
			add(scrapeLabel);//add label
			
			JButton scrape = new JButton("Scrape"); //Scrape button made
			add(scrape,BorderLayout.CENTER); //add button to jframe instance	
			

			JComboBox combo = new JComboBox();
			add(combo);
			ItemListener itemListener = new ItemListener(){
				public void itemStateChanged(ItemEvent itemEvent){
					int state = itemEvent.getStateChange();
					if(state==ItemEvent.SELECTED) System.out.println("Link: " + itemEvent.getItem());	
				}
			};
			combo.addItemListener(itemListener);

			MyListener listener = new MyListener(combo); //subclass of actionlistener
			scrape.addActionListener(listener);//call MyListener(JTextArea text) constructor
			
			this.setLocationRelativeTo(null);//set location on window
			this.setSize(500,500);//set size of frame
			setVisible(true);//visible to user
		}
		
		//actionlistener implemented by mylistener
		public class MyListener implements ActionListener{
		

			private JComboBox combo;
			String link = "";//Listener instance link
			ArrayList <String> listing = new ArrayList();//ArrayList to house Scraper links
			
			//call MyListener with JTextArea  and set text

			public MyListener(JComboBox combo){
				this.combo = combo;
			}
			
			//implement actionlistener on button click event
			public void actionPerformed(ActionEvent e){
				Scraper test = new Scraper();//build new Scraper obj
				test.scapper();//run scraper
				this.listing = test.getList(this.listing);//MyListener given links from scraper
				

				for(String i: this.listing){
					this.link+= i + "\n";
					this.combo.addItem(i);
				}
				
				

				
			}
		}
		
	}
}