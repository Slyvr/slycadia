package com.slyvronline.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.menus.LevelEditMenu;
import com.slyvronline.game.utils.GameConstants;

public class LevelEditInstance extends GameInstance {

	private int moveSpeed=3;
	private Ent gameboard;
	private Ent placeEnt;
	private ArrayList<Ent> placedEnts;
	private EditStatus editStatus;
	public enum EditStatus {UNDO,PLACE,SELECT,SAVE}
	
	public LevelEditInstance(){
		gameboard = new Ent();
		gameboard.setName("gameboard");
		gameboard.setImg(Game.getGlobal().getImgByName("gameboard"));
		gameboard.setPosBox(new Rectangle(0,0,
				gameboard.getImg().getTex().getWidth(),
				gameboard.getImg().getTex().getHeight()));
		
		placedEnts = new ArrayList<Ent>();
		
		Game.getGlobal().getCamera().zoom = 1.5f;
		float effectiveViewportWidth = Game.getGlobal().getCamera().viewportWidth * Game.getGlobal().getCamera().zoom;
		float effectiveViewportHeight = Game.getGlobal().getCamera().viewportHeight * Game.getGlobal().getCamera().zoom;
		Game.getGlobal().getCamera().position.x = gameboard.getX()+(effectiveViewportWidth/2);
		Game.getGlobal().getCamera().position.y = gameboard.getY()+(effectiveViewportHeight/2);
		
		editStatus = EditStatus.SELECT;
	}
	
	public void render(SpriteBatch batch){
		gameboard.render(batch);
		
		for(Ent ent : placedEnts){
			ent.render(batch);
		}
		
		if (placeEnt != null) placeEnt.render(batch);
	}
	
	public void update(){
		if (editStatus.equals(EditStatus.PLACE)){
			updateMove();
			updatePlace();
			if (placeEnt == null){
				LevelEditMenu menu = (LevelEditMenu) Game.getGlobal().getCurrentMenu();
				placeEnt = new Ent(menu.getSelectedConsumable());
				placeEnt.setX(Game.getGlobal().getCamera().position.x);
				placeEnt.setY(Game.getGlobal().getCamera().position.y);
			}
		}
		else if (editStatus.equals(EditStatus.SELECT)){
			placeEnt = null;
			updateMove();
			//Moved to LevelEditMenu
		}
		else if (editStatus.equals(EditStatus.UNDO)){
			placeEnt = null;
			if (placedEnts.size() > 0){
				placedEnts.remove(placedEnts.size()-1);
			}
			editStatus = EditStatus.SELECT;
		}
		else if (editStatus.equals(EditStatus.SAVE)){
			placeEnt = null;
			saveLevel();
			editStatus = EditStatus.SELECT;
		}
		else{
			placeEnt = null;
		}
	}
	
	public void updateMove(){
		if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_B) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_B)){
			OrthographicCamera cam = Game.getGlobal().getCamera();
			if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_DOWN) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_DOWN)){
				cam.position.y = cam.position.y - moveSpeed;
				if (placeEnt != null){
					placeEnt.setY(placeEnt.getY() - moveSpeed);
					if (placeEnt.getY() <= gameboard.getY()){
						placeEnt.setY(placeEnt.getY() + moveSpeed);
					}
					if (placeEnt.getY() > cam.position.y){
						cam.position.y = cam.position.y + moveSpeed;
					}
				}
			}
			if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_UP) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_UP)){
				cam.position.y = cam.position.y + moveSpeed;
				if (placeEnt != null){
					placeEnt.setY(placeEnt.getY() + moveSpeed);
					if (placeEnt.getY() >= gameboard.getHeight() - placeEnt.getHeight()){
						placeEnt.setY(placeEnt.getY() - moveSpeed);
					}
					if (placeEnt.getY() < cam.position.y){
						cam.position.y = cam.position.y - moveSpeed;
					}
				}
			}
			if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_LEFT) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_LEFT)){
				cam.position.x = cam.position.x - moveSpeed;
				if (placeEnt != null){
					placeEnt.setX(placeEnt.getX() - moveSpeed);
					if (placeEnt.getX() <= gameboard.getX()){
						placeEnt.setX(placeEnt.getX() + moveSpeed);
					}
					if (placeEnt.getX() > cam.position.x){
						cam.position.x = cam.position.x + moveSpeed;
					}
				}
			}
			if (Gdx.input.isKeyPressed(GameConstants.FIRST_KEY_RIGHT) || Gdx.input.isKeyPressed(GameConstants.SEC_KEY_RIGHT)){
				cam.position.x = cam.position.x + moveSpeed;
				if (placeEnt != null){
					placeEnt.setX(placeEnt.getX() + moveSpeed);
					if (placeEnt.getX() >= gameboard.getWidth() - placeEnt.getWidth()){
						placeEnt.setX(placeEnt.getX() - moveSpeed);
					}
					if (placeEnt.getX() < cam.position.x){
						cam.position.x = cam.position.x - moveSpeed;
					}
				}
			}
			
			//Stop movement outside of gameboard
			float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
			float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
			if (cam.position.x < gameboard.getX()+(effectiveViewportWidth/2)){
				cam.position.x = gameboard.getX()+(effectiveViewportWidth/2);
			}
			if (cam.position.x > gameboard.getWidth()-(effectiveViewportWidth/2)){
				cam.position.x = gameboard.getWidth()-(effectiveViewportWidth/2);
			}
			if (cam.position.y < gameboard.getY()+(effectiveViewportHeight/2)){
				cam.position.y = gameboard.getY()+(effectiveViewportHeight/2);
			}
			if (cam.position.y > gameboard.getHeight()-(effectiveViewportHeight/2)){
				cam.position.y = gameboard.getHeight()-(effectiveViewportHeight/2);
			}
		}
	}
	
	public void updatePlace(){
		if (Gdx.input.isKeyJustPressed(GameConstants.FIRST_KEY_A) || Gdx.input.isKeyJustPressed(GameConstants.SEC_KEY_A)){
			if (placeEnt != null) placedEnts.add(new Ent(placeEnt));
		}
	}
	
	public void saveLevel(){
		String outputXML="<ents>\n";
		for(Ent e : placedEnts){
			outputXML+= "<ent><name>"+e.getName().replace("ent_", "")+"</name><x>"+e.getX()+"</x><y>"+e.getY()+"</y></ent>\n";
		}
		outputXML+="</ents>";
		
		FileHandle levelDataFile = Gdx.files.local("data/levels/levelDataFile.xml");
		FileHandle levelDataFile2 = Gdx.files.local("data/levels/levelDataFile"+System.currentTimeMillis()+".xml");
		levelDataFile2.writeString(levelDataFile.readString(), false);
		levelDataFile.writeString(outputXML, false);
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public Ent getGameboard() {
		return gameboard;
	}

	public void setGameboard(Ent gameboard) {
		this.gameboard = gameboard;
	}

	public EditStatus getEditStatus() {
		return editStatus;
	}

	public void setEditStatus(EditStatus editStatus) {
		this.editStatus = editStatus;
	}
	
	
}
