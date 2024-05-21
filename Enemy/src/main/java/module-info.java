import dk.sdu.mmmi.cbse.common.enemy.EnemyShipCreator;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.enemysystem.ControlSystem;

module Enemy {
    exports dk.sdu.mmmi.cbse.enemysystem;
    requires Common;
    requires static CommonBullet;
    requires CommonAsteroids;
    requires CommonEnemy;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    uses dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
    uses dk.sdu.mmmi.cbse.common.enemy.EnemyShipCreator;
    provides EnemySPI with ControlSystem;
    provides EnemyShipCreator with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
    provides IGamePluginService with dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
    provides IEntityProcessingService with ControlSystem;
}