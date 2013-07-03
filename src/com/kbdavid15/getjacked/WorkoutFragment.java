package com.kbdavid15.getjacked;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.kbdavid15.getjacked.workouts.DatabaseHelper;

public class WorkoutFragment extends ListFragment implements IFragmentPosition {
	private static final int NEWWORKOUT_DIALOG_REQUEST = 0;
	public static final String WORKOUT_ID = "workout_id";
	private static final int EXISTINGWORKOUT_DIALOG_REQUEST = 1;
	private long programId;
	private Cursor mCursor;
	private CursorAdapter cursorAdapter;
	private TextView emptyText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.workout_fragment, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// determine whether to display workouts for a specific program
		Bundle b = getArguments();
		if (b == null) {
			mCursor = DatabaseHelper.getInstance(getActivity()).getWorkouts();	
		} else {
			programId = b.getLong(WorkoutProgramFragment.PROGRAM_CURSOR_ID);
			mCursor = DatabaseHelper.getInstance(getActivity()).getWorkouts(programId);
		}
		
		// check if cursor is null
		if (mCursor == null) {
			setEmptyText("Please add or create a workout");
		} else {
			setEmptyText(null);
		}
		
		cursorAdapter = new SimpleCursorAdapter(
				getActivity(),
				android.R.layout.simple_list_item_1,
				mCursor,
				new String[] { DatabaseHelper.COLUMN_WORKOUT_NAME },
				new int[] { android.R.id.text1 });
		
		setListAdapter(cursorAdapter);		
	}
	
	@Override
	public void setEmptyText(CharSequence text) {
		if (emptyText == null) {
			emptyText = (TextView)getView().findViewById(R.id.empty);
		}		
		if (text != null) {
			emptyText.setText(text);
		} else {
			emptyText.setText("");
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.workout_menu, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_new_workout:
			// create a new workout dialog
			NewWorkoutDialogFragment dialog = new NewWorkoutDialogFragment();
			dialog.setTargetFragment(this, NEWWORKOUT_DIALOG_REQUEST);
			dialog.show(getFragmentManager(), "NewWorkoutTag");
			return true;
		case R.id.action_add_existing_workout:
			//TODO: show a dialog populated with a list of workouts
			ExistingWorkoutDialogFragment existingDialog = new ExistingWorkoutDialogFragment();
			existingDialog.setTargetFragment(this, EXISTINGWORKOUT_DIALOG_REQUEST);
			existingDialog.show(getFragmentManager(), "ExistingWorkoutTag");
			return true;			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case NEWWORKOUT_DIALOG_REQUEST:
			if (resultCode == Activity.RESULT_OK) {
				cursorAdapter.changeCursor(DatabaseHelper.getInstance(getActivity()).getWorkouts(programId));
			}
			break;
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// get the Cursor to the selected workout
		Cursor cursor = (Cursor)l.getItemAtPosition(position);
//		// start the ExerciseFragment, passing it the id of the selected workout
//		Fragment exerciseFragment = new ExerciseFragment();
//		Bundle bundle = new Bundle();
//		bundle.putLong(WORKOUT_ID, cursor.getLong(0));
//		exerciseFragment.setArguments(bundle);
//		((MainActivity)getActivity()).switchFragment(exerciseFragment, "Exercise", MainActivity.EXERCISE_POSITION, true);
		
		// start the ExerciseListActivity
		Intent intent = new Intent(getActivity(), ExerciseListActivity.class);
		intent.putExtra(WORKOUT_ID, cursor.getLong(0));
		startActivity(intent);
	}
	public long getProgramId() {
		return programId;
	}
	public void setProgramId(long programId) {
		this.programId = programId;
	}
	@Override
	public int getFragmentPosition() {
		return MainActivity.WORKOUT_POSITION;
	}
}
