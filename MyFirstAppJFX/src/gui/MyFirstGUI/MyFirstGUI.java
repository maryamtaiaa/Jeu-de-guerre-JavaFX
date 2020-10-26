package gui.MyFirstGUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyFirstGUI extends Application {

	//Pane1
	Pane root1=new Pane();
	Pane root=new Pane();
	//Pane root3=new Pane();
	
	//Scene
	Scene scene1=new Scene(root1,1000,550);
	Scene scene=new Scene(root,1000,750);
	//Scene scene3=new Scene(root3,1000,500);
	
	
	
	//accueil		
	String introurl="SonJeu/intro.mp4";
	Media intro=new Media(new File(introurl).toURI().toString());
	MediaPlayer media=new MediaPlayer(intro);
	MediaView mediaview=new MediaView(media);
	Button button1=new Button("Start");
	
	
	private void drawvideo() {
	   mediaview.setFitHeight(800);
	   mediaview.setFitWidth(1000);
		root1.getChildren().addAll(mediaview,button1);
		media.play();
	}
	
	//Text t=new Texte();
	
	//Les objets de jeu
	//elements

	Line linezone=new Line(0,300,1000,300);
	Zone zone1=new Zone(0,linezone.getEndX()-100,30,linezone.getEndY()-100);
	Zone zone2=new Zone(linezone.getStartX(),linezone.getEndX()-100,linezone.getStartY(),800-100);
	
	Player player=new Player(zone2);
	private List<Monstre> monstres=new ArrayList<>();
	private List<Balle> balles=new ArrayList<>();
	private List<BalleEnnemi> ballesennemi=new ArrayList<>();
	private List<vieplayer> coeurs=new ArrayList<>();
	Arme arme=new Arme(player);
	
	private List<Chauve> chauves = new ArrayList();
	//Media
	
	private String shoturl="SonJeu/shot.mp3";
	Media shot=new Media(new File(shoturl).toURI().toString());
	private String boomurl="SonJeu/mort.mp3";
	Media boom=new Media(new File(boomurl).toURI().toString());
	private String dragonurl="SonJeu/dragonshot.mp3";
	Media dragon=new Media(new File(dragonurl).toURI().toString());
	private String loseurl="SonJeu/lose.mp3";
	Media lose=new Media(new File(loseurl).toURI().toString());
	
	Image image2=new Image("file:ImageJeu/lose1.jpg");
	ImageView end=new ImageView();
	
	
	public void drawresultat() {
		
		ImageView iv=new ImageView();
		iv.setImage(image);
		iv.setFitWidth(1000);
		iv.setFitHeight(500);	
		Label label2=new Label("Votre score est :"+player.getScore());
		//root3.getChildren().add(iv);
		//root3.getChildren().add(label2);
		
		
	}
	
	
	//AnimationTimer
	
	AnimationTimer animation=new AnimationTimer() {

		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			if(coeurs.size()>=1) {
			for(Monstre m:monstres) {
				if(Math.random()<0.005 && coeurs.size()>=1) {
				BalleEnnemi balleennemi=new BalleEnnemi(m);
				   root.getChildren().add(balleennemi.getCorps());
				   ballesennemi.add(balleennemi);
				   MediaPlayer mediaplayer=new MediaPlayer(dragon);
					mediaplayer.play();										
				   }
				}}
			refreshContent();
			
		}						
	};
	
	//event handler
		
	EventHandler<KeyEvent> event=new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent event) {
			// TODO Auto-generated method stub
			

if(event.getCode()==KeyCode.SPACE) {
	MediaPlayer mediaplayer=new MediaPlayer(shot);
	mediaplayer.play();
	
	Balle balle=new Balle(arme);
	root.getChildren().add(balle.getCorps());
	balles.add(balle);
	
	
}
if(event.getCode()==KeyCode.LEFT) {
	player.getCorps().setTranslateX(player.getCorps().getTranslateX()-5);
	arme.attachToPlayer(player);
}
if(event.getCode()==KeyCode.RIGHT) {
	player.getCorps().setTranslateX(player.getCorps().getTranslateX()+5);
	arme.attachToPlayer(player);
}

if(event.getCode()==KeyCode.UP) {
	player.getCorps().setTranslateY(player.getCorps().getTranslateY()-5);
	arme.attachToPlayer(player);
}

if(event.getCode()==KeyCode.DOWN) {
	player.getCorps().setTranslateY(player.getCorps().getTranslateY()+5);
    arme.attachToPlayer(player);
}


if(event.getCode()==KeyCode.ENTER) {
	//initWindow(player);
	
	root.getChildren().remove(end);
	for(Monstre monstre:monstres) {
		root.getChildren().remove(monstre.getCorps());
	}
	
	root.getChildren().remove(label1);
	
	Label label1=new Label("Votre score est :"+player.getScore());
	label1.setTranslateX(300);
	label1.setTranslateY(300);
	label1.setFont(Font.font("Cambria", 50));
	label1.setTextFill(Color.WHITE);
	root.getChildren().add(label1);
}


		}
		
		
	};
	


	Label label1=new Label("Score:"+player.score);
	
	
	private void refreshContent() {
		//Parcourir la collection des balles: pour mettre à jour la position
	
		
		for(Balle balle:balles) {
			for(Monstre monstre:monstres) {
				if(balle.touch(monstre)) {
					root.getChildren().removeAll(balle.getCorps(),monstre.getCorps());
				    balle.setAlive(false);
				    monstre.setAlive(false);
				    ++player.score;
				    label1.setText("Score:"+player.score);
					player.setScore(player.score);
				}
			}
		}
		
		
		
		for(BalleEnnemi balle:ballesennemi) {
			
				if(balle.touch(player)) {	
					if(coeurs.size()!=0) {
					
					root.getChildren().removeAll(balle.getCorps());
					balle.setAlive(false);
				
					vieplayer p=coeurs.get(coeurs.size()-1);
					root.getChildren().removeAll(p.getCorps());
					p.setAlive(false);
					}
					if(coeurs.size()<=1) {
					MediaPlayer mediaplayer=new MediaPlayer(boom);
					mediaplayer.play();
					root.getChildren().removeAll(balle.getCorps(),player.getCorps());
					balle.setAlive(false);
				    player.setAlive(false);
				    
				    mediaplayer.stop();
				    MediaPlayer mediaplayer2=new MediaPlayer(lose);
					mediaplayer2.play();
				    
					
					end.setImage(image2);
					end.setTranslateX(100);
					
					end.setFitWidth(800);
					end.setFitHeight(800);						
					root.getChildren().add(end);
					
					/*for(Chauve c:chauves) {
						root.getChildren().remove(c.getCorps());
						c.setAlive(false);
					}*/
					
				    }	
					
				}	
			
		}
		
		ballesennemi.removeIf(GraphicObject::isDead);
		
		monstres.removeIf(GraphicObject::isDead);
		balles.removeIf(GraphicObject::isDead);
	    coeurs.removeIf(GraphicObject::isDead);
		
		for(Balle balle:balles) {
			balle.update();
		}
		
		
		for(BalleEnnemi balle:ballesennemi) {
			balle.update();
		}
		
		if(Math.random()<0.02 && coeurs.size()>1) {
		Monstre monstre=new Monstre(zone1);
		root.getChildren().add(monstre.getCorps());
		monstres.add(monstre);	
		}
		
		
		
		if(Math.random()<0.01) {
			Chauve man = new Chauve(zone2);
			root.getChildren().add(man.getCorps());
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
				root.getChildren().remove(r.getCorps());
				r.setAlive(false);
			}
			
		}
		
		
		
		
	}
	
	private void alerte() {
        Alert alert = new Alert(AlertType.WARNING);
 
        alert.setTitle("");
         alert.setHeight(30);
        // Header Text: null
        alert.setHeaderText("                     Votre Score est :");
        alert.setContentText("                                    "+player.getScore());
        alert.showAndWait();
    }
	
	

	
	private void initElements() {
		linezone.setStroke(Color.TRANSPARENT);	
	}
	
	
	
	private void drawElement() {
		//ajouter à root
		root.getChildren().add(linezone);
		root.getChildren().add(player.getCorps());
		root.getChildren().add(arme.getCorps());
		root.getChildren().add(arme.getSortie());
		
		
		for(int i=0;i<3;i++) {
			vieplayer coeur=new vieplayer(zone1);
			coeur.getCorps().setTranslateX(i*20);
			coeur.getCorps().setTranslateY(4);
			root.getChildren().add(coeur.getCorps());
			coeurs.add(coeur);
		}
		
		label1.setTranslateX(80);
		label1.setTranslateY(4);
		label1.setFont(Font.font("Cambria", 25));
		label1.setTextFill(Color.WHITE);
		root.getChildren().add(label1);
		
		
	}
	
	public Parent getRoot() {
		return root;
	}
	
	public void initWindow(Player player) {
	       
        FormResultat form=new FormResultat(player,this);
        Parent root = null;
        root = form.getRoot();
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setScene(newScene);
        newStage.show();

}
    
	Image image=new Image("file:ImageJeu/9.jpg");
	
	public void refresh() {
		initElements();
		drawElement();
		animation.start();
		scene.setOnKeyPressed(event);
	}
	
	@Override
	public void start(Stage window) throws Exception{
		
		
	   window.setY(20);
	   window.setX(300);	
		drawvideo();
		button1.setTranslateX(450);
		button1.setTranslateY(0);
		button1.setStyle("-fx-background-color: black;-fx-font-size: 3em; -fx-text-fill: white;");
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {            	
            	media.stop();            	
            	initElements();
        		drawElement();
        		animation.start();
        		scene.setOnKeyPressed(event);
        		window.setScene(scene);  
        		 
        		
        		
        	
            }
        });
		
	
		
		
		window.setTitle("Jeu du guerre");
		//window.setWidth(1000);
		//window.setHeight(800);
		ImageView iv=new ImageView();
		iv.setImage(image);
		iv.setFitWidth(1000);
		iv.setFitHeight(800);
				
		root.getChildren().add(iv);
				
		
		
		//licer la scene a window
		window.setScene(scene1);					
		window.show();
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
