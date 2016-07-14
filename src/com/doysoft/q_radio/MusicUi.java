package com.doysoft.q_radio;



import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.doysoft.app.radio.R;
import com.doysoft.q_radio.lib.Extra;
import com.doysoft.q_radio.lib.TinyDB;

import io.vov.vitamio.LibsChecker;



public class MusicUi extends Activity implements OnCheckedChangeListener, OnClickListener{

    private Button btnPause, btnPlayUrl, btnStop, btn;  
    private SeekBar  seekProgress;
   // public Radioplayer mMediaPlayer; 
    public static ToggleButton btnPause1; 
    public TextView music;
    private Intent ServiceIntent;
    private ActionBar actionBar;
   
    Intent urlint ;
    String title;
	  
    String texttitle;
    
   
    
    
    public void goback(){
    	
    	 if(btnPause1.isChecked()==false){
    			RadioUi.pause.setChecked(false);
    		   }else if(btnPause1.isChecked()==true){
    			RadioUi.pause.setChecked(true);
    		 }
    	 
        	Intent intent = new Intent(this, RadioUi.class);
        	
        	intent.putExtra("buttonname", texttitle);
        	 TinyDB tb = new TinyDB(this.getApplicationContext());
        	RadioUi.text.setText(tb.getString("radio_name"));
    	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         startActivity(intent); 
         this.finish();
         
    }

    
    private class MusicBordcastReceiver extends BroadcastReceiver{

    	@Override
    	public void onReceive(Context context, Intent intent) {
    		// TODO Auto-generated method stub
    		
    	}
    	   
       }
     
	public void init(){
	
		btnPause1= (ToggleButton)findViewById(R.id.btnpause1);
		btnPause1.setOnCheckedChangeListener(this);
		
		seekProgress = (SeekBar) this.findViewById(R.id.seekBar1);  
	//	seekProgress.setOnSeekBarChangeListener(new SeekBarChangeEvent());  
		//mMediaPlayer = new MediaPlayer(); 
		//component= new ComponentName(this, Radioplayer.class);
	        music = (TextView) findViewById(R.id.titleMusic);

	    
	        btn = (Button)findViewById(R.id.but);
	    	btn.setOnClickListener(this);
	}

	

	
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
	
		 if(isChecked){  			 
			 urlint=null;

	    	 Intent urlint = new Intent(MusicUi.this, Radioplayer.class);
			 urlint.putExtra("action", "pause");
			 startService(urlint);
			 
				
	           
     }else{  
    //	 mMediaPlayer.pause(); 
    	 urlint=null;
    	 
		 Intent urlint = new Intent(MusicUi.this, Radioplayer.class);
		 urlint.putExtra("action", "play");	
         
		 
		 
		 startService(urlint);
     }  
		
	}
	
	
	public void getContent(){
		  
			title=this.getIntent().getStringExtra("text");	
			
			texttitle=this.getIntent().getStringExtra("texttitle");	
          //  music.setText(texttitle);
            
            TinyDB tb = new TinyDB(this.getApplicationContext());
   		   //  tb.putString("play_url", title);
        
 		
            
   		     music.setText(tb.getString("radio_name"));
   		  
            
			if(title ==null){
        	  title="look";
          }
		
	//	Log.v("aa", title);
	}
	
	
	@Override 
	public void onBackPressed() { 
	super.onBackPressed(); 

	goback();
	   /*  if(btnPause1.isChecked()==false){
		RadioUi.pause.setChecked(false);
	   }else if(btnPause1.isChecked()==true){
		RadioUi.pause.setChecked(true);
	 }
     	RadioUi.text.setText(texttitle);
*/
	}  
	
	
	public void start(){
		//RadioPlayer = new Radioplayer(); 
		// RadioPlayer.stop();  
 	
		String url=title;  
	//	mMediaPlayer.playUrl(url);  
		
	}
	
	public void geturl(){
		
		
		if(title.equals("look")){
			//music.setText(texttitle);
		    if(Radioplayer.isPlaying=false){
				RadioUi.pause.setChecked(false);
				btnPause1.setChecked(false);
		    }else if(RadioUi.pause.isChecked()){
		    	RadioUi.pause.setChecked(true);
				btnPause1.setChecked(true);
		    }
			
		    }else if(title.equals("nothing")){
			
		    }
		
		
		else {
			urlint=null;
			// btnPause1.setChecked(true);

		 urlint = new Intent(MusicUi.this, Radioplayer.class);

		urlint.putExtra("url", title);	
		urlint.putExtra("action", "run");
		urlint.putExtra("title", texttitle);
		
		
		
		startService(urlint);
		String buffer_txt = this.getResources().getString(R.string.buffering);
		Toast toast = Toast.makeText(MusicUi.this, buffer_txt, Toast.LENGTH_SHORT);
		toast.show();
		
		
		
		
		
		
		
		}
		
	
	}
	
	@Override
	public void onResume() {
	    super.onResume();  // Always call the superclass method first

		//init();
		//geturl();
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_ui);
		if (!LibsChecker.checkVitamioLibs(this))
			return;
	
		 actionBar = getActionBar();  
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
        actionBar.setCustomView(R.layout.actionbar);//自定义ActionBar布局  
        actionBar.getCustomView().setOnClickListener(new OnClickListener() {
            @Override  
            public void onClick(View arg0) {  
               goback();
            	//onBackPressed();
            }  
        });  
    
	
	    
		init();
	//	startService(ServiceIntent);
		
		getContent();
		
		geturl();
		

		
	if(isNetConnected()==true){
			
		}else {
			
			String chk_txt = this.getResources().getString(R.string.chk_network);
			Toast.makeText(getApplicationContext(), chk_txt,
				     Toast.LENGTH_SHORT).show();
		}
		
	 
	}
	
	
	private boolean isNetConnected() {  
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);  
        if (cm != null) {  
            NetworkInfo[] infos = cm.getAllNetworkInfo();  
            if (infos != null) {  
                for (NetworkInfo ni : infos) {  
                    if (ni.isConnected()) {  
                        return true;  
                    }  
                }  
            }  
        }  
        return false;  
    }  
	
	

	
 
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.music_ui, menu);
		
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	/*	int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
			
		}*/
		
		 switch (item.getItemId()) {
	        case R.id.back:
	            Intent intent = new Intent(this, RadioUi.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	        case R.id.menu_item_share:
	        	shareApp();
	        	break;
	        default:
	        	break;
		
		 }
		
		return super.onOptionsItemSelected(item);
	}

	public void shareApp(){
		   
		//int resourceId = ct.getResources().getIdentifier("string", "app_name", ct.getPackageName());
		//CharSequence  app_name = ct.getResources().getText(R.string.app_name);
		  String msg = getString(R.string.share_msg);
		  String app = getString(R.string.app_name);
		  Intent sendIntent = new Intent();
		  sendIntent.setAction(Intent.ACTION_SEND);
		  sendIntent.putExtra(Intent.EXTRA_TEXT,msg);
		  sendIntent.setType("text/plain");
		 startActivity(Intent.createChooser(sendIntent, app));
	 }





	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	

	
}
