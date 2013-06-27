package com.kbdavid15.getjacked;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProgressFragment extends Fragment implements IFragmentPosition {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.progress, container, false);
	}

	@Override
	public int getFragmentPosition() {
		return MainActivity.PROGRESS_POSITION;
	}

}
