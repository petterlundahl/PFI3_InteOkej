package nu.spiritusmundi.inteokej.android;

import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.drawable;
import nu.spiritusmundi.inteokej.android.R.layout;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.*;

public class TabHandler extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        

        Resources res = getResources(); 
        TabHost tabHost = getTabHost();  
        TabHost.TabSpec spec;  
        Intent intent;  
    

        intent = new Intent().setClass(this, BrowseQuestions.class);

        spec = tabHost.newTabSpec("allafragor").setIndicator("Alla Frågor",
                          res.getDrawable(R.drawable.allafragor_tab))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, PostQuestion.class);
        spec = tabHost.newTabSpec("stallfraga").setIndicator("Ställ Fråga",
                          res.getDrawable(R.drawable.stallfraga_tab))
                      .setContent(intent);
        tabHost.addTab(spec);

        
        intent = new Intent().setClass(this, BrowseMyQuestions.class);
        spec = tabHost.newTabSpec("minafragor").setIndicator("Mina Frågor",
                          res.getDrawable(R.drawable.minafragor_tab)) 
                      .setContent(intent);
        tabHost.addTab(spec);
      
        intent = new Intent().setClass(this, ViewProfile.class);
        spec = tabHost.newTabSpec("minprofil").setIndicator("Min Profil",
                          res.getDrawable(R.drawable.minprofil_tab))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#ffcccc"));
        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#ccccff"));
        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#ccff99"));
        tabHost.getTabWidget().getChildAt(3).setBackgroundColor(Color.parseColor("#ffff99"));
        
        tabHost.setCurrentTab(0);   
        
    }
    
}