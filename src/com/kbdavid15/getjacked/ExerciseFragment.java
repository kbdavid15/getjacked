package com.kbdavid15.getjacked;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kbdavid15.getjacked.workouts.Exercise;
import com.kbdavid15.getjacked.workouts.DatabaseHelper;

public class ExerciseFragment extends ListFragment implements LoaderCallbacks<Cursor> {
	private DatabaseHelper dbHelper;
	private SimpleCursorAdapter cursorAdapter;
	
	private static final int EXERCISE_LOADER_ID = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		dbHelper = DatabaseHelper.getInstance(getActivity());
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Prepare the loader.  Either re-connect with an existing one,
		// or start a new one.
		getLoaderManager().initLoader(EXERCISE_LOADER_ID, null, this);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.exercises, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// populate the listview with the contents of the exercise database
		Cursor cursor = dbHelper.getExercises();
		
		// The columns to be bound
		String[] columns = new String[] {
				DatabaseHelper.COLUMN_EXERCISE_NAME,
				DatabaseHelper.COLUMN_EXERCISE_DESCRIPTION,
				DatabaseHelper.COLUMN_EXERCISE_TYPE
		};
		
		// the xml defined values the data will be bound to
		int[] to = new int[] {
				R.id.li_exerciseName,
				R.id.li_exerciseDescription,
				R.id.li_exerciseType
		};
		//TODO: use a loader - http://developer.android.com/guide/components/loaders.html
		cursorAdapter = new SimpleCursorAdapter(
				getActivity(),
				R.layout.exercise_listitem,
				cursor,
				columns,
				to);
		
		setListAdapter(cursorAdapter);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.exercise_menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_exercise:
			NewExerciseDialogFragment dialog = new NewExerciseDialogFragment();
			dialog.setTargetFragment(this, 1);
			dialog.show(getFragmentManager(), "NewExerciseTag");
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	public void onExerciseCreated(Exercise exercise) {
		final Exercise e = exercise;
		Runnable r = new Runnable() {
			@Override
			public void run() {
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				ContentValues values = new ContentValues();
				values.put(DatabaseHelper.COLUMN_EXERCISE_NAME, e.getName());
				values.put(DatabaseHelper.COLUMN_EXERCISE_DESCRIPTION, e.getDescription());
				values.put(DatabaseHelper.COLUMN_EXERCISE_TYPE, e.getType().name());
				
				db.insert(DatabaseHelper.TABLE_EXERCISES_NAME, null, values);
			}
		};
		r.run();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
	
	
	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
}
