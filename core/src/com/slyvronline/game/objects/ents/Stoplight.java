package com.slyvronline.game.objects.ents;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Stoplight extends Consumable {

	public Stoplight(){
		this.setName("stoplight");
		this.setImg(Game.getGlobal().getImgByName("stoplight"));
		this.setSize(2);
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
