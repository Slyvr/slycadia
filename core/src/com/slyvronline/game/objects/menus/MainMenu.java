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

public class MainMenu extends Menu{
	
	public MainMenu(){
		load();
	}
	
	public void load(){
		this.setName("main");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		/*
		Ent bg07_1 = new Ent();
		bg07_1.setName("bg07_1");
		bg07_1.setImg(Game.getGlobal().getImgByName("bg07"));
		bg07_1.setPosBox(new Rectangle(0,
				-100,
				bg07_1.getImg().getTex().getWidth(),
				bg07_1.getImg().getTex().getHeight()));
		ents.add(bg07_1);
		*/
		
		ents.addAll(buildChevronBG());
		
		Ent logoTitle = new Ent();
		logoTitle.setName("logoTitle");
		logoTitle.setImg(Game.getGlobal().getImgByName("logoTitle"));
		logoTitle.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(logoTitle.getImg().getTex().getWidth()/2)),
				400,
				logoTitle.getImg().getTex().getWidth(),
				logoTitle.getImg().getTex().getHeight()));
		ents.add(logoTitle);
		
		Ent btnStart = new Ent();
		btnStart.setName("btnStart");
		btnStart.setId(1);
		btnStart.setImg(Game.getGlobal().getImgByName("btnStart"));
		btnStart.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(btnStart.getImg().getTex().getWidth()/2)),
				logoTitle.getY()-btnStart.getImg().getTex().getHeight()-3,
				btnStart.getImg().getTex().getWidth(),
				btnStart.getImg().getTex().getHeight()));
		btnStart.setSelected(true);
		ents.add(btnStart);
		
		Ent btnCredits = new Ent();
		btnCredits.setName("btnCredits");
		btnCredits.setId(2);
		btnCredits.setImg(Game.getGlobal().getImgByName("btnCredits"));
		btnCredits.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(btnCredits.getImg().getTex().getWidth()/2)),
				btnStart.getY()-btnCredits.getImg().getTex().getHeight()-3,
				btnCredits.getImg().getTex().getWidth(),
				btnCredits.getImg().getTex().getHeight()));
		ents.add(btnCredits);
		
		Ent btnExit = new Ent();
		btnExit.setName("btnExit");
		btnExit.setId(3);
		btnExit.setImg(Game.getGlobal().getImgByName("btnExit"));
		btnExit.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(btnExit.getImg().getTex().getWidth()/2)),
				btnCredits.getY()-btnExit.getImg().getTex().getHeight()-3,
				btnExit.getImg().getTex().getWidth(),
				btnExit.getImg().getTex().getHeight()));
		ents.add(btnExit);
		
		this.setEnts(ents);
	}
	
	public void update(float stateTime){
		updateKeyboardNavigation();
		updateKeyboardSelect();
		cycleChevronBg();
	}
	
	public void buttonSelect(){
		Ent selected = this.getSelectedEnt();
		if (selected.getName().equals("btnStart")){
			Game.getGlobal().getSfxByName("papery").play();
			Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("game"));
		}
		if (selected.getName().equals("btnCredits")){
			Game.getGlobal().getSfxByName("papery").play();
			Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("credits"));
		}
		if (selected.getName().equals("btnExit")){
			Gdx.app.exit();
		}
	}
	
	public void buttonDeselect(){
		if (Gdx.input.isKeyJustPressed(GameConstants.KEY_QUIT)){
			Gdx.app.exit();
		}
	}
	
	public static double angleBetween2Lines(int x11, int y11, int x12, int y12, int x21, int y21, int x22, int y22){
        double angle1 = Math.atan2(y11 - y12,
                                   x11 - x12);
        double angle2 = Math.atan2(y21 - y22,
                                   x21 - x22);
        return angle1-angle2;
    }
}
