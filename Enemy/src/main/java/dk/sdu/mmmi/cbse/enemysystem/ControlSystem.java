package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.enemy.EnemySPI;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public class ControlSystem implements IEntityProcessingService, EnemySPI {


    @Override
    public void process(GameData gameData, World world) {

        // This class implements EnemySPI, but this should make it easier to change it out for another SPI later on.
        System.out.println(Arrays.toString(getEnemySPIs().toArray()));
        getEnemySPIs().stream().findFirst().ifPresent(

                    enemySPI -> {
                        System.out.println("SPI found");
                        enemySPI.movementBehaviour(gameData, world);
                        enemySPI.shootingBehaviour(gameData, world);
                    }
            );




    }


    private Collection<? extends EnemySPI> getEnemySPIs() {
        return ServiceLoader.load(EnemySPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

    @Override
    public void movementBehaviour(GameData gameData, World world) {
        System.out.println("SPI called");
        for (Entity enemy : world.getEntities(Enemy.class)) {
            Random rand = new Random();
            int rotChange = rand.nextInt(0, 20) - 10; // Allow it to rotate between -10 and 10 degrees, effectively allowing rotation either left or right.
            enemy.setRotation(enemy.getRotation() + rotChange);
            double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
            double changeY = Math.cos(Math.toRadians(enemy.getRotation()));
            enemy.setX(enemy.getX() + changeX);
            enemy.setY(enemy.getY() + changeY);



            if (enemy.getX() < 0)
                enemy.setX(1);

            if (enemy.getY() < 0 )
                enemy.setY(1);


            if (enemy.getX() > gameData.getDisplayWidth())
                enemy.setX(gameData.getDisplayWidth()-1);

            if (enemy.getY() > gameData.getDisplayHeight())
                enemy.setY(gameData.getDisplayHeight()-1);


        }

    }

    @Override
    public void shootingBehaviour(GameData gameData, World world) {
        System.out.println("SPI called");
        Random rand = new Random();
        boolean willFire = rand.nextInt(100) < 25; // 25% chance to fire. This is random, right?

        if (willFire)
            for (Entity enemy : world.getEntities(Enemy.class))
                getBulletSPIs().stream().findFirst().ifPresent(

                        bulletSPI -> { world.addEntity(bulletSPI.createBullet(enemy, gameData)); }
                );
    }
}
