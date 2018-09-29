package com.slyvronline.game.objects.ents;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Car2 extends Consumable {

	public Car2(){
		this.setName("car2");
		this.setImg(Game.getGlobal().getImgByName("car2"));
		this.setSize(3);
		this.setAnimateCounterMax(new Random().nextInt(5)+5);
		this.setAnimateCountUp(true);
		this.setAnimateAmount(0.3f);
		this.setAnimateSpeed(1);
		this.setFlipY(new Random().nextBoolean());
		this.setPosBox(new Rectangle(0,0,
				this.getImg().getTex().getWidth(),
				this.getImg().getTex().getHeight()));
	}
	
	public void animate(){
		consume();
		hop();
	}
}
