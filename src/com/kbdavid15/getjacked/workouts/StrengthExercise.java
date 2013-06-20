package com.kbdavid15.getjacked.workouts;

public class StrengthExercise extends Exercise {
	private int reps;
	private float weight;
	
	public StrengthExercise(String name, int sets, int reps, float weight) {
		super(name, sets);
	}

	public int getReps() {
		return reps;
	}

	public void setReps(int reps) {
		this.reps = reps;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}
}
