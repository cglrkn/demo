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
import org.w3c.dom.Comment;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FriendActivity extends ActionBarActivity implements View.OnClickListener{
	Button askButton;
	EditText commentBox;
	TextView success;
	public static final String Server ="http://192.168.2.133:8080/WebApplication6/commentInsert";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        askButton = ((Button) findViewById(R.id.ask));
        commentBox =((EditText) findViewById(R.id.commentBox));
        success = ((TextView) findViewById(R.id.success));
        askButton.setOnClickListener(this);
     
    }
    @Override
    public void onClick(View v) {
    	// TODO Auto-generated method stub
    	 String text2 = getIntent().getStringExtra("result");
         String[] array= text2.split(",");
         String emailArr[]= array[1].split("\"");
         
         
    	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

		StrictMode.setThreadPolicy(policy); 
		String text = commentBox.getText().toString();
		
		HttpPost post = new HttpPost(Server);
		DefaultHttpClient client = new DefaultHttpClient();
		 client.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(
		            0, false));
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("user_id", emailArr[2].substring(1)));
		nameValuePairs.add(new BasicNameValuePair("comment", text));
				
	try {
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		HttpResponse httpResponse = client.execute(post);
		HttpEntity httpEntity = httpResponse.getEntity();
		String output = EntityUtils.toString(httpEntity);
		if(output.equals("done"))
		{
			success.setText("Successfully sent!");
		}
		else
		{
			success.setText("Error occured!");
		}
					
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}catch (ClientProtocolException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//regLink.setText("2");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		//regLink.setText("3");
		
	}
    	
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater menuInflater = getMenuInflater();
    	menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        String text = getIntent().getStringExtra("result");
        if(id==R.id.logoutlink)
        {
        	Intent inte = new Intent(this, MainActivity.class);
			startActivity(inte);
			finish();
        	return true;
        	
        }
        if(id==R.id.profilepg)
        {
        	Intent inte = new Intent(this, ProfileActivity.class);
        	inte.putExtra("result", text);
			startActivity(inte);
			finish();
        	return true;
        }
        if(id==R.id.friends)
        {
        	Intent inte = new Intent(this, FriendListActivity.class);
        	inte.putExtra("result", text);
			startActivity(inte);
			finish();
        	return true;
        }
        if(id==R.id.home)
        {
        	Intent inte = new Intent(this, HomeActivty.class);
        	inte.putExtra("result", text);
			startActivity(inte);
			finish();
        	return true;
        }
        if(id==R.id.find_friends)
        {
        	Intent inte = new Intent(this, FindFriendActivity.class);
        	inte.putExtra("result", text);
			startActivity(inte);
			finish();
			return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
