package com.getjacked.workoutlog.workouts;

public class Exercise {
	private int sets;
	private String name;

	public Exercise(String name, int sets) {
		this.setName(name);
		this.sets = sets;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
