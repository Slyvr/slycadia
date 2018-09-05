package com.slyvronline.game.objects.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Menu;

public class CreditsMenu extends Menu{

	public CreditsMenu(){
		load();
	}
	
	public void load(){
		this.setName("credits");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent logoCredits = new Ent();
		logoCredits.setName("logoCredits");
		logoCredits.setImg(Game.getGlobal().getImgByName("logoCredits"));
		logoCredits.setPosBox(new Rectangle(Gdx.graphics.getWidth()/2 - logoCredits.getImg().getTex().getWidth()/2,
				Gdx.graphics.getHeight() - logoCredits.getImg().getTex().getHeight(),
				logoCredits.getImg().getTex().getWidth(),
				logoCredits.getImg().getTex().getHeight()));
		ents.add(logoCredits);
		
		Ent fntCredits = new Ent();
		fntCredits.setName("fntCredits");
		fntCredits.setFont(Game.getGlobal().getFontByName("AgencyFbGlow32"));
		fntCredits.setPosBox(new Rectangle(logoCredits.getX(),
				logoCredits.getY()-32,
				0,
				0));
		fntCredits.setText("Game by Matthew Schrum aka Slyvr89\n\n"+
				"Created for 2018 STL Arcade Jam\n\n"+
				"Thanks for playing my game!\n\n" +
				"\n\n" +
				"");
		fntCredits.setColor(Color.WHITE);
		ents.add(fntCredits);
		
		Ent btnBack = new Ent();
		btnBack.setName("btnBack");
		btnBack.setImg(Game.getGlobal().getImgByName("btnBack"));
		btnBack.setPosBox(new Rectangle((Gdx.graphics.getWidth()/2)-(btnBack.getImg().getTex().getWidth()/2),
				0+15,
				btnBack.getImg().getTex().getWidth(),
				btnBack.getImg().getTex().getHeight()));
		ents.add(btnBack);
		
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
}
