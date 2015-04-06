package com.mygdx.tirehero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.Vector;

/**
 * Created by Vladimir on 4/6/2015.
 */
public class Bird extends Actor {
    private TextureRegion region;
    private Vector2 position;
    private Vector2 acceleration;
    private Vector2 velocity;

    public Bird(float x, float y, int width, int height, TextureRegion region)
    {
        setSize(width, height);
        setOrigin(width / 2, height / 2);
        setPosition(x, y);
        setRotation(0); // TODO: Implement rotation

        this.region = region;

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, -460); // TODO: Declare as constant
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation());
    }

    @Override
    public void act(float delta) {
        // Speed
        velocity.add(acceleration.cpy().scl(delta)); // velocity + acceleration * delta
      //  velocity.y = MathUtils.clamp(velocity.y, 0, 200); // TODO: Doesn't work

        // Position
        // TODO: Probably optimmize. Instead of cpy().scl() use *delta
        position.add(velocity.cpy().scl(delta)); // position + velocity * delta
        setX(position.x);
        setY(position.y);
    }

    public void onClick() {
        Gdx.app.log("TireHero", "clicked");
        velocity.y = 140; // TODO: Make constant too
    }
}
