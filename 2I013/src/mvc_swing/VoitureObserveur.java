package mvc_swing;

import geometrie.Vecteur;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import voiture.Voiture;
import voiture.VoitureIcone;

public class VoitureObserveur implements ObserveurSWING{ 
	private Voiture voiture ;
	private Color color = Color.yellow ;
	
	public VoitureObserveur(Voiture voiture) { 
		this.voiture = voiture ;
		}
	
	public int getX(){ 
		return (int) voiture.getPosition().getX();
	}
	public int getY(){ 
		return (int) voiture.getPosition().getY();
	}
	public Voiture getVoiture(){
		return voiture;
	}
	
	public void print(Graphics g){ 
		g.setColor(color);
		BufferedImage car = (BufferedImage)VoitureIcone.voitureIcone("voiture1.png");
		double angle= (Math.PI/2)-voiture.getDirection().angle(new Vecteur(0,1));
		// construction d'une transformation
		AffineTransform transform = new AffineTransform();
		
		transform.rotate(angle, (car.getWidth() / 2), (car.getHeight() / 2));
		// transformation => transformation d'image
		AffineTransformOp op = new AffineTransformOp(transform,AffineTransformOp.TYPE_BICUBIC);
		// image finale
		car = op.filter(car, null);
		g.drawImage(car, getX() - 25, getY() - 25, null);
		
		

	}
}
