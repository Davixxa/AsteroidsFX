package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImpl implements IAsteroidSplitter {


    @Override
    public void createSplitAsteroid(Entity e, World world) {

        System.out.println("\n\n\n\n\n***********SPLITASTEROID CALLED***************\n\n\n\n\n");

        Entity asteroid1 = new Asteroid();
        int size = Math.round(e.getRadius() / 2);
        asteroid1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid1.setX(e.getX() - e.getRadius() - 5);
        asteroid1.setY(e.getY() - e.getRadius() - 5);
        asteroid1.setRadius(size);
        asteroid1.setRotation(e.getRotation() - 90);

        Entity asteroid2 = new Asteroid();
        asteroid1.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid1.setX(e.getX() - e.getRadius() + 5);
        asteroid1.setY(e.getY() - e.getRadius() + 5);
        asteroid1.setRadius(size);
        asteroid1.setRotation(e.getRotation() + 90);

        //world.removeEntity(e);
        world.addEntity(asteroid1);
        world.addEntity(asteroid2);

    }

}
