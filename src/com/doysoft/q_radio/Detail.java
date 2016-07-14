package com.doysoft.q_radio;

import java.util.HashMap;
import java.util.List;

import com.doysoft.app.radio.R;
import com.doysoft.q_radio.lib.JsonRadio;
import com.doysoft.q_radio.lib.TinyDB;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Detail extends Activity {

	//public List<Conthing> lists;
	public static Context mContext;
	
   public Intent intent = null;
    String url;
    String title;
	public static String type ;
    private ActionBar actionBar;

  
    
	public void init(){
		  mContext = getApplicationContext(); 
        final ListView listViewDetail = (ListView) findViewById(R.id.detail);
        
    
        
        RadioList list=new RadioList();
        
        type = this.getIntent().getStringExtra("Category");
          //before into list, judge type(setList())
        DetailAdapter detailadapter = new DetailAdapter(this, RadioList.setlist());
        listViewDetail.setAdapter(detailadapter);
	      
        
        listViewDetail.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int postion, long arg3) {
            //    Intent intent = null;
            	
          
                for (int i = 0;i<=JsonRadio.map_arr.size();i++){

                	if(i==postion){
   
                		 title =  DetailAdapter.lists.get(postion).getcatename();
                		url= DetailAdapter.lists.get(postion).getcateurl();
             
          	  intent = new Intent(arg0.getContext(), MusicUi.class);
         	  
         	  intent.putExtra("text", url); 
              intent.putExtra("texttitle", title);
              
              TinyDB tb = new TinyDB(mContext);
   		   tb.putString("radio_name", title);
   		   tb.putString("play_url",url);
                break; 
                
            }
            	 
      
        }
          
             startActivity(intent);  
            }
        });
       
       
		
        
        
	}
	
	public void goback(){
		Intent intent = new Intent(this, RadioUi.class);
   	// intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent); 
        this.finish();
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		// setTitle("");
		 actionBar = getActionBar();  
			actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);  
	        actionBar.setCustomView(R.layout.actionbar);//自定义ActionBar布局  
	        actionBar.getCustomView().setOnClickListener(new OnClickListener() {
	            @Override  
	            public void onClick(View arg0) {  
	               goback();
	            }  
	        });  
	        
		init();
		

	 
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		 switch (item.getItemId()) {
	        case android.R.id.home:
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
		String str = "share";
		  Intent sendIntent = new Intent();
		  sendIntent.setAction(Intent.ACTION_SEND);
		  sendIntent.putExtra(Intent.EXTRA_TEXT,str);
		  sendIntent.setType("text/plain");
		 startActivity(Intent.createChooser(sendIntent, str));
	 }
	

}
