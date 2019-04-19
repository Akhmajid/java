package strategie;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import radar.Radar;
import terrain.Circuit;
import voiture.Commande;
import voiture.Voiture;

public class StrategyListCommande implements Strategy{
	private ArrayList <Commande> liste ;
	private int index ;
	
	public StrategyListCommande ( ArrayList<Commande>liste ) { 
		this.liste = liste;
		index =0; 
		}
	
	public ArrayList<Commande> getListe() {
		return liste;
	}

	public int getIndex() {
		return index;
	}

	public Commande getCommande() {
		return liste.get(index);
		
	}
	
	public void increment(){
		index++;
	}
	

	public void addCommande(Commande c) {
		liste.add(c);
		
	}

	@Override
	public void init(Voiture v, Circuit c) {
		// TODO Auto-generated method stub
		
	}

	
	
}
