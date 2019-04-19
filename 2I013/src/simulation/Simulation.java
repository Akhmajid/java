package simulation;

import geometrie.Vecteur;

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
import radar.RadarDijkstra;
import strategie.Strategy;
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

public class Simulation implements UpdateEventSender{
	private Circuit c;
	private ArrayList<Commande> record;
	private ArrayList <UpdateEventListener> listeners;
	//private ObserveurImage o;
	private Voiture v;
	private Strategy s;
	private ArrayList<Vecteur> recordPos;
	private ArrayList<Double> recordVit;
	private boolean play;
	private SimulationEtat etat;
	private int iteration;
	private ArrayList<Commande> recordKey;
	
	public Simulation(Circuit c, Voiture v, Strategy s) {
		this.c=c;
		this.v=v;
		this.s=s;
		this.record=(new ArrayList<Commande>());
		this.listeners=new ArrayList<UpdateEventListener>();
		recordPos = new ArrayList<Vecteur>();
		recordVit = new ArrayList<Double>();
		etat=SimulationEtat.EnCours;
		iteration=0;
		this.recordKey=new ArrayList<Commande>();
	}
	public void add(Commande c){
		recordKey.add(c);
	}
	public ArrayList<Commande> getRecordKey(){
		return recordKey;
	}
	
	public void add(UpdateEventListener listener) {
		listeners.add(listener);
		
	}
	public void update() {
		for(UpdateEventListener listener: listeners){
			listener.manageUpdate();
		}
		
	}
	public SimulationEtat updateEtat(){
		Terrain terrain= c.getTerrain((int)v.getPosition().getX(),(int)v.getPosition().getY());
		if(TerrainTools.isRunnable(terrain)==false){
			return SimulationEtat.Echec;
		}
		if((terrain==Terrain.EndLine) && (c.getDirectionArrivee().produitScalaire(v.getDirection())<=0)){
			return SimulationEtat.Echec;
		}
		if((terrain==Terrain.EndLine) && (c.getDirectionArrivee().produitScalaire(v.getDirection())>0)){
			return SimulationEtat.Termine;
		}
		return SimulationEtat.EnCours;
	}
	
	public SimulationEtat getEtat(){
		return etat;
	}
	
	public boolean isPlaying(){
		etat=updateEtat();
		if(etat==SimulationEtat.EnCours){
			return true;
		}
		return false;
	}
	
	public boolean getPlay(){
		return play;
	}

	public void run() throws Exception { 
		play=true;
		
        while((isPlaying()==true)&&(play==true)){
        	 this.PlayOneShot();
        	 
        	}
        	//System.out.println("nb itération: "+ iteration);
           
		 	/*try {
		          File outputfile = new File("terrain.png");
		          ImageIO.write(im, "png", outputfile);
		    } catch (IOException e) {
		          System.out.println("Erreur lors de la sauvegarde");
		    }*/
            
            }
	
	public void runCommande () throws Exception { 
		play=true;
		
        while((isPlaying()==true)){
        	etat = updateEtat();
        	try {
 				Thread.sleep(1);
 			} catch (InterruptedException e) {
 				e.printStackTrace();
 			}
        	
        	}
        	//System.out.println("nb itération: "+ iteration);
	}
	
	
	public void PlayOneShot(){
		Commande co= s.getCommande();
   	 	record.add(co);
   	 	recordPos.add(v.getPosition().clonage());
   	 	recordVit.add(v.getVitesse());
   	 	v.drive(co);
   	 	update();
   	 	iteration++;
   	 	//System.out.println("nb itération: "+ iteration);
	}
	
	public void PlayCommande(Commande c){
		record.add(c);
   	 	recordPos.add(v.getPosition().clonage());
   	 	recordVit.add(v.getVitesse());
   	 	v.drive(c);
   	 	update();
   	 	iteration++;
   	 	//System.out.println("nb itération: "+ iteration);
	}
	

	public ArrayList<Commande> getRecord() {
		return record;
	}
	
	public void setStrategy(Strategy s){
		this.s=s;
	}
	public ArrayList<Vecteur> getRecordPos() {
		return recordPos;
	}
	public ArrayList<Double> getRecordVit() {
		return recordVit;
	}
	public Voiture getVoiture() {
		return v;
	}
	
	
	public void pause(){
		this.play=false;
	}
	public int getIteration(){
		return iteration;
	}
	

	public static void main(String[] args) throws Exception{
		File f =new	File( "1_safe.trk" ) ;
		//Terrain[][]t=TerrainTools.lectureFichier(f);
	 	//BufferedImage im = TerrainTools.imageFromCircuit (t);
	 	Circuit circuit=CircuitFactory.buildCircuit(f);
	 	Voiture voiture= VoitureFactory.buildVoiture(circuit);
	 	
	 	double [] thetas= {Math.PI/3,Math.PI/6,0,-Math.PI/6,-Math.PI/3};
	 	//double [] thetas= RadarClassique.generateThetas(70, Math.PI/3);
	 	
	 	Strategy strategy=new StrategyRadar(new RadarDijkstra(voiture,circuit,thetas)); 
	 	
		Simulation simu= new Simulation(circuit,voiture,strategy);
		simu.run();
		ArrayList<Commande> liste= simu.getRecord();
		CommandeSauv.saveListeCommande(liste, "listeCommande");
		}
	
}

