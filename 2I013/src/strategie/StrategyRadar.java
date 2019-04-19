package strategie;

import geometrie.Vecteur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import radar.Radar;
import radar.RadarClassique;
import simulation.Simulation;
import strategie.Strategy;
import strategie.StrategyListCommande;
import strategie.StrategyToutDroit;
import terrain.Circuit;
import terrain.CircuitFactory;
import terrain.CircuitImpl;
import terrain.Terrain;
import terrain.TerrainTools;
import voiture.Commande;
import voiture.Voiture;
import voiture.VoitureFactory;

public class StrategyRadar implements Strategy {
	private RadarClassique r;
	
	
	public StrategyRadar(RadarClassique r) {
		super();
		this.r = r;
		
		
	}
	
	public Commande getCommande() {
		r.scores();
		double acc=1;
		int bestInd=r.getBestIndex();
		
		double direction=r.thetas()[bestInd];
		double rotation=direction/(Math.PI/2);
		
		if (Math.abs(rotation)>r.getVoiture().getMaxTurn()) {
			rotation=Math.signum(rotation)*r.getVoiture().getMaxTurn();
			
			acc= -1;
			}
		else {
			
			if (r.getVoiture().getVitesse()<0.3){
				acc=1;
			}
			else if (r.getVoiture().getVitesse()<0.6){
				acc=0.8;
			}
			else if (r.getVoiture().getVitesse()<0.8){
				acc=0.5;
			}
			else if (r.getVoiture().getVitesse()<1){
				acc=0.1;
			}
			
		}
		
		return new Commande(acc,rotation);		}
	
	public Radar getRadar(){
		return r;
	}


	public void init(Voiture v, Circuit c) {
		
		
	}

	public Voiture getVoiture() {
		return r.getVoiture();
		
	}
		
		
}
	
