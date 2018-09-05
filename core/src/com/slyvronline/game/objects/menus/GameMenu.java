package com.slyvronline.game.objects.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Img;
import com.slyvronline.game.objects.Menu;
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
		updateButtons();
		updateButtonHover();
	}
	
	public void updateButtons(){
		if (Gdx.input.justTouched()){
			Rectangle mousePos = new Rectangle(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY(),1,1);
			for(Ent e : this.getEnts()){
				Rectangle ePos = new Rectangle(e.getPosBox());
				ePos.setX(ePos.getX()+Game.getGlobal().getViewport().x);
				ePos.setY(ePos.getY()+Game.getGlobal().getViewport().y);
				if (e.getName().contains("btn") && e.isDisplay()){
					if (mousePos.overlaps(ePos)){
						
					}
				}
			}
		}
	}
}
