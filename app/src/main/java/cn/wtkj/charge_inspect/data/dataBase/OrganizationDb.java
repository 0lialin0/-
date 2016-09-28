package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;

/**
 * Created by ghj on 2016/9/22.
 */
public class OrganizationDb {
    private SQLiteDatabase db;
    private MyDataBaseHelper dataBaseHelper;
    private String tableName = MyDataBaseHelper.VIEW_ORGANIZATION;

    public OrganizationDb(Context context) {
        dataBaseHelper = new MyDataBaseHelper(context);
        DatabaseManager.initializeInstance(dataBaseHelper);
    }

    public void inertOrg(ViewOrganizationData.MData.info info) {
        try {
            String sql = String
                    .format("INSERT INTO %s(OrgCode, OrgLevel,Name,ShortName,Viewcode," +
                            "EffectTime,Lvs,Leaf,Businesslineid,Status,Orgversion)  " +
                            "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            MyDataBaseHelper.VIEW_ORGANIZATION, info.getOrgCode(),info.getOrgLevel(),
                            info.getName(),info.getShortName(),info.getViewcode(),info.getEffectTime(),
                            info.getLvs(),info.getLeaf(),info.getBusinesslineid(),info.getStatus(),
                            info.getOrgversion());

            db = dataBaseHelper.getWritableDatabase();
            db.execSQL(sql);
            Cursor cursor = db.rawQuery(
                    "select last_insert_rowid() from View_Organization", null);
            cursor.moveToFirst();
            db.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateOrg(ViewOrganizationData.MData.info info){
        if(getCount(info.getOrgCode())>0){
            String sql = String.format(
                    "UPDATE %s SET OrgCode='%s',OrgLevel='%s',Name='%s',ShortName='%s'," +
                            "Viewcode='%s',EffectTime='%s',Lvs='%s',Leaf='%s',Businesslineid='%s'" +
                            ",Status='%s',Orgversion='%s'",
                    tableName,info.getOrgCode(),info.getOrgLevel(),info.getName(),
                    info.getShortName(),info.getViewcode(),info.getEffectTime(),info.getLvs(),
                    info.getLeaf(),info.getBusinesslineid(),info.getStatus(),info.getOrgversion());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
        }else{
            inertOrg(info);
        }
    }

    public int getCount(int OrgCode) {
        String sql = String.format("SELECT * FROM %s WHERE OrgCode = "+OrgCode+" ",
                tableName);
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql, null);
        int num = cur.getCount();
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return num;
    }

    public String getCheckUnit(int orgId,String OrgLevel){
        /*String sql = String.format("SELECT ShortName FROM %s WHERE OrgCode = "+orgId+" " +
                "AND OrgLevel = '"+OrgLevel+"'",
                tableName);*/
        String sql = String.format("SELECT ShortName FROM %s WHERE OrgCode = 3 " +
                        "AND OrgLevel = 'Company'",
                tableName);
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql,null);
        int num = cur.getCount();
        cur.moveToFirst();
        String unit = cur.getString(0);
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return unit;
    }
}
