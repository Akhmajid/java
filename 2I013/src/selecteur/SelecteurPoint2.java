package selecteur;

import geometrie.Vecteur;

import java.util.ArrayList;

import terrain.Circuit;
import terrain.TerrainTools;
import voiture.Voiture;

public class SelecteurPoint2 implements Selecteur {
	private Voiture voiture;
	private Circuit circuit;
	private boolean state;
	private ArrayList<Vecteur> listePoints;
	
	public SelecteurPoint2(Voiture voiture,Circuit circuit,ArrayList<Vecteur> liste) {
		super();
		this.voiture = voiture;
		this.circuit = circuit;
		this.listePoints = liste;
		this.state = false;
	}
	
	public boolean isSelected() {
		return state;
	}

	
	public void update() {
		if (!listePoints.isEmpty()) {
			Vecteur p = voiture.getPosition();
			Vecteur d = voiture.getDirection();
			
			Vecteur v = d.rotation(d.angle(listePoints.get(0).soustraction(p)));
			state=true;
			
			while (((int)p.getX() != (int)(listePoints.get(0).getX()) || ((int)p.getY() != (int)(listePoints.get(0).getY())))) {
				if (!TerrainTools.isRunnable(circuit.getTerrain(p))) {
					state= false;
					break;
				}
				p = p.addition(v.fact(0.1));
		
		
			}
			
		}
		
	}

}
