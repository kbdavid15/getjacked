package com.kbdavid15.getjacked;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.kbdavid15.getjacked.workouts.DatabaseHelper;
import com.kbdavid15.getjacked.workouts.Exercise;
import com.kbdavid15.getjacked.workouts.ExerciseType;

public class NewExerciseDialogFragment extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// inflate the view for the dialog
		View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_new_exercise, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.title_activity_new_exercise);
		builder.setView(view);
		builder.setPositiveButton(R.string.action_ok, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// get the input from the dialog
				RadioButton strengthRb = (RadioButton)((AlertDialog)dialog).findViewById(R.id.radioButtonStrength);
				ExerciseType type = strengthRb.isChecked() ? ExerciseType.EXERCISE_STRENGTH : ExerciseType.EXERCISE_AEROBIC;
			    String name = ((EditText)((AlertDialog)dialog).findViewById(R.id.editTextExerciseName)).getText().toString();
			    String description = ((EditText)((AlertDialog)dialog).findViewById(R.id.editTextDescription)).getText().toString();
			    
			    long id = ((ExerciseFragment)getTargetFragment()).getWorkoutId();			    
			    
			    Exercise exercise = new Exercise(type, name, description);
			    exercise.setWorkoutId(id);
			    
			    // insert the exercise into the database asynchronously
			    new AsyncInsertExercise().execute(exercise);
			}
		});
		builder.setNegativeButton(R.string.action_cancel, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// user canceled the dialog
			}
		});
		
		return builder.create();
	}
//TODO: Bring back the NewExercise Activity and have a section to add sets to the exercise.
	
	private class AsyncInsertExercise extends AsyncTask<Exercise, Integer, List<Long>> {

		@Override
		protected List<Long> doInBackground(Exercise... exercises) {
			// inserts the programs into the database
			ArrayList<Long> exerciseIDs = new ArrayList<Long>();
			for (int i = 0; i < exercises.length; i++) {
				long id = DatabaseHelper.getInstance(getActivity()).insertExercise(exercises[i]);
				exerciseIDs.add(id);
			}
			DatabaseHelper.closeDatabase();
			return exerciseIDs;
		}
		@Override
		protected void onPostExecute(List<Long> result) {
			// call on activityresult
		    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, null);
		}
	}
}
