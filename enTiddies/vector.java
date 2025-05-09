package enTiddies;

/**
 * The {@code vector} class represents a 2D vector using polar coordinates (angle and velocity).
 * It provides methods to compute the Cartesian components (X and Y), modify the vector, and
 * combine it with other vectors.
 */
public class vector {
    
    /** Angle of the vector in radians. 0 is right, π/2 is down. */
    public double angle;

    /** Magnitude or speed of the vector. */
    public double velocity;

    /**
     * Constructs a {@code vector} with the given angle and velocity.
     *
     * @param angle    The direction of the vector in radians.
     * @param velocity The magnitude of the vector.
     */
    public vector(double angle, double velocity) {
        this.angle = angle;
        this.velocity = velocity;
    }

    /**
     * Returns the angle (direction) of the vector in radians.
     *
     * @return The angle in radians.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Returns the magnitude (velocity) of the vector.
     *
     * @return The magnitude of the vector.
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Returns the horizontal component of the vector.
     *
     * @return The X velocity derived from angle and magnitude.
     */
    public double getXVelocity() {
        return Math.cos(angle) * velocity;
    }

    /**
     * Returns the vertical component of the vector.
     *
     * @return The Y velocity derived from angle and magnitude.
     */
    public double getYVelocity() {
        return Math.sin(angle) * velocity;
    }

    /**
     * Sets the magnitude (velocity) of the vector.
     *
     * @param velocity The new magnitude to assign.
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    /**
     * Sets the direction (angle) of the vector.
     *
     * @param angle The new angle in radians.
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Adds another vector to this one by converting both to Cartesian,
     * adding components, and converting the result back to polar form.
     *
     * @param v The vector to be added to this vector.
     */
    public void addVector(vector v) {
        double newXVel = this.getXVelocity() + v.getXVelocity();
        double newYVel = this.getYVelocity() + v.getYVelocity();
        this.setVelocity(Math.sqrt(Math.pow(newXVel, 2) + Math.pow(newYVel, 2)));
        this.setAngle(Math.atan2(newYVel, newXVel));
    }
}