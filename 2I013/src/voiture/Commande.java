package voiture;

public class Commande {
	private double acc;
	private double turn;
	
	
	public Commande(double acc, double turn) {
		this.acc = acc;
		this.turn = turn;
	}


	public double getAcc() {
		return acc;
	}

	public double getTurn() {
		return turn;
	}
	public void setTurn(double t){
		this.turn=t;
	}


	public void setAcc(double a) {
		this.acc=a;
		
	}


	
}
