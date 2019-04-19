package terrain;

public class TerrainException extends Exception {
	//private  static  final  long serialVersionUID=0;
	public TerrainException (String message){
		super("Erreur :"+ message);
	}
}
