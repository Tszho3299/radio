package com.doysoft.q_radio.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doysoft.q_radio.Catething;
import com.doysoft.q_radio.Conthing;
import com.doysoft.q_radio.Detail;
import com.doysoft.q_radio.RadioUi;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;



public class JsonRadio {


	public static List<Map<String, String>> data;
	 public static  List<Catething> lists; 

	 static List<Catething> list = new ArrayList<Catething>();
	 
	public  static HashMap map=null;
	 
   public static HashMap<String, HashMap> map_arr = new HashMap<String, HashMap>();

	
	 
	/* public String getJson(String fileName) {


	       StringBuilder stringBuilder = new StringBuilder();
	       try {
	           BufferedReader bf = new BufferedReader(new InputStreamReader(
	                   context.getAssets().open(fileName)));
	           String line;
	           while ((line = bf.readLine()) != null) {
	               stringBuilder.append(line);
	           }
	       } catch (IOException e) {
	           e.printStackTrace();
	       }
	       return stringBuilder.toString();
	   }
	
	*/
   
  
   
   
	/*	public static String initdata() {
			StringBuffer sb = new StringBuffer();
			AssetManager mAssetManager = Detail.mContext.getAssets();
			try {
				InputStream is = mAssetManager.open("radio.json");
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
		
		*/
	


	public static void setData(String str) {
		    try {
		    	//  data = new ArrayList<Map<String, String>>();
		    //	map_arr=null;
		        JSONArray array = new JSONArray(str);
		       int len = (array).length();
		   
		     //     List myList = new ArrayList();

		
		        for (int i = 0; i < len; i++) {
		             JSONObject object = array.getJSONObject(i);
		            map = new HashMap<String, String>();
		        
		            
		            map.put("pic", object.getString("radio_pic"));
		            map.put("region", object.getString("radio_region"));
		            map.put("name", object.getString("radio_name"));
		            map.put("link", object.getString("radio_link"));
		            map.put("category", object.getString("radio_category"));

		            map_arr.put(String.valueOf(i), map);
		        }
		       
		        
		       // map_arr.pu
		        
		        
		      // String a =  map.get("pic");
		        
		    } catch (JSONException e) {
		        e.printStackTrace();
		    }
	 }
	
	/*  public static void main() { 
		  try {
	            InputStreamReader inputStreamReader = new InputStreamReader(Detail.mContext.getAssets().open("radio.json"), "UTF-8");
	            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	            String line;
	            StringBuilder stringBuilder = new StringBuilder();
	            while((line = bufferedReader.readLine()) != null) {
	                stringBuilder.append(line);
	            }
	            bufferedReader.close();
	            inputStreamReader.close();
	            JSONArray  json = new JSONArray (stringBuilder.toString());
	    //        Log.i("TESTJSON", "cat=" + jsonObject.getString("cat"));
	        //    JSONArray jsonArray = jsonObject.getJSONArray();
	            for (int i = 0; i < json.length(); i++) {
	                JSONObject object = json.getJSONObject(i);
	                
	                Log.i("TESTJSON", "----------------");
	                Log.i("TESTJSON", "pic" + object.getString("radio_pic"));
	                Log.i("TESTJSON", "region" + object.getString("radio_region"));
	                Log.i("TESTJSON", "name" + object.getString("radio_name"));
	                Log.i("TESTJSON", "link" + object.getString("radio_link"));
	           
    }
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }
	  }
	  

	 */
	  
/*	public static String ReadFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		// 构造最后返回的json串
		String laststr = "";
		try {
			// 以行为单位读取文件内容，一次读一整行：
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 拼接数据信息
				laststr = laststr + tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}*/
	
	/*public String ReadFile(String Path){
		BufferedReader reader = null;
		String laststr = "";
		try{
		FileInputStream fileInputStream = new FileInputStream(Path);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
		reader = new BufferedReader(inputStreamReader);
		String tempString = null;
		while((tempString = reader.readLine()) != null){
		laststr += tempString;
		}
		reader.close();
		}catch(IOException e){
		e.printStackTrace();
		}finally{
		if(reader != null){
		try {
		reader.close();
		} catch (IOException e) {
		e.printStackTrace();
		}
		}
		}
		return laststr;
		}

	*/
	
	
	
	
	
/*	
public static List<Conthing>list( String path) throws Exception {
		
		// 文件存储路径
		File dataFile = new File(path);
		// 如果不存在，创建新文件
		
		List<Conthing> list = new ArrayList<Conthing>();
		// 获取JSON数据的字符串
		String JsonContext = ReadFile(path);
		JSONArray jsonArray;
		// 判断文件内是否有数据
		if (JsonContext.length() > 0) {
			// 获取文件JSON对象
			JSONObject json = JSONObject.fromObject(JsonContext);
			// 获取文件JSON数组对象
			jsonArray = json.getJSONArray("radio");
			int size = jsonArray.size();
			for (int i = 0; i < size; i++) {
				// 获取jsonObject数据对象
				JSONObject jsonObject = jsonArray.getJSONObject(i);
			
			// 数据添加到list里面
				list.add(new Conthing());
			}
		}
		return list;
	}
	*/
}
