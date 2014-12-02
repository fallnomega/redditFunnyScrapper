package scapper;
import com.jaunt.*;

public class scapper {
	public static class Scapper
	{
		Scapper(){}
		public void scapper(){
		try{

			UserAgent userAgent = new UserAgent(); //create userAgent (headless browser)
			userAgent.visit("http://www.reddit.com/r/funny/");//visit url
			System.out.println(userAgent.doc.innerHTML());//print the document as html
			
			System.out.println("\n\nSettings: " + userAgent.settings);
			String title = userAgent.doc.findFirst("<title>").getText();
			System.out.println("\nReddits website title: " + title);
			String page = userAgent.doc.innerHTML();
			System.out.println(userAgent.doc.innerHTML().contains("<a class=\"thumbnail may-blank \""));//find beginning of img tag
			long imgAt = page.indexOf("><a class=\"thumbnail may-blank \"");//find index position of first img tag
			System.out.println("imgAt gives: " + imgAt);
			System.out.println(userAgent.doc.innerHTML().indexOf("><a class=\"thumbnail may-blank \""));

			
			
		}
		catch(JauntException e){ //if an http/connection error occurs, handle jaunt exception
			System.err.println(e);
		}

	}
	}

}
