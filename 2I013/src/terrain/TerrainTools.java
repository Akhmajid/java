package terrain;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class TerrainTools {
	public static Terrain terrainFromChar(char c) throws TerrainException{
		Terrain[] values = Terrain.values();
		for(int i=0; i<values.length; i++)
			if(c == Terrain.conversion[i])
				return values[i];
		
	throw new TerrainException("Terrain inconnu : "+c);
	}
	
	public static char charFromTerrain (Terrain c) {
		return Terrain.conversion [c.ordinal()];
	}
	
	public static Color terrainToRGB(Terrain c) {
		return Terrain.convColor [c.ordinal()];
	}
	public static boolean isRunnable(Terrain t) {
		if((t.ordinal() == 1)||(t.ordinal() == 2)||(t.ordinal() == 3)) {
			return false;
		}
		else {
			return true;
		}
	}
	public static Terrain[][] lectureFichier(File fichier) throws Exception{
		InputStream file = new FileInputStream(fichier);
		try {
			// ouverture
			InputStreamReader lecteur = new InputStreamReader(file);
			// fonction supplŽmentaire
			BufferedReader in = new BufferedReader(lecteur);
			
			String buf = in.readLine();
			int nColonnes = Integer.parseInt(buf);
			
			String buf2 = in.readLine();
			int nLignes = Integer.parseInt(buf2);		   
			Terrain[][] ter = new Terrain[nLignes][nColonnes];
			for(int i=0; i< ter.length ; i++) {
				String buf3 = in.readLine();
				for(int j=0; j<ter[i].length; j++) {
				  char track = buf3.charAt(j);
				  ter[i][j] = terrainFromChar(track);
				}
			 }
			
			
			 in.close(); // penser ˆ la fermeture
			 return ter;
		 // dans l'idŽal, on sŽpare la gestion des exceptions
		 } catch (Exception e) {
		   e.printStackTrace();
		   System.err.println("Invalid Format : " + fichier 
		            + "... Loading aborted");
		   return null;
		 }
	}
	public static BufferedImage imageFromCircuit (Terrain[][] ter){
		int nColonnes = ter[0].length;
        int nLignes = ter.length;
        Color[][] c=new Color[nLignes][nColonnes];
        BufferedImage im = new BufferedImage(nLignes, nColonnes, BufferedImage.TYPE_INT_ARGB);
        for(int i=0; i< nLignes; i++) {
			   for(int j=0; j<nColonnes; j++) {
				   c[i][j]=terrainToRGB(ter[i][j]);
			   }
        	}
        // rŽcupŽration d'un stylo pour Žcrire dans l'image
        Graphics g = im.getGraphics();
        for(int i=0; i< nLignes; i++) {
			   for(int j=0; j<nColonnes; j++) {
				   g.setColor(c[i][j]);
				   g.drawLine(i, j, i, j);
			   }
     	}

        // Sauver l'image dans un fichier
        try {
        	File outputfile = new File("terrain.png");
        	ImageIO.write(im, "png", outputfile);
        } catch (IOException e) {
        	System.out.println("Erreur lors de la sauvegarde");
        }

        return im;
	}
	
	
	public static void main(String[]larges) {
		File fich = new File ("1_safe.trk");
		try {
			Terrain[][] ter  = lectureFichier(fich);
			BufferedImage im = imageFromCircuit (ter);
			System.out.println(im);
			

		}
		catch(Exception e){
			System.out.println("Erreur");
		}
	}

		
	
}