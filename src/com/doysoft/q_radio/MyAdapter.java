package com.doysoft.q_radio;

import java.util.List;


import android.content.Context;
import android.provider.SyncStateContract.Constants;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.doysoft.app.radio.R;



public  class MyAdapter extends BaseAdapter {  
     private Context context;  
     public static List<Conthing> lists =null;  
     private LayoutInflater layoutInflater;  
     private LayoutInflater mInflater;
     //   private List<HashMap<String, Object>> dataList;  
     

		
		 
     public MyAdapter(Context context, List<Conthing> lists) {  
    		if(lists !=null){

    		//	lists.clear();
    		  
        	}
    	 this.context = context;  
         this.lists = lists;  
        
         layoutInflater = LayoutInflater.from(this.context);  
       //  queue = Volley.newRequestQueue(context);
 		//imageLoader = new ImageLoader(queue, new BitmapCache());
 	
     
     } 

	 int num=3;
     @Override
 	public int getCount() {
    	/* if(lists.size()<3){  
             return lists.size();  
         }*/  
    	 int size= lists.size()/num;
         return lists.size();
 		//return lists.size();
 	}

 	@Override
 	public Object getItem(int position) {
 		return lists.get(position);
 	}

 	@Override
 	public long getItemId(int position) {
 		return position;
 	}
 	
 	 public void clear() {
         lists.clear();
         notifyDataSetChanged();
     }
 	 
 	 public void remove(int index) {
         lists.remove(index);
         notifyDataSetChanged();
     }
 	 
     //移除对象
     public void remove(Object obj) {
          lists.remove(obj);
         notifyDataSetChanged();
     }
 
   //  private final int TYPE_ONE=0,TYPE_TWO=1,TYPE_COUNT=2;
     @Override
 	public int getItemViewType(int position) {
    	 
		
		
 		// TODO Auto-generated method stub
 	//	int po = 1;
 	//	if (position == po)
 		//	return TYPE_AD;
 	//	else
 			return TYPE_ITEM;
 		
    	 
 	}
  
     
     /*
      * 
      * 	holder.image=(ImageView)convertView.findViewById(R.id.imageView1);
	    		holder.tv1 = (TextView) convertView.findViewById(R.id.info);  
	    		holder.tv2 = (TextView) convertView.findViewById(R.id.title);  
	    		holder.tv3 = (TextView) convertView.findViewById(R.id.url);  
	    		
      * 
      * (non-Javadoc)
      * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
      */
     
     private  final int TYPE_ITEM = 1;
     private  final int TYPE_AD = 2;
     int count_i = 1;
     int type ;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		
		ViewHolder holder=null;
		 
		type = getItemViewType(position);
		  
		 
		  
		  
		/*   if(count_i%3==1&&count_i>1){
			   type  = TYPE_AD;
		   }else{
			   type  = TYPE_ITEM;
		   }
		 */
	      if (convertView == null) {     	
	    	  holder = new ViewHolder();
	    	/*  convertView = LayoutInflater.from(context).inflate(
						R.layout.reclist, null);
	    		
	    		holder.image=(ImageView)convertView.findViewById(R.id.imageView1);
	    		holder.tv1 = (TextView) convertView.findViewById(R.id.info);  
	    		holder.tv2 = (TextView) convertView.findViewById(R.id.title);  
	    		holder.tv3 = (TextView) convertView.findViewById(R.id.url);  
	    		
	    		*/
	    		
	    		switch (type) {
                case TYPE_ITEM:
                	convertView = LayoutInflater.from(context).inflate(
    						R.layout.reclist, null);
                	holder.image=(ImageView)convertView.findViewById(R.id.imageView1);
    	    		holder.tv1 = (TextView) convertView.findViewById(R.id.info);  
    	    		holder.tv2 = (TextView) convertView.findViewById(R.id.title);  
    	    		holder.tv3 = (TextView) convertView.findViewById(R.id.url); 
                    break;
                    
                case TYPE_AD:
                	
                	convertView = LayoutInflater.from(context).inflate(
    						R.layout.recad, null);
                
                //	getView(3, convertView, parent);
                	
                    break;
	    		
	    		

	    	//	holder.mAdView = (AdView) convertView.findViewById(R.id.reclistAdView);
	 		  
	    		}  
	    		convertView.setTag(holder);  
	    		
	    			
	      }  else {
				holder = (ViewHolder) convertView.getTag();
			try {
				resetViewHolder(holder);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
	      

  		switch (type) {
  		  case TYPE_ITEM:
  			  
  			  holder.image.setBackgroundResource(lists.get(position).getPicture());  
  	  		holder.tv1.setText(lists.get(position).getTitle());  
  	  		holder.tv2.setText(lists.get(position).getinfo());  
  	  		holder.tv3.setText(lists.get(position).geturl());  
  	      break;
  	      
  		  case TYPE_AD:
  			
  		
  	  		
  	  	
            break;
  		} 
  		
  		
  	 //  AdRequest adRequest = new AdRequest.Builder().build();
  	   //  holder.mAdView.loadAd(adRequest);
  		/*AdView adView = new AdView(RadioUi.class, AdSize.SMART_BANNER, Constants.getAdmobId(RadioUi.class));
  	  AdRequest adRequest = new AdRequest.Builder().build();
  		adView.loadAd(adRequest);
	      */
  		 count_i ++;
        return convertView;  

}  
	protected void resetViewHolder(ViewHolder ViewHolder) throws InterruptedException
	{
		
		ViewHolder.tv1.setText(null);
		ViewHolder.tv2.setText(null);
		ViewHolder.tv3.setText(null);
	
		ViewHolder.image.setImageResource(0);
		
	}
	
	
	
	
 	private  class ViewHolder {
 		ImageView image;
 		TextView tv1;
 		TextView tv2;
 		TextView tv3;
 	
 	} 	
     
 
 }
 