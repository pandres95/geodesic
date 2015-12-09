package geodesia.data.calculations;

import geodesia.data.Angle;
import geodesia.data.CoordinatePoint;

public class Trissection {

	private CoordinatePoint point;
	
	public Trissection(CoordinatePoint a, CoordinatePoint b, CoordinatePoint c, 
			Angle x, Angle y, Angle z){
		CoordinatePoint ac, ab, ba, bc, cb, ca;
		
		ac = CoordinatePoint.difference(a, c);
		ab = CoordinatePoint.difference(a, b);
		Angle angA = new Angle(Math.atan(
			ac.getEast() / ac.getNorth()
		) - Math.atan(
			ab.getEast() / ab.getNorth()
		));
		
		ba = CoordinatePoint.difference(b, a);
		bc = CoordinatePoint.difference(b, c);
		Angle angB = new Angle(Math.atan(
			ba.getEast() / ba.getNorth()
		) - Math.atan(
			bc.getEast() / bc.getNorth()
		));

		cb = CoordinatePoint.difference(c, b);
		ca = CoordinatePoint.difference(c, a);
		Angle angC = new Angle(Math.atan(
			cb.getEast() / cb.getNorth()
		) - Math.atan(
			ca.getEast() / ca.getNorth()
		));
		
		double k1, k2, k3;
		
		k1 = 1 / ((1/Math.tan(angA.getRadians())) - (1/Math.tan(x.getRadians())));
		k2 = 1 / ((1/Math.tan(angB.getRadians())) - (1/Math.tan(y.getRadians())));
		k3 = 1 / ((1/Math.tan(angC.getRadians())) - (1/Math.tan(z.getRadians())));
		
		point = new CoordinatePoint(
			( k1 * a.getNorth() + k2 * b.getNorth() + k3 * c.getNorth() ) / ( k1 + k2 + k3 ),
			( k1 * a.getEast() + k2 * b.getEast() + k3 * c.getEast() ) / ( k1 + k2 + k3 )
		);
	}

	public CoordinatePoint getPoint() {
		return point;
	}

}
