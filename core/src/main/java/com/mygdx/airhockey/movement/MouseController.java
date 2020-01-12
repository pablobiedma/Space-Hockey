//package com.mygdx.airhockey.movement;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.Body;
//import com.mygdx.airhockey.backend.Config;
//
//public class MouseController implements MovementController {
//    @Override
//    public void updateVelocity(Body body) {
//        Config config = Config.getInstance();
//        if (MovementController.touchesMiddleLine(body)) {
//            if (body.getPosition().x > 0) {
//                body.setLinearVelocity(config.paddleSpeed, 0);
//            } else {
//                body.setLinearVelocity(-config.paddleSpeed, 0);
//            }
//
//        } else {
//
//            float x = (Gdx.input.getX() / config.resolution
//                    * config.viewportSize - config.viewportSize / 2) - body.getPosition().x;
//            float y = ((config.resolution - Gdx.input.getY()) / config.resolution
//                    * config.viewportSize - config.viewportSize / 2) - body.getPosition().y;
////            body.setTransform(x,y,0);
//            System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
//            System.out.println(x + " " + y);
//            Vector2 towardsTheCursor = new Vector2(x, y);
//            if(towardsTheCursor.len() > 0.01) {
//                towardsTheCursor.setLength(2 * config.paddleSpeed);
//                body.setLinearVelocity(towardsTheCursor);
//            }
//        }
//    }
//}
