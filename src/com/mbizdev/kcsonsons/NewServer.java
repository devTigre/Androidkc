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

				import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;
 
public class NewServer extends Activity {

	private String Title = "KC & Son & Sons";
	private String Subtitle = "New";
	private int timer1 = 0;
	private int timer2 = 10000;
	
 private boolean isNet =false;
	String webTab = "none";

	private ProgressDialog Dialog;
	   
	private Timer autoUpdate;
	   // declare internal using controls
	  
	//String urlQcam = "http://www.kcandco.ie/qcam.html";

//	String urlQcamr="http://86.47.107.113:72/image.jpg?resolution=160x120";
//	String urlQcamr="ftp://f298872:M4_X!p9HYfNXLr2p@ftp.d1341839-49137.blacknighthosting.com/webspace/httpdocs/kcs_stuff/special_pic.jpg";

	String urlserverPic="http://resc.promobile.ie/kcs/application/special_pic.gif";
	String urlserverPic1="http://resc.promobile.ie/kcs/application/special_pic1.gif";
	String urlserverPic2="http://resc.promobile.ie/kcs/application/special_pic2.png";
	String urlserverPic3="http://resc.promobile.ie/kcs/application/special_pic3.png";

	String urlJson="http://resc.promobile.ie/kcs/application/kcspecial.json";

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_server);
         Dialog = new ProgressDialog(NewServer.this);

		actionBarSetup();
		
	  isNet =	isNetworkAvailable();
       if (isNet) {	  
    	   showToast(webTab);
           Dialog.setMessage("Please wait..");
           Dialog.show();
    	   new HttpAsyncTask().execute(urlJson);   //get text from server

    	   timerSetup();    // gets pic from server
       }
       else {
    	   webTab="noNet";
    	   showToast(webTab);
    	   Intent i = new Intent(NewServer.this, PittaActivity.class); 	  
	 	        startActivity(i);
       }
 	
	} //end of onCreate   !!!!!!!!!!!!!!!!!!!

	
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
		    } catch (Exception e) {
		        Log.e("Error", e.getMessage());
		        e.printStackTrace();	        
		    }
		    return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			if (result==null){ 
			//    Log.v("Error","NoServerPic");
				webTab="NoServerPic";
		       showToast(webTab);
		   }
			else { 
			webTab="YesServerPic";
		//    Log.v("Error","ServerPic");

		  //     showToast(webTab);
		       bmImage.setImageBitmap(result); 
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
         //  browser.loadUrl(url);
        	new DownloadImageTask((ImageView) findViewById(R.id.imageViewServerPic))
            .execute(urlserverPic);
			new DownloadImageTask((ImageView) findViewById(R.id.imageViewServerPic1))
					.execute(urlserverPic1);
			new DownloadImageTask((ImageView) findViewById(R.id.imageViewServerPic2))
					.execute(urlserverPic2);
			new DownloadImageTask((ImageView) findViewById(R.id.imageViewServerPic3))
					.execute(urlserverPic3);
        	 
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
	    	menu.add(Menu.NONE, 3, 3, "KC&SON&SONS");
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
	    		 i = new Intent(NewServer.this, OpenHoursActivity.class); 	  
	 	        startActivity(i);
	    		break;
	    	case 2:
	    		 i = new Intent(NewServer.this, PittaActivity.class); 	  
	  	        startActivity(i);    		
	  	        break;
	    	case 3:
	   		 	i = new Intent(NewServer.this, MoreActivity.class); 	  
		        startActivity(i);      		
		        break;
	    		
	    	}
	    	return false;
	    }

	    
	    
		
		public  void showToast(String webTab) {
			
		    int toastduration=2200;

	        // create a LinearLayout and Views 
	       LinearLayout  toastlayout=new LinearLayout(this); 
	       toastlayout.setOrientation(LinearLayout.HORIZONTAL);
			final  TextView  tv=new TextView(this);   // for toast
		    final  ImageView   img=new ImageView(this);
	       tv.setTextColor(Color.RED);
	       tv.setTextSize(17);   
	       tv.setGravity(Gravity.CENTER);

    if (isNet == false){
		img.setImageResource(R.drawable.neww128);
		tv.setText("Connect to the web\n to see\n new .."); 
	}
	else if (isNet ==true && webTab.contains("YesServerPic")){
		img.setImageResource(R.drawable.neww128);
		toastduration = 1000;
		tv.setText("loading"); 
	}										      
	else if (isNet == true && webTab.contains("NoServerPic")){
		img.setImageResource(R.drawable.shutdownrte);
		toastduration = 5000;
		tv.setText("oops ..\n no pic..\n server is off-line"); 
	}

		    // add both the Views TextView and ImageView in layout
		       toastlayout.addView(img);
		       toastlayout.addView(tv);
	 
		       Toast customToast=new Toast(this); //context is object of Context write "this" if you are an Activity
		      // Set The layout as Toast View
		       customToast.setView(toastlayout);
		             
		         // Position you toast here toast position is 50 dp from bottom you can give any integral value    
		        customToast.setGravity(Gravity.CENTER, 0, 100);	        
		        customToast.setDuration(Toast.LENGTH_LONG);
		         customToast.show();
		         webTab="none";
		}
		

	    
	
		public static String GET(String url){
			InputStream inputStream = null;
			String result = "";
			try {
				
				// create HttpClient
				HttpClient httpclient = new DefaultHttpClient();
				
				// make GET request to the given URL
				HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
				
				// receive response as inputStream
				inputStream = httpResponse.getEntity().getContent();
				
				// convert inputstream to string
				if(inputStream != null)
					result = convertInputStreamToString(inputStream);
				else
					result = "Did not work!";
			
			} catch (Exception e) {
				Log.d("InputStream", e.getLocalizedMessage());
			}
			
			return result;
		}
	 

	    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
	        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
	        String line = "";
	        String result = "";
	        while((line = bufferedReader.readLine()) != null)
	            result += line;
	        
	        inputStream.close();
	        return result;
	        
	    }

	    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
	        @Override
	        protected String doInBackground(String... urls) {
	              
	            return GET(urls[0]);
	        }
	        // onPostExecute displays the results of the AsyncTask.
	        @Override
	        protected void onPostExecute(String result) {
	        	//Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
	            Dialog.dismiss();

	        	try {
					JSONObject json = new JSONObject(result);
					
					String strBlurb = ""; String strItem=""; String strDesc =""; String strPrice="";
	
					JSONArray serverNotes = json.getJSONArray("serverNotes");
				
					strBlurb = serverNotes.getJSONObject(0).getString("blurb");				
					strItem = serverNotes.getJSONObject(0).getString("item");
					strDesc = serverNotes.getJSONObject(0).getString("desc");
					strPrice =serverNotes.getJSONObject(0).getString("price");
					
					    TextView textViewBlurb = (TextView) findViewById(R.id.textViewBlurb);
						   TextView textViewItem = (TextView) findViewById(R.id.textViewItem);
						   TextView textViewDesc = (TextView) findViewById(R.id.textViewDesc);
						   TextView textViewPrice = (TextView) findViewById(R.id.textViewPrice);
						   
					textViewBlurb.setText(strBlurb);
					textViewItem.setText(strItem);
					textViewPrice.setText(strPrice);
					textViewDesc.setText(strDesc);
					
					Typeface faceAmaticBold = Typeface.createFromAsset(getAssets(), "fonts/Amatic-Bold.ttf");
					Typeface faceChantelli = Typeface.createFromAsset(getAssets(), "fonts/Chantelli_Antiqua.ttf");
					Typeface faceKiddish = Typeface.createFromAsset(getAssets(), "fonts/Kiddish.ttf");

			  	       textViewBlurb.setTypeface(faceChantelli);
			 	       textViewBlurb.setTextSize(2);

				   textViewItem.setTypeface(faceAmaticBold);  //this  is "WHATS NEW
				   textViewItem.setTextSize(25);

				   textViewDesc.setTypeface(faceKiddish);
				   textViewDesc.setTextSize(2);
				 //  textViewDesc.setTextColor(getResources().getColor(R.color.darkRed));
			  
				   textViewPrice.setTypeface(faceKiddish);
				   textViewPrice.setTextSize(2);
				   

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	    	autoUpdate.cancel();
	        super.onStop();
	        finish();
	    }
	


}
