package com.kbdavid15.getjacked;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.kbdavid15.getjacked.workouts.DatabaseHelper;
import com.kbdavid15.getjacked.workouts.Workout;

public class NewWorkoutDialogFragment extends DialogFragment {
	private AlertDialog dialog;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.dialog_new_workout, null);
		EditText et = (EditText)view.findViewById(R.id.dialog_workoutName);
		et.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
				}
			}
		});

		Builder builder = new Builder(getActivity());
		builder.setTitle(R.string.title_activity_new_workout);
		builder.setView(view);
		builder.setPositiveButton(R.string.action_ok, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// get the input from the dialog
				EditText workoutName = (EditText)((AlertDialog)dialog)
						.findViewById(R.id.dialog_workoutName);
				
				long id = ((WorkoutFragment)getTargetFragment()).getProgramId();

				Workout program = new Workout(
						workoutName.getText().toString(), null);
				program.setProgramId(id);

				// insert the program into the database using async task
				new AsyncInsertWorkout().execute(program);
			}
		});
		builder.setNegativeButton(R.string.action_cancel, null);

		InputMethodManager imm = (InputMethodManager)getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(
				view.findViewById(R.id.dialog_programName),
				InputMethodManager.SHOW_IMPLICIT);

		return dialog = builder.create();
	}

	private class AsyncInsertWorkout extends AsyncTask<Workout, Integer, Integer> {

		@Override
		protected Integer doInBackground(Workout... programs) {
			// inserts the programs into the database
			int numberInserted = 0;
			for (int i = 0; i < programs.length; i++) {
				long id = DatabaseHelper.getInstance(getActivity()).insertWorkout(programs[i]);
				if (id >= 0) {
					numberInserted++;
				}	// else an error occurred
			}
			DatabaseHelper.closeDatabase();
			return numberInserted;
		}

		@Override
		protected void onPostExecute(Integer result) {
			// call on activityresult
			getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
		}
	}
}
