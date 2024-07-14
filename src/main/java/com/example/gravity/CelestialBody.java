package com.example.gravity;

import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class CelestialBody {
    private double x;
    private double y;
    private double r;
    private double mass;
    private double velocityX;
    private double velocityY;
    private Circle circle;
    CelestialBody(double x, double y, double r, double mass, double velocityX, double velocityY, AnchorPane box){
        this.mass=mass;
        this.x=x;
        this.y=y;
        this.velocityY=velocityY;
        this.velocityX=velocityX;
        this.r=r;
        this.circle=new Circle(x,y,r);
        box.getChildren().add(circle);
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public Circle getCircle() {
        return circle;
    }

    public void setCircle(Circle circle) {
        this.circle = circle;
    }

}
