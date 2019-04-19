package radar;

import geometrie.Vecteur;
import terrain.Circuit;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Voiture;

public class RadarParabolique extends RadarClassique {
	
	
	public RadarParabolique(Voiture v, Circuit c, double[] thetas) {
		super(v, c, thetas);
		
	}
	
	public boolean isTurnGTight(){
		//boolean virageGauche=true;
		Vecteur position= super.getVoiture().getPosition().clonage();
		Vecteur direction =super.getVoiture().getDirection().clonage();
		
		
		for (int i=0;i<80;i++){
			
			position=position.addition(direction.multiplication(1));
			if (TerrainTools.isRunnable(getCircuit().getTerrain(position))==false){
				return false;
			}
		}
		for(int i=0;i<35;i++){
			direction=direction.rotation(-0.07);
			position=position.addition(direction.multiplication(1));
			if (TerrainTools.isRunnable(getCircuit().getTerrain(position))==false){
				return false;
			}
		}
		for (int i=0;i<50;i++){
			position=position.addition(direction.multiplication(0.5));
			if (TerrainTools.isRunnable(getCircuit().getTerrain(position))==false){
				return false;
			}
		}
		
		return true;
	}
	
	
	public boolean isTurnDTight(){
		//boolean virageGauche=true;
		Vecteur position= super.getVoiture().getPosition().clonage();
		Vecteur direction =super.getVoiture().getDirection().clonage();
		
		
		for (int i=0;i<70;i++){
			
			position=position.addition(direction.multiplication(1));
			if (TerrainTools.isRunnable(getCircuit().getTerrain(position))==false){
				return false;
			}
		}
		for(int i=0;i<35;i++){
			direction=direction.rotation(0.08);
			position=position.addition(direction.multiplication(1));
			if (TerrainTools.isRunnable(getCircuit().getTerrain(position))==false){
				return false;
			}
		}
		for (int i=0;i<40;i++){
			position=position.addition(direction.multiplication(0.5));
			if (TerrainTools.isRunnable(getCircuit().getTerrain(position))==false){
				return false;
			}
		}
		
		return true;
	}
}
