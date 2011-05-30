package nu.spiritusmundi.inteokej.android;

import java.util.Set;

import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.id;
import nu.spiritusmundi.inteokej.android.R.layout;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class PostQuestion extends Activity implements OnEditorActionListener, OnClickListener, OnItemSelectedListener {
	
	private EditText questionHeadlineEditText;		
	private Button submitQuestionButton;
	private EditText questionContentEditText;
	private Button tagButton;
	
	private String tag = Tag.UNTAGGED;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_questions);
		
		questionHeadlineEditText = (EditText) findViewById(R.id.questionHeadlineEditText);		
		submitQuestionButton = (Button) findViewById(R.id.submitQuestionButton);
		questionContentEditText = (EditText) findViewById(R.id.questionContentEditText);
		
		
		
		Spinner spinner = (Spinner) findViewById(R.id.Spinner01);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_spinner_item, Tag.ALL_TAGS);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spinner.setAdapter(adapter);
		
		spinner.setOnItemSelectedListener(this);
		
		
		submitQuestionButton.setOnClickListener(this);
		questionContentEditText.setOnEditorActionListener(this);   
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		//post();
		return false;
	}
	
	@Override
	public void onClick(View v) {
		post();
		
	}
	
	
	private void post()
	{
		if(!questionContentEditText.getText().toString().equals("") && !questionHeadlineEditText.getText().toString().equals("")){
			FakeDatabase.postNewQuestion(new Question(questionHeadlineEditText.getText().toString(), 
			questionContentEditText.getText().toString(), FakeDatabase.getCurrentUserName(), tag));
			
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(questionContentEditText.getWindowToken(), 0);
			
			questionHeadlineEditText.setText("");
			questionContentEditText.setText("");
		
			Toast.makeText(PostQuestion.this, "Din fråga har skickats", 2000).show();
		
			BrowseQuestions.REDIRECTED_FROM_POST = true;
			TabHandler.setTab(0);
		
	} else {
		Toast.makeText(PostQuestion.this, "Du måste fylla i rubrik och fråga", 2000).show();
	}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int index,
			long arg3) {
		tag = Tag.ALL_TAGS[index];
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
