package geodesia.data.calculations;

public abstract class Calculation {

	protected double a, b, e, f;

	public Calculation(double a, double b, double e, double f) {
		this.a = a;
		this.b = b;
		this.e = e;
		this.f = f;
	}

}
