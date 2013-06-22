package com.kbdavid15.getjacked.workouts;

import java.util.Date;

public class ExerciseSet {
	private int mTargetReps;
	private int mTargetWeight;
	private int mNumReps;
	private int mWeight;
	private Date mTargetDuration;
	private Date mDuration;
	
	public ExerciseSet() {}
	
	public ExerciseSet(int targetReps, int targetWeight) {
		this.mTargetReps = targetReps;
		this.mTargetWeight = targetWeight;
	}

	public int getNumReps() {
		return mNumReps;
	}
	public void setNumReps(int numReps) {
		this.mNumReps = numReps;
	}
	public int getWeight() {
		return mWeight;
	}
	public void setWeight(int weight) {
		this.mWeight = weight;
	}
	public int getTargetReps() {
		return mTargetReps;
	}
	public void setTargetReps(int targetReps) {
		this.mTargetReps = targetReps;
	}
	public int getTargetWeight() {
		return mTargetWeight;
	}
	public void setTargetWeight(int targetWeight) {
		this.mTargetWeight = targetWeight;
	}
	public Date getmDuration() {
		return mDuration;
	}
	public void setmDuration(Date mDuration) {
		this.mDuration = mDuration;
	}
	public Date getmTargetDuration() {
		return mTargetDuration;
	}
	public void setmTargetDuration(Date mTargetDuration) {
		this.mTargetDuration = mTargetDuration;
	}
}
