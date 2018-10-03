package com.slyvronline.game.load;

import java.util.ArrayList;
import java.util.Random;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.slyvronline.game.objects.ents.Consumable;
import com.slyvronline.game.utils.GameUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.ents.*;

public class LoadGameboardEnts {

	public static ArrayList<Consumable> loadEnts(){
		ArrayList<Consumable> ents = new ArrayList<Consumable>();
		
		FileHandle fh = Gdx.files.internal("data/levels/levelDataFile.xml");
		String xmlContent = fh.readString();
		Document doc = GameUtils.loadXMLFromString(xmlContent);
		if (doc != null){
			NodeList nlEnt = doc.getElementsByTagName("ent");
			for (int i = 0; i < nlEnt.getLength(); i++) {
				Node nNode = nlEnt.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					String name = eElement.getElementsByTagName("name").item(0).getTextContent();
					String x = eElement.getElementsByTagName("x").item(0).getTextContent();
					String y = eElement.getElementsByTagName("y").item(0).getTextContent();
					
					Consumable c = new Consumable(Game.getGlobal().getConsumableByName(name));
					c.setName(name);
					c.setX(Float.parseFloat(x));
					c.setY(Float.parseFloat(y));
					ents.add(c);
				}
			}
		}
		
		return ents;
	}
}
