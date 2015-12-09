package geodesia.data.calculations;

import geodesia.data.Angle;
import geodesia.data.CoordinatePoint;

public class Bissection {
	
	private double ab, ap;
	private Angle alpAb, alpAp;
	private CoordinatePoint point;
	
	public Bissection(CoordinatePoint a, CoordinatePoint b, Angle alpha, Angle beta) {
		CoordinatePoint d = CoordinatePoint.difference(a, b);
		ab = d.getDistance();
		alpAb = d.getAngle();
		
		Angle delta = new Angle(180 - alpha.getDecimal() - beta.getDecimal());
		ap = ab * Math.sin(beta.getRadians()) / Math.sin(delta.getRadians());
		alpAp = new Angle(alpAb.getDecimal() - alpha.getDecimal()); 
		
		d = new CoordinatePoint(ap, alpAp);
		this.point = CoordinatePoint.sum(a, d);
	}

	public double getAb() {
		return ab;
	}

	public double getAp() {
		return ap;
	}

	public Angle getAlpAb() {
		return alpAb;
	}

	public Angle getAlpAp() {
		return alpAp;
	}

	public CoordinatePoint getPoint() {
		return point;
	}
	
}
