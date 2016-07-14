package com.doysoft.q_radio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.doysoft.app.radio.R;
import com.doysoft.q_radio.lib.JsonRecRadio;
import com.doysoft.q_radio.lib.TinyDB;
import com.doysoft.q_radio.upgradejosn.copy;

public class Rcoradio extends Fragment {
	private List<Conthing> lists = null;
	private ListView listView;
	// ListView listview =new ListView(getActivity());
	// List<Conthing> lists;

	private RadioUi main;
	private RequestQueue requestQueue;
	private LinearLayout loadingLayout;

	public static Context rContext;

	public Intent intent = null;

	// public String lname;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		main = (RadioUi) activity;
		requestQueue = Volley.newRequestQueue(activity);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rContext = this.getActivity();

		View v = inflater.inflate(R.layout.activity_rcoradio, container, false);
		ListView listView = (ListView) v.findViewById(R.id.listview1);
		loadingLayout = (LinearLayout) v.findViewById(R.id.loading);

		listView.setOnScrollListener(new AbsListView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// 监听listView的状态是否改变
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// 在滚动的过程中会触发该事件
			}
		});

		setlist();
		MyAdapter adapter = new MyAdapter(main, lists);
	
		listView.setAdapter(adapter);

		// Intent intent = null;

		/*
		 * listView.setOnClickListener(new OnClickListener(){
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub
		 * 
		 * } });
		 */
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int postion, long arg3) {

				for (int i = 0; i <= JsonRecRadio.map_arrDeta.size(); i++) {

					if (i == postion) {
                       
                    	   String url = MyAdapter.lists.get(postion).geturl();
   						String lname = MyAdapter.lists.get(postion).getinfo();

   						intent = new Intent(arg0.getContext(), MusicUi.class);

   						intent.putExtra("text", url);
   						intent.putExtra("texttitle", lname);

   						main.setBottomText(lname);

   						 TinyDB tb = new TinyDB(main.getApplicationContext());
   						   tb.putString("radio_name", lname);
   				 		   tb.putString("play_url",url); 
                       
						
						
						break;
					}

				}

				startActivity(intent);
			}
		});

		return v;

	}

	// NewsAdapter adapter = new NewsAdapter(main, data);
	// listView.setAdapter(adapter);

	private void setlist() {
		
		
		
		 copy file = new copy();

		lists = new ArrayList<Conthing>();
		
		
		 try {
				JsonRecRadio.setdetailData(file.read());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    	
		
		//JsonRecRadio.setdetailData(JsonRecRadio.initdetaildata());

		int i;
		for (i = 0; i < JsonRecRadio.map_arrDeta.size(); i++) {
             
		
						
			String Lname = JsonRecRadio.map_arrDeta.get(String.valueOf(i))
					.get("name").toString();

			String Lregion = JsonRecRadio.map_arrDeta.get(String.valueOf(i))
					.get("region").toString();
			String Lpic = JsonRecRadio.map_arrDeta.get(String.valueOf(i))
					.get("pic").toString();
			String Llink = JsonRecRadio.map_arrDeta.get(String.valueOf(i))
					.get("link").toString();

			int resID = Rcoradio.rContext.getResources().getIdentifier(Lpic,
					"drawable", Rcoradio.rContext.getPackageName());

			Conthing data = new Conthing(Lregion, Lname, resID, Llink);
			// Conthing bbb = new Conthing( "女", "喜欢读书",R.drawable.p2 );
			String taiwan=getString(R.string.other_taiwan);
			String foreign=getString(R.string.other_foreign);
			if(Lregion.equals(taiwan)||Lregion.equals(foreign)){
				
			}else{
					lists.add(data);
			
			}

			data = null;
			
		}

		/*
		 * Conthing aaa = new Conthing( "男", "喜欢玩游戏",R.drawable.p1 ); //
		 * lists.add(aaa); Conthing bbb = new Conthing( "女",
		 * "喜欢读书",R.drawable.p2 ); Conthing ccc = new Conthing( "女",
		 * "喜欢读书",R.drawable.p3 ); Conthing d = new Conthing( "女",
		 * "喜欢读书",R.drawable.p1 ); Conthing e = new Conthing( "女",
		 * "喜欢读书",R.drawable.p2 ); Conthing f = new Conthing( "女",
		 * "喜欢读书",R.drawable.p3 ); Conthing g = new Conthing( "女",
		 * "喜欢读书",R.drawable.p3 ); Conthing h = new Conthing( "女",
		 * "喜欢读书",R.drawable.p2 );
		 * 
		 * lists.add(aaa); lists.add(bbb); lists.add(ccc); lists.add(d);
		 * lists.add(e); lists.add(f); lists.add(g); lists.add(h);
		 */

	}

}
