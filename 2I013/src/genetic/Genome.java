package genetic;


import java.util.ArrayList;
import java.util.Comparator;

public class Genome<Gene> extends ArrayList<Gene>{
	private static final long serialVersionUID = 1L;
	private  double fitness;
	
	public Genome() {
		super();
		fitness = GeneticAlgorithm.MIN_VALUE; 
	}
	
	public void mute(MutationOperator<Gene> op){
		//for(Gene g:this)
	    op.mute(this);
	}
	
	public double getFitness(){
		return fitness;
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer("[");
		for(Gene g:this)
			sb.append(g+" ");
		sb.append("]");
		
		
		return sb.toString();
	}
	
	
	public void eval(FitnessOperator<Gene> fit){
		fitness = fit.fit(this);
	}
	
	public static Comparator<Genome> getComparator(){
		return new Comparator<Genome>(){
			public int compare(Genome g1, Genome g2){
				if(g1.fitness < g2.fitness) return 1;
				else if(g1.fitness > g2.fitness) return -1;
				return 0;
			}
		};
	}
	
}
