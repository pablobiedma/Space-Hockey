package com.mygdx.airhockey.movement;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.airhockey.backend.Config;

public interface MovementController {
    void updateVelocity(Body body);

    /**
     * Checks if the paddle touches the middle line.
     * @return true if it does, else false.
     */
    static boolean touchesMiddleLine(Body body) {
        Circle c = new Circle();
        c.setPosition(body.getPosition().x, body.getPosition().y);
        Config config = Config.getInstance();
        c.setRadius(config.paddleRadius);
        Rectangle border = new Rectangle(
                0,-config.viewportSize / 2,
                0, config.viewportSize);
        return Intersector.overlaps(c, border);
    }
}
