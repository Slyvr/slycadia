package com.slyvronline.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.load.LoadGameboardEnts;
import com.slyvronline.game.objects.ents.Consumable;
import com.slyvronline.game.utils.GameConstants;


/**
 * This class stores the data of a particular game instance, whether new or saved.
 * The galaxy can be used to dig down and find specific locations to travel to.
 * All other location variables are used to store which places the player in this
 * instance is currently located in.
 * 
 * The currentCollection determines the specific place that should currently be rendered
 * to the screen.
 * @author Matt Schrum - slyvr89
 * @date 10/25/2013
 */
public class BlackHoleInstance extends GameInstance {

	private boolean paused;
	private Ent gameboard;
	private Ent blackhole;
	private float moveSpeed;
	private int blackholeSize;
	private int score;
	private ArrayList<Consumable> ents;
	private long startMillis;
	private int scaleUpTimer;
	private float camZoom;
	
	public BlackHoleInstance(){
		startMillis = System.currentTimeMillis();
		blackholeSize = 1;
		score = 0;
		gameboard = new Ent();
		gameboard.setName("gameboard");
		gameboard.setImg(Game.getGlobal().getImgByName("gameboard"));
		gameboard.setPosBox(new Rectangle(0,0,
				gameboard.getImg().getTex().getWidth(),
				gameboard.getImg().getTex().getHeight()));
		//1296*10 real gameboard width
		
		blackhole = new Ent();
		blackhole.setName("blackhole");
		blackhole.setImg(Game.getGlobal().getImgByName("blackhole-icon"));
		blackhole.setPosBox(new Rectangle(
				gameboard.getImg().getTex().getWidth()/2,
				gameboard.getImg().getTex().getHeight()/2,
				blackhole.getImg().getTex().getWidth()/6,
				blackhole.getImg().getTex().getHeight()/6));
		blackhole.setFlipX(true);
		
		ents = LoadGameboardEnts.loadEnts();
		
		Game.getGlobal().getCamera().position.x = blackhole.getPosBox().getX() - (blackhole.getWidth()/2);
		Game.getGlobal().getCamera().position.y = blackhole.getPosBox().getY() - (blackhole.getHeight()/2);
		Game.getGlobal().getCamera().zoom = 0.5f;
		
		moveSpeed = 2.0f;
	}

	public void render(SpriteBatch batch){
		gameboard.render(batch);
		
		blackhole.render(batch);
		
		for(Consumable ent : ents){
			ent.render(batch);
		}
	}
	
	int count=0;
	
	public void update(){
		if (!this.isPaused()){
			//Constantly rotate blackhole
			blackhole.setRotation(blackhole.getRotation() + 1.0f);
			
			updateMove();
			updateConsume();
			
			//Update entity animation
			for(Consumable ent : ents){
				ent.animate();
			}
			
			//Update destroy entities
			for(int i=ents.size()-1; i>0; i--){
				Consumable ent = ents.get(i);
				if (ent.isDestroy()){
					ents.remove(i);
				}
			}
			
			//Update scaleUp turn off
			if (scaleUpTimer > 0){
				scaleUpTimer--;
			}
			else{
				Game.getGlobal().getCurrentMenu().getEntByName("scaleup").setDisplay(false);
			}
			
			//Update timer
			Ent timer = Game.getGlobal().getCurrentMenu().getEntByName("timer");
			long timeSpent = System.currentTimeMillis() - startMillis;
			timer.setText("Timer: "+(GameConstants.MAX_TIMER-(timeSpent/1000)));
			
			if (timeSpent/1000 >= GameConstants.MAX_TIMER){
				this.setPaused(true);
			}
			
			//Update camera zoom
			if (Game.getGlobal().getCamera().zoom < camZoom){
				Game.getGlobal().getCamera().zoom = Game.getGlobal().getCamera().zoom + 0.001f;
				//Stop movement outside of gameboard
				float effectiveViewportWidth = Game.getGlobal().getCamera().viewportWidth * Game.getGlobal().getCamera().zoom;
				float effectiveViewportHeight = Game.getGlobal().getCamera().viewportHeight * Game.getGlobal().getCamera().zoom;
				if (Game.getGlobal().getCamera().position.x < gameboard.getX()+(effectiveViewportWidth/2)){
					Game.getGlobal().getCamera().position.x = gameboard.getX()+(effectiveViewportWidth/2);
				}
				if (Game.getGlobal().getCamera().position.x > gameboard.getWidth()-(effectiveViewportWidth/2)){
					Game.getGlobal().getCamera().position.x = gameboard.getWidth()-(effectiveViewportWidth/2);
				}
				if (Game.getGlobal().getCamera().position.y < gameboard.getY()+(effectiveViewportHeight/2)){
					Game.getGlobal().getCamera().position.y = gameboard.getY()+(effectiveViewportHeight/2);
				}
				if (Game.getGlobal().getCamera().position.y > gameboard.getHeight()-(effectiveViewportHeight/2)){
					Game.getGlobal().getCamera().position.y = gameboard.getHeight()-(effectiveViewportHeight/2);
				}
			}
		}
	}
	
	public void updateMove(){
		OrthographicCamera cam = Game.getGlobal().getCamera();
		if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_DOWN) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_DOWN)){
			cam.position.y = cam.position.y - moveSpeed;
			if (blackhole != null){
				blackhole.setY(blackhole.getY() - moveSpeed);
				if (blackhole.getY() <= gameboard.getY()){
					blackhole.setY(blackhole.getY() + moveSpeed);
				}
				if (blackhole.getY() > cam.position.y){
					cam.position.y = cam.position.y + moveSpeed;
				}
			}
		}
		if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_UP) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_UP)){
			cam.position.y = cam.position.y + moveSpeed;
			if (blackhole != null){
				blackhole.setY(blackhole.getY() + moveSpeed);
				if (blackhole.getY() >= gameboard.getHeight() - blackhole.getHeight()){
					blackhole.setY(blackhole.getY() - moveSpeed);
				}
				if (blackhole.getY() < cam.position.y){
					cam.position.y = cam.position.y - moveSpeed;
				}
			}
		}
		if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_LEFT) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_LEFT)){
			cam.position.x = cam.position.x - moveSpeed;
			if (blackhole != null){
				blackhole.setX(blackhole.getX() - moveSpeed);
				if (blackhole.getX() <= gameboard.getX()){
					blackhole.setX(blackhole.getX() + moveSpeed);
				}
				if (blackhole.getX() > cam.position.x){
					cam.position.x = cam.position.x + moveSpeed;
				}
			}
		}
		if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_RIGHT) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_RIGHT)){
			cam.position.x = cam.position.x + moveSpeed;
			if (blackhole != null){
				blackhole.setX(blackhole.getX() + moveSpeed);
				if (blackhole.getX() >= gameboard.getWidth() - blackhole.getWidth()){
					blackhole.setX(blackhole.getX() - moveSpeed);
				}
				if (blackhole.getX() < cam.position.x){
					cam.position.x = cam.position.x - moveSpeed;
				}
			}
		}
		
		//Stop movement outside of gameboard
		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
		if (cam.position.x < gameboard.getX()+(effectiveViewportWidth/2)){
			cam.position.x = gameboard.getX()+(effectiveViewportWidth/2);
		}
		if (cam.position.x > gameboard.getWidth()-(effectiveViewportWidth/2)){
			cam.position.x = gameboard.getWidth()-(effectiveViewportWidth/2);
		}
		if (cam.position.y < gameboard.getY()+(effectiveViewportHeight/2)){
			cam.position.y = gameboard.getY()+(effectiveViewportHeight/2);
		}
		if (cam.position.y > gameboard.getHeight()-(effectiveViewportHeight/2)){
			cam.position.y = gameboard.getHeight()-(effectiveViewportHeight/2);
		}
	}
	
	public void updateConsume(){
		for(Consumable ent : ents){
			if (blackhole.getPosBox().overlaps(ent.getPosBox()) && blackholeSize >= ent.getSize() && !ent.isConsumed()){
				ent.setConsumed(true);
				score += ent.getSize();
				Ent eScore = Game.getGlobal().getCurrentMenu().getEntByName("score");
				eScore.setText("Score: "+score);
			}
			else if (blackhole.getPosBox().overlaps(ent.getPosBox()) && !ent.isConsumed()){
				ent.setShakeLeft(true);
				ent.setShakeRight(true);
			}
		}
		
		if (score >= 235){
			if (blackholeSize != 6) scaleUpTimer = 50;
			blackholeSize = 6;
			blackhole.setWidth(blackhole.getWidth() + 0.2f);
			blackhole.setHeight(blackhole.getHeight() + 0.2f);
			blackhole.setCenterX((int) (blackhole.getWidth()/2));
			blackhole.setCenterY((int) (blackhole.getHeight()/2));
			Game.getGlobal().getCurrentMenu().getEntByName("scaleup").setDisplay(true);
			moveSpeed = 1.0f;
			camZoom = 1.2f;
		}
		else if (score >= 180){
			if (blackholeSize != 5) scaleUpTimer = 50;
			blackholeSize = 5;
			blackhole.setWidth(blackhole.getWidth() + 0.2f);
			blackhole.setHeight(blackhole.getHeight() + 0.2f);
			blackhole.setCenterX((int) (blackhole.getWidth()/2));
			blackhole.setCenterY((int) (blackhole.getHeight()/2));
			Game.getGlobal().getCurrentMenu().getEntByName("scaleup").setDisplay(true);
			moveSpeed = 1.0f;
			camZoom = 1.2f;
		}
		else if (score >= 100){
			if (blackholeSize != 4) scaleUpTimer = 50;
			blackholeSize = 4;
			blackhole.setWidth(blackhole.getWidth() + 0.2f);
			blackhole.setHeight(blackhole.getHeight() + 0.2f);
			blackhole.setCenterX((int) (blackhole.getWidth()/2));
			blackhole.setCenterY((int) (blackhole.getHeight()/2));
			Game.getGlobal().getCurrentMenu().getEntByName("scaleup").setDisplay(true);
			moveSpeed = 1.2f;
			camZoom = 1.2f;
		}
		else if (score >= 40){
			if (blackholeSize != 3) scaleUpTimer = 50;
			blackholeSize = 3;
			blackhole.setWidth(blackhole.getWidth() + 0.2f);
			blackhole.setHeight(blackhole.getHeight() + 0.2f);
			blackhole.setCenterX((int) (blackhole.getWidth()/2));
			blackhole.setCenterY((int) (blackhole.getHeight()/2));
			Game.getGlobal().getCurrentMenu().getEntByName("scaleup").setDisplay(true);
			moveSpeed = 1.5f;
			camZoom = 0.9f;
		}
		else if (score >= 20){
			if (blackholeSize != 2) scaleUpTimer = 50;
			blackholeSize = 2;
			blackhole.setWidth(blackhole.getWidth() + 0.1f);
			blackhole.setHeight(blackhole.getHeight() + 0.1f);
			blackhole.setCenterX((int) (blackhole.getWidth()/2));
			blackhole.setCenterY((int) (blackhole.getHeight()/2));
			Game.getGlobal().getCurrentMenu().getEntByName("scaleup").setDisplay(true);
			moveSpeed = 1.9f;
			camZoom = 0.7f;
		}
	}

	public boolean isPaused() {
		return paused;
	}
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
