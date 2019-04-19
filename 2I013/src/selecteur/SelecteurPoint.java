package selecteur;

import geometrie.Vecteur;
import strategie.StrategyPoint;
import terrain.Circuit;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Voiture;

public class SelecteurPoint implements Selecteur{
	private Voiture voiture;
	private Circuit circuit;
	private StrategyPoint strategy;
	private boolean state;
	
	public SelecteurPoint(Voiture voiture, Circuit circuit, StrategyPoint strategy){
		this.voiture=voiture;
		this.circuit=circuit;
		this.strategy=strategy;
		state=false;
	}
	
	public boolean isSelected() {
		return state;
	}
	
	public void update(){
		if (strategy.getIndex()>strategy.getListePoints().size()-1){
			state= false;
			return;
		}
		
		Vecteur position= voiture.getPosition().clonage();
		Vecteur direction= new Vecteur(strategy.getListePoints().get(strategy.getIndex()).getX()-position.getX(),strategy.getListePoints().get(strategy.getIndex()).getY()-position.getY());
		
		while(position.egalite(strategy.getListePoints().get(strategy.getIndex()))==false){
			if (TerrainTools.isRunnable(circuit.getTerrain(position))==false){
				state= false;
				return;
			}
			position.addition(direction.multiplication(0.01));
		}
		state= true;
	}
}
