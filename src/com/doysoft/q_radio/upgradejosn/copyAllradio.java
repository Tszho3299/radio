package com.doysoft.q_radio.upgradejosn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EncodingUtils;

public class copyAllradio {

	 HttpClient client;
	    StringBuilder builder ;
	 String allurl;
	
	 public void startAllUrlCheck() {
			new Thread() {
				public void run() {
					client = new DefaultHttpClient();
					 builder = new StringBuilder();
					HttpGet myget = new HttpGet("http://192.168.0.108/radio.php");
					
				
					//http://192.168.0.105/json.php
					try {
						HttpResponse response = client.execute(myget);
						HttpEntity entity = response.getEntity();
						BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
						for (String s = reader.readLine(); s != null; s = reader.readLine()) {
							builder.append(s);
						}
						allurl = builder.toString();
						judgeAll();
					

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
			
			
	 }
	
	 public boolean AllfileIsExists(){
	        try{
	       	 File Allfile = new File("/data/data/com.doysoft.app.radio/files/data2.txt");
	   		 if(!Allfile.exists()||Allfile.list().length==0){
	                        return false;
	                }
	                
	        }catch (Exception e) {
	                // TODO: handle exception
	                return false;
	        }
	       
	        
	        
	        return true;
	}
	 
	 
	 public void judgeAll(){
		 boolean fileExists = AllfileIsExists();
		if(!fileExists){
			writeall();
		 }else{
			 
		 }
	 }
	 
	  public void writeall(){
	    	 //  RadioUi ct new RadioUi();
	   	
	           File fi = new File("/data/data/com.doysoft.app.radio/files/");
	    	   if (!fi.exists()) {  
	               fi.mkdirs();  
	    	   }
	    	   
	    	   String fileName = "/data/data/com.doysoft.app.radio/files/Alldata.text";
	    		String  Alldata = allurl;
	    			File file = new File("/data/data/com.doysoft.app.radio/files/Alldata.text");
	    		 FileOutputStream fout;
			      
	    			    	try {
	    		    			//file.createNewFile();
		
	    						fout = new FileOutputStream(fileName);
	    						byte[] buffer = new byte[1024];
	    				    	int length;
	    				    	FileOutputStream  fos = new FileOutputStream(file);   
	    		                fos.write(Alldata.getBytes());   
	    			    		
	    			                fos.close();
	    						
	    			
	    					} catch (FileNotFoundException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}catch (IOException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    		    		 // code above is equivalent to below:
   		 
	       }
	 
	  
		public String readall() throws IOException{
	    	   String fileName = "/data/data/com.doysoft.app.radio/files/Alldata.text";

			  
				      // --------------单纯用file来读入的方式-----------------   
				    //   fis =  ct.getApplicationContext().openFileInput("wdata2.txt");   
	    	   FileInputStream fin =  new FileInputStream(fileName);
	    	   int length = fin.available();
	            byte[] buffer = new byte[length];
	            fin.read(buffer);
	          String  Alltemp = EncodingUtils.getString(buffer, "UTF-8");////依Y.txt的编码类型选择合适的编码，如果不调整会乱码
	            fin.close();//
	
				  return Alltemp;

			  
		}
	
	 
	 
	 
	 
	
}
