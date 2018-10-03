package com.slyvronline.game.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class PingWebsite {

	public static void ping(int score){
		String strUrl = "http://172.221.151.178:8181/score.jsp?score="+score;
		try {
		         URL url = new URL(strUrl);
		         URLConnection urlConnection = url.openConnection();
		         HttpURLConnection connection = null;
		         if(urlConnection instanceof HttpURLConnection) {
		            connection = (HttpURLConnection) urlConnection;
		         }else {
		            System.out.println("Please enter an HTTP URL.");
		            return;
		         }
		         
		         BufferedReader in = new BufferedReader(
		            new InputStreamReader(connection.getInputStream()));
		         String urlString = "";
		         String current;
		         
		         while((current = in.readLine()) != null) {
		            urlString += current;
		         }
		         //System.out.println(urlString);
		         
		         System.out.println("Pinged Website");
		      } catch (IOException e) {
		         e.printStackTrace();
		      }
	}
}
