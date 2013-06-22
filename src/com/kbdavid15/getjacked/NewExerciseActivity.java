package com.kbdavid15.getjacked;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.kbdavid15.getjacked.workouts.ExerciseType;

public class NewExerciseActivity extends FragmentActivity {
	private ExerciseType mExerciseType;
	private EditText mExerciseName;
	private TextView mTextViewNumSets;
	private Button mButtonChooseNumSets;
	private int mNumSets;
	
	private ViewFlipper mViewFlipper;
	private View strengthView;
	private View aerobicView;
	
	private OnClickListener mNumSetsClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// show the NumberPicker dialog and store the result in mEditTextNumSets
			NumSetsDialogFragment dialog = new NumSetsDialogFragment();
			dialog.show(getSupportFragmentManager(), "NUM_SETS_DIALOG");
		}
	};
	
	public static class NumSetsDialogFragment extends DialogFragment {
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// inflate the numberpicker view
			View view = getActivity().getLayoutInflater().inflate(R.layout.number_picker, null);
			final NumberPicker numPicker = (NumberPicker)view;
			numPicker.setMinValue(1);
			numPicker.setMaxValue(10);
			
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setTitle(R.string.sets_dialog_title)
				.setView(view)
				.setPositiveButton(R.string.action_ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						int numSets = numPicker.getValue();
//						TextView tv = (TextView)getActivity().findViewById(R.id.textViewNumSets);
//						tv.setText(String.valueOf(numSets));
					}
				})
				.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// User canceled the dialog
					}
				});
			return builder.create();
		}
	}	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_exercise);
		// Show the Up button in the action bar.
		setupActionBar();

		// get handles on UI elements
		mExerciseName = (EditText)findViewById(R.id.editTextExerciseName);
//		mButtonChooseNumSets = (Button)findViewById(R.id.buttonSelectNumSets);
//		mViewFlipper = (ViewFlipper)findViewById(R.id.exerciseViewFlipper);
		// add event listeners
		mButtonChooseNumSets.setOnClickListener(mNumSetsClickListener);
		
		// since default is strength, inflate the xml and place in the content frame
		strengthView = getLayoutInflater().inflate(R.layout.strength, null);
		aerobicView = getLayoutInflater().inflate(R.layout.aerobic, null);
		
		mViewFlipper.addView(strengthView);
		mViewFlipper.addView(aerobicView);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_exercise, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_accept:
			// store the user input in the database
			if (storeExercise()) {			
				setResult(RESULT_OK);
				finish();
			}
			break;
		case R.id.action_cancel:
			// close the activity discarding all changes
			setResult(RESULT_CANCELED);
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	public boolean storeExercise() {
		if (editTextIsEmpty(mExerciseName)) {
			// make the user enter a value in this field
			Toast.makeText(this, "Please enter a name for the exercise", Toast.LENGTH_SHORT).show();
			return false;
		}
		String exerciseName = mExerciseName.getText().toString();
		//TODO further validate the exercise name to make sure it is unique in the database
		
		
		
		
		
		// the rest is different depending on if aerobic or strength
		
		
		
		// store the values in the database
		
		
		
		return true;
	}

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton)view).isChecked();
		// check which radio button was clicked
		switch (view.getId()) {
		case R.id.radioButtonAerobic:
			if (checked) {
				mExerciseType = ExerciseType.EXERCISE_AEROBIC;
				// load the aerobic xml content
				if (!mViewFlipper.getCurrentView().equals(aerobicView)) {
					mViewFlipper.showNext();
				}
			}
			break;
		case R.id.radioButtonStrength:
			if (checked) {
				mExerciseType = ExerciseType.EXERCISE_STRENGTH;
				if (!mViewFlipper.getCurrentView().equals(strengthView)) {
					mViewFlipper.showNext();
				}
			}
			break;
		}
	}

	private boolean editTextIsEmpty(EditText etText) {
		return etText.getText().toString().trim().length() == 0;
	}
}
