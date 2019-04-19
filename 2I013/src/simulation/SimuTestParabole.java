package simulation;

import geometrie.Vecteur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureFactory;

public class SimuTestParabole {

	public static void main(String[] args) throws Exception {
		File f =new	File( "1_safe.trk" ) ;
	 	
	 	
	 	Circuit c =  CircuitFactory.buildCircuit(f ); 
	 	Terrain[][]t=TerrainTools.lectureFichier(f);
	 	Voiture v = VoitureFactory.buildVoiture(c);
	 	
	 	
	 	BufferedImage im = TerrainTools.imageFromCircuit(t);
		Graphics g = im.getGraphics();
	 	g.setColor(Color.YELLOW);
	 	g.drawLine((int)v.getPosition().getX(),(int)v.getPosition().getY() ,(int)v.getPosition().getX() ,(int)v.getPosition().getY());
		
		
		
	 	Vecteur position =v.getPosition();
	 	Vecteur direction=v.getDirection();
		for (int i=0;i<80;i++){
			
			position=position.addition(direction.multiplication(1));
			g.drawLine((int)position.getX(),(int)position.getY() ,(int)position.getX() ,(int)position.getY());
		 	
		}
		for(int i=0;i<35;i++){
			direction=direction.rotation(0.07);
			position=position.addition(direction.multiplication(1));
			g.drawLine((int)position.getX(),(int)position.getY() ,(int)position.getX() ,(int)position.getY());
		 	
		}
		for (int i=0;i<50;i++){
			position=position.addition(direction.multiplication(0.5));
			g.drawLine((int)position.getX(),(int)position.getY() ,(int)position.getX() ,(int)position.getY());
		 	
		}
	
	try {
          File outputfile = new File("Ess.png");
          ImageIO.write(im, "png", outputfile);
       } catch (IOException e) {
          System.out.println("Erreur lors de la sauvegarde");
        }
	}

}
