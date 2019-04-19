package voiture;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class VoitureIcone {

	public static Image voitureIcone(String filename) {
		BufferedImage im = null;
		try {
			im = ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return im;

	}
}


