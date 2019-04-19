package mvc_swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import simulation.Simulation;
import voiture.Voiture;

public class SimulationObserveur implements ObserveurSWING{
	private Simulation simulation;
	private Voiture voiture;

	public SimulationObserveur(Simulation s, Voiture v) {
		this.simulation = s;
		this.voiture = v;
	}

	
	public void print(Graphics g) {
		g.setColor(new Color(225,200,244));
		g.fillRect(15, 10, 220, 90);
		g.setColor(new Color(125,10,211));
		g.drawRect(15, 10, 220, 90);
		
		g.setColor(new Color(74,45,157));
		Font font10 = new Font("SansSerif", Font.BOLD, 15);
		g.setFont(font10);
		
		
		g.drawString(String.format("Vitesse: %.2f ", voiture.getVitesse() ),30, 30);
		g.drawString(String.format("Direction: (%6.2f, %6.2f) ",  
                voiture.getDirection().getX(), voiture.getDirection().getY() ) ,
                30, 50);
		g.drawString(String.format("Itérations: %d ", simulation.getIteration() ) ,
                30, 70);
		g.drawString(String.format("Etat: %s",simulation.getEtat() ) ,
                30, 90);
	}

	

}
