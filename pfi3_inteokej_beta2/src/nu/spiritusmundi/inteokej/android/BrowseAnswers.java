package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;

import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.id;
import nu.spiritusmundi.inteokej.android.R.layout;
import nu.spiritusmundi.inteokej.android.R.string;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BrowseAnswers extends Activity implements OnClickListener {
	
	private ListView listView;
	private ArrayList<Answer> answers;
	private static Question question;
	
	private TextView textViewQuestionHeadline;
	private TextView textViewQuestionContent;
	private EditText editTextReplyBox;
	private TextView numSupportsText;
	private TextView numAnswersText;
	private TextView numViewsText;
	
	private View viewFooter;
	private View viewHeader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.browse_answer);
		
		answers = question.getAnswers();
		
		listView = (ListView) findViewById(R.id.questionlist);
		viewFooter = (View) getLayoutInflater().inflate(R.layout.browse_answer_footer_replybox, null);
		viewHeader = (View) getLayoutInflater().inflate(R.layout.browse_answer_header_questioncontent, null);
		textViewQuestionHeadline = (TextView)viewHeader.findViewById(R.id.questionViewHeadline);
		
		textViewQuestionContent = (TextView)viewHeader.findViewById(R.id.questionViewContent);
		numSupportsText = (TextView) viewHeader.findViewById(R.id.num_support_answerview);
		numAnswersText = (TextView) viewHeader.findViewById(R.id.num_reply_answerview);
		
		numViewsText = (TextView) viewHeader.findViewById(R.id.num_view_answerview);
		
		listView.addHeaderView(viewHeader);
		listView.addFooterView(viewFooter);
		listView.setAdapter(new AnswerAdapter(this, R.layout.question_list_item, answers));
		
		editTextReplyBox = (EditText) findViewById(R.id.editTextReplyBox);
		
		SupportButton supportButton = (SupportButton) viewHeader.findViewById(R.id.supportbutton);
		
		supportButton.setOnClickListener(this);
		
		
		
		
		updateTextViews();
		
	}
	
	
	
	public static void setQuestion(Question p_question){
		question = p_question;
	}
	
	public void replyButtonClicked(View view){
		if(!editTextReplyBox.getText().toString().equals("")){
			FakeDatabase.answerQuestion(question, new Answer(editTextReplyBox.getText().toString(), FakeDatabase.getCurrentUserName()));
			editTextReplyBox.setText("");
			editTextReplyBox.setHint(R.string.replyBoxHint);
		
			/*The below code hides the soft keyboard when user has submitted their question. AL 13/5*/
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(listView.getWindowToken(), 0);
		
        
			updateTextViews();
        
		}
	}
	
	public void likeButtonClicked(View view){
		
		LikeButton likeButton = (LikeButton) view;
		Answer answer = answers.get(likeButton.getPosition());
		
		if(FakeDatabase.likeAnswer(answer)){
			Toast.makeText(this, "Nu har du gillat det h�r svaret!", 1000).show();
		} else {
			Toast.makeText(this, "Du har redan gillat det h�r svaret", 1000).show();
		}
		
		likeButton.getTextView().setText(Integer.toString(answer.getUsersWhoLikeThis().size()));
		
	}
	


	/*
	 * onClick for the support button listening. 
	 * */
	@Override
	public void onClick(View arg0) {
		if(FakeDatabase.supportQuestion(question)){
			Toast.makeText(this, "Nu har du givit ditt st�d till den h�r fr�gan!", 1000).show();
		} else {
			Toast.makeText(this, "Du har redan givit ditt st�d till den h�r fr�gan", 1000).show();
		}
		
		
		updateTextViews();
		
	}
	
	private void updateTextViews()
	{
		answers = question.getAnswers();
		
		textViewQuestionHeadline.setText(question.getTitle());
		textViewQuestionContent.setText(question.getContent());
		numSupportsText.setText(Integer.toString(question.getUsersWhoSupportsThis().size()));
		numAnswersText.setText(Integer.toString(question.getAnswers().size()));
		numViewsText.setText(Integer.toString(question.getNumViews()));
	}
	
}
