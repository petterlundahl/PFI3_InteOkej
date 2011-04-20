package se.essemmess.messenger;

import java.util.ArrayList;

import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import se.k3.goransson.andreas.essemmesslib.EssemmessListener;
import se.k3.goransson.andreas.essemmesslib.EssemmessLoginEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessPublishEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessReadEvent;
import se.k3.goransson.andreas.essemmesslib.Post;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ReadActivity extends Activity implements EssemmessListener, OnItemClickListener {

	private ListView listView;
	private ArrayList<Post> posts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.read);
		Login.essemess.addEssemmessEventListener(this);
		Login.essemess.read("");
		
		//ListView listView = getListView();
		listView = (ListView) findViewById(R.id.messagelist);
		listView.setTextFilterEnabled(true);
		listView.setOnItemClickListener(this);
		
		Button button = (Button) findViewById(R.id.backbutton);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				endActivity();
			}
		});
	}
	
	private void endActivity()
	{
		Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}



	@Override
	public void NewEssemmessPosts(EssemmessReadEvent evt) {
		posts = evt.getPosts();
		ArrayAdapter<Post> adapter = new ArrayAdapter<Post>(this, R.layout.list_item, posts);
		listView.setAdapter(adapter);
	}

	@Override
	public void NewEssemmessLogin(EssemmessLoginEvent evt) {
		
	}

	@Override
	public void NewEssemmessPublish(EssemmessPublishEvent evt) {
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int index, long arg3) {
		TextView tv = (TextView) view;
		Toast.makeText(this, posts.get(index).getFullInfo(), Toast.LENGTH_LONG).show();
	}

	

}
