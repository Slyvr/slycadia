package com.slyvronline.game.objects.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;

public class MainMenu extends Menu{
	
	public MainMenu(){
		load();
	}
	
	public void load(){
		this.setName("main");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent btnNewGame = new Ent();
		btnNewGame.setName("btnNewGame");
		btnNewGame.setImg(Game.getGlobal().getImgByName("btnNewGame"));
		btnNewGame.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(btnNewGame.getImg().getTex().getWidth()/2)),
				500,
				btnNewGame.getImg().getTex().getWidth(),
				btnNewGame.getImg().getTex().getHeight()));
		ents.add(btnNewGame);
		
		Ent btnCredits = new Ent();
		btnCredits.setName("btnCredits");
		btnCredits.setImg(Game.getGlobal().getImgByName("btnCredits"));
		btnCredits.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(btnCredits.getImg().getTex().getWidth()/2)),
				btnNewGame.getY()-btnCredits.getImg().getTex().getHeight()-15,
				btnCredits.getImg().getTex().getWidth(),
				btnCredits.getImg().getTex().getHeight()));
		ents.add(btnCredits);
		
		Ent btnControls = new Ent();
		btnControls.setName("btnControls");
		btnControls.setImg(Game.getGlobal().getImgByName("btnCredits"));
		btnControls.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(btnControls.getImg().getTex().getWidth()/2)),
				btnCredits.getY()-btnControls.getImg().getTex().getHeight()-15,
				btnControls.getImg().getTex().getWidth(),
				btnControls.getImg().getTex().getHeight()));
		ents.add(btnControls);
		
		Ent btnExit = new Ent();
		btnExit.setName("btnExit");
		btnExit.setImg(Game.getGlobal().getImgByName("btnExit"));
		btnExit.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(btnExit.getImg().getTex().getWidth()/2)),
				btnControls.getY()-btnExit.getImg().getTex().getHeight()-15,
				btnExit.getImg().getTex().getWidth(),
				btnExit.getImg().getTex().getHeight()));
		ents.add(btnExit);
		
		this.setEnts(ents);
	}
	
	public void update(float stateTime){
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
				if (e.getName().contains("btn")){
					if (mousePos.overlaps(ePos)){
						if (e.getName().equals("btnNewGame")){
							Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("game"));
						}
						if (e.getName().equals("btnCredits")){
							Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("credits"));
						}
						if (e.getName().equals("btnControls")){
							Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("controls"));
						}
						if (e.getName().equals("btnExit")){
							Gdx.app.exit();
						}
					}
				}
			}
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
