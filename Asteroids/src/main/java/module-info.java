import dk.sdu.mmmi.cbse.asteroid.AsteroidPlugin;
import dk.sdu.mmmi.cbse.asteroid.AsteroidProcessorImpl;
import dk.sdu.mmmi.cbse.common.services.ColliderService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
    exports dk.sdu.mmmi.cbse.asteroid;
    requires Common;
    requires CommonAsteroids;
    requires CommonBullet;
    provides IGamePluginService with AsteroidPlugin;
    provides ColliderService with AsteroidProcessorImpl;
    provides IEntityProcessingService with AsteroidProcessorImpl;
}