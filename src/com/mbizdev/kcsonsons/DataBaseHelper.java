package com.mbizdev.kcsonsons;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import android.app.ActivityManager;
//import android.widget.Toast;



	public class DataBaseHelper extends SQLiteOpenHelper 
	{ 
	private static String TAG = "DataBaseHelper"; // Tag just for the LogCat window 
	//destination path (location) of our database on device 
	private static String DB_PATH = "";  
	private static String DB_NAME ="kcs.sqlite";// Database name
        public static int LATEST_DATABASE_VERSION = 10;//updated to 10 aug2017
	//updated to 9 may2017 // updated to 7 2016 jan28// was 4  // 5 has new opening hours
	 public static int OLDER_DATABASE_VERSION = 9;//was 8 //was 6//was4 // was 3

        private String OLD_TABLE_NAME_4 = "kcmenu_oct2016";
	private String OLD_TABLE_NAME_3 = "kcmenu_jan2015";
	private String OLD_TABLE_NAME_1 = "kcmenu_may2017";
	private String NEW_TABLE_NAME_1 = "kcmenu_aug2017";
	private String NEW_TABLE_NAME_2 = "Opening";

    private static final String DATABASE_NAME = "kcs.sqlite";
    private static final int DATABASE_VERSION = 10;  //was 1 premay17 //was 9 preaug2017
    private static final String SP_KEY_DB_VER = "db_ver";
	
	private SQLiteDatabase mDataBase;  
	private final Context mContext; 
	 
	public DataBaseHelper(Context context)  
	{ 
	    super(context, DB_NAME, null, LATEST_DATABASE_VERSION);// 9// 7 its Database Version
	    DB_PATH = "/data/data/" + context.getPackageName() + "/databases/"; 
	    this.mContext = context; 
	    initialize();
	}    
	 
	 /**
     * Initializes database. Creates database if doesn't exist.
     */
    private void initialize() {
        if (databaseExists()) {
            SharedPreferences prefs = PreferenceManager
                    .getDefaultSharedPreferences(mContext);
            int dbVersion = prefs.getInt(SP_KEY_DB_VER, 10); //was 9// was 7 was6 , def was 1
            if (DATABASE_VERSION != dbVersion) {
                File dbFile = mContext.getDatabasePath(DATABASE_NAME);
                if (!dbFile.delete()) {
                    Log.w(TAG, "Unable to update database");
                }
            }
        }
        if (!databaseExists()) {
            createDatabase();
        }
    }
    
    /**
    * Returns true if database file exists, false otherwise.
    * @return
    */
   private boolean databaseExists() {
       File dbFile = mContext.getDatabasePath(DATABASE_NAME);
       return dbFile.exists();
   }
	
   /**
    * Creates database by copying it from assets directory.
    */
   void createDatabase() {
       String parentPath = mContext.getDatabasePath(DATABASE_NAME).getParent();
       String path = mContext.getDatabasePath(DATABASE_NAME).getPath();

       File file = new File(parentPath);
       if (!file.exists()) {
           if (!file.mkdir()) {
               Log.w(TAG, "Unable to create database directory");
               return;
           }
       }

       InputStream is = null;
       OutputStream os = null;
       try {
           is = mContext.getAssets().open(DATABASE_NAME);
           os = new FileOutputStream(path);

           byte[] buffer = new byte[1024];
           int length;
           while ((length = is.read(buffer)) > 0) {
               os.write(buffer, 0, length);
           }
           os.flush();
           SharedPreferences prefs = PreferenceManager
                   .getDefaultSharedPreferences(mContext);
           SharedPreferences.Editor editor = prefs.edit();
           editor.putInt(SP_KEY_DB_VER, DATABASE_VERSION);
           editor.commit();
       } catch (IOException e) {
           e.printStackTrace();
       } finally {
           if (is != null) {
               try {
                   is.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           if (os != null) {
               try {
                   os.close();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   }
	
	

	    public boolean openDataBase() throws SQLException 
	    { 
	        String mPath = DB_PATH + DB_NAME; 
	        //Log.v("mPath", mPath); 
	        mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY); 
	        //mDataBase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.NO_LOCALIZED_COLLATORS); 
	        return mDataBase != null; 
	    } 
	 
	 
	
	    @Override 
	    public synchronized void close()  
	    { 
	        if(mDataBase != null) 
	            mDataBase.close(); 
	        super.close(); 
	    }

		@Override
	public void onCreate(SQLiteDatabase mDataBase) {
		   			 
		}

		@Override
		public void onUpgrade(SQLiteDatabase mDataBase, int oldVersion, int newVersion) {
		}
	
		public void clearApplicationData() {
			File cache = mContext.getCacheDir();
			File appDir = new File(cache.getParent());
			if(appDir.exists()){
				String[] children = appDir.list();
				for(String s : children){
					if(!s.equals("lib")){
						deleteDir(new File(appDir, s));
						Log.i("TAG", "File /data/data/APP_PACKAGE/" + s +" DELETED");
					}
				}
			}
		}
		
		
		public static boolean deleteDir(File dir) {
		    if (dir != null && dir.isDirectory()) {
		        String[] children = dir.list();
		        for (int i = 0; i < children.length; i++) {
		            boolean success = deleteDir(new File(dir, children[i]));
		            if (!success) {
		                return false;
		            }
		        }
		    }

		    return dir.delete();
		}
		
		
		
		
		
} //end
