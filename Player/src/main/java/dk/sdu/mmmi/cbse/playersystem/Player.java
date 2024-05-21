package dk.sdu.mmmi.cbse.playersystem;

//import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;

// This doesn't seem to need anything on its own.

public class Player extends Entity {

    public double speed = 0;
    public double velocityX = 0;
    public double velocityY = 0;
    public double maxVelocity = 2;
    public double maxSpeed = 2.5;
    public int rotationSpeed = 5;
    public double speedIncrement = .25;
    public double speedDecay = 0.1;
    public int health = 3;


}
