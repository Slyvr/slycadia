package com.slyvronline.game.load;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Img;
import com.slyvronline.game.utils.GameConstants;

/**
 * This class creates new Img classes to an arraylist containing a searchable name and the image data
 * @author Matt Schrum - slyvr89
 * @date 10/25/2013
 */
public class LoadImgs {

	public static void load(){
		ArrayList<Img> imgs = new ArrayList<Img>();
		
		//LOGOS - Titles
		imgs.add(new Img(new Texture(Gdx.files.internal("data/logos/logoSplash.png")),"logoSplash"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/logos/logoCredits.png")),"logoCredits"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/logos/logoTitle.png")),"logoTitle"));
		
		//BUTTONS
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnStart.png")),"btnStart"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnStart_hover.png")),"btnStart_hover"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnExit.png")),"btnExit"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnExit_hover.png")),"btnExit_hover"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnBack.png")),"btnBack"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnBack_hover.png")),"btnBack_hover"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnCredits.png")),"btnCredits"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnCredits_hover.png")),"btnCredits_hover"));
		
		//Backgrounds
		imgs.add(new Img(new Texture(Gdx.files.internal("data/bg/BackgroundsFree/07/PNG/1920x1080.png")),"bg07"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/icons/chevron-color.png")),"chevron-othercolor"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/icons/chevron-blue.png")),"chevron-color"));
		
		//Tiles
		Img basictiles = new Img(new Texture(Gdx.files.internal("data/tiles/basictiles.png")),"basictiles");
		TextureRegion[] texRegs = new TextureRegion[128/16*256/16];
		int counter = 0;
		for(int y=0; y<basictiles.getTex().getHeight()/16; y++){
			for(int x=0; x<basictiles.getTex().getWidth()/16; x++){
				texRegs[counter] = new TextureRegion(basictiles.getTex(), x, y, 16, 16);
				counter++;
			}
		}
		basictiles.setTexRegs(texRegs);
		imgs.add(basictiles);
		
		Game.getGlobal().setImgs(imgs);
	}
}
