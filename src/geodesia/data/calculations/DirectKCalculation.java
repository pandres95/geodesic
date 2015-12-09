package geodesia.data.calculations;

import geodesia.data.Angle;
import geodesia.data.Datum;

public class DirectKCalculation extends Calculation {
	
	Angle lambda;
	
	public DirectKCalculation(Datum datum, Angle lambda){
		super(datum.getA(), datum.getB(), datum.getE(), datum.getF());
		this.lambda = lambda;
	}
	
	public double[] geodesical(Angle phi){
		double n = a / Math.sqrt(1 - e * Math.pow(Math.sin(phi.getRadians()), 2));
		return new double[]{
			n * Math.cos(phi.getRadians()) * Math.cos(lambda.getRadians()),
			n * Math.cos(phi.getRadians()) * Math.sin(lambda.getRadians()),
			n * (1 - e) * Math.sin(phi.getRadians())
		};
	}
	
	public double[] geocentrical(Angle theta, Angle w){
		double[] coords = parametric(theta);
		double r = Math.sqrt(Math.pow(coords[0], 2) + Math.pow(coords[1], 2) + Math.pow(coords[2], 2));
		return new double[]{
			r * Math.cos(w.getRadians()) * Math.cos(lambda.getRadians()),
			r * Math.cos(w.getRadians()) * Math.sin(lambda.getRadians()),
			r * Math.sin(w.getRadians())
		};
	}
	
	public double[] parametric(Angle theta){
		return new double[]{
			a * Math.cos(theta.getRadians()) * Math.cos(lambda.getRadians()),
			a * Math.cos(theta.getRadians()) * Math.sin(lambda.getRadians()),
			b * Math.sin(theta.getRadians())
		};
	}

}
