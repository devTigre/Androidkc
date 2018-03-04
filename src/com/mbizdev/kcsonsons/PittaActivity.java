package com.mbizdev.kcsonsons;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class PittaActivity extends Activity {
	private String Title = "Menu";
	private String Subtitle = "Filled Pitas";
	private  String webTab = "none";
	private int count;
	
	private ListView list;
	private ArrayList<KCMenuItem> MenuItemPittas;
 	DataBaseAdapter mDataBase;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pitta);
		
		actionBarSetup();
		
		final Typeface faceTomatoRoundCondensed;
	    faceTomatoRoundCondensed = Typeface.createFromAsset(getAssets(), "fonts/TomatoRoundCondensed.ttf");
  
	    mDataBase = new DataBaseAdapter(this);         

 
	//    mDataBase.createDatabase(); // now in Home activity out 22jan
	    mDataBase.open(); 	      	
 
    	String category ="'FilledPittas'";
      //  ArrayList<MenuItem> MenuItemPittas = mDb.SelectMenuCategory(category);
        MenuItemPittas = mDataBase.SelectMenuCategory(category);

        mDataBase.close();

        
        
     list = (ListView)findViewById(R.id.listView1);
        
        CustomListAdapter adapter = new CustomListAdapter(this);
        list.setAdapter(adapter);
  


 //     KCMenuItem third = MenuItemPittas.get(3); //Mon
      count = MenuItemPittas.size();
  //    String pricecreole = MenuItemPittas.get(1).getPrice();

	    Button ButtonNew = (Button) findViewById(R.id.buttonNew);
	    ButtonNew.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
	    Boolean isNet =	isNetworkAvailable();
	  //  webTab="Special";
      if (isNet) {
     	
      	//showToast(webTab);
	        Intent i = new Intent(PittaActivity.this, NewServer.class); 	  
	        startActivity(i);
      }
      else {
	       // noNetInsta = true;
        showToast(webTab);
      	}
      }
   }); 


  	list.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
			{
						 //       Log.v("chose;", "true");
			        String itemChoice =  MenuItemPittas.get(position).getItem();
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
  			      
  			     //	v.setBackground(getResources().getDrawable(R.drawable.fb_icon));
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
	ButtonBurger.setTextSize(22);
	Button ButtonPitta = (Button) findViewById(R.id.buttonPitta);
	ButtonPitta.setTypeface(OstrichSansInlineReg);
	ButtonPitta.setTextSize(25);
	
      Button bHamburger= (Button) findViewById(R.id.buttonHamburger);
      bHamburger.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		        Intent i = new Intent(PittaActivity.this, BurgerActivity.class); 	  
		        startActivity(i);
		        }
		     });
      Button bEvery= (Button) findViewById(R.id.buttonEverything);
      bEvery.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		        Intent i = new Intent(PittaActivity.this, EveryThingActivity.class); 	  
		        startActivity(i);
		        }
		     });
      Button bOrder= (Button) findViewById(R.id.buttonOrder);
      bOrder.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		        Intent i = new Intent(PittaActivity.this, OrderActivity.class); 	  
		        startActivity(i);
		        }
		     });
  } // end of create

  
  
 
      
      


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

    		// define text for list items
    		Typeface faceOstrichBlack = Typeface.createFromAsset(getAssets(), "fonts/ostrich-black.ttf");
    		Typeface faceTomatoRoundCondensed = Typeface.createFromAsset(getAssets(), "fonts/TomatoRoundCondensed.ttf");

    		  //flag
    		String flag = MenuItemPittas.get(position).getFlag();
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
    		String item = MenuItemPittas.get(position).getItem();
    		if (item==null) {
    			tvListItem.setVisibility(View.GONE);
		    	}
		    	else {
		    		tvListItem.setVisibility(View.VISIBLE);
 		    		tvListItem.setTypeface(faceOstrichBlack);	    		
		    		tvListItem.setText(MenuItemPittas.get(position).getItem());
		    	}
    		tvListItem.setText(MenuItemPittas.get(position).getItem());
     		
    		TextView tvListDesc = (TextView)listItemView.findViewById(R.id.textViewDesc);
    		String desc = MenuItemPittas.get(position).getDesc();
    		if (desc==null) {
    			tvListDesc.setVisibility(View.GONE);
		    	}
		    	else {
		    		tvListDesc.setVisibility(View.VISIBLE);
		    		tvListDesc.setText(MenuItemPittas.get(position).getDesc());
 		    		tvListDesc.setTypeface(faceTomatoRoundCondensed);

		    	}
    		
    		TextView tvCenterBig = (TextView)listItemView.findViewById(R.id.textViewCenterBig);
    		String centerBig = MenuItemPittas.get(position).getCenterBig();
			tvCenterBig.setVisibility(View.GONE);

    		if (centerBig==null) {
    			tvCenterBig.setVisibility(View.GONE);
		    	}
		    	else {
		    		tvCenterBig.setVisibility(View.VISIBLE);
		    		tvCenterBig.setTypeface(faceOstrichBlack);	    		
		    		tvCenterBig.setText(MenuItemPittas.get(position).getCenterBig());
		    	}
    		
    		
    		TextView tvListCenterRed = (TextView)listItemView.findViewById(R.id.textViewCenterRed);
    		String centerRed = MenuItemPittas.get(position).getCenterRed();
    		if (centerRed==null) {
    			tvListCenterRed.setVisibility(View.GONE);
		    	}
		    	else {
		    		tvListCenterRed.setVisibility(View.VISIBLE);
		    		tvListCenterRed.setTypeface(facebutter);
		    		tvListCenterRed.setText(MenuItemPittas.get(position).getCenterRed());
		    	}
    		
    		TextView tvListCenterBlack = (TextView)listItemView.findViewById(R.id.textViewCenterBlack);
    		String centerBlack = MenuItemPittas.get(position).getCenterBlack();
    		if (centerBlack==null) {
    			tvListCenterBlack.setVisibility(View.GONE);
		    	}
		    	else {
		    		tvListCenterBlack.setVisibility(View.VISIBLE);
		    		tvCenterBig.setTypeface(faceOstrichBlack);	    		
		    		tvListCenterBlack.setText(MenuItemPittas.get(position).getCenterBlack());
		    	}
    		
    		TextView tvListPrice = (TextView)listItemView.findViewById(R.id.textViewPrice);
    		String price = MenuItemPittas.get(position).getPrice();
    		if (price==null) {
    			tvListPrice.setVisibility(View.GONE);
		    	}
		    	else {
		    		tvListPrice.setVisibility(View.VISIBLE);
 		    		tvListPrice.setTypeface(faceTomatoRoundCondensed);	    		
		    		tvListPrice.setText(MenuItemPittas.get(position).getPrice());
		    	}
    		
    		TextView tvSmallText = (TextView)listItemView.findViewById(R.id.textViewSmallText);
    		String smallText = MenuItemPittas.get(position).getSmallText();
    		if (smallText==null) {
    			tvSmallText.setVisibility(View.GONE);
		    	}
		    	else {
		    		tvSmallText.setVisibility(View.VISIBLE);
		    		tvSmallText.setTypeface(faceTomatoRoundCondensed);
		    		tvSmallText.setText(MenuItemPittas.get(position).getSmallText());
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
    	menu.add(Menu.NONE, 3, 3, "Allergen Advice");

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
    		 i = new Intent(PittaActivity.this, OpenHoursActivity.class); 	  
 	        startActivity(i);
    		break;
 
    	case 2:
   		 	i = new Intent(PittaActivity.this, MoreActivity.class); 	  
	        startActivity(i);      		break;
 		case 3:
   		 	i = new Intent(PittaActivity.this, AllergenActivity.class);
	        startActivity(i);      		break;

    	}
    	return false;
    }

    
	public  void showToast(String webTab) {
		
	    int toastduration=1000;

        // create a LinearLayout and Views 
       LinearLayout  toastlayout=new LinearLayout(this); 
       toastlayout.setOrientation(1);
		final  TextView  tv=new TextView(this);   // for toast
	    final  ImageView   img=new ImageView(this);
       tv.setTextColor(Color.RED);
       tv.setTextSize(17);   
       tv.setGravity(Gravity.CENTER);

									      

	img.setImageResource(R.drawable.neww128);
	toastduration = 5000;
	tv.setText("connect to the web for new .."); 


	    // add both the Views TextView and ImageView in layout
	       toastlayout.addView(img);
	       toastlayout.addView(tv);
 
	       Toast customToast=new Toast(this); //context is object of Context write "this" if you are an Activity
	      // Set The layout as Toast View
	       customToast.setView(toastlayout);
	             
	         // Position you toast here toast position is 50 dp from bottom you can give any integral value    
	        customToast.setGravity(Gravity.CENTER, 0, 0);	        
	        customToast.setDuration(LENGTH_LONG);
	         customToast.show();
	      
	         webTab="none";
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
    	mDataBase.close();

        super.onStop();
	finish();
    }

    


}