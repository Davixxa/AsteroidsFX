package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.services.ColliderService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.enemysystem.Enemy;
import dk.sdu.mmmi.cbse.playersystem.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;

public class CollisionDetector implements IPostEntityProcessingService {

    public CollisionDetector() {
    }

    @Override
    public void process(GameData gameData, World world) {
        // two for loops for all entities in the world
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {

                // if the two entities are identical, skip the iteration
                if (entity1.getID().equals(entity2.getID())) {
                    continue;                    
                }

                // CollisionDetection
                if (this.collides(entity1, entity2)) {

                    if (Asteroid.class.isInstance(entity1) && Asteroid.class.isInstance(entity2)) {
                        // Not part of spec, but makes it feel a little better than just having them flow through each other. Doesn't seem to work.
                        //System.out.println("Collision between 2 asteroids");
                        entity1.setRotation(180 - entity1.getRotation());
                        entity2.setRotation(180 - entity2.getRotation());
                    }

                    // Asteroid collission logic

                    if (Asteroid.class.isInstance(entity1)) {
                        if(!Asteroid.class.isInstance(entity2)) {
                            if (Bullet.class.isInstance(entity2)) {
                                System.out.println("Asteroid Shot!");
                                System.out.println(Arrays.toString(getAsteroidColliders().toArray()));
                                getAsteroidColliders().stream().findFirst().ifPresent(asteroidProcessorProvided -> asteroidProcessorProvided.collided(entity1, world));
                                world.removeEntity(entity1);
                            }
                            world.removeEntity(entity2); // Anything that isn't an asteroid that collides with an asteroid should be destroyed
                        }
                    }


                    if (Asteroid.class.isInstance(entity2)) {
                        if(!Asteroid.class.isInstance(entity1)) {
                            if (Bullet.class.isInstance(entity1)) {
                                System.out.println("Asteroid Shot!");
                                getAsteroidColliders().stream().findFirst().ifPresent(asteroidProcessorProvided -> asteroidProcessorProvided.collided(entity2, world));
                            }
                            world.removeEntity(entity1); // Anything that isn't an asteroid that collides with an asteroid should be destroyed
                        }
                    }

                    if (Asteroid.class.isInstance(entity2)) {
                        if(!Asteroid.class.isInstance(entity1))
                            world.removeEntity(entity1);
                    }

                    // Bullet collission logic for player
                    // Note, technically there's nothing preventing the player or the enemy from being hit by their own bullets
                    // That being said, this will never in practice happen because neither the player nor the enemy can catch up
                    // with their own bullets.

                    if (Bullet.class.isInstance(entity1)) {
                        if(Player.class.isInstance(entity2)) {
                            ((Player) entity2).health -= 1;
                            if (((Player) entity2).health <= 0)
                                world.removeEntity(entity2);
                        }

                    }

                    if (Bullet.class.isInstance(entity2)) {
                        if(Player.class.isInstance(entity1)) {
                            ((Player) entity1).health -= 1;
                            if (((Player) entity1).health <= 0)
                                world.removeEntity(entity1);
                        }

                    }

                    // ...and for Enemy

                    if (Bullet.class.isInstance(entity1)) {
                        if(Enemy.class.isInstance(entity2)) {
                            ((Enemy) entity2).health -= 1;
                            if (((Enemy) entity2).health <= 0)
                                world.removeEntity(entity2);
                        }

                    }

                    if (Bullet.class.isInstance(entity2)) {
                        if(Enemy.class.isInstance(entity1)) {
                            ((Enemy) entity1).health -= 1;
                            if (((Enemy) entity1).health <= 0)
                                world.removeEntity(entity1);
                        }

                    }

                    //  System.out.println(entity1.toString() + " collided with " + entity2.toString());
                    /*world.removeEntity(entity1);
                    world.removeEntity(entity2);*/
                }
            }
        }

    }

    private Collection<? extends AsteroidSplitterImpl> getAsteroidProcessors() {
        return ServiceLoader.load(AsteroidSplitterImpl.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    private Collection<? extends ColliderService> getAsteroidColliders() {
        return ServiceLoader.load(ColliderService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    public Boolean collides(Entity entity1, Entity entity2) {
        float dx = (float) entity1.getX() - (float) entity2.getX();
        float dy = (float) entity1.getY() - (float) entity2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < (entity1.getRadius() + entity2.getRadius());
    }

}
