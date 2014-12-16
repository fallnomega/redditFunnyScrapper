package scapper;

import com.jaunt.*; 
import java.util.ArrayList;


public class scapper {
	//Scraper class to pull /r/funny image links
	public static class Scraper
	{
		private UserAgent userAgent = new UserAgent();//create userAgent (headless browser)
		private ArrayList<String> pages = new ArrayList(); //house additional page links
		private ArrayList<String> list = new ArrayList(); //house all the /r/funny img links
		private int numPages;//num of pages to scrape
		
		//default constructor
		Scraper(){
		}//end Scraper()
		
		//set num of pages to scrape
		Scraper(int pages){
			this.numPages = pages;
		}//end Scraper(int pages)
		
		//get num of pages
		int getNumPages(){
			return this.numPages;		
		}//end int getNumPages()
		
		//get img list size. not used but kept as utility for troubleshooting
		int getListSize(){
			return this.list.size();
		}//end int getListSize()
		
		//set link for userAgent to pull page data from first page on /r/funny
		void setLink(){
			try{
				this.userAgent.visit("http://www.reddit.com/r/funny/");//visit url
			}//end try
			catch(JauntException e){
				System.out.println("Connection timed out");
			}//end catch
		}//end void setLink()
		
		//set link for userAgent for additional pages 
		void setLink(String link){
			try{
				this.userAgent.visit(link);//visit url
			}//end try
			catch(JauntException e){
				System.out.println("Connection timed out");
			}//end catch
		}//end void setLink(String link)
		
		//return img links
		ArrayList<String> getList(ArrayList<String> list){
			list = this.list;
			return list;
		}//end ArrayList<String> getList(ArrayList<String> list)
		
		//find first image on page 1 and then call listing(string,int,int) to get additional links
		String getLinks(String page){
			int startPos = page.indexOf("thumbnail may-blank \" href=\"")+28;//find index position of first img tag
			int sentinal = 1;
			String link = this.listing(page, startPos,sentinal);
			return link;
		}//end String getLinks(String page)
		
		//find additional links
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
		}//end String listing(String page, int startPos, int sentinal)
		
		//set link to retreive img links and call findNextPage(String) if additional pages set for scrape
		public void getPages(){
			//</a><span class="separator"></span><a href=
			this.setLink();
			if(this.numPages > 1){
				findNextPage(this.userAgent.doc.innerHTML(), 1);	
			}//end if
			
		}//end public void getPages()
		
		//get links to additional pages in order to scrape them
		public void findNextPage(String page, int pageNum){
			if(pageNum> this.numPages) return;
			
			else if(pageNum==1) {
				int temp = page.indexOf("view more:&#32;<a href=\"");
				int startPos = temp + 24;
				int endPos = page.indexOf('"', startPos);
				String nextPage = page.substring(startPos, endPos);
				this.pages.add(nextPage);
//				System.out.println("pageNum is: " + pageNum + "\nelse if Next page is: " + nextPage);
				pageNum++;
				this.setLink(nextPage);
				findNextPage(this.userAgent.doc.innerHTML(), pageNum);
			}//end else if
			
			else{
				int temp = page.indexOf("prev</a><span class=\"separator\">");
				int startPos = temp + 48;
				int endPos = page.indexOf('"', startPos);
				String nextPage = page.substring(startPos, endPos);
				this.pages.add(nextPage);
//				System.out.println("pageNum is: " + pageNum + "\nelse Next page is: " + nextPage);
				pageNum++;
				this.setLink(nextPage);
				findNextPage(this.userAgent.doc.innerHTML(), pageNum);
			}//end else		
		}//end public void findNextPage(String page, int pageNum)
		
		//scrape function
		public void scrape(){
			this.getPages();//set the link and add pages
			getLinks(this.userAgent.doc.innerHTML());
			if(this.numPages > 1){
				for(int i = 0; i < this.pages.size();i++){
				this.setLink(this.pages.get(i));
				getLinks(this.userAgent.doc.innerHTML());
				}//end for(int i = 0; i < this.pages.size();i++)
			}//end if(this.numPages > 1
		}//end public void scrape()
	}//end public static class Scraper
}//end public class scapper