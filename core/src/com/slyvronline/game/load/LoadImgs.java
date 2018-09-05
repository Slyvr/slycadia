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
		
		//BUTTONS
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnNewGame.png")),"btnNewGame"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnNewGame_hover.png")),"btnNewGame_hover"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnExit.png")),"btnExit"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnExit_hover.png")),"btnExit_hover"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnBack.png")),"btnBack"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnBack_hover.png")),"btnBack_hover"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnCredits.png")),"btnCredits"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/btns/btnCredits_hover.png")),"btnCredits_hover"));
		
		Game.getGlobal().setImgs(imgs);
	}
}
