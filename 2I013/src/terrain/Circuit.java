package terrain;

import java.io.File;
import java.util.ArrayList;

import geometrie.Vecteur;

public interface Circuit {
	
	 public Terrain getTerrain(int i, int j);
     public Terrain getTerrain(Vecteur v);

     public Vecteur getPointDepart();
     public Vecteur getDirectionDepart();
     public Vecteur getDirectionArrivee();
     public int getWidth();
     public int getHeight();
     public ArrayList<Vecteur> getArrivees();
     public boolean estDansC(Vecteur v);
     public double getDist(int i, int j);
	 public void setTerrain(int i, int j, Terrain t);
	 public void updateDijkstra(); 
    
     
    
}
