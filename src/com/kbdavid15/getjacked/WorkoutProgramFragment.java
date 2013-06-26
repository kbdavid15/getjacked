package com.kbdavid15.getjacked;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.kbdavid15.getjacked.workouts.DatabaseHelper;

public class WorkoutProgramFragment extends ListFragment {
	private static final int NEWPROGRAM_DIALOG_REQUEST = 0x00;
	public static final String PROGRAM_CURSOR_ID = "program_cursor_id";
	
	private SimpleCursorAdapter cursorAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.program_menu, menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add_program:
			// show the add program dialog
			NewProgramDialogFragment dialog = new NewProgramDialogFragment();
			dialog.setTargetFragment(this, NEWPROGRAM_DIALOG_REQUEST);
			dialog.show(getFragmentManager(), "NewProgramTag");
			break;
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.workoutprogram_fragment_layout, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// populate the listview with the contents of the exercise database
		Cursor cursor = DatabaseHelper.getInstance(getActivity()).getPrograms();
		
		// The columns to be bound
		String[] columns = new String[] {
				DatabaseHelper.COLUMN_PROGRAM_TITLE
		};
		
		// the xml defined values the data will be bound to
		int[] to = new int[] {
				android.R.id.text1
		};
		//TODO: use a loader - http://developer.android.com/guide/components/loaders.html
		cursorAdapter = new SimpleCursorAdapter(
				getActivity(),
				android.R.layout.simple_list_item_1,
				cursor,
				columns,
				to);
		
		setListAdapter(cursorAdapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// get the selected program
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
		// start the WorkoutFragment, passing it the id of the selected program
		Fragment workout = new WorkoutFragment();
		Bundle bundle = new Bundle();
		bundle.putLong(PROGRAM_CURSOR_ID, cursor.getLong(0));
		workout.setArguments(bundle);
		getFragmentManager().beginTransaction().replace(R.id.content_frame, workout).commit();
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case NEWPROGRAM_DIALOG_REQUEST:
			if (resultCode == Activity.RESULT_OK) {
				cursorAdapter.changeCursor(DatabaseHelper.getInstance(getActivity()).getPrograms());
			}
			break;
		}
	}
}