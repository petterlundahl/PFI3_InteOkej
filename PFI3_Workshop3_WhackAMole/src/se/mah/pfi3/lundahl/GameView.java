package se.mah.pfi3.lundahl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class GameView extends View {
	
	private int y = 0;
	private Bitmap moleAvatar;
	private Bitmap grass;
	private int score;
	private int screenWidth;
	private int screenHeight;
	
	private Mole[] moles;
	
	private Vibrator vibrator;
	
	public GameView(Context context) {
		super(context);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		moles = new Mole[2];
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		screenHeight = wm.getDefaultDisplay().getHeight();
		
		for(int i = 0; i < moles.length; i ++){
			moles[i] = new Mole(screenWidth, screenHeight, this);
		}
		
		
		moleAvatar = BitmapFactory.decodeResource(getResources(), R.drawable.mole);
		grass = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
		
		vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawBitmap(grass, 0, 0, null);
		
		for(int i = 0; i < moles.length; i ++){
			if(moles[i].getVisibility()){
				canvas.drawBitmap(moleAvatar, moles[i].getX(), moles[i].getY(), null);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		for(int i = 0; i < moles.length; i ++){
			if(moles[i].isHit(event.getX(), event.getY(), moleAvatar.getWidth(), moleAvatar.getHeight())){
				score ++;
				vibrator.vibrate(200);
			}
		}
		return super.onTouchEvent(event);
	}
	
	public void updateView()
	{
		for(int i = 0; i < moles.length; i ++){
			moles[i].update();
		}
	}
	
	public void invalidateView()
	{
		invalidate();
	}
	
	public void newGame()
	{
		score = 0;
	}
	
	public int getScore()
	{
		return score;
	}


}
