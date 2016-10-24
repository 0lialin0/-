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
                    .format("INSERT INTO %s(blackListID,nameType, " +
                                    "fileName ,fileUrl, fileType ,createTime) " +
                                    " VALUES ('%s','%s','%s','%s','%s','%s')",
                            tablename,
                            data.getBlackListID(), data.getNameType(), data.getFileName(),
                            data.getFileUrl(), data.getFileType(), data.getCreateTime());

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
        if (getCount(data.getFileName(), data.getNameType()) > 0) {
            String sql = String.format(
                    "UPDATE %s SET blackListID='%s', nameType='%s', fileName='%s' ,fileUrl='%s' ," +
                            "fileType='%s',createTime='%s'",
                    tablename, data.getBlackListID(),data.getNameType(), data.getFileName(), data.getFileUrl(),
                    data.getFileType(), data.getCreateTime());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
        } else {
            insertPv(data);
        }
    }

    public int getCount(String name, int type) {
        String sql = String.format("SELECT * FROM %s WHERE fileName = '" + name + "' " +
                "and nameType="+type +" ",
                tablename);
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql, null);
        int num = cur.getCount();
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return num;
    }

    public List<PhotoVideoData> getPv(String uuid,int type, int fileType) {
        List<PhotoVideoData> list = new ArrayList<>();
        PhotoVideoData data;

        db = dataBaseHelper.getReadableDatabase();
        String sql = "";

       if (fileType >= 0){
           sql = String.format("SELECT * FROM %s WHERE blackListID = '" + uuid + "' " +
                   "and nameType="+type +" and fileType=" + fileType, tablename);
       }else{
           sql = String.format("SELECT * FROM %s WHERE blackListID = '" + uuid + "' " +
                   "and nameType="+type , tablename);
       }


        Cursor cur = db.rawQuery(sql, null);
        if (cur.moveToFirst()) {
            for (int i = 0; i < cur.getCount(); i++) {
                data = new PhotoVideoData();
                data.setPvId(cur.getInt(0));
                data.setBlackListID(cur.getString(1));
                data.setNameType(cur.getInt(2));
                data.setFileName(cur.getString(3));
                data.setFileUrl(cur.getString(4));
                data.setFileType(cur.getInt(5));
                data.setCreateTime(cur.getString(6));

                list.add(data);
                cur.moveToNext();
            }
        }
        cur.close();
        db.close();
        return list;
    }

    public void delData(String id, int type) {

        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        database.delete(tablename, "blackListID=? and type = ?", new String[]{id, type+""});
        DatabaseManager.getInstance().closeDatabase();

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
                data.setFileName(fileList.get(i).getName());

                data.setBlackListID(uuid);

                data.setNameType(type);
                data.setFileUrl(fileList.get(i).getPath());

                int fileType = 0;
                if (fileList.get(i).getName().endsWith(".mp4")){
                     fileType = 1;
                }

                data.setFileType(fileType);
                this.updatePv(data);
            }
        }
    }
}
