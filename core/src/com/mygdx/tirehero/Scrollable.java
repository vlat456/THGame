package com.mygdx.tirehero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by Vladimir on 08.04.2015.
 */
public class Scrollable extends Actor {

    private Vector2 position;
    private float speed;
    private TextureRegion region;
    private float width = 1;
    private float height = 1;
    private boolean endless = false;

    private Vector2 position2; // position for 2nd chunk if endless;

    public Scrollable(TextureRegion region, Vector2 position, float width, float height, float speed, boolean endless) {
        this.region = region;
        this.position = position;
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.endless = endless;

        if (endless) {
            position2 = new Vector2(width, position.y);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, position.x, position.y, width, height);
        if (endless) {
            batch.draw(region, position2.x, position2.y, width, height);
        }
    }

    @Override
    public void act(float delta) {
       // super.act(delta);
        position.x += speed * delta;
        // Note! Assuming texture width = camera.viewport width!
        if (endless) {
            /* Right-to-left */
            if (speed < 0) {
                if (position.x + width < 0) {
                    position.x = position.x + 2 * width;
                }

                if (position2.x + width < 0) {
                    position2.x = position2.x + 2 * width;
                }
                position2.x += speed * delta;

            }
            /* Left-to-right */
            else if (speed > 0) {
                if (position.x > width) {
                    position.x = 0;
                }
                if (position2.x > 0) {      // if pos2 is right offscreen;
                    position2.x = -width;   // move it to left with with
                }
                position2.x += speed * delta;
            }
        }
    }

}