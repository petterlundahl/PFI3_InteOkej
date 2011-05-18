package nu.spiritusmundi.inteokej.android;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

public class FlagButton extends Button{
	
	private int myOwnPosition;
	private boolean flagged;

	public FlagButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

public int getPosition(){
		
		return myOwnPosition;
		
	}
	
	public void setPosition(int position){
		
		myOwnPosition = position;
		
	}
	
	
}
