package com.kbdavid15.getjacked.workouts;

import java.util.Date;

import android.database.Cursor;

public class ExerciseSet {	
	private int targetReps;
	private int targetWeight;
	private Date targetDuration;
	private int reps;
	private int weight;
	private Date duration;
	private long exerciseId;
	
	// column values for cursor with all columns
	public static final int C_ID = 0;
	public static final int C_EXERCISE_ID = 1;
	public static final int C_TARGET_REPS = 2;
	public static final int C_TARGET_WEIGHT = 3;
	public static final int C_TARGET_DURATION = 4;
	public static final int C_REPS = 5;
	public static final int C_WEIGHT = 6;
	public static final int C_DURATION = 7;

	public ExerciseSet(int targetReps, int targetWeight) {
		this.targetReps = targetReps;
		this.targetWeight = targetWeight;
	}
	public ExerciseSet(Cursor cursor) {
		this.exerciseId = cursor.getLong(C_EXERCISE_ID);
		this.targetReps = cursor.getInt(C_TARGET_REPS);
		this.targetWeight = cursor.getInt(C_TARGET_WEIGHT);
		this.targetDuration = new Date(cursor.getLong(C_TARGET_DURATION));
		this.reps = cursor.getInt(C_REPS);
		this.weight = cursor.getInt(C_WEIGHT);
		this.duration = new Date(cursor.getLong(C_DURATION));
	}
	public int getReps() {
		return reps;
	}
	public void setReps(int reps) {
		this.reps = reps;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getTargetReps() {
		return targetReps;
	}
	public void setTargetReps(int targetReps) {
		this.targetReps = targetReps;
	}
	public int getTargetWeight() {
		return targetWeight;
	}
	public void setTargetWeight(int targetWeight) {
		this.targetWeight = targetWeight;
	}
	public Date getDuration() {
		return duration;
	}
	public void setDuration(Date duration) {
		this.duration = duration;
	}
	public void setDuration(long millis) {
		this.duration = new Date(millis);
	}
	public Date getTargetDuration() {
		return targetDuration;
	}
	public void setTargetDuration(Date targetDuration) {
		this.targetDuration = targetDuration;
	}
	public void setTargerDuration(long millis) {
		this.targetDuration = new Date(millis);
	}
	public long getExerciseId() {
		return exerciseId;
	}
	public void setExerciseId(long exerciseId) {
		this.exerciseId = exerciseId;
	}
}
