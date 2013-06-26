package com.kbdavid15.getjacked.workouts;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author Kyle
 *
 */
public class Workout {
	private String title;
	private ArrayList<Exercise> exercises;
	private Date workoutDate;
	private long programId;
	
	public Workout(String title, ArrayList<Exercise> exercises) {
		this.title = title;
		this.exercises = exercises;
	}
	public Workout(String title, ArrayList<Exercise> exercises, Date workoutDate) {
		this.title = title;
		this.exercises = exercises;
		this.workoutDate = workoutDate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<Exercise> getExercises() {
		return exercises;
	}
	public void setExercises(ArrayList<Exercise> exercises) {
		this.exercises = exercises;
	}
	public Date getWorkoutDate() {
		return workoutDate;
	}
	public void setWorkoutDate(Date workoutDate) {
		this.workoutDate = workoutDate;
	}
	public long getProgramId() {
		return programId;
	}
	public void setProgramId(long programId) {
		this.programId = programId;
	}
}
