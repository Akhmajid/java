package genetic;

import java.util.Random;

public class MutationOperatorImplDouble implements MutationOperator<Double>{
    private double rate;
    private static Random r = new Random();
    private double[] sigma;

    public MutationOperatorImplDouble(double rate,  double[] sigma) {
        super();
        this.rate = rate;
        this.sigma = sigma;
    }
    
    public void mute(Genome<Double> g) {
        for(int i=0; i<g.size(); i++){
            if(r.nextDouble()<rate)
                g.set(i, g.get(i)+r.nextGaussian()*sigma[i]);
        }
    }
}
