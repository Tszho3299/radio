package com.doysoft.q_radio;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.doysoft.app.radio.R;

public class DetailAdapter extends BaseAdapter{
	   private Context context;  
	     public static List<Catething> lists =null;  
	     private LayoutInflater layoutInflater;  
	     

	     public DetailAdapter (Context context, List<Catething> lists){
	    	 this.context = context;  
	         this.lists = lists;  
	        
	         layoutInflater = LayoutInflater.from(this.context);  
	     }
	     
	     
	     @Override
	 	public View getView(int position, View convertView, ViewGroup parent) {
	 	
	 		
	 		ViewHolder holder=null;
	 		
	 	      if (convertView == null) {     	
	 	    	  convertView = LayoutInflater.from(context).inflate(
	 						R.layout.reclist, null);
	 	    		holder = new ViewHolder();
	 	    		holder.image=(ImageView)convertView.findViewById(R.id.imageView1);
	 	    		holder.tv1 = (TextView) convertView.findViewById(R.id.title);  
	 	    		holder.tv2 = (TextView) convertView.findViewById(R.id.info);  
	 	    		
	 	    		holder.tv3 = (TextView) convertView.findViewById(R.id.url);  
	 	    		holder.tv4 = (TextView) convertView.findViewById(R.id.category);  

	 	      
	 	    		convertView.setTag(holder);  
	 	    		
	 	    		
	 	      }  else {
	 				holder = (ViewHolder) convertView.getTag();
	 				resetViewHolder(holder);
	 			}
	 	      
	 	    holder.image.setBackgroundResource(lists.get(position).getcatepicture());  
	 	    holder.tv1.setText(lists.get(position).getcatename()); 
	 	    holder.tv2.setText(lists.get(position).getcateregion());  
	   		 
	   		holder.tv3.setText(lists.get(position).getcateurl());  
	   		holder.tv4.setText(lists.get(position).getcategory()); 

	 	      
	 	      
	         return convertView;  

	 }
	     protected void resetViewHolder(ViewHolder ViewHolder)
	 	{
	 		ViewHolder.tv1.setText(null);
	 		ViewHolder.tv2.setText(null);
	 		ViewHolder.tv3.setText(null);
	 		ViewHolder.tv4.setText(null);

	 		ViewHolder.image.setImageResource(0);
	 	}
	 	
	  	private  class ViewHolder {
	  		ImageView image;
	  		TextView tv1;
	  		TextView tv2;
	  		TextView tv3;
	  		TextView tv4;
	  	} 	

	     
	     @Override
	 	public int getCount() {
	 		return lists.size();
	 	}

	 	@Override
	 	public Object getItem(int position) {
	 		return lists.get(position);
	 	}

	 	@Override
	 	public long getItemId(int position) {
	 		return position;
	 	}

	     
}
