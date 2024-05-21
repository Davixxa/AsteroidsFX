package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author jcs
 */
public interface IPostEntityProcessingService {
    /**
     *
     *
     * Contains all the logic happening each frame for the given module after entities have been processed.
     * This would be used for physics and collision detection.
     * The pre-condition for this must be that the game state has been initialised.
     * Post-condition: The state of the game should be in working order
     *
     * @param gameData
     * @param world
     */
    void process(GameData gameData, World world);
}
