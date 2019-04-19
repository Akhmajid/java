package mvc_swing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import terrain.Circuit;
import terrain.CircuitImpl;

public class CircuitObserveur implements ObserveurSWING{
	private BufferedImage im;
	
	public CircuitObserveur(Circuit c){
		this.im=CircuitImpl.imageFromCircuit(c);
	}
	
	public void print (Graphics g){
		g.drawImage(im, 0, 0, null);
	}
}
