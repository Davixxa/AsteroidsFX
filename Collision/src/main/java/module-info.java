import dk.sdu.mmmi.cbse.asteroid.AsteroidProcessorImpl;
import dk.sdu.mmmi.cbse.common.services.ColliderService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    uses AsteroidProcessorImpl;
    uses dk.sdu.mmmi.cbse.asteroid.AsteroidSplitterImpl;
    uses dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
    uses ColliderService;
    requires Common;
    requires CommonAsteroids;
    requires static Player;
    requires static CommonBullet;
    requires static Enemy;
    requires Asteroid;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
}