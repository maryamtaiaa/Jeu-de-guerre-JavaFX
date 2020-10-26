package gui.MyFirstGUI;


import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class FormResultat {

	Player player;
	MyFirstGUI form;
       Pane root3=new Pane();
	
       Line linezone=new Line(0,300,1000,300);
       Zone zone2=new Zone(linezone.getStartX(),linezone.getEndX()-100,linezone.getStartY(),800-100);
       public FormResultat(Player player,MyFirstGUI form) {
    	   this.form=form;
    	   this.player=player;
    	   drawresultat();
       }
	
	public Parent getRoot() {
		return root3;
	}
	
	Image image=new Image("file:ImageJeu/9.jpg");
	Image image2=new Image("file:ImageJeu/replay.png");
	
	private List<Chauve> chauves = new ArrayList();
	
public void drawresultat() {
		
		ImageView iv=new ImageView();
		ImageView replayimage=new ImageView();
		iv.setImage(image);
		iv.setFitWidth(1000);
		iv.setFitHeight(750);	
		Label label1=new Label("Votre score est :"+player.getScore());
		root3.getChildren().add(iv);
		label1.setTranslateX(300);
		label1.setTranslateY(300);
		label1.setFont(Font.font("Cambria", 50));
		label1.setTextFill(Color.WHITE);
		root3.getChildren().add(label1);
		Button replay=new Button("Replay",replayimage);
		replay.setLayoutX(300);
		replay.setLayoutY(400);
		replay.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
		//root3.getChildren().addAll(replay);
		
		if(Math.random()<0.01) {
			Chauve man = new Chauve(zone2);
			root3.getChildren().add(man.getCorps());
			chauves.add(man);
			man.getCorps().setTranslateX(man.getCorps().getTranslateX()+Math.random()*0.9);
			if(Math.random()<0.1) {
				man.getCorps().setTranslateY(man.getCorps().getTranslateY()-Math.random()*7);
			}
		}
		for(Chauve r:chauves){
			r.getCorps().setTranslateX(r.getCorps().getTranslateX()+Math.random()*0.9+Math.random());
			if(r.getCorps().getTranslateY()>350) {
				r.getCorps().setTranslateY(r.getCorps().getTranslateY()-0.19);
			}else {
				r.getCorps().setTranslateY(r.getCorps().getTranslateY()+0.19);
			}
			// remove Man if X > Window width
			if(r.getCorps().getTranslateX()>1000-20) {
				root3.getChildren().remove(r.getCorps());
				r.setAlive(false);
			}
			
		}
		
	}

      
}
