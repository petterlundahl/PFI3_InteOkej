package se.mah.pfi3.lundahl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	
	private int y = 0;
	private Paint paint;
	private Bitmap bm;
	
	private Mole mole;
	
	public GameView(Context context) {
		super(context);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mole = new Mole();
		bm = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawRGB(0, 255, 0);
		canvas.drawBitmap(bm, 50, y, null);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(event.getX() > 50 && event.getX() < 50 + bm.getWidth() &&
		event.getY() > y && event.getY() < y + bm.getHeight()){
			y = 0;
		}
		
		return super.onTouchEvent(event);
	}
	
	public void updateView()
	{
		y++;
		invalidate();
	}


}
