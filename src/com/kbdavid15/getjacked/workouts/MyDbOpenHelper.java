//package com.kbdavid15.getjacked.workouts;
//
//import java.util.ArrayList;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//public class MyDbOpenHelper extends SQLiteOpenHelper {
//	private SQLiteDatabase database;
//	private String[] allColumns = { COLUMN_ID, COLUMN_NAME, COLUMN_TYPE,
//			COLUMN_NUM_SETS, COLUMN_NUM_REPS, COLUMN_WEIGHT };
//	private static final String DB_NAME = "jacked.db";
//	private static final int DB_VERSION = 1;
//	
//	public static final String TABLE_EXERCISES_NAME = "exercises";
//	public static final String COLUMN_ID = "_id";
//	public static final String COLUMN_NAME = "name";
//	/** Enum describing if exercise is cardio or lift or something else */
//	public static final String COLUMN_TYPE = "type";
//	public static final String COLUMN_NUM_SETS = "numsets";
//	public static final String COLUMN_NUM_REPS = "numreps";
//	public static final String COLUMN_WEIGHT = "weight";
//
//	private static final String CREATE_EXERCISE_TABLE = 
//			"create table if not exists " + TABLE_EXERCISES_NAME + "(" +
//					COLUMN_ID + " integer primary key autoincrement, " +
//					COLUMN_NAME + " text not null, " +
//					COLUMN_TYPE + " integer not null, " +
//					COLUMN_NUM_SETS + " ingeger not null, " +
//					COLUMN_NUM_REPS + " integer, " +
//					COLUMN_WEIGHT + " real);";
//	
//	public static final String TABLE_WORKOUTS_NAME = "workout";	
//	public static final String COLUMN_EXERCISE_ID = "elapsedtime";
//	
//	private static final String CREATE_WORKOUT_TABLE = 
//			"create table if not exists " + TABLE_WORKOUTS_NAME + "(" +
//					COLUMN_ID + " integer primary key autoincrement, " + 
//					COLUMN_EXERCISE_ID + " integer not null);";
//
//	public MyDbOpenHelper(Context context) {
//		super(context, DB_NAME, null, DB_VERSION);
//	}
//
//	@Override
//	public void onCreate(SQLiteDatabase db) {
//		db.execSQL(CREATE_GPS_LOCATION_TABLE);
//		db.execSQL(CREATE_VIDEO_LOCATION_TABLE);
//	}
//
//	@Override
//	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		Log.w(MyDbOpenHelper.class.getName(),
//				"Upgrading database from version " + oldVersion + " to "
//						+ newVersion + ", which will destroy all old data");
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_GPS_LOCATION_NAME);
//		db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIDEO_LOCATION);
//		onCreate(db);
//	}
//	
//	public void getAllPointsForFile(String filePath) {
////		ArrayList<LocationCoordinate> points = new ArrayList<LocationCoordinate>();
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
////			points.add(new LocationCoordinate(cursor, vidCursor.getInt(0)));
//			cursor.moveToNext();
//		}
//		database.close();
////		return points;
//	}
//
//	public SQLiteDatabase getDatabase() {
//		return database;
//	}
//	public void setDatabase(SQLiteDatabase database) {
//		this.database = database;
//	}
//}
