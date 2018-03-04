package com.mbizdev.kcsonsons;


 import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


 

public class Home_Activity extends Activity {
 
	
  //  private static final String KC_PREFS_NAME = null;



	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
 /*       
      SharedPreferences.Editor editor = getSharedPreferences(KC_PREFS_NAME, MODE_PRIVATE).edit();
     editor.putInt("SP_KEY_DB_VER", 7);
      editor.commit();  
  */ 
    	
   	DataBaseAdapter mDataBase = new DataBaseAdapter(this);  
   	
   	
    if (DataBaseHelper.LATEST_DATABASE_VERSION == 6) {
    }
    	
    mDataBase.close();
    
    mDataBase.createDatabase();

        Button bMenu= (Button) findViewById(R.id.buttonMenu);
        bMenu.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View arg0) {
		        Intent i = new Intent(Home_Activity.this, PittaActivity.class); 	  
		        startActivity(i);
		        }
		     });
        
        
        Button bMore = (Button) findViewById(R.id.buttonMore);
        bMore.setOnClickListener(new View.OnClickListener() {
           public void onClick(View arg0) {     
		send2ActMore();
           }         
        });
    } // end of create

    
    
    
    public void send2ActMore (){
  
 		Intent i = new Intent(this, MoreActivity.class);	
 		startActivity(i); 	      
    }
    
    

    
   @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	populateOptionsMenu(menu);
		return super.onCreateOptionsMenu(menu);
    }
    
    
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
    	super.onCreateContextMenu(menu, v, menuInfo);
    	/*
    	Object list;
		if(v.equals(list))
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
 
    }
    
 /**   public void populateListContextMenu(Menu menu)
    {
    	menu.add(Menu.NONE, 11, 1, "Profile");
    	menu.add(Menu.NONE, 12, 2, "Exit");
    	
    }
*/
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	return applyMenuChoice(item);
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
    		 i = new Intent(Home_Activity.this, OpenHoursActivity.class); 	  
 	        startActivity(i);
    		break;
    	case 2:
    		 i = new Intent(Home_Activity.this, PittaActivity.class); 	  
  	        startActivity(i);    		break;
 

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
    
    
    
    
    @Override
    protected void onStop() {
    	
        super.onStop();
       // finish();
    }
    
    
    
    @Override
    protected void onDestroy() {
          super.onDestroy();
 
    }


   
    
} // end
