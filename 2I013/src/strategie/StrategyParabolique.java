package strategie;

import radar.RadarClassique;
import radar.RadarParabolique;
import radar.RadarPrudent;
import voiture.Commande;

public class StrategyParabolique extends StrategyRadar{
	private RadarParabolique rp;
	//private RadarPrudent rpr;
	
	public StrategyParabolique(RadarClassique r, RadarParabolique rp) {
		super(r);
		this.rp=rp;
		//this.rpr=rpr;
	}
	
	public Commande getCommande(){
		//this.getRadar().scores();
		Commande c = super.getCommande();
		double acc=c.getAcc();
		double angle=c.getTurn();
		
		//double[]distanceSecurite = rpr.scores();
		boolean vg = rp.isTurnGTight();
		boolean vd = rp.isTurnDTight();
		vg=false;
		if (vg==true){
			acc=-1;
		}
		else{
			acc+=0.5;
			if (acc>1){
				acc=1;
			}
		}
		if (vd==true){
			acc=-1;
		}
		else{
			acc+=0.5;
			if (acc>1){
				acc=1;
			}
		}
		
		/*if (rpr.safeDevant(distanceSecurite[1])){
			acc=1;
		}
		else{
			acc=0.05;
		}
		if(rpr.dangerDevant(distanceSecurite[1])) {
			acc = -1;
		}
		if(!rpr.safeAutour(distanceSecurite[0])) {
			angle = Math.max(angle, -0.5);
		}
		if(!rpr.safeAutour(distanceSecurite[2])) {
			angle = Math.min(angle, 0.5);
		}
		
		if (super.getVoiture().getVitesse()<=0.1){
			acc=0.2;
		}
		*/
		if (super.getVoiture().getVitesse()<=0.1){
			acc=0.2;
		}
		
		
		return new Commande(acc, angle);
	}

	
}
