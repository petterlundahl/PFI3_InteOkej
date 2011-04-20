package se.essemmess.messenger;

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import se.k3.goransson.andreas.essemmesslib.EssemmessListener;
import se.k3.goransson.andreas.essemmesslib.EssemmessLoginEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessPublishEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessReadEvent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener, EssemmessListener {

	public static Essemmess essemess;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button button = (Button) findViewById(R.id.Button01);
		button.setOnClickListener(this);
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
	public void onClick(View v) {
		essemess = EssemmessHelper.getServer(this);
		essemess.addEssemmessEventListener(this);
		
		
		EditText userText = (EditText) findViewById(R.id.EditText01);
		String username = userText.getText().toString();
		
		EditText passwordText = (EditText) findViewById(R.id.EditText02);
		String password = passwordText.getText().toString();
		
		if(!password.equals("") && !username.equals("")){
			essemess.login(username, password);
		} else {
			Toast.makeText(this, "Please enter user name and password", Toast.LENGTH_LONG).show();
		}
		
	}


	@Override
	public void NewEssemmessPosts(EssemmessReadEvent evt) {
		
	}

	@Override
	public void NewEssemmessLogin(EssemmessLoginEvent evt) {
		Toast.makeText(this, "Logged in sucessfully!", Toast.LENGTH_LONG).show();
		Intent intent = new Intent(this, Main.class);
		startActivity(intent);
	}

	@Override
	public void NewEssemmessPublish(EssemmessPublishEvent evt) {
		
	}

	

}
