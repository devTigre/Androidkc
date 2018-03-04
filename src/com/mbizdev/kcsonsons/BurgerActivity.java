

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
import android.widget.Toast;

 
	public class BurgerActivity extends Activity {

	//	private static final String TAG = "flagStatus";
 		private String Title = "Menu";
 		private String Subtitle = "Burgers and Ciabatta";
 		
 		String webTab = "none";
 		Toast customToast;
		String itemChoice = "none";
 		private int count;

		private ListView list;
		private ArrayList<KCMenuItem> menuItemBurgers;
    	DataBaseAdapter mDataBase;           

		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_burger);
			
 
			actionBarSetup();
			
			final Typeface faceTomatoRoundCondensed;
		    faceTomatoRoundCondensed = Typeface.createFromAsset(getAssets(), "fonts/TomatoRoundCondensed.ttf");
			
	    	 mDataBase = new DataBaseAdapter(this);         

			
	    	//DataBaseAdapter mDb = new DataBaseAdapter(this);         

	   // 	 mDataBase.createDatabase(); //taken out jan2022
	    	 mDataBase.open(); 	  
	 //   	mDb.getOpenData();

	    	String category ="'Hamburgers'";
	      //  ArrayList<MenuItem> MenuItemPittas = mDb.SelectMenuCategory(category);
	        menuItemBurgers = mDataBase.SelectMenuCategory(category);

	        mDataBase.close();

	        
	        
	     list = (ListView)findViewById(R.id.listView1);
	        
	        CustomListAdapter adapter = new CustomListAdapter(this);
	        list.setAdapter(adapter);
	  
	
	      count = menuItemBurgers.size();

	      

	
	  	list.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
				{
				 //       Log.v("chose;", "true");
			         itemChoice =  menuItemBurgers.get(position).getItem();
 			//		Toast.makeText(getBaseContext(), "Add "+itemChoice, Toast.LENGTH_SHORT).show();
				   
			        if (itemChoice != null){
			        final TextView textViewOrderDesc = (TextView)findViewById(R.id.textViewOrderDesc);
			        textViewOrderDesc.setTypeface(faceTomatoRoundCondensed);
			        textViewOrderDesc.setText("A "+itemChoice+" is added to the list");
			        textViewOrderDesc.postDelayed(new Runnable() {
			            public void run() {
			               textViewOrderDesc.setText("");
			            }
			        }, 1200);		        
			        } 
			        
			        mDataBase.open();
 			   // 	v.getFocusables(position);
 			   // 	v.setSelected(true);
 			     	v.setBackgroundColor(0xFFFF00);
  			      
 			     	mDataBase.UpdateOrderby1(itemChoice);
 			     	mDataBase.close(); 	     	
				}
			});
	   
	
		Typeface OstrichSansInlineReg = Typeface.createFromAsset(getAssets(), "fonts/OstrichSansInline-regular.otf");   		
		Button ButtonEverything = (Button) findViewById(R.id.buttonEverything);
		ButtonEverything.setTypeface(OstrichSansInlineReg);
		ButtonEverything.setTextSize(22);
		Button ButtonBurger = (Button) findViewById(R.id.buttonHamburger);
		ButtonBurger.setTypeface(OstrichSansInlineReg);
		ButtonBurger.setTextSize(25);
		Button ButtonPitta = (Button) findViewById(R.id.buttonPitta);
		ButtonPitta.setTypeface(OstrichSansInlineReg);
		ButtonPitta.setTextSize(22);
	 
	      Button bPitta= (Button) findViewById(R.id.buttonPitta);
	      bPitta.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(BurgerActivity.this, PittaActivity.class); 	  
			        startActivity(i);
			        }
			     });
	      Button bEvery= (Button) findViewById(R.id.buttonEverything);
	      bEvery.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(BurgerActivity.this, EveryThingActivity.class); 	  
			        startActivity(i);
			        }
			     }); 
	      Button bOrder= (Button) findViewById(R.id.buttonOrder);
	      bOrder.setOnClickListener(new View.OnClickListener() {
			        public void onClick(View arg0) {
			        Intent i = new Intent(BurgerActivity.this, OrderActivity.class); 	  
			        startActivity(i);
			        }
			     });
		} // end onCreate

///////////////////////////////////////////////////////////////////////////////////////////////
		
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
	    		Typeface faceOstrichBlack= Typeface.createFromAsset(getAssets(), "fonts/ostrich-black.ttf");
	    		Typeface faceTomatoRoundCondensed = Typeface.createFromAsset(getAssets(), "fonts/TomatoRoundCondensed.ttf");
  //flag
	    		String flag = menuItemBurgers.get(position).getFlag();
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
 		    		if (flag.contentEquals("chicken")){flagListItem.setImageResource(R.drawable.chicken);
		    		flagListItem.setVisibility(View.VISIBLE);}
		    		else if (flag.contentEquals("beef")){flagListItem.setImageResource(R.drawable.beef);
		    		flagListItem.setVisibility(View.VISIBLE);}
		    		else if (flag.contentEquals("veg")){flagListItem.setImageResource(R.drawable.vegetarian);
		    		flagListItem.setVisibility(View.VISIBLE);}
		    		else if (flag.contentEquals("pork")){flagListItem.setImageResource(R.drawable.pork);
		    		flagListItem.setVisibility(View.VISIBLE);}
		    		else if (flag.contentEquals("vegsides")){flagListItem.setImageResource(R.drawable.vegy_sides);
		    		flagListItem.setVisibility(View.VISIBLE);}
		    		else if (flag.contentEquals("fish")){flagListItem.setImageResource(R.drawable.fish);
		    		flagListItem.setVisibility(View.VISIBLE);}
		    		else if (flag.contentEquals("othersides")){flagListItem.setImageResource(R.drawable.other_sides);
		    		flagListItem.setVisibility(View.VISIBLE);}
		    		else if (flag.contentEquals("chips")){flagListItem.setImageResource(R.drawable.chips);
		    		flagListItem.setVisibility(View.VISIBLE);}	    		
 		    	}
	    		
	    		if (flag.contentEquals("gluten"))	    		
	    			{flagGlutenListItem.setVisibility(View.VISIBLE);
	    		 	flagGlutenListItem.setImageResource(R.drawable.gluten_free);}		    			    		


	    		if (flag==null || flag.contentEquals("0") || flag.contentEquals("gluten")){
	    		flagBannerListItem.setVisibility(View.GONE);
 		    	}
		    	else {
		    		if (flag.contentEquals("suggested_combo")){flagBannerListItem.setVisibility(View.VISIBLE);
		    			flagBannerListItem.setImageResource(R.drawable.suggested_combos);}
		    		else if (flag.contentEquals("burger_theme")){flagBannerListItem.setVisibility(View.VISIBLE);
		    			flagBannerListItem.setImageResource(R.drawable.burger_theme);}
		    		else if (flag.contentEquals("pure_irish_beef")){flagBannerListItem.setVisibility(View.VISIBLE);
		    			flagBannerListItem.setImageResource(R.drawable.pure_irish_beef);}
		    		else if (flag.contentEquals("add_cheese")){flagBannerListItem.setVisibility(View.VISIBLE);
		    			flagBannerListItem.setImageResource(R.drawable.add_cheese);}
   		
 		    	}
	    		
    		
	    		
	    		
	    		TextView tvListItem = (TextView)listItemView.findViewById(R.id.textViewItem);
	    		String item = menuItemBurgers.get(position).getItem();
	    		if (item==null) {
	    			tvListItem.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListItem.setVisibility(View.VISIBLE);
 		    		tvListItem.setTypeface(faceOstrichBlack);	    		
 		    		tvListItem.setText(menuItemBurgers.get(position).getItem());
 		    		System.out.print(item);
 		    	}
	     		
	    		TextView tvListDesc = (TextView)listItemView.findViewById(R.id.textViewDesc);
	    		if (item==null) {
	    			tvListDesc.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListDesc.setVisibility(View.VISIBLE);
 		    		tvListDesc.setTypeface(faceTomatoRoundCondensed);
 		    		tvListDesc.setText(menuItemBurgers.get(position).getDesc());
 		    	}
 	    		
	    		TextView tvCenterBig = (TextView)listItemView.findViewById(R.id.textViewCenterBig);
	    		String centerBig = menuItemBurgers.get(position).getCenterBig();
    			tvCenterBig.setVisibility(View.GONE);

	    		if (centerBig==null) {
	    			tvCenterBig.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvCenterBig.setVisibility(View.VISIBLE);
 		    		tvCenterBig.setTypeface(faceOstrichBlack);
 		    		tvCenterBig.setText(menuItemBurgers.get(position).getCenterBig());
 		    	}
	    		
	    		
	    		TextView tvListCenterRed = (TextView)listItemView.findViewById(R.id.textViewCenterRed);
	    		String centerRed = menuItemBurgers.get(position).getCenterRed();
	    		if (centerRed==null) {
	    			tvListCenterRed.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListCenterRed.setVisibility(View.VISIBLE);
 		    		tvListCenterRed.setTypeface(faceTomatoRoundCondensed);
 		    		tvListCenterRed.setText(menuItemBurgers.get(position).getCenterRed());
 		    	}
	    		
	    		TextView tvListCenterBlack = (TextView)listItemView.findViewById(R.id.textViewCenterBlack);
	    		String centerBlack = menuItemBurgers.get(position).getCenterBlack();
	    		if (centerBlack==null) {
	    			tvListCenterBlack.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListCenterBlack.setVisibility(View.VISIBLE);
 		    		tvListCenterBlack.setTypeface(faceTomatoRoundCondensed);
 		    		tvListCenterBlack.setText(menuItemBurgers.get(position).getCenterBlack());
 		    	}
	    		
	    		TextView tvListPrice = (TextView)listItemView.findViewById(R.id.textViewPrice);
	    		String price = menuItemBurgers.get(position).getPrice();
	    		if (price==null) {
	    			tvListPrice.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvListPrice.setVisibility(View.VISIBLE);
 		    		tvListPrice.setTypeface(faceTomatoRoundCondensed);
 		    		tvListPrice.setText(menuItemBurgers.get(position).getPrice());
 		    	}    		
	    			    
	    		TextView tvSmallText = (TextView)listItemView.findViewById(R.id.textViewSmallText);
	    		String smallText = menuItemBurgers.get(position).getSmallText();
	    		if (smallText==null) {
	    			tvSmallText.setVisibility(View.GONE);
 		    	}
 		    	else {
 		    		tvSmallText.setVisibility(View.VISIBLE);
 		    		tvSmallText.setTypeface(faceTomatoRoundCondensed);
 		    		tvSmallText.setText(menuItemBurgers.get(position).getSmallText());
 		    	}  
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
	    }

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
	    		 i = new Intent(BurgerActivity.this, OpenHoursActivity.class); 	  
	  	        startActivity(i);    		
	  	        break;
	    	case 2:
	    		 i = new Intent(BurgerActivity.this, MoreActivity.class); 	  
		  	     startActivity(i);    		    		
		  	     break;
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

	    	super.onStop();	        finish();

	    }

	}
	
	
	/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.burger, menu);
		return true;
	}
	*/

