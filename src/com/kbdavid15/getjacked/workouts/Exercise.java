package com.kbdavid15.getjacked.workouts;

import java.util.ArrayList;

public class Exercise {
	private String name;
	private ArrayList<ExerciseSet> setList;
	private ExerciseType type;
	private String description;

	public Exercise(ExerciseType type, String name, ArrayList<ExerciseSet> sets) {
		this.name = name;
		this.type = type;
		this.setList = sets;
	}
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
	public ArrayList<ExerciseSet> getSetList() {
		return setList;
	}
	public void setSetList(ArrayList<ExerciseSet> setList) {
		this.setList = setList;
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
}
