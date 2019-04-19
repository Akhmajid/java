package terrain;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dijkstra.Dijkstra;
import terrain.TerrainTools;
import geometrie.Vecteur;

public class CircuitImpl implements Circuit {
	private Terrain[][]matrice;
	private Vecteur ptDepart;
	private Vecteur sensDepart;
	private Vecteur sensArrivee;
	private Dijkstra dijkstra;
	
	public CircuitImpl(Terrain[][] matrice, Vecteur ptDepart, Vecteur sensDepart, Vecteur sensArrivee) {
		this.matrice = matrice;
		this.ptDepart = ptDepart;
		this.sensDepart = sensDepart;
		this.sensArrivee = sensArrivee;
		dijkstra = new Dijkstra(this);
	}
	
	public CircuitImpl(Terrain[][] matrice, Vecteur sensDepart, Vecteur sensArrivee) {
		this.matrice = matrice;
		this.ptDepart = this.getPointDepart();
		this.sensDepart = sensDepart;
		this.sensArrivee = sensArrivee;
		dijkstra = new Dijkstra(this);
	}
	
	public Terrain[][] getMatrice() {
		return matrice;
	}

	public Terrain getTerrain(int i, int j) {
		return matrice[i][j];
	}
	public boolean estDansC(Vecteur v){
		boolean b=false;
		if ((v.getX()>=0)&&(v.getX()<matrice.length)&&(v.getY()>=0)&&(v.getY()<matrice[0].length)){
			b=true;
		}
		
		return b;
	}
	
	 		
	public Vecteur getPtDepart() {
		return ptDepart;
	}

	public Vecteur getSensDepart() {
		return sensDepart;
	}

	public Vecteur getSensArrivee() {
		return sensArrivee;
	}
 
	public Terrain getTerrain(Vecteur v) {
		return matrice[(int)v.getX()][(int)v.getY()];
	}
	
	public Vecteur getPointDepart()  {
		for(int i=0;i<matrice.length;i++) {
	 		for(int j=0;j<matrice[0].length;j++) {
	 			if(matrice[i][j]==Terrain.StartPoint) {
	 				return new Vecteur(i,j);
	 			}
	}
		}
		return null;
	}

	
	public Vecteur getDirectionDepart() {
		
		return sensDepart;
	}
	

	public Vecteur getDirectionArrivee() {
		
		return sensArrivee;
	}
	
	
	public int getWidth() {
		return matrice[0].length;
	}
	
	public int getHeight() {
		return matrice.length;
	}
	
	public ArrayList<Vecteur> getArrivees() {
		
		ArrayList<Vecteur> listeArriv = new ArrayList<Vecteur>();
		
		for(int i=0; i< matrice.length; i++) {
			for(int j=0; j< matrice[0].length; j++) {
				if (matrice[i][j]== Terrain.EndLine) {
					Vecteur v = new Vecteur(i,j);
					listeArriv.add(v);
				}
			}
		}
		
		return listeArriv;
	}
	
		
	
	public double getDist(int i, int j) {
		
		return dijkstra.getDistance(i,j);
	}

	public String toString() {
		return "CircuitImpl ptDepart=" + ptDepart + ", sensDepart=" + sensDepart
				+ ", sensArrivee=" + sensArrivee + "]";
	}
	public void setTerrain(int i, int j, Terrain t) {
		matrice[j][i] = t;
	}
	
	public void updateDijkstra(){
		dijkstra=new Dijkstra(this);
	}
	
	public static BufferedImage imageFromCircuit (Circuit c){
    	 BufferedImage im = new BufferedImage(c.getHeight(),c.getWidth(), BufferedImage.TYPE_4BYTE_ABGR);
    	 
    	 for (int i=0; i<c.getHeight(); i++){
    		 for(int j=0; j<c.getWidth(); j++){
    			 im.setRGB(i, j, (TerrainTools.terrainToRGB(c.getTerrain(i,j)).getRGB()));
    		 }
    	 }
    	 
    	 return im;
     }
	
	
	
}
