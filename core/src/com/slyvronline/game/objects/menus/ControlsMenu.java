package com.slyvronline.game.objects.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.GameController;
import com.slyvronline.game.objects.Menu;

public class ControlsMenu extends Menu{
	
	public ControlsMenu(){
		load();
	}
	
	public void load(){
		this.setName("controls");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent btnBack = new Ent();
		btnBack.setName("btnBack");
		btnBack.setImg(Game.getGlobal().getImgByName("btnBack"));
		btnBack.setPosBox(new Rectangle((int) ((Gdx.graphics.getWidth()/2)-(btnBack.getImg().getTex().getWidth()/2)),
				100,
				btnBack.getImg().getTex().getWidth(),
				btnBack.getImg().getTex().getHeight()));
		ents.add(btnBack);
		
		this.setEnts(ents);
	}
	
	public void loadControllers(){
		ArrayList<GameController> controls = new ArrayList<GameController>();
		for (Controller controller : Controllers.getControllers()) {
		    GameController control = new GameController();
		    control.setName(controller.getName());
		    control.setControl(controller);
		    controls.add(control);
		}
		Game.getGlobal().setControllers(controls);
	}
	
	public void update(float stateTime){
		updateControllers();
		updateButtons();
		updateButtonHover();
	}
	
	public void updateControllers(){
		
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
						if (e.getName().equals("btnBack")){
							Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
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
