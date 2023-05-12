import java.awt.Color;
import java.awt.Graphics;


public class Planet {
    private double mass;
    private double velocityX;
    private double velocityY;
    private double positionX;
    private double positionY;

    private final double GRAVITY = 0.0001;

    public Planet(double m, double vX, double vY, int xVal, int yVal){
        mass = m;
        velocityX = vX;
        velocityY = vY;
        positionX = xVal;
        positionY = yVal;
    }

    public double getMass() {
        return mass;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setVelocityX(double vX) {
        this.velocityX = vX;
    }

    public void setVelocityY(double vY) {
        this.velocityY = vY;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void update(Planet[] planets){
        double forceX = 0;
        double forceY = 0;


        for(Planet p : planets){
            double valX = Math.abs(p.getMass() / Math.sqrt(Math.pow(positionX - p.getPositionX(), 2) + Math.pow(positionY - p.getPositionY(), 2)) *
                    Math.cos(Math.atan((positionY - p.getPositionY())/(positionX - p.getPositionX()))));
            double valY = Math.abs(p.getMass() / Math.sqrt(Math.pow(positionX - p.getPositionX(), 2) + Math.pow(positionY - p.getPositionY(), 2)) *
                    Math.sin(Math.atan((positionY - p.getPositionY())/(positionX - p.getPositionX()))));

            if(positionX > p.getPositionX()) forceX -= valX;
            else forceX += valX;

            if(positionY > p.getPositionY()) forceY -= valY;
            else forceY += valY;

        }

        velocityX += GRAVITY*forceX;
        velocityY += GRAVITY*forceY;

        positionX += velocityX;
        positionY += velocityY;


    }

    public void draw(Graphics g, int shiftX, int shiftY, double zoom){
        int radius = 10;

        g.fillOval((int)(positionX/zoom) + 400 + shiftX, (int)(positionY/zoom) + 400 + shiftY, radius, radius);
    }

    public String toString(){
        return "Mass: " + mass + " Position: " + positionX + ", " + positionY + " Velocity: " + velocityX +", " + velocityY;
    }

}
