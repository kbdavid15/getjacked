package com.kbdavid15.getjacked.workouts;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Note: SQLite databases have an implicit column named "rowid"
 * which is an integer primary key
 * @author Kyle
 *
 */
public class MyDbOpenHelper extends SQLiteOpenHelper {
	private SQLiteDatabase database;
	private String[] allColumns = { COLUMN_ID, COLUMN_NAME, COLUMN_TYPE,
			COLUMN_NUM_SETS, COLUMN_NUM_REPS, COLUMN_WEIGHT };
	private static final String DB_NAME = "jacked.db";
	private static final int DB_VERSION = 1;
	
	/**
	 * The Exercise table contains all the exercises done by the user.
	 * A list of all available exercises (for creating a new workout)
	 * can be realized using "SELECT DISTINCT name FROM exercises"
	 */
	public static final String TABLE_EXERCISES_NAME = "exercises";
	public static final String COLUMN_ID = "rowid";
	public static final String COLUMN_NAME = "name";
	/** Enum describing if exercise is aerobic or strength or something else */
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_NUM_SETS = "numsets";
	public static final String COLUMN_NUM_REPS = "numreps";
	public static final String COLUMN_WEIGHT = "weight";

	private static final String CREATE_EXERCISE_TABLE = 
			"create table if not exists " + TABLE_EXERCISES_NAME + "(" +
					COLUMN_NAME + " text not null, " +
					COLUMN_TYPE + " integer not null, " +
					COLUMN_NUM_SETS + " integer not null, " +
					COLUMN_NUM_REPS + " integer, " +
					COLUMN_WEIGHT + " real);";
	
	
	public static final String TABLE_WORKOUTS_NAME = "workout";	
	public static final String COLUMN_EXERCISE_ID = "elapsedtime";
	
	private static final String CREATE_WORKOUT_TABLE = 
			"create table if not exists " + TABLE_WORKOUTS_NAME + "(" +
					COLUMN_EXERCISE_ID + " integer not null);";
	
	
	public static final String TABLE_WORKOUT_PROGRAM_NAME = "workout_program";
	public static final String COLUMN_PROGRAM_NAME = "program_name";
	public static final String COLUMN_WORKOUT_ID = "workout_id";
	public static final String COLUMN_WORKOUT_DATE = "date";
	
	private static final String CREATE_WORKOUT_PROGRAM_TABLE = 
			"create table if not exists " + TABLE_WORKOUT_PROGRAM_NAME + "(" +
					COLUMN_PROGRAM_NAME + " text not null, " +
					COLUMN_WORKOUT_ID + " integer not null, " +
					COLUMN_WORKOUT_DATE + " integer not null);";

	public MyDbOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_EXERCISE_TABLE);
		db.execSQL(CREATE_WORKOUT_TABLE);
		db.execSQL(CREATE_WORKOUT_PROGRAM_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(MyDbOpenHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXERCISES_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUTS_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORKOUT_PROGRAM_NAME);
		onCreate(db);
	}
	
//	public void getAllPointsForFile(String filePath) {
//		ArrayList<LocationCoordinate> points = new ArrayList<LocationCoordinate>();
//		database = getReadableDatabase();
//		
//		// find the data associated with the chosen video
//		String selectCriteria = COLUMN_FILENAME + " = '" + filePath + "'";
//		Cursor cursor = database.query(TABLE_GPS_LOCATION_NAME, allColumns, selectCriteria, null, null, null, null);
//		cursor.moveToFirst();
//		while (!cursor.isAfterLast()) {
//			String selectId = COLUMN_ID + " = " + cursor.getLong(0);
//			Cursor vidCursor = database.query(TABLE_VIDEO_LOCATION, new String[] { COLUMN_VIDEO_ELAPSED }, selectId, null, null, null, null);
//			vidCursor.moveToFirst();
//			points.add(new LocationCoordinate(cursor, vidCursor.getInt(0)));
//			cursor.moveToNext();
//		}
//		database.close();
//		return points;
//	}

	public SQLiteDatabase getDatabase() {
		return database;
	}
	public void setDatabase(SQLiteDatabase database) {
		this.database = database;
	}
}
