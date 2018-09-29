package com.slyvronline.game.objects.ents;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Bike extends Consumable {

	public Bike(){
		this.setName("bike");
		this.setImg(Game.getGlobal().getImgByName("bike"));
		this.setSize(2);
		this.setAnimateCounterMax(new Random().nextInt(3)+3);
		this.setAnimateCountUp(true);
		this.setAnimateAmount(0.1f);
		this.setAnimateSpeed(1);
		this.setPosBox(new Rectangle(0,0,
				this.getImg().getTex().getWidth(),
				this.getImg().getTex().getHeight()));
	}
	
	public void animate(){
		consume();
		shimmy();
	}
}
