package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Random;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {

        // Spawn a random amount of asteroids
        Random random = new Random();
        for (int i = 0; i < 1 + random.nextInt(20); i++)
            // Spawn them at random coordinates
            world.addEntity(spawnAsteroid(random.nextInt(gameData.getDisplayWidth()), random.nextInt(gameData.getDisplayHeight())));

    }

    @Override
    public void stop(GameData gameData, World world) {

        // Clean up.
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            world.removeEntity(asteroid);
        }

    }

    private Entity spawnAsteroid(int spawnCoordX, int spawnCoordY)  {
        Entity asteroid = new Asteroid();
        Random random = new Random();
        int direction = random.nextInt(180);
        int size = random.nextInt(10) + 5;



        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(spawnCoordX);
        asteroid.setY(spawnCoordY);
        asteroid.setRadius(size);
        asteroid.setRotation(direction);

        return asteroid;
    }

}
