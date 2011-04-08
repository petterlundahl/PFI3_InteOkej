package se.essemmess.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener {
    
	private Button btn;
	private EditText messageTextField;
	private EditText tagTextField;
	private TextView messageCharactersLeftView;
	private TextView tagCharactersLeftView;
	
	private String messageCharactersLeft;
	private String tagCharactersLeft;
	
	private String message;
	private String tag;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btn = (Button) findViewById(R.id.Button01);
        
        messageCharactersLeftView = (TextView) findViewById(R.id.TextView03);
        tagCharactersLeftView = (TextView) findViewById(R.id.TextView04);
        
        tagTextField = (EditText) findViewById(R.id.EditText01);
        tagTextField.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				tagCharactersLeft = "(" + Integer.toString(tagTextField.getText().length()) + "/32)";
				tagCharactersLeftView.setText(tagCharactersLeft);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
        
        btn.setOnClickListener(this);
        
        messageTextField = (EditText) findViewById(R.id.EditText02);
        messageTextField.addTextChangedListener(new TextWatcher() {
        	
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				messageCharactersLeft = "(" + Integer.toString(messageTextField.getText().length()) + "/256)";
				messageCharactersLeftView.setText(messageCharactersLeft);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
    }

	@Override
	public void onClick(View v) {
		message = messageTextField.getText().toString();
		tag = tagTextField.getText().toString();
		
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
		
		messageTextField.setText("");
		tagTextField.setText("");
		
		//sendSMS(message);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        new AlertDialog.Builder(this).setMessage("Menu not yet implemented in this version").show();
		
        return true;
    }
	
	
	
	@SuppressWarnings("deprecation")
	private void sendSMS(String msg)
	{
		SmsManager smsManager = SmsManager.getDefault();
	    smsManager.sendTextMessage("0046708921714", null, msg, null, null);
	}
}