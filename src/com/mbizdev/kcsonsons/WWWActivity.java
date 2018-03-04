package com.mbizdev.kcsonsons;

 import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WWWActivity extends Activity {

//	public Context mContext; 

	private String Title = "KC & Son & Sons";
	private String Subtitle = "www.kcandco.ie";

	WebView browser;

	String url = "http://www.kcandco.ie/index.html";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_www);
		

		
		actionBarSetup();

	     // setup webview
        WebView webview = (WebView) findViewById(R.id.webViewWebKC);
        { browser=(WebView) findViewById(R.id.webViewWebKC);
        browser.loadUrl(url);
 
        browser.getSettings().setJavaScriptEnabled(true);
        webview.setInitialScale(30);
        webview.getSettings( ).setSupportZoom( true );
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
			webview.getSettings().setBuiltInZoomControls(true);
			webview.getSettings().setDisplayZoomControls(true);
        }
	
   
		
        // create a LinearLayout and Views 

       LinearLayout  layout=new LinearLayout(this);
   
       layout.setOrientation(1);
      
       TextView  tv=new TextView(this);
       tv.setTextColor(Color.RED);
       tv.setTextSize(13);   
       tv.setText("loading");   
       tv.setGravity(Gravity.CENTER);


	      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	          tv.setRotation(-20);
	          tv.setAlpha((float) 0.7);
	          tv.setGravity(Gravity.RIGHT);
	          tv.setPadding(100, 35, 0, 35);
	          tv.setText("  loading");   
	      }
	     
 

      ImageView   img=new ImageView(this);
       // give the drawble resource for the ImageView
       img.setImageResource(R.drawable.kc_pich124);
	      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	    	  img.setAlpha((float) 0.7);
	    	  img.setRotation(-20);
	          img.setPadding(0, 30, 0, 30);
	      }        
 

     // add both the Views TextView and ImageView in layout
       layout.addView(img);
       layout.addView(tv);

       Toast customToast=new Toast(this); //context is object of Context write "this" if you are an Activity
      // Set The layout as Toast View
       customToast.setView(layout);
             
        customToast.setGravity(Gravity.TOP, 100, 50);
        customToast.setDuration(2000);
         customToast.show();
	
	} // end of onCreate

	  public boolean onCreateOptionsMenu(Menu menu)
	    {
	    	populateOptionsMenu(menu);
			return super.onCreateOptionsMenu(menu);
	    }
	    
	    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
	    {
	    	super.onCreateContextMenu(menu, v, menuInfo);
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
	    		 i = new Intent(WWWActivity.this, OpenHoursActivity.class); 	  
	 	        startActivity(i);
	    		break;
	    	case 2:
	    		 i = new Intent(WWWActivity.this, PittaActivity.class); 	  
	  	        startActivity(i);    		
	  	        break;
	    	case 3:
	   		 	i = new Intent(WWWActivity.this, MoreActivity.class); 	  
		        startActivity(i);      		break;

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
	        finish();
	        super.onStop();
	    }

}
