package mvc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import mvc_swing.VoitureObserveur;
import radar.RadarDijkstra;
import simulation.Simulation;
import strategie.Strategy;
import strategie.StrategyRadar;
import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.CircuitImpl;
import voiture.Voiture;
import voiture.VoitureFactory;

public class Controleur implements UpdateEventListener {
	private ArrayList<ObserveurImage> liste; 
	private BufferedImage im;
	
	
	public Controleur (Circuit c){
		liste = new ArrayList<ObserveurImage>();
		im=CircuitImpl.imageFromCircuit(c);
	}
	public void manageUpdate() {
		for(ObserveurImage o: liste ){
			o.print(im);
		}
	

		
	}
	
	public void add(ObserveurImage obs){
		liste.add(obs);
	}
	
	
	public static void main(String[] args) throws Exception{
		File f =new	File( "1_safe.trk" ) ;
		Circuit c = CircuitFactory.buildCircuit(f);
		Voiture v = VoitureFactory.buildVoiture(c);
		
		Controleur ihm = new Controleur ( c );
		
		double [] thetas= {Math.PI/3,Math.PI/6,0,-Math.PI/6,-Math.PI/3};
		Strategy s=new StrategyRadar(new RadarDijkstra(v,c,thetas)); 
	 	
		Simulation simu= new Simulation(c,v,s);
		ihm.add(new VoitureObserver(v)); 
		simu.add(ihm );
		simu.run();
	}
}
