package mvc_swing;

import java.awt.Color;
import java.awt.Graphics;

import terrain.Circuit;
import dijkstra.Dijkstra;

public class DijkstraObserveur implements ObserveurSWING {
	
	private Circuit c;

	
	
	public DijkstraObserveur(Circuit c) {
		super();
		this.c = c;
	}

	
	public void print(Graphics g) {
		for (int i = 0; i < c.getHeight(); i++) {
			for (int j = 0; j < c.getWidth(); j++) {
				if (c.getDist(i, j) < (int) Double.POSITIVE_INFINITY) {
					g.setColor(new Color((int) (c.getDist(i, j) % 255.), 0,
							0));
					g.drawLine(i, j, i, j);
				}
			}
		}
	}


}

