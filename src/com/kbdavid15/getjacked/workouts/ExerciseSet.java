package com.kbdavid15.getjacked.workouts;

import java.util.Date;

public class ExerciseSet {	
	private int mTargetReps;
	private int mTargetWeight;
	private int mNumReps;
	private int mWeight;
	private Date mTargetDuration;
	private Date mDuration;
	private long exerciseId;

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
	public Date getDuration() {
		return mDuration;
	}
	public void setDuration(Date duration) {
		this.mDuration = duration;
	}
	public void setDuration(long millis) {
		this.mDuration = new Date(millis);
	}
	public Date getTargetDuration() {
		return mTargetDuration;
	}
	public void setTargetDuration(Date targetDuration) {
		this.mTargetDuration = targetDuration;
	}
	public void setTargerDuration(long millis) {
		this.mTargetDuration = new Date(millis);
	}
	public long getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(long exerciseId) {
		this.exerciseId = exerciseId;
	}
}
