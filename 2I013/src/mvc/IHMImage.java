package mvc;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import terrain.Circuit;
import terrain.CircuitImpl;


public class IHMImage implements UpdateEventListener {
	private ArrayList<ObserveurImage> liste;
	private BufferedImage im;

	public IHMImage(Circuit c) { 
		liste = new ArrayList<ObserveurImage>();
		im = CircuitImpl.imageFromCircuit(c);
	}

	public void manageUpdate() { 
		for (ObserveurImage o : liste)
			o.print(im);
	}

	public BufferedImage getImage() {
		return im;
	}

	public void add(ObserveurImage obj) {
		liste.add(obj);
	}
}
