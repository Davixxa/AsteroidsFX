package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Contains all the logic happening each frame for the given module. Equivalent to void Update() in Unity or _process() in Godot.
     * The pre-condition for this must be that the game state has been initialised.
     * Post-condition: The state of the game should be unchanged other than applicable entities being altered.
     * @param gameData
     * @param world
     * @throws
     */
    void process(GameData gameData, World world);
}
