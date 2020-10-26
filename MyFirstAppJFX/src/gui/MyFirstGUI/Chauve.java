package gui.MyFirstGUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Chauve extends GraphicObject{

	public Chauve(Zone zone){
		Image image=null;
		try {
			
			image = new Image(new FileInputStream("ImageJeu/chauve.png"));
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		corps=new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
		

		((ImageView)corps).setFitHeight(80);
		((ImageView)corps).setFitWidth(100);
		
		corps.setTranslateX(10);
		double min=zone.getY1();
		double max=zone.getY2();
	    double x = (Math.random()*((max-min)+1))+min;
		corps.setTranslateY(x);
		
	}
	
}
