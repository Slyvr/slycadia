package com.slyvronline.game.objects.ents;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;

public class Tree5 extends Consumable {

	public Tree5(){
		this.setName("tree5");
		this.setImg(Game.getGlobal().getImgByName("tree5"));
		this.setSize(4);
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
