package com.mbizdev.kcsonsons;


 import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

//import android.util.Log;

public class MoreActivity extends Activity {

    private static final String KC_PREFS_NAME = null;

	
	private String Title = "KC & Son & Sons";
	private String Subtitle = "navigation";
	
    private Handler mHandler = new Handler();


	String webTab = "none";
  private  boolean noNetWWW = false;    private boolean noNetGooglePlus = false;
  private boolean noNetTwitter = false;   private boolean noNetFacebook = false;
  private  boolean noNetMerch= false;    private boolean noNetInsta = false; private boolean noNetQCam;
    private  int toastduration=1000;
   // private AnimationDrawable animation;
   private ImageButton ImagebMenu;
   private ImageButton ImagebGPlus; 
   private ImageButton ImagebLocation; 
   private ImageButton ImagebFacebook;
  private ImageButton ImagebMerch;
  private ImageButton ImagebInsta;
  private ImageButton ImagebAbout; 
  private ImageButton ImagebOpening; 
  private ImageButton ImagebTwitter; 
  private ImageButton ImagebKCs ; 
  private View lineFacebook;
  private View lineGoogleplus;
  private View lineBottom;
  private View lineOpen;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
				
		actionBarSetup();
	      
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
		  ImageButton ImagebThink = (ImageButton) findViewById(R.id.imageButtonThink);
		  ImagebThink.setPadding(0, 5, 0,10);
		}
		
	    SharedPreferences.Editor editor = getSharedPreferences(KC_PREFS_NAME, MODE_PRIVATE).edit();
	     editor.putInt("SP_KEY_DB_VER", 10);   //was 7
	      editor.commit();  	
		
		ImagebMenu = (ImageButton) findViewById(R.id.imageButtonMenu);
	    ImagebGPlus = (ImageButton) findViewById(R.id.imageButtonGooglePlus);
	     ImagebLocation = (ImageButton) findViewById(R.id.imageButtonLocation);
 	     ImagebFacebook = (ImageButton) findViewById(R.id.imageButtonFacebook);
		 ImagebMerch = (ImageButton) findViewById(R.id.imageButtonMerch);
		 ImagebInsta = (ImageButton) findViewById(R.id.imageButtonInstagram);
		ImagebAbout = (ImageButton) findViewById(R.id.imageButtonAbout);
	    ImagebOpening = (ImageButton) findViewById(R.id.imageButtonOpen);
	    ImagebTwitter = (ImageButton) findViewById(R.id.imageButtonTwitter);
	    ImagebKCs = (ImageButton) findViewById(R.id.imageButtonKCs);
	    lineFacebook = (View) findViewById(R.id.lineFacebook);
	    lineGoogleplus = (View) findViewById(R.id.lineGoogleplus);
	    lineBottom = (View) findViewById(R.id.lineBottom);
	    lineOpen = (View) findViewById(R.id.lineOpen);

	    lineFacebook.setVisibility(View.INVISIBLE);
	    lineGoogleplus.setVisibility(View.INVISIBLE);
	    lineBottom.setVisibility(View.INVISIBLE);
	    lineOpen.setVisibility(View.INVISIBLE);



	    ImagebMerch.setVisibility(View.INVISIBLE);
		    ImagebMerch.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		    webTab="Merch";
		    Boolean isNet =	isNetworkAvailable();
	        if (isNet) {	
  		        noNetMerch = false;
	        	showToast(webTab);
		        webTab="none";
 		        Intent i = new Intent(MoreActivity.this, MerchActivity.class); 	  
		        startActivity(i);
	        }
	        else {
  		        noNetMerch = true;
		        showToast(webTab);
		        webTab="none";
	        	}
	        }
	     });
		    ImagebInsta.setVisibility(View.INVISIBLE);
		    ImagebInsta.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		    Boolean isNet =	isNetworkAvailable();
		    webTab="Insta";
	        if (isNet) {
	        	noNetInsta = false;
	        	showToast(webTab);
		        Intent i = new Intent(MoreActivity.this, InstaActivity.class); 	  
		        startActivity(i);
	        }
	        else {
  		        noNetInsta = true;
		        showToast(webTab);
	        	}
	        }
	     });
	    
		    ImagebGPlus.setVisibility(View.INVISIBLE);
		    ImagebGPlus.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {		        
		        Boolean isNet =	isNetworkAvailable();
		        webTab="GooglePlus";
		        if (isNet) {				    	
//		        	Log.v("isNetworkAvailable();", "true");
		        	showToast(webTab);
			        Intent i = new Intent(MoreActivity.this, GPlusActivity.class); 	  
			        startActivity(i);
		        }
		        else {
//			        Log.v("isNetworkAvailable();", "false");
 					//Toast.makeText(getBaseContext(), "no connect", Toast.LENGTH_LONG).show();
			        noNetGooglePlus = true;
			        showToast(webTab);
		        }
		        }
		     });
		    
		    
		    
		    
		    ImagebLocation.setVisibility(View.INVISIBLE);
	    ImagebLocation.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
 	        Intent i = new Intent(MoreActivity.this, CameraMapActivity.class); 	  

	        startActivity(i);
	        }
	     });
	    
	    ImagebAbout.setVisibility(View.INVISIBLE);
	    ImagebAbout.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
	        Intent i = new Intent(MoreActivity.this, AboutActivity.class); 	  
	        startActivity(i);
	        }
	     });
	
	    
	    ImagebMenu.setVisibility(View.INVISIBLE);
	    ImagebMenu.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
	        Intent i = new Intent(MoreActivity.this, PittaActivity.class); 	  
	        startActivity(i);
	        }
	     });

 
	    ImagebOpening.setVisibility(View.INVISIBLE);
	    ImagebOpening.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
	        Intent i = new Intent(MoreActivity.this, OpenHoursActivity.class); 	  
	        startActivity(i);
	        }
	     });

	    
	    
	    ImagebTwitter.setVisibility(View.INVISIBLE);
 	     ImagebTwitter.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
		    Boolean isNet =	isNetworkAvailable();
		    webTab="Twitter";
	        if (isNet) {
	        	showToast(webTab);
	        Intent i = new Intent(MoreActivity.this, TwitterActivity.class); 	  
		        startActivity(i);
	        }
	        else {
  		        noNetTwitter = true;
		        showToast(webTab);
	        	}
	        }
	     });
 	    
 	     ImagebFacebook.setVisibility(View.INVISIBLE);

	     ImagebFacebook.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View arg0) {
		    Boolean isNet =	isNetworkAvailable();
		    webTab="Facebook";
	        if (isNet) {
	        	showToast(webTab);
		        Intent i = new Intent(MoreActivity.this, FacebookActivity.class); 	  
		        startActivity(i);
	        }
	        else {
  		        noNetFacebook = true;
		        showToast(webTab);
 	        	}
	        }
	     });		
	     

	     ImagebKCs.setVisibility(View.INVISIBLE);
	 	   ImagebKCs.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		        	
		        Boolean isNet =	isNetworkAvailable();
		        if (isNet) {				    	
//		        	Log.v("isNetworkAvailable();", "true");
			        Intent i = new Intent(MoreActivity.this, WWWActivity.class); 	  
			        startActivity(i);
		        }
		        else {
//			        Log.v("isNetworkAvailable();", "false");
 					//Toast.makeText(getBaseContext(), "no connect", Toast.LENGTH_LONG).show();
			        noNetWWW = true;
			        showToast(webTab);
		        	}
		        }
		     }); 
	     

   
	 	   
	 	   ImageButton button = (ImageButton) findViewById(R.id.imageButtonThink);
	 	 final TransitionDrawable drawable = (TransitionDrawable) button.getDrawable();
 
	 	button.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
 		         
 			 	 drawable.startTransition(1300);
		        	drawable.reverseTransition(1300);
		        	
		       //     Boolean isNet =	isNetworkAvailable();
				//    webTab="QCam";
			      //  if (isNet) {				  
			        	int delay=80;
			        	mHandler.postDelayed(new Runnable() {
			                 public void run() {
			                	 showlineGoogleplus();
			                 }
			             }, delay);
			        	
			        	 mHandler.postDelayed(new Runnable() {
			                 public void run() {
			                     showImagebGPlus();
 			                 }
			             }, delay * 2);
			        	 
			        	 mHandler.postDelayed(new Runnable() {
			                 public void run() {
			                	 showImagebMerch();
 			                 }
			             }, delay * 3);
			        	 mHandler.postDelayed(new Runnable() {
			                 public void run() {
			                	 showImagebInsta();
 			                 }
			             }, delay * 4);
			        	 mHandler.postDelayed(new Runnable() {
			                 public void run() {
			                	 showlineFacebook();
 			                 }
			             }, delay * 5);   
			        	 mHandler.postDelayed(new Runnable() {
			                 public void run() {
			                	 showImagebFacebook();
 			                 }
			             }, delay * 6);
			         	 mHandler.postDelayed(new Runnable() {public void run() {showlineBottom(); }}, delay * 7);		
			         	 mHandler.postDelayed(new Runnable() {public void run() {showImagebAbout(); }}, delay * 8);
			         	 mHandler.postDelayed(new Runnable() {public void run() {showImagebLocation(); }}, delay * 9);		
			         	 mHandler.postDelayed(new Runnable() {public void run() {showImagebMenu(); }}, delay * 10);		

			         	 mHandler.postDelayed(new Runnable() {public void run() {showlineOpen(); } }, delay * 11); 	 
			         	 mHandler.postDelayed(new Runnable() {public void run() { showImagebOpening(); } }, delay * 12);		        	
		
			         	 mHandler.postDelayed(new Runnable() {public void run() {showImagebKCs(); }}, delay * 13);		
			         	 mHandler.postDelayed(new Runnable() {public void run() {showImagebTwitter(); }}, delay * 14);

			         	 mHandler.postDelayed(new Runnable() {public void run() {lineOpen.setVisibility(View.INVISIBLE); }}, delay * 15);
			         	 mHandler.postDelayed(new Runnable() {public void run() {lineOpen.setVisibility(View.VISIBLE); }}, delay * 17);
			         	 mHandler.postDelayed(new Runnable() {public void run() {ImagebMenu.setVisibility(View.INVISIBLE); }}, delay * 27);
			         	 mHandler.postDelayed(new Runnable() {public void run() {showImagebMenu(); }}, delay * 29);


//			        	Log.v("isNetworkAvailable();", "true");
				//	     Intent i = new Intent(MoreActivity.this, Q_Cam.class); 	
				//        startActivity(i);
			           // showToast(webTab); // now in Q__Cam.java
			        }		        
			     //   else {
//				        Log.v("isNetworkAvailable();", "false");
	 					//Toast.makeText(getBaseContext(), "no connect", Toast.LENGTH_LONG).show();
				   //     noNetQCam = true;
				   //     showToast(webTab);
			       // 	}
		       // }
		     }); 
	 	

 	 	   
	 	   
	}  // end of  onCreate

	private void showImagebGPlus() {
        //Toast.makeText(this, "Delayed Toast!", Toast.LENGTH_SHORT).show();
		ImagebGPlus.setVisibility(View.VISIBLE);
     }
	
	private void showlineGoogleplus() {
  	 lineGoogleplus.setVisibility(View.VISIBLE);
    }
	private void showImagebMerch() {
		ImagebMerch.setVisibility(View.VISIBLE);
	    }
	private void showImagebInsta() {
		ImagebInsta.setVisibility(View.VISIBLE);
	    }
	private void showlineFacebook() {
		lineFacebook.setVisibility(View.VISIBLE);
	    }
	private void showImagebFacebook() {
		ImagebFacebook.setVisibility(View.VISIBLE);
	    }
	private void showlineOpen() {
		lineOpen.setVisibility(View.VISIBLE);
	    }
	private void showImagebOpening() {
		ImagebOpening.setVisibility(View.VISIBLE);
	    }
	private void showImagebMenu() {
		ImagebMenu.setVisibility(View.VISIBLE);
	    }
	private void showImagebLocation() {
		ImagebLocation.setVisibility(View.VISIBLE);
	    }
	private void showlineBottom() {
		lineBottom.setVisibility(View.VISIBLE);
	    }
	private void showImagebAbout() {ImagebAbout.setVisibility(View.VISIBLE);}
	private void showImagebKCs() {ImagebKCs.setVisibility(View.VISIBLE);}
	private void showImagebTwitter() {ImagebTwitter.setVisibility(View.VISIBLE);}


	
	public  void showToast(String webTab) {
        // create a LinearLayout and Views 
       LinearLayout  layout=new LinearLayout(this); 
       layout.setOrientation(LinearLayout.VERTICAL);
		final  TextView  tv=new TextView(this);   // for toast
	    final  ImageView   img=new ImageView(this);
       tv.setTextColor(Color.RED);
       tv.setTextSize(17);   
       tv.setGravity(Gravity.CENTER);
if (noNetWWW){
           img.setImageResource(R.drawable.www_icon);
	       tv.setText("Connect\n to the web\n to visit\n www.kcandco.ie");   }

else if (noNetGooglePlus && webTab.contains("GooglePlus")){
			img.setImageResource(R.drawable.googleplus_icon);
			tv.setText("Connect\n to the web\n to visit\n google+"); 
}
else if (noNetGooglePlus==false && webTab.contains("GooglePlus")){
	img.setImageResource(R.drawable.googleplus_icon);
	tv.setText("loading"); 
}
else if (noNetFacebook && webTab.contains("Facebook")){
	img.setImageResource(R.drawable.fb_icon);
	tv.setText("Connect\n to the web\n to visit\n facebook"); 
}
else if (noNetFacebook==false && webTab.contains("Facebook")){
	img.setImageResource(R.drawable.fb_icon);
	tv.setText("loading");
}
else if (noNetInsta && webTab.contains("Insta")){
	img.setImageResource(R.drawable.instagram_icon);
	tv.setText("Connect\n to the web\n to visit\n instagram"); 
}
else if (noNetInsta==false && webTab.contains("Insta")){
	img.setImageResource(R.drawable.instagram_icon);
	tv.setText("loading"); 
}
else if (noNetMerch && webTab.contains("Merch")){
	img.setImageResource(R.drawable.merch_icon);
	tv.setText("Connect\n to the web\n to see our\n merch"); 
}
else if (noNetMerch==false && webTab.contains("Merch")){
	img.setImageResource(R.drawable.merch_icon);
	tv.setText("loading"); 
}
else if (noNetTwitter && webTab.contains("Twitter")){
	img.setImageResource(R.drawable.twitter_icon);
	tv.setText("Connect\n to the web\n to visit\n twitter"); 
}
else if (noNetTwitter==false && webTab.contains("Twitter")){
	img.setImageResource(R.drawable.twitter_icon);
	tv.setText("loading"); 
}
/*
else if (noNetQCam && webTab.contains("QCam")){
	img.setImageResource(R.drawable.kc_pic_h68);
	tv.setText("Connect\n to the web\n to visit\n Q Cam"); 
}*/
else if (noNetQCam==false && webTab.contains("QCam")){
	img.setImageResource(R.drawable.kc_pic_h68);
	tv.setText("loading"); 
}

	    // add both the Views TextView and ImageView in layout
	       layout.addView(img);
	       layout.addView(tv);
 
	       Toast customToast=new Toast(this); //context is object of Context write "this" if you are an Activity
	      // Set The layout as Toast View
	       customToast.setView(layout);
	             
	         // Position you toast here toast position is 50 dp from bottom you can give any integral value    
	        customToast.setGravity(Gravity.CENTER, 0, 250);
	   
	        if(webTab.contains("QCam")){
	        	toastduration=10000;}        
	        	else {toastduration = 3000; 	        	
	        	}
	        
	        customToast.setDuration(Toast.LENGTH_LONG);
	         customToast.show();
	         noNetWWW = false; noNetGooglePlus=false;noNetFacebook=false;noNetInsta=false;noNetMerch=false;noNetTwitter=false; noNetQCam=false;
	         webTab="none";
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
    	menu.add(Menu.NONE, 1, 1, "Our Menu");
    	menu.add(Menu.NONE, 2, 2, "Opening hours");
    	menu.add(Menu.NONE, 3, 3, "Find Us");

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
    		 i = new Intent(MoreActivity.this, PittaActivity.class); 	  
 	        startActivity(i);
    		break;
    	case 2:
    		 i = new Intent(MoreActivity.this, OpenHoursActivity.class); 	  
  	        startActivity(i);    		
  	        break;
    	case 3:
   		 i = new Intent(MoreActivity.this, CameraMapActivity.class);
 	        startActivity(i);    		
 	        break;    
    	}
    	return false;
	
    }
    
    public static boolean deleteDir(File dir) {
	    if (dir != null && dir.isDirectory()) {
	        String[] children = dir.list();
	        for (int i = 0; i < children.length; i++) {
	            boolean success = deleteDir(new File(dir, children[i]));
	            if (!success) {
	                return false;
	            }
	        }
	    }

	    return dir.delete();
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
}