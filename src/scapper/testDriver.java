package scapper;

import javax.swing.*;
//import java.applet.Applet;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.net.URL;


import javax.imageio.ImageIO;

import scapper.scapper.Scapper;

public class testDriver {
	public static void main(String args[]){
		Scapper test = new Scapper();
		test.scapper();
		HelloWorld hello = new HelloWorld();

		
	}
	
static class HelloWorld extends JFrame{
		HelloWorld(){
			JLabel jlbHelloWorld = new JLabel("Hello World");
			add(jlbHelloWorld);
			this.setSize(100,300);
			setVisible(true);
		}
		
	}


}
