package com.slyvronline.game.objects.ents;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Man1 extends Consumable {

	public Man1(){
		this.setName("man1");
		this.setImg(Game.getGlobal().getImgByName("man1"));
		this.setSize(1);
		this.setAnimateCounterMax(new Random().nextInt(5)+5);
		this.setAnimateCountUp(true);
		this.setAnimateAmount(0.5f);
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
