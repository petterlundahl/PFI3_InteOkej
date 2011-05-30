package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;



import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class AnswerAdapter extends ArrayAdapter<Answer> implements OnClickListener {

	private ArrayList<Answer> answers;

	private BrowseAnswers myParent;


	public AnswerAdapter(Context context, int textViewResourceId, ArrayList items) {
		super(context, textViewResourceId, items);
		this.answers = items;
		this.myParent = (BrowseAnswers) context;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = myParent.getLayoutInflater();
		View row = inflater.inflate(R.layout.answer_list_item, parent, false);

		TextView usernameTag = (TextView) row.findViewById(R.id.username_tag);
		TextView answerLabel = (TextView)row.findViewById(R.id.answer_content);
		TextView numLikesText = (TextView) row.findViewById(R.id.num_likes);
		LikeButton likeButton = (LikeButton) row.findViewById(R.id.gillabutton);
		TextView timeStampText = (TextView) row.findViewById(R.id.timestampanswer);
		FlagButton flagButton = (FlagButton) row.findViewById(R.id.flaganswerbutton);
		
		likeButton.setPosition(position);
		likeButton.setTextView(numLikesText);

		Answer myAnswer = answers.get(position);
		
		myAnswer.setReadByUser();

		usernameTag.setText(myAnswer.getUserName());
		answerLabel.setText(myAnswer.getContent());
		numLikesText.setText(Integer.toString(myAnswer.getUsersWhoLikeThis().size()));
		timeStampText.setText(myAnswer.getDate().toLocaleString());
		
		if(myAnswer.getUsersWhoLikeThis().indexOf(FakeDatabase.getCurrentUserName()) != -1)
		{
			likeButton.setBackgroundResource(R.drawable.gilla);
		}
		
		if(myAnswer.getUsersWhoFlaggedThis().indexOf(FakeDatabase.getCurrentUserName()) != -1)
		{
			flagButton.setBackgroundResource(R.drawable.flagged_button);
		}
		
		flagButton.setPosition(position);
		flagButton.setOnClickListener(this);
		
		return row;
	}

	
	//on answer flag button clicked
	@Override
	public void onClick(View view) {
		final FlagButton flagButton = (FlagButton) view;
		final Answer selectedAnswer = answers.get(flagButton.getPosition());
		
		if(selectedAnswer.getUsersWhoFlaggedThis().indexOf(FakeDatabase.getCurrentUserName()) == -1){
			
			AlertDialog.Builder builder = new AlertDialog.Builder(myParent);
			builder.setMessage("Är du säker på att du vill anmäla det här svaret?")
			       .setCancelable(false)
			       .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			       		FakeDatabase.flagAnswer(selectedAnswer);
			       		Toast.makeText(myParent, "Du har flaggat detta svar som olämpligt.", 2000);
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
			Toast.makeText(myParent, "Du har redan flaggat det här svaret", 2000).show();
		}
		
	}

}