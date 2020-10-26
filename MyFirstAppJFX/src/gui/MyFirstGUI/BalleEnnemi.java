package gui.MyFirstGUI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BalleEnnemi extends GraphicObject{
	
	private Point2D direction=new Point2D(0,0);
	
	public BalleEnnemi(Monstre monstre) {
		
		Image image=null;
		try {
			image = new Image(new FileInputStream("ImageJeu/balle1-1.PNG"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		}
		
		corps=new ImageView(image);
		corps.setTranslateX(monstre.getCorps().getTranslateX());
		corps.setTranslateY(monstre.getCorps().getTranslateY());
		updateDirection(monstre.getCorps().getRotate());
		
	}
	
	
	private void updateDirection(double rotation) {
		Point2D p;
		double x=0;
		double y=1;
		p=new Point2D(x,y);
		direction=p.normalize().multiply(7);
	}
	
	public void update() {
		corps.setTranslateX(corps.getTranslateX()+direction.getX());
		corps.setTranslateY(corps.getTranslateY()+direction.getY());		
	}
	
	

}
