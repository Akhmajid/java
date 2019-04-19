package voiture;

import java.io.File;

import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.CircuitImpl;
import terrain.Terrain;
import geometrie.Vecteur;
import terrain.TerrainTools;

public class VoitureFactory {
	private static double[] tabVitesse= {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.};
	private static double[] tabTurn= {1.,  0.9, 0.75, 0.6, 0.5, 0.4, 0.3, 0.2, 0.1, 0.05};

	private static double vmax = 0.9;
	private static double alpha_c = 0.005;
	private static double braquage = 0.1;
	private static double alpha_f = 0.0002;
	private static double beta_f = 0.0005;

	private static double vitesse = 0.; // vitesse initiale
	
	private static Vecteur direction= new Vecteur(0,1);
	
	public static Voiture buildVoiture(Circuit c) {
		//Circuit c=CircuitFactory.buildCircuit(fichier);
		Voiture v = new VoitureImpl(tabVitesse, tabTurn, vmax, braquage, alpha_c,alpha_f, beta_f,
				vitesse,c.getPointDepart(), direction );
		return v;
	}
	
	
	public static void main(String[] args) {
		  try{
			  File fichier = new File ("1_safe.trk");
			  Circuit c=CircuitFactory.buildCircuit(fichier);
			  Voiture v = buildVoiture(c);
			  System.out.println(v.toString());}
		  catch (Exception e){
			  System.out.println("Erreur de construction de la voiture");
		  }
	  }
	  
	
	
}

