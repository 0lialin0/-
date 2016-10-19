package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
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
    List<File> files;
    List<String> fileName;

    public PhotoVideoDb(Context context) {
        dataBaseHelper = new MyDataBaseHelper(context);
    }

    public void insertPv(PhotoVideoData data) {
        try {
            String sql = String
                    .format("INSERT INTO %s(BlackListID,VehicleID,YListID, GCListID, NameType, " +
                                    "videoName ,videoUrl, photoName,photoUrl ,creartTime) " +
                                    " VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            tablename,
                            data.getBlackListID(), data.getVehicleID(), data.getYListID(),
                            data.getGCListID(), data.getNameType(), data.getVideoName(),
                            data.getVideoUrl(),
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
        if (getCount(data.getPhotoName(), data.getNameType()) > 0) {
            String sql = String.format(
                    "UPDATE %s SET BlackListID='%s',VehicleID='%s',YListID='%s', " +
                            "GCListID='%s', NameType='%s', videoName='%s' ,videoUrl='%s' ," +
                            "photoName='%s',photoUrl='%s' ,creartTime='%s'",
                    tablename, data.getBlackListID(), data.getVehicleID(), data.getYListID(),
                    data.getGCListID(),data.getNameType(), data.getVideoName(), data.getVideoUrl(),
                    data.getPhotoName(), data.getPhotoUrl(), data.getCreartTime());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
        } else {
            insertPv(data);
        }
    }

    public int getCount(String name, int type) {
        String sql = String.format("SELECT * FROM %s WHERE photoName = '" + name + "' " +
                "and NameType="+type +" ",
                tablename);
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql, null);
        int num = cur.getCount();
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return num;
    }

    public List<PhotoVideoData> getPv(String uuid,int type) {
        List<PhotoVideoData> list = new ArrayList<>();
        PhotoVideoData data;
        String col[] = {"pvid", "BlackListID", "VehicleID", "YListID", "NameType",
                "videoName", "videoUrl", "photoName"
                , "photoUrl", "creartTime","GCListID"};
        db = dataBaseHelper.getReadableDatabase();
        Cursor cur=db.query(tablename, col, "BlackListID= ?",
                new String[]{uuid}, null, null, "creartTime desc");
        if(type==0){
            cur = db.query(tablename, col, "BlackListID= ?",
                    new String[]{uuid}, null, null, "creartTime desc");
        }else if(type==1){
            cur = db.query(tablename, col, "VehicleID= ?",
                    new String[]{uuid}, null, null, "creartTime desc");
        }else if(type==2){
            cur = db.query(tablename, col, "YListID= ?",
                    new String[]{uuid}, null, null, "creartTime desc");
        }else if(type==3){
            cur = db.query(tablename, col, "GCListID= ?",
                    new String[]{uuid}, null, null, "creartTime desc");
        }

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
                data.setGCListID(cur.getString(10));
                list.add(data);
                cur.moveToNext();
            }
        }
        cur.close();
        db.close();
        return list;
    }

    public void delData(String id, int type) {
        if (type == 0) {
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.delete(tablename, "BlackListID=?", new String[]{id});
            DatabaseManager.getInstance().closeDatabase();
        } else if (type == 1) {
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.delete(tablename, "VehicleID=?", new String[]{id});
            DatabaseManager.getInstance().closeDatabase();
        } else if (type == 2) {
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.delete(tablename, "YListID=?", new String[]{id});
            DatabaseManager.getInstance().closeDatabase();
        } else if(type==3) {
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.delete(tablename, "GCListID=?", new String[]{id});
            DatabaseManager.getInstance().closeDatabase();
        }

    }


    public void insertListPvd(List<File> fileList, String uuid, int type){
        PhotoVideoData data;
        int fileIndex = 0;
        files = new ArrayList<>();
        fileName = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            if (file.exists()) {
                data = new PhotoVideoData();
                data.setPhotoName(fileList.get(i).getName());
                if (type == 0) {
                    data.setBlackListID(uuid);
                } else if (type == 1) {
                    data.setVehicleID(uuid);
                } else if (type == 2) {
                    data.setYListID(uuid);
                }else if(type==3){
                    data.setGCListID(uuid);
                }
                data.setNameType(type);
                data.setPhotoUrl(fileList.get(i).getPath());
                this.updatePv(data);
            }
        }
    }
}
