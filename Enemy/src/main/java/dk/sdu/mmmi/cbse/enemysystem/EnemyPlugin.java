package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.enemy.EnemyShipCreator;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;


public class EnemyPlugin implements IGamePluginService, EnemyShipCreator {

    private Entity enemy;

    public EnemyPlugin() {}

    @Override
    public void start(GameData gameData, World world) {

        // This class implements EnemyShipCreator, but this should make it easier to change it out for another one later on.
        getEnemyShipCreators().stream().findFirst().ifPresent(

                enemySPI -> {
                   enemy = enemySPI.createEnemyShip(gameData);

                }
        );

        world.addEntity(enemy);
    }


    private Collection<? extends EnemyShipCreator> getEnemyShipCreators() {
        return ServiceLoader.load(EnemyShipCreator.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    public Entity createEnemyShip(GameData gameData) {
        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
        enemyShip.setX(gameData.getDisplayWidth()/4);
        enemyShip.setY(gameData.getDisplayHeight()/4);
        enemyShip.setRadius(8);
        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(enemy);
    }

}
