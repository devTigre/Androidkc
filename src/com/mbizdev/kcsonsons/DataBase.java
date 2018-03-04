package com.mbizdev.kcsonsons;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
 
public class DataBase {

//	private Context c;
 	public SQLiteDatabase mDb;
    
 	private Context mContext;
    private DataBaseHelper mDbHelper; 

 	
    public DataBase(Context context)  
    { 
        this.mContext = context; 
        mDbHelper = new DataBaseHelper(mContext); 
    } 




public ArrayList<Opening> SelectOpening() {
	
	ArrayList<Opening> OpeningList = new ArrayList<Opening>();
	
	 
 	   String [] columns = 	{"day","open1","close1","open2", "close2"};

	  mDb.beginTransaction();
 	try { 
	   Cursor cr = mDb.rawQuery("select * from Opening", null); 
	   //int idCol = cr.getColumnIndex("rowid");
	   int dayCol = cr.getColumnIndex("day"); 
	   int open1Col = cr.getColumnIndex("open1"); 
	   int close1Col = cr.getColumnIndex("close1"); 
	   int open2Col = cr.getColumnIndex("open2"); 
	   int close2Col = cr.getColumnIndex("close2");


	   while (cr.moveToNext()) { 
		//  columns[0] = cr.getString(idCol);    
	    columns[0] = cr.getString(dayCol); 
	    columns[1] = cr.getString(open1Col); 
	    columns[2] = cr.getString(close1Col);
	    columns[3] = cr.getString(open2Col); 
	    columns[4] = cr.getString(close2Col); 

	      // add data as objects to array 
	   OpeningList.add(new Opening( columns[0], columns[1],columns[2],columns[3],columns[4])); 
	   // Log.v("success", "open selected");
	   
	   }
	   if (cr != null) {
    		 cr.close();
         }
		 cr.close();
	 
   	   } catch (Exception e) {   //Log.v("failure", "opening not selected");
    	 } 
       	mDb.endTransaction();
       	closeDatabase(); // new 29Jan2016
      // 	Log.v("success", "open selected");
       	
  		 return  OpeningList;
    	       }

		public void closeDatabase() {
			mDb.close();	
		}    

} //end