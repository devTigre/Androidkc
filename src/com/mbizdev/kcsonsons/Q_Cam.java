 package com.mbizdev.kcsonsons;

 import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
 
public class Q_Cam extends Activity {

	private String Title = "KC & Son & Sons";
	private String Subtitle = "Q Cam";
	private int timer1 = 1;
	private int timer2 = 500;  //was 5000
	private int fonttoday=30;
	
    boolean noNetWWW = false;    boolean noNetGooglePlus = false;
    boolean noNetTwitter = false;    boolean noNetFacebook = false;
    boolean noNetMerch= false;    boolean noNetInsta = false; boolean noNetQCam;
	String webTab = "none";
	Toast customToast;

	private ProgressDialog Dialog; //taken out until cam back!


	private Timer autoUpdate;//taken out until cam back!

	   // declare internal using controls
	  
	//String urlQcam = "http://www.kcandco.ie/qcam.html";
//	String urlQcamr="http://192.168.1.60/image.jpg?resolution=160x120"; //new cam

	String urlQcamr="http://86.47.107.113:72/image.jpg?resolution=160x120"; //old cam
//	String urlQcamrs="http://86.47.107.113:72/image.jpg?resolution=80x60";
//	String urlQcamrb="http://86.47.107.113:72/image.jpg?resolution=640x480";

//    String URL1 = "http://86.47.107.113:72/image.jpg";

 	//String urlQcam="http://www.watchthiscam.com/199/199.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_q__cam);
        Dialog = new ProgressDialog(Q_Cam.this);  //taken out until cam back!



		actionBarSetup();
		
 		Typeface faceOstrichSansInlineRegular = Typeface.createFromAsset(getAssets(), "fonts/ostrich-black.ttf");
 		Typeface americantypewriter = Typeface.createFromAsset(getAssets(), "fonts/americantypewriter.ttf");

	       TextView openHours = (TextView) findViewById(R.id.textViewOpenhours);
	       openHours.setText(getResources().getString(R.string.todaysopenHours));
	       openHours.setTypeface(faceOstrichSansInlineRegular);
	       openHours.setTextColor(getResources().getColor(R.color.darkRed));		
	       openHours.setTextSize(35);

		
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 
		//day = day+2;
		// If current day is Sunday, day=1
		
		
		
		//mDb = new DataBase(this,(path));
		DataBaseAdapter mDb = new DataBaseAdapter(this);         

		mDb.createDatabase(); 
		mDb.open(); 

	    
	 //
		mDb.getOpenData();
	    
	    
	     ArrayList<Opening> op = mDb.SelectOpening();
	   
	   mDb.close();

	  
	   Opening mon = op.get(0); //Mon
	   Opening tue = op.get(1); //Tue
	   Opening wed = op.get(2);
	   Opening thu = op.get(3);
	   Opening fri = op.get(4);
	   Opening sat = op.get(5);
	   Opening sun = op.get(6);

	//mon
	 //  TextView m = (TextView) findViewById(R.id.textViewMonMore);
	   TextView todayO1 = (TextView) findViewById(R.id.textViewMOpen1);
	   TextView todayC1 = (TextView) findViewById(R.id.textViewMClose1);
	   TextView todayO2 = (TextView) findViewById(R.id.textViewMOpen2);
	   TextView todayC2 = (TextView) findViewById(R.id.textViewMClose2);
	   
	   TextView todayDash1 = (TextView) findViewById(R.id.textView1);
	   TextView todayDash2 = (TextView) findViewById(R.id.textView2);


   todayO1.setTypeface(americantypewriter);
   todayO1.setTextSize(fonttoday);
   todayO1.setTextColor(getResources().getColor(R.color.darkRed));

   todayC1.setTypeface(americantypewriter);
   todayC1.setTextSize(fonttoday);
   todayC1.setTextColor(getResources().getColor(R.color.darkRed));
  
   todayO2.setTypeface(americantypewriter);
   todayO2.setTextSize(fonttoday);
   todayO2.setTextColor(getResources().getColor(R.color.darkRed));

   todayC2.setTypeface(americantypewriter);
   todayC2.setTextSize(fonttoday);
   todayC2.setTextColor(getResources().getColor(R.color.darkRed));   
   
   todayDash1.setTypeface(americantypewriter);
   todayDash1.setTextSize(fonttoday);
   todayDash1.setTextColor(getResources().getColor(R.color.darkRed));  
   
   todayDash2.setTypeface(americantypewriter);
   todayDash2.setTextSize(fonttoday);
   todayDash2.setTextColor(getResources().getColor(R.color.darkRed));   
   
	   if (day==2){   //mon
	      todayO1.setText(mon.getOpen1());
	      todayC1.setText(mon.getClose1());
	   }
	   else if (day==3){
		      todayO1.setText(tue.getOpen1());
		      todayC1.setText(tue.getClose1());
	   }
	   else if (day==4){
		      todayO1.setText(wed.getOpen1());
		      todayC1.setText(wed.getClose1());
	   }
	   else if (day==5){
		      todayO1.setText(thu.getOpen1());
		      todayC1.setText(thu.getClose1());
		      todayO2.setText(thu.getOpen2());
		      todayC2.setText(thu.getClose2());
		      todayDash2.setText("-");
	   }
	   else if (day==6){
		      todayO1.setText(fri.getOpen1());
		      todayC1.setText(fri.getClose1());
		      todayO2.setText(thu.getOpen2());
		      todayC2.setText(thu.getClose2());
		      todayDash2.setText("-");

	   }
	   else if (day==7){
		      todayO1.setText(sat.getOpen1());
		      todayC1.setText(sat.getClose1());
	   }
	   else if (day==1){
		      todayO1.setText(sun.getOpen1());
		      todayC1.setText(sun.getClose1());
	   }		
		
	   Boolean isNet =	isNetworkAvailable();
       if (isNet) {	
    	 ///  webTab="QCam"; //taken out until cam back!
			webTab="NoCamImage";


       showToast(webTab);
    //  Dialog.setMessage("Please wait..");//taken out until cam back!
    //   Dialog.show();//taken out until cam back!
       }
	 
 	//timerSetup();  // this trigger cam attempt, taken out until cam back!
 	
	} //end of onCreate          !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;
		

		public DownloadImageTask(ImageView bmImage) {
		    this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
		    String urldisplay = urls[0];
		    Bitmap mIcon11 = null;
	
	        
		    try {
	
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		        //in.close();
 		    } catch (Exception e) {
		        Log.e("Error", e.getMessage());
		        e.printStackTrace();

		    }
		    return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			if (result==null){  
				webTab="NoCamImage";
		       showToast(webTab);
		   }
			else {   
				bmImage.setImageBitmap(result);	   
			//	Log.v("b","after bitmap");
		         Dialog.dismiss();
			//		Log.v("debug","after dialog");
					customToast.cancel();
				}


		}
	}
	
 
    //refresh timer//////////////-----------------
    public void timerSetup(){
     autoUpdate = new Timer();
     autoUpdate.schedule(new TimerTask() {

    //  @Override
      public void run() {
       runOnUiThread(new Runnable() {
        @Override
     public void run() {
         //Actions goes here
         //  browser.loadUrl(urlQcam);
        	new DownloadImageTask((ImageView) findViewById(R.id.imageViewQ))
            .execute(urlQcamr);
        	 
        }
       });
      }
     }, timer1, timer2);//refresh rate time interval (ms)
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
	    	menu.add(Menu.NONE, 1, 1, "Opening Hours");
	    	menu.add(Menu.NONE, 2, 2, "Our Menu");
	    	menu.add(Menu.NONE, 3, 3, "Home");
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
	    		 i = new Intent(Q_Cam.this, OpenHoursActivity.class); 	  
	 	        startActivity(i);
	    		break;
	    	case 2:
	    		 i = new Intent(Q_Cam.this, PittaActivity.class); 	  
	  	        startActivity(i);    		
	  	        break;
	    	case 3:
	   		 	i = new Intent(Q_Cam.this, MoreActivity  .class);
		        startActivity(i);      		
		        break;
	    		
	    	}
	    	return false;
	    }

	    
	    
		
		public  void showToast(String webTab) {
			
		    int toastduration=9600;

	        // create a LinearLayout and Views 
	       LinearLayout  toastlayout=new LinearLayout(this); 
	       toastlayout.setOrientation(LinearLayout.HORIZONTAL);
			final  TextView  tv=new TextView(this);   // for toast
		    final  ImageView   img=new ImageView(this);
	       tv.setTextColor(Color.YELLOW);
	       tv.setTextSize(19);   
	       tv.setGravity(Gravity.CENTER);

    if (noNetQCam && webTab.contains("QCam")){
		img.setImageResource(R.drawable.kc_pic_h68);
		tv.setText("Connect\n to the web\n to visit\n Q Cam"); 
	}
	else if (noNetQCam==false && webTab.contains("QCam")){
		img.setImageResource(R.drawable.kc_pic_h68);
		tv.setText("loading"); 
	}										      
	else if (noNetQCam==false && webTab.contains("NoCamImage")){
		img.setImageResource(R.drawable.shutdownrte);
		toastduration = 9000;
		tv.setText("Sorry - Q cam is off-line"); 
	}

		    // add both the Views TextView and ImageView in layout
		       toastlayout.addView(img);
		       toastlayout.addView(tv);
	 
		        customToast=new Toast(this); //context is object of Context write "this" if you are an Activity
		      // Set The layout as Toast View
		       customToast.setView(toastlayout);
		      
		         // Position you toast here toast position is 50 dp from bottom you can give any integral value    
		        customToast.setGravity(Gravity.CENTER, 0, 100);	        
		        customToast.setDuration(Toast.LENGTH_LONG);
		         customToast.show();
		         noNetWWW = false;  noNetQCam=false;
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
	    	//autoUpdate.cancel();  //taken out until cam back!

	        super.onStop();
	        finish();
	    }
	


}
