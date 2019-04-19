package voiture;

import java.util.Arrays;

import geometrie.Vecteur;

public class VoitureImpl implements Voiture{
	
	// outils pour la gestion des limites de rotation en fonction de la vitesse
	private double[] tabVitesse= {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.}; 
	private double[] tabTurn={1.,  0.9, 0.75, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05}; 

	// capacites   
	private final double vmax, braquage;
	private final double alpha_c, alpha_f, beta_f;

	// etat a l'instant t
	private double vitesse;
	private Vecteur position;
	private Vecteur direction;
	
	public VoitureImpl(double[] tabVitesse, double[] tabTurn, double vmax, double braquage, double alpha_c,
			double alpha_f, double beta_f, double vitesse, Vecteur position, Vecteur direction) {
		
		this.tabVitesse = tabVitesse;
		this.tabTurn = tabTurn;
		this.vmax = vmax;
		this.braquage = braquage;
		this.alpha_c = alpha_c;
		this.alpha_f = alpha_f;
		this.beta_f = beta_f;
		this.vitesse = vitesse;
		this.position = position;
		this.direction = direction;
	}
	
	public double[] getTabVitesse() {
		return tabVitesse;
	}

	public double[] getTabTurn() {
		return tabTurn;
	}

	public double getVmax() {
		return vmax;
	}

	public double getBraquage() {
		return braquage;
	}

	public double getAlpha_c() {
		return alpha_c;
	}

	public double getAlpha_f() {
		return alpha_f;
	}

	public double getBeta_f() {
		return beta_f;
	}

	public double getVitesse() {
		return vitesse;
	}

	public Vecteur getPosition() {
		return position;
	}

	public Vecteur getDirection() {
		return direction;
	}

	public double getMaxTurn() {
		
		for(int i=0; i<tabVitesse.length;i++) {
			if(vmax*tabVitesse[i]> vitesse) {
				return tabTurn[i]; 
				}
			}
		return tabTurn[tabVitesse.length-1];	
		
	}
	
	public void drive(Commande c)throws RuntimeException{
        // VERIFICATIONS !!!
        // 1) Est ce que la rotation et l'acceleration sont entre -1 et 1, sinon, throw new RuntimeException
		
        if ((c.getTurn()>1)||(c.getTurn()<(-1))){
        	throw new RuntimeException("La rotation et/ou l'acceleration ne sont pas dans le bon intervalle");
        }
        if (((c.getAcc()>1))||(c.getAcc()<(-1))) {
        	throw new RuntimeException("La rotation et/ou l'acceleration ne sont pas dans le bon intervalle");
        }
        // 2) Est ce que la rotation demandee est compatible avec la vitesse actuelle, sinon, throw new RuntimeException
        
        if (Math.abs(c.getTurn())>this.getMaxTurn()) {
        	throw new RuntimeException("Rotation demandee incompatible avec la vitesse actuelle");
        }

        // approche normale
        // 1.1) gestion du volant
        direction = direction.rotation(c.getTurn() * braquage); // modif de direction
        // Note: on remarque bien que l'on tourne d'un pourcentage des capacités de la voiture

        // 1.2) garanties, bornes...
        direction = direction.unitVec(); // renormalisation pour éviter les approximations

        // 2.1) gestion des frottements

        vitesse -= alpha_f;
        vitesse -= beta_f*vitesse;

        // 2.2) gestion de l'acceleration/freinage

        vitesse += c.getAcc() * alpha_c;

        vitesse = Math.max(0., vitesse); // pas de vitesse négative (= pas de marche arriere)
        vitesse = Math.min(vmax, vitesse); // pas de dépassement des capacités

        // 3) mise a jour de la position

        position = position.addition(direction.fact(vitesse));
}

	@Override
	public String toString() {
		return "VoitureImpl [tabVitesse=" + Arrays.toString(tabVitesse)
				+ ", tabTurn=" + Arrays.toString(tabTurn) + ", vmax=" + vmax
				+ ", braquage=" + braquage + ", alpha_c=" + alpha_c
				+ ", alpha_f=" + alpha_f + ", beta_f=" + beta_f + ", vitesse="
				+ vitesse + ", position=" + position + ", direction="
				+ direction + "]";
	}
	
	
}


