package com.mbizdev.kcsonsons;

 import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

public class MerchActivity extends Activity {

	private String Title = "KC & Son & Sons";
	private String Subtitle = "Merchandise";

	WebView browser;

	//String urlMerch = "http://www.kcandco.ie/store.php?catalog/all/-/date/1";
	String urlMerch = "http://www.kcandco.ie/rapidcartpro/store.php?catalog/all/-/date/1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_merch);
		
		actionBarSetup();

	     // setup webview
        WebView webview = (WebView) findViewById(R.id.webViewMerch);
        { browser=(WebView) findViewById(R.id.webViewMerch);
        browser.loadUrl(urlMerch);
 
        browser.getSettings().setJavaScriptEnabled(true);
        webview.setInitialScale(30);
        webview.getSettings( ).setSupportZoom( true );
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);  
        webview.scrollBy(00,500);
			webview.getSettings().setBuiltInZoomControls(true);
			webview.getSettings().setDisplayZoomControls(false);

        }
		
		
		
		
		
	} //end of onCreate

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
	   /* 
	    public void populateListContextMenu(Menu menu)
	    {
	    	menu.add(Menu.NONE, 11, 1, "Profile");
	    	menu.add(Menu.NONE, 12, 2, "KCs Home");
	    	
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
	    		 i = new Intent(MerchActivity.this, OpenHoursActivity.class); 	  
	 	        startActivity(i);
	    		break;
	    	case 2:
	    		 i = new Intent(MerchActivity.this, PittaActivity.class); 	  
	  	        startActivity(i);    		
	  	        break;
	    	case 3:
	   		 	i = new Intent(MerchActivity.this, MoreActivity.class); 	  
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
	        super.onStop();
  finish();
	    }
	


}
