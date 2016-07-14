package com.doysoft.q_radio.lib;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;



import android.content.Context;
import android.content.res.AssetManager;



import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doysoft.q_radio.Conthing;
import com.doysoft.q_radio.Detail;
import com.doysoft.q_radio.RadioUi;
import com.doysoft.q_radio.Rcoradio;


public class JsonRecRadio {


	 public static  List<Conthing> lists; 

	 static List<Conthing> list = new ArrayList<Conthing>();

	 public  static HashMap map =null;
	 
	 public static HashMap<String, HashMap> map_arrDeta = new HashMap<String, HashMap>();


		public static String initdetaildata() {
			StringBuffer sb = new StringBuffer();
			AssetManager mAssetManager = Rcoradio.rContext.getAssets();
			try {
				InputStream is = mAssetManager.open("Recradio.json");
				byte[] data = new byte[is.available()];
				int len = -1;
				while ((len = is.read(data)) != -1) {
					sb.append(new String(data, 0, len, "UTF-8"));
				}
				is.close();
				return sb.toString();
				
			} catch (IOException e) {
				e.printStackTrace();

			}
			return sb.toString();
		}
		
		
	


	public static void setdetailData(String str) {
		    try {
		    	//  data = new ArrayList<Map<String, String>>();
		        JSONArray array = new JSONArray(str);
		       int len = (array).length();
		   
		      //    List myList = new ArrayList();

		
		        for (int i = 0; i < len; i++) {
		             JSONObject object = array.getJSONObject(i);
		            map = new HashMap<String, String>();
		        
		            
		            map.put("pic", object.getString("radio_pic"));
		            map.put("region", object.getString("radio_region"));
		            map.put("name", object.getString("radio_name"));
		            map.put("link", object.getString("radio_link"));
		         //   map.put("category", object.getString("radio_category"));
		            map_arrDeta.put(String.valueOf(i), map);
		        }
		       
		        
		       // map_arr.pu
		        
		        
		      // String a =  map.get("pic");
		        
		    } catch (JSONException e) {
		        e.printStackTrace();
		    }
	 }
	
}
