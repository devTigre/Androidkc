package com.mbizdev.kcsonsons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//package com.mbizdev.kcsonsons;

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

public class AllergenActivity extends Activity {

    private String Title = "KC & Son & Sons";
    private String Subtitle = "Allergen Advice";



    WebView browser;

    String urlAllergen="http://resc.promobile.ie/kcs/application/allergens.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergen);

        actionBarSetup();


        // setup webview
        WebView webview = (WebView) findViewById(R.id.webViewAllergen);
        { browser=(WebView) findViewById(R.id.webViewAllergen);
            browser.loadUrl(urlAllergen);

            browser.getSettings().setJavaScriptEnabled(true);
            webview.setInitialScale(70);
            webview.getSettings( ).setSupportZoom( true );
            webview.getSettings().setUseWideViewPort(true);
            webview.setHorizontalScrollBarEnabled(true);
            webview.getSettings().setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
            webview.getSettings().setBuiltInZoomControls(true);

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
                i = new Intent(AllergenActivity.this, OpenHoursActivity.class);
                startActivity(i);
                break;
            case 2:
                i = new Intent(AllergenActivity.this, PittaActivity.class);
                startActivity(i);
                break;
            case 3:
                i = new Intent(AllergenActivity.this, MoreActivity.class);
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
