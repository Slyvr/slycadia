package com.slyvronline.game.desktop;

import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.slyvronline.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "2018ArcadeJam - A Blackhole Ate My Town";
		
		//Set 'borderless' fullscreen
		System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
		//Set to windowed mode, resolution less than monitor fullscreen
		cfg.fullscreen = false;
		
		cfg.width = LwjglApplicationConfiguration.getDesktopDisplayMode().width;
        cfg.height = LwjglApplicationConfiguration.getDesktopDisplayMode().height;
		
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
