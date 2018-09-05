package com.slyvronline.game.load;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Font;

/**
 * @author Matt Schrum - slyvr89
 * @date 10/25/2013
 */
public class LoadFonts {

	public static void load(){
		ArrayList<Font> fonts = new ArrayList<Font>();
		
		fonts.add(new Font("Fixedys1",32,new BitmapFont(Gdx.files.internal("data/fonts/Fixedys1.fnt"),false)));
		fonts.add(new Font("Fixedys2",32,new BitmapFont(Gdx.files.internal("data/fonts/Fixedys2.fnt"),false)));
		fonts.add(new Font("Fixedys3",16,new BitmapFont(Gdx.files.internal("data/fonts/Fixedys3.fnt"),false)));
		
		fonts.add(new Font("Alexis8",8,new BitmapFont(Gdx.files.internal("data/fonts/Alexis8.fnt"),false)));
		fonts.add(new Font("Alexis12",12,new BitmapFont(Gdx.files.internal("data/fonts/Alexis12.fnt"),false)));
		fonts.add(new Font("Alexis16",16,new BitmapFont(Gdx.files.internal("data/fonts/Alexis16.fnt"),false)));
		fonts.add(new Font("Alexis32",32,new BitmapFont(Gdx.files.internal("data/fonts/Alexis32.fnt"),false)));
		
		fonts.add(new Font("AgencyFb12",12,new BitmapFont(Gdx.files.internal("data/fonts/AgencyFb12.fnt"),false)));
		fonts.add(new Font("AgencyFb16",16,new BitmapFont(Gdx.files.internal("data/fonts/AgencyFb16.fnt"),false)));
		fonts.add(new Font("AgencyFb24",24,new BitmapFont(Gdx.files.internal("data/fonts/AgencyFb24.fnt"),false)));
		fonts.add(new Font("AgencyFb32",32,new BitmapFont(Gdx.files.internal("data/fonts/AgencyFb32.fnt"),false)));
		fonts.add(new Font("AgencyFb64",64,new BitmapFont(Gdx.files.internal("data/fonts/AgencyFb64.fnt"),false)));
		
		fonts.add(new Font("AgencyFbGlow32",32,new BitmapFont(Gdx.files.internal("data/fonts/AgencyFbGlow32.fnt"),false)));
		
		fonts.add(new Font("Consolas12",12,new BitmapFont(Gdx.files.internal("data/fonts/Consolas12.fnt"),false)));
		fonts.add(new Font("Consolas16",16,new BitmapFont(Gdx.files.internal("data/fonts/Consolas16.fnt"),false)));
		fonts.add(new Font("Consolas24",24,new BitmapFont(Gdx.files.internal("data/fonts/Consolas24.fnt"),false)));
		fonts.add(new Font("Consolas32",32,new BitmapFont(Gdx.files.internal("data/fonts/Consolas32.fnt"),false)));
		fonts.add(new Font("Consolas40",64,new BitmapFont(Gdx.files.internal("data/fonts/Consolas40.fnt"),false)));
		fonts.add(new Font("Consolas64",64,new BitmapFont(Gdx.files.internal("data/fonts/Consolas64.fnt"),false)));
		fonts.add(new Font("Consolas72",64,new BitmapFont(Gdx.files.internal("data/fonts/Consolas72.fnt"),false)));
		fonts.add(new Font("Consolas128",64,new BitmapFont(Gdx.files.internal("data/fonts/Consolas128.fnt"),false)));
		
		Game.getGlobal().setFonts(fonts);
	}
}
