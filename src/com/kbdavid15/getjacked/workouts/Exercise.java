package com.kbdavid15.getjacked.workouts;

import java.util.ArrayList;

public class Exercise {
	private String name;
	private String description;
	private ArrayList<ExerciseSet> setList;
	private ExerciseType type;

	public Exercise(String name, ExerciseType type, ArrayList<ExerciseSet> sets) {
		this.name = name;
		this.setType(type);
		this.setList = sets;
		this.description = null;
	}
	public Exercise(String name, ExerciseType type, String description, ArrayList<ExerciseSet> sets) {
		this.name = name;
		this.setType(type);
		this.description = description;
		this.setList = sets;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}
