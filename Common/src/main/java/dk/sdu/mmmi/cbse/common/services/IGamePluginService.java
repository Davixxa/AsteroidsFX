package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;



public interface IGamePluginService {


    /**
     * This method is called upon plugin load.
     * Pre-condition: The plugin must be unloaded.
     * Post-condition: The gameData object should be unchanged.
     * @param gameData
     * @param world
     */
    void start(GameData gameData, World world);

    /**
     * This method is called upon plugin unload.
     * Pre-condition: The plugin must be loaded
     * Post-condition: The plugin must have unloaded all its resources, and must no longer affect the state of the game.
     * @param gameData
     * @param world
     */
    void stop(GameData gameData, World world);
}
