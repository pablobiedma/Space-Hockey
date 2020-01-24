package com.mygdx.airhockey.backend.managers;

import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.elements.Goal;
import com.mygdx.airhockey.statistics.Level;

public class GoalManager {
    private static Config config = Config.getInstance();
    Goal goalLeft;
    Goal goalRight;
    public transient boolean isGoalScored;

    public GoalManager() {
        this.goalLeft = new Goal(- config.wallWidth - config.goalDepth, -config.goalWidth);
        this.goalRight = new Goal(config.wallWidth + config.goalDepth - 1, -config.goalWidth);
    }

    public GoalManager(Goal goalLeft, Goal goalRight) {
        this.goalLeft = goalLeft;
        this.goalRight = goalRight;
    }

    /**
     * Updates the physics after a goal is scored.
     * @param controllerManager the controllermanager to use.
     * @param level the level to use.
     */
    public void updateGoalPhysics(ControllerManager controllerManager, Level level) {
        if (goalLeft.checkForGoal(controllerManager.getPuck())) {
            level.goalRight();
            controllerManager.resetPositions();
            isGoalScored = true;
            System.out.println(level.getScore());

        } else if (goalRight.checkForGoal(controllerManager.getPuck())) {
            level.goalLeft();
            controllerManager.resetPositions();
            isGoalScored = true;
            System.out.println(level.getScore());
        }
    }

    public Goal getGoalLeft() {
        return goalLeft;
    }

    public void setGoalLeft(Goal goalLeft) {
        this.goalLeft = goalLeft;
    }

    public Goal getGoalRight() {
        return goalRight;
    }

    public void setGoalRight(Goal goalRight) {
        this.goalRight = goalRight;
    }
}
