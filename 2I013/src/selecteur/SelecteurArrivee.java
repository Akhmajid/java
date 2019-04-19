package selecteur;

import geometrie.Vecteur;
import terrain.Circuit;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.*;

public class SelecteurArrivee implements Selecteur {
	private Voiture voiture;
	private Circuit circuit;
	private boolean state;
	double[]thetas;
	
	public SelecteurArrivee(Voiture voiture, Circuit circuit){
		this.voiture=voiture;
		this.circuit=circuit;
		state=false;

	}
	public boolean isSelected(){
		return state;
	}
	
	
	public void update(){
		//Si l'on voit dŽja la ligne d'arrivŽe pas besoin de refaire le test
		if (state==false){
				
				if (ligneVisible(0)==true){
					state=true;
					System.out.println("Strategie Arrivee selectionne");
				
			}
		}
	
	}
	
	public boolean ligneVisible(double angle){
		
		Vecteur p=voiture.getPosition();
		Vecteur d=voiture.getDirection();
		boolean v=false;

		while(TerrainTools.isRunnable(circuit.getTerrain(p))){
			if ((circuit.getTerrain(p)==Terrain.EndLine)&&(d.produitScalaire(circuit.getDirectionArrivee())>0)){
				return true;
			}
			p=p.addition(d.rotation(angle).multiplication(0.1));
		}
		
		return v;
	}

	
	
}
