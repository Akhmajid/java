package strategie;

import java.util.ArrayList;

import radar.Radar;
import terrain.Circuit;
import voiture.Commande;
import voiture.*;
import geometrie.Vecteur;

public class StrategyPoint implements Strategy{
	private ArrayList<Vecteur> listePoints;
	private int index;
	private Voiture v;
	
	public StrategyPoint(Voiture v,ArrayList<Vecteur> liste){
		super();
		this.v=v;
		listePoints=liste;
		index=0;
	}
	
	public void init(Voiture v, Circuit c) {
	}

	
	public Commande getCommande() {
		Vecteur position= v.getPosition().clonage();
		Vecteur direction= v.getDirection().clonage();
		
		if(v.getPosition().egalite(listePoints.get(index))) {
			index++;
			if (index>= listePoints.size()){
				return new Commande(0,0);
			}
		}
		
		Vecteur cible =new Vecteur (listePoints.get(index).getX()-position.getX(), listePoints.get(index).getY()-position.getY());
		
		double angle = direction.angle(cible); 
		
		double acc = 0; 
		
		
		if(Math.abs(angle) < v.getMaxTurn()) {
			acc = 1;
		} else {
			acc = -1;
			angle = v.getMaxTurn() * Math.signum(angle);
		}
		
		return new Commande(acc, angle);
		
	}

	public ArrayList<Vecteur> getListePoints(){
		return listePoints;
	}
	
	public int getIndex(){
		return index;
	}
}
