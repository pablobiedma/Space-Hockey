package com.mygdx.game.game_frontend;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;

public class UiOperator {
    public Stage getStage() {
        return stage;
    }

    private Stage stage;
    private Label scoreLabel;

    /**
     * Instantiates UI operator.
     */
    public UiOperator() {
        stage = new Stage();
        fillStage();
        stage.draw();
    }

    /**
     * Fill stage with UI objects.
     */
    public void fillStage() {
        Skin mySkin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        scoreLabel = new Label("0 - 0",mySkin);
        scoreLabel.setSize(100,20);
        scoreLabel.setPosition(450, 200);
        scoreLabel.setFontScale(5);
        scoreLabel.setColor(Color.RED);
        scoreLabel.setAlignment(Align.center);
        stage.addActor(scoreLabel);
    }

    /**
     * Updates score label.
     * @param scoreA score 1.
     * @param scoreB score 2.
     */
    public void updateLabel(int scoreA, int scoreB) {
        scoreLabel.setText(scoreA + " - " + scoreB);
        stage.draw();
    }


}
