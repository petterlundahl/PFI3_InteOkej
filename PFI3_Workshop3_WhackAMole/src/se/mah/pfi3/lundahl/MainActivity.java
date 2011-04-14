package se.mah.pfi3.lundahl;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends Activity implements Callback {
    
	private GameThread gameThread;
	private Handler handler;
	private GameView gameView;
	private TextView timeText;
	
	private boolean running;
	private float secondsLeft = 30 * (1000 / GameThread.DELAY);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.main);
        
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        
        newGame();
    }

	@Override
	public boolean handleMessage(Message arg0) {
		gameView.updateView();
		
		if(running){
			secondsLeft -= (1000 / GameThread.DELAY);
			//timeText.se
		}
		
		return false;
	}
	
	public void newGame()
	{
		secondsLeft = 30 * (1000 / GameThread.DELAY);
		
		handler = new Handler(this);
        gameThread = new GameThread(handler);
        gameThread.start();
        
        gameView = (GameView) findViewById(R.id.view);
        timeText = (TextView) findViewById(R.id.timeText);
	}
	
}