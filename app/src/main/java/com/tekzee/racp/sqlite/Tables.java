package com.tekzee.racp.sqlite;

public class Tables {


    public static String table_sidemenu = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_SideMenu + "("
            + dbConstant.menu_name + " TEXT ,"
            + dbConstant.menu_image + " TEXT " + ")";

    public static String table_homedata = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_HomeData + "("
            + dbConstant.id + " INTEGER ,"
            + dbConstant.menu_name + " TEXT ,"
            + dbConstant.menu_image + " TEXT " + ")";




    public static String table1 = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_Form1 + "("
            + dbConstant.record_no + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + dbConstant.Tag_no + " TEXT ,"
            + dbConstant.phy_proof_date + " TEXT ,"
            + dbConstant.pprdate + " TEXT ,"
            + dbConstant.etdate + " TEXT ,"
            + dbConstant.fmddate + " TEXT ,"
            + dbConstant.hsdate + " TEXT ,"
            + dbConstant.mtg_groupId + " INTEGER ,"
            + dbConstant.mtg_memberId + " INTEGER " + ")";



    public static String table2 = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_Form2 + "("
            + dbConstant.record_no + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + dbConstant.Tag_no + " TEXT ,"
            + dbConstant.age + " INTEGER ,"
            + dbConstant.avrage_milk_production + " TEXT ,"
            + dbConstant.nasl + " TEXT ,"
            + dbConstant.note + " TEXT ,"
            + dbConstant.mtg_groupId + " INTEGER ,"
            + dbConstant.mtg_memberId + " INTEGER " + ")";

    public static String table3 = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_BakriDetail + "("
            + dbConstant.record_no + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + dbConstant.Tag_no + " INTEGER ,"
            + dbConstant.avrage_milk_production + " TEXT ,"
            + dbConstant.phy_proof_date + " TEXT ,"
            + dbConstant.pprdate + " TEXT ,"
            + dbConstant.etdate + " TEXT ,"
            + dbConstant.fmddate + " TEXT ,"
            + dbConstant.hsdate + " TEXT ,"
            + dbConstant.note + " TEXT ,"
            + dbConstant.mtg_groupId + " INTEGER ,"
            + dbConstant.mtg_memberId + " INTEGER " + ")";

    public static String table4 = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_CleamMilkKit + "("
            + dbConstant.record_no + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + dbConstant.phy_proof + " BOOOLEAN ,"
            + dbConstant.use + " BOOOLEAN ,"
            + dbConstant.availebility + " BOOOLEAN ,"
            + dbConstant.date_test + " TEXT ,"
            + dbConstant.result_test + " BOOOLEAN ,"
            + dbConstant.before_use + " TEXT ,"
            + dbConstant.after_use + " TEXT ,"
            + dbConstant.note + " TEXT " + ")";

    public static String table5 = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_KuttiMachine + "("
            + dbConstant.record_no + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + dbConstant.phy_proof + " BOOOLEAN ,"
            + dbConstant.use + " BOOOLEAN ,"
            + dbConstant.note + " TEXT " + ")";

    public static String table6 = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_Ajola + "("
            + dbConstant.record_no + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + dbConstant.animal_type + " TEXT ,"
            +dbConstant.animal_selection + " INTEGER ,"
            + dbConstant.phy_proof + " BOOOLEAN ,"
            + dbConstant.avrage_milk_production + " TEXT ,"
            + dbConstant.milkamount1 + " TEXT ,"
            + dbConstant.faitpercent1 + " TEXT ,"
            + dbConstant.milkamount2 + " TEXT ,"
            + dbConstant.faitpercent2 + " TEXT " + ")";

    public static String table_mtg_group = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_MtgGroup + "("
            + dbConstant.mtg_groupId + " INTEGER ,"
            + dbConstant.mtg_groupName + " TEXT " + ")";

    public static String table_mtg_member = "CREATE TABLE IF NOT EXISTS " + dbConstant.Table_MtgMember + "("
            + dbConstant.mtg_groupId + " INTEGER ,"
            + dbConstant.mtg_memberId + " INTEGER ,"
            + dbConstant.mtg_memberName+ " TEXT " + ")";


}
