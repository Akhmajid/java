package mvc_swing;

import geometrie.Vecteur;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import strategie.StrategyPoint;
import voiture.VoitureIcone;

public class PointsObserveur2 implements ObserveurSWING{
	private ArrayList<Vecteur> listePoints;
	
	

	public PointsObserveur2( ArrayList<Vecteur> listePoints){
		super();
		this.listePoints=listePoints;
		
	}
	
	public void print(Graphics g) {
		
		for (int i = 0; i < listePoints.size(); i++) {
			BufferedImage point = (BufferedImage) VoitureIcone.voitureIcone("marker.png");
			g.drawImage(point, (int) listePoints.get(i).getY() - 8,(int) listePoints.get(i).getX() - 16, null);
		}
		
	}
}