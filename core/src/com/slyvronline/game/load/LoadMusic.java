package com.slyvronline.game.load;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.AudioTrack;

public class LoadMusic {

	public static void load(){
		ArrayList<AudioTrack> tracks = new ArrayList<AudioTrack>();
		
		AudioTrack odyssey = new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/Chiptune_Artic_Odyssey_teknoaxe.mp3")), "odyssey");
		odyssey.setArtistName("Teknoaxe");
		odyssey.setContactText("http://teknoaxe.com");
		odyssey.setTrackName("Artic Odyssey");
		odyssey.setLoop(true);
		odyssey.setPan(1);
		odyssey.setPosition(0);
		odyssey.setPitch(1);
		odyssey.setVolume(0.5f);
		tracks.add(odyssey);
		
		AudioTrack shopping = new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/Chiptune_Shopping_Adventures_teknoaxe.mp3")), "shopping");
		shopping.setArtistName("Teknoaxe");
		shopping.setContactText("http://teknoaxe.com");
		shopping.setTrackName("Shopping Adventures");
		shopping.setLoop(true);
		shopping.setPan(1);
		shopping.setPosition(0);
		shopping.setPitch(1);
		shopping.setVolume(0.5f);
		tracks.add(shopping);
		
		AudioTrack ducksoup = new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/DuckSoup_pupleplanet.mp3")), "ducksoup");
		ducksoup.setArtistName("PurplePlanet");
		ducksoup.setContactText("http://www.purple-planet.com/");
		ducksoup.setTrackName("Duck Soup");
		ducksoup.setLoop(true);
		ducksoup.setPan(1);
		ducksoup.setPosition(0);
		ducksoup.setPitch(1);
		ducksoup.setVolume(0.5f);
		tracks.add(ducksoup);
		
		Game.getGlobal().setTracks(tracks);
	}
}
