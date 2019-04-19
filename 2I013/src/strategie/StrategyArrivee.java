package strategie;

import geometrie.Vecteur;
import selecteur.*;
import terrain.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyArrivee implements Strategy{
	

	
	public StrategyArrivee( ){
		
	}
	
	public void init(Voiture voiture, Circuit circuit) {
	}

	public Commande getCommande() {
		return new Commande (1,0);
	}

}
