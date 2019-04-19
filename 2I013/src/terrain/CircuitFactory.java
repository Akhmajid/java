package terrain;

import java.io.File;

import geometrie.Vecteur;

public class CircuitFactory {

	  public final static Vecteur dirDepart = new Vecteur(0,1);
	  public final static Vecteur dirArrivee = new Vecteur(0,1);
	  
	  
	  public static Circuit buildCircuit(File fichier){
		  
		  try{
			  Terrain[][]ter=TerrainTools.lectureFichier(fichier);
			  Circuit c= new  CircuitImpl(ter,dirDepart,dirArrivee);
			  return c;
		  }
		  catch (Exception e){
			  System.out.println("Erreur de construction du circuit");
		  }
		  return null;
	  }
	  public static void main(String[] args) {
		  File fich = new File ("1_safe.trk");
		  Circuit c= buildCircuit(fich);
		  System.out.println(c.toString());
	  }
}