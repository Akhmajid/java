package strategie;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import terrain.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyPilot implements Strategy, KeyListener{
	private Voiture voiture;
	
	private boolean up = false; 
	private boolean down = false; 
	private boolean right = false; 
	private boolean left = false; 
	
	public void init(Voiture v, Circuit c) {
	}
	public StrategyPilot(Voiture voiture){
		this.voiture=voiture;
	}

	
	public Commande getCommande() {
		double acc=0;
		double angle=0;
		if(up) {
			if(!down) {
				acc = 0.2; 
			}
		} else {
			if(down) {
				acc = -0.7; 
			}
		}
		
		if(right) {
			if(!left) {
				angle = 0.3;
			}
		} else {
			if(left) {
				angle = -0.3; 
			}
		}
		
		if(Math.abs(angle) > voiture.getMaxTurn()) {
			angle = voiture.getMaxTurn() * Math.signum(angle);
		}
		
		Commande c = new Commande(acc, angle); 
		return c;
		
	}

	
	public void keyPressed(KeyEvent e) {
		if (e != null) {
			int k = e.getKeyCode(); 
			if (k == KeyEvent.VK_UP) {
				up = true; 
			} else if (k == KeyEvent.VK_DOWN) {
				down = true; 
			} else if (k == KeyEvent.VK_RIGHT) {
				right = true; 
			} else if (k == KeyEvent.VK_LEFT) {
				left = true; 
			}
		}
	}

	
	public void keyReleased(KeyEvent e) {
		if (e != null) {
			int k = e.getKeyCode(); 
			if (k == KeyEvent.VK_UP) {
				up = false; 
			} else if (k == KeyEvent.VK_DOWN) {
				down = false; 
			} else if (k == KeyEvent.VK_RIGHT) {
				right = false; 
			} else if (k == KeyEvent.VK_LEFT) {
				left = false; 
			}
		}
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		
		
	}

}
