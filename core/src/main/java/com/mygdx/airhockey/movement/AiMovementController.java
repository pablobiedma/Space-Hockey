package com.mygdx.airhockey.movement;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.elements.Puck;

public class AiMovementController implements MovementController {
    private transient Puck puck;
    private static Config config = Config.getInstance();
    private transient Vector2 towardsOwnGoal;
    private transient Vector2 towardsPuck;
    private transient Vector2 towardsDefault;

    /**
     * Constructor for movement controller.
     */
    public AiMovementController(Puck puck) {
        this.puck = puck;
    }

    private void updateVectors(Body body) {
        float puckX = puck.getBody().getPosition().x;
        float puckY = puck.getBody().getPosition().y;
        float x = body.getPosition().x;
        float y = body.getPosition().y;
        towardsDefault = new Vector2(config.wallWidth - config.bluePaddleX / 4 - x, -y);
        towardsPuck = new Vector2(puckX - x, puckY - y);
        towardsOwnGoal = new Vector2(config.wallWidth - x, -y);
    }

    /**
     * Updates velocity of the object.
     */
    public void updateVelocity(Body body) {
        updateVectors(body);
        if (MovementController.touchesMiddleLine(body)) {
            body.setTransform(body.getPosition().x + 0.5f, body.getPosition().y, 0);
        }

        if (puck.getBody().getPosition().x < 0) {
            //System.out.println("waiting");
            //if already close to the starting position, then do not move
            double eps = 0.5;
            if (towardsDefault.len() < eps) {
                body.setLinearVelocity(0, 0);
            } else {
                towardsDefault.setLength(config.paddleSpeed);
                body.setLinearVelocity(towardsDefault);
            }
        } else if (puck.getBody().getPosition().x > config.bluePaddleX) {
            //if the puck is close to my own goal move towards own goal,
            // unless the puck is stuck or you are already
            //at the goal
            //System.out.println("defending");

            //if puck does not move in x direction, hit it
            towardsPuck.setLength(config.paddleSpeed);
            double eps = 0.5;
            if (puck.getBody().getLinearVelocity().x < eps) {
                body.setLinearVelocity(towardsPuck);
            } else {
                if (towardsOwnGoal.len() < eps) {
                    body.setLinearVelocity(0, 0);
                } else {
                    towardsOwnGoal.setLength(config.paddleSpeed);
                    body.setLinearVelocity(towardsOwnGoal);
                }
            }
        } else {
            //if the puck is not close to my own goal, but still on my own half,
            // attack - move towards the puck
            //System.out.println("attacking");
            //calculate the vector towards the puck
            towardsPuck.setLength(config.paddleSpeed);
            //don't score own goals - if towards the puck vector
            // does not lead to a hit in the direction of
            // opponents half, then don't move
            if (towardsPuck.x > 0) {
                body.setLinearVelocity(0, 0);
            } else {
                body.setLinearVelocity(towardsPuck);
            }
        }
    }
}
