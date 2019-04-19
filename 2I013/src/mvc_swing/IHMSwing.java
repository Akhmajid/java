package mvc_swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import mvc.UpdateEventListener;

public class IHMSwing extends JPanel implements UpdateEventListener{
	private ArrayList <ObserveurSWING> list;
	//private BufferedImage im;
	
	// constructeur
	public IHMSwing(){
		super();
		this.list=new ArrayList<ObserveurSWING>();
		//this.im=im;
		//setLayout(new BorderLayout());
		//setPreferredSize(new Dimension(1024, 768));
		//setBackground(Color.gray);
		//setVisible(true);
	}
	public void add(ObserveurSWING obs){
		list.add(obs);
	}
	public void init(){
		list=new ArrayList<ObserveurSWING>();
	}
	
	// gestion des evenement standard
	public void manageUpdate () {
		//repaint (); 
		try {      // temporisation (sinon, on ne voit rien)
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		// fonction specifique des composants SWING 
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				repaint();
			}
		});
		
		
		}
	
	// surcharge de la methode d’affichage du composant
	public void paint(Graphics g) { 
		super.paint(g);
	//La qualité du rendu des dessins peut être améliorée en invoquant la méthode setRenderingHint() du contexte graphique qui permet de paramétrer differentes options comme l'anti-crenelage, le rendu des couleurs, le dithering
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		for(ObserveurSWING o: list){
			if(o!= null){
				o.print(g);
			}
		}
		
		 // code à ajouter pour dessiner ce que l’on veut 
	}
	public void reset(){
		list.clear();
	}
}