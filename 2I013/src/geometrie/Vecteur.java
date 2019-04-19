package geometrie;

public class Vecteur {
	public final double x,y;

	public Vecteur(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vecteur other = (Vecteur) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vecteur [x=" + x + ", y=" + y + "]";
	}
	
	public Vecteur addition(Vecteur v) {
		return new Vecteur(x+v.x, y+v.y);
	}
	
	public Vecteur soustraction(Vecteur v) {
		return new Vecteur(x-v.x, y-v.y);
	}
	public double produitScalaire(Vecteur v) {
		return x*v.x+y*v.y;
	}
	public double norme() {
		return Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
	}
	public double produitVectoriel (Vecteur v) {
		return x*v.y-y*v.x;
	}
	
	public Vecteur multiplication(double s) {
		return new Vecteur(x*s,y*s);
	}
	public Vecteur rotation (double a) {
		return new Vecteur(x*Math.cos(a)-y*Math.sin(a), x*Math.sin(a)+y*Math.cos(a));
	}
	
	public Vecteur clonage() {
		return new Vecteur(x,y);
	}
	
	public boolean egalite(Vecteur v) {
		return ((x==v.x)&&(y==v.y));
	}
	public double angle(Vecteur v) {
		double angle=0;
		double signe=1;
		//double deg;
		if (this.produitVectoriel(v)<0) {
			signe=-1;
		}
		double ps=this.produitScalaire(v);
		
		double na=this.norme();
		double nb=v.norme();
		angle=signe*Math.acos((ps/(na*nb)));
		//deg = angle * (180/Math.PI);
	
		return angle;
	
	}
	public Vecteur unitVec(){
		return new Vecteur(x/this.norme(),y/this.norme());
	}

	public Vecteur fact(double vitesse) {
		return new Vecteur(x*vitesse, y*vitesse);
	}
	
	public static Vecteur inv(Vecteur v){
		return new Vecteur(v.getY(), v.getX());
	}
	
	public Vecteur rotationAr (double a) {
		
		return new Vecteur(    ((int)  ((x*Math.cos(a)-y*Math.sin(a))  *10)/10.)  , ((int)((x*Math.sin(a)+y*Math.cos(a))*10)/10.)    );
	}
	
	public static double dist(Vecteur v1, Vecteur v2) {
		double a = Math.pow(v2.getX() - v1.getX(), 2);
		double b = Math.pow(v2.getY() - v1.getY(), 2);
		return Math.sqrt(a + b);
	}
}
