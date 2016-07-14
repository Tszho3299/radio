package com.doysoft.q_radio;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.AudioTrack;
//import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.doysoft.app.radio.R;
import com.doysoft.q_radio.lib.TinyDB;
import com.doysoft.q_radio.logcat.Mylog;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.MediaPlayer.OnErrorListener;

public class Radioplayer extends Service implements OnBufferingUpdateListener,
		OnCompletionListener, OnPreparedListener,
		io.vov.vitamio.MediaPlayer.OnPreparedListener, OnErrorListener {

	public static MediaPlayer mMediaPlayer;
	// private Intent BradCastcurrentIntent;

	// private SeekBar seekProgress;
	private Timer mTimer = new Timer();
	// private int MSG;
	

	public static boolean isPlaying = true;

	String play = "play";
	String pause = "pause";
	String run = "run";
	String stop = "stop";
	String texttitle=null;
	String titles=null;
	// private RemoteViews contentView;
	// private Notification notification;
	public NotificationManager notificationManager;

	public Radioplayer() {

		/*
		 * mMediaPlayer= new MediaPlayer();
		 * mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
		 * mMediaPlayer.setOnPreparedListener(this);
		 */

		
	}

	private static final String TAG = "MediaPlayerDemo";

	private void playAudio() {
		try {
			initNotification();

			mMediaPlayer.reset();

			mMediaPlayer.setDataSource(titles);
			mMediaPlayer.prepareAsync();
			mMediaPlayer.setOnPreparedListener(this);

			mMediaPlayer.setOnErrorListener(this);

		} catch (Exception e) {
			Log.e(TAG, "error: " + e.getMessage(), e);
		}

	}

	public MediaPlayer createMediaPlayer(Context context, int resid) {
		try {

			MediaPlayer mp = new MediaPlayer(context);

			mp.setDataSource(titles);

			mp.prepare();

			return mp;
			
		} catch (IOException ex) {
			Log.d(TAG, "create failed:", ex);
			// fall through
		} catch (IllegalArgumentException ex) {
			Log.d(TAG, "create failed:", ex);
			// fall through
		} catch (SecurityException ex) {
			Log.d(TAG, "create failed:", ex);
			// fall through
		}
		// return ;
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		// mMediaPlayer =MediaPlayer.create(getApplicationContext(),
		// R.raw.music);

		// Initialization mediaplayer

		/*
		 * mMediaPlayer= new MediaPlayer();
		 * mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);// 设置媒体流类型
		 * mMediaPlayer.setOnPreparedListener(this);
		 */

	}
  
	
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		String action = intent.getStringExtra("action");

		Log.v("cccc", "ccc");
		titles = intent.getStringExtra("url");
		texttitle = intent.getStringExtra("title");
		// String num = intent.getStringExtra("one");
		

		if (action.equals(run)) {
			// onDestroy();

			if (mMediaPlayer != null) {
				if (mMediaPlayer.isPlaying() || mMediaPlayer.isBuffering()) {
					mMediaPlayer.release();
				}
			}
			init();
			playAudio();
			
		} else if (action.equals(pause)) {
			pause();
		} else if (action.equals(play)) {
			play();
		} else if (action.equals(stop)) {
			onDestroy();
		}else{
			onDestroy();
		}

		return super.onStartCommand(intent, flags, startId);
	}

	public void init() {
		mMediaPlayer = new MediaPlayer(this);
		
		
		
		/*
		 * try { initNotification(); mMediaPlayer.reset();
		 * 
		 * mMediaPlayer.setDataSource(titles); mMediaPlayer.prepareAsync();
		 * mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
		 * 
		 * @Override public void onPrepared(MediaPlayer mp) { // 装载完毕回调
		 * mMediaPlayer.start();
		 * 
		 * }
		 * 
		 * } catch (IllegalArgumentException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IllegalStateException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	class ServiceBinder extends Binder {
		public Radioplayer getService() {
			return Radioplayer.this;
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		
		// mMediaPlayer.stop();

		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
		/*
		 * if (mMediaPlayer != null) { mMediaPlayer.stop();
		 * mMediaPlayer.release(); mMediaPlayer = null;
		 * 
		 * 
		 * }
		 */
		// stopSelf();
		deleteNotification();
		// deleteNotification();
	}

	public void play() {

		isPlaying = true;
  
		mMediaPlayer.start();

	}

	public void pause() {
		
		isPlaying = false;
		mMediaPlayer.pause();
		// radiostate="pause";

	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public static void stop() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
			mMediaPlayer.reset();
			mMediaPlayer.release();
			// mMediaPlayer = null;

		}

	}

	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
        
		return null;
	}

	
	/*
	 * @Override public void onBufferingUpdate(MediaPlayer mp, int percent) { /*
	 * seekProgress.setSecondaryProgress(percent); int currentProgress =
	 * seekProgress.getMax() mMediaPlayer.getCurrentPosition() /
	 * mMediaPlayer.getDuration(); Log.e(currentProgress + "% play", percent +
	 * " buffer"); }
	 * 
	 * @Override public void onPrepared(MediaPlayer mp) { // TODO Auto-generated
	 * method stub if (mMediaPlayer != null) mMediaPlayer.start();// 开始播放
	 * 
	 * Log.e("mediaPlayer", "onPrepared"); }
	 * 
	 * @Override public void onCompletion(MediaPlayer mp) { // TODO
	 * Auto-generated method stub Log.e("mediaPlayer", "onCompletion"); }
	 */
	 Notification notification;

	public void initNotification() {

		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	
	
		int icon = R.drawable.notification;
		CharSequence tickerText = texttitle;	
		long when = System.currentTimeMillis();

		notification = new Notification(icon, tickerText, when);
		//notification
		RemoteViews contentView = new RemoteViews(getPackageName(),
				R.layout.notification_control);
		 
		contentView.setTextViewText(R.id.titlename, texttitle);
		notification.contentView = contentView;
		Intent intent = new Intent(this, MusicUi.class);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
     	PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				intent, 0);		
		notification.contentIntent = contentIntent;
		String ns = Context.NOTIFICATION_SERVICE;

		notificationManager = (NotificationManager) getSystemService(ns);
     	startForeground(10, notification);

		// stopForeground(true);

	}

	public void deleteNotification() {
		// notificationManager = (NotificationManager)
		// getSystemService(Context.NOTIFICATION_SERVICE);
		// notificationManager.cancel(10);
		
		stopForeground(true);
	}

	@Override
	public void onPrepared(android.media.MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCompletion(android.media.MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBufferingUpdate(android.media.MediaPlayer mp, int percent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		if (mMediaPlayer != null){
			TimerTask 	task = new TimerTask() {  
	            @Override  
	            public void run() {  
	            	
	    			mMediaPlayer.start();
  		
                    Log.v("aa", "aa");
	            }  
	        };  
	  
	      Timer timer = new Timer();  
	    
	        timer.schedule(task, 5000);
		} 
	//	mMediaPlayer.start();
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}

}