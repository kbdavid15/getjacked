package com.kbdavid15.getjacked.workouts;

import java.util.ArrayList;
import java.util.Date;

/**
 * This class contains a collection of workouts that are scheduled
 * for specific days
 * @author Kyle
 *
 */
public class WorkoutProgram {
	private String title;
	private Date startDate;
	private ArrayList<Workout> workouts;
	
	public WorkoutProgram(String title, ArrayList<Workout> workouts) {
		this.title = title;
		this.workouts = workouts;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public ArrayList<Workout> getWorkouts() {
		return workouts;
	}
	public void setWorkouts(ArrayList<Workout> workouts) {
		this.workouts = workouts;
	}
}
