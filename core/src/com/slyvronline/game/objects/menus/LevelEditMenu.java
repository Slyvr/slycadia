package com.slyvronline.game.objects.menus;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.BlackHoleInstance;
import com.slyvronline.game.objects.Ent;
import com.slyvronline.game.objects.Img;
import com.slyvronline.game.objects.LevelEditInstance;
import com.slyvronline.game.objects.LevelEditInstance.EditStatus;
import com.slyvronline.game.objects.Menu;
import com.slyvronline.game.objects.ents.Consumable;
import com.slyvronline.game.utils.GameConstants;
import com.slyvronline.game.utils.PingWebsite;

public class LevelEditMenu extends Menu{

	private ArrayList<Ent> consumables;
	
	public LevelEditMenu(){
		load();
	}
	
	public void load(){
		this.setName("leveledit");
		
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent btnBack = new Ent();
		btnBack.setName("btnBack");
		btnBack.setId(5);
		btnBack.setImg(Game.getGlobal().getImgByName("btnBack"));
		btnBack.setPosBox(new Rectangle(Gdx.graphics.getWidth() - btnBack.getImg().getTex().getWidth(),
				0+15,
				btnBack.getImg().getTex().getWidth(),
				btnBack.getImg().getTex().getHeight()));
		ents.add(btnBack);
		
		Ent btnSelect = new Ent();
		btnSelect.setName("btnSelect");
		btnSelect.setId(4);
		btnSelect.setImg(Game.getGlobal().getImgByName("btnSelect"));
		btnSelect.setPosBox(new Rectangle(btnBack.getX(),
				btnBack.getY() + btnBack.getHeight() + 3,
				btnSelect.getImg().getTex().getWidth(),
				btnSelect.getImg().getTex().getHeight()));
		btnSelect.setSelected(true);
		ents.add(btnSelect);
		
		Ent btnUndo = new Ent();
		btnUndo.setName("btnUndo");
		btnUndo.setId(3);
		btnUndo.setImg(Game.getGlobal().getImgByName("btnUndo"));
		btnUndo.setPosBox(new Rectangle(btnSelect.getX(),
				btnSelect.getY() + btnSelect.getHeight() + 3,
				btnUndo.getImg().getTex().getWidth(),
				btnUndo.getImg().getTex().getHeight()));
		ents.add(btnUndo);
		
		Ent btnPlace = new Ent();
		btnPlace.setName("btnPlace");
		btnPlace.setId(2);
		btnPlace.setImg(Game.getGlobal().getImgByName("btnPlace"));
		btnPlace.setPosBox(new Rectangle(btnUndo.getX(),
				btnUndo.getY() + btnUndo.getHeight() + 3,
				btnPlace.getImg().getTex().getWidth(),
				btnPlace.getImg().getTex().getHeight()));
		ents.add(btnPlace);
		
		Ent btnSave = new Ent();
		btnSave.setName("btnSave");
		btnSave.setId(1);
		btnSave.setImg(Game.getGlobal().getImgByName("btnSave"));
		btnSave.setPosBox(new Rectangle(btnPlace.getX(),
				btnPlace.getY() + btnPlace.getHeight() + 3,
				btnSave.getImg().getTex().getWidth(),
				btnSave.getImg().getTex().getHeight()));
		ents.add(btnSave);
		
		Ent selectBox = new Ent();
		selectBox.setName("selectBox");
		selectBox.setImg(Game.getGlobal().getImgByName("selectBox"));
		selectBox.setPosBox(new Rectangle(64,
				15,
				Game.getGlobal().getConsumables().get(0).getWidth(),
				Game.getGlobal().getConsumables().get(0).getHeight()));
		ents.add(selectBox);
		
		consumables = new ArrayList<Ent>();
		float lastWidth = 0;
		float lastX = 64;
		boolean selected = true;
		int idCount=0;
		for(Consumable c : Game.getGlobal().getConsumables()){
			Ent consumable = new Ent();
			consumable.setName("ent_"+c.getName());
			consumable.setId(idCount);
			consumable.setImg(new Img(c.getImg()));
			consumable.setPosBox(new Rectangle(c.getPosBox()));
			consumable.setY(selectBox.getY());
			consumable.setX(lastX+lastWidth);
			consumable.setSelected(selected);
			consumables.add(consumable);
			lastWidth = consumable.getWidth();
			lastX = consumable.getX();
			if (selected) selected = false;
			idCount++;
		}
		
		this.setEnts(ents);
	}
	
	public void render(SpriteBatch batch){
		for(Ent e : consumables){
			e.render(batch);
		}
		
		for(Ent e : this.getEnts()){
			e.render(batch);
		}
	}
	
	public void update (float stateTime){
		if (Game.getGlobal().getGame() == null){
			Game.getGlobal().setGame(new LevelEditInstance());
		}
		
		if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_B) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_B)){
			
		}
		else {
			updateKeyboardNavigation();
			updateKeyboardNavigationLeftRight();
			updateKeyboardSelect();
		}
		
		
		buttonDeselect();
	}
	
	public void updateKeyboardNavigationLeftRight(){
		LevelEditInstance game = (LevelEditInstance) Game.getGlobal().getGame();
		if (game.getEditStatus().equals(EditStatus.SELECT)){
			if (Gdx.input.isKeyJustPressed(GameConstants.FIRST_KEY_LEFT) || Gdx.input.isKeyJustPressed(GameConstants.SEC_KEY_LEFT)){
				Ent selectedEnt = this.getSelectedConsumable();
				if (selectedEnt.getId() > 0){
					Ent nextEnt = this.getConsumableById(selectedEnt.getId()-1);
					nextEnt.setSelected(true);
					selectedEnt.setSelected(false);
					
					Ent selectBox = this.getEntByName("selectBox");
					selectBox.setWidth(nextEnt.getWidth());
					selectBox.setHeight(nextEnt.getHeight());
					
					for(Ent e : consumables){
						e.setX(e.getX() + nextEnt.getWidth());
					}
				}
			}
			if (Gdx.input.isKeyJustPressed(GameConstants.FIRST_KEY_RIGHT) || Gdx.input.isKeyJustPressed(GameConstants.SEC_KEY_RIGHT)){
				Ent selectedEnt = this.getSelectedConsumable();
				if (selectedEnt.getId() < consumables.size()-1){
					Ent nextEnt = this.getConsumableById(selectedEnt.getId()+1);
					nextEnt.setSelected(true);
					selectedEnt.setSelected(false);
					
					Ent selectBox = this.getEntByName("selectBox");
					selectBox.setWidth(nextEnt.getWidth());
					selectBox.setHeight(nextEnt.getHeight());
					
					for(Ent e : consumables){
						e.setX(e.getX() - selectedEnt.getWidth());
					}
				}
			}
		}
	}
	
	public void buttonSelect(){
		Ent selected = this.getSelectedEnt();
		if (selected.getName().equals("btnBack")){
			Game.getGlobal().getSfxByName("papery").play();
			Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
			Game.getGlobal().setGame(null);
		}
		if (selected.getName().equals("btnSave")){
			LevelEditInstance game = (LevelEditInstance) Game.getGlobal().getGame();
			game.setEditStatus(EditStatus.SAVE);
		}
		if (selected.getName().equals("btnPlace")){
			LevelEditInstance game = (LevelEditInstance) Game.getGlobal().getGame();
			game.setEditStatus(EditStatus.PLACE);
		}
		if (selected.getName().equals("btnUndo")){
			LevelEditInstance game = (LevelEditInstance) Game.getGlobal().getGame();
			game.setEditStatus(EditStatus.UNDO);
		}
		if (selected.getName().equals("btnSelect")){
			LevelEditInstance game = (LevelEditInstance) Game.getGlobal().getGame();
			game.setEditStatus(EditStatus.SELECT);
		}
	}
	
	public void buttonDeselect(){
		if (Gdx.input.isKeyJustPressed(GameConstants.KEY_QUIT)){
			Game.getGlobal().getSfxByName("papery").play();
			Game.getGlobal().setCurrentMenu(Game.getGlobal().getMenuByName("main"));
			Game.getGlobal().setGame(null);
		}
	}
	
	public Ent getSelectedConsumable(){
		for(Ent e : consumables)
			if (e.isSelected()) return e;
		return null;
	}
	
	public Ent getConsumableById(int id){
		for(Ent e : consumables)
			if (e.getId() == id) return e;
		return null;
	}
}
