package com.kbdavid15.getjacked.workouts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Note: SQLite databases have an implicit column named "rowid"
 * which is an integer primary key
 * @author Kyle
 *
 */	
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DB_NAME = "jacked.db";
	private static final int DB_VERSION = 5;
	
	public static final String TABLE_SETS_NAME = "sets";
	/** The exercise that each set belongs to */
	public static final String COLUMN_EXERCISE_ID = "exercise_id";
	public static final String COLUMN_TARGET_REPS = "target_reps";
	public static final String COLUMN_TARGET_WEIGHT = "target_weight";
	public static final String COLUMN_TARGET_DURATION = "target_duration";
	public static final String COLUMN_REPS = "reps";
	public static final String COLUMN_WEIGHT = "weight";
	public static final String COLUMN_DURATION = "duration";
	
	private static final String CREATE_SETS_TABLE =
			"create table if not exists " + TABLE_SETS_NAME + "(" +
					BaseColumns._ID + " integer primary key autoincrement, " +
					COLUMN_EXERCISE_ID + " integer not null, " +
					COLUMN_TARGET_REPS + " integer, " +
					COLUMN_TARGET_WEIGHT + " integer, " +
					COLUMN_TARGET_DURATION + " integer, " +
					COLUMN_REPS + " integer, " +
					COLUMN_WEIGHT + " integer, " +
					COLUMN_DURATION + " integer);";
	
	/**
	 * The Exercise table contains all the exercises done by the user.
	 * A list of all available exercises (for creating a new workout)
	 * can be realized using "SELECT DISTINCT name FROM exercises"
	 */
	public static final String TABLE_EXERCISES_NAME = "exercises";
	public static final String COLUMN_EXERCISE_NAME = "name";
	public static final String COLUMN_EXERCISE_DESCRIPTION = "description";	
	/** Enum describing if exercise is aerobic or strength or something else */
	public static final String COLUMN_EXERCISE_TYPE = "type";
	/** The id of the workout each exercise corresponds to */
	public static final String COLUMN_WORKOUT_ID = "workout_id";

	private static final String CREATE_EXERCISE_TABLE = 
			"create table if not exists " + TABLE_EXERCISES_NAME + "(" +
					BaseColumns._ID + " integer primary key autoincrement, " +
					COLUMN_EXERCISE_NAME + " text not null, " +
					COLUMN_EXERCISE_DESCRIPTION + " text, " +
					COLUMN_EXERCISE_TYPE + " text not null, " +
					COLUMN_WORKOUT_ID + " integer);";
	
	/**
	 * 
	 */
	public static final String TABLE_WORKOUTS_NAME = "workout";
	public static final String COLUMN_WORKOUT_NAME = "name";
	public static final String COLUMN_WORKOUT_DATE = "date";
	/** The id of the WorkoutProgram each Workout corresponds to */
	public static final String COLUMN_WORKOUTPROGRAM_ID = "program_id";
	
	private static final String CREATE_WORKOUT_TABLE = 
			"create table if not exists " + TABLE_WORKOUTS_NAME + "(" +
					BaseColumns._ID + " integer primary key autoincrement, " +
					COLUMN_WORKOUT_NAME + " text not null, " +
					COLUMN_WORKOUT_DATE + " integer, " +
					COLUMN_WORKOUTPROGRAM_ID + " integer not null);";
	
	
	/**
	 * 
	 */
	public static final String TABLE_WORKOUT_PROGRAM_NAME = "workout_program";
	public static final String COLUMN_PROGRAM_TITLE = "program_title";
	
	private static final String CREATE_WORKOUT_PROGRAM_TABLE = 
			"create table if not exists " + TABLE_WORKOUT_PROGRAM_NAME + "(" +
					BaseColumns._ID + " integer primary key autoincrement, " +
					COLUMN_PROGRAM_TITLE + " text not null);";
	
	
	private static DatabaseHelper mInstance;
	
	public static DatabaseHelper getInstance(Context context) {
		if (mInstance == null)
			mInstance = new DatabaseHelper(context.getApplicationContext());
		return mInstance;
	}
	private DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}
	public static void closeDatabase() {
		mInstance.close();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_SETS_TABLE);
		db.execSQL(CREATE_EXERCISE_TABLE);
		db.execSQL(CREATE_WORKOUT_TABLE);
		db.execSQL(CREATE_WORKOUT_PROGRAM_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETS_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT_PROGRAM_NAME);
		onCreate(db);
	}
	/**
	 * Gets all of the {@link Exercise}s in the exercise table
	 * @return	Cursor pointing to the first {@link Exercise} in the exercises table
	 */
	public Cursor getExercises() {
		Cursor cursor = getReadableDatabase().query(
				TABLE_EXERCISES_NAME,
				new String[] { BaseColumns._ID, COLUMN_EXERCISE_NAME, COLUMN_EXERCISE_DESCRIPTION, COLUMN_EXERCISE_TYPE },
				null, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		return cursor;
	}
	/**
	 * Gets all of the {@link Exercise}s belonging to the {@link Workout}
	 * @param workoutId	The _id of the {@link Workout}
	 * @return Cursor pointing to the first {@link Exercise} in the {@link Workout}
	 */
	public Cursor getExercises(long workoutId) {
		Cursor cursor = getReadableDatabase().query(
				TABLE_EXERCISES_NAME,
				new String[] { BaseColumns._ID, COLUMN_EXERCISE_NAME, COLUMN_EXERCISE_DESCRIPTION, COLUMN_EXERCISE_TYPE },
				COLUMN_WORKOUT_ID + " = " + String.valueOf(workoutId),
				null, null, null, null);
				
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			return cursor;
		} else {
			return null;
		}
	}
	/**
	 * Gets all of the {@link ExerciseSet}s in the sets table
	 * @return	Cursor pointing to the first {@link ExerciseSet} in the sets table
	 */
	public Cursor getSets() {
		Cursor cursor = getReadableDatabase().query(
				TABLE_SETS_NAME,
				new String[] { BaseColumns._ID,
						COLUMN_TARGET_REPS,
						COLUMN_TARGET_WEIGHT,
						COLUMN_TARGET_DURATION,
						COLUMN_REPS,
						COLUMN_WEIGHT,
						COLUMN_DURATION },
				null, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		return cursor;
	}
	/**
	 * Gets all of the {@link ExerciseSet}s belonging to the {@link Exercise}
	 * @param exerciseId	The _id of the {@link Exercise}
	 * @return	Cursor pointing to the first {@link ExerciseSet} in the {@link Exercise}
	 */
	public Cursor getSets(long exerciseId) {
		//TODO: implement this method
		throw new UnsupportedOperationException("Not implemented yet");
	}
	/**
	 * Gets all of the {@link WorkoutProgram}s in the workout_program table
	 * @return	Cursor pointing to the first {@link WorkoutProgram}
	 */
	public Cursor getPrograms() {
		Cursor cursor = getReadableDatabase().query(
				TABLE_WORKOUT_PROGRAM_NAME,
				new String[] { BaseColumns._ID, COLUMN_PROGRAM_TITLE },
				null, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		return cursor;
	}
	/**
	 * Gets all of the {@link Workout}s in the workout table belonging
	 * to the specified {@link WorkoutProgram}
	 * @param programId	The _id of the {@link WorkoutProgram}
	 * @return	Cursor pointing to the first {@link Workout} in the {@link WorkoutProgram}
	 */
	public Cursor getWorkouts(long programId) {
		Cursor cursor = getReadableDatabase().query(
				TABLE_WORKOUTS_NAME,
				new String[] { BaseColumns._ID, COLUMN_WORKOUT_NAME, COLUMN_WORKOUT_DATE },
				COLUMN_WORKOUTPROGRAM_ID + " = " + String.valueOf(programId),
				null, null, null, null);
		
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			return cursor;
		} else {
			return null;
		}
	}
	/**
	 * Gets all of the {@link Workout}s in the workout table
	 * @return	Cursor pointing to the first {@link Workout} in the table
	 */
	public Cursor getWorkouts() {
		Cursor cursor = getReadableDatabase().query(
				TABLE_WORKOUTS_NAME,
				new String[] { BaseColumns._ID, COLUMN_WORKOUT_NAME, COLUMN_WORKOUT_DATE },
				null, null, null, null, null);
		
		if (cursor != null )
			cursor.moveToFirst();
		
		return cursor;
	}
	/**
	 * Gets all of the {@link Workout}s in the workout table
	 * @param unique	If true, this function returns only {@link Workout}s with
	 * 					distinct names.
	 * @return			Cursor pointing to the first distinct {@link Workout} in the table
	 */
	public Cursor getWorkouts(boolean unique) {
		if (unique) {
			Cursor cursor = getReadableDatabase().query(
					true,					// only distinct rows
					TABLE_WORKOUTS_NAME,
					new String[] {BaseColumns._ID, COLUMN_WORKOUT_NAME },
					null,
					null, null, null, null, null);
			
			if (cursor != null)
				cursor.moveToFirst();
			
			return cursor;
		}
		return null;
	}
	/**
	 * Inserts a {@link WorkoutProgram} into the workout_program table
	 * @param program	The {@link WorkoutProgram} to insert 
	 * @return	_id of the inserted program, or -1 if an error occurred
	 */
	public long insertProgram(WorkoutProgram program) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_PROGRAM_TITLE, program.getTitle());
		
		return getWritableDatabase().insert(
				TABLE_WORKOUT_PROGRAM_NAME,
				null,
				values);
	}
	/**
	 * Inserts a {@link Exercise} into the exercises table
	 * @param exercise	The {@link Exercise} to insert 
	 * @return	_id of the inserted exercise, or -1 if an error occurred
	 */
	public long insertExercise(Exercise exercise) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_EXERCISE_NAME, exercise.getName());
		values.put(COLUMN_EXERCISE_DESCRIPTION, exercise.getDescription());
		values.put(COLUMN_EXERCISE_TYPE, exercise.getType().name());
		values.put(COLUMN_WORKOUT_ID, exercise.getWorkoutId());
		
		return getWritableDatabase().insert(
				TABLE_EXERCISES_NAME,
				null,
				values);
	}
	/**
	 * Inserts a {@link Workout} into the workout table
	 * @param workout	The {@link Workout} to insert 
	 * @return	_id of the inserted workout, or -1 if an error occurred
	 */
	public long insertWorkout(Workout workout) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_WORKOUT_NAME, workout.getTitle());
		values.put(COLUMN_WORKOUTPROGRAM_ID, workout.getProgramId());
		
		return getWritableDatabase().insert(
				TABLE_WORKOUTS_NAME,
				null,
				values);
	}
	/**
	 * Inserts a {@link ExerciseSet} into the workout table
	 * @param set	The {@link ExerciseSet} to insert
	 * @return	_id of the inserted set, or -1 if an error occurred
	 */
	public long insertSet(ExerciseSet set) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_TARGET_REPS, set.getTargetReps());
		values.put(COLUMN_TARGET_WEIGHT, set.getTargetWeight());
		values.put(COLUMN_TARGET_DURATION, set.getTargetDuration().getTime());
		values.put(COLUMN_REPS, set.getNumReps());
		values.put(COLUMN_WEIGHT, set.getWeight());
		values.put(COLUMN_DURATION, set.getDuration().getTime());
		values.put(COLUMN_EXERCISE_ID, set.getExerciseId());
		
		return getWritableDatabase().insert(
				TABLE_SETS_NAME,
				null,
				values);
	}
}
