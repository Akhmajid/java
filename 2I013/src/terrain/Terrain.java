package terrain;

import java.awt.Color;

public enum Terrain {
	Route, Herbe, Eau, Obstacle, BandeRouge, BandeBlanche, StartPoint, EndLine, Boue;

  public static char[] conversion = {'.', 'g', 'b', 'o', 'r', 'w', '*', '!', 'm'};

  public static Color[] convColor = {new Color (196,196,196), new Color(126,255,111), 
	  new Color(128,189,220), new Color (196,196,196), new Color(245,76,38), Color.white, 
      new Color(157,225,243), new Color(157,225,243), new Color(200, 150, 128)};
}
