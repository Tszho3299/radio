package com.doysoft.q_radio.lib;



import com.doysoft.app.radio.R;

import android.content.Context;
import android.content.Intent;

public class Extra {

	

	  static Context ct;
	 
	 
	

		 
	 public Extra(Context context) {
		 
		
			
			ct = context;
			
			
		
	}
	
	public void shareApp(){
		   
		//int resourceId = ct.getResources().getIdentifier("string", "app_name", ct.getPackageName());
		//CharSequence  app_name = ct.getResources().getText(R.string.app_name);
		String str = "share";
		  Intent sendIntent = new Intent();
		  sendIntent.setAction(Intent.ACTION_SEND);
		  sendIntent.putExtra(Intent.EXTRA_TEXT,str);
		  sendIntent.setType("text/plain");
		 ct.startActivity(Intent.createChooser(sendIntent, str));
	 }
}
