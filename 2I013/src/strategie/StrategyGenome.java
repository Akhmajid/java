package strategie;

import radar.RadarClassique;
import terrain.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyGenome implements Strategy {
	private RadarClassique radar;

	public void init(Voiture v, Circuit c) {

	}

	
	public Commande getCommande() {
		radar.scores();         
        double com = 
          radar.thetas()[radar.getBestIndex()]/car.getBraquage();
        double turnAbs = 
          Math.min(Math.abs(com), car.getMaxTurn());

        double acc = 1; // accélération par défaut
        if(RadarClassique.distInPixels(0, car, track)<param[0]) // methode static qui regarde devant la voiture
            acc = param[1];   
        else if(RadarClassique.distInPixels(
                radar.thetas()[radar.getBestIndex()], car, track)  // methode static qui regarde là où la voiture désire aller
                     < param[0]*1.5)
            acc = param[1];                     
        else if(param[2]*turnAbs < Math.abs(com)) acc = param[3];

        if(car.getVitesse() < param[4]) acc = 1;

        Commande c = new Commande(acc, turnAbs * Math.signum(com));
        return c;
			}

}
