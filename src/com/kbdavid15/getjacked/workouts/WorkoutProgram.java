package com.kbdavid15.getjacked.workouts;

import java.util.Date;

/**
 * This class contains a collection of workouts that are scheduled
 * for specific days
 * @author Kyle
 */
public class WorkoutProgram {
	private String title;
	private Date startDate;
	
	public WorkoutProgram(String title) {
		this.title = title;
	}
	public WorkoutProgram(String title, Date startDate) {
		this.title = title;
		this.startDate = startDate;
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
}
