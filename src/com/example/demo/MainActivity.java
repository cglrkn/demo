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
import com.example.demo.Register;
import com.google.gson.Gson;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
	public static final String Server ="http://192.168.2.133:8080/WebApplication6/loginServlet";
	EditText password, email;
	Button login;
	TextView regLink, alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email= ((EditText) findViewById(R.id.email));
        password= ((EditText) findViewById(R.id.password));
        login= ((Button) findViewById(R.id.login));
        alert= ((TextView) findViewById(R.id.alert));
		regLink=((TextView) findViewById(R.id.regLink));
	
		login.setOnClickListener(this);
		regLink.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	switch(v.getId()){
    	case R.id.login:
    		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    		StrictMode.setThreadPolicy(policy); 
			String email1 = email.getText().toString();
			String password1 = password.getText().toString();
			HttpPost post = new HttpPost(Server);
			DefaultHttpClient client = new DefaultHttpClient();
			 client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
			            0, false));
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("email", email1));
			nameValuePairs.add(new BasicNameValuePair("password", password1));
			
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse httpResponse = client.execute(post);
			HttpEntity httpEntity = httpResponse.getEntity();
			String output = EntityUtils.toString(httpEntity);
			if(output.length()>23 && output.substring(15, 22).equals("SUCCESS"))
			{
				Intent in = new Intent(this, HomeActivty.class);
				in.putExtra("result", output.substring(36));
				startActivity(in);
			}
			else{
				alert.setText("Wrong email or password");
			}
		
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			regLink.setText("2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			regLink.setText("3");
			
		}
		break;
			case R.id.regLink:
				Intent i = new Intent(this, Register.class);
				startActivity(i);
				break;
    	}
    	
    }

}
