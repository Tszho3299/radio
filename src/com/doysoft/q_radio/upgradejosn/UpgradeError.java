package com.doysoft.q_radio.upgradejosn;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.doysoft.app.radio.R;
import com.doysoft.app.radio.R.id;
import com.doysoft.app.radio.R.layout;
import com.doysoft.app.radio.R.menu;
import com.doysoft.q_radio.RadioUi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class UpgradeError extends Activity {

	//EditText edName;
	//EditText edRegion;
	Button btm;
	public void init(){
	//	edName = (EditText)findViewById(R.id.et1);
	//	edRegion = (EditText)findViewById(R.id.et2);
	//	btm = (Button)findViewById(R.id.sub);
	}
	
	
	public void submitData(){
		new Thread(){
			public void run(){	
				String postReceiverUrl = "http://192.168.0.103/error.php";

				HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(postReceiverUrl);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("errorname",RadioUi.edna));
				nameValuePairs.add(new BasicNameValuePair("errorregion",RadioUi.edre));
				 
				try {
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					// execute HTTP post request
					HttpResponse response = httpClient.execute(httpPost);
					HttpEntity resEntity = response.getEntity();
					 
					if (resEntity != null) { 				     
					    String responseStr = EntityUtils.toString(resEntity).trim();		
					}
					
				}  catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		}.start();
	}
	
	public void submit(){
		
		/*	String submit = "";
		String path = "http://192.168.0.103/Errorreport.php";
		JSONObject jsonObject = new JSONObject();
		HttpPost httpPost = new HttpPost(path);
		httpPost.setHeader("Content-Type",
		"application/x-www-form-urlencoded; charset=utf-8");
		
		
		
		try {
		//Post参数
		//	RadioUi ra= new RadioUi();
			//ra.edname();
		jsonObject.put("name",RadioUi.edna);
		jsonObject.put("region",RadioUi.edre);

		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
		nameValuePair.add(new BasicNameValuePair("param", jsonObject.toString()));
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair,HTTP.UTF_8));
		//获取HttpClient对象
		HttpClient httpClient = new DefaultHttpClient();
		// 获取HttpResponse实例
		HttpResponse httpResp = httpClient.execute(httpPost);
		submit = EntityUtils.toString(httpResp.getEntity());
		Log.v("txt", jsonObject.toString());
		 
		} catch (Exception e) {
		e.printStackTrace();
		}
		*/
	
		
	}
	
	public void submit1(){
		
		String url="";
		 try {  
	            //得到HttpClient对象  
	            HttpClient getClient = new DefaultHttpClient();  
	            //得到HttpGet对象  
	            HttpGet request = new HttpGet(url);  
	            //客户端使用GET方式执行请教，获得服务器端的回应response  
	            HttpResponse response = getClient.execute(request);  
	            //判断请求是否成功    
	            if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){  
	              //  Log.i(TAG_STRING, "请求服务器端成功");  
	                //获得输入流  
	                InputStream  inStrem = response.getEntity().getContent();  
	                int result = inStrem.read();  
	                while (result != -1){  
	                    System.out.print((char)result);  
	                    result = inStrem.read();  
	                }  
	                //关闭输入流  
	                inStrem.close();      
	            }else {  
	              //  Log.i(TAG_STRING, "请求服务器端失败");  
	            }             
	        } catch (Exception e) {  
	            // TODO Auto-generated catch block  
	            e.printStackTrace();  
	        }  
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upgradeerror);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.upgradeerror, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
