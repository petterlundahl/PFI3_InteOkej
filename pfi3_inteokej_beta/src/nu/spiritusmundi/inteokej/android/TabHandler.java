package nu.spiritusmundi.inteokej.android;

import java.util.Timer;
import java.util.TimerTask;



import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.drawable;
import nu.spiritusmundi.inteokej.android.R.layout;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.*;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.TabHost.OnTabChangeListener;

public class TabHandler extends TabActivity {
    
	private static TabHost tabHost;
	private static Timer myTimer;
	NotificationManager myNotificationManager;
	private static final int NOTIFICATION_ID = 1;
	
	private final int NOTIFICATION_DELAY = 10000;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        
        
        myTimer = new Timer();
        myTimer.schedule(new NotificationTask(), NOTIFICATION_DELAY);
        

        Resources res = getResources(); 
        tabHost = getTabHost();  
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
    
    public static void setTab(int tabIndex)
    {
    	tabHost.setCurrentTab(tabIndex);
    }
    
    
    private void generateNotification(){
		  
		  myNotificationManager =
		   (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		  
		  CharSequence NotificationTicket = "Nytt svar!";
		  CharSequence NotificationTitle = "InteOkej";
		  CharSequence NotificationContent = "Du har fått ett nytt svar på din fråga!";
		  long when = System.currentTimeMillis();
		  
		  Notification notification =
		   new Notification(R.drawable.loggan,
		     NotificationTicket, when);
		  notification.flags = Notification.FLAG_AUTO_CANCEL;
		    
		  Context context = getCurrentActivity();

		  Intent notificationIntent = new Intent(this,
				  TabHandler.class);
		  
		  notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		  
		  		  
		  PendingIntent contentIntent =
		   PendingIntent.getActivity(this, 0, notificationIntent, android.content.Intent.FLAG_ACTIVITY_NEW_TASK);

		  notification.setLatestEventInfo(context, NotificationTitle,
		    NotificationContent, contentIntent);
		  
		  myNotificationManager.notify(NOTIFICATION_ID, notification);

		}

    
    private class NotificationTask extends TimerTask {

    	@Override
    	public void run() {
    		FakeDatabase.answerQuestion(FakeDatabase.getMyQuestionsSorted().get(0), new Answer("Jag råkade ut för samma sak för nåt år sen. Fick mycket stöd från min lärare, kan inte du snacka med din lärare?", "jimmy92"));
    		generateNotification();
    		myTimer.purge();
    	}
    	
    } 
    
}