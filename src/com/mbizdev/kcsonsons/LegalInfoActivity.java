package com.mbizdev.kcsonsons;


import java.io.File;

import com.google.android.gms.common.GooglePlayServicesUtil;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;


public class LegalInfoActivity extends Activity {
	
	private String Title = "KC & Son & Sons";
	private String Subtitle = "Privacy & Legal";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_legal_info);
		
		actionBarSetup(); 	     

	//	clearApplicationData();
		
        TextView legalInfoTextView = (TextView) findViewById(R.id.legal_info);
        String openSourceSoftwareLicenseInfo =
            GooglePlayServicesUtil.getOpenSourceSoftwareLicenseInfo(this);
        if (openSourceSoftwareLicenseInfo != null) {
            legalInfoTextView.setText(openSourceSoftwareLicenseInfo);
        } else {
            legalInfoTextView.setText(R.string.play_services_not_installed);
        }

   	}  // end onCreate


/// menu 
	
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
	    		 i = new Intent(LegalInfoActivity.this, OpenHoursActivity.class); 	  
	 	        startActivity(i);
	    		break;
	    	case 2:
	    		 i = new Intent(LegalInfoActivity.this, PittaActivity.class); 	  
	  	        startActivity(i);    		
	  	        break;
	    	case 3:
	   		 	i = new Intent(LegalInfoActivity.this, MoreActivity.class); 	  
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
	    
	    //////////
	
		

	    
	    
	    
	    
	    
	    
	    
	    
	    @Override
	    protected void onStop() {
	        super.onStop();
	    	// MyApplication.getInstance().clearApplicationData();
	        finish();
	    }
}
