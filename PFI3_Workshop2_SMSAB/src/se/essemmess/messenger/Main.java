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

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessListener;
import se.k3.goransson.andreas.essemmesslib.EssemmessLoginEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessPublishEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessReadEvent;


public class Main extends Activity implements OnClickListener, EssemmessListener {
    
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
        
        Login.essemess.addEssemmessEventListener(this);
        
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
        
        Button browseButton = (Button) findViewById(R.id.Button02);
        browseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
				startActivityForResult(intent, 0);
			}
		});
    }

	@Override
	public void onClick(View v) {
		message = messageTextField.getText().toString();
		tag = tagTextField.getText().toString();
		
		if( !message.equals("") && !tag.equals("") )
		{
			Login.essemess.post(message, tag);
			messageTextField.setText("");
			tagTextField.setText("");
		} else {
			Toast.makeText(getApplicationContext(), "Please enter tag and message", Toast.LENGTH_LONG).show();
		}
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        new AlertDialog.Builder(this).setMessage("Menu not yet implemented in this version").show();
		
        return true;
    }
	

	@Override
	public void NewEssemmessPosts(EssemmessReadEvent evt) {
		
	}

	@Override
	public void NewEssemmessLogin(EssemmessLoginEvent evt) {
		
	}

	@Override
	public void NewEssemmessPublish(EssemmessPublishEvent evt) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Posted sucessfully", Toast.LENGTH_LONG).show();
	}
}