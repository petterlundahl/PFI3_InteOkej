package nu.spiritusmundi.inteokej.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class LikeButton extends Button{
	
	private int myOwnPosition;
	private TextView numLikesTextView;
	
	public LikeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
	}

	
	public int getPosition(){
		
		return myOwnPosition;
		
	}
	
	public void setPosition(int position){
		
		myOwnPosition = position;
		
	}
	
	public void setTextView(TextView textView){
		numLikesTextView = textView;
	}
	
	public TextView getTextView()
	{
		return numLikesTextView;
	}

}
