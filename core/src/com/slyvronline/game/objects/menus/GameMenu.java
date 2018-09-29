package com.slyvronline.game.objects.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.load.LoadLevel;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.GameInstance;
import com.slyvronline.game.objects.Img;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.objects.TwoDAdventureInstance;
import com.slyvronline.game.utils.GameConstants;

public class GameMenu extends Menu{

	
	public GameMenu(){
		load();
	}
	
	public void load(){
		this.setName("game");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		this.setEnts(ents);
	}
	
	public void update (float stateTime){
		if (Game.getGlobal().getGame() == null){
			Game.getGlobal().setGame(new TwoDAdventureInstance());
			LoadLevel.loadAll();
		}
		
		buttonDeselect();
	}
	
	public void buttonDeselect(){
		if (Gdx.input.isKeyJustPressed(GameConstants.KEY_QUIT)){
			Game.getGlobal().getSfxByName("papery").play();
			Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
			Game.getGlobal().setGame(null);
		}
	}
}
