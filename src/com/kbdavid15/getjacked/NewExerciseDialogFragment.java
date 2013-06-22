package com.kbdavid15.getjacked;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

public class NewExerciseDialogFragment extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// inflate the view for the dialog
		View view = getActivity().getLayoutInflater().inflate(R.layout.activity_new_exercise, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.title_activity_new_exercise);
		builder.setView(view);
		builder.setPositiveButton(R.string.action_ok, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// get the input from the dialog
				
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
	

}