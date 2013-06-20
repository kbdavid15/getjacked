package com.kbdavid15.getjacked;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.kbdavid15.getjacked.R;

public class MainActivity extends SherlockFragmentActivity {
	private String[] mDrawerTitles;
	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDrawerTitles = getResources().getStringArray(R.array.drawer_titles);
		mDrawerList = (ListView)findViewById(R.id.left_drawer);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		
		// Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, R.id.drawer_item_text, mDrawerTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public class DrawerItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}
	
	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
	    // Create a new fragment and specify the planet to show based on position
		WorkoutFragment fragment = new WorkoutFragment();
	    Bundle args = new Bundle();
	    fragment.setArguments(args);

	    // Insert the fragment by replacing any existing fragment
	    FragmentManager fragmentManager = getSupportFragmentManager();
	    fragmentManager.beginTransaction()
	                   .replace(R.id.content_frame, fragment)
	                   .commit();

	    // Highlight the selected item, update the title, and close the drawer
	    mDrawerList.setItemChecked(position, true);
	    setTitle(mDrawerTitles[position]);
	    mDrawerLayout.closeDrawer(mDrawerList);
	}
	@Override
	public void setTitle(CharSequence title) {
	    mTitle = title;
	    getSupportActionBar().setTitle(mTitle);
	}
}