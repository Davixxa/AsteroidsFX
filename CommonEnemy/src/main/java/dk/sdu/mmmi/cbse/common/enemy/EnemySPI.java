package dk.sdu.mmmi.cbse.common.enemy;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface EnemySPI {

    void movementBehaviour(GameData gameData, World world);
    void shootingBehaviour(GameData gameData, World world);


}
