package controleur;
import java.awt.Dimension;
import  java.awt.event.*;
import java.io.File;

import  javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pilotClavier.ClavierControl;
import mouseListener.MousePositionListener;
import mvc.UpdateEventListener;
import mvc_swing.CircuitObserveur;
import mvc_swing.PointsObserveur;
import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.Obstacle;
import voiture.VoitureException;
import vue.InterfaceGraphique;
import jeu.Jeu;



public class Controleur implements ActionListener,ItemListener, UpdateEventListener, ChangeListener {
	private Jeu jeu;
	private InterfaceGraphique panel;
	
	public Controleur (){
		jeu = new Jeu();
	}
	
	public void actionPerformed(ActionEvent a) {
		String s=((JButton)a.getSource()).getText();
		if (s.compareTo("  Démarrer")==0){
			this.getJeu().interrupt();
			this.getPanel().launch();
				
		}
		if (s.compareTo("  Play/Pause")==0){
			if(this.getJeu().getSimulation()==null){
				System.out.println("Erreur");
				return;
			}
			if (this.getJeu().getSimulation().isPlaying()){
				if (this.getJeu().getSimulation().getPlay()==false){
					this.getJeu().play();
				}
				else{
					this.getJeu().interrupt();
				}
			}
		}
		if(s.compareTo("  Arrêter")==0){
			this.getJeu().interrupt();
			
		}
		if (s.compareTo("Ajouter Point")==0){
			MouseListener[] listeners= this.getPanel().getIhm().getMouseListeners();
			for(MouseListener ml : listeners){
				this.getPanel().getIhm().removeMouseListener(ml);
			}
			this.getPanel().getIhm().addMouseListener(new MousePositionListener(this.getPanel().getListePoints(), this.getPanel().getIhm()));
		}
		if (s.compareTo("  Supprimer Points")==0){
			this.getPanel().clearListePoints();
			this.getPanel().getIhm().manageUpdate();
			}
		
		if (s.compareTo("  Piloter")==0){
			this.getPanel().getIhm().requestFocus();
			this.getPanel().getIhm().addKeyListener(new ClavierControl(this.getJeu(),this.getPanel().getIhm()));
			Thread t = new Thread() {
				public void run() {
					try {
						
						jeu.getSimulation().runCommande();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			t.start();
		}
		if (s.compareTo("  Ajouter Obstacle")==0){
			MouseListener[] listeners= this.getPanel().getIhm().getMouseListeners();
			for(MouseListener ml : listeners){
				this.getPanel().getIhm().removeMouseListener(ml);
			}
			this.getPanel().getIhm().addMouseListener(new Obstacle(this.getPanel().getListeObstacles(), this.getJeu(),this.getPanel().getIhm()));
		}
		if (s.compareTo("  Supprimer Obstacles")==0){
			this.getPanel().clearListeObstacles();
			this.getPanel().getIhm().manageUpdate();
			}
		
	}

	public void itemStateChanged(final ItemEvent e) {
		if (e.getStateChange()== ItemEvent.SELECTED){
			jeu.interrupt();
			
			
			Thread t =new Thread(){
				public void run(){
					String nomFichier = e.getItem().toString();
					File f =new	File( nomFichier );
				 	Circuit circuit =  CircuitFactory.buildCircuit(f );
				 	jeu.setCircuit(circuit);
				 	
				 	panel.clearListePoints();
				 	
				 	//panel.getIhm().setSize(new Dimension(circuit.getWidth(),circuit.getHeight()));
				 	panel.getIhm().add(new CircuitObserveur(jeu.getCircuit()));
				 	//panel.getIhm().add(new PointsObserveur
				 	
				 	panel.getIhm().manageUpdate();
				 	//panel.getBoutonDemarrer().setEnabled(true);
				 	//panel.getBoutonPlayPause().setEnabled(true);
				 	//panel.getBoutonArret().setEnabled(true);
				 	//panel.getBoutonPoints().setEnabled(true);
				
				}
			};
			t.start();
			return;
		}
		
	}
	
	public void manageUpdate() {
		jeu.update();
	}
	
	public void setPanel(InterfaceGraphique panel){
		this.panel=panel;
	}
	public InterfaceGraphique getPanel(){
		return panel;
	}
	public void setJeu(Jeu jeu){
		this.jeu=jeu;
	}
	public Jeu getJeu(){
		return jeu;
	}

	
	public void stateChanged(ChangeEvent e) {
		
		
	}

	
	

}
