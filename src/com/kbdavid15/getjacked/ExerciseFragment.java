package com.kbdavid15.getjacked;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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

import com.kbdavid15.getjacked.workouts.DatabaseHelper;

public class ExerciseFragment extends ListFragment implements LoaderCallbacks<Cursor> {
	private SimpleCursorAdapter cursorAdapter;
	
	private static final int DIALOG_REQUEST = 0x00;
	private static final int EXERCISE_LOADER_ID = 0x01;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Prepare the loader.  Either re-connect with an existing one,
		// or start a new one.
		//getLoaderManager().initLoader(EXERCISE_LOADER_ID, null, this);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list_fragment_layout, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// populate the listview with the contents of the exercise database
		Cursor cursor = DatabaseHelper.getInstance(getActivity()).getExercises();
		
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
			dialog.setTargetFragment(this, DIALOG_REQUEST);
			dialog.show(getFragmentManager(), "NewExerciseTag");
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case DIALOG_REQUEST:
			if (resultCode == Activity.RESULT_OK) {
				cursorAdapter.changeCursor(DatabaseHelper.getInstance(getActivity()).getExercises());
			}
			break;
		}
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
