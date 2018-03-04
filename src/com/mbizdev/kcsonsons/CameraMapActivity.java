/*
* Copyright (C) 2012 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.mbizdev.kcsonsons;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


/**
 * This shows how to change the camera position for the map.
 */
public class CameraMapActivity extends FragmentActivity
        implements
        OnMapReadyCallback,
        OnMarkerClickListener,
        OnInfoWindowClickListener {


    private String Title = "KC & Son & Sons";
    private String Subtitle = "Find us";

    private String openToday = " ";
    private String phone = "021 4361418";
    static double homelat = 51.87745841107571;
    static double homelng = -8.436315134167671;


    private static final LatLng KC = new LatLng(homelat, homelng);


    static final CameraPosition HOME =
            new CameraPosition.Builder().target(new LatLng(homelat, homelng))
                    .zoom(16.3f)
                    .bearing(270) ///was 0
                    .tilt(70)
                    .build();

    private GoogleMap mMap;
    private final Random mRandom = new Random();

    // Obtain the MapFragment and set the async listener to be notified when the map is ready.


    ///  onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_map);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        setUpMapIfNeeded();
        actionBarSetup();

        Typeface faceKiddish = Typeface.createFromAsset(getAssets(), "fonts/Kiddish.ttf");
        Button ButtonZoom = (Button) findViewById(R.id.buttonZoom);

        ButtonZoom.setTypeface(faceKiddish);
        ButtonZoom.setTextSize(30);

        setUpDB();
    } //end OnCreate


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // We will provide our own zoom controls.
        mMap.getUiSettings().setZoomControlsEnabled(false);

        // Show KCs
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(homelat, homelng), 12.0f));


        addMarkersToMap();
        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpDB(){
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK); 		// If current day is Sunday, day=1	
		//mDb = new DataBase(this,(path));
    	DataBaseAdapter mDb = new DataBaseAdapter(this);         
    	mDb.createDatabase(); 
    	mDb.open(); 
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
       
       if (day == 1) { openToday = "Open Today "+sun.getOpen1()+"-"+sun.getClose1(); }
       		else if (day==2) {openToday="Open Today "+mon.getOpen1()+"-"+mon.getClose1(); }
       		else if (day==3) {openToday="Open Today "+tue.getOpen1()+"-"+tue.getClose1(); }
			else if (day==4) {openToday="Open Today "+wed.getOpen1()+"-"+wed.getClose1(); }
			else if (day==5) {openToday="Open Today "+thu.getOpen1()+"-"+thu.getClose1()+"+"+thu.getOpen2()+"-"+thu.getClose2(); }
			else if (day==6) {openToday="Open Today "+fri.getOpen1()+"-"+fri.getClose1()+"+"+fri.getOpen2()+"-"+fri.getClose2(); }
			else if (day==7) {openToday="Open Today "+sat.getOpen1()+"-"+sat.getClose1(); }
			else {  openToday = "open today";}		
       }
      
       
       
    
    private void setUpMapIfNeeded() {
        if (mMap == null) {
         //
            //
            //mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
         //   SupportMapFragment.getMapAsync(this);

   //         MapFragment mapFragment = (MapFragment) getFragmentManager()
     //               .findFragmentById(R.id.map);

       //     mapFragment.getMapAsync(this);



            if (mMap != null) {
              //  setUpMap();
            }
        }
    }
    /**
    private void setUpMap() {
        // We will provide our own zoom controls.
        mMap.getUiSettings().setZoomControlsEnabled(false);

        // Show KCs
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(homelat, homelng), 5));
        
        addMarkersToMap();
        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
    }

    /**
     * When the map is not ready the CameraUpdateFactory cannot be used. This should be called on
     * all entry points that call methods on the Google Maps API.
     */
    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, R.string.map_not_ready, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

 
    /**
     * Called when the Animate To Sydney button is clicked.
     */
    public void onGoToSydney(View view) {
        if (!checkReady()) {
            return;
        }
 
        changeCamera(CameraUpdateFactory.newCameraPosition(HOME), new CancelableCallback() {
            @Override
            public void onFinish() {
           //     Toast.makeText(getBaseContext(), "now at KCs", Toast.LENGTH_SHORT)
           //             .show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getBaseContext(), "going to KC's canceled", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    /**
     * Called when the stop button is clicked.
     */
    public void onStopAnimation(View view) {
        if (!checkReady()) {
            return;
        }

        mMap.stopAnimation();
    }

  
/**    private void changeCamera(CameraUpdate update) {
        changeCamera(update, null);
    }
*/
    /**
     * Change the camera position by moving or animating the camera depending on the state of the
     * animate toggle button.
     */
    private void changeCamera(CameraUpdate update, CancelableCallback callback) {
     
        mMap.animateCamera(update, callback);
        }
    
    // Marker related listeners.
    //
    private Marker mHome;
    private Marker mFINGERPOST;
    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (marker.equals(mHome)) {
            // This causes the marker at Perth to bounce into position when it is clicked.
            final Handler handler = new Handler();            

            final long start = SystemClock.uptimeMillis();
            final long duration = 1500;             

            final Interpolator interpolator = new BounceInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = Math.max(1 - interpolator
                            .getInterpolation((float) elapsed / duration), 0);
                    marker.setAnchor(0.5f, 1.0f + 2 * t);

                    if (t > 0.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    }
                }
            });
 
            marker.setTitle("tap to see the Queue");

            
        } else if (marker.equals(mFINGERPOST)) {
            // This causes the marker at FINGERPOST to change color and alpha.
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(mRandom.nextFloat() * 360));
            marker.setAlpha(mRandom.nextFloat());
        }
        // We return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
    	
        // create a LinearLayout and Views 
       LinearLayout  layout=new LinearLayout(this);
       TextView  tv=new TextView(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        tv.setTextColor(Color.RED);
       tv.setTextSize(18);   
       tv.setText(phone);   
	      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
 	          tv.setAlpha((float) 0.8);
	          tv.setGravity(Gravity.BOTTOM);
	          tv.setPadding(5, 5, 5, 5);
	          
 	      }
	     
      ImageView   img=new ImageView(this);
       // give the drawble resource for the ImageView
       img.setImageResource(R.drawable.oldtelephoneicon_h60);
	      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
	    	  img.setAlpha((float) 0.4);
 	          img.setPadding(5, 5, 5, 5);
	      }        
 
     // add both the Views TextView and ImageView in layout
       layout.addView(img);
       layout.addView(tv);

        
       Toast customToast=new Toast(this); //context is object of Context write "this" if you are an Activity
      // Set The layout as Toast View
       customToast.setView(layout);
             
         // Position you toast here toast position is 50 dp from bottom you can give any integral value    
        customToast.setGravity(Gravity.BOTTOM, 0, 10);
        customToast.setDuration(Toast.LENGTH_LONG);
         customToast.show();
         
	     Intent i = new Intent(CameraMapActivity.this, Q_Cam.class); 	
	       startActivity(i);
    	
     }
    
    
    /** Demonstrates customizing the info window and/or its contents. */
    class CustomInfoWindowAdapter implements InfoWindowAdapter {
 
        // These a both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".
        private final View mWindow;
        private final View mContents;

        CustomInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
            mContents = getLayoutInflater().inflate(R.layout.custom_info_contents, null);
        }


        
        @Override
        public View getInfoContents(Marker marker) {
          //  if (mOptions.getCheckedRadioButtonId() != R.id.custom_info_contents) {
                // This means that the default info contents will be used.
           //     return null;
          //  }
            render(marker, mContents);
            return mContents;
        }      
        
        @Override
        public View getInfoWindow(Marker marker) {
           // if (mOptions.getCheckedRadioButtonId() != R.id.custom_info_window) {
                // This means that getInfoContents will be called.
             //   return null;
          //  }
            render(marker, mWindow);
            return mWindow;
        } 
    }   
    
    
    private void render(Marker marker, View view) {
        int badge;
        
         if (marker.equals(mHome)) {
            badge = R.drawable.kciconsq_h50;
        } else if (marker.equals(mFINGERPOST)) {
            badge = R.drawable.badge_sa;
        }
         else {
            // Passing 0 to setImageResource will clear the image view.
            badge = 0;
        }
        ((ImageView) view.findViewById(R.id.badge)).setImageResource(badge);
       
        String title = marker.getTitle();
        TextView titleUi = ((TextView) view.findViewById(R.id.title));
        if (title != null) {
            // Spannable string allows us to edit the formatting of the text.
            SpannableString titleText = new SpannableString(title);
            titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
            titleUi.setText(titleText);
        } else {
            titleUi.setText("");
        }

       String snippet = marker.getSnippet();
    
        TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
        
        if (snippet != null && snippet.length() > 10) {
            SpannableString snippetText = new SpannableString(snippet);
            snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 10, 0);
            snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 11, snippet.length(), 0);
            snippetUi.setText(snippetText);
        } else {
            snippetUi.setText("Open Today");
        }
    } // end render
    
    private void addMarkersToMap() {
        // Uses a custom icon with the info window popping out of the center of the icon.
 /**
        mFINGERPOST = mMap.addMarker(new MarkerOptions()
                .position(FINGERPOST)
                .title("FINGERPOST")
                .snippet("Signpost Douglas"));
 */       
        mHome = mMap.addMarker(new MarkerOptions()
        .position(KC)
        .title("East Village, Douglas")
        .snippet(openToday)
        .icon(BitmapDescriptorFactory.fromResource(R.drawable.kc_pic_h68))
        .flat(false)
        .infoWindowAnchor(0.5f, 0.5f));
        
    }
    
    
    // menu
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
	    	menu.add(Menu.NONE, 4, 4, "Privacy & Legal");
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
	    		 i = new Intent(CameraMapActivity.this, OpenHoursActivity.class); 	  
	 	        startActivity(i);
	    		break;
	    	case 2:
	    		 i = new Intent(CameraMapActivity.this, PittaActivity.class); 	  
	  	        startActivity(i);    		
	  	        break;
	    	case 3:
	   		 	i = new Intent(CameraMapActivity.this, MoreActivity.class); 	  
		        startActivity(i);      		
		        break;
	    	case 4:
	   		 	i = new Intent(CameraMapActivity.this, LegalInfoActivity.class); 	  
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
    
} /// end
