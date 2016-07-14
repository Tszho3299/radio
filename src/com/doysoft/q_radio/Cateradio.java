package com.doysoft.q_radio;

import java.util.ArrayList;
import java.util.List;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.doysoft.app.radio.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData.Item;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.FrameLayout.LayoutParams;

public class Cateradio extends Fragment {

	ExpandableListView mExpandableListView;
	ExpandablelistviewAdapter mExpandableListViewAdapter;

	private RadioUi main;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		main = (RadioUi) activity;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater
				.inflate(R.layout.activity_cateradio, container, false);

		mExpandableListView = (ExpandableListView) v
				.findViewById(R.id.expandableListView);

		// initData();

		mExpandableListViewAdapter = new ExpandablelistviewAdapter(main);

		mExpandableListView.setAdapter(mExpandableListViewAdapter);
		mExpandableListView.expandGroup(0);

		mExpandableListView
				.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, int groupPosition, long id) {
						// TODO Auto-generated method stub

						// Log.e("hefeng", "ExpandableListView
						// GroupClickListener
						// groupPosition=" + groupPosition);
						Intent category = null;
						category = new Intent(main, Detail.class);
						//Log.v("zz", String.valueOf(groupPosition));

						// category.putExtra("cate", groupPosition);
						// startActivity(category);

						return false;
					}
				});

		mExpandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {

				//Log.v("zs", String.valueOf(childPosition));

				// Toast.makeText( main, "position=" + groupPosition+
				// Toast.LENGTH_SHORT, childPosition).show();
				Intent cate = null;
				cate = new Intent(main, Detail.class);
				// Log.v("zs", "cc");
				if (groupPosition == 0) {

					if (childPosition == 0) {
						cate.putExtra("Category", "music");
					} else if (childPosition == 1) {
						cate.putExtra("Category", "information");
					} else if (childPosition == 2) {
						cate.putExtra("Category", "internet");
					}
				} else if (groupPosition == 1) {

					if (childPosition == 0) {
						cate.putExtra("Category", "beijing");
					} else if (childPosition == 1) {
						cate.putExtra("Category", "guangdong");
					} else if (childPosition == 2) {
						cate.putExtra("Category", "henan");
					} else if (childPosition == 3) {
						cate.putExtra("Category", "hebei");
					} else if (childPosition == 4) {
						cate.putExtra("Category", "jiangxi");
					} else if (childPosition == 5) {
						cate.putExtra("Category", "jiangsu");
					}else if (childPosition == 6) {
						cate.putExtra("Category", "shandong");
					}else if (childPosition == 7) {
						cate.putExtra("Category", "xinjiang");
					}else if (childPosition == 8) {
						cate.putExtra("Category", "guangxi");
					}else if (childPosition == 9) {
						cate.putExtra("Category", "fujian");
					}
					

				} else if (groupPosition == 2) {

				/*	if (childPosition == 0) {
						cate.putExtra("Category", "foreign");
					} else if (childPosition == 1) {
						cate.putExtra("Category", "taiwan");
					} 
					else if (childPosition == 2) {
						cate.putExtra("Category", "macao");
					} else if (childPosition == 3) {
						cate.putExtra("Category", "hongkong");
					}*/

				}

				startActivity(cate);
				return false;
			}
		});

		// mExpandableListView.setOnChildClickListener((OnChildClickListener)
		// main);
	
		
		
		return v;
	}

}
