package com.slyvronline.game.objects.menus;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.utils.GameConstants;

public class MainMenu_recycle extends Menu{
	
	private ArrayList<Ent> garbage;
	
	public MainMenu_recycle(){
		load();
		garbage = new ArrayList<Ent>();
	}
	
	public void load(){
		this.setName("main");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		
		Ent bg07_1 = new Ent();
		bg07_1.setName("bg07_1");
		bg07_1.setImg(Game.getGlobal().getImgByName("bg07"));
		bg07_1.setPosBox(new Rectangle(0,
				-100,
				bg07_1.getImg().getTex().getWidth(),
				bg07_1.getImg().getTex().getHeight()));
		ents.add(bg07_1);
		
		
		//ents.addAll(buildChevronBG());
		
		Ent bin = new Ent();
		bin.setName("bin");
		bin.setImg(Game.getGlobal().getImgByName("bin"));
		bin.setPosBox(new Rectangle(256,
				0,
				bin.getImg().getTex().getWidth(),
				bin.getImg().getTex().getHeight()));
		ents.add(bin);
		
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
	
	public void render(SpriteBatch batch){
		for(Ent e : this.getEnts()){
			if (e.getName().contains("bg07"))
				e.render(batch);
		}
		for(Ent e : garbage){
			e.render(batch);
		}
		for(Ent e : this.getEnts()){
			if (!e.getName().contains("bg07"))
				e.render(batch);
		}
	}
	
	public void update(float stateTime){
		updateKeyboardNavigation();
		updateKeyboardSelect();
		cycleChevronBg();
		updateGarbage();
	}
	
	int garbageCounter;
	int garbageCountMax = 150;
	
	public void updateGarbage(){
		Ent bin = this.getEntByName("bin");
		if (garbageCounter == 0) garbageCounter = 140;
		if (garbageCounter > garbageCountMax){
			Ent newGarbage = new Ent();
			newGarbage.setName("newGarbage");
			String garbName = "recycle"+(new Random().nextInt(10)+1);
			System.out.println(garbName);
			newGarbage.setImg(Game.getGlobal().getImgByName(garbName));
			newGarbage.setPosBox(new Rectangle(256+(bin.getWidth()/2)-(newGarbage.getImg().getTex().getWidth()/2),
					Gdx.graphics.getHeight(),
					newGarbage.getImg().getTex().getWidth(),
					newGarbage.getImg().getTex().getHeight()));
			newGarbage.setCenterX((int) (newGarbage.getWidth()/2));
			newGarbage.setCenterY((int) (newGarbage.getHeight()/2));
			newGarbage.setRotate(true);
			garbage.add(newGarbage);
			garbageCounter = 1;
		}
		garbageCounter++;
		
		for (int i=garbage.size()-1; i>0; i--){
			Ent e = garbage.get(i);
			if (e.getY() == bin.getY()+bin.getHeight()/2){
				garbage.remove(i);
			}
			else{
				e.setY(e.getY() - 1);
				e.setRotation(e.getRotation()+1.0f);
			}
		}
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
