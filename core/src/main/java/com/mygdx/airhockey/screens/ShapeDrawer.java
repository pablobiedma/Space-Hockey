package com.mygdx.airhockey.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.airhockey.backend.Config;
import com.mygdx.airhockey.backend.CoordinateTranslator;
import com.mygdx.airhockey.backend.GameOperator;

public class ShapeDrawer {
    private static Config config = Config.getInstance();
    private static final Sprite leftPaddleSprite =
            new Sprite(new Texture(config.redPaddleTexturePath));
    private static final Sprite rightPaddleSprite =
            new Sprite(new Texture(config.bluePaddleTexturePath));
    private static final Sprite puckSprite = new Sprite(new Texture(config.puckTexturePath));

    private transient ShapeRenderer shapeRenderer;
    private transient Batch batch;

    /**
     * Draws shapes.
     * @param shapeRenderer renderer to use.
     * @param batch batch to use.
     */
    public ShapeDrawer(ShapeRenderer shapeRenderer, Batch batch) {
        this.shapeRenderer = shapeRenderer;
        this.batch = batch;

        int paddleWidth = (int) CoordinateTranslator.translateSize(2 * config.paddleRadius);
        leftPaddleSprite.setSize(paddleWidth, paddleWidth);
        rightPaddleSprite.setSize(paddleWidth, paddleWidth);

        int puckWidth = (int) CoordinateTranslator.translateSize(2 * config.puckRadius);
        puckSprite.setSize(puckWidth, puckWidth);
    }

    /**
     * Draws the pitch.
     */
    public void drawPitch() {
        Vector2 bottomLeftPitch = CoordinateTranslator.translatePosition(
                new Vector2(-config.wallWidth, -config.wallHeight));
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.GOLD);
        shapeRenderer.rect(bottomLeftPitch.x, bottomLeftPitch.y,
                2 * CoordinateTranslator.translateSize(config.wallWidth),
                2 * CoordinateTranslator.translateSize(config.wallHeight));
        Vector2 middleLineStart =
                CoordinateTranslator.translatePosition(new Vector2(0, config.wallHeight));
        Vector2 middleLineEnd =
                CoordinateTranslator.translatePosition(new Vector2(0, -config.wallHeight));
        shapeRenderer.line(middleLineStart.x, middleLineStart.y, middleLineEnd.x, middleLineEnd.y);
        shapeRenderer.circle(config.resolution / 2, config.resolution / 2,
                CoordinateTranslator.translateSize(config.wallHeight) / 4);

        shapeRenderer.setColor(Color.WHITE);
        Vector2 leftGoal = CoordinateTranslator.translatePosition(
                new Vector2(-config.wallWidth - config.goalDepth, -config.goalWidth));
        Vector2 rightGoal = CoordinateTranslator.translatePosition(
                new Vector2(config.wallWidth, -config.goalWidth));
        shapeRenderer.rect(leftGoal.x, leftGoal.y,
                CoordinateTranslator.translateSize(config.goalDepth),
                CoordinateTranslator.translateSize(2 * config.goalWidth));
        shapeRenderer.rect(rightGoal.x, rightGoal.y,
                CoordinateTranslator.translateSize(config.goalDepth),
                CoordinateTranslator.translateSize(2 * config.goalWidth));
        shapeRenderer.end();
    }

    private void drawPlanet(Sprite sprite, float radius, Vector2 position) {
        int width = (int) CoordinateTranslator.translateSize(2 * radius);
        Vector2 spritePos = CoordinateTranslator.translatePosition(position);
        sprite.setPosition(spritePos.x - width / 2, spritePos.y - width / 2);
        sprite.draw(batch);
    }

    /**
     * Draws paddles.
     */
    public void drawElements(GameOperator gameOperator) {
        batch.begin();
        drawPlanet(leftPaddleSprite, config.paddleRadius,
                gameOperator.getControllerManager().getRedPaddle().getBody().getPosition());
        drawPlanet(rightPaddleSprite, config.paddleRadius,
                gameOperator.getControllerManager().getBluePaddle().getBody().getPosition());
        drawPlanet(puckSprite, config.puckRadius,
                gameOperator.getControllerManager().getPuck().getBody().getPosition());
        batch.end();
    }


}
