package com.doysoft.q_radio.lib;

import com.doysoft.q_radio.MusicUi;
import com.doysoft.q_radio.RadioUi;
import com.doysoft.q_radio.Radioplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Alarmreceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		// if(intent.getAction().equals("set")){
          
				 Intent urlint = new Intent(context , Radioplayer.class);  
			        // 启动指定Server  
				 urlint.putExtra("action", "pause");
			        context.startService(urlint);  
			    	RadioUi.pause.setChecked(true);
			    	MusicUi.btnPause1.setChecked(true);
			        Toast.makeText(context, "off radio", Toast.LENGTH_SHORT).show();
    
		 
		 
		 
	}

}
