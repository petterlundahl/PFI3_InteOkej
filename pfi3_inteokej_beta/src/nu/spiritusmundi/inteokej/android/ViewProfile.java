package nu.spiritusmundi.inteokej.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewProfile extends Activity {

	private TextView textViewUserName;
	private TextView textViewUserTitle;
	private TextView textViewScoreCounter;
	private TextView textViewScoreDescription;
	private TextView textViewUserQuestionCount;
	private TextView textViewUserAnswerCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_profile);
		
		textViewUserName = (TextView) findViewById(R.id.username_profileview);
		textViewUserTitle = (TextView) findViewById(R.id.usertitle_profileview);
		textViewScoreCounter = (TextView) findViewById(R.id.score_counter_profileview);
		textViewScoreDescription = (TextView) findViewById(R.id.score_description_profileview);		
		textViewUserQuestionCount = (TextView) findViewById(R.id.questionCounterTextView);
		textViewUserAnswerCount = (TextView) findViewById(R.id.answerCounterTextView);
		
		textViewUserName.setText(FakeDatabase.getCurrentUserName());
		textViewUserTitle.setText("Smygare");
		textViewScoreCounter.setText(Integer.toString(FakeDatabase.userScore));
		textViewUserQuestionCount.setText(Integer.toString(FakeDatabase.getUserQuestionCount()));
		textViewUserAnswerCount.setText(Integer.toString(FakeDatabase.getUserQuestionCount()));
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		textViewScoreCounter.setText(Integer.toString(FakeDatabase.userScore));
		textViewUserQuestionCount.setText(Integer.toString(FakeDatabase.getUserQuestionCount()));
		textViewUserAnswerCount.setText(Integer.toString(FakeDatabase.getUserQuestionCount()));
		
	}

	
	
	
}
