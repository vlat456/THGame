package com.mygdx.tirehero;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;



public class TireHero extends ApplicationAdapter {
	private Stage stage;
	private OrthographicCamera camera;
	private Viewport viewport;

	private SpriteBatch batch;
	private Texture myTexture;
	private TextureRegion birdSprite;
	private TextureRegion bg;

	private Bird bird;

	@Override
	public void create () {
		Gdx.app.log("Tire Hero", "created");

		camera = new OrthographicCamera();
		//camera.setToOrtho(false, 1000, 1000);


		/* TODO: Sort out this viewport thing, and make tests in Android emulator */
		viewport = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		viewport.apply();

		// Textures
		myTexture = new Texture("texture.png");

		birdSprite = new TextureRegion(myTexture, 153, 0, 17, 12);
		bg = new TextureRegion(myTexture, 0, 0, 136, 43);


		bird = new Bird(33, (int)camera.viewportHeight/2.0f, 32, 24, birdSprite);

		batch = new SpriteBatch();

		stage = new Stage(viewport, batch);

		stage.addListener(new InputListener() {
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				bird.onClick();
				return true;
			}
		});

		stage.addActor(bird);
		Gdx.input.setInputProcessor(stage);

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.disableBlending();
		/* TODO: To inspect, since such approach causes non-uniform scale */
		batch.draw(bg, 0, camera.viewportHeight / 2, camera.viewportWidth, bg.getRegionHeight()*2);
		batch.enableBlending();
		batch.end();

		stage.draw();
		stage.act();
	}

	@Override
	public void dispose() {
		// TODO: Should actor be disposed too?
		stage.dispose();
	}
}
