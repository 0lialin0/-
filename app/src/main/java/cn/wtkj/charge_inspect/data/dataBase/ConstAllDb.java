package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;

/**
 * Created by ghj on 2016/9/22.
 */
public class ConstAllDb {
    private SQLiteDatabase db;
    private MyDataBaseHelper dataBaseHelper;

    public ConstAllDb(Context context) {
        dataBaseHelper = new MyDataBaseHelper(context);
    }

    public void inertConst(ConstAllData.MData.info constAllData) {
        try {
            String sql = String
                    .format("INSERT INTO %s(code, name, type)  VALUES ('%s','%s','%s')",
                            MyDataBaseHelper.EM_CONST, constAllData.getCode(),
                            constAllData.getName(), constAllData.getType());

            db = dataBaseHelper.getWritableDatabase();
            db.execSQL(sql);
            Cursor cursor = db.rawQuery(
                    "select last_insert_rowid() from EM_Const", null);
            cursor.moveToFirst();
            db.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    public List<ConstAllData.MData.info> getConstList(int type) {
        List<ConstAllData.MData.info> list = new ArrayList<>();
        ConstAllData data=new ConstAllData();
        ConstAllData.MData mData=data.new MData();
        String sql= String.format("SELECT name,code,type FROM %s WHERE type = "+type+"",
                MyDataBaseHelper.EM_CONST);
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql,null);
        if (cur.moveToFirst()) {
            for (int i = 0; i < cur.getCount(); i++) {
                ConstAllData.MData.info info=mData.new info();
                info.setName(cur.getString(0));
                info.setCode(cur.getInt(1));
                info.setType(cur.getInt(2));
                list.add(info);
                cur.moveToNext();
            }
        }
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return list;
    }
}
