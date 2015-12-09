package geodesia.data;

public class Angle {
	private int degrees, minutes;
	private double seconds;

	/**
	 * Degrees value of angle
	 * @return Angle's degrees
	 */
	public int getDegrees() {
		return degrees;
	}

	/**
	 * Minutes value of angle
	 * @return Angle's minutes
	 */
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Seconds value of angle
	 * @return Angle's seconds
	 */
	public double getSeconds() {
		return seconds;
	}

	/**
	 * Builds angle from its base representation
	 * @param degrees Angle's degrees
	 * @param minutes Angle's minutes
	 * @param seconds Angle's seconds
	 */
	public Angle(int degrees, int minutes, double seconds) {
		super();
		this.degrees = degrees;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	/**
	 * Creates an angle from its radians value
	 * @param radianAngle The angle in radians
	 */
	public Angle(double radianAngle){
		double degrees = radianAngle * (180 / Math.PI);
		this.degrees = (int)degrees;
		
		// Calculates degrees as remaining decimal values to get minutes and seconds
		degrees = (degrees - this.degrees) * 60;
		this.minutes = (int)degrees;
		this.seconds = (degrees - this.minutes) * 60;
	}
	
	/**
	 * Retrieves decimal representation of angle
	 * @return Angle's value in decimal representation
	 */
	public double getDecimal(){
		return this.degrees + (this.minutes / 60.0) + (this.seconds / 3600.0);
	}
	
	/**
	 * Retrieves angle's value in radians.
	 * @return Angle's value in radians
	 */
	public double getRadians(){
		return Math.PI * getDecimal() / 180.0;
	}
	
	/**
	 * Textual representation of an Angle in Degrees, Minutes and Seconds
	 */
	@Override public String toString(){
		return String.format("%dº %d' %d''", this.degrees, this.minutes, this.seconds);
	}

}
