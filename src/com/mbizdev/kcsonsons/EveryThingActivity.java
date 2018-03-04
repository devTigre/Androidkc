

	package com.mbizdev.kcsonsons;

	import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
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
 

	public class EveryThingActivity extends Activity {
		
		private String Title = "Menu";
 		private String Subtitle = "Everything Else";
 		
		private int count;
		
		private ListView list;
		private ArrayList<KCMenuItem> menuItemEvery;
///		Toast toast;
 
    	DataBaseAdapter mDataBase;           

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_every_thing);
			
			final Typeface faceTomatoRoundCondensed;
			faceTomatoRoundCondensed = Typeface.createFromAsset(getAssets(), "fonts/TomatoRoundCondensed.ttf");
		    
	        final TextView textViewOrderDesc = (TextView)findViewById(R.id.textViewOrderDesc);
	        final TextView isGlutenFree = (TextView)findViewById(R.id.textViewGlutenMessage);
	        //isGlutenFree.setVisibility(View.GONE);
			actionBarSetup();
			
			mDataBase = new DataBaseAdapter(this);         

	//		mDataBase.createDatabase(); //taken out jan22
			mDataBase.open(); 	  
	    	
	//    	mDb.getOpenData();

	    	String category ="'Everything'";
	      //  ArrayList<MenuItem> MenuItemPittas = mDb.SelectMenuCategory(category);
	        menuItemEvery = mDataBase.SelectMenuCategory(category);

	        mDataBase.close();

	        
	        
	     list = (ListView)findViewById(R.id.listView1);
	        
	        CustomListAdapter adapter = new CustomListAdapter(this);
	        list.setAdapter(adapter);
	  
	
	      count = menuItemEvery.size();

 

		
	  	list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
				{
			 //       Log.v("chose;", "true");
			        String itemChoice =  menuItemEvery.get(position).getItem();
			        String isitemChoiceGluten =  menuItemEvery.get(position).getFlag();

 			//		Toast.makeText(getBaseContext(), "Add "+itemChoice+isitemChoiceGluten, Toast.LENGTH_SHORT).show();
			        
			        if (itemChoice != null){
				        textViewOrderDesc.setTypeface(faceTomatoRoundCondensed);
				        textViewOrderDesc.setText("A "+itemChoice+" is added to the list");
				       
				        textViewOrderDesc.postDelayed(new Runnable() {
				            public void run() {
				               textViewOrderDesc.setText("");
 
					    	//   isGlutenFree.setImageResource(R.drawable.gluten_free_large);
				              // isGlutenFree.setVisibility(View.VISIBLE);
 				            }
				        	}, 1400);	
			        	}		
			        
			        if (isitemChoiceGluten.contentEquals("gluten")){
			        	isGlutenFree.setTypeface(faceTomatoRoundCondensed);
			        	isGlutenFree.setGravity(Gravity.CENTER);
			        	isGlutenFree.setText("gluten\n free");
				       
			        	isGlutenFree.postDelayed(new Runnable() {
				            public void run() {
				            	isGlutenFree.setText("");
  				            }
				        	}, 1400);	
			        	}		
			       
			        
			        
			        
			        mDataBase.open();
 			   // 	v.getFocusables(position);
 			   // 	v.setSelected(true);
 			     	v.setBackgroundColor(0xFFFF00);
  			      
  			     //	v.setBackground(getResources().getDrawable(R.drawable.fb_icon));
 			     	mDataBase.UpdateOrderby1(itemChoice);
 			     	mDataBase.close(); 	     	
 		/*			// define intent
					Intent intent = new Intent(PittaActivity.this, TwitterActivity.class);

			 
		*/	    	
				}
			});
	  	
		Typeface OstrichSansInlineReg = Typeface.createFromAsset(getAssets(), "fonts/OstrichSansInline-regular.otf");	     
		Button ButtonEverything = (Button) findViewById(R.id.buttonEverything);
		ButtonEverything.setTypeface(OstrichSansInlineReg);
		ButtonEverything.setTextSize(25);
		Button ButtonBurger = (Button) findViewById(R.id.buttonHamburger);
		ButtonBurger.setTypeface(OstrichSansInlineReg);
		ButtonBurger.setTextSize(22);
		Button ButtonPitta = (Button) findViewById(R.id.buttonPitta);
		ButtonPitta.setTypeface(OstrichSansInlineReg);
		ButtonPitta.setTextSize(22);
		Button ButtonOrder = (Button) findViewById(R.id.buttonOrder);
		ButtonOrder.setTypeface(OstrichSansInlineReg);
		ButtonOrder.setTextSize(22);
		
	      Button bPitta= (Button) findViewById(R.id.buttonPitta);
	      bPitta.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(EveryThingActivity.this, PittaActivity.class); 	  
			        startActivity(i);
			        }
			     });
	      Button bEvery= (Button) findViewById(R.id.buttonHamburger);
	      bEvery.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(EveryThingActivity.this, BurgerActivity.class); 	  
			        startActivity(i);
			        }
			     }); 
	      Button bOrder= (Button) findViewById(R.id.buttonOrder);
	      bOrder.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(EveryThingActivity.this, OrderActivity.class); 	  
			        startActivity(i);
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
	    			listItemView = li.inflate(R.layout.list_item_view,null);
	    	 
	    		}
	    		else
	    		{
	    			listItemView = convertView;
	    		}
	    		 

	    		// define text for list items
	    		Typeface facebutter = Typeface.createFromAsset(getAssets(), "fonts/butterbrotpapier.ttf");
	    //..		Typeface faceAmaticBold = Typeface.createFromAsset(getAssets(), "fonts/AmaticBold_Antiqua.ttf");
	    		
	      		Typeface faceOstrichBlack = Typeface.createFromAsset(getAssets(), "fonts/ostrich-black.ttf");
		    		Typeface faceTomatoRoundCondensed = Typeface.createFromAsset(getAssets(), "fonts/TomatoRoundCondensed.ttf");

		    		  //flag
		    		String flag = menuItemEvery.get(position).getFlag();
		    		ImageView flagListItem = (ImageView)listItemView.findViewById(R.id.imageViewFlag);
		    		ImageView flagGlutenListItem = (ImageView)listItemView.findViewById(R.id.imageViewGluten);
		    		ImageView flagBannerListItem = (ImageView)listItemView.findViewById(R.id.imageViewBanner);
		    		 
		    		flagListItem.setVisibility(View.GONE);
		    		flagGlutenListItem.setVisibility(View.GONE);
		    		flagBannerListItem.setVisibility(View.GONE);


		    		if (flag==null || flag.contentEquals("0") || flag.contentEquals("gluten")){
		    		flagListItem.setVisibility(View.GONE);
	 		    	}
			    	else {
			    		flagListItem.setVisibility(View.VISIBLE);
			    		if (flag.contentEquals("chicken")){flagListItem.setImageResource(R.drawable.chicken);}
			    		else if (flag.contentEquals("beef")){flagListItem.setImageResource(R.drawable.beef);}
			    		else if (flag.contentEquals("veg")){flagListItem.setImageResource(R.drawable.vegetarian);}
			    		else if (flag.contentEquals("pork")){flagListItem.setImageResource(R.drawable.pork);}
			    		else if (flag.contentEquals("vegsides")){flagListItem.setImageResource(R.drawable.vegy_sides);}
			    		else if (flag.contentEquals("fish")){flagListItem.setImageResource(R.drawable.fish);}
			    		else if (flag.contentEquals("othersides")){flagListItem.setImageResource(R.drawable.other_sides);}
			    		else if (flag.contentEquals("chips")){flagListItem.setImageResource(R.drawable.chips);}	    		
	 		    	}
		    		
		    		if (flag.contentEquals("gluten"))	    		
		    			{flagGlutenListItem.setVisibility(View.VISIBLE);
		    		 	flagGlutenListItem.setImageResource(R.drawable.gluten_free);}	
		    		
	    		TextView tvListItem = (TextView)listItemView.findViewById(R.id.textViewItem);
	    		String item = menuItemEvery.get(position).getItem();
	    		if (item==null) {
	    			tvListItem.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListItem.setVisibility(View.VISIBLE);
 		    		tvListItem.setTypeface(faceOstrichBlack);	    		
 		    		tvListItem.setText(menuItemEvery.get(position).getItem());
 		    	}
	    		
	    		
	    		TextView tvListDesc = (TextView)listItemView.findViewById(R.id.textViewDesc);
	    		String desc= menuItemEvery.get(position).getDesc();
	    		if (desc==null) {
	    			tvListDesc.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListDesc.setVisibility(View.VISIBLE);
 		    		tvListDesc.setTypeface(faceTomatoRoundCondensed);	   
 		    //..		tvListDesc.setTypeface(faceAmaticBold);	    		

 		    		tvListDesc.setText(menuItemEvery.get(position).getDesc());
 		    	}
	    		
	    		TextView tvCenterBig = (TextView)listItemView.findViewById(R.id.textViewCenterBig);
	    		String centerBig = menuItemEvery.get(position).getCenterBig();
				tvCenterBig.setVisibility(View.GONE);

	    		if (centerBig==null) {
	    			tvCenterBig.setVisibility(View.GONE);
			    	}
			    	else {
			    		tvCenterBig.setVisibility(View.VISIBLE);
			    //..		tvCenterBig.setTypeface(faceAmaticBold);
					    	tvCenterBig.setTypeface(faceOstrichBlack);

			    		tvCenterBig.setText(menuItemEvery.get(position).getCenterBig());
			    	}
	    		
	    		
	    		TextView tvListCenterRed = (TextView)listItemView.findViewById(R.id.textViewCenterRed);
	    		String centerRed = menuItemEvery.get(position).getCenterRed();
	    		if (centerRed==null) {
	    			tvListCenterRed.setVisibility(View.GONE);
			    	}
			    	else {
			    		tvListCenterRed.setVisibility(View.VISIBLE);
			    		tvListCenterRed.setTypeface(faceOstrichBlack);
			    		tvListCenterRed.setText(menuItemEvery.get(position).getCenterRed());
			    	}
	    		
	    		TextView tvListCenterBlack = (TextView)listItemView.findViewById(R.id.textViewCenterBlack);
	    		String centerBlack = menuItemEvery.get(position).getCenterBlack();
	    		if (centerBlack==null) {
	    			tvListCenterBlack.setVisibility(View.GONE);
			    	}
			    	else {
			    		tvListCenterBlack.setVisibility(View.VISIBLE);
			    		tvListCenterBlack.setTypeface(faceTomatoRoundCondensed);
			    		tvListCenterBlack.setText(menuItemEvery.get(position).getCenterBlack());
			    	}
	    		
	    		TextView tvListPrice = (TextView)listItemView.findViewById(R.id.textViewPrice);
	    		String price = menuItemEvery.get(position).getPrice();		
 		    	if (price==null) {
 		    		tvListPrice.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListPrice.setVisibility(View.VISIBLE);
 		    		tvListPrice.setTypeface(faceTomatoRoundCondensed);	    		
 		    		tvListPrice.setText(menuItemEvery.get(position).getPrice());
 		    	}
 		    	
	    		TextView tvSmallText = (TextView)listItemView.findViewById(R.id.textViewSmallText);
	    		String smallText = menuItemEvery.get(position).getSmallText();
	    		if (smallText==null) {
	    			tvSmallText.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvSmallText.setVisibility(View.VISIBLE);
 		    		tvSmallText.setTypeface(faceTomatoRoundCondensed);
 		    		tvSmallText.setText(menuItemEvery.get(position).getSmallText());
 		    	}
 		    	/*    	
 		   	Toast toast= Toast.makeText(getApplicationContext(), ".."+item+" "+desc+" "+price,Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.TOP, 50, 100);
			toast.show();	
 		*/    	
 		    	
	    	return listItemView;
	    	}

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
	    	menu.add(Menu.NONE, 2, 2, "KC&SON&SONS");
	    	menu.add(Menu.NONE, 3, 3, "List");

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
	    		 i = new Intent(EveryThingActivity.this, OpenHoursActivity.class); 	  
	 	        startActivity(i);
	    		break;

	    	case 2:
	    		 i = new Intent(EveryThingActivity.this, MoreActivity.class); 	  
		  	        startActivity(i);    		break;	   
	    	case 3:
	    		 i = new Intent(EveryThingActivity.this, OrderActivity.class); 	  
		  	        startActivity(i);    		break;	   
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
	    	mDataBase.close();
	        finish();
	        super.onStop();
	    }
	
	
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.burger, menu);
		return true;
	}
	*/




}
