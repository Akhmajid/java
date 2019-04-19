package genetic;

import java.util.Random;

public class CrossingOperatorImpl<T> implements CrossingOperator<T> {
	private Random r = new Random();
	
	public Genome<T> cross(Genome<T> gen1, Genome<T> gen2) {
		Genome<T> genome = new Genome<T>();
		
		for(int i=0; i<gen1.size(); i++){
			if(r.nextDouble()>0.5)
				genome.add(gen1.get(i));
			else
				genome.add(gen2.get(i));
		}
		
		return genome;
	}
	
}