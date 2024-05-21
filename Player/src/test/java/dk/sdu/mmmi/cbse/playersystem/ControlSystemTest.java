package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ControlSystemTest {

    private GameData mockedGameData;
    private World mockedWorld;
    private Player player;

    @BeforeEach
    void setUp() {

        mockedWorld = new World();
        mockedGameData = new GameData();
        mockedGameData.setDisplayHeight(500);
        mockedGameData.setDisplayWidth(500);
        player = new Player();
        player.setX(mockedGameData.getDisplayWidth() / 2);
        player.setY(mockedGameData.getDisplayHeight() / 2);
        player.setRotation(0);
        mockedWorld.addEntity(player);



    }

    @Test
    @DisplayName("Move the player")
    void move() {

        double initialPlayerXPosition = player.getX();
        double initialPlayerYPosition = player.getY();

        mockedGameData.getKeys().setKey(GameKeys.UP, true);

        // Theoretically simulates a frame
        new ControlSystem().process(mockedGameData, mockedWorld);

        // Speed should change as a result
        assertTrue(player.speed > 0);

        new ControlSystem().process(mockedGameData, mockedWorld);


        // Player should have moved.
        assertTrue(initialPlayerXPosition != player.getX() || initialPlayerYPosition != player.getY());


    }

    @Test
    @DisplayName("Move and rotate the player")
    void moveAndRotate() {


        double initialPlayerXPosition = player.getX();
        double initialPlayerYPosition = player.getY();
        double initialPlayerRotation = player.getRotation();

        mockedGameData.getKeys().setKey(GameKeys.UP, true);
        mockedGameData.getKeys().setKey(GameKeys.LEFT, true);

        // Theoretically simulates a frame
        new ControlSystem().process(mockedGameData, mockedWorld);

        // Speed should change as a result
        assertTrue(player.speed > 0);

        new ControlSystem().process(mockedGameData, mockedWorld);


        // Player should have moved and rotated.
        assertTrue(initialPlayerXPosition != player.getX() || initialPlayerYPosition != player.getY());
        assertNotEquals(initialPlayerRotation, player.getRotation());
    }




}