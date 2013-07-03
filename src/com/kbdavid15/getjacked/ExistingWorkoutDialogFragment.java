package com.kbdavid15.getjacked;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.kbdavid15.getjacked.workouts.DatabaseHelper;

public class ExistingWorkoutDialogFragment extends DialogFragment {
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.existing_workout_title);
		
		View view = getActivity().getLayoutInflater().inflate(android.R.layout.list_content, null);
		ListView lv = (ListView)view.findViewById(android.R.id.list);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		lv.setOnItemClickListener(new ListView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				((CheckedTextView)view).setChecked(!((CheckedTextView)view).isChecked());
			}
			
		});
		
		builder.setView(view);
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				getActivity(),
				//R.layout.list_fragment_layout,
				android.R.layout.simple_list_item_checked,
				DatabaseHelper.getInstance(getActivity()).getWorkouts(),
				new String[] { DatabaseHelper.COLUMN_WORKOUT_NAME },
				new int[] { android.R.id.text1 } );
		
		lv.setAdapter(adapter);		
		
		builder.setPositiveButton(R.string.action_ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ListView lv = (ListView)((AlertDialog)dialog)
						.findViewById(android.R.id.list);
				Toast.makeText(getActivity(), String.valueOf(lv.getCheckedItemCount()), Toast.LENGTH_SHORT).show();
			}
		}).setNegativeButton(R.string.action_cancel, null);
		
		return builder.create();
	}

}
