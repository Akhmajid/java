package radar;

import java.io.File;

import geometrie.Vecteur;
import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Voiture;
import voiture.VoitureFactory;

public class RadarClassique implements Radar{
	private static final double EPSILON = 0.1;
	private Voiture v;
	private Circuit c;
	private double[] thetas;
	private int bestIndex;
	
	
	public RadarClassique(Voiture v, Circuit c, double[] thetas) {
		super();
		this.v = v;
		this.c = c;
		this.thetas = thetas;
		
	}
	
	
	public double calcScore(double angle){
		
		Vecteur p=v.getPosition();
		Vecteur d=v.getDirection();
		double pas=0;

		while(( c.estDansC(p)) && (c.getTerrain(p)!=Terrain.Herbe)){
			
			pas++;
			p=p.addition(d.rotation(angle).multiplication(EPSILON));
		}
		
		return pas;
	}

	public double[] scores() {
		double[] score= new double[thetas.length];
		for (int i=0; i< score.length; i++){
			score[i]= calcScore(thetas[i]);
		}
		bestIndex=0;
		for (int i=0; i< score.length; i++){
			if(score[bestIndex]<score[i]){
				bestIndex=i;
			}
		}
		return score;
	}

	
	public double[] distancesInPixels() {
		double [] distPix= scores();
		for(int i=0; i<distPix.length;i++){
			distPix[i]=distPix[i]*EPSILON;
		}
		return distPix;
	}

	
	public int getBestIndex() {
		return bestIndex;
	}

	
	public double[] thetas() {
		return thetas;
	}
	
	public static void afficherTableau(double[] tab){
		System.out.print("[");
		for( int i=0;i<tab.length;i++){
			System.out.print(tab[i]+";");
		}
		System.out.println("]");
	}
	
	public Voiture getVoiture(){
		return v;
	}
	
	public Circuit getCircuit(){
		return c;
	}
	
	public double getEpsilon(){
		return EPSILON;
	}
	
	public static double[] generateThetas(int size, double angleVision) {
		double[] thetas = new double[size];
		double delta = (angleVision - Math.PI / 64) / size;
		thetas[0] = 0.;
		for (int i = 1; i < size; i++) {
			thetas[i] = -angleVision + i * delta;
			if (i % 2 == 0) {
				thetas[i] *= -1;
			}
		}
		
		return thetas;
	}
	
	public static void main(String[] args) throws Exception{
		double [] thetas= {Math.PI/3,Math.PI/6,0,-Math.PI/6,-Math.PI/3};
		File f =new	File( "1_safe.trk" ) ;
	 	
	 	
	 	Circuit c = (Circuit) CircuitFactory.buildCircuit(f ); 
	 	//Terrain[][]t=TerrainTools.lectureFichier(f);
	 	Voiture v = VoitureFactory.buildVoiture(c);
	 	
		RadarClassique r= new RadarClassique(v,c,thetas);
		
		System.out.print("thetas=");
		afficherTableau(r.thetas());
		System.out.println("s=");
		afficherTableau(r.scores());
		System.out.println("BestIndex="+r.getBestIndex());
		
		System.out.println("d=");
		afficherTableau(r.distancesInPixels());
	}
	
}


