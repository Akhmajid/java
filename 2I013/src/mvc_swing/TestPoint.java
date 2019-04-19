package mvc_swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import mouseListener.MousePositionListener;
import radar.RadarClassique;
import radar.RadarDijkstra;
import simulation.Simulation;
import strategie.Strategy;
import strategie.StrategyPoint;
import strategie.StrategyRadar;
import terrain.Circuit;
import terrain.CircuitFactory;
import voiture.Voiture;
import voiture.VoitureFactory;
import geometrie.Vecteur;

public class TestPoint {

	public static void main(String[] args) {
		//double [] thetas= RadarClassique.generateThetas(50, Math.PI/3);
		double [] thetas= {Math.PI/3,Math.PI/6,0,-Math.PI/6,-Math.PI/3};
		//Commande[] allCom = CommandeFactory.generateAllComm(thetas, 1);
		JFrame fen = new JFrame(); 
		IHMSwing ihm = new IHMSwing();
		File f =new	File( "1_safe.trk" ) ;
	 	
		
	 	Circuit c =  CircuitFactory.buildCircuit(f ); 
	 	
	 	ArrayList<Vecteur> listePoints = null;
		try {
			Voiture v = VoitureFactory.buildVoiture(c);
			MousePositionListener m= new MousePositionListener(listePoints, ihm);
			
			
			RadarClassique r =new RadarDijkstra(v,c,thetas);
			//Strategy s= new StrategyRadar(r); 
		 	StrategyPoint s = new StrategyPoint(v, listePoints);
			Simulation simu= new Simulation(c,v,s);
			
			ihm.add(new CircuitObserveur(c));
			//ihm.add(new DijkstraObserveur(c));
			ihm.add(new TrajectoireObserveur(simu));
			//ihm.add(new RadarObserveur(v,r));
			ihm.add(new PointsObserveur(listePoints));
			ihm.add(new VoitureObserveur(v));
			
			simu.add(ihm); // possibilite de mettre plusieurs obs
			
			JPanel menu =new JPanel();
			menu.setLayout(new BorderLayout());
			
			
			
			
			
			ihm.setPreferredSize(new Dimension(768, 1024));
			//fen.setSize(1024, 1024);
			//fen.setBackground(Color.black);
			fen.setContentPane(ihm);
			fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			fen.setVisible (true );
			fen.pack ();
			simu.run();
		
		
		
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	 	
		
		
		
		

	}


}
