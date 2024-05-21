package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;
import java.util.stream.Collectors;


public class ControlSystem implements IEntityProcessingService {


    @Override
    public void process(GameData gameData, World world) {
        // Implement actual controls. Only so many ways to do this to be honest.

        // Find the first (and hopefully only) player in the game.
        // Implement more floaty velocity, more akin to the original?
        world.getEntities(Player.class).stream().findFirst().ifPresent( player -> {
            if (gameData.getKeys().isDown(GameKeys.UP)) {
                // Instead of moving, increment speed
                ((Player) player).speed = ((Player) player).speed + ((Player) player).speedIncrement; // This seems to get reset every frame. Why?

                // This never executes, why?
                if (((Player) player).speed > ((Player) player).maxSpeed) {
                    ((Player) player).speed = ((Player) player).maxSpeed;
                }
            }

            if (gameData.getKeys().isDown(GameKeys.LEFT))
                player.setRotation(player.getRotation() - ((Player) player).rotationSpeed);
            if (gameData.getKeys().isDown(GameKeys.RIGHT))
                player.setRotation(player.getRotation() + ((Player) player).rotationSpeed);
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                getBulletProviders().stream().findFirst().ifPresent( provider -> {
                    world.addEntity(provider.createBullet(player, gameData));
                });
            }



            // Finally move and decay speed.
            double changeX = Math.cos(Math.toRadians(player.getRotation()));
            double changeY = Math.sin(Math.toRadians(player.getRotation()));
            changeX = changeX * ((Player) player).speed;
            changeY = changeY * ((Player) player).speed; // These should scale. Why don't they?
            player.setX(player.getX() + changeX);
            player.setY(player.getY() + changeY);
            ((Player) player).speed -= ((Player) player).speedDecay; // Speed doesn't seem to factor into speed. Also seems to reset every frame. Why?
            if (((Player) player).speed < 0)
                ((Player) player).speed = 0;


        });



    }

    // Stolen from provided implementation for now.
    private Collection<? extends BulletSPI> getBulletProviders() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }

}
