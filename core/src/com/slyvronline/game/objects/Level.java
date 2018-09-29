package com.slyvronline.game.objects;

import java.util.ArrayList;

import com.slyvronline.game.objects.tiles.Tile;

public class Level {

	private String name;
	private ArrayList<Tile> tiles0;
	private ArrayList<Tile> tiles1;
	private ArrayList<Tile> tiles2;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Tile> getTiles0() {
		return tiles0;
	}
	public void setTiles0(ArrayList<Tile> tiles0) {
		this.tiles0 = tiles0;
	}
	public ArrayList<Tile> getTiles1() {
		return tiles1;
	}
	public void setTiles1(ArrayList<Tile> tiles1) {
		this.tiles1 = tiles1;
	}
	public ArrayList<Tile> getTiles2() {
		return tiles2;
	}
	public void setTiles2(ArrayList<Tile> tiles2) {
		this.tiles2 = tiles2;
	}
	
}
