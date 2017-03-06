package com.lba.stars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Stars extends ApplicationAdapter {

	ShapeRenderer myRenderer;
	Random myRandom;
	Array<Vector3> stars;
	int screenWidth, screenHeight, x, xc, y, yc, numStars, redVal, greenVal, blueVal, fullColor;
	float density = 0.01f;
	float redF, greenF, blueF, x0, y0;

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

		xc = screenWidth / 2;
		yc = screenHeight / 2;

		myRenderer.begin(ShapeRenderer.ShapeType.Line);
		for (Vector3 star : stars){
			fullColor = (int) star.z;

			redVal = fullColor / 1000000;
			greenVal = (fullColor / 1000) % 1000;
			blueVal = fullColor % 1000;

			redF = redVal / (255f);
			greenF = greenVal / (255f);
			blueF = blueVal / (255f);

			myRenderer.setColor(redF, greenF, blueF, 1);
			myRenderer.rect(star.x, star.y, 1,0);

			x0 =  star.x - xc;
			x0*=1.005;
			star.x = x0+xc;

			y0 =  star.y - yc;
			y0*=1.005;
			star.y = y0+yc;

			if(star.x > screenWidth || star.y > screenHeight || star.x < 0 || star.y <0) {
				star.x = (myRandom.nextInt(screenWidth)+xc)/2;
				star.y = (myRandom.nextInt(screenHeight)+yc)/2;
			}
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

		stars = new Array<Vector3>(numStars);

		for (int i = 0; i < numStars; i++){
			x = myRandom.nextInt(screenWidth);
			y = myRandom.nextInt(screenHeight);

			redVal = myRandom.nextInt(256);
			greenVal = myRandom.nextInt(256);
			blueVal = myRandom.nextInt(256);
			fullColor = redVal * 1000000 + greenVal * 1000 + blueVal;

			stars.add(new Vector3(x, y, fullColor));
		}
	}
}
