package com.slyvronline.game;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.slyvronline.game.controllers.GameController;
import com.slyvronline.game.load.LoadConsumables;
import com.slyvronline.game.load.LoadFonts;
import com.slyvronline.game.load.LoadImgs;
import com.slyvronline.game.load.LoadMenus;
import com.slyvronline.game.load.LoadMusic;
import com.slyvronline.game.load.LoadSfx;
import com.slyvronline.game.objects.Global;

public class Game extends ApplicationAdapter {
	
	private static Global global;
	private static Map<String,String> gameConfig;
	
	@Override
	public void create() {
		loadConfigs();
		
		global = new Global();
		global.setDefaultScreenWidth(Gdx.graphics.getWidth());
		global.setDefaultScreenHeight(Gdx.graphics.getHeight());
		OrthographicCamera cam = new OrthographicCamera(global.getDefaultScreenWidth(),global.getDefaultScreenHeight());
		cam.position.set(0,0,0);
		cam.update();
		global.setCamera(cam);
		
		DisplayMode displayMode = Gdx.graphics.getDisplayMode();
		
		if (displayMode != null)
			this.writeLog("info", "Monitor Resolution: "+displayMode.width+"x"+displayMode.height);
		
		this.writeLog("info", "Game Resolution: "+Gdx.graphics.getWidth()+"x"+Gdx.graphics.getHeight());
		
		LoadImgs.load();
		LoadConsumables.load();
		LoadFonts.load();
		LoadMenus.load();
		LoadSfx.load();
		LoadMusic.load();
		
		loadControllers();
		
		global.setCurrentTrack(global.getTrackByName("knock"));
		global.getCurrentTrack().play();
		global.getCurrentTrack().getMusic().setLooping(true);
		
		//Initial load of levelData in case it was overwritten
		if (this.getConfig("reloadLevel").equals("true")){
			boolean rewriteLevelData = false;
			FileHandle fh = Gdx.files.local("data/levels/levelDataFile.xml");
			FileHandle fh2 = Gdx.files.internal("data/levels/levelDataFile_bak.xml");
			FileHandle fh3 = Gdx.files.local("data/levels/levelDataFile"+System.currentTimeMillis()+".xml");
			String fhContent = fh.readString();
			String fh2Content = fh.readString();
			if (!fh2Content.equals(fhContent) && rewriteLevelData){
				fh3.writeString(fhContent, false);
				fh.writeString(fh2Content, false);
			}
		}
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		SpriteBatch batch = global.getBatch();
		SpriteBatch menuBatch = global.getMenuBatch();
		
		//UPDATES
		global.getCamera().update();
		global.getCurrentMenu().update(global.getStateTime());
		if (global.getGame() != null){
			global.getGame().update();
		}
		
		//RENDERS
		batch.setProjectionMatrix(global.getCamera().combined);
		batch.begin();
		if (global.getGame() != null){
			global.getGame().render(batch);
		}
		batch.end();
		
		menuBatch.begin();
		global.getCurrentMenu().render(menuBatch);
		menuBatch.end();
		
		//System.out.println(Gdx.graphics.getFramesPerSecond());
		
	}
	
	private void loadConfigs(){
		gameConfig = new HashMap<String,String>();
		FileHandle fh = Gdx.files.internal("data/game_config.txt");
		String[] configs = fh.readString().replace("\r","").split("\n");
		for(String config : configs){
			String[] configData = config.split("=");
			gameConfig.put(configData[0].replace("=",""), configData[1].replace("=",""));
		}
	}
	
	public static String getConfig(String key){
		return gameConfig.get(key);
	}
	
	private void handleCameraInput() {

		OrthographicCamera cam = (OrthographicCamera) global.getCamera();
		if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
			cam.zoom += 0.01;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
			cam.zoom -= 0.01;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			cam.translate(-15, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			cam.translate(15, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			cam.translate(0, -15, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
			cam.translate(0, 15, 0);
		}
//		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//			cam.rotate(-0.5f, 0, 0, 1);
//		}
//		if (Gdx.input.isKeyPressed(Input.Keys.E)) {
//			cam.rotate(0.5f, 0, 0, 1);
//		}
		
		//max zoom out
		//cam.zoom = MathUtils.clamp(cam.zoom, 0.1f, world_height/cam.viewportHeight);
		float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
		float effectiveViewportHeight = cam.viewportHeight * cam.zoom;
		//cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, world_width - effectiveViewportWidth / 2f);
		//cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, world_height - effectiveViewportHeight / 2f);
	}
	
	
	@Override
	public void resize(int width, int height) {
		float aspectRatio = (float)width/(float)height;
		float virtualAspectRatio = global.getDefaultScreenWidth()/global.getDefaultScreenHeight();
        float scale = 1f;
        Vector2 crop = new Vector2(0f, 0f);
        Vector2 cameraCrop = new Vector2(0f, 0f);
        if(aspectRatio > virtualAspectRatio)
        {
            scale = (float)height/(float)global.getDefaultScreenHeight();
            crop.x = (width - global.getDefaultScreenWidth()*scale)/2f;
            cameraCrop.x = (width - global.getCamera().viewportWidth * scale)/2f;
        }
        else if(aspectRatio < virtualAspectRatio)
        {
            scale = (float)width/(float)global.getDefaultScreenWidth();
            crop.y = (height - global.getDefaultScreenHeight()*scale)/2f;
            cameraCrop.y = (height - global.getCamera().viewportHeight*scale)/2f;
        }
        else
        {
            scale = (float)width/(float)global.getDefaultScreenWidth();
        }
 
        float w = (float)global.getDefaultScreenWidth()*scale;
        float h = (float)global.getDefaultScreenHeight()*scale;
        global.setViewport(new Rectangle(crop.x, crop.y, w, h));
        
        //global.getCamera().viewportHeight = 30f * height/width;
		//global.getCamera().viewportWidth = 30f;
        global.getCamera().viewportHeight = h;
        global.getCamera().viewportWidth = w;
        global.getCamera().position.x = cameraCrop.x;
        global.getCamera().position.y = cameraCrop.y;
		global.getCamera().update();
	}
	

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public static Global getGlobal(){
		return global;
	}
	
	public static void writeLog(String type, Exception ex){
		writeLog(type, ex.getMessage());
	}
	public static void writeLog(String type, String text){
		Gdx.app.log(type, text);
	}
	public static void writeLog(String text){
		writeLog("Unknown",text);
	}
	
	public static void loadControllers(){
		for(Controller controller : Controllers.getControllers()){
			controller.addListener(new GameController());
		}
	}
}
