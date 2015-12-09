package geodesia.data;

public class CoordinatePoint {
	
	private double north, east;

	public CoordinatePoint(double north, double east) {
		this.north = north;
		this.east = east;
	}
	
	public CoordinatePoint(double constant, Angle a){
		this.north = constant * Math.cos(a.getRadians());
		this.east = constant * Math.sin(a.getRadians());
	}
	
	public static CoordinatePoint difference(CoordinatePoint a, CoordinatePoint b){
		return new CoordinatePoint(b.north - a.north, b.east - a.east);
	}
	
	public static CoordinatePoint sum(CoordinatePoint a, CoordinatePoint b){
		return new CoordinatePoint(a.north + b.north, a.east + b.east);
	}

	public double getNorth() {
		return north;
	}

	public double getEast() {
		return east;
	}
	
	public double getDistance(){
		return Math.sqrt(Math.pow(north, 2) + Math.pow(east, 2));
	}
	
	public Angle getAngle(){
		return new Angle(Math.atan(east / north));
	}

}
