package nu.spiritusmundi.inteokej.android;


	import java.util.ArrayList;
	import java.util.List;

	import android.content.Context;
	import android.graphics.Typeface;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

	public class ArchiveAdapter  extends ArrayAdapter<Question> {

		private ArrayList<Question> questions;
		private BrowseMyQuestions parentActivity;
		private TextView textVTitle;
		private Question myQuestion;
		
		public ArchiveAdapter(Context context, int textViewResourceId, ArrayList<Question> p_myQuestions) {
			super(context, textViewResourceId, p_myQuestions);
			this.parentActivity = (BrowseMyQuestions) context;
			this.questions = p_myQuestions;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = parentActivity.getLayoutInflater();
			View row = inflater.inflate(R.layout.archive_list_item, parent, false);
			textVTitle = (TextView)row.findViewById(R.id.archive_content_title);
			myQuestion = (Question) questions.get(position);
			textVTitle.setText(myQuestion.getTitle());
			
			textVTitle.setTypeface(Typeface.createFromAsset(parentActivity.getAssets(), "fonts/MyriadWebPro.ttf"), Typeface.BOLD);
			
			TextView numAnswersText = (TextView) row.findViewById(R.id.num_answers);
			
			int numAnswers = 0;
			
			for(int i = 0; i < myQuestion.getAnswers().size(); i ++){
				if(!myQuestion.getAnswers().get(i).getReadByUser()){
					numAnswers ++;
				}
			}
			
			numAnswersText.setText(Integer.toString(numAnswers));
			
			if(numAnswers == 0){
				LinearLayout bubble = (LinearLayout) row.findViewById(R.id.bubble);
				bubble.setBackgroundResource(R.drawable.archive_bubble_grey);
				
				numAnswersText.setText(Integer.toString(myQuestion.getAnswers().size()));
				
				TextView label = (TextView) row.findViewById(R.id.new_answers_label);
				label.setText("lästa svar");
			}
			
			return row;
		}


	}
