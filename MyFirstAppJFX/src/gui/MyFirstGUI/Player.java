package gui.MyFirstGUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends GraphicObject{

	int score;
	public Player(Zone zone) {
		
		Image image=null;
		try {
			image = new Image(new FileInputStream("ImageJeu/player2.PNG"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		corps=new ImageView(image);
		((ImageView)corps).setX(0);
		((ImageView)corps).setY(0);
	    //doit appartenir zone
		double x=zone.getX1()+(zone.getX2()-zone.getX1())*Math.random();
		double y=646;/*zone.getY1()+(zone.getY2()-zone.getY1())*Math.random();*/
		
		corps.setTranslateX(x);
		corps.setTranslateY(y);
			
		
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
