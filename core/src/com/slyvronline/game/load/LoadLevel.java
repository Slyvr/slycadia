package com.slyvronline.game.load;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.Level;
import com.slyvronline.game.objects.TwoDAdventureInstance;
import com.slyvronline.game.objects.tiles.StoneFloorTile;
import com.slyvronline.game.objects.tiles.Tile;

public class LoadLevel {

	public static void loadAll(){
		ArrayList<Level> levels = new ArrayList<Level>();
		
		levels.add(loadLevel(0));
		
		TwoDAdventureInstance game = (TwoDAdventureInstance) Game.getGlobal().getGame();
		game.setLevels(levels);
		game.setCurrentLevel(levels.get(0));
	}
	
	public static Level loadLevel(int id){
		FileHandle file = Gdx.files.internal("data/levels/level"+id+"_0.txt");
		String level0 = file.readString();
		file = Gdx.files.internal("data/levels/level"+id+"_1.txt");
		String level1 = file.readString();
		file = Gdx.files.internal("data/levels/level"+id+"_2.txt");
		String level2 = file.readString();
		
		Level level = new Level();
		level.setName("level"+id);
		
		int levelWidth = level0.indexOf("\n")-1;
		int levelHeight = level0.lastIndexOf("\n")/levelWidth;
		
		ArrayList<Tile> tiles0 = new ArrayList<Tile>();
		for(char c : level0.toCharArray()){
			Tile t = translateCharToTile(c);
			tiles0.add(t);
		}
		
		ArrayList<Tile> tiles1 = new ArrayList<Tile>();
		for(char c : level1.toCharArray()){
					
		}

		ArrayList<Tile> tiles2 = new ArrayList<Tile>();
		for(char c : level2.toCharArray()){
			
		}
		
		level.setTiles0(tiles0);
		level.setTiles1(tiles1);
		level.setTiles2(tiles2);
		
		return level;
	}
	
	public static Tile translateCharToTile(char c){
		Tile tile = null;
		if (c == '.'){
			tile = new StoneFloorTile();
		}
		else if (c == '1'){
			
		}
		return tile;
	}
}
