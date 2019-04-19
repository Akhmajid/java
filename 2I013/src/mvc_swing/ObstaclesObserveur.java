package mvc_swing;

import geometrie.Vecteur;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import voiture.VoitureIcone;

public class ObstaclesObserveur implements ObserveurSWING {
	private ArrayList<Vecteur> listeObstacles;

	public ObstaclesObserveur(ArrayList<Vecteur> listeObstacles) {
		super();
		this.listeObstacles = listeObstacles;
	}

	public void print(Graphics g) {
		for (int i = 0; i < listeObstacles.size(); i++) {
			if (i==2 || i==8 || i== 14 || i== 20){
				BufferedImage obs = (BufferedImage)VoitureIcone.voitureIcone("obstacle2.png");
				g.drawImage(obs, (int) listeObstacles.get(i).getY() - 20,(int) listeObstacles.get(i).getX() - 20, null);
			}
			else if (i==3 || i== 9 || i== 15 ||i==21){
				BufferedImage obs = (BufferedImage)VoitureIcone.voitureIcone("obstacle3.png");
				g.drawImage(obs, (int) listeObstacles.get(i).getY() - 20,(int) listeObstacles.get(i).getX() - 30, null);
				}
			else if (i==4||i==10 || i==16 ||i==22 || i==0){
				BufferedImage obs = (BufferedImage)VoitureIcone.voitureIcone("obstacle4.png");
				g.drawImage(obs, (int) listeObstacles.get(i).getY() - 15,(int) listeObstacles.get(i).getX() - 15, null);
				}
			else if (i==5 || i==11 || i== 17 || i== 23){
				BufferedImage obs = (BufferedImage)VoitureIcone.voitureIcone("obstacle5.png");
				g.drawImage(obs, (int) listeObstacles.get(i).getY() - 15,(int) listeObstacles.get(i).getX() - 15, null);
				}
			else if (i==6 || i==12 || i==18 || i==24 ){
				BufferedImage obs = (BufferedImage)VoitureIcone.voitureIcone("obstacle6.png");
				g.drawImage(obs, (int) listeObstacles.get(i).getY() - 15,(int) listeObstacles.get(i).getX() - 15, null);
				}
			else{
				BufferedImage obs = (BufferedImage)VoitureIcone.voitureIcone("obstacle1.png");
				g.drawImage(obs, (int) listeObstacles.get(i).getY() - 15,(int) listeObstacles.get(i).getX() - 15, null);
				
			}
		}
	}

}
