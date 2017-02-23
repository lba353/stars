package com.lba.stars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Stars extends ApplicationAdapter {

	ShapeRenderer myRenderer;
	Random myRandom;
	Array<Vector2> stars;
	int screenWidth, screenHeight, x, y, numStars;
	float density = 0.01f;

	@Override
	public void create () {
		myRenderer = new ShapeRenderer();
		myRandom = new Random();
		starPoint();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		myRenderer.begin(ShapeRenderer.ShapeType.Line);
		for (Vector2 star : stars){
			myRenderer.rect(star.x, star.y, 1,0);
		}
		myRenderer.end();
	}

	@Override
	public void dispose () {
		myRenderer.dispose();
	}

	@Override
	public void resize(int width, int height) {
		starPoint();
	}

	public void starPoint(){
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		numStars = (int) (screenHeight * screenWidth * density);

		stars = new Array<Vector2>(numStars);

		for (int i = 0; i < numStars; i++){
			x = myRandom.nextInt(screenWidth);
			y = myRandom.nextInt(screenHeight);
			stars.add(new Vector2(x, y));
		}
	}
}
