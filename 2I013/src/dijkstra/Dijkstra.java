package dijkstra;

import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import mvc.UpdateEventListener;
import dijkstra.ComparatorDijk;
import geometrie.Vecteur;
import terrain.Circuit;
import terrain.TerrainTools;

public class Dijkstra {
	private Circuit c;
	private double [][]dist;
	private Vecteur current;
	private PriorityBlockingQueue<Vecteur> queue;
	private ArrayList<UpdateEventListener> liste;
	
	public Dijkstra(Circuit c){
		this.c=c;
		dist=new double[c.getHeight()][c.getWidth()];
		for (int i=0;i<dist.length;i++)
			for(int j=0;j<dist[i].length;j++)
				dist[i][j]=Double.POSITIVE_INFINITY;
	
	
		queue = new PriorityBlockingQueue<Vecteur>( 1000, new ComparatorDijk(dist));
		liste = new ArrayList<UpdateEventListener>();
		for(Vecteur v : c.getArrivees()){
			dist[(int) v.getX()][(int) v.getY()] = 0;
			queue.add(v);
		}
		compute();
	
	}
	
	
	public void compute(){
		while (queue.isEmpty()==false){
			try {
				current= queue.take();
				int x=(int)current.getX();
				int y=(int)current.getY();
				
				for (int i=-1; i<=1;i++){
					for (int j=-1;j<=1;j++){
						Vecteur v = new Vecteur(x+i,y+j);
						if ((i==0)&&(j==0)){
							continue;
						}
						if (TerrainTools.isRunnable(c.getTerrain(v))==false){
							continue;
						}
						if ((x+i>=c.getHeight()) || ( y+j>=c.getWidth()) || (x+i < 0) || (y+j < 0)){
							continue;
						}
						// gerer la ligne d'arrivee:
						if ((dist[x][y]==0) && (c.getDirectionArrivee().produitScalaire(new Vecteur(i,j))>0)){
							continue;
						}
						int p=poids(i,j);
						if (dist[x+i][y+j]>dist[x][y] +p){
							queue.remove(v);
							dist[x+i][y+j]=dist[x][y]+p;
							queue.add(v);
						}
						
					}
				}
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}	
		}
	}
	
	public int poids(int i, int j) {
		if (((i==-1) && (j == -1))||((i == 1)&&(j == -1))||((i == -1)&&(j == 1))||((i == 1) && (j == 1))){
			return 14;
		}
		return 10;
	}
	
	public Vecteur getCurrent() {
		return current;
	}

	public double getDistance(int x, int y) {
		return dist[x][y];
	}

	public double[][] getDist() {
		return dist;
	}
	
	public void update(){
		for (UpdateEventListener listener : liste) {
			listener.manageUpdate();
		}
	}
	public void add(UpdateEventListener listener) {
		liste.add(listener);
	}
}
