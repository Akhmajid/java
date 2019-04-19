package mvc_swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import radar.Radar;
import radar.RadarClassique;
import radar.RadarDijkstra;
import strategie.StrategyRadar;
import simulation.Simulation;
import simulation.SimulationRadClassique;
import strategie.Strategy;
import strategie.StrategyListCommande;
import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureFactory;

public class Main {

	public static void main(String[] args) {
		JFrame fen = new JFrame(); 
		IHMSwing ihm = new IHMSwing();
		File f =new	File( "1_safe.trk" ) ;
	 	
		
	 	Circuit c =  CircuitFactory.buildCircuit(f ); 
	 	//Terrain[][] t;
		try {
			//t = TerrainTools.lectureFichier(f);
			//BufferedImage im = TerrainTools.imageFromCircuit (t);
			Voiture v = VoitureFactory.buildVoiture(c);
			
			double [] thetas= RadarClassique.generateThetas(50, Math.PI/3);
			//double [] thetas= {Math.PI/3,Math.PI/6,0,-Math.PI/6,-Math.PI/3};
			RadarClassique r =new RadarDijkstra(v,c,thetas);
			Strategy s= new StrategyRadar(r); 
		 	
			Simulation simu= new Simulation(c,v,s);
			
			ihm.add(new CircuitObserveur(c));
			//ihm.add(new DijkstraObserveur(c));
			ihm.add(new TrajectoireObserveur(simu));
			ihm.add(new RadarObserveur(v,r));
			
			ihm.add(new VoitureObserveur(v));
			
			simu.add(ihm); // possibilite de mettre plusieurs obs
			
			//JPanel menu =new JPanel();
			//"menu.setLayout(new BorderLayout());
			
		 
			ihm.setPreferredSize(new Dimension(768, 1024));
			//fen.setSize(1024, 1024);
			//fen.setBackground(Color.black);
			fen.setContentPane(ihm);
			fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fen.setVisible (true );
			fen.pack ();
			simu.run();
		
		
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	
		
	 	
	 	

	}

}
