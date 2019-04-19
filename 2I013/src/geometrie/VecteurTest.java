package geometrie;

public class VecteurTest {

	public static void main(String[] args) {
		Vecteur v1=new Vecteur (1,2);
		Vecteur v2=new Vecteur (1,3);
		
		Vecteur v3=v1.addition(v2);
		System.out.println(v3.toString());
		
		Vecteur v4=new Vecteur (1,1);
		Vecteur v5=new Vecteur (1,0);
		Vecteur v6=new Vecteur (0,1);
		Vecteur v7=new Vecteur (0,3);
		Vecteur v8=new Vecteur (0,-1);
		System.out.println((v4.rotation((Math.PI)/2.)).toString());
		
		System.out.println((v6.produitScalaire(v5)));
		System.out.println((v6.produitScalaire(v7)));
		System.out.println((v6.produitScalaire(v8)));
		System.out.println((v5.angle(v6)));
		
	}

}
