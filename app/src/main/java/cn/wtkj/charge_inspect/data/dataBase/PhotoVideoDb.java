package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;

/**
 * Created by ghj on 2016/10/12.
 */
public class PhotoVideoDb {
    private SQLiteDatabase db;
    private MyDataBaseHelper dataBaseHelper;
    private String tablename = MyDataBaseHelper.PHOTOVIDEO;

    public PhotoVideoDb(Context context) {
        dataBaseHelper = new MyDataBaseHelper(context);
    }

    public void insertPv(PhotoVideoData data) {
        try {
            String sql = String
                    .format("INSERT INTO %s(BlackListID,VehicleID,YListID, NameType, " +
                                    "videoName ,videoUrl  photoName,photoUrl ,creartTime) " +
                                    " VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            tablename,
                            data.getBlackListID(), data.getVehicleID(), data.getYListID(),
                            data.getNameType(), data.getVideoName(), data.getVideoUrl(),
                            data.getPhotoName(), data.getPhotoUrl(), data.getCreartTime());

            db = dataBaseHelper.getWritableDatabase();
            db.execSQL(sql);
            Cursor cursor = db.rawQuery(
                    "select last_insert_rowid() from PhotoVideo", null);
            cursor.moveToFirst();
            db.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updatePv(PhotoVideoData data) {
        String uuid = "";
        if (data.getNameType() == 0) {
            uuid = data.getBlackListID();
        } else if (data.getNameType() == 1) {
            uuid = data.getVehicleID();
        } else if (data.getNameType() == 2) {
            uuid = data.getYListID();
        }
        if (getCount(uuid,data.getNameType()) > 0) {
            String sql = String.format(
                    "UPDATE %s SET BlackListID='%s',VehicleID='%s',YListID='%s', " +
                            "NameType='%s', videoName='%s' ,videoUrl='%s' ," +
                            "photoName='%s',photoUrl='%s' ,creartTime='%s'",
                    tablename, data.getBlackListID(), data.getVehicleID(), data.getYListID(),
                    data.getNameType(), data.getVideoName(), data.getVideoUrl(),
                    data.getPhotoName(), data.getPhotoUrl(), data.getCreartTime());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
        } else {
            insertPv(data);
        }
    }

    public int getCount(String uuid,int type) {
        String sql= String.format("SELECT * FROM %s WHERE BlackListID = '" + uuid + "' ",
                tablename);
        if(type==0){
            sql = String.format("SELECT * FROM %s WHERE BlackListID = '" + uuid + "' ",
                    tablename);
        }else if(type==1){
            sql = String.format("SELECT * FROM %s WHERE VehicleID = '" + uuid + "' ",
                    tablename);
        }else if(type==2){
            sql = String.format("SELECT * FROM %s WHERE YListID = '" + uuid + "' ",
                    tablename);
        }
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql, null);
        int num = cur.getCount();
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return num;
    }

    public List<PhotoVideoData> getPv(String uuid) {
        List<PhotoVideoData> list = new ArrayList<>();
        PhotoVideoData data;
        String col[] = {"pvid", "BlackListID","VehicleID","YListID","NameType",
                "videoName", "videoUrl", "photoName"
                , "photoUrl", "creartTime"};
        db = dataBaseHelper.getReadableDatabase();
        Cursor cur;
        cur = db.query(tablename, col, "proId= ?",
                new String[]{uuid}, null, null, "creartTime desc");
        if (cur.moveToFirst()) {
            for (int i = 0; i < cur.getCount(); i++) {
                data = new PhotoVideoData();
                data.setPvid(cur.getInt(0));
                data.setBlackListID(cur.getString(1));
                data.setVehicleID(cur.getString(2));
                data.setYListID(cur.getString(3));
                data.setNameType(cur.getInt(4));
                data.setVideoName(cur.getString(5));
                data.setVideoUrl(cur.getString(6));
                data.setPhotoName(cur.getString(7));
                data.setPhotoUrl(cur.getString(8));
                data.setCreartTime(cur.getString(9));
                list.add(data);
                cur.moveToNext();
            }
        }
        cur.close();
        db.close();
        return list;
    }

    public void delData(String id,int type) {
        if(type==0){
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.delete(tablename, "BlackListID=?", new String[]{id});
            DatabaseManager.getInstance().closeDatabase();
        }else if(type==1){
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.delete(tablename, "VehicleID=?", new String[]{id});
            DatabaseManager.getInstance().closeDatabase();
        }else if(type==2){
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.delete(tablename, "YListID=?", new String[]{id});
            DatabaseManager.getInstance().closeDatabase();
        }

    }
}
