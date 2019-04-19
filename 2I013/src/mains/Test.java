package mains;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;

import controleur.Controleur;
import simulation.Simulation;
import strategie.Strategy;
import terrain.Circuit;
import terrain.CircuitFactory;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import vue.InterfaceGraphique;


public class Test {

	public static void main(String[] args) {
		JFrame windows = new JFrame("Course de voiture");
		Controleur controleur = new Controleur();
		InterfaceGraphique ihm = new InterfaceGraphique(controleur);
		windows.setLayout(new BorderLayout());
		windows.setContentPane(ihm);
		
		//Permission de redimentionner l'image par l'utilisateur:
		//windows.setResizable(false);
		
		windows.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windows.pack();
		windows.setLocationRelativeTo(null);
		windows.setVisible(true);
	}
}