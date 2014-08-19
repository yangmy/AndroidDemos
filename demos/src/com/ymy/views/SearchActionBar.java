package com.ymy.views;

import com.example.demos.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.SearchView.OnQueryTextListener;

public class SearchActionBar extends Activity implements OnQueryTextListener{

	TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchaction);
		text=(TextView) findViewById(R.id.textView);
	}
	
	SearchView msearch;
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater infl=getMenuInflater();
		infl.inflate(R.menu.searchaction, menu);
		MenuItem item=menu.findItem(R.id.search);
		msearch=(SearchView)item.getActionView();
		msearch.setOnQueryTextListener(this);
		return true;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		return false;
	}
	@Override
	public boolean onQueryTextChange(String newText) {
		text.setText(newText);
		return true;
	}
	
	
}
