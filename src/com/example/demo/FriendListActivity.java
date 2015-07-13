package com.example.demo;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class FriendListActivity extends ActionBarActivity implements View.OnClickListener {
	TextView friend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        friend= ((TextView) findViewById(R.id.friend1));
        friend.setOnClickListener(this);
    }
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	String text = getIntent().getStringExtra("result");
	Intent inte = new Intent(this, FriendActivity.class);
	inte.putExtra("result", text);
	startActivity(inte);
	finish();
	
}
    @Override
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
