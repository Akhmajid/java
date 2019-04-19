package genetic;

public interface FitnessOperator<Gene> {
	double fit(Genome<Gene>g);

}
