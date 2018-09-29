package com.slyvronline.game.objects.ents;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Cow extends Consumable {

	public Cow(){
		this.setName("cow");
		this.setImg(Game.getGlobal().getImgByName("cow"));
		this.setSize(2);
		this.setAnimateCounterMax(new Random().nextInt(3)+3);
		this.setAnimateCountUp(true);
		this.setAnimateAmount(0.4f);
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
