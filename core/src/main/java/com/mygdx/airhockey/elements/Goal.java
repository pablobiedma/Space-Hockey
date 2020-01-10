package com.mygdx.airhockey.elements;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.airhockey.backend.Config;

public class Goal {
    private Rectangle collider;

    /**
     * Creates a goal object.
     * @param x position for the goal.
     * @param y position for the goal.
     */
    public Goal(float x, float y) {
        collider = new Rectangle(
                x,y,
                1, Config.getInstance().wallHeight);
    }

    /**
     * Checks if a puck is in the goal.
     * @param puck to check for.
     * @return true if a goal, else false.
     */
    public boolean checkForGoal(Puck puck) {
        Circle collisionTestCircle = new Circle();
        collisionTestCircle.set(puck.getBody().getPosition().x,
                puck.getBody().getPosition().y,
                Config.getInstance().puckRadius);
        return Intersector.overlaps(collisionTestCircle, collider);
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }
}
