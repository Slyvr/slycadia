package com.slyvronline.game.load;

import java.util.ArrayList;
import java.util.Random;

import com.slyvronline.game.objects.ents.Consumable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.ents.*;

public class LoadGameboardEnts {

	public static ArrayList<Consumable> loadEnts(){
		ArrayList<Consumable> ents = new ArrayList<Consumable>();
		
		Texture tex = Game.getGlobal().getImgByName("gameboard_locations").getTex();
		tex.getTextureData().prepare();
		Pixmap pixmap = tex.getTextureData().consumePixmap();
		
		for(int y=0; y<tex.getHeight(); y++){
			for(int x=0; x<tex.getWidth(); x++){
				Color color = new Color(pixmap.getPixel(x, y));
				int posX = x;
				int posY = tex.getHeight() - y;
				if (color.r == 1.0f && color.g == 0.0f && color.b == 0.0f){
					System.out.println(x+":"+y+" COLOR_RED:"+color.r+" - "+color.g+" - "+color.b);
					Stoplight stoplight = new Stoplight();
					stoplight.setX(posX);
					stoplight.setY(posY);
					ents.add(stoplight);
				}
				if (color.r == 0.00f && color.g == 0.0f && color.b == 1.0f){
					System.out.println(x+":"+y+" COLOR_BLUE:"+color.r+" - "+color.g+" - "+color.b);
					Dog dog = new Dog();
					dog.setX(posX);
					dog.setY(posY);
					ents.add(dog);
				}
				if (color.r == 0.00f && color.g == 1.0f && color.b == 0.0f){
					System.out.println(x+":"+y+" COLOR_GREEN:"+color.r+" - "+color.g+" - "+color.b);
					Consumable house = new House1();
					int randInt = new Random().nextInt(3);
					if (randInt == 1){
						house = new House2();
					}
					else if (randInt == 2){
						house = new House3();
					}
					house.setX(posX);
					house.setY(posY);
					ents.add(house);
				}
				if (color.r == 0.00f && color.g == 1.0f && color.b == 1.0f){
					System.out.println(x+":"+y+" COLOR_TEAL:"+color.r+" - "+color.g+" - "+color.b);
					Consumable tree = new Tree1();
					int randInt = new Random().nextInt(3);
					if (randInt == 1){
						tree = new Tree2();
					}
					else if (randInt == 2){
						tree = new Tree3();
					}
					tree.setX(posX);
					tree.setY(posY);
					ents.add(tree);
				}
				if (color.r == 1.00f && color.g == 1.0f && color.b == 0.0f){
					System.out.println(x+":"+y+" COLOR_YELLOW:"+color.r+" - "+color.g+" - "+color.b);
					Consumable church = new Church();
					church.setX(posX);
					church.setY(posY);
					ents.add(church);
				}
				if (color.r == 1.00f && color.g == 0.0f && color.b == 1.0f){
					System.out.println(x+":"+y+" COLOR_PINK:"+color.r+" - "+color.g+" - "+color.b);
					Consumable building = new Building1();
					building.setX(posX);
					building.setY(posY);
					ents.add(building);
				}
				if (color.r == 1.00f && color.g == 1.0f && color.b < 1.0f && color.b > 0.3f){
					System.out.println(x+":"+y+" COLOR_YELLOW-ish:"+color.r+" - "+color.g+" - "+color.b); //b=150
					Consumable consumable = new Bush1();
					int randInt = new Random().nextInt(5);
					if (randInt == 1){
						consumable = new Bush2();
					}
					else if (randInt == 2){
						consumable = new Grass1();
					}
					else if (randInt == 3){
						consumable = new Grass2();
					}
					else if (randInt == 4){
						consumable = new Flowers();
					}
					consumable.setX(posX);
					consumable.setY(posY);
					ents.add(consumable);
				}
				if (color.r == 1.00f && color.g < 1.0f && color.g > 0.3f && color.b == 1.0f){
					System.out.println(x+":"+y+" COLOR_PINK-ish:"+color.r+" - "+color.g+" - "+color.b); //g=150
					Consumable consumable = new Man1();
					int randInt = new Random().nextInt(3)+1;
					if (randInt == 1){
						consumable = new Man2();
					}
					consumable.setX(posX);
					consumable.setY(posY);
					ents.add(consumable);
				}
				if (color.r < 1.00f && color.r > 0.3f && color.g < 1.0f && color.g > 0.3f && color.b == 1.0f){
					System.out.println(x+":"+y+" COLOR_PURPLE:"+color.r+" - "+color.g+" - "+color.b); //g=150 r=150
					Consumable consumable = new Building2();
					consumable.setX(posX);
					consumable.setY(posY);
					ents.add(consumable);
				}
				if (color.r == 0.0f && color.g < 1.0f && color.g > 0.3f && color.b < 1.0f && color.b > 0.3f){
					System.out.println(x+":"+y+" COLOR_DARKTEAL:"+color.r+" - "+color.g+" - "+color.b); //g=150 r=0 b=150
					Consumable consumable = new Car1();
					int randInt = new Random().nextInt(3)+1;
					if (randInt == 1){
						consumable = new Bus1();
					}
					consumable.setX(posX);
					consumable.setY(posY);
					ents.add(consumable);
				}
				if (color.r == 1.0f && color.g < 1.0f && color.g > 0.3f && color.b < 1.0f && color.b > 0.3f){
					System.out.println(x+":"+y+" COLOR_SALMON:"+color.r+" - "+color.g+" - "+color.b); //g=150 b=150
					Consumable consumable = new Car2();
					int randInt = new Random().nextInt(3)+1;
					if (randInt == 1){
						consumable = new Bus2();
					}
					consumable.setX(posX);
					consumable.setY(posY);
					ents.add(consumable);
				}
				if (color.r == 0.0f && color.g == 0.0f && color.b < 1.0f && color.b > 0.9f){
					System.out.println(x+":"+y+" COLOR_DARKBLUE:"+color.r+" - "+color.g+" - "+color.b); //r=0 g=0 b=150
					Consumable consumable = new Colosseum();
					int randInt = new Random().nextInt(3)+1;
					if (randInt == 1){
						consumable = new Bus2();
					}
					consumable.setX(posX);
					consumable.setY(posY);
					ents.add(consumable);
				}
			}
		}
		
		
		return ents;
	}
}
