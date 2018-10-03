package com.slyvronline.game.load;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.ents.Consumable;
import com.slyvronline.game.utils.GameUtils;

public class LoadConsumables {

	public static void load(){
		ArrayList<Consumable> consumables = new ArrayList<Consumable>();
		
		FileHandle fh = Gdx.files.internal("data/game_entities/ent_metadata.xml");
		String xmlContent = fh.readString();
		Document doc = GameUtils.loadXMLFromString(xmlContent);
		if (doc != null){
			NodeList nlEnt = doc.getElementsByTagName("ent");
			for (int i = 0; i < nlEnt.getLength(); i++) {
				Node nNode = nlEnt.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					String name = eElement.getElementsByTagName("name").item(0).getTextContent();
					String size = eElement.getElementsByTagName("size").item(0).getTextContent();
					String counterMax = eElement.getElementsByTagName("counterMax").item(0).getTextContent();
					String counterMaxRand = eElement.getElementsByTagName("counterMaxRand").item(0).getTextContent();
					String animateCountUp = eElement.getElementsByTagName("animateCountUp").item(0).getTextContent();
					String animateAmt = eElement.getElementsByTagName("animateAmt").item(0).getTextContent();
					String animateSpeed = eElement.getElementsByTagName("animateSpeed").item(0).getTextContent();
					NodeList nlAnimations = eElement.getElementsByTagName("animations").item(0).getChildNodes();
					ArrayList<String> animations = new ArrayList<String>();
					
					for(int x=0; x<nlAnimations.getLength(); x++){
						Node n = nlAnimations.item(x);
						NodeList nlAnimations2 = n.getChildNodes();
						for(int r=0; r<nlAnimations2.getLength(); r++){
							Node n2 = nlAnimations2.item(r);
							animations.add(n2.getNodeValue());
						}
					}
					
					Consumable c = new Consumable();
					c.setName(name);
					c.setSize(Integer.parseInt(size));
					c.setAnimateCounterMax(Integer.parseInt(counterMax)+new Random().nextInt(Integer.parseInt(counterMaxRand)));
					if (animateCountUp.equals("true")) c.setAnimateCountUp(true);
					else c.setAnimateCountUp(false);
					c.setAnimateAmount(Float.parseFloat(animateAmt));
					c.setAnimateSpeed(Integer.parseInt(animateSpeed));
					c.setAnimates(animations);
					c.setImg(Game.getGlobal().getImgByName(name));
					c.setPosBox(new Rectangle());
					c.setWidth(c.getImg().getTex().getWidth());
					c.setHeight(c.getImg().getTex().getHeight());
					consumables.add(c);
				}
			}
		}
		
		Game.getGlobal().setConsumables(consumables);
	}
}
