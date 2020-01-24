package com.mygdx.airhockey.backend;

import static com.mygdx.airhockey.backend.generators.BodyGenerator.makePitch;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.airhockey.backend.managers.ControllerManager;
import com.mygdx.airhockey.backend.managers.GoalManager;
import com.mygdx.airhockey.elements.Goal;
import com.mygdx.airhockey.elements.Paddle;
import com.mygdx.airhockey.elements.Pitch;
import com.mygdx.airhockey.elements.Puck;
import com.mygdx.airhockey.movement.AiMovementController;
import com.mygdx.airhockey.movement.KeyboardController;
import com.mygdx.airhockey.movement.MovementController;
import com.mygdx.airhockey.statistics.Level;
import com.mygdx.airhockey.statistics.Player;


/**
 * Class that handles the backend of the com.mygdx.airhockey.game.
 */
public class GameOperator {
    private static Config config = Config.getInstance();
    private transient Pitch pitch;
    private transient ControllerManager controllerManager;
    private transient GoalManager goalManager;
    public transient  Level level;
    public transient boolean isGoalScored;

    /**
     * Constructor for game operator.
     *
     * @param pitch      pitch for the game.
     * @param redPaddle  in the game.
     * @param bluePaddle in the game.
     * @param puck       in the game.
     * @param goalLeft left goal.
     * @param goalRight right goal.
     */
    public GameOperator(Pitch pitch, Paddle redPaddle, Paddle bluePaddle, Puck puck,
                        Goal goalLeft, Goal goalRight) {
        this.pitch = pitch;
        controllerManager = new ControllerManager(redPaddle, bluePaddle, puck);
        goalManager = new GoalManager(goalLeft, goalRight);
    }

    /**
     * Set's up a new game.
     */
    public GameOperator(World world, Player player, boolean multiplayer) {
        this.level = new Level(player);
        controllerManager = new ControllerManager(world, multiplayer);
        this.pitch = makePitch(world);
        MovementController opponentController = new AiMovementController(
                controllerManager.getPuck());
        if (multiplayer) {
            opponentController = new KeyboardController(config.bluePaddleKeys, Gdx.input);
        }
        goalManager = new GoalManager();
    }

    /**
     * Updates physics of the game.
     */
    public void updatePhysics() {
        goalManager.updateGoalPhysics(controllerManager, level);

        controllerManager.updatePositions();
    }

    /**
     * Checks if the game is finished by
     * comparing scores.
     * @return if game is finished.
     */
    public boolean isFinished() {
        return level.isFinished();
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public ControllerManager getControllerManager() {
        return controllerManager;
    }

    public GoalManager getGoalManager() {
        return goalManager;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
