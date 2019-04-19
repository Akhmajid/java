package pilotClavier;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import voiture.Commande;
import mvc.UpdateEventListener;
import jeu.Jeu;


public class ClavierControl implements KeyListener{
	private ClavierModele modele;
	private Jeu jeu;
	private UpdateEventListener ihm;
	private final ArrayList<Character> pressedKey = new ArrayList<Character>();
	
	public ClavierControl( Jeu jeu, UpdateEventListener ihm){
		super();
		modele= new ClavierModele(jeu);
		this.ihm=ihm;
		this.jeu=jeu;
	}

	
	public void keyPressed(KeyEvent e) {
		
		jeu.interrupt();
		pressedKey.add(e.getKeyChar());
		try {
			modele.play(pressedKey);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		ihm.manageUpdate();
	}

	public void keyReleased(KeyEvent arg0) {
		
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	

}
