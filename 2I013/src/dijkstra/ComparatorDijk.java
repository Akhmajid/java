package dijkstra;

import java.util.Comparator;

import geometrie.Vecteur;

public class ComparatorDijk implements Comparator<Vecteur>{
	private double [][]distance;
	
	public ComparatorDijk(double[][]distance){
		this.distance=distance;
	}
	
	
	public int compare(Vecteur v1,Vecteur v2){
		int x1= (int) v1.getX();
		int x2= (int) v2.getX();
		int y1= (int) v1.getY();
		int y2= (int) v2.getY();
		
		if (distance[x1][y1]>distance[x2][y2]){
			return 1;
		}
		else if (distance[x1][y1]==distance[x2][y2]){
			return 0;
		}
		
		return -1;
	}
	
}
