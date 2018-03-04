

	package com.mbizdev.kcsonsons;

	import java.util.ArrayList;



import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toast;

	public class OrderActivity extends Activity implements View.OnClickListener {

		private String Title = "Menu";
 		private String Subtitle = "List";
 		
		private int count;
		
		private ListView list;
		private ArrayList<KCMenuItem> menuItemOrder;

    	DataBaseAdapter mDataBase;           

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_order);

    		final Typeface OstrichSansInlineReg = Typeface.createFromAsset(getAssets(), "fonts/OstrichSansInline-regular.otf");
    		Typeface faceKiddish = Typeface.createFromAsset(getAssets(), "fonts/Kiddish.ttf");

    		Button ButtonEverything = (Button) findViewById(R.id.buttonEverything);
    		ButtonEverything.setTypeface(OstrichSansInlineReg);
    		ButtonEverything.setTextSize(23);
    		Button ButtonBurger = (Button) findViewById(R.id.buttonHamburger);
    		ButtonBurger.setTypeface(OstrichSansInlineReg);
    		ButtonBurger.setTextSize(22);
    		Button ButtonPitta = (Button) findViewById(R.id.buttonPitta);
    		ButtonPitta.setTypeface(OstrichSansInlineReg);
    		ButtonPitta.setTextSize(22);
    		Button ButtonClear = (Button) findViewById(R.id.buttonClear);
    		ButtonClear.setTypeface(OstrichSansInlineReg);
    		ButtonClear.setTextSize(22);
		     TextView Desc = (TextView) findViewById(R.id.textViewDescription);
	    		Desc.setTypeface(faceKiddish);	 
			    

			
			actionBarSetup();
			
			mDataBase = new DataBaseAdapter(this);         

	//		mDataBase.createDatabase(); /// added this !!!!!!!!!!!!
	    	
	    	mDataBase.open(); 	  
	    	
	//    	mDb.getOpenData();

	    	String order ="'order'";
	      //  ArrayList<MenuItem> MenuItemPittas = mDb.SelectMenuCategory(category);
	        menuItemOrder = mDataBase.SelectMenuOrder(order);

	    //    mDb.close();

	        
	        
	     list = (ListView)findViewById(R.id.listView1);
	        
	        final CustomListAdapter adapter = new CustomListAdapter(this);
	        list.setAdapter(adapter);
	  
	        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		   //     list.setSelector(android.R.color.darker_gray);

	
	      count = menuItemOrder.size();
	      

	  	list.setOnItemClickListener(new OnItemClickListener() {
		//	public void onItemClick(AdapterView<?> parent, View v, int position, long id) 

				public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
				{
		    		Typeface faceKiddish = Typeface.createFromAsset(getAssets(), "fonts/Kiddish.ttf");
		    		mDataBase.open();

			   //     Log.v("chose;", "true");
			        String itemDesc =  menuItemOrder.get(position).getDesc();
			        String itemChoice =  menuItemOrder.get(position).getItem();
 
 				//	Toast.makeText(getBaseContext(), "you select order : "+position+itemChoice, Toast.LENGTH_SHORT).show();
 
 			     //	v.setBackgroundColor(0x5555FFFF);

 			    	//mDb.UpdateOrderby1(itemChoice);
 				      TextView Desc = (TextView) findViewById(R.id.textViewDescription);
	 		    		Desc.setTypeface(faceKiddish);	    		
 			    		if (itemDesc==null) {
 		 				      Desc.setText(itemChoice);		      
  		 		    	}
 		 		    	else {
 		 		    		Desc.setTypeface(faceKiddish);	    		
 		 				      Desc.setText(itemDesc);		      
 		 		    	}
 			    	//	mDataBase.close();
				}
			});
	   
	      
 

	 
	  	
	  	
	      Button bPitta= (Button) findViewById(R.id.buttonPitta);
	      bPitta.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(OrderActivity.this, PittaActivity.class); 	  
			        startActivity(i);
			        }
			     });
	      Button bBurger= (Button) findViewById(R.id.buttonHamburger);
	      bBurger.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(OrderActivity.this, BurgerActivity.class); 	  
			        startActivity(i);
			        }
			     }); 
	      
	      Button bEvery= (Button) findViewById(R.id.buttonEverything);
	      bEvery.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(OrderActivity.this, EveryThingActivity.class); 	  
			        startActivity(i);
			        }
			     }); 
	      
	      
	      Button bClear= (Button) findViewById(R.id.buttonClear);
	      bClear.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        	mDataBase.UpdateOrderClear();
			        Intent i = new Intent(OrderActivity.this, OrderActivity.class); 	  
				        startActivity(i);
			        }
			     }); 
	      ImageView bQcam= (ImageView) findViewById(R.id.imageViewQCAM);
	      bQcam.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
				  	Boolean isNet =  isNetworkAvailable();
			  	    if (isNet){
				  	      Intent i = new Intent(OrderActivity.this, Q_Cam.class); 	  
					        startActivity(i);
			  	    }
			  	    else {
					      TextView Desc = (TextView) findViewById(R.id.textViewDescription);
		 		    		Desc.setTypeface(OstrichSansInlineReg);
					      Desc.setText("Connect to the Web to see the queue");
			  	    	}
			  	    }
			  	   
			     }); 
	      
		} // end onCreate

		
		
	    private class CustomListAdapter extends BaseAdapter 
	    {
	    	
	    	private Activity listContext;
	    	
	    	public CustomListAdapter(Context c)
	    	{
	    		listContext = (Activity)c;
	    	}
	    	
	    	public int getCount() 
	    	{
	    		// return number of lines to display
	    		
	    		return count;
	    	}
		
	    	public Object getItem(int position) 
	    	{
	    		// return item
	    		return position;  //was null
	    	}

	    	public long getItemId(int position) 
	    	{
	    		// return player id
	    		return position;   // was 0
	    	}

	    	public View getView(int position, View convertView, ViewGroup parent) 
	    	{
	    		
	    		LayoutInflater li = listContext.getLayoutInflater();
	    		View listItemView;
	    		if(convertView == null)
	    		{	
	    			listItemView = li.inflate(R.layout.list_item_view_order,null);
	    	 
	    		}
	    		else
	    		{
	    			listItemView = convertView;
	    		}
	    		 

	    		// define text for list items
	 //   		Typeface facebutter = Typeface.createFromAsset(getAssets(), "fonts/butterbrotpapier.ttf");
	  //  		Typeface faceChantelli = Typeface.createFromAsset(getAssets(), "fonts/Chantelli_Antiqua.ttf");
	    		
	      		Typeface faceAmaticBold = Typeface.createFromAsset(getAssets(), "fonts/Amatic-Bold.ttf");
		    		Typeface faceKiddish = Typeface.createFromAsset(getAssets(), "fonts/Kiddish.ttf");
 
	    		
	    		TextView tvListItem = (TextView)listItemView.findViewById(R.id.textViewItem);
	    		String item = menuItemOrder.get(position).getItem();
	    		if (item==null) {
	    			tvListItem.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListItem.setVisibility(View.VISIBLE);
 		    		tvListItem.setTypeface(faceAmaticBold);	    		
 		    		tvListItem.setText(menuItemOrder.get(position).getItem());
 		    	}
	    		
		        TextView tvListPrice = (TextView)listItemView.findViewById(R.id.textViewQuantity);
	    		String price = menuItemOrder.get(position).getOrderCount();		
 		    	if (price==null) {
 		    		tvListPrice.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListPrice.setVisibility(View.VISIBLE);
 		    		tvListPrice.setTypeface(faceKiddish);	    		
 		    		tvListPrice.setText(menuItemOrder.get(position).getOrderCount());
 		    	}


	    		
	    	//     listItemView = getView(position, convertView, parent);

	                View right = listItemView.findViewById(R.id.imageViewPlus);
	                right.setTag(position);
	                right.setOnClickListener(OrderActivity.this);
	                View left = listItemView.findViewById(R.id.imageViewMinus);
	                left.setTag(position);
	                left.setOnClickListener(OrderActivity.this);
	                View Item = listItemView.findViewById(R.id.textViewItem);
	                Item.setTag(position);
	                Item.setOnClickListener(OrderActivity.this);
	                View blankLeft = listItemView.findViewById(R.id.imageViewblankleft);
	                blankLeft.setTag(position);
	                blankLeft.setOnClickListener(OrderActivity.this);
	                View blankRight = listItemView.findViewById(R.id.imageViewblankright);
	                blankRight.setTag(position);
	                blankRight.setOnClickListener(OrderActivity.this);
	                
	          /*      View order = listItemView.findViewById(R.id.textViewQuantity);
	                order.setTag(position);
	                order.setOnClickListener(OrderActivity.this);
	            */    
	    	return listItemView;
	    	}

	    }	

	    public void onClick(View v) {
	        switch(v.getId()) {
	        case R.id.imageViewMinus:
	      //      Toast.makeText(this, "Left Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
	            int positionMinus = (Integer) v.getTag();
		       String itemChoiceMinus =  menuItemOrder.get(positionMinus).getItem();

		       mDataBase.UpdateOrderbyless1(itemChoiceMinus);
			        Intent i = new Intent(OrderActivity.this, OrderActivity.class); 	  
			        startActivity(i);

	            break;
	        case R.id.imageViewPlus:
	      //      Toast.makeText(this, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
	            int positionPlus = (Integer) v.getTag();
			       String itemChoicePlus =  menuItemOrder.get(positionPlus).getItem();

			       mDataBase.UpdateOrderby1(itemChoicePlus);	       
				       Intent iPlus = new Intent(OrderActivity.this, OrderActivity.class); 	  
				       startActivity(iPlus);       
				 break;       
	        case R.id.imageViewblankleft:
	      //      Toast.makeText(this, "Left Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
	            int positionMinusleft = (Integer) v.getTag();
		       String itemChoiceMinusleft =  menuItemOrder.get(positionMinusleft).getItem();

		       mDataBase.UpdateOrderbyless1(itemChoiceMinusleft);
			        Intent iblankleft = new Intent(OrderActivity.this, OrderActivity.class); 	  
			        startActivity(iblankleft);

	            break;
	        case R.id.imageViewblankright:
	      //      Toast.makeText(this, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
	            int positionPlusRight = (Integer) v.getTag();
			       String itemChoicePlusRight =  menuItemOrder.get(positionPlusRight).getItem();

			       mDataBase.UpdateOrderby1(itemChoicePlusRight);
				        Intent iPlusRight = new Intent(OrderActivity.this, OrderActivity.class); 	  
				        startActivity(iPlusRight);		        				        
	            break;
	        case R.id.textViewItem:
	 	      //      Toast.makeText(this, "Right Accessory "+v.getTag(), Toast.LENGTH_SHORT).show();
	    		Typeface faceKiddish = Typeface.createFromAsset(getAssets(), "fonts/Kiddish.ttf");

	  	            int positionDesc = (Integer) v.getTag();
	  			       String itemDesc =  menuItemOrder.get(positionDesc).getDesc();
				        String itemChoice =  menuItemOrder.get(positionDesc).getItem();

	  			     TextView Desc = (TextView) findViewById(R.id.textViewDescription);
	 		    		Desc.setTypeface(faceKiddish);	  
 			    		if (itemDesc==null) {
		 		    		Desc.setTypeface(faceKiddish);	    		
		 				      Desc.setText(itemChoice);		      
		 		    	}
		 		    	else {
  		 				      Desc.setText(itemDesc);
 		 		    	} 			
				        			    		
	        default:
	            break;
	        }
	    }
	    
	    
	    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//	        Toast.makeText(this, "Item Click "+position, Toast.LENGTH_SHORT).show();
	    }
/*	
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.pitta, menu);
			return true;
		}
	*/	
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
	    	populateOptionsMenu(menu);
			return super.onCreateOptionsMenu(menu);
	    }
	    
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	    {
	    	super.onCreateContextMenu(menu, v, menuInfo);
	    /*	if(v.equals(list))
	    	{
	    		menu.setHeaderTitle("Your Choice");
	    		menu.setHeaderIcon(R.drawable.apron);
	    		populateListContextMenu(menu);
	    	}
	    */	
	    }
	    
	    public void populateOptionsMenu(Menu menu)
	    {
	    	// add items to menu
	    	menu.add(Menu.NONE, 1, 1, "Opening Hours");
	    	menu.add(Menu.NONE, 2, 2, "Q Cam");
	    	menu.add(Menu.NONE, 3, 3, "Find us");

	    }
	   /* 
	    public void populateListContextMenu(Menu menu)
	    {
	    	menu.add(Menu.NONE, 11, 1, "Profile");
	    	menu.add(Menu.NONE, 12, 2, "Exit");
	    	
	    }
	  */
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	    	switch (item.getItemId()){
	    	case (android.R.id.home) :
	    		Intent intent = new Intent(this, MoreActivity.class);
	    		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    		startActivity(intent);
	    		return true;
	    		default:
	    			return applyMenuChoice(item);
	    	}
	    }
	    
	    public boolean onContextItemSelected(MenuItem item)
	    {
	    	return applyMenuChoice(item);
	    }
	    
	    private boolean applyMenuChoice(MenuItem item)
	    {
	    	Intent i;
	    	int menuItemId = item.getItemId();
	    	switch(menuItemId)
	    	{
	    	case 1:
	    		 i = new Intent(OrderActivity.this, OpenHoursActivity.class); 	  
	 	        startActivity(i);
	    		break;

	    	case 2:
	    		 i = new Intent(OrderActivity.this, Q_Cam.class); 	  
		  	        startActivity(i);    		break;	   
	    	case 3:
	    		 i = new Intent(OrderActivity.this, CameraMapActivity.class); 	  
		  	        startActivity(i);    		break;	   
	    	}
	    	return false;
	    }

	    public boolean isNetworkAvailable() {
	        ConnectivityManager cm = (ConnectivityManager) 
	          getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	        // if no network is available networkInfo will be null
	        // otherwise check if we are connected
	        if (networkInfo != null && networkInfo.isConnected()) {
	            return true;
	        }
	        return false;
	    } 
	    /**
	     * Sets the Action Bar for new Android versions.
	     */
	    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	    private void actionBarSetup() {
	      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	        ActionBar ab = getActionBar();
	        ab.setHomeButtonEnabled(true);
	        ab.setTitle(Title);
	        ab.setSubtitle(Subtitle);
	      }
	    }
	      
	    @Override
	    protected void onStop() {
	    	mDataBase.close(); //out jan22
	        finish();
	        super.onStop();
	    }
	
	
	
	



}

