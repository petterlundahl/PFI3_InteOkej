package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class AnswerAdapter extends ArrayAdapter<Answer> {

	private ArrayList items;
	
	private BrowseAnswers myParent;

	
	public AnswerAdapter(Context context, int textViewResourceId, ArrayList items) {
		super(context, textViewResourceId, items);
		this.items = items;
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
		
		likeButton.setPosition(position);
		likeButton.setTextView(numLikesText);
		
		Answer myAnswer = (Answer) items.get(position);
		
		usernameTag.setText(myAnswer.getUserName());
		answerLabel.setText(myAnswer.getContent());
		numLikesText.setText(Integer.toString(myAnswer.getUsersWhoLikeThis().size()));
		
		
		return row;
	}

}
