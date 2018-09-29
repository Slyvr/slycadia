package com.slyvronline.game.objects.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.load.LoadLevel;
import com.slyvronline.game.objects.BlackHoleInstance;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.objects.RecycleGameInstance;
import com.slyvronline.game.utils.GameConstants;

public class GameMenu extends Menu{

	
	public GameMenu(){
		load();
	}
	
	public void load(){
		this.setName("game");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		//ents.addAll(buildChevronBG());
		
		Ent timer = new Ent();
		timer.setName("timer");
		timer.setFont(Game.getGlobal().getFontByName("AgencyFbGlow32"));
		timer.setText("Timer: "+GameConstants.MAX_TIMER);
		timer.setPosBox(new Rectangle(100,Gdx.graphics.getHeight() - 100,0,0));
		ents.add(timer);
		
		Ent score = new Ent();
		score.setName("score");
		score.setFont(Game.getGlobal().getFontByName("AgencyFbGlow32"));
		score.setText("Score: 0");
		score.setPosBox(new Rectangle(100,Gdx.graphics.getHeight() - 150,0,0));
		ents.add(score);
		
		Ent timeup = new Ent();
		timeup.setName("timeup");
		timeup.setImg(Game.getGlobal().getImgByName("logoTimeUp"));
		timeup.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(timeup.getImg().getTex().getWidth()/2)),
				400,
				timeup.getImg().getTex().getWidth(),
				timeup.getImg().getTex().getHeight()));
		timeup.setDisplay(false);
		ents.add(timeup);
		
		Ent scaleup = new Ent();
		scaleup.setName("scaleup");
		scaleup.setImg(Game.getGlobal().getImgByName("logoScaledUp"));
		scaleup.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(scaleup.getImg().getTex().getWidth()/2)),
				400,
				scaleup.getImg().getTex().getWidth(),
				scaleup.getImg().getTex().getHeight()));
		scaleup.setDisplay(false);
		ents.add(scaleup);
		
		this.setEnts(ents);
	}
	
	public void update (float stateTime){
		if (Game.getGlobal().getGame() == null){
			Game.getGlobal().setGame(new BlackHoleInstance());
		}
		
		//cycleChevronBg();
		buttonDeselect();
	}
	
	public void buttonDeselect(){
		if (Gdx.input.isKeyJustPressed(GameConstants.KEY_QUIT)){
			this.getEntByName("scaleup").setDisplay(false);
			this.getEntByName("timeup").setDisplay(false);
			this.getEntByName("score").setText("Score: 0");
			this.getEntByName("timer").setText("Timer: "+GameConstants.MAX_TIMER);
			Game.getGlobal().getSfxByName("papery").play();
			Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
			Game.getGlobal().setGame(null);
		}
	}
}
