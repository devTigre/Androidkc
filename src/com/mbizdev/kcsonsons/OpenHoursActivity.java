

	package com.mbizdev.kcsonsons;


	import java.util.ArrayList;
import java.util.Calendar;

 import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

	public class OpenHoursActivity extends Activity {


		private String Title = "KC & Son & Sons";
 
	    private int fontdays = 18;	    private int fonttimes = 20; private int fonttoday = 21;


	// AnimationDrawable animation;
    	DataBaseAdapter mDataBase;      

		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_open_hours);
		
			actionBarSetup();

			Typeface faceOstrichblack = Typeface.createFromAsset(getAssets(), "fonts/ostrich-black.ttf");
    		Typeface faceamericantypewriter = Typeface.createFromAsset(getAssets(), "fonts/americantypewriter.ttf");

		 //      TextView openHours = (TextView) findViewById(R.id.textViewOpenhours);
		 //      openHours.setText(getResources().getString(R.string.openHours));
		 //      openHours.setTypeface(faceOstrichblack);
		  //     openHours.setTextColor(getResources().getColor(R.color.darkRed));		
		  //     openHours.setTextSize(30);

			
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_WEEK); 
			// If current day is Sunday, day=1
			
			
			
			//mDb = new DataBase(this,(path));
	    	 mDataBase = new DataBaseAdapter(this);         

	    //	mDataBase.createDatabase(); out29jan2016
	    	mDataBase.open(); 

	        
	     //
	    	mDataBase.getOpenData();
	        
	        
	         ArrayList<Opening> op = mDataBase.SelectOpening();
	       
	         mDataBase.close();

	      
	       Opening mon = op.get(0); //Mon
	       Opening tue = op.get(1); //Tue
	       Opening wed = op.get(2);
	       Opening thu = op.get(3);
	       Opening fri = op.get(4);
	       Opening sat = op.get(5);
	       Opening sun = op.get(6);

	//mon
	       TextView m = (TextView) findViewById(R.id.textViewMonMore);
	       TextView mo1 = (TextView) findViewById(R.id.textViewMOpen1);
	       TextView mc1 = (TextView) findViewById(R.id.textViewMClose1);

	       m.setTypeface(faceOstrichblack);
	       m.setTextSize(fontdays);
	       mo1.setText(mon.getOpen1());
	       mo1.setTypeface(faceamericantypewriter);
	       mo1.setTextSize(fonttimes);
	       mc1.setText(mon.getClose1());
	       mc1.setTypeface(faceamericantypewriter);
	       mc1.setTextSize(fonttimes);
	       
	       if (day==2){
	       m.setText(getResources().getString(R.string.Today));
	       m.setTypeface(faceOstrichblack, Typeface.BOLD);
	       m.setTextSize(fonttoday);
		      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		    	  m.setRotationX(-40);}
	       m.setTextColor(getResources().getColor(R.color.darkRed));
	       mo1.setTextColor(getResources().getColor(R.color.darkRed));
	       mc1.setTextColor(getResources().getColor(R.color.darkRed));
	       }
	       else {
	       m.setText(mon.getDay());
	       }



//tue	       
	       TextView tu = (TextView) findViewById(R.id.textViewTue);
	       TextView tueOpen1 = (TextView) findViewById(R.id.textViewTuOpen1);
	       TextView tueClose1 = (TextView) findViewById(R.id.textViewTuClose1);
	       
	       tueOpen1.setText(tue.getOpen1());	
	       tueClose1.setText(tue.getClose1());
	       tueOpen1.setTypeface(faceamericantypewriter);
	       tueOpen1.setTextSize(fonttimes);
	       tueClose1.setTypeface(faceamericantypewriter);
	       tueClose1.setTextSize(fonttimes);
	       tu.setTypeface(faceOstrichblack);
	       tu.setTextSize(fontdays);
	       
	       if (day==3){
		       tu.setText(getResources().getString(R.string.Today));
		       tu.setTypeface(faceOstrichblack, Typeface.BOLD);
		       tu.setRotationX(-40);
		       tu.setTextColor(getResources().getColor(R.color.darkRed));
		       tueOpen1.setTextColor(getResources().getColor(R.color.darkRed));
		       tueClose1.setTextColor(getResources().getColor(R.color.darkRed));
			      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		       tu.setTextSize(fonttoday);	}	         
	       }
		   else {
	       tu.setText(tue.getDay());
		   }       

	//wed
	       TextView we = (TextView) findViewById(R.id.textViewWed);
	       TextView wedOpen1 = (TextView) findViewById(R.id.textViewWOpen1);
	       TextView weClose1 = (TextView) findViewById(R.id.textViewWClose1);
	       
	       we.setTypeface(faceOstrichblack);
	       we.setTextSize(fontdays);
	       wedOpen1.setText(wed.getOpen1());
	       weClose1.setText(wed.getClose1());
	       wedOpen1.setTypeface(faceamericantypewriter);
	       wedOpen1.setTextSize(fonttimes);
	       weClose1.setTypeface(faceamericantypewriter);
	       weClose1.setTextSize(fonttimes);
	       if (day==4){
		       we.setText(getResources().getString(R.string.Today));
		       we.setTypeface(faceOstrichblack);
		       we.setTextSize(fonttoday);
		       we.setTypeface(faceOstrichblack, Typeface.BOLD);
		       we.setTextColor(getResources().getColor(R.color.darkRed));
		       wedOpen1.setTextColor(getResources().getColor(R.color.darkRed));
		       weClose1.setTextColor(getResources().getColor(R.color.darkRed));
			      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		       we.setRotationX(-40);}
}
		       else {
	       we.setText(wed.getDay());
	       }  

	       
	//Thur
	       TextView thursday = (TextView) findViewById(R.id.textViewTHur);
	       TextView thuOpen1 = (TextView) findViewById(R.id.textViewThOpen1);
	       TextView thuClose1 = (TextView) findViewById(R.id.textViewThClose1);
	       TextView thuOpen2 = (TextView) findViewById(R.id.textViewThOpen2);
	       TextView thuClose2 = (TextView) findViewById(R.id.textViewThClose2);
	       
	       thursday.setTypeface(faceOstrichblack);
	       thursday.setTextSize(fontdays);
	       thuOpen1.setText(thu.getOpen1());
	       thuClose1.setText(thu.getClose1());      
	       thuOpen2.setText(thu.getOpen2());
	       thuClose2.setText(thu.getClose2());        
	       thuOpen1.setTypeface(faceamericantypewriter);
	       thuOpen1.setTextSize(fonttimes);
	       thuClose1.setTypeface(faceamericantypewriter);
	       thuClose1.setTextSize(fonttimes);
	       thuOpen2.setTypeface(faceamericantypewriter);
	       thuOpen2.setTextSize(fonttimes);
	       thuClose2.setTypeface(faceamericantypewriter);
	       thuClose2.setTextSize(fonttimes);
	       
	       if (day==5){
	    	   thursday.setText(getResources().getString(R.string.Today));
	    	   thursday.setTypeface(faceOstrichblack, Typeface.BOLD);
		       thursday.setTextSize(fonttoday);
	    	   thursday.setTextColor(getResources().getColor(R.color.darkRed));
	    	     thuOpen1.setTextColor(getResources().getColor(R.color.darkRed));
			       thuOpen2.setTextColor(getResources().getColor(R.color.darkRed));     
			       thuClose1.setTextColor(getResources().getColor(R.color.darkRed));     
			       thuClose2.setTextColor(getResources().getColor(R.color.darkRed));
				      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			       thursday.setRotationX(-40);}
}
		       else {
	       thursday.setText(thu.getDay()); 

    }

	       
	 //Fri
	     
	       TextView friday = (TextView) findViewById(R.id.textViewFri);
	       TextView friOpen1 = (TextView) findViewById(R.id.textViewFOpen1);
	       TextView friClose1 = (TextView) findViewById(R.id.textViewFClose1);
	       TextView friOpen2 = (TextView) findViewById(R.id.textViewFOpen2);
	       TextView friClose2 = (TextView) findViewById(R.id.textViewFClose2);
	       
	       friday.setTypeface(faceOstrichblack);
	       friday.setTextSize(fontdays);
	       friOpen1.setText(fri.getOpen1());
	       friClose1.setText(fri.getClose1());      
	       friOpen2.setText(fri.getOpen2());
	       friClose2.setText(fri.getClose2());   
	       friOpen1.setTypeface(faceamericantypewriter);
	       friOpen1.setTextSize(fonttimes);
	       friClose1.setTypeface(faceamericantypewriter);
	       friClose1.setTextSize(fonttimes);
	       friOpen2.setTypeface(faceamericantypewriter);
	       friOpen2.setTextSize(fonttimes);
	       friClose2.setTypeface(faceamericantypewriter);
	       friClose2.setTextSize(fonttimes);
	       
	       if (day==6){
	    	   friday.setText(getResources().getString(R.string.Today));
	    	   friday.setTypeface(faceOstrichblack, Typeface.BOLD);
	    	   friday.setTextColor(getResources().getColor(R.color.darkRed));
	    	   friday.setTextSize(fonttoday);
	    	     friOpen1.setTextColor(getResources().getColor(R.color.darkRed));
			       friClose1.setTextColor(getResources().getColor(R.color.darkRed));
		    	     friOpen2.setTextColor(getResources().getColor(R.color.darkRed));
				       friClose2.setTextColor(getResources().getColor(R.color.darkRed));
					      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				       friday.setRotationX(-40);}
				      
	       
	       }
		       else {
	       friday.setText(fri.getDay());
			}

	       
	       //sat
	       TextView satday = (TextView) findViewById(R.id.textViewSat);
	       TextView satOpen1 = (TextView) findViewById(R.id.textViewSaOpen1);
	       TextView satClose1 = (TextView) findViewById(R.id.textViewSaClose1);
	       
	       satday.setTypeface(faceOstrichblack);
	       satday.setTextSize(fontdays);
	       satOpen1.setText(sat.getOpen1());
	       satClose1.setText(sat.getClose1());  
	       satOpen1.setTypeface(faceamericantypewriter);
	       satOpen1.setTextSize(fonttimes);
	       satClose1.setTypeface(faceamericantypewriter);
	       satClose1.setTextSize(fonttimes);
	       if (day==7){
	    	   satday.setText(getResources().getString(R.string.Today));
	    	   satday.setTypeface(faceOstrichblack, Typeface.BOLD);
	    	   satday.setTextSize(fonttoday);
			      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			    	  satday.setRotationX(-40);}
	    	   satday.setTextColor(getResources().getColor(R.color.darkRed));
	    	     satOpen1.setTextColor(getResources().getColor(R.color.darkRed));
				 satClose1.setTextColor(getResources().getColor(R.color.darkRed));}
		       else {
	       satday.setText(sat.getDay());

		       }

	//sun    
	       TextView sunday = (TextView) findViewById(R.id.textViewSu);
	       TextView sunOpen1 = (TextView) findViewById(R.id.textViewSuOpen1);
	       TextView sunClose1 = (TextView) findViewById(R.id.textViewSuClose1);
	       
	       sunday.setTypeface(faceOstrichblack);
	       sunday.setTextSize(fontdays);
	       sunOpen1.setText(sun.getOpen1());
	       sunClose1.setText(sun.getClose1());        
	       sunClose1.setText(sun.getClose1());  
	       sunOpen1.setTypeface(faceamericantypewriter);
	       sunOpen1.setTextSize(fonttimes);
	       sunClose1.setTypeface(faceamericantypewriter);
	       sunClose1.setTextSize(fonttimes);	
	       if (day==1){
	    	   sunday.setText(getResources().getString(R.string.Today));
	    	   sunday.setTypeface(faceOstrichblack, Typeface.BOLD);
		       sunday.setTextSize(fonttoday);
			      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		       sunday.setRotationX(-40);}
	    	   sunday.setTextColor(getResources().getColor(R.color.darkRed));
	    	   sunOpen1.setTextColor(getResources().getColor(R.color.darkRed));
			   sunClose1.setTextColor(getResources().getColor(R.color.darkRed));}
		       else {
	       sunday.setText(sun.getDay());
		       }
       
	       
		}
			
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
		    	menu.add(Menu.NONE, 1, 1, "KC&SON&SONS");
		    	menu.add(Menu.NONE, 2, 2, "Our Menu");
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
		    		 i = new Intent(OpenHoursActivity.this, MoreActivity.class); 	  
		 	        startActivity(i);
		    		break;
		    	case 2:
		    		 i = new Intent(OpenHoursActivity.this, PittaActivity.class); 	  
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
		      //  ab.setSubtitle(Subtitle);
		      }
		    }
	 	 	   
		 	   
	//	}  // end of  onCreate
		
	
    @Override
    protected void onStop() {
    	mDataBase.close();

        super.onStop();
	finish();
    }
   
	}
   
	
	
	
	/*	
	    public void onWindowFocusChanged(boolean hasFocus) {

	        super.onWindowFocusChanged(hasFocus);
	     
	        if(hasFocus) 
		        drawable.startTransition(500);
	    }
	*/
	/*
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.more, menu);
			return true;
		}
	*/	
	    

	
