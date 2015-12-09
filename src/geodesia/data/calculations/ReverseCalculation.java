package geodesia.data.calculations;

import geodesia.data.Angle;
import geodesia.data.Datum;

public class ReverseCalculation extends Calculation {

	public ReverseCalculation(Datum datum) {
		super(datum.getA(), datum.getB(), datum.getE(), datum.getF());
		
	}
	
	private Angle sigma(double x, double y, double z){
		return new Angle(Math.atan((z * a) / (b * Math.hypot(x, y))));
	}
	
	public Angle phi(double x, double y, double z){
		double	g	= sigma(x, y, z).getRadians()
		,		eps	= (Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(b, 2);
		return new Angle(Math.atan(
			( z + eps * b * Math.pow(Math.sin(g), 3) ) / 
			(Math.hypot(x, y) - e * a * Math.pow(Math.cos(g), 2) )
		));
	}
	
	public Angle lambda(double x, double y, double z) {
		return new Angle(2 * Math.atan(y / (x + Math.hypot(x, y))));
	}
	
	private double reverseN(double x, double y, double z){
		double phi = phi(x, y, z).getRadians();
		return a / Math.sqrt(1 - Math.pow(e, 2) * Math.pow(Math.sin(phi), 2));
	}
	
	public double h(double x, double y, double z){
		double	phi = phi(x, y, z).getRadians()
		,		n	= reverseN(x, y, z);
		return Math.hypot(x, y) / Math.cos(phi) - n;
	}
	
	public Object[] calculate(double x, double y, double z){
		return new Object[]{phi(x, y, z), lambda(x, y, z), h(x, y, z) };
	}

}
