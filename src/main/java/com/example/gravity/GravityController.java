package com.example.gravity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GravityController implements Initializable {
    @FXML
    protected AnchorPane box;
    @FXML
    protected ToggleButton toggle;

    private Timeline timeline;
    private List<CelestialBody> celestialBodies = new ArrayList<>();
    private List<CelestialBody> list = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createCelestialBodies();
        box.setBackground(Background.fill(Color.rgb(240, 240, 240)));

        timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            list.addAll(celestialBodies);
            celestialBodies.forEach(celestialBody -> {
                list.remove(celestialBody);
                list.forEach(celestialBody1 -> simulate(celestialBody,celestialBody1));
            });
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    protected void createCelestialBodies() {
        celestialBodies.add(new CelestialBody(300, 300, 20, 1, 0, 0, box));
        celestialBodies.add(new CelestialBody(900, 300, 20, 0.01, 0, -0.5, box));
        celestialBodies.add(new CelestialBody(0, 300, 20, 0.01, 0, 1, box));
        celestialBodies.add(new CelestialBody(930, 300, 5, 0.00001, 0, -0.5, box));
    }

    @FXML
    public void toggle() {
        if (toggle.isSelected()) {
            timeline.play();
            toggle.setText("Turn off");
        } else {
            timeline.stop();
            toggle.setText("Turn on");
        }
    }

    private void simulate(CelestialBody body1, CelestialBody body2) {
        Circle circle1 = body1.getCircle();
        Circle circle2 = body2.getCircle();

        double velocityX1 = body1.getVelocityX();
        double velocityY1 = body1.getVelocityY();
        double velocityX2 = body2.getVelocityX();
        double velocityY2 = body2.getVelocityY();

        double mass1 = body1.getMass();
        double mass2 = body2.getMass();

        double x1 = circle1.getCenterX();
        double y1 = circle1.getCenterY();
        double x2 = circle2.getCenterX();
        double y2 = circle2.getCenterY();

        // Check for collision
        if (Math.abs(x1 - x2) < circle1.getRadius() && Math.abs(y1 - y2) < circle1.getRadius()) {
            circle1.setCenterX(x1 + velocityX1);
            circle1.setCenterY(y1 + velocityY1);
            circle2.setCenterX(x2 + velocityX2);
            circle2.setCenterY(y2 + velocityY2);
            return;
        }

        // Calculate the distance between the bodies
        double rSquared = Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);

        // Calculate the gravitational force
        double force = 1000 * mass1 * mass2 / rSquared;

        // Calculate the angle between the bodies
        double angle = Math.atan2(y2 - y1, x2 - x1);

        // Calculate acceleration components
        double ax1 = force * Math.cos(angle) / mass1;
        double ay1 = force * Math.sin(angle) / mass1;
        double ax2 = -force * Math.cos(angle) / mass2;
        double ay2 = -force * Math.sin(angle) / mass2;

        // Update velocities based on acceleration
        velocityX1 += ax1;
        velocityY1 += ay1;
        velocityX2 += ax2;
        velocityY2 += ay2;

        // Update the velocities in the celestial bodies
        body1.setVelocityX(velocityX1);
        body1.setVelocityY(velocityY1);
        body2.setVelocityX(velocityX2);
        body2.setVelocityY(velocityY2);

        // Update positions based on velocities
        circle1.setCenterX(x1 + velocityX1);
        circle1.setCenterY(y1 + velocityY1);
        circle2.setCenterX(x2 + velocityX2);
        circle2.setCenterY(y2 + velocityY2);

    }
}
