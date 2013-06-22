package com.kbdavid15.getjacked.workouts;

import java.util.ArrayList;

/**
 * 
 * @author Kyle
 *
 */
public class Workout {
	private String title;
	private String description;
	private ArrayList<Exercise> exercises;
	
	public Workout(String title, ArrayList<Exercise> exercises) {
		this.title = title;
		this.description = null;
		this.exercises = exercises;
	}
	public Workout(String title, String description, ArrayList<Exercise> exercises) {
		this.title = title;
		this.description = description;
		this.exercises = exercises;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Exercise> getExercises() {
		return exercises;
	}
	public void setExercises(ArrayList<Exercise> exercises) {
		this.exercises = exercises;
	}
}
