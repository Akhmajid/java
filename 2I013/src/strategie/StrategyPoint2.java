package strategie;

import java.util.ArrayList;

import terrain.Circuit;
import voiture.Commande;
import voiture.Voiture;
import geometrie.Vecteur;

public class StrategyPoint2 implements Strategy {
	private Voiture voiture;
	private ArrayList<Vecteur> liste;
	
	public StrategyPoint2(Voiture voiture,ArrayList<Vecteur> liste ){
		this.voiture=voiture;
		this.liste=liste;
	}
	
	public void init(Voiture v, Circuit c) {
	}

	
	public Commande getCommande() {
		Vecteur position= voiture.getPosition().clonage();
		Vecteur direction= voiture.getDirection().clonage();
		
		if(!liste.isEmpty()) {
			if(Vecteur.dist(position, liste.get(0)) < 5) {
				liste.remove(liste.size()-1);
			}
		}
		
		if(liste.isEmpty()) {
			return new Commande(-1,0);
		}
		
		Vecteur cible = liste.get(0);
		
		double angle = direction.angle(cible.soustraction(position)); 
		double acc = 0; 
		
		if(Math.abs(angle) < voiture.getMaxTurn()) {
			acc = 0.5;//a modifier
		} else {
			acc = -1;
			angle = voiture.getMaxTurn() * Math.signum(angle);
		}
		
		return new Commande(acc, angle);
		
		
	}

}
