package com.slyvronline.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.slyvronline.game.Game;
import com.slyvronline.game.load.LoadLevel;
import com.slyvronline.game.load.LoadMenus;
import com.slyvronline.game.objects.tiles.Tile;
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
public class TwoDAdventureInstance extends GameInstance {

	private ArrayList<Level> levels;
	private Level currentLevel;
	
	public TwoDAdventureInstance(){
		
	}

	public void render(SpriteBatch batch){
		for(Tile t : currentLevel.getTiles0()){
			if (t != null){
				t.render(batch);
			}
		}
	}
	
	public void update(){
		
	}

	public ArrayList<Level> getLevels() {
		return levels;
	}
	public void setLevels(ArrayList<Level> levels) {
		this.levels = levels;
	}
	public Level getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}
}
