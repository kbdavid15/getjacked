package com.kbdavid15.getjacked.workouts;

import java.util.Date;

/**
 * 
 * @author Kyle
 */
public class Workout {
	private String title;
	private Date workoutDate;
	private long programId;
	
	public Workout(String title) {
		this.title = title;
	}
	public Workout(String title, Date workoutDate) {
		this.title = title;
		this.workoutDate = workoutDate;
	}
	public Workout(String title, long programId) {
		this.title = title;
		this.programId = programId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
