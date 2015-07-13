package com.example.demo;

import java.util.ArrayList;

import com.google.gson.Gson;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class ProfileActivity extends ActionBarActivity {
	ActionBar a;

	TextView textV,textV2, textV3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        textV= (TextView)(findViewById(R.id.profEmail));
        textV2= (TextView)(findViewById(R.id.fname));
        textV3= (TextView)(findViewById(R.id.lastname));
        String text = getIntent().getStringExtra("result");
        String[] array= text.split(",");
        String emailArr[]= array[0].split("\"");
        textV.setText(emailArr[3]);
        String nameArr[]= array[2].split("\"");
        textV2.setText(nameArr[3]);
        String lastnameArr[]= array[3].split("\"");
        textV3.setText(lastnameArr[3]);
//        setupTabs();
    }
//    @SuppressWarnings("deprecation")
//	private void setupTabs() {
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);
//    }
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
        
        if(id==R.id.home)
        {
        	Intent inte = new Intent(this, HomeActivty.class);
        	inte.putExtra("result", text);
			startActivity(inte);
			finish();
        	return true;
        }
        if(id==R.id.profilepg)
        {
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
        if(id== R.id.logoutlink)
        {
        	Intent inte = new Intent(this, MainActivity.class);
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
