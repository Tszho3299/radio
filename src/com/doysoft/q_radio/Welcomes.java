package com.doysoft.q_radio;

import com.doysoft.app.radio.R;
import com.doysoft.app.radio.R.id;
import com.doysoft.app.radio.R.layout;
import com.doysoft.app.radio.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Welcomes extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_welcomes);
		

		 Thread welcomeThread = new Thread() {

	            @Override
	            public void run() {
	                try {
	                    super.run();
	                    
	                    sleep(2000);
	                    
	                	
	                } catch (Exception e) {

	                } finally {
                     finish();
	                	 
	                	
	                
	                }
	            }
	        };
	        welcomeThread.start();
		
	       
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcomes, menu);
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
