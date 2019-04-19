package strategie;

import java.util.ArrayList;

import selecteur.Selecteur;
import terrain.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategySelecteurImpl implements StrategySelecteur {
	private ArrayList<Strategy> listeStrategy;
	private ArrayList<Selecteur> listeSelecteur;
	
	public StrategySelecteurImpl(){
		listeStrategy = new ArrayList<Strategy>();
		listeSelecteur = new ArrayList<Selecteur>();
	}
	
	public void init(Voiture v, Circuit c) {
	}

	
	public Commande getCommande() {
		for (Selecteur s :listeSelecteur){
			s.update();
		}
		
		for (int i=0; i<listeStrategy.size();i++){
			if (listeSelecteur.get(i).isSelected()==true){
				return listeStrategy.get(i).getCommande();
			}
		}
		return new Commande (1,0);
	}

	
	public void add(Strategy strategy, Selecteur selecteur) {
		listeStrategy.add(strategy);
		listeSelecteur.add(selecteur);
		
	}

}
