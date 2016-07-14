package com.doysoft.q_radio.upgradejosn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class UpgradeVersion {

    public  HashMap map =null;
	 
	 public HashMap<String, HashMap> map_arrVer = new HashMap<String, HashMap>();
	 
	 public  String state;

	 String Verdata;
	int	Oldversion;
	int  Newversion;
	public  int getVerCode(Context context) {  
       int verCode = -1;  
       try {  
       verCode = context.getPackageManager().getPackageInfo(  
                   "com.example.q_radio", 0).versionCode;  
       Oldversion = verCode;
       } catch (NameNotFoundException e) {  
           Log.e("msg",e.getMessage());  
       }  
       return verCode;  
   }  
	
	
	public  String getVerName(Context context) {  
       String verName = "";  
       try {  
           verName = context.getPackageManager().getPackageInfo(  
                   "com.example.q_radio", 0).versionName;  
       } catch (NameNotFoundException e) {  
           Log.e("msg",e.getMessage());  
       }  
       return verName;     
       
	}
	
 
       public void startVersionCheck() {
			new Thread() {
				public void run() {
					HttpClient	client = new DefaultHttpClient();
					StringBuilder builder = new StringBuilder();
					HttpGet myget = new HttpGet("http://api.doysoft.com/v1/app_radio/radios/version");
				
					try {
						HttpResponse response = client.execute(myget);
						HttpEntity entity = response.getEntity();
						BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
						for (String s = reader.readLine(); s != null; s = reader.readLine()) {
							builder.append(s);
						}
						Verdata = builder.toString();
						
						judgeVer();
						getVer();
						readVer();
						compare();
						//writeVer();
					
					//	readVer();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
			
			
	 }
       
       
       public void compare(){
    	   if(Newversion>Oldversion){
    		   deleteVer();
    	   }else {
    		   
    	   }
       }
       
       
       public void judgeVer(){
  		 boolean fileExists = fileIsExists();
  		if(!fileExists){
  			writeVer();
  		 }else{
  			 
  		 }
  	 }
       
       public boolean fileIsExists(){
           try{
          	 File file = new File("/data/data/com.doysoft.app.radio/files/Verson.text");
      		 if(!file.exists()){
                           return false;
                   }
                   
           }catch (Exception e) {
                   // TODO: handle exception
                   return false;
           }
          
           
           
           return true;
   }
       
       
       String Vnumber;
	    public void getVer(){
		    
	    	 try {
				JSONArray array = new JSONArray(Verdata);
				  int len = (array).length();
				   
			      //    List myList = new ArrayList();
		             JSONObject object = array.getJSONObject(0);
				       	Vnumber= object.getString("radio_version");
				       	Newversion =Integer.parseInt(Vnumber);
			
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		 
	   /* 	if(Vnumber>Oldversion){
	    		  state = "up";
	    		  Oldversion = 2;
	    	}else {
	    		 state = "keep";
	    	}*/
	    	 
	}
	
	    public void deleteVer(){
			File verfile = new File("/data/data/com.doysoft.app.radio/files/Verson.text");
			File recfile = new File("/data/data/com.doysoft.app.radio/files/data2.text");
			File allfile = new File("/data/data/com.doysoft.app.radio/files/alldata.text");
				verfile.delete();	
				recfile.delete();	
				allfile.delete();
	    }
	    
	    
	 /*  public void delete(){
		   
   	  //  String fileName = "/data/data/com.example.q_radio/files/data2.text";
			File recfile = new File("/data/data/com.example.q_radio/files/data2.text");
			File allfile = new File("/data/data/com.example.q_radio/files/alldata.text");
			if(recfile.exists()){
				recfile.delete();	
			}
				if(allfile.exists()){
					allfile.delete();
				}
		   
	   }*/
	    
	   public void writeVer(){

	          File fi = new File("/data/data/com.doysoft.app.radio/files/");
	   	   if (!fi.exists()) {  
	              fi.mkdirs();  
	   	   }
	   	   
	   	   String fileName = "/data/data/com.doysoft.app.radio/files/Verson.text";
	   		String  data = Verdata;
	   			File file = new File("/data/data/com.doysoft.app.radio/files/Verson.text");
	   		 FileOutputStream fout;
			      
	   			    	try {
	   		    			//file.createNewFile();
		
	   						fout = new FileOutputStream(fileName);
	   						byte[] buffer = new byte[1024];
	   				    	int length;
	   				    	FileOutputStream fos = new FileOutputStream(file);   
	   		                fos.write(data.getBytes());   
	   			    		
	   			                fos.close();
	   						
	   						
	   					/*	fout = new FileOutputStream(file);
	   						
	   			    		  fout = ct.getApplicationContext().openFileOutput(fileName, Context.MODE_APPEND);
	   			    		  dos = new DataOutputStream(fos); 
	   					       
	   							
	   							dos.writeUTF(data);
	   						
	   						   dos.close();   
	   			                fos.close(); */
	   					} catch (FileNotFoundException e) {
	   						// TODO Auto-generated catch block
	   						e.printStackTrace();
	   					}catch (IOException e) {
	   						// TODO Auto-generated catch block
	   						e.printStackTrace();
	   					}
	   		    		 // code above is equivalent to below:

	   			    

	   }
	 
		public void readVer() throws IOException, JSONException{
	    	   String fileName = "/data/data/com.doysoft.app.radio/files/Verson.text";

			  
				      // --------------单纯用file来读入的方式-----------------   
				    //   fis =  ct.getApplicationContext().openFileInput("wdata2.txt");   
	    	   FileInputStream fin =  new FileInputStream(fileName);
	    	   int length = fin.available();
	            byte[] buffer = new byte[length];
	            fin.read(buffer);
	          String  temp = EncodingUtils.getString(buffer, "UTF-8");////依Y.txt的编码类型选择合适的编码，如果不调整会乱码
	            fin.close();  
	        	JSONArray array = new JSONArray(temp);
				  int len = (array).length();
				   
			      //    List myList = new ArrayList();
		             JSONObject object = array.getJSONObject(0);
		           Oldversion= Integer.parseInt(object.getString("radio_version"));
	          
				

			  
		}
	   
}


