package com.kbdavid15.getjacked;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
	private String[] mDrawerTitles;
	private ListView mDrawerList;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle = "Get Jacked";
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

		// set up the drawer toggle
		mDrawerToggle = new ActionBarDrawerToggle(
				this,                  /* host Activity */
				mDrawerLayout,         /* DrawerLayout object */
				R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */	//TODO replace with new icon
				R.string.drawer_open,  /* "open drawer" description */
				R.string.drawer_close  /* "close drawer" description */
				) {


			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		// load the programs fragment as the default view
		setTitle(mDrawerTitles[0]);
		Fragment fragment = new WorkoutProgramFragment();
		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
	}

	/* Called whenever we call invalidateOptionsMenu() */
//	@Override
//	public boolean onPrepareOptionsMenu(Menu menu) {
//		// If the nav drawer is open, hide action items related to the content view
//		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//		if (drawerOpen) {
//			mNewWorkoutMenuItem.setVisible(false);
//			mNewExerciseMenuItem.setVisible(false);
//		} else {
//			//TODO have each fragment manage menu items independently
//			switch (mDrawerList.getCheckedItemPosition()) {
//			case 0:
//				// Workout plans
//				
//			case 1:
//				// workout
//				mNewExerciseMenuItem.setVisible(false);
//				mNewWorkoutMenuItem.setVisible(true);
//				break;
//			case 2:
//				// Exercises
//				mNewWorkoutMenuItem.setVisible(false);
//				mNewExerciseMenuItem.setVisible(true);
//				break;
//			case 3:
//				// progress
//				mNewWorkoutMenuItem.setVisible(false);
//				mNewExerciseMenuItem.setVisible(false);
//				break;
//			case 4:
//				// calendar
//				break;
//			case 5:
//				// settings
//				break;
//			}
//		}
//		
//		return super.onPrepareOptionsMenu(menu);
//	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
//		// get handles on the menu items
//		mNewExerciseMenuItem = menu.findItem(R.id.action_add_exercise);
//		mNewWorkoutMenuItem = menu.findItem(R.id.action_add_workout);
		
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// handle item selection
		switch (item.getItemId()) {
//		case R.id.action_add_workout:
//			startActivity(new Intent(this, NewWorkoutActivity.class));
//			return true;
//		case R.id.action_add_exercise:
//			//startActivity(new Intent(this, NewExerciseActivity.class));
//			NewExerciseDialogFragment dialog = new NewExerciseDialogFragment();
//			dialog.show(getSupportFragmentManager(), "NewExerciseTag");
//			
//			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class DrawerItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// Create a new fragment
		Fragment fragment;
		switch (position) {
		case 0:
			fragment = new WorkoutProgramFragment();
			break;
		case 1:
			fragment = new WorkoutFragment();
			break;
		case 2:
			fragment = new ExerciseFragment();
			break;
		case 3:
			fragment = new ProgressFragment();
			break;
		case 4:
			fragment = new CalendarFragment();
			break;
		case 5:
			// start the settings activity
			startActivity(new Intent(this, SettingsActivity.class));
			return;
		default:
			return;
		}

		// Insert the fragment by replacing any existing fragment
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

		// Highlight the selected item, update the title, and close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawerTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
}