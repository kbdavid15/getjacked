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
	private static final int DB_VERSION = 4;
	
	/**
	 * 
	 */
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
	public static final String COLUMN_WORKOUT_DATE = "date";
	/** The id of the WorkoutProgram each Workout corresponds to */
	public static final String COLUMN_WORKOUTPROGRAM_ID = "program_id";
	
	private static final String CREATE_WORKOUT_TABLE = 
			"create table if not exists " + TABLE_WORKOUTS_NAME + "(" +
					BaseColumns._ID + " integer primary key autoincrement, " +
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
	
	public Cursor getExercises() {
		Cursor cursor = getReadableDatabase().query(
				TABLE_EXERCISES_NAME,
				new String[] { BaseColumns._ID, COLUMN_EXERCISE_NAME, COLUMN_EXERCISE_DESCRIPTION, COLUMN_EXERCISE_TYPE },
				null, null, null, null, null);
		
		if (cursor != null)
			cursor.moveToFirst();
		
		return cursor;
	}
	
	public long insertProgram(WorkoutProgram program) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_PROGRAM_TITLE, program.getTitle());
		
		return getWritableDatabase().insert(
				TABLE_WORKOUT_PROGRAM_NAME,
				null,
				values);
	}
	
	public long insertExercise(Exercise exercise) {
		ContentValues values = new ContentValues();
		values.put(COLUMN_EXERCISE_NAME, exercise.getName());
		values.put(COLUMN_EXERCISE_DESCRIPTION, exercise.getDescription());
		values.put(COLUMN_EXERCISE_TYPE, exercise.getType().name());
		
		return getWritableDatabase().insert(
				TABLE_EXERCISES_NAME,
				null,
				values);
	}
}
