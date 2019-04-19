package strategie;

import radar.Radar;
import terrain.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyToutDroit implements Strategy{
	public StrategyToutDroit(){
		
	}
	public  Commande getCommande() { 
		return new Commande(1 ,0);
	}
	@Override
	public void init(Voiture v, Circuit c) {
		// TODO Auto-generated method stub
		
	}
	
}
