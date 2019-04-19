package strategie;

import radar.RadarClassique;
import radar.RadarPrudent;
import voiture.Commande;

public class StrategyPrudente extends StrategyRadar{
	private RadarPrudent radarP;

	public StrategyPrudente(RadarClassique r, RadarPrudent radar) {
		super(r);
		this.radarP=radar;
		
	}
	public Commande getCommande(){
		//this.getRadar().scores();
		Commande c = super.getCommande();
		double acc=c.getAcc();
		double angle=c.getTurn();
		
		
		double[]distanceSecurite = radarP.scores();
		
		
		if (radarP.safeDevant(distanceSecurite[1])){
			acc=1;
		}
		else{
			acc=0.05;
		}
		if(radarP.dangerDevant(distanceSecurite[1])) {
			acc = -1;
		}
		if(!radarP.safeAutour(distanceSecurite[0])) {
			angle = Math.max(angle, -0.5);
		}
		if(!radarP.safeAutour(distanceSecurite[2])) {
			angle = Math.min(angle, 0.5);
		}
		
		if (super.getVoiture().getVitesse()<=0.1){
			acc=0.2;
		}
		
		return new Commande(acc, angle);
	}

	
}
