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

        spec = tabHost.newTabSpec("allafragor").setIndicator("Frågor",
                          res.getDrawable(R.drawable.allafragor_tab_icon))
                      .setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, PostQuestion.class);
        spec = tabHost.newTabSpec("stallfraga").setIndicator("Ställ Fråga",
                          res.getDrawable(R.drawable.stallfraga_tab_icon))
                      .setContent(intent);
        tabHost.addTab(spec);

        
        intent = new Intent().setClass(this, BrowseMyQuestions.class);
        spec = tabHost.newTabSpec("minafragor").setIndicator("Arkiv",
                          res.getDrawable(R.drawable.minafragor_tab_icon)) 
                      .setContent(intent);
        tabHost.addTab(spec);
      
        intent = new Intent().setClass(this, ViewProfile.class);
        spec = tabHost.newTabSpec("minprofil").setIndicator("Profil",
                          res.getDrawable(R.drawable.minprofil_tab_icon))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.allafragor_tab);
        tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.stallfraga_tab);
        tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.minafragor_tab);
        tabHost.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.minprofil_tab);
        
        
        for(int i = 0; i < 4; i ++){
        	final TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title); 
            tv.setTextColor(Color.BLACK);
        }
        
        tabHost.setCurrentTab(0);
        
    }
    
}