package voiture;

import geometrie.Vecteur;

public interface Voiture {
	        // pour le pilotage
	        public void drive(Commande c);
	        public double getMaxTurn(); 

	        // pour l'observation
	        public double getVitesse();
	        public Vecteur getPosition();
	        public Vecteur getDirection();
	        public double getBraquage();
	  }


