package ethan.demo.myfirstapplication;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class DBhelper extends SQLiteOpenHelper {
    private final static int VERSION = 1;
    private static String creatable;
    private SQLiteDatabase db;
    private Cursor cursor = null;
    private final static String DATABASE_NAME = "englishstudy.db";
    private static  String DATA_PATH;

    public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        if(android.os.Build.VERSION.SDK_INT >= 4.2){
            DATA_PATH= context.getApplicationInfo().dataDir+"/databases/englishstudy.db";
        } else {
            DATA_PATH="/data/data/"+context.getPackageName()+"/databases/englishstudy.db";
        }
        this.db = SQLiteDatabase.openOrCreateDatabase(DATA_PATH, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
 //       System.out.println("create table");
//        db.execSQL(creatable);
    }

    public void inidb(String creatable) {
        this.creatable = creatable;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String createheadtable(String tablename) {
        return "CREATE TABLE " + tablename + "head ( " +
                "   hindex INTEGER PRIMARY KEY AUTOINCREMENT " +
                "                   NOT NULL " +
                "                   UNIQUE, " +
                "                   hword  VARCHAR NOT NULL" +
                ")";
    }

    public String createvocabularytable(String tablename) {
        return "CREATE TABLE " + tablename + "vocabulary (" +
                "    vindex  INTEGER PRIMARY KEY AUTOINCREMENT" +
                "                    NOT NULL" +
                "                    UNIQUE," +
                "    vword   VARCHAR NOT NULL," +
                "    vbelong INT     NOT NULL" +
                "                    CONSTRAINT headbelong REFERENCES " + tablename + "head (hindex) ON DELETE CASCADE" +
                "                                                                        ON UPDATE CASCADE" +
                "                                                                        MATCH [FULL]" +
                ")";
    }


    public Cursor query(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        try{
            cursor = db.rawQuery(sql,null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i(TAG, "查询所有");

        return cursor;
    }


    public ArrayList<String> getdata(int cunit,int cindex){
        String sql = "select * from Unit"+cunit+"head inner join Unit"+cunit+"vocabulary on Unit"+cunit+"head.hindex = Unit"+cunit+"vocabulary.vbelong where hindex ="+cindex+"";
        Cursor cursor =query(sql);
        ArrayList array = new ArrayList();
        while (cursor.moveToNext()) {
            int i =0;
            array.add(cursor.getString(4));//获取第一列的值,第一列的索引从0开始
            i++;
        }
        return array;
    }
    public String[] getdata(int cunit){
        String sql ="select * from Unit"+cunit+"head";
        Cursor cursor =query(sql);
        ArrayList array = new ArrayList();
        while (cursor.moveToNext()) {
            int i =0;
            array.add(cursor.getString(1));//获取第一列的值,第一列的索引从0开始
            i++;
        }
        String[] sarry =new String[array.size()];
        for(int o = 0;o<array.size();o++){
            sarry[o]=String.valueOf(array.get(o));
        }
        return sarry;
    }
    public String[] getdata(){
        String sql ="select * from Unitlist";
        Cursor cursor =query(sql);
        ArrayList array = new ArrayList();
        while (cursor.moveToNext()) {
            int i =0;
            array.add(cursor.getString(1));//获取第一列的值,第一列的索引从0开始
            i++;
        }
        String[] sarry =new String[array.size()];
        for(int o = 0;o<array.size();o++){
            sarry[o]=String.valueOf(array.get(o));
        }
        return sarry;
    }
}