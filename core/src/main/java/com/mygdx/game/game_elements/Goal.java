package com.mygdx.game.game_elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Goal {
    private int score;

    /**
     * Sets score to 0
     */
    public Goal() {
        score = 0;
    }

    /**
     * Initializes a wall.
     * @param x position of the centre of the wall.
     * @param y position of the centre of the wall.
     * @param width of the wall.
     * @param height of the wall.
     */
    public PolygonShape initializeGoal(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        Body wallBody  = world.createBody(bodyDef);

        PolygonShape wall = new PolygonShape();
        wall.setAsBox(width, height);
        wallBody.createFixture(wall, 0);
        return wall;
    }

    /**
     * Sets score to score
     * @param score score to set score to
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Gets score
     * @return score
     */
    public int getScore() {
        return score;
    }
}
