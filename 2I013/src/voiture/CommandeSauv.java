package voiture;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CommandeSauv {
	
	public static void saveListeCommande(ArrayList<Commande> liste, String filename){
        try {
                DataOutputStream os = new DataOutputStream(new FileOutputStream(filename));
                for(Commande c:liste){
                        os.writeDouble(c.getAcc());
                        os.writeDouble(c.getTurn());
                }
                os.close();
                System.out.println("Liste de commandes sauvegardée");
        } catch (IOException e) {
                e.printStackTrace();
        }
	}

	public static ArrayList<Commande> loadListeCommande(  String filename) throws IOException{
        ArrayList<Commande> liste = null;

        try {
                DataInputStream os = new DataInputStream(new FileInputStream(filename));

                liste = new ArrayList<Commande>();
                double a,t;
                while(true){ // on attend la fin de fichier
                        a = os.readDouble();
                        t = os.readDouble();
                        liste.add(new Commande(a,t));
                }
                
        } catch (EOFException e){
                return liste;
        } 

	}
}
