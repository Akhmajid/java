package mouseListener;

import geometrie.Vecteur;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import mvc.UpdateEventListener;

public class MousePositionListener extends MouseAdapter {
	private ArrayList<Vecteur> listePoints;
	private UpdateEventListener ihm;
	
	public MousePositionListener(ArrayList<Vecteur> listePoints,UpdateEventListener ihm) {
		super();
		this.listePoints = listePoints;
		this.ihm = ihm;
	}

	public void mouseReleased(MouseEvent e) {
		listePoints.add(new Vecteur(e.getY(), e.getX()));
		ihm.manageUpdate();
	}

	public ArrayList<Vecteur> getListePoints() {
		return listePoints;
	}
	
}
