package com.mygdx.tirehero;

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
    private float speed = -100;
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
        super.act(delta);
        position.x += speed * delta;

        // TODO: Right now works with only right-to-left direction
        if (position.x + width < 0) {
            position.x = position.x + 2 * width;
        }

        if (endless) {
            position2.x += speed * delta;
            if (position2.x + width < 0) {
                position2.x = position2.x + 2 * width;
            }
        }
    }

}