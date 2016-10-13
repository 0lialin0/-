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
                    .format("INSERT INTO %s(proId, videoName ,videoUrl ," +
                                    "photoName,photoUrl ,creartTime)  VALUES ('%s','%s','%s','%s','%s','%s')",
                            tablename,
                            data.getProId(), data.getVideoName(), data.getVideoUrl(),
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
        if (getCount(data.getProId()) > 0) {
            String sql = String.format(
                    "UPDATE %s SET proId='%s', videoName='%s' ,videoUrl='%s' ," +
                            "photoName='%s',photoUrl='%s' ,creartTime='%s'",
                    tablename, data.getProId(), data.getVideoName(), data.getVideoUrl(),
                    data.getPhotoName(), data.getPhotoUrl(), data.getCreartTime());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
        } else {
            insertPv(data);
        }
    }

    public int getCount(String uuid) {
        String sql = String.format("SELECT * FROM %s WHERE proId = '" + uuid + "' ",
                tablename);
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
        String col[] = {"pvid", "proId", "videoName", "videoUrl", "photoName"
                , "photoUrl", "creartTime"};
        db = dataBaseHelper.getReadableDatabase();
        Cursor cur;
        cur = db.query(tablename, col, "proId= ?",
                new String[]{uuid}, null, null, "creartTime desc");
        if (cur.moveToFirst()) {
            for (int i = 0; i < cur.getCount(); i++) {
                data = new PhotoVideoData();
                data.setPvid(cur.getInt(0));
                data.setProId(cur.getString(1));
                data.setVideoName(cur.getString(2));
                data.setVideoUrl(cur.getString(3));
                data.setPhotoName(cur.getString(4));
                data.setPhotoUrl(cur.getString(5));
                data.setCreartTime(cur.getString(6));
                list.add(data);
                cur.moveToNext();
            }
        }
        cur.close();
        db.close();
        return list;
    }

    public void delData(String id) {
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        database.delete(tablename, "proId=?", new String[]{id});
        DatabaseManager.getInstance().closeDatabase();
    }
}
