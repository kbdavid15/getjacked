package com.kbdavid15.getjacked;

import com.kbdavid15.getjacked.workouts.WorkoutProgram;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class WorkoutProgramFragment extends Fragment {
	private static final int NEW_PROGRAM_DIALOG = 2;
	
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
			dialog.setTargetFragment(this, NEW_PROGRAM_DIALOG);
			dialog.show(getFragmentManager(), "NewProgramTag");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	
	/**
	 * 
	 * @param program
	 */
	public void onProgramAdded(WorkoutProgram program) {
		// submit the program into the database
		
	}
}