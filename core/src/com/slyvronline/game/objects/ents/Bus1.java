package com.slyvronline.game.objects.ents;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Bus1 extends Consumable {

	public Bus1(){
		this.setName("bus1");
		this.setImg(Game.getGlobal().getImgByName("bus1"));
		this.setSize(3);
		this.setAnimateCounterMax(new Random().nextInt(5)+5);
		this.setAnimateCountUp(true);
		this.setAnimateAmount(0.3f);
		this.setAnimateSpeed(1);
		this.setFlipX(new Random().nextBoolean());
		this.setPosBox(new Rectangle(0,0,
				this.getImg().getTex().getWidth(),
				this.getImg().getTex().getHeight()));
	}
	
	public void animate(){
		consume();
		shimmy();
	}
}
