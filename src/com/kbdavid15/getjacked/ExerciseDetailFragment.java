package com.kbdavid15.getjacked;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kbdavid15.getjacked.dummy.DummyContent;
import com.kbdavid15.getjacked.workouts.DatabaseHelper;
import com.kbdavid15.getjacked.workouts.ExerciseSet;

/**
 * A fragment representing a single Exercise detail screen. This fragment is
 * either contained in a {@link ExerciseListActivity} in two-pane mode (on
 * tablets) or a {@link ExerciseDetailActivity} on handsets.
 */
public class ExerciseDetailFragment extends Fragment {
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_EXERCISE_ID = "exercise_id";
	
	private ArrayList<ExerciseSet> mSets;

	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public ExerciseDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey(ARG_EXERCISE_ID)) {
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mSets = DatabaseHelper.getInstance(getActivity()).getSets(getArguments().getLong(ARG_EXERCISE_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_exercise_detail,
				container, false);

		// Show the dummy content as text in a TextView.
		if (!mSets.isEmpty()) {
			// create a new set layout for each set in the list
			for (ExerciseSet set : mSets) {
				TextView t = new TextView(inflater.getContext());
				t.setText("Reps: " + set.getTargetReps());
				container.addView(t);
			}
		}

		return rootView;
	}
}
