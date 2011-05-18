package nu.spiritusmundi.inteokej.android;

import nu.spiritusmundi.inteokej.android.R;
import nu.spiritusmundi.inteokej.android.R.id;
import nu.spiritusmundi.inteokej.android.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		FakeDatabase.create();
		
		Button loginButton = (Button) findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
	}
	@Override
	public void onClick(View arg0) {
		
		EditText userNameText = (EditText) findViewById(R.id.username);
		String userName = userNameText.getText().toString();
		
		if(!userName.equals("")){
			FakeDatabase.login(userName);
			
			Intent intent = new Intent(Login.this, TabHandler.class);
			startActivity(intent);
		}
		
		
	}
	
}

	
	