package com.doysoft.q_radio;

import java.lang.reflect.Field;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import io.vov.vitamio.utils.Log;

import com.astuetz.PagerSlidingTabStrip;
import com.doysoft.app.radio.PollingService;
import com.doysoft.app.radio.PollingUtils;
import com.doysoft.app.radio.R;
import com.doysoft.q_radio.lib.Alarmreceiver;
import com.doysoft.q_radio.lib.TinyDB;
import com.doysoft.q_radio.logcat.Mylog;
import com.doysoft.q_radio.upgradejosn.UpgradeError;
import com.doysoft.q_radio.upgradejosn.UpgradeVersion;
import com.doysoft.q_radio.upgradejosn.copy;
import com.doysoft.q_radio.upgradejosn.copyAllradio;

public class RadioUi extends FragmentActivity implements OnClickListener, OnCheckedChangeListener {

	ViewPager viewPager;

	private Rcoradio rcoradio;
	private Cateradio cateradio;
	private Moreradio moreradio;
	private PagerSlidingTabStrip tabs;
	private DisplayMetrics dm;

	private Button btn;
	public static TextView text;

	public static ToggleButton pause;

	// public static Context Rcontext;

	Intent urlint = null;

	private int icon;
	private CharSequence tickerText;
	private long when;

	String rebote = null;
	// String play_url=null;

	public void init() {
		setOverflowShowingAlways();
		dm = getResources().getDisplayMetrics();
		ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		// pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		btn = (Button) findViewById(R.id.btnui);
		btn.setOnClickListener(this);
		text = (TextView) findViewById(R.id.textname);
		text.setText(R.string.status_stop);
		pause = (ToggleButton) findViewById(R.id.tgbtn);
		pause.setOnCheckedChangeListener(this);
		tabs.setViewPager(pager);
		setTabsValue();

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		Intent intent = null;
		intent = new Intent(this, MusicUi.class);
		intent.putExtra("text", "look");
		intent.putExtra("texttitle", text.getText());

		// startActivity(intent);

	}

	/*
	 * TinyDB tb = new TinyDB(this.getApplicationContext());
	 * tb.putString("radio_name", texttitle); tb.putString("play_url",titles);
	 */

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub

		TinyDB tb = new TinyDB(this.getApplicationContext());

		String play_url = tb.getString("play_url");
		if (play_url.isEmpty()) {
			Toast toast = Toast.makeText(this.getApplicationContext(), getString(R.string.no_brocast),
					Toast.LENGTH_SHORT);
			toast.show();
			buttonView.setChecked(true);

			return;
		}

		if (isChecked) {

			urlint = new Intent(this, Radioplayer.class);
			urlint.putExtra("action", "pause");
			urlint.putExtra("url", play_url);
			// urlint.putExtra(name, value);
			// urlint.putExtra("num", "two");

			startService(urlint);

		} else {

			urlint = new Intent(this, Radioplayer.class);
			urlint.putExtra("action", "play");
			urlint.putExtra("url", play_url);
			startService(urlint);

		}
	}

	@Override
	protected void onDestroy() {
		TinyDB tb = new TinyDB(this.getApplicationContext());
		tb.clear();

		this.finish();

		super.onDestroy();

	}

	public void stopservice() {

		urlint = new Intent(this, Radioplayer.class);
		urlint.putExtra("action", "stop");
		// urlint.putExtra("num", "two");

		startService(urlint);
	}

	public void setBottomText(String txt) {
		TinyDB tb = new TinyDB(this.getApplicationContext());

		// text.setText(tb.getString("radio_name"));

	}

	public void returnbottomText() {
		// TinyDB tb = new TinyDB(this.getApplicationContext());
		rebote = this.getIntent().getStringExtra("buttonname");

		text.setText(rebote);

	}

	/*   @Override
	 public void onBackPressed() {
	             boolean drawerState =  mDrawerLayout.isDrawerOpen(mMenuListView);
	              if (drawerState) {
	                  mDrawerLayout.closeDrawers();
	                  return;
	              }
	              //也就是说，当按下返回功能键的时候，不是直接对Activity进行弹栈，而是先将菜单视图关闭
	            super.onBackPressed();
	          }*/
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		
		boolean drawerState =  mDrawerLayout.isDrawerOpen(mMenuListView);
        if (drawerState) {
            mDrawerLayout.closeDrawers();
            drawerlayout=0;
        }else{
      
   //   super.onBackPressed();
		
		
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			AlertDialog.Builder build = new AlertDialog.Builder(this);
			build.setTitle(getString(R.string.bk_exit)).setMessage(getString(R.string.bk_confirm))
					.setPositiveButton(getString(R.string.bk_sure), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// stopService(Rcoradio.intent);
							// Radioplayer.cancelNotification();

							stopservice();

							// Radioplayer.stop();

							finish();

						}
					}).setNeutralButton(getString(R.string.bk_playinback), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							// finish();
							Intent i = new Intent(Intent.ACTION_MAIN);
							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							i.addCategory(Intent.CATEGORY_HOME);
							startActivity(i);

						}
					}).setNegativeButton(getString(R.string.bk_cancel), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					}).show();
			break;

		default:
			break;
		}
        }
		return false;
	}

	String sleeptime;
	
	public void sleeptimer(){
		String timer=getString(R.string.sleeptimer); 
		final String custom=getString(R.string.cus); 
		
		
		 new AlertDialog.Builder(this)  
         .setTitle(timer)  
         .setItems(R.array.time_array,  
                 new DialogInterface.OnClickListener() {  
                     public void onClick(DialogInterface dialog,  
                             int which) {  
                         String[] items = getResources()  
                                 .getStringArray(  
                                         R.array.time_array);  
                    
                        
                         
                         if(items[which].equals(custom)){
              
                        	 custom();
      
                        	 
                        	 
                         }else{
                        	  String a =items[which];
                Toast.makeText(RadioUi.this,   a +getString(R.string.closeradio), Toast.LENGTH_SHORT).show();
                              sleeptime   = a;
                         //     TinyDB tb = new TinyDB(getApplicationContext());
           			//	   tb.putString("sleep_time", sleeptime);
                          //    time=0;
           				   time = Integer.parseInt(sleeptime)*100000;
           				  alarm();
           				   
                         }
                         
                     }  
                 }).create().show();  

	
		
	}
	
	int time;
	public void custom(){
		
     	 
        AlertDialog.Builder Custom = new AlertDialog.Builder(RadioUi.this);
        Custom.setTitle(getString(R.string.inputtitle));
        View view = LayoutInflater.from(RadioUi.this).inflate(R.layout.custom, null);
        Custom.setView(view);
        final EditText customtime = (EditText)view.findViewById(R.id.editText1);
        
        Custom.setPositiveButton(getString(R.string.bk_sure), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
              //  String a = username.getText().toString().trim();
                String b = customtime.getText().toString().trim();
              
                Toast.makeText(RadioUi.this,   b +getString(R.string.closeradio), Toast.LENGTH_SHORT).show();
                sleeptime = b;
               // time=0;
                time = Integer.parseInt(b)*100000;
              //  TinyDB tb = new TinyDB(getApplicationContext());
				//   tb.putString("sleep_time", sleeptime);
				   
				   alarm();
				   
            } 
        });
        Custom.setNegativeButton(getString(R.string.bk_cancel), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                
            }
        });
        Custom.show();
    // PollingUtils.startPollingService(this, 5, PollingService.class, PollingService.ACTION);
    //PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);

	}
	
	public void alarm(){
	
	
		
		Intent intent =new Intent(RadioUi.this, Alarmreceiver.class);
	    intent.setAction("set");
	    PendingIntent sender=
	        PendingIntent.getBroadcast(RadioUi.this, 0, intent, 0);
	    
	    //设定一个五秒后的时间
	    Calendar calendar=Calendar.getInstance();
	    calendar.setTimeInMillis(System.currentTimeMillis());
	   // calendar.add(Calendar.SECOND, 5);
	    
	    AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
	  //  alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
		
	    alarm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+time, sender);
	    
		
		
	}
	
/*	public String edname(){
		String errorn = edna;
		return errorn;
		
	}
	public String edregion(){
		String errorr = edre;
		return errorr;
	}*/
	public static String edna ;
	public static String edre ; 
	public void reportError(){
		
        AlertDialog.Builder Error = new AlertDialog.Builder(RadioUi.this);
        Error.setTitle(getString(R.string.report));
        final View view = LayoutInflater.from(RadioUi.this).inflate(R.layout.activity_upgradeerror, null);
        Error.setView(view);
    
        Error.setPositiveButton(getString(R.string.bk_sure), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            	EditText edName = (EditText)view.findViewById(R.id.et1);
                 EditText edRegion = (EditText)view.findViewById(R.id.et2);
                edna = edName.getText().toString().trim();
                  edre = edRegion.getText().toString().trim();
                 
             UpgradeError uper= new UpgradeError();
             uper.submitData();
             Toast.makeText(RadioUi.this, getString(R.string.input_ok) , Toast.LENGTH_SHORT).show();
            } 
        });
        Error.setNegativeButton(getString(R.string.bk_cancel), new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                
            }
        });
        Error.show();
	}
	


	private void setTabsValue() {
		// 设置Tab是自动填充满屏幕的
		tabs.setShouldExpand(true);
		// 设置Tab的分割线是透明的
		tabs.setDividerColor(Color.TRANSPARENT);
		// 设置Tab底部线的高度
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// 设置Tab Indicator的高度
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm));
		// 设置Tab标题文字的大小
		tabs.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, dm));
		// 设置Tab Indicator的颜色
		tabs.setIndicatorColor(Color.parseColor("#BADAF9"));
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)
		tabs.setSelectedTextColor(Color.parseColor("#BADAF9"));
		// 取消点击Tab时的背景色
		tabs.setTabBackground(0);
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		private final String[] titles = { getString(R.string.recommend), getString(R.string.category) };

		public CharSequence getPageTitle(int position) {
			return titles[position];
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (rcoradio == null) {
					rcoradio = new Rcoradio();
				}
				return rcoradio;
			case 1:
				if (cateradio == null) {
					cateradio = new Cateradio();
				}
				return cateradio;
			/*
			 * case 2: if (moreradio == null) { moreradio = new Moreradio(); }
			 * return moreradio;
			 */
			default:
				return null;
			}
		}

	}

	public void initclear() {
		TinyDB tb = new TinyDB(this.getApplicationContext());
		tb.clear();
	}

	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DrawerLayout mDrawerLayout;
	private ListView mMenuListView;
	private String[] mMenuTitles;
    private View showView;
	
    
	
	
	DrawerAdapter menuDrawerAdapter ;
	public void initmenu() {
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mMenuListView = (ListView) findViewById(R.id.left_drawer);
	//	mMenuTitles = getResources().getStringArray(R.array.menu_array);

	}

	public void setdrawerlayout() {

		//mDrawerLayout.setDrawerShadow(R.drawable.backcolor, GravityCompat.START);
		menuDrawerAdapter = new DrawerAdapter(this) ;
		mMenuListView.setAdapter(menuDrawerAdapter);
		//mMenuListView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, mMenuTitles));
		mMenuListView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				mMenuListView.setItemChecked(position, true);
	          //  setTitle(mMenuTitles[position]);
               mDrawerLayout.closeDrawer(mMenuListView);
               drawerlayout=0;
               selectItem(position);
			}
			
		});
		mDrawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {  
            @Override  
            public void onDrawerClosed(View drawerView) {  
                super.onDrawerClosed(drawerView);  
                //mDrawerLayout.closeDrawer(mMenuListView);
                invalidateOptionsMenu(); 
                drawerlayout=0;
            }  
            @Override  
            public void onDrawerOpened(View drawerView) {  
                super.onDrawerOpened(drawerView);  
            	//drawerlayout=0;
                drawerlayout=1;
            //    mDrawerLayout.openDrawer(mMenuListView);
                invalidateOptionsMenu();
            }  
        });  
	}

     	private void selectItem(int position) {
		// TODO Auto-generated method stub
	//	Fragment fragment = new ContentFragment();
		//Bundle args = new Bundle();
			TinyDB tb = new TinyDB(this.getApplicationContext());

     		String play_url;
		switch (position) {
		case 0:
			
			break;
		case 1:
			 play_url = tb.getString("play_url");
				if (play_url.isEmpty()) {
					Toast toast = Toast.makeText(this.getApplicationContext(), getString(R.string.no_brocast),
							Toast.LENGTH_SHORT);
					toast.show();
				
				}else{
					Intent it = new Intent(this,MusicUi.class);
					startActivity(it);
				}
		
			break;
		case 2:
			
			 play_url = tb.getString("play_url");
				if (play_url.isEmpty()) {
					Toast toast = Toast.makeText(this.getApplicationContext(), getString(R.string.no_brocast),
							Toast.LENGTH_SHORT);
					toast.show();
				
				}else{
					
					sleeptimer();
				}
			
	
			break;
			
		case 3:
			
			
					reportError();
					
				
			
	
			break;
			
		default:
			break;
	    	}
     	}
	
     	
     	public void clean(){
     		
     		TinyDB tb = new TinyDB(this.getApplicationContext());
    		tb.clear();
     	}
     	

		private ActionBar actionBar;
		TextView abTitle;
	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);

			Intent i = new Intent(RadioUi.this, Welcomes.class);
			startActivityForResult(i, 0);
			// startActivity(i);
			// initUrldata();
			clean();
			checkdata();
			initUrldata();

		

			setContentView(R.layout.activity_radio_ui);
			init();
			ActionBar actionBar = getActionBar();

			int titleId = getResources().getIdentifier("action_bar_title", "id", "android");

			abTitle = (TextView) findViewById(titleId);
			abTitle.setTextColor(getResources().getColor(R.color.white));
			// actionBar.setBackgroundDrawable(new
			// ColorDrawable(Color.parseColor("#74B1C5")));
			// actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDisplayShowHomeEnabled(false);
			  getActionBar().setDisplayHomeAsUpEnabled(true);
			//  getActionBar().setLogo(R.drawable.page1);
			// rContext = this.getActivity();
			// abTitle.setTextColor(getResources().getColor(R.color.white));

			/*
			 * actionBar = getActionBar();
			 * actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
			 * actionBar.setCustomView(R.layout.actionbar_rec);//自定义ActionBar布局
			 * 
			 */
			// initclear();
			  
			initmenu();
			 setdrawerlayout();


		}

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is
			// present.
			getMenuInflater().inflate(R.menu.radio_ui, menu);
			return true;
		}

		int drawerlayout=0;
		public void ocdrawerlayout(){
			if(drawerlayout==0){
				mDrawerLayout.openDrawer(mMenuListView);
				drawerlayout=1;
			} else{
				mDrawerLayout.closeDrawer(mMenuListView);
				drawerlayout=0;
			}
			
		}
		
		
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle action bar item clicks here. The action bar will
			// automatically handle clicks on the Home/Up button, so long
			// as you specify a parent activity in AndroidManifest.xml.
			int id = item.getItemId();
			switch (item.getItemId()) {
			
			case android.R.id.home:
			//mDrawerLayout.closeDrawer(mMenuListView);
				//mDrawerLayout.openDrawer(mMenuListView);
				ocdrawerlayout();
			
				
			
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

		public void shareApp() {

			String msg = getString(R.string.share_msg);
			String app = getString(R.string.app_name);
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, app));
		}

		//boolean insert;

		public void initUrldata() {

			copy file = new copy();
			file.startUrlCheck();

			copyAllradio allfile = new copyAllradio();
			allfile.startAllUrlCheck();

		//	insert = true;
		}

		public void checkdata() {
			UpgradeVersion check = new UpgradeVersion();
			check.startVersionCheck();

		}

}
