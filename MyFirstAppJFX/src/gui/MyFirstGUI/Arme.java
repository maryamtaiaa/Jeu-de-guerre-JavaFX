package gui.MyFirstGUI;

import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Arme {

	private Rectangle corps=new Rectangle(-6,0,10,50);
	private Circle sortie =new Circle(-3,0,4);
	
	
	
	public void updateSortie() {
		
		//System.out.println(corps.getTranslateY());
		sortie.setTranslateX(corps.getTranslateX());
		sortie.setTranslateY(corps.getTranslateY());
		
	}
	public Arme(GraphicObject player) {
		corps.setTranslateX(player.getCorps().getTranslateX());
		corps.setTranslateY(player.getCorps().getTranslateY());
		corps.setFill(Color.TRANSPARENT);
		sortie.setFill(Color.TRANSPARENT);
		updateSortie();
		
	}
	
	public void attachToPlayer(Player player) {
		corps.setTranslateX(player.getCorps().getTranslateX());
		corps.setTranslateY(player.getCorps().getTranslateY());
		updateSortie();
	}
	

	

	public Rectangle getCorps() {
		return corps;
	}

	public void setCorps(Rectangle corps) {
		this.corps = corps;
	}

	public Circle getSortie() {
		return sortie;
	}

	public void setSortie(Circle sortie) {
		this.sortie = sortie;
	}
	
	//si on tape w on va tourner vers la droite
	
	public void rotateRight() {
		corps.setRotate(corps.getRotate()-5);
	}
	
	//x: vers la gauche
	public void rotateLeft() {
		corps.setRotate(corps.getRotate()+5);
	}
	
	public double getRotate() {
		return corps.getRotate()-140;
	}
	

}
