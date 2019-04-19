package simulation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.CircuitImpl;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Voiture;
import voiture.VoitureException;
import voiture.VoitureFactory;
import voiture.Commande;
import voiture.VoitureImpl;
import strategie.StrategyListCommande;


public class SimulationTD {
	private CircuitImpl c;
	private Voiture v;
	private StrategyListCommande str;
	
	public SimulationTD(Voiture v, CircuitImpl c, StrategyListCommande str){
		this.v=v;
		this.c=c;
		this.str=str;
		}
	
	/*public void playOneShot() throws VoitureException{
		Commande c = strategie.getCommande(); 
		voiture.drive(c);
		
		state = updateState();
	}*/
	
	public void play(){
		BufferedImage im = TerrainTools.imageFromCircuit(c.getMatrice());
		Graphics g = im.getGraphics();
	 	g.setColor(Color.YELLOW);
	 	g.drawLine((int)v.getPosition().x,(int)v.getPosition().y ,(int)v.getPosition().x ,(int)v.getPosition().y);
	 
		for(int i=0; i< str.getListe().size();i++) {	
		 	Commande co= str.getCommande();
			str.increment();
			//System.out.println(co.getRotation() +"co");
		 	v.drive(co);
		 	
		 	g.drawLine((int)v.getPosition().getX(),(int)v.getPosition().getY() ,(int)v.getPosition().getX() ,(int)v.getPosition().getY());
		 	}
		
		try {
	          File outputfile = new File("terrain.png");
	          ImageIO.write(im, "png", outputfile);
	       } catch (IOException e) {
	          System.out.println("Erreur lors de la sauvegarde");
	        }
	 	    
	 	}
	
	
	public static void main(String[] args) throws Exception{
		File f =new	File( "1_safe.trk" ) ;
	 	
	 	
	 	Circuit c =  CircuitFactory.buildCircuit(f ); 
	 	Terrain[][]t=TerrainTools.lectureFichier(f);
	 	Voiture v = VoitureFactory.buildVoiture(c);
	 	
	 	
	 	BufferedImage im = TerrainTools.imageFromCircuit(t);
		Graphics g = im.getGraphics();
	 	g.setColor(Color.YELLOW);
	 	g.drawLine((int)v.getPosition().getX(),(int)v.getPosition().getY() ,(int)v.getPosition().getX() ,(int)v.getPosition().getY());
	 	
	 	ArrayList<Commande> coms = new ArrayList<Commande>(); 
	 	
	 	for(int i=0; i<50; i++) coms.add(new Commande(1,0));
	 	for(int i=0; i<50; i++) coms.add(new Commande(1,0.1)); 
	 	for(int i=0; i<50; i++) coms.add(new Commande(1,0));
	 	for(int i=0; i<50; i++) coms.add(new Commande(1,-0.1));
	 	
	 	StrategyListCommande str = new StrategyListCommande(coms); 
	 	
	
	 	/*for(Commande co:coms){
            v.drive(co);
            //System.out.println("position : "+ v.getPosition());
            im.setRGB((int) v.getPosition().getY(), (int) v.getPosition().getX(), Color.orange.getRGB());
	 	}

	 	im.setRGB(0,0, Color.orange.getRGB());
	 	ImageIO.write(im, "png", new File("test.png"));*/

	 	SimulationTD simu = new SimulationTD(v, (CircuitImpl)c , str );
	 	simu.play();
	 
	 	    
	 	}
	 	
	}



