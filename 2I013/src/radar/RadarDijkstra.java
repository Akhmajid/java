package radar;

import geometrie.Vecteur;
import terrain.Circuit;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Voiture;
import dijkstra.Dijkstra;

public class RadarDijkstra extends RadarClassique {
	private Dijkstra dijk;
	
	
	public RadarDijkstra(Voiture v, Circuit c, double[] thetas) {
		super(v, c, thetas);
		dijk=new Dijkstra(c);
		
	}
	
	
	
	public double calcScore(double angle){
		
		Vecteur direction= getVoiture().getDirection().clonage();
		Vecteur position= getVoiture().getPosition().clonage();
		direction= direction.rotation(angle);
		double score = Double.POSITIVE_INFINITY;
		int pas=0;
		
		while(TerrainTools.isRunnable(getCircuit().getTerrain(position))){
			if ((getCircuit().getTerrain(position)==Terrain.EndLine)&&(direction.produitScalaire(getCircuit().getDirectionArrivee())<=0)){
				
				return -Double.POSITIVE_INFINITY;
			}
			
			int s= (int)dijk.getDistance((int)position.getX(), (int)position.getY());
			if (s==0){
				s=Integer.MAX_VALUE+pas;
			}
			if (s<score){
				score=s;
			}
			position=position.addition(direction.multiplication(getEpsilon()));
			pas++;
			
		}
	return -1*score;
	}
	
	public double[] distancesInPixels() {
		double [] distPix= scores();
		for(int i=0; i<distPix.length;i++){
			distPix[i]=distPix[i]*getEpsilon()*-1;
			if (distPix[i]<0){
				distPix[i]=0;
			}
		}
		return distPix;
	}

	
}
