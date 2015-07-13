package com.example.demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.demo.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Register extends ActionBarActivity implements View.OnClickListener{
	public static final String Server ="http://192.168.2.133:8080/WebApplication6/signUpServlet";
	EditText email, password, fname, lname, day, year;
	Button continueButton, register;
	TextView backtologin, warning;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = ((EditText) findViewById(R.id.regEmail));
        password = ((EditText) findViewById(R.id.regPassword));
        fname = ((EditText) findViewById(R.id.fname));
        lname = ((EditText) findViewById(R.id.lastname));
        continueButton = ((Button) findViewById(R.id.cont));
        backtologin = ((TextView) findViewById(R.id.backToLoginLink));
        warning= ((TextView) findViewById(R.id.warning));
        
        continueButton.setOnClickListener(this);
        backtologin.setOnClickListener(this);
      
      
    }
    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	switch(v.getId()){
		
		case R.id.cont:
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    		StrictMode.setThreadPolicy(policy); 
			String email1 = email.getText().toString();
			String password1 = password.getText().toString();
			String firstname = fname.getText().toString();
			String lastname = lname.getText().toString();
			if(email1.length()==0|| password1.length()==0
					|| firstname.length()==0 || lastname.length()==0)
			{
				warning.setText("Please fill all the blank spaces");
				break;
			}
			else{
				HttpPost post = new HttpPost(Server);
				DefaultHttpClient client = new DefaultHttpClient();
				 client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
				            0, false));
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
				nameValuePairs.add(new BasicNameValuePair("email", email1));
				nameValuePairs.add(new BasicNameValuePair("firstname", firstname));
				nameValuePairs.add(new BasicNameValuePair("lastname", lastname));
				nameValuePairs.add(new BasicNameValuePair("password", password1));
				
				try {
					post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse httpResponse = client.execute(post);
					HttpEntity httpEntity = httpResponse.getEntity();
					String output = EntityUtils.toString(httpEntity);
					if(output.equals("done"))
					{
						Intent in = new Intent(this, AfterRegisterActivity.class);
						startActivity(in);
					}
					else
					{
						warning.setText(output);
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					backtologin.setText("2");
				}catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					backtologin.setText("3");
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			
				}
				break;
				
			}
		case R.id.backToLoginLink:
			Intent in = new Intent(this, MainActivity.class);
			startActivity(in);
			finish();
			break;
    	}
    	
    }

}