package com.tekzee.racp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteDB extends SQLiteOpenHelper {
    private static String tag= SqliteDB.class.getSimpleName();
    private String notes = "";
    private static final String DB_NAME = "RACP";

    public SqliteDB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Tables.table1);
        db.execSQL(Tables.table2);
        db.execSQL(Tables.table3);
        db.execSQL(Tables.table4);
        db.execSQL(Tables.table5);
        db.execSQL(Tables.table6);
        db.execSQL(Tables.table_sidemenu);
        db.execSQL(Tables.table_homedata);
        db.execSQL(Tables.table_mtg_group);
        db.execSQL(Tables.table_mtg_member);
        /*db.execSQL(table3);
        db.execSQL(table4);
        db.execSQL(table5);
        db.execSQL(table6);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("drop table if exists "+dbConstant.Table_Form1);
    db.execSQL("drop table if exists "+dbConstant.Table_Form2);
    db.execSQL("drop table if exists "+dbConstant.Table_BakriDetail);
    db.execSQL("drop table if exists "+dbConstant.Table_CleamMilkKit);
    db.execSQL("drop table if exists "+dbConstant.Table_KuttiMachine);
    db.execSQL("drop table if exists "+dbConstant.Table_HomeData);
    db.execSQL("drop table if exists "+dbConstant.Table_SideMenu);
    db.execSQL("drop table if exists "+dbConstant.Table_MtgGroup);
    db.execSQL("drop table if exists "+dbConstant.Table_MtgMember);

    }


    public boolean addMtgGroup( Integer id, String name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.mtg_groupId,id);
        cv.put(dbConstant.mtg_groupName,name);
        Log.e(tag,"addMtgGroup , "+id+" ,"+name);
        long a =  sqLiteDatabase.insert(dbConstant.Table_MtgGroup,null,cv);
        if (a==-1)
            return false;
        else
            return true;
    }
    public Cursor getMtgGroup(){
        Log.e(tag,"getMtgGroup");
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_MtgGroup,null);

        return cursor;

    }
    public void clearMtgGroup() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_MtgGroup);
        db.close();

        Log.e(tag,"clearMtgGroup");
    }

    public boolean addMtgMember( Integer groupId,Integer memberId, String name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.mtg_groupId,groupId);
        cv.put(dbConstant.mtg_memberId,memberId);
        cv.put(dbConstant.mtg_memberName,name);

        Log.e(tag,"addMtgMember"+groupId+" ,"+memberId+" ,"+name);
        long a =  sqLiteDatabase.insert(dbConstant.Table_MtgMember,null,cv);
        if (a==-1)
            return false;
        else
            return true;
    }
    public Cursor getMtgMember(Integer groupId){
        Log.e(tag,"getMtgMember");
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_MtgMember +" where mtg_groupId = ?",new String[]{String.valueOf(groupId)});

        return cursor;

    }
    public void clearMtgMember() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_MtgMember);
        db.close();

        Log.e(tag,"clearMtgMember");
    }


    public boolean addHomeData(int id, String name1, String image1){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.id,id);
        cv.put(dbConstant.menu_name,name1);
        cv.put(dbConstant.menu_image,image1);

        Log.e(tag,"addHomeData");
        long a =  sqLiteDatabase.insert(dbConstant.Table_HomeData,null,cv);
        if (a==-1)
            return false;
        else
            return true;
    }
    public Cursor getHomeData(){
        Log.e(tag,"getHomeData");
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_HomeData,null);

        return cursor;

    }
    public void clearHomeData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_HomeData);
        db.close();

        Log.e(tag,"clearHomeData clear");
    }

    public boolean addSideMenu(String name, String image){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.menu_name,name);
        cv.put(dbConstant.menu_image,image);

        long a =  sqLiteDatabase.insert(dbConstant.Table_SideMenu,null,cv);
        if (a==-1)
            return false;
        else
            return true;
    }
    public Cursor getSideMenu(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_SideMenu,null);

        return cursor;

    }
    public void clearSideMenu() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_SideMenu);
        db.close();

        Log.e(tag,"form1 clear");
    }


    public boolean addfrom1(Integer record, String tag , String phy_date,int group_id,int member_id){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.record_no,record);
        cv.put(dbConstant.Tag_no,tag);
        cv.put(dbConstant.phy_proof_date,phy_date);

        cv.put(dbConstant.mtg_groupId,group_id);
        cv.put(dbConstant.mtg_memberId,member_id);

       long a =  sqLiteDatabase.insert(dbConstant.Table_Form1,null,cv);
        if (a==-1)
            return false;
        else
        return true;
    }

    public Cursor showform1(int record){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_Form1+" where record_no = ?",new String[]{String.valueOf(record)});
        return cursor;

    }

    public int form1count(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_Form1,null);
        return cursor.getCount();
    }

    public void clearform1() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_Form1);
        db.close();

        Log.e(tag,"form1 clear");
    }



    public boolean addfrom2(Integer record, Integer tag , String age, String average, String nasl, String note,int group_id,int member_id){

        if (note.isEmpty()){
            note = notes;
        }
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.record_no,record);
        cv.put(dbConstant.Tag_no,tag);
       cv.put(dbConstant.age,age);
        cv.put(dbConstant.avrage_milk_production,average);
        cv.put(dbConstant.nasl,nasl);
        cv.put(dbConstant.note,note);
        cv.put(dbConstant.mtg_groupId,group_id);
        cv.put(dbConstant.mtg_memberId,member_id);

        long a =  sqLiteDatabase.insert(dbConstant.Table_Form2,null,cv);
        if (a==-1)
            return false;
        else
            return true;
    }

    public int form2count(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_Form2,null);
        return cursor.getCount();

    }
    public Cursor showform2(int record){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_Form2+" where record_no = ?",new String[]{String.valueOf(record)});

        return cursor;

    }
    public void clearform2() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_Form2);
        db.close();

        Log.e(tag,"form2 clear");

    }


    public boolean addBakariDetail(Integer record, Integer tag , String average , String phy_date, String ppr_date, String et_date, String fmd_date, String hs_date,String note,int groupid,int memberid){

        if (note.isEmpty()){
            note = notes;
        }
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.record_no,record);
        cv.put(dbConstant.Tag_no,tag);
        cv.put(dbConstant.avrage_milk_production,average);
        cv.put(dbConstant.phy_proof_date,phy_date);
        cv.put(dbConstant.pprdate,ppr_date);
        cv.put(dbConstant.etdate,et_date);
        cv.put(dbConstant.fmddate,fmd_date);
        cv.put(dbConstant.hsdate,hs_date);
        cv.put(dbConstant.note,note);
        cv.put(dbConstant.mtg_groupId,groupid);
        cv.put(dbConstant.mtg_memberId,memberid);

        long a =  sqLiteDatabase.insert(dbConstant.Table_BakriDetail,null,cv);
        if (a==-1)
            return false;
        else
            return true;
    }

    public Cursor getBakariDetail(int record){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_BakriDetail+" where record_no = ?",new String[]{String.valueOf(record)});

        return cursor;

    }

    public int countBakriDetail(){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_BakriDetail,null);

        return cursor.getCount();

    }

    public void clearBakariDetail() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_BakriDetail);
        db.close();
        Log.e(tag,"clearBakariDetail clear");

    }

    public boolean addCleanMilkKit(Integer record, Boolean proof , Boolean use , Boolean availebility, String date, Boolean test_result, String before, String after,String note){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.record_no,record);
        cv.put(dbConstant.phy_proof,proof);
        cv.put(dbConstant.use,use);
        cv.put(dbConstant.availebility,availebility);
        cv.put(dbConstant.date_test,date);
        cv.put(dbConstant.result_test,test_result);
        cv.put(dbConstant.before_use,before);
        cv.put(dbConstant.after_use,after);
        cv.put(dbConstant.note,note);

        long a =  sqLiteDatabase.insert(dbConstant.Table_CleamMilkKit,null,cv);
        if (a==-1)
            return false;
        else
            return true;
    }

    public Cursor getCleanMilkKit(int record){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_CleamMilkKit+" where record_no = ?",new String[]{String.valueOf(record)});

        return cursor;

    }

    public void clearCleanMilkKit() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_CleamMilkKit);
        db.close();
        Log.e(tag,"form2 clear");

    }

    public boolean addKuttiMachine(Integer record, Boolean proof , Boolean use ,String note){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(dbConstant.record_no,record);
        cv.put(dbConstant.phy_proof,proof);
        cv.put(dbConstant.use,use);
        cv.put(dbConstant.note,note);

        long a =  sqLiteDatabase.insert(dbConstant.Table_KuttiMachine,null,cv);
        if (a==-1)
            return false;
        else
            return true;
    }

    public Cursor getKuttiMachine(int record){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor;
        cursor = sqLiteDatabase.rawQuery("select * from "+dbConstant.Table_KuttiMachine+" where record_no = ?",new String[]{String.valueOf(record)});

        return cursor;

    }

    public void clearKuttiMachine() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ dbConstant.Table_KuttiMachine);
        db.close();
        Log.e(tag,"form2 clear");

    }

}
