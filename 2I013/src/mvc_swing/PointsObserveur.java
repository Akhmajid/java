package mvc_swing;

import geometrie.Vecteur;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import strategie.StrategyPoint;
import voiture.VoitureIcone;

public class PointsObserveur implements ObserveurSWING{

	private StrategyPoint s;
	
	public PointsObserveur(StrategyPoint s){
		super();
		//this.listePoints=listePoints;
		this.s=s;
	}
	
	
	public void print(Graphics g) {
		ArrayList<Vecteur> listePoints=s.getListePoints();
		for (int i = 0; i < listePoints.size(); i++) {
			BufferedImage point = (BufferedImage) VoitureIcone.voitureIcone("marker.png");
			g.drawImage(point, (int) listePoints.get(i).getY() - 8,(int) listePoints.get(i).getX() - 16, null);
		}
		
	}

}
