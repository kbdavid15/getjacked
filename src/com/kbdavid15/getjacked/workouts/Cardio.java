package com.kbdavid15.getjacked.workouts;

import java.util.Date;

public class Cardio extends Exercise {
	private Date duration;

	public Cardio(String name, int sets) {
		super(name, sets);
	}

	public Date getDuration() {
		return duration;
	}

	public void setDuration(Date duration) {
		this.duration = duration;
	}
}
