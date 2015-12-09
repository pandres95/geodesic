package geodesia.data.calculations;

import geodesia.data.Angle;
import geodesia.data.Datum;

public class DirectCalculation extends Calculation {

	public DirectCalculation(Datum datum) {
		super(datum.getA(), datum.getB(), datum.getE(), datum.getF());
	}

	private double nDirect(Angle phi, Angle lambda, double h) {
		return a / Math.sqrt(1 - e * Math.pow(Math.sin(phi.getRadians()), 2));
	}

	public double x(Angle phi, Angle lambda, double h) {
		double n = nDirect(phi, lambda, h);
		return (n + h) * Math.cos(phi.getRadians()) * Math.cos(lambda.getRadians());
	}

	public double y(Angle phi, Angle lambda, double h) {
		double n = nDirect(phi, lambda, h);
		return (n + h) * Math.cos(phi.getRadians()) * Math.sin(lambda.getRadians());
	}

	public double z(Angle phi, Angle lambda, double h) {
		double n = nDirect(phi, lambda, h);
		return ((n * (1 - e)) + h) * Math.sin(phi.getRadians());
	}

	public double[] calculate(Angle phi, Angle lambda, double h) {
		return new double[] { x(phi, lambda, h), y(phi, lambda, h), x(phi, lambda, h) };
	}

}
