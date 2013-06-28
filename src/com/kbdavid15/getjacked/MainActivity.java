package com.kbdavid15.getjacked;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
	private static int mLastSelection;
	public static final int WORKOUT_PROGRAM_POSITION = 0;
	public static final int WORKOUT_POSITION = 1;
	public static final int EXERCISE_POSITION = 2;
	public static final int PROGRESS_POSITION = 3;
	public static final int CALENDAR_POSITION = 4;
	public static final int SETTINGS_POSITION = 5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		mDrawerTitles = getResources().getStringArray(R.array.drawer_titles);
		mDrawerList = (ListView)findViewById(R.id.left_drawer);
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(
				this,
				R.layout.drawer_list_item,
				R.id.drawer_item_text, mDrawerTitles));
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

		// if there is a savedInstance state, restore it
		if (mLastSelection >= 0) {
			selectItem(mLastSelection);
		} else if (savedInstanceState != null && 
				savedInstanceState.containsKey("currentPosition")) {
			int currentPosition = savedInstanceState.getInt("currentPosition");
			selectItem(currentPosition);
		} else {
			// load the programs fragment as the default view
			setTitle(mDrawerTitles[0]);
			Fragment fragment = new WorkoutProgramFragment();
			getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
		}
	}

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
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public class DrawerItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// check if settings was clicked (last item in list)
			if (position == SETTINGS_POSITION) {
				// start the settings activity
				startActivity(new Intent(MainActivity.this, SettingsActivity.class));
			} else {
				selectItem(position);
			}
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
		// save the last selected item
		mLastSelection = mDrawerList.getCheckedItemPosition();
		switch (position) {
		case WORKOUT_PROGRAM_POSITION:
			switchFragment(new WorkoutProgramFragment(), "WorkoutProgram", position);
			break;
		case WORKOUT_POSITION:
			switchFragment(new WorkoutFragment(), "Workout", position);
			break;
		case EXERCISE_POSITION:
			switchFragment(new ExerciseFragment(), "Exercise", position);
			break;
		case PROGRESS_POSITION:
			switchFragment(new ProgressFragment(), "Progress", position);
			break;
		case CALENDAR_POSITION:
			switchFragment(new CalendarFragment(), "Calendar", position);
			break;
		default:
			return;
		}
		
		mDrawerLayout.closeDrawer(mDrawerList);
	}
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}
	public void switchFragment(Fragment fragment, String tag, int position) {
		getSupportFragmentManager()
			.beginTransaction()
			.replace(R.id.content_frame, fragment, tag)
			.addToBackStack(null)
			.commit();
		
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawerTitles[position]);
	}
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);
		if (f != null)
			setTitle(mDrawerTitles[((IFragmentPosition)f).getFragmentPosition()]);
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("currentPosition", mLastSelection);
	}
}