package nu.spiritusmundi.inteokej.android;

import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.id;
import nu.spiritusmundi.inteokej.android.R.layout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostQuestion extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_questions);
		
		
		final EditText questionHeadlineEditText = (EditText) findViewById(R.id.questionHeadlineEditText);		
		final Button submitQuestionButton = (Button) findViewById(R.id.submitQuestionButton);
		final EditText questionContentEditText = (EditText) findViewById(R.id.questionContentEditText);
		
		submitQuestionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                
            	if(!questionContentEditText.getText().toString().equals("") || !questionHeadlineEditText.getText().toString().equals("")){
            			FakeDatabase.postNewQuestion(new Question(questionHeadlineEditText.getText().toString(), 
            			questionContentEditText.getText().toString(), FakeDatabase.getCurrentUserName(), "test-tagg"));
            		
            		questionHeadlineEditText.setText("");
            		questionContentEditText.setText("");
            		
            		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        			imm.hideSoftInputFromWindow(questionContentEditText.getWindowToken(), 0);
            		
            		Toast.makeText(PostQuestion.this, "Din fråga har skickats", 2000).show();
            	} else {
            		Toast.makeText(PostQuestion.this, "Du måste fylla i rubrik och fråga", 2000).show();
            	}
            }
            
            
        });
	}
	
}
