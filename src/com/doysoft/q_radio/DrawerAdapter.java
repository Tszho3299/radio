package com.doysoft.q_radio;

import java.util.ArrayList;
import java.util.List;

import com.doysoft.app.radio.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


class MenuItem {
    String menuTitle ;
    int menuIcon ;
    
   
    
    //构造方法
    public MenuItem(String menuTitle , int menuIcon ){
        this.menuTitle = menuTitle ;
        this.menuIcon = menuIcon ;
    }
    
  /*  public String getmenuTitle() {  
        return menuTitle;  
    }  
    public void setmenuTitle(String menuTitle){
        this.menuTitle = menuTitle;

    }
   */
    
}

public class DrawerAdapter extends BaseAdapter{

	
	 String sleeptimer;
	    String title;
	    String musicui;
	String error;
	
	
	
	 List<MenuItem> MenuItems = new ArrayList<MenuItem>( ) ;
	    
	      Context context ;
	
	      public DrawerAdapter( Context context ){
	    	           
	    	          this.context = context ;
	    	          
	    	      	title =context.getString(R.string.title); 
	    	      	musicui =context.getString(R.string.musicui); 
	    	      	sleeptimer =context.getString(R.string.sleeptimer); 
	    	      	error= context.getString(R.string.error); 
	    	    //      MenuItems.add(new MenuItem("", R.drawable.logo)) ;
	    	                   MenuItems.add(new MenuItem(title, R.drawable.home)) ;
	    	                  MenuItems.add(new MenuItem(musicui, R.drawable.music)) ;
	    	                  MenuItems.add(new MenuItem(sleeptimer, R.drawable.timer)) ;
	    	                  MenuItems.add(new MenuItem(error,R.drawable.page1_1));
	      }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return MenuItems.size();
	}

	@Override
	public Object getItem(int position) { 
		// TODO Auto-generated method stub
		return MenuItems.get(position) ;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position ;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//ViewHolder holder=null;
		 View view = convertView ;
		          if(view == null){
		        //	  holder = new ViewHolder();
		        	  view =LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
	          	  ((TextView) view).setText(MenuItems.get(position).menuTitle) ;
				       ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(MenuItems.get(position).menuIcon, 0, 0, 0) ;

		          }else {
		   	
		        	  }
		     
		          return view ;
		
		
	}

	
 	private  class ViewHolder {
 		
 		TextView tx;
 		
 	
 	} 	
	
	
	
}
