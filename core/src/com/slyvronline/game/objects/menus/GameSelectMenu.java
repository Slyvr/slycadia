package com.slyvronline.game.objects.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.controllers.Xbox360Controller;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Img;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.utils.GameConstants;

public class GameSelectMenu extends Menu{

	
	public GameSelectMenu(){
		load();
	}
	
	public void load(){
		this.setName("gameselect");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent bg07_1 = new Ent();
		bg07_1.setName("bg07_1");
		bg07_1.setImg(Game.getGlobal().getImgByName("bg07"));
		bg07_1.setPosBox(new Rectangle(0,
				-100,
				bg07_1.getImg().getTex().getWidth(),
				bg07_1.getImg().getTex().getHeight()));
		ents.add(bg07_1);
		
		
		Ent btnBack = new Ent();
		btnBack.setName("btnBack");
		btnBack.setId(1);
		btnBack.setImg(Game.getGlobal().getImgByName("btnBack"));
		btnBack.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(btnBack.getImg().getTex().getWidth()/2),
				0+15,
				btnBack.getImg().getTex().getWidth(),
				btnBack.getImg().getTex().getHeight()));
		btnBack.setSelected(true);
		ents.add(btnBack);
		
		this.setEnts(ents);
	}
	
	public void update (float stateTime){
		updateKeyboardNavigation();
		updateKeyboardSelect();
	}
	
	public void buttonSelect(){
		Ent selected = this.getSelectedEnt();
		if (selected.getName().equals("btnBack")){
			Game.getGlobal().getSfxByName("papery").play();
			Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
		}
	}
	
	public void buttonDeselect(){
		Game.getGlobal().getSfxByName("papery").play();
		Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
	}
}
