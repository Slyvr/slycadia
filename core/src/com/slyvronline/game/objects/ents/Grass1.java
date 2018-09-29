package com.slyvronline.game.objects.ents;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Grass1 extends Consumable {

	public Grass1(){
		this.setName("grass1");
		this.setImg(Game.getGlobal().getImgByName("grass1"));
		this.setSize(1);
		this.setAnimateCounterMax(50);
		this.setAnimateCountUp(true);
		this.setAnimateAmount(0.1f);
		this.setAnimateSpeed(1);
		this.setPosBox(new Rectangle(0,0,
				this.getImg().getTex().getWidth(),
				this.getImg().getTex().getHeight()));
	}
	
	public void animate(){
		consume();
	}
}
