package com.android;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity 
{
	String operations [] = {"Gray scale", "black & white", "Show contours"};//, "no. of contours"

	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter <String>(Menu.this, android.R.layout.simple_expandable_list_item_1, operations));
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	 {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String click = operations[position];

		//Class menuClass = Class.forName("com.android."+ click);
		Intent menuIntent = new Intent ();
		menuIntent.putExtra("position", position);
		setResult(RESULT_OK,menuIntent);
		Log.v(this.toString(), " i am in Menu.java and i have clicked position num"+position);
		finish();
		
	   }
}




