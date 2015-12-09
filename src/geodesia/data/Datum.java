package geodesia.data;

public abstract class Datum {

	private double a, b, e, f;

	public Datum(double a, double b, double f) {
		this.a = a;
		this.f = f;
		this.b = b;
		this.e = ((Math.pow(a, 2) - Math.pow(b, 2)) / (Math.pow(a, 2)));
	}
	
	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getE() {
		return e;
	}
	
	public double getF() {
		return f;
	}
	
	public double getArea(){
		double a = 2 * 
					Math.PI * 
					Math.pow(b, 2) * 
					( 
						(1 / (1 - e)) + 
						(1 / (2 * Math.sqrt(e))) * Math.log((1 + Math.sqrt(e)) / (1 - Math.sqrt(e))) 
					);
		return a / Math.pow(100, 3);
	}
	
	public double getVolume(){
		double v = 4* Math.PI / 3 * Math.pow(a, 2) * b;
		return v / Math.pow(100, 3);
	}
	
	/*
	 * Inhereted Datums 
	 */
	
	public static class WGS84 extends Datum {
		public WGS84(){
			super(6378137, 6356752.3141, 1/298.2572223563);
		}
	}
	
	public static class International extends Datum {
		public International(){
			super(6378388, 6356911.946, 1/297.0);
		}
	}
	
	public static class Bogota extends Datum {
		public Bogota(){
			super(6378388, 6356911.946, 1/297.0);
		}
	}
	
	public static class Airy1830 extends Datum {
		public Airy1830(){
			super(6377563.396, 6356256.909, 1/299.32496);
		}
	}
	
	public static class ModifAiry extends Datum {
		public ModifAiry(){
			super(6377340.189, 6356034.448, 1/299.32496);
		}
	}
	
	public static class AuNational extends Datum {
		public AuNational(){
			super(6378160, 6356774.719, 1/298.25);
		}
	}
	
	public static class Delambre1800 extends Datum {
		public Delambre1800(){
			super(6375635, 6356564, 1/334);
		}
	}
	
	public static class MoFi1960 extends Datum {
		public MoFi1960(){
			super(6378155, 6356773.32, 1/298.3);
		}
	}
	
	public static class Helmert1906 extends Datum {
		public Helmert1906(){
			super(6378200, 6356818.17, 1/298.3);
		}
	}
	
	public static class Indonesian1974 extends Datum {
		public Indonesian1974(){
			super(6378160, 6356774.504, 1/298.247);
		}
	}
	
	public static class Krassovsky1940 extends Datum {
		public Krassovsky1940(){
			super(6378245, 6356863.019, 1/298.3);
		}
	}
	
	public static class GRS80 extends Datum {
		public GRS80(){
			super(6738167, 6356752.314, 1/298.257);
		}
	}
	
	public static class SouthAm1969 extends Datum {
		public SouthAm1969(){
			super(6738160, 6356774.719, 1/298.25);
		}
	}
	
	public static class Struve1924 extends Datum {
		public Struve1924(){
			super(6738298.3, 6356657.1, 1/294.73);
		}
	}
	
	public static class Wallbeck1819 extends Datum {
		public Wallbeck1819(){
			super(6736896, 6355833, 1/302.8);
		}
	}
	
}
