package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

import javax.imageio.ImageIO;

import mvc.ObserveurImage;
import mvc.UpdateEventListener;
import mvc.UpdateEventSender;
import radar.RadarClassique;
import strategie.StrategyRadar;
import strategie.StrategyListCommande;
import strategie.StrategyToutDroit;
import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.CircuitImpl;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Commande;
import voiture.CommandeSauv;
import voiture.Voiture;
import voiture.VoitureFactory;
import voiture.VoitureImpl;

public class SimulationRadClassique implements UpdateEventSender{
	private File f;
	private ArrayList<Commande> record;
	private ArrayList <UpdateEventListener> listeners;
	private ObserveurImage o;
	
	public SimulationRadClassique(File f) {
		this.f=f;
		this.record=(new ArrayList<Commande>());
		this.listeners=(new ArrayList<UpdateEventListener>());
	}
	public void add(UpdateEventListener listener) {
		listeners.add(listener);
		
	}
	public void update() {
		for(UpdateEventListener listener: listeners){
			listener.manageUpdate();
		}
		
	}

	public void run() throws Exception { 
		Terrain[][]t=TerrainTools.lectureFichier(f);
	 	BufferedImage im = TerrainTools.imageFromCircuit (t);
	 	Circuit circuit=CircuitFactory.buildCircuit(f);
	 	Graphics g = im.getGraphics();
	 	Voiture v= VoitureFactory.buildVoiture(circuit);
	 	
	 	g.setColor(Color.YELLOW);
	 	g.drawLine((int)v.getPosition().x,(int)v.getPosition().y ,(int)v.getPosition().x ,(int)v.getPosition().y);
	 
	 	
	 	double [] thetas= {Math.PI/3,Math.PI/6,0,-Math.PI/6,-Math.PI/3};
	 	//double [] thetas= RadarClassique.generateThetas(70, Math.PI/3);
	 	
	 	StrategyRadar r=new StrategyRadar(new RadarClassique(v,circuit,thetas)); 
        ArrayList<Commande> coms = new ArrayList<Commande>();            
        StrategyListCommande strategy=new StrategyListCommande(coms);
            
        while(circuit.getTerrain(v.getPosition())!=Terrain.EndLine){
        	 strategy.addCommande(r.getCommande());
        	 Commande co= strategy.getCommande();
        	 strategy.increment();
        	 record.add(co);
        	 v.drive(co);
        	 g.drawLine((int)v.getPosition().x,(int)v.getPosition().y ,(int)v.getPosition().x ,(int)v.getPosition().y);
        	 }
		 		 	
            System.out.println("circuit terminé");
		 	try {
		          File outputfile = new File("terrain.png");
		          ImageIO.write(im, "png", outputfile);
		    } catch (IOException e) {
		          System.out.println("Erreur lors de la sauvegarde");
		    }
		 	   
            }


	public ArrayList<Commande> getRecord() {
		return record;
	}

	public static void main(String[] args) throws Exception{
		File f =new	File( "3_safe.trk" ) ;
		SimulationRadClassique simu= new SimulationRadClassique(f);
		simu.run();
		ArrayList<Commande> liste= simu.getRecord();
		CommandeSauv.saveListeCommande(liste, "listeCommande");
		}
	
}

