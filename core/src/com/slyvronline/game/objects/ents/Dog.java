package com.slyvronline.game.objects.ents;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Dog extends Consumable {

	public Dog(){
		this.setName("dog");
		this.setImg(Game.getGlobal().getImgByName("dog"));
		this.setSize(1);
		this.setAnimateCounterMax(new Random().nextInt(5)+5);
		this.setAnimateCountUp(true);
		this.setAnimateAmount(0.8f);
		this.setAnimateSpeed(1);
		this.setPosBox(new Rectangle(0,0,
				this.getImg().getTex().getWidth(),
				this.getImg().getTex().getHeight()));
	}
	
	public void animate(){
		consume();
		hop();
	}
}
