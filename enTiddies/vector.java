package enTiddies;

public class vector {
    public double angle;
    public double xVelocity, yVelocity;
    public double xAcceleration, yAcceleration;
    public vector(double angle, double xVelocity, double yVelocity, double xAcceleration, double yAcceleration) {
        this.angle = angle;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xAcceleration = xAcceleration;
        this.yAcceleration = yAcceleration;
    }
    public double getAngle() {
        return angle;
    }
    public double getxVelocity() {
        return xVelocity;
    }
    public double getyVelocity() {
        return yVelocity;
    }
    public double getxAcceleration() {
        return xAcceleration;
    }
    public double getyAcceleration() {
        return yAcceleration;
    }
    public double overallVelocity() {
        return Math.sqrt(Math.pow(xVelocity, 2.0)+Math.pow(yAcceleration, 2.0));
    }
    public void accelerate() {
        
    }
}