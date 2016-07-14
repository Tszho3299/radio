package com.doysoft.app.radio;

import com.doysoft.q_radio.Radioplayer;
import com.doysoft.q_radio.lib.TinyDB;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.widget.TextView;
import android.widget.Toast;

public class PollingService  extends Service{
	public static final String ACTION = "com.ryantang.service.PollingService";
    
   // private Notification mNotification;
   // private NotificationManager mManager;
  
    @Override
    public void onCreate() {
      //  initNotifiManager();
    }
                                                                                                                                                                                                                           
    @Override
    public void onStart(Intent intent, int startId) {
      
    	stopmusic();
    	//Toast.makeText(getApplicationContext(), "调用服务第 1次",
		//		Toast.LENGTH_SHORT).show();
    } 
    //初始化通知栏配置
  
  public void stopmusic(){
	  Intent  urlint = new Intent(this, Radioplayer.class);
		urlint.putExtra("action", "pause");
		// urlint.putExtra("num", "two");

		startService(urlint);
  }
    
  CountDownTimer cdt;
  public void clock(){
		 cdt =	new CountDownTimer(30000, 1000) {

		     public void onTick(long millisUntilFinished) {
		         //mTextField.setText(" " + sleeptime / 1000);
		    	 
		    	 if((sleeptime / 1000)==0){
		    		 stopmusic();
		    		 
		    	 }
		   //	  ((TextView) view).setText(MenuItems.get(position).menuTitle) ; 
		     }

		     public void onFinish() {
		      //   mTextField.setText("done!");
		    	 
		    	 if((sleeptime / 1000)==0){
		    		// stopmusic();
		    		 
		    	 }
		    	 
		     }
		  }.start();
     
  }
  
    String count ;
    int con;
    int sleeptime;
    class PollingThread extends Thread {
        @Override
        public void run() {
           

    		TinyDB tb = new TinyDB(getApplicationContext());

    		count= tb.getString("sleep_time");
    		sleeptime =	Integer.parseInt(count);
    		//cdt.start();
    		con++;
    		if (con % 5 == 0) {
    			stopmusic();
			//	showNotification();
				System.out.println("New message!");
			}
    	
        }
    }
                                                                                                                                                                                                                           
    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service:onDestroy");
    }
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}