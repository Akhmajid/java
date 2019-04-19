package mvc_swing;

import geometrie.Vecteur;

import java.awt.Color;
import java.awt.Graphics;

import radar.Radar;
import voiture.Voiture;

public class RadarObserveur implements ObserveurSWING{
	private Voiture v;
	private Radar r;
	
	public RadarObserveur(Voiture v, Radar r){
		this.v=v;
		this.r=r;
	}
	
	public void print(Graphics g){
		
		
		
		for (int i=0; i<r.thetas().length;i++){
			Vecteur dir = v.getDirection().clonage();
			dir=dir.rotation(r.thetas()[i]);
			dir=dir.multiplication(r.distancesInPixels()[i]);
			dir=dir.addition(v.getPosition());
			
			if (i == r.getBestIndex()){
				g.setColor(new Color(255,234,163));
			}
			else{
				g.setColor(new Color(251,255,163));
			}
			g.drawLine((int)v.getPosition().getX(),(int)v.getPosition().getY(), (int)(dir.getX()),(int)(dir.getY()));
		}
	}
}
