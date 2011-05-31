package nu.spiritusmundi.inteokej.android;

import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.id;
import nu.spiritusmundi.inteokej.android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener, OnEditorActionListener{
	
	private EditText passwordText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		FakeDatabase.create();
		
		Button loginButton = (Button) findViewById(R.id.login_button);
		
		passwordText = (EditText) findViewById(R.id.password_textview);
		passwordText.setOnEditorActionListener(this);
		loginButton.setOnClickListener(this);
		
		Button infoButton = (Button) findViewById(R.id.info_button);
		infoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(Login.this, "Nu skulle du ha skickas till hemsidan", 2000).show();
			}
		});
	}
	@Override
	public void onClick(View arg0) {
		login();
	}
	@Override
	public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
		login();
		return false;
	}
	
	private void login()
	{
		EditText userNameText = (EditText) findViewById(R.id.username);
		String userName = userNameText.getText().toString();
		
		if(!userName.equals("")){
			FakeDatabase.login(userName);
			
			Intent intent = new Intent(Login.this, TabHandler.class);
			startActivity(intent);
		}
	}
	
}

	
	