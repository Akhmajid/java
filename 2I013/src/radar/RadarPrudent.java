package radar;

import terrain.Circuit;
import voiture.Voiture;

public class RadarPrudent extends RadarClassique {
	private final double EPSILON = 0.1;
	//public double[] thetas={-Math.PI/2,0,Math.PI/2};
	
	public RadarPrudent(Voiture voiture, Circuit circuit, double[] thetas) {
		super(voiture, circuit, thetas);
	
	}
	
	public boolean safeAutour(double d) {
		return (d >= 50*(1/EPSILON));
	}
	
	public boolean safeDevant(double d) {
		return (d >= 200*(1/EPSILON));
	}
	
	public boolean dangerDevant(double d) {
		return (d < 50*(1/EPSILON));
	}

}
