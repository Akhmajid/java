package genetic;

public interface MutationOperator<Gene> {
	void mute (Genome<Gene> g);
}
