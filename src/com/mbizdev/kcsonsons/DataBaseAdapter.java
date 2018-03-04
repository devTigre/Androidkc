package com.mbizdev.kcsonsons;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

//import android.util.Log;


public class DataBaseAdapter {


	    protected static final String TAG = "DataAdapter"; 
	 
	    private final Context mContext; 
	    private SQLiteDatabase mDataBase; 
	    private DataBaseHelper mDbHelper; 
		//private static String latest_TABLE_NAME ="kcmenu_jan2016"; // latest jan2016" Table name
	 // latest may2017" Table name
	private static String latest_TABLE_NAME ="kcmenu_aug2017"; // latest aug2017" Table name

	public DataBaseAdapter(Context context)
	    { 
	        this.mContext = context; 
	        mDbHelper = new DataBaseHelper(mContext); 
	    } 
	 
	    public DataBaseAdapter createDatabase() throws SQLException  
	    { 
	        mDbHelper.createDatabase();  
	        return this; 
	    } 
	 
	    public DataBaseAdapter open() throws SQLException  
	    { 
	        try  
	        { 
	            mDbHelper.openDataBase();
	            mDbHelper.close(); 
	        //   mDb = mDbHelper.getReadableDatabase(); 
	            mDataBase = mDbHelper.getWritableDatabase();    /// see onUpgrade is auto called !
	            Integer vers = mDataBase.getVersion();
	            Log.e(TAG, vers + "  database version !!!!"); 
 
	        }  
	        catch (SQLException mSQLException)  
	        { 
//	           Log.e(TAG, "open >>"+ mSQLException.toString()); 
	            throw mSQLException; 
	        } 
	        return this; 
	    } 
	 
	    public void close()  
	    { 
	        mDbHelper.close(); 
	    } 
 
		
	     public Cursor getOpenData() 
	     { 
	         try 
	         { 
	             String sql ="SELECT open1 FROM Opening"; 
	 
	             Cursor mCur = mDataBase.rawQuery(sql, null); 
	             if (mCur!=null) 
	             { 
	                mCur.moveToNext(); 
	             } 
	             return mCur; 
	         } 
	         catch (SQLException mSQLException)  
	         { 
	        //     Log.e(TAG, "getTestData >>"+ mSQLException.toString()); 
	             throw mSQLException; 
	         } 
	     }	
	     
	     
	     
	     public ArrayList<Opening> SelectOpening() {

	    	 ArrayList<Opening> OpeningList = new ArrayList<Opening>();


	    	 String [] columns = 	{"day","open1","close1","open2", "close2"};
	    	 mDataBase.beginTransaction();
	    	 try { 
	    		 Cursor cr = mDataBase.rawQuery("select day, open1, close1, open2, close2 from Opening", null); 
	    		 //int idCol = cr.getColumnIndex("rowid");
	    		 int dayCol = cr.getColumnIndex("day"); 
	    		 int open1Col = cr.getColumnIndex("open1"); 
	    		 int close1Col = cr.getColumnIndex("close1"); 
	    		 int open2Col = cr.getColumnIndex("open2"); 
	    		 int close2Col = cr.getColumnIndex("close2");


	    		 while (cr.moveToNext()) { 
	    			 //columns[0] = cr.getString(idCol);    
	    			 columns[0] = cr.getString(dayCol); 
	    			 columns[1] = cr.getString(open1Col); 
	    			 columns[2] = cr.getString(close1Col);
	    			 columns[3] = cr.getString(open2Col); 
	    			 columns[4] = cr.getString(close2Col); 

	    			 // add data as objects to array 
	    			 OpeningList.add(new Opening( columns[0], columns[1],columns[2],columns[3],columns[4])); 
	    		//	 Log.v("success", "open selected");

	    		 }
	    		 if (cr != null) {
	    			 cr.close();
	    		 }
	    		 cr.close();

	    	 } catch (Exception e)
	    	 	{  
	    		 //Log.v("failure", "opening not selected");
	    	 } 
	    	 mDataBase.endTransaction();

	    	// Log.v("end", "transacation");

	    	 return  OpeningList;
	     }

	     
	     public ArrayList<KCMenuItem> SelectMenuCategory(String category) {

    	 ArrayList<KCMenuItem> KCMenuItemList = new ArrayList<KCMenuItem>();

    	 String [] columns = 	{"category","item","flag","orderCount","desc","centerRed","centerBlack","price","centerBig", "smallText"};
    	 mDataBase.beginTransaction();
    	 try { 
    		// Cursor cr = mDb.rawQuery("SELECT * FROM kcmenu WHERE category ='FilledPittas'", null); 
    		 Cursor cr = mDataBase.rawQuery("SELECT *  FROM "+latest_TABLE_NAME+" WHERE category ="+category+"", null); 

    		 int catCol = cr.getColumnIndex("category");
    		 int itemCol = cr.getColumnIndex("item"); 
    		 int flagCol = cr.getColumnIndex("flag");     		 
    		 int orderCountCol = cr.getColumnIndex("orderCount"); 
    		 int descCol = cr.getColumnIndex("desc"); 
    		 int centerRedCol = cr.getColumnIndex("centerRed"); 
    		 int centerBlackCol = cr.getColumnIndex("centerBlack"); 
    		 int priceCol = cr.getColumnIndex("price"); 
    		 int centerBigCol = cr.getColumnIndex("centerBig"); 
    		 int smallTextCol = cr.getColumnIndex("smallText"); 


    		 while (cr.moveToNext()) { 
    			 columns[0] = cr.getString(catCol);    
    			 columns[1] = cr.getString(itemCol); 
    			 columns[2] = cr.getString(flagCol);     			 
    			 columns[3] = cr.getString(orderCountCol);
    			 columns[4] = cr.getString(descCol); 
    			 columns[5] = cr.getString(centerRedCol);
    			 columns[6] = cr.getString(centerBlackCol);
    			 columns[7] = cr.getString(priceCol);
    			 columns[8] = cr.getString(centerBigCol);
    			 columns[9] = cr.getString(smallTextCol);

    		
    			 // add data as objects to array 
    			 KCMenuItemList.add(new KCMenuItem( columns[0], columns[1],columns[2],columns[3],columns[4],columns[5],columns[6],columns[7],columns[8], columns[9] )); 
    		//	 Log.v("success", "Menu selected");

    		 }
    		 if (cr != null) {
    			 cr.close();
    		 }
    		 cr.close();

    	 } catch (Exception e)
    	 	{   
    		 //Log.v("failure", "Menu not selected");
    	 } 
    	 mDataBase.endTransaction();

    	 //Log.v("end", "transacation");

    	 return  KCMenuItemList;
	     }
	     

	     public ArrayList<KCMenuItem> SelectMenuOrder(String order) {

    	 ArrayList<KCMenuItem> KCMenuItemList = new ArrayList<KCMenuItem>();

    	 String [] columns = 	{"category","item", "flag","orderCount","desc","centerRed","centerBlack","price","centerBig","smallText" };
    	 mDataBase.beginTransaction();
    	 try { 
    		// Cursor cr = mDb.rawQuery("SELECT * FROM kcmenu WHERE category ='FilledPittas'", null); 
    		 Cursor cr = mDataBase.rawQuery("SELECT *  FROM "+latest_TABLE_NAME+" WHERE orderCount >0", null); 

    		 int catCol = cr.getColumnIndex("category");
    		 int itemCol = cr.getColumnIndex("item"); 
    		 int flagCol = cr.getColumnIndex("flag"); 
    		 int orderCountCol = cr.getColumnIndex("orderCount"); 
    		 int descCol = cr.getColumnIndex("desc"); 
    		 int centerRedCol = cr.getColumnIndex("centerRed"); 
    		 int centerBlackCol = cr.getColumnIndex("centerBlack"); 
    		 int priceCol = cr.getColumnIndex("price"); 
    		 int centerBigCol = cr.getColumnIndex("centerBig"); 
    		 int smallTextCol = cr.getColumnIndex("smallText"); 


    		 while (cr.moveToNext()) { 
    			 columns[0] = cr.getString(catCol);    
    			 columns[1] = cr.getString(itemCol);
    			 columns[2] = cr.getString(flagCol);
    			 columns[3] = cr.getString(orderCountCol);
    			 columns[4] = cr.getString(descCol); 
    			 columns[5] = cr.getString(centerRedCol);
    			 columns[6] = cr.getString(centerBlackCol);
    			 columns[7] = cr.getString(priceCol);
    			 columns[8] = cr.getString(centerBigCol);
    			 columns[9] = cr.getString(smallTextCol);

    		
    			 // add data as objects to array 
    			 KCMenuItemList.add(new KCMenuItem( columns[0], columns[1],columns[2],columns[3],columns[4],columns[5],columns[6],columns[7], columns[8], columns[9])); 
//    			 Log.v("success", "Order selected");

    		 }
    		 if (cr != null) {
    			 cr.close();
    		 }
    		 cr.close();

    	 } catch (Exception e)
    	 	{   
    	//	Log.v("failure", "Order not selected");
    	 } 
    	 mDataBase.endTransaction();

    	// Log.v("end", "order transacation");

    	 return  KCMenuItemList;
	     }
	     
	     public void UpdateOrderby1(String itemChoice){
	    	 String orderCount="orderCount"; String item="item";
	    	 String sql = "UPDATE "  +latest_TABLE_NAME+ 
	                 " SET " +orderCount+ "=" +orderCount+ "+1" + 
	                 " WHERE "+item+ "="+"'"+itemChoice+"'";

	    mDataBase.execSQL(sql);
	    mDataBase.close(); //new29jan2016
	     }
	     public void UpdateOrderbyless1(String itemChoice){
	    	 String orderCount="orderCount"; String item="item";
	    	 String sql = "UPDATE "  +latest_TABLE_NAME+ 
	                 " SET " +orderCount+ "=" +orderCount+ "-1" + 
	                 " WHERE "+item+ "="+"'"+itemChoice+"'";

	    mDataBase.execSQL(sql);
	    mDataBase.close(); //new29jan2016

	     }	     
	     
	//     public String OrderCount SelectOrderCount(String itemChoice){
	  //  	 String orderCount="orderCount"; String item="item";
	    //	 String sql = "SELECT " +orderCount+ "FROM " +latest_TABLE_NAME+ 
	      //           " WHERE "+item+ "="+"'"+itemChoice+"'";
	    	// SELECT orderCount FROM kcmenu_jan2016 WHERE item == "MEXICAN"
	   // mDataBase.execSQL(sql);
	   //  }	    
	     
	     
	     
	     
	     public void UpdateOrderClear(){
	    	 String orderCount="orderCount";
	    	 String sql = "UPDATE "  +latest_TABLE_NAME+ 
	                 " SET " +orderCount+"=0";

	    mDataBase.execSQL(sql);
	     }	
	     
	     public void UpdateOrder(String itemChoice) {
	    	 // define the new value you want
	    	 ContentValues newValues = new ContentValues();
	    	 newValues.put("orderCount", "orderCount+1");
	    	 // you can .put() even more here if you want to update more than 1 row

	    	 // define the WHERE clause w/o the WHERE and replace variables by ?
	    	 // Note: there are no ' ' around ? - they are added automatically
	    	 String whereClause = "item == ?";

	    	 // now define what those ? should be
	    	 String[] whereArgs = new String[] {
	    	         // in order the ? appear
	    	         itemChoice
	    	 };

	    	 int amountOfUpdatedColumns = mDataBase.update("latest_TABLE_NAME", newValues, whereClause, whereArgs);
//			 Log.v("success", "choice updated by update"+itemChoice+amountOfUpdatedColumns);

	     }
	     
	     public void UpdateCountItem(String itemChoice) {

   // 	 ArrayList<KCMenuItem> KCMenuItemList = new ArrayList<KCMenuItem>();

     	 mDataBase.beginTransaction();
    	 String sqlupdate = "UPDATE "+latest_TABLE_NAME+"  SET orderCount=orderCount+1 WHERE item = '"+itemChoice+"'";
    	 try { 
      		 mDataBase.execSQL(sqlupdate);
 
//    			 Log.v("success", "choice updated"+itemChoice);

    	 } catch (Exception e)
    	 	{   
//    		 Log.v("failure", "item choice not updated");
    	 } 
    	 mDataBase.endTransaction();

//    	 Log.v("end", "transacation");

 	     }  
	   
} //end
