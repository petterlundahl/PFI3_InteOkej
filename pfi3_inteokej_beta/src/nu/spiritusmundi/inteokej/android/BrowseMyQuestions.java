package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class BrowseMyQuestions extends Activity implements OnItemClickListener {

	private ArrayList<Question> questions;
	private ListView listView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_archive);
		
		listView = (ListView) findViewById(R.id.archivelist);
		listView.setOnItemClickListener(this);
		populateList();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		populateList();
	}
	

	private void populateList(){
		questions = FakeDatabase.getMyQuestionsSorted();
		listView.setAdapter(new ArchiveAdapter(this, R.layout.archive_list_item, questions));
	}
	

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
		
		BrowseAnswers.setQuestion(questions.get(index));
		
		Intent intent = new Intent(this, BrowseAnswers.class);
		startActivity(intent);
	}
}