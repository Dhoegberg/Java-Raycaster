package vectorMath;

public final class AngleCalculation {
	
	public static final float circle = (float)(Math.PI*2);
	/**
	 * Rotates angle with rotation.
	 * Return angle will be in radians,
	 * converted to span: 0 to x to 2pi.
	 * @param angle
	 * @param rotation
	 * @return
	 */
	public static final float rotateDirection(float direction, float rotation){
		direction += rotation;
		return getDirectionFromAngle(direction);
	}
	/**
	 * Return angle will be in radians,
	 * converted to span: 0<x<pi*2.
	 * @param angle
	 * @return
	 */
	public static final float getDirectionFromAngle(float angle){
		while(angle < 0) angle += circle;
		angle %= circle;
		return angle;
	}
	public static final double gameSin(float angle){
		return -Math.sin(-angle);
	}
	public static final double gameCos(float angle){
		return -Math.cos(-angle);
	}
	public static final double perspectiveCos(float angle){
		return Math.cos(angle);
	}
}
