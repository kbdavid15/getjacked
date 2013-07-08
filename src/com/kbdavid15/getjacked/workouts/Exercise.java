package com.kbdavid15.getjacked.workouts;

public class Exercise {
	private String name;
	private ExerciseType type;
	private String description;
	private long workoutId;
	
	public Exercise(ExerciseType type, String name) {
		this.name = name;
		this.type = type;
	}
	public Exercise(ExerciseType type, String name, String description) {
		this.type = type;
		this.name = name;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ExerciseType getType() {
		return type;
	}
	public void setType(ExerciseType type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(long workoutId) {
		this.workoutId = workoutId;
	}
}
