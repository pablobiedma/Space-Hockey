package com.mygdx.game.game_elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;
import java.util.List;

public class Walls {
    List<PolygonShape> walls;

    /**
     * Constructor for walls class.
     * @param world to create the walls in.
     * @param width of the walls.
     * @param height of the walls.
     * @param thickness of the walls.
     */
    public Walls(World world, float width, float height, float thickness) {
        walls = new ArrayList<>();
        walls.add(initializeWall(world, 0, -height,
                width, thickness));
        walls.add(initializeWall(world, 0, height,
                width, thickness));
        walls.add(initializeWall(world, width, 0,
                thickness, height));
        walls.add(initializeWall(world, -width, 0,
                thickness, height));
    }

    /**
     * Initializes a wall.
     * @param x position of the centre of the wall.
     * @param y position of the centre of the wall.
     * @param width of the wall.
     * @param height of the wall.
     */
    public PolygonShape initializeWall(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);
        Body wallBody  = world.createBody(bodyDef);

        PolygonShape wall = new PolygonShape();
        wall.setAsBox(width, height);
        wallBody.createFixture(wall, 0);
        return wall;
    }
}
