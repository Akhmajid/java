package strategie;

import java.io.Serializable;

import radar.Radar;
import terrain.Circuit;
import voiture.Commande;
import voiture.Voiture;

public interface Strategy extends Serializable {
	public void init (Voiture v, Circuit c);
	public Commande getCommande();
	//public  Radar getRadar();
}
