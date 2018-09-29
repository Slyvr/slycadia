package com.slyvronline.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.controllers.Xbox360Controller;
import com.slyvronline.game.utils.GameConstants;

/**
 * This class maintains all the data and entities required in a menu
 * @author Matt Schrum - slyvr89
 * @date 10/25/2013
 */
public class Menu {

	private String name;
	private int id;
	private String type;
	private ArrayList<Ent> ents;
	private ArrayList<Menu> subMenus;
	private Menu currentSubMenu;
	private int controllerAxisTimer;
	
	public Menu(){
		
	}
	
	public void render(SpriteBatch batch){
		for(Ent e : ents){
			e.render(batch);
		}
	}
	
	public void update(float stateTime){
		
	}
	
	public static void updateButtonHover(){
		
	}
	
	public void updateKeyboardNavigation(){
		if (Gdx.input.isKeyJustPressed(GameConstants.FIRST_KEY_DOWN) || Gdx.input.isKeyJustPressed(GameConstants.SEC_KEY_DOWN)){
			downButton();
		}
		if (Gdx.input.isKeyJustPressed(GameConstants.FIRST_KEY_UP) || Gdx.input.isKeyJustPressed(GameConstants.SEC_KEY_UP)){
			upButton();
		}
	}
	
	public void updateKeyboardSelect(){
		if (Gdx.input.isKeyJustPressed(GameConstants.FIRST_KEY_A) || Gdx.input.isKeyJustPressed(GameConstants.SEC_KEY_A)){
			buttonSelect();
		}
		if (Gdx.input.isKeyJustPressed(GameConstants.FIRST_KEY_B) || Gdx.input.isKeyJustPressed(GameConstants.SEC_KEY_B) || Gdx.input.isKeyJustPressed(GameConstants.KEY_QUIT)){
			buttonDeselect();
		}
	}
	
	public void buttonSelect(){
		
	}
	
	public void buttonDeselect(){
		
	}
	
	public void updateControllerPOV(Controller controller, int povCode, PovDirection value){
		//System.out.println(controller.getName()+" - "+povCode+" - "+value);
	}
	
	public void updateControllerAxis(Controller controller, int axisCode, float value){
		//Down
		if (value > 0.5 && axisCode == 0){
			if (value == 1.0){
				downButton();
			}
		}
		//Up
		if (value < -0.5 && axisCode == 0){
			if (value == -1.0){
				upButton();
			}
		}
		//Right
		if (value > 0.5 && axisCode == 1){
			if (value == 1.0){
				
			}
		}
		//Left
		if (value < -0.5 && axisCode == 1){
			if (value == -1.0){
				
			}
		}
	}
	
	public void downButton(){
		//Navigate btn to next one down
		Ent selected = this.getSelectedEnt();
		if (selected != null){
			int nextId = selected.getId() + 1;
			if (selected.getId() == this.getMaxBtnId()){
				nextId = 1;
			}
			else{
				if (this.getEntById(nextId) == null){
					nextId = 1;
				}
			}
			if (this.getEntById(nextId) != null && !this.getEntById(nextId).isSelected()){
				Game.getGlobal().getSfxByName("paperflip").play();
			}
			selected.setSelected(false);
			if (this.getEntById(nextId) != null) this.getEntById(nextId).setSelected(true);
		}
	}
	
	public void upButton(){
		//Navigate btn to next one up
		Ent selected = this.getSelectedEnt();
		if (selected != null){
			int nextId = selected.getId() - 1;
			if (selected.getId() <= 1){
				nextId = this.getMaxBtnId();
			}
			else{
				if (this.getEntById(nextId) == null){
					nextId = 1;
				}
			}
			if (this.getEntById(nextId) != null && !this.getEntById(nextId).isSelected()){
				Game.getGlobal().getSfxByName("paperflip").play();
			}
			selected.setSelected(false);
			if (this.getEntById(nextId) != null) this.getEntById(nextId).setSelected(true);
		}
	}
	
	public void updateControllerButtonDown(Controller controller, int buttonCode){
		if (buttonCode == Xbox360Controller.BUTTON_A){
			buttonSelect();
		}
		if (buttonCode == Xbox360Controller.BUTTON_B){
			buttonDeselect();
		}
	}
	
	public void updateControllerButtonUp(Controller controller, int buttonCode){
		//System.out.println(controller.getName()+" - "+buttonCode+" UP");
	}
	
	public void cycleChevronBg(){
		for(Ent e : ents){
			if (e.getName().contains("chevron")){
				e.setX(e.getX() + 1);
				e.setY(e.getY() + 1);
				if (e.getX() >= 2048){
					e.setX(-e.getPosBox().getWidth());
				}
				if (e.getY() >= 1024){
					e.setY(-e.getPosBox().getHeight());
				}
			}
		}
	}
	
	public ArrayList<Ent> buildChevronBG(){
		ArrayList<Ent> ents = new ArrayList<Ent>();
		
		Ent chevron1 = new Ent();
		chevron1.setName("chevron1");
		chevron1.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron1.setPosBox(new Rectangle(-1,
				-1,
				chevron1.getImg().getTex().getWidth(),
				chevron1.getImg().getTex().getHeight()));
		ents.add(chevron1);
		
		Ent chevron12 = new Ent();
		chevron12.setName("chevron12");
		chevron12.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron12.setPosBox(new Rectangle(chevron1.getPosBox().getX()+chevron1.getImg().getTex().getWidth(),
				chevron1.getPosBox().getY(),
				chevron12.getImg().getTex().getWidth(),
				chevron12.getImg().getTex().getHeight()));
		ents.add(chevron12);
		
		Ent chevron13 = new Ent();
		chevron13.setName("chevron13");
		chevron13.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron13.setPosBox(new Rectangle(chevron12.getPosBox().getX()+chevron12.getImg().getTex().getWidth(),
				chevron1.getPosBox().getY(),
				chevron13.getImg().getTex().getWidth(),
				chevron13.getImg().getTex().getHeight()));
		ents.add(chevron13);
		
		Ent chevron14 = new Ent();
		chevron14.setName("chevron14");
		chevron14.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron14.setPosBox(new Rectangle(chevron13.getPosBox().getX()+chevron13.getImg().getTex().getWidth(),
				chevron1.getPosBox().getY(),
				chevron14.getImg().getTex().getWidth(),
				chevron14.getImg().getTex().getHeight()));
		ents.add(chevron14);
		
		Ent chevron15 = new Ent();
		chevron15.setName("chevron15");
		chevron15.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron15.setPosBox(new Rectangle(chevron14.getPosBox().getX()+chevron14.getImg().getTex().getWidth(),
				chevron1.getPosBox().getY(),
				chevron15.getImg().getTex().getWidth(),
				chevron15.getImg().getTex().getHeight()));
		ents.add(chevron15);
		
		Ent chevron2 = new Ent();
		chevron2.setName("chevron2");
		chevron2.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron2.setPosBox(new Rectangle(chevron1.getPosBox().getX(),
				chevron1.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron2.getImg().getTex().getWidth(),
				chevron2.getImg().getTex().getHeight()));
		ents.add(chevron2);
		
		Ent chevron22 = new Ent();
		chevron22.setName("chevron22");
		chevron22.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron22.setPosBox(new Rectangle(chevron1.getPosBox().getX()+chevron1.getImg().getTex().getWidth(),
				chevron1.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron22.getImg().getTex().getWidth(),
				chevron22.getImg().getTex().getHeight()));
		ents.add(chevron22);
		
		Ent chevron23 = new Ent();
		chevron23.setName("chevron23");
		chevron23.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron23.setPosBox(new Rectangle(chevron12.getPosBox().getX()+chevron12.getImg().getTex().getWidth(),
				chevron1.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron23.getImg().getTex().getWidth(),
				chevron23.getImg().getTex().getHeight()));
		ents.add(chevron23);
		
		Ent chevron24 = new Ent();
		chevron24.setName("chevron24");
		chevron24.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron24.setPosBox(new Rectangle(chevron13.getPosBox().getX()+chevron13.getImg().getTex().getWidth(),
				chevron1.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron24.getImg().getTex().getWidth(),
				chevron24.getImg().getTex().getHeight()));
		ents.add(chevron24);
		
		Ent chevron25 = new Ent();
		chevron25.setName("chevron25");
		chevron25.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron25.setPosBox(new Rectangle(chevron14.getPosBox().getX()+chevron14.getImg().getTex().getWidth(),
				chevron1.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron25.getImg().getTex().getWidth(),
				chevron25.getImg().getTex().getHeight()));
		ents.add(chevron25);
		
		Ent chevron3 = new Ent();
		chevron3.setName("chevron3");
		chevron3.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron3.setPosBox(new Rectangle(chevron1.getPosBox().getX(),
				chevron2.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron3.getImg().getTex().getWidth(),
				chevron3.getImg().getTex().getHeight()));
		ents.add(chevron3);
		
		Ent chevron32 = new Ent();
		chevron32.setName("chevron32");
		chevron32.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron32.setPosBox(new Rectangle(chevron1.getPosBox().getX()+chevron1.getImg().getTex().getWidth(),
				chevron2.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron32.getImg().getTex().getWidth(),
				chevron32.getImg().getTex().getHeight()));
		ents.add(chevron32);
		
		Ent chevron33 = new Ent();
		chevron33.setName("chevron33");
		chevron33.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron33.setPosBox(new Rectangle(chevron12.getPosBox().getX()+chevron12.getImg().getTex().getWidth(),
				chevron2.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron33.getImg().getTex().getWidth(),
				chevron33.getImg().getTex().getHeight()));
		ents.add(chevron33);
		
		Ent chevron34 = new Ent();
		chevron34.setName("chevron34");
		chevron34.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron34.setPosBox(new Rectangle(chevron13.getPosBox().getX()+chevron13.getImg().getTex().getWidth(),
				chevron2.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron34.getImg().getTex().getWidth(),
				chevron34.getImg().getTex().getHeight()));
		ents.add(chevron34);
		
		Ent chevron35 = new Ent();
		chevron35.setName("chevron35");
		chevron35.setImg(Game.getGlobal().getImgByName("chevron-color"));
		chevron35.setPosBox(new Rectangle(chevron14.getPosBox().getX()+chevron14.getImg().getTex().getWidth(),
				chevron2.getPosBox().getY()+chevron1.getImg().getTex().getHeight(),
				chevron35.getImg().getTex().getWidth(),
				chevron35.getImg().getTex().getHeight()));
		ents.add(chevron35);
		
		return ents;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ArrayList<Ent> getEnts() {
		return ents;
	}
	public void setEnts(ArrayList<Ent> ents) {
		this.ents = ents;
	}
	public ArrayList<Menu> getSubMenus(){
		return subMenus;
	}
	public void setSubMenus(ArrayList<Menu> subMenus){
		this.subMenus=subMenus;
	}
	public Menu getCurrentSubMenu(){
		return currentSubMenu;
	}
	public void setCurrentSubMenu(Menu currentSubMenu){
		this.currentSubMenu=currentSubMenu;
	}
	public void setCurrentSubMenuByName(String menuName){
		for(Menu subMenu : subMenus){
			if (menuName.equals(subMenu.getName())){
				this.currentSubMenu = subMenu;
				break;
			}
		}
	}
	public Ent getEntByName(String name){
		for(Ent e : ents)
			if (e.getName().equals(name)) return e;
		return null;
	}
	public Menu getSubMenuByName(String name){
		for(Menu m : subMenus)
			if (m.getName().equals(name)) return m;
		return null;
	}
	public int getMaxBtnId(){
		int maxId = 1;
		for(Ent e : this.getEnts()){
			if (maxId < e.getId()) maxId = e.getId();
		}
		return maxId;
	}
	public Ent getEntById(int id){
		for(Ent e : this.getEnts())
			if (e.getId() == id) return e;
		return null;
	}
	public int getControllerAxisTimer() {
		return controllerAxisTimer;
	}
	public void setControllerAxisTimer(int controllerAxisTimer) {
		this.controllerAxisTimer = controllerAxisTimer;
	}
	public Ent getSelectedEnt(){
		for(Ent e : this.getEnts())
			if (e.isSelected()) return e;
		return null;
	}
}
