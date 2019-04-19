package mvc_swing;

import geometrie.Vecteur;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import simulation.Simulation;


public class TrajectoireObserveur implements ObserveurSWING{
	private Simulation simu;

	public TrajectoireObserveur(Simulation simu) {
		super();
		this.simu = simu;
	}
	public Color getColor(int i) {
         if(simu.getRecordVit().get(i)<=0.4) 
            return new Color(185,184,247);

         if(simu.getRecordVit().get(i) >= 0.9)
           return new Color(28,27,146);

         return new Color(115,114,210);
     }
	
	public void print(Graphics g) {
		ArrayList<Vecteur> recordPos = simu.getRecordPos();
		
		for (int i = 0; i < recordPos.size(); i++) {
				g.setColor(this.getColor(i));
			
		          
				int size = 3;
				g.fillOval((int) (recordPos.get(i).getX() - size / 2),
						(int) (recordPos.get(i).getY() - size / 2), size, size);
			}
			
		
	}
}
