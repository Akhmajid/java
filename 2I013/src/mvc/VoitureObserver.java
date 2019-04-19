package mvc;

import java.awt.Color;
import java.awt.image.BufferedImage;

import voiture.Voiture;

public class VoitureObserver implements ObserveurImage {
	private Voiture voiture;
    private Color color = Color.yellow; 

    public VoitureObserver(Voiture voiture) {
            super();
            this.voiture = voiture;
    }

    public int getX(){ 
            return (int) voiture.getPosition().getX();
    }
    public int getY(){
            return (int) voiture.getPosition().getY();
    }

    public Color getColor() {
        if(voiture.getVitesse()<0.3) // vitesse faible -> cyan 
           return new Color(0, (int)(voiture.getVitesse()*255*2), (int) (voiture.getVitesse()*255*2));

        if(voiture.getVitesse() == 0.9)
          return new Color((int)(voiture.getVitesse()*255),  (int) (voiture.getVitesse()*255), 0);

        return new Color((int)(voiture.getVitesse()*255), 0, (int) (voiture.getVitesse()*255));
    }

    public void print(BufferedImage im) {
            im.setRGB(getX(), getY(), getColor().getRGB()); 
    }

}
