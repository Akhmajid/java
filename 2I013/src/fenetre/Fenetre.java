package fenetre;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	public Fenetre(){
		this.setTitle("Simulation");
		this.setSize(1500,900);
		//Demande ˆ l'objet de se positionner au centre:
		this.setLocationRelativeTo(null);
		//Termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	
		JPanel pan = new JPanel();
		pan.setBackground(Color.GREEN);
		this.setContentPane(pan);
		
		
	
	}
	public static void main(String[] args){
		Fenetre fen = new Fenetre();
	}
}
