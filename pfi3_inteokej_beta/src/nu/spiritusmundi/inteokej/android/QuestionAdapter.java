package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;



import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class QuestionAdapter extends ArrayAdapter<Question> {

	private ArrayList questions;
	private LayoutInflater inflater;
	private BrowseQuestions parentActivity;

	public QuestionAdapter(Context context, int textViewResourceId, ArrayList questions) {
		super(context, textViewResourceId, questions);
		this.questions = questions;
		this.parentActivity = (BrowseQuestions) context;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = parentActivity.getLayoutInflater();
		View row = inflater.inflate(R.layout.question_list_item, parent, false);
		TextView titleText = (TextView)row.findViewById(R.id.list_content);
		
		Question question = (Question) questions.get(position);
		
		titleText.setText(question.getTitle());
		
		titleText.setTypeface(Typeface.createFromAsset(parentActivity.getAssets(), "fonts/MyriadWebPro.ttf"), Typeface.BOLD);
		
		TextView numReplies = (TextView) row.findViewById(R.id.num_replies);
		numReplies.setText(Integer.toString(question.getAnswers().size()));
		
		TextView numSupporters = (TextView) row.findViewById(R.id.num_supports);
		numSupporters.setText(Integer.toString(question.getSupporters().size()));
		
		TextView numViews = (TextView) row.findViewById(R.id.num_views);
		numViews.setText(Integer.toString(question.getNumViews()));
		return row;
	}


	

}
