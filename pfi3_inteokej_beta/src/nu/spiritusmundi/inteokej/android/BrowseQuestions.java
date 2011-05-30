package nu.spiritusmundi.inteokej.android;

import java.util.ArrayList;

import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.color;
import nu.spiritusmundi.inteokej.android.R.id;
import nu.spiritusmundi.inteokej.android.R.layout;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class BrowseQuestions extends Activity implements OnItemClickListener, OnClickListener {
	
	private ListView listView;
	
	private ArrayList<Question> questions;
	
	private Button[] sortButtons;
	private int[] btnIds = {R.id.sortbtn01, R.id.sortbtn02, R.id.sortbtn03, R.id.sortbtn04};
	
	private final int VIEW_TITLES = 0;
	private final int VIEW_CATEGORIES = 1;
	private final int VIEW_CATEGORY_TITLES = 2;
	private int viewMode = VIEW_TITLES;
	
	int currentSubTab = 0;
	
	private String currentTag;
	
	public static boolean REDIRECTED_FROM_POST = false;
	
	private ArrayList<String> categories;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_questions);
		
		sortButtons = new Button[btnIds.length];
		
		listView = (ListView) findViewById(R.id.questionlist);
		
		listView.setOnItemClickListener(this);
		
		for(int i = 0; i < btnIds.length; i ++)
		{
			sortButtons[i] = (Button) findViewById(btnIds[i]);
			sortButtons[i].setOnClickListener(this);
		}
		sortButtons[0].setBackgroundResource(R.drawable.markering);
		
		loadLatestQuestions();
	}
	
	public void loadLatestQuestions()
	{
		listView.setDividerHeight(1);
		
		questions = FakeDatabase.getLatestQuestions();

		listView.setAdapter(new QuestionAdapter(
	            this, R.layout.question_list_item, questions));
		
		setButtonSelected(sortButtons[0]);
	}
	
	private void setButtonSelected(Button btn)
	{
		btn.setBackgroundResource(R.drawable.markering);
		
		for(int i = 0; i < sortButtons.length; i ++)
		{
			if(btn.getId() != btnIds[i]){
				sortButtons[i].setBackgroundResource(R.color.question_sort_button);
			}
		}
	}

	@Override
	protected void onResume() {
		if(REDIRECTED_FROM_POST){
			loadLatestQuestions();
			REDIRECTED_FROM_POST = false;
		} else {
		
		switch(currentSubTab){
		case 0:
			loadLatestQuestions();
			break;
		case 1:
			questions = FakeDatabase.getHottestQuestions();
			listView.setAdapter(new QuestionAdapter(
		            this, R.layout.question_list_item, questions));
			break;
		case 2:
			questions = FakeDatabase.getUnansweredQuestions();
			listView.setAdapter(new QuestionAdapter(
		            this, R.layout.question_list_item, questions));
			break;
		case 3:
			
			if(viewMode == VIEW_CATEGORIES){
				listView.setDividerHeight(0);
				categories = FakeDatabase.getSortedTags();
				listView.setAdapter(new CategoryAdapter(
						this, R.layout.browse_categories_item, categories));
			} else if(viewMode == VIEW_CATEGORY_TITLES){
				questions = FakeDatabase.getFilteredQuestions(currentTag);
				listView.setAdapter(new QuestionAdapter(
			            this, R.layout.question_list_item, questions));
			}
			break;
		}
		}
		
		super.onResume();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
		
		if(viewMode == VIEW_CATEGORIES){
			listView.setDividerHeight(1);
			viewMode = VIEW_CATEGORY_TITLES;
			currentTag = categories.get(index);
			categories = FakeDatabase.getSortedTags();
			questions = FakeDatabase.getFilteredQuestions(currentTag);
			listView.setAdapter(new QuestionAdapter(
		            this, R.layout.question_list_item, questions));
		} else {
			FakeDatabase.viewQuestion(questions.get(index));
			BrowseAnswers.setQuestion(questions.get(index));
			
			Intent intent = new Intent(this, BrowseAnswers.class);
			startActivity(intent);
		}
		
	}

	@Override
	public void onClick(View v) {
		
		setButtonSelected((Button) v);
		
		listView.setDividerHeight(1);
		viewMode = VIEW_TITLES;
		
		switch(v.getId()){
		case R.id.sortbtn01:
			currentSubTab = 0;
			loadLatestQuestions();
			break;
		case R.id.sortbtn02:
			currentSubTab = 1;
			questions = FakeDatabase.getHottestQuestions();
			listView.setAdapter(new QuestionAdapter(
		            this, R.layout.question_list_item, questions));
			break;
		case R.id.sortbtn03:
			currentSubTab = 2;
			questions = FakeDatabase.getUnansweredQuestions();
			listView.setAdapter(new QuestionAdapter(
		            this, R.layout.question_list_item, questions));
			break;
		case R.id.sortbtn04:
			currentSubTab = 3;
			viewMode = VIEW_CATEGORIES;
			listView.setDividerHeight(0);
			categories = FakeDatabase.getSortedTags();
			listView.setAdapter(new CategoryAdapter(
		            this, R.layout.browse_categories_item, categories));
			
			break;
		}
		
		
	}

	@Override
	public void onBackPressed() {
		if(viewMode == VIEW_CATEGORY_TITLES){
			viewMode = VIEW_CATEGORIES;
			listView.setDividerHeight(0);
			categories = FakeDatabase.getSortedTags();
			listView.setAdapter(new CategoryAdapter(
		            this, R.layout.browse_categories_item, categories));
			
		} else {
			super.onBackPressed();
		}
	}

	
}
