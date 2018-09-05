package com.slyvronline.game.utils;

import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.objects.Ent;

public class GameUtils {

	public GameUtils() {
		
	}
	
	public static double calcDistance(Rectangle pos1, Rectangle pos2){
		//Set to center point of entity
		pos1 = new Rectangle(pos1);
		pos2 = new Rectangle(pos2);
		pos1.setX(pos1.getX() + pos1.getWidth()/2);
		pos1.setY(pos1.getY() + pos1.getHeight()/2);
		pos2.setX(pos2.getX() + pos2.getWidth()/2);
		pos2.setY(pos2.getY() + pos2.getHeight()/2);
		return Math.sqrt(Math.pow((pos2.x - pos1.x), 2) + Math.pow((pos2.y - pos1.y), 2));
	}

}
