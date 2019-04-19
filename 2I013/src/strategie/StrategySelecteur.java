package strategie;

import selecteur.Selecteur;

public interface StrategySelecteur extends Strategy{
	public void add(Strategy strategy, Selecteur selecteur);

}
