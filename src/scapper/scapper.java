package scapper;
import com.jaunt.*;
import java.util.ArrayList;


public class scapper {
	public static class Scapper
	{
		private UserAgent userAgent = new UserAgent();//create userAgent (headless browser)
		private ArrayList<String> list = new ArrayList(); //house all the /r/funny img links
		Scapper(){
		}
		
		void setLink(){
			try{
				this.userAgent.visit("http://www.reddit.com/r/funny/");//visit url
			}
			catch(JauntException e){
				System.out.println("Connection timed out");
			}
		}
		
		String getLinks(){
			String page = this.userAgent.doc.innerHTML(); //save HTML to String obj
			int size = page.length();
			//System.out.println("Size of page is: " + size);
			int startPos = page.indexOf("thumbnail may-blank \" href=\"")+28;//find index position of first img tag
			int sentinal = 0;
			String link = this.listing(page, startPos,sentinal);
			//int endPos = page.indexOf('"', startPos); //find end position of index where img link end based on next " char
			//String link = page.substring(startPos,endPos); //retrieve img link based on start and end positions
			return link;
		}
		
		String listing(String page, int startPos, int sentinal){
			
			//System.out.println("First link at:  " + startPos);
			int endPos = page.indexOf('"', startPos); //find end position of index where img link end based on next " char
			String link = page.substring(startPos, endPos);//retrieve img link based on start and end positions
			//System.out.println(link);
			this.list.add(link);
			
			//startPos = endPos;
			//startPos = page.indexOf(("thumbnail may-blank \" href=\""), endPos);
			//System.out.println(startPos);
			//System.out.println(page.substring(startPos, startPos + 60));
			
			int temp = page.indexOf(("thumbnail may-blank \" href=\""), endPos);
			startPos = temp + 28;

			//System.out.println("New startPos and endPos gives: " + page.substring(startPos, startPos +40));
			if (sentinal == 25) return link;
			sentinal++;
			this.listing(page, startPos, sentinal);
			return link;
		}
		public void scapper(){
	
			this.setLink();//set the link
			//System.out.println(this.userAgent.doc.innerHTML());//print the document as html
			
			//System.out.println("\n\nSettings: " + userAgent.settings);
			//String title = userAgent.doc.findFirst("<title>").getText();
			//System.out.println("\nReddits website title: " + title);

			//String link = getLinks();
			//System.out.println("getLink returns: " + link);
			//this.list.add(getLinks());
			getLinks();
			//System.out.println("ArrayList list index 0 returns: " + this.list.get(0));
			System.out.println("The image list returned is :");
			for(String i : this.list){
				System.out.println(i);
			}
			


	}
	}

}
