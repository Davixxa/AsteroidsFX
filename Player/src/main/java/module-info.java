
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.playersystem.ControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;

module Player {
    exports dk.sdu.mmmi.cbse.playersystem;
    requires Common;
    requires transitive CommonBullet;
    //requires CommonAsteroids;
    uses dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
    provides IGamePluginService with PlayerPlugin;
    provides IEntityProcessingService with ControlSystem;
    //provides BulletSPI with dk.sdu.mmmi.cbse.playersystem.PlayerBulletSystem;
    
}
