package pilotClavier;

import jeu.Jeu;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import voiture.Commande;

public class ClavierModele {
	private Jeu jeu;
	
	
	public ClavierModele(Jeu jeu){
		this.jeu=jeu;
	}
	
	public boolean isValide(char c){
		if (c == 'z' || c == 's' || c == 'q' || c == 'd'){
			return true;
		}
		else
			return false;
	}
	
	public boolean isValide(int i){
		if (i == KeyEvent.VK_UP || i == KeyEvent.VK_LEFT || i == KeyEvent.VK_RIGHT || i == KeyEvent.VK_DOWN){
			return true;
		}
		else
			return false;
	}
	
	public double getAcc(char c){
		if (c == 'z') {
			return 1;
		}
		if (c == 's') {
			return -1;
		}
		return 0;
	}
	
	public double getRot(char c){
		if (c == 'q') {
			return -0.7;
		}
		if (c == 'd') {
			return 0.7;
		}
		return 0;
	}

	public void play(ArrayList<Character> liste) {
		double acceleration=0;
		double rotation=0;
		for (Character c: liste){
			acceleration=0;
			rotation=0;
			if (this.isValide(c)==false){
				continue;
			}
			acceleration+=getAcc(c);
			rotation+=getRot(c);
			if (rotation >1){
				rotation=1;
			}
			if (rotation <-1){
				rotation=-1;
			}
			if (acceleration >1){
				acceleration=1;
			}
			if (acceleration <-1){
				acceleration=-1;
			}
				
			}
		
		Commande co = new Commande (acceleration, rotation);
		if (Math.abs(co.getTurn())>=jeu.getVoiture().getMaxTurn()){
			co.setTurn(Math.signum(co.getTurn())*jeu.getVoiture().getMaxTurn());
			co.setAcc(-1);
		}
		jeu.getSimulation().PlayCommande(co);
			
		
	}
	
	
}
