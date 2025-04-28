package enTiddies;

public class vector {
    public double angle;
    public double velocity;
    public vector(double angle, double velocity) {
        this.angle = angle%2*Math.PI;
        this.velocity = velocity;
    }
    public double getAngle() {
        return angle;
    }
    public double getVelocity() {
        return velocity;
    }
    public double getXVelocity() {
        return Math.cos(angle)*velocity;
    }
    public double getYVelocity() {
        return Math.sin(angle)*velocity;
    }
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    } public void setAngle(double angle) {
        this.angle = angle;
    }
    public void addVector(vector v) {
        double newXVel = this.getXVelocity() + v.getXVelocity();
        double newYVel = this.getYVelocity() + v.getYVelocity();
        this.setVelocity(Math.sqrt(Math.pow(newXVel, 2) + Math.pow(newYVel, 2)));
        this.setAngle(Math.atan2(newYVel, newXVel));
    }
}