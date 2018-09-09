package com.slyvronline.game.objects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
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
	
	public void updateControllerPOV(Controller controller, int povCode, PovDirection value){
		System.out.println(controller.getName()+" - "+povCode+" - "+value);
	}
	
	public void updateControllerAxis(Controller controller, int axisCode, float value){
		//Down
		if (value > 0.5 && axisCode == 0){
			if (value == 1.0){
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
					selected.setSelected(false);
					if (this.getEntById(nextId) != null) this.getEntById(nextId).setSelected(true);
				}
			}
		}
		//Up
		if (value < -0.5 && axisCode == 0){
			if (value == -1.0){
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
					selected.setSelected(false);
					if (this.getEntById(nextId) != null) this.getEntById(nextId).setSelected(true);
				}
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
	
	public void updateControllerButtonDown(Controller controller, int buttonCode){
		//System.out.println(controller.getName()+" - "+buttonCode+" DOWN");
	}
	
	public void updateControllerButtonUp(Controller controller, int buttonCode){
		//System.out.println(controller.getName()+" - "+buttonCode+" UP");
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
