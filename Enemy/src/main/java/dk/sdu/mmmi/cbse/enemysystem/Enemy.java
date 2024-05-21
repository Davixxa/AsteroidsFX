package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ColliderService;

public class Enemy extends Entity implements ColliderService {

    public int health = 3;

    @Override
    public void collided(Entity collided, World world) {
        if (Asteroid.class.isInstance(collided))
            world.removeEntity(this);
        if (Bullet.class.isInstance(collided))
            health -= 1;

        if (health <= 0)
            world.removeEntity(this);

    }

}
