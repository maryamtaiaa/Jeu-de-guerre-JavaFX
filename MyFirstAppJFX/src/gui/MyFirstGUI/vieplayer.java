package gui.MyFirstGUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class vieplayer extends GraphicObject{

	
public vieplayer(Zone zone) {
		
		Image image=null;
		try {
			image = new Image(new FileInputStream("ImageJeu/vie3.PNG"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		corps=new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
	    //doit appartenir zone
		double x=4;
		double y=0;
		
		corps.setTranslateX(x);
		corps.setTranslateY(y);
		
		//System.out.println(this);
		
		
	}
}
