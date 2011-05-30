package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;

import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.id;
import nu.spiritusmundi.inteokej.android.R.layout;
import nu.spiritusmundi.inteokej.android.R.string;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
	private SupportButton supportButton;
	private FlagButton flagButton;
	private TextView timeStampText;
	
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
		
		TextView userNameText = (TextView) viewHeader.findViewById(R.id.browse_answer_header_username_display);
		userNameText.setText(question.getUserName());
		
		listView.addHeaderView(viewHeader);
		listView.addFooterView(viewFooter);
		listView.setAdapter(new AnswerAdapter(this, R.layout.question_list_item, answers));
		
		editTextReplyBox = (EditText) findViewById(R.id.editTextReplyBox);
		
		supportButton = (SupportButton) viewHeader.findViewById(R.id.supportbutton);
		supportButton.setOnClickListener(this);
		
		if(question.getUsersWhoSupportsThis().indexOf(FakeDatabase.getCurrentUserName()) != -1){
			supportButton.setBackgroundResource(R.drawable.supportbutton_selected);
		} 
		
		updateTextViews();
		
		flagButton = (FlagButton) viewHeader.findViewById(R.id.flagbutton);
		flagButton.setOnClickListener(this);
		
		if(question.getUsersWhoFlaggedThis().indexOf(FakeDatabase.getCurrentUserName()) != -1){
			flagButton.setBackgroundResource(R.drawable.flagged_button);
		}
		
		timeStampText = (TextView) viewHeader.findViewById(R.id.timestamptext);
		timeStampText.setText(question.getDate().toLocaleString());
		
		
		Button backButton = (Button) findViewById(R.id.back_button);
		backButton.setOnClickListener(this);
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
			Toast.makeText(this, "Nu har du gillat det här svaret!", 1000).show();
			likeButton.setBackgroundResource(R.drawable.gilla);
		} else {
			Toast.makeText(this, "Du har redan gillat det här svaret", 1000).show();
		}
		
		likeButton.getTextView().setText(Integer.toString(answer.getUsersWhoLikeThis().size()));
	}
	

	@Override
	public void onClick(View view) {
		
		switch(view.getId()){
		case R.id.supportbutton:
			if(FakeDatabase.supportQuestion(question)){
				Toast.makeText(this, "Nu har du givit ditt stöd till den här frågan!", 1000).show();
				supportButton.setBackgroundResource(R.drawable.supportbutton_selected);
				updateTextViews();
				
			} else {
				Toast.makeText(this, "Du har redan givit ditt stöd till den här frågan", 1000).show();
			}
			break;
		case R.id.flagbutton:
				if(question.getUsersWhoFlaggedThis().indexOf(FakeDatabase.getCurrentUserName()) == -1){
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setMessage("Är du säker på att du vill anmäla denna fråga?")
				       .setCancelable(false)
				       .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				       		FakeDatabase.flagQuestion(question);
				       		Toast.makeText(BrowseAnswers.this, "Du har flaggat detta meddelande som olämpligt.", 2000);
				       		flagButton.setBackgroundResource(R.drawable.flagged_button);

				           }
				       })
				       .setNegativeButton("Nej", new DialogInterface.OnClickListener() {
				           public void onClick(DialogInterface dialog, int id) {
				                dialog.cancel();
				           }
				       });
				AlertDialog alert = builder.create();
				alert.show();
				
				
			} else {
			Toast.makeText(this, "Du har redan flaggat den här frågan", 2000).show();
			}
			break;
			
		case R.id.back_button:
			
			onBackPressed();
			
			break;
		}
		
		
		
		
		
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
