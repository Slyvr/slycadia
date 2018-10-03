package com.slyvronline.game.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.slyvronline.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		DisplayMode displayMode = LwjglApplicationConfiguration.getDesktopDisplayMode();
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "2018ArcadeJam - A Blackhole Ate My Town";
		
		//Set to windowed mode, resolution less than monitor fullscreen
		cfg.fullscreen = false;
		
		cfg.width = (int) (displayMode.width/1.5f);
		cfg.height = (int) (displayMode.height/1.5f);
		
		Game game = new Game();
		try{
			new LwjglApplication(game, cfg);
		}
		catch(Exception ex){
			System.out.println(ex);
			ex.printStackTrace();
		}
	}
}
