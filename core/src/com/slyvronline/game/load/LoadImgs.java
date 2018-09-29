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
		imgs.add(new Img(new Texture(Gdx.files.internal("data/logos/logoTimeUp.png")),"logoTimeUp"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/logos/logoScaledUp.png")),"logoScaledUp"));
		
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
		imgs.add(new Img(new Texture(Gdx.files.internal("data/bg/gameboard.png")),"gameboard"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/bg/gameboard_locations.png")),"gameboard_locations"));
		
		//Ents
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/blackhole.png")),"blackhole"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/blackhole-icon.png")),"blackhole-icon"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/bike.png")),"bike"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/bin.png")),"bin"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/box.png")),"box"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/building1.png")),"building1"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/building2.png")),"building2"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/bus1.png")),"bus1"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/bus2.png")),"bus2"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/bush1.png")),"bush1"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/bush2.png")),"bush2"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/car1.png")),"car1"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/car2.png")),"car2"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/church.png")),"church"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/cow.png")),"cow"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/dog.png")),"dog"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/fence.png")),"fence"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/fire.png")),"fire"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/firehydrant.png")),"firehydrant"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/flowers.png")),"flowers"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/grass1.png")),"grass1"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/grass2.png")),"grass2"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/house1.png")),"house1"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/house2.png")),"house2"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/house3.png")),"house3"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/man1.png")),"man1"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/man2.png")),"man2"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/statue.png")),"statue"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/stoplight.png")),"stoplight"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/streetlamp.png")),"streetlamp"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/tree1.png")),"tree1"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/tree2.png")),"tree2"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/tree3.png")),"tree3"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/tree4.png")),"tree4"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/tree5.png")),"tree5"));
		imgs.add(new Img(new Texture(Gdx.files.internal("data/ents/colosseum.png")),"colosseum"));
		
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
