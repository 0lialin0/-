package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;

/**
 * Created by ghj on 2016/9/22.
 */
public class BlackListDb {
    private SQLiteDatabase db;
    private MyDataBaseHelper dataBaseHelper;
    private String tablename = MyDataBaseHelper.JC_BLACKLIST;

    public BlackListDb(Context context) {
        dataBaseHelper = new MyDataBaseHelper(context);
    }

    public String inertBlackList(JCBlackListData data) {
        String uuid = "";
        try {
            String sql = String
                    .format("INSERT INTO %s(BlackListID, CardNo, VepPlateNo, VehicleTypeID," +
                                    "VehicleTypeName, VehType, VehTypeName, VepColor, VepColorName," +
                                    "VepPlateNoColor,VepPlateNoColorName, PeccancyTypeID, " +
                                    "PeccancyTypeName,FactoryType,  GenDT, InOrgID, InOrgName," +
                                    " OutOrgID, OutOrgName,PeccancyOrgID, PeccancyOrgName," +
                                    "GenCause,AxleCount, Tonnage, Seating, videoName, videoList," +
                                    "photoName, photoList,OperType,userID,AxleCountName," +
                                    "VehicleID,YListID,NameType,OwnerAddress,OwnerType," +
                                    "OwnerTypeName,Postalcode,TeletePhone,MobilePhone,Owner," +
                                    "PeccancyDescription,HistoryInfo)  " +
                                    "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                                    "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                                    "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                                    "'%s','%s','%s','%s')",
                            tablename, data.getBlackListID(), data.getCardNo(), data.getVepPlateNo(),
                            data.getVehicleTypeID(), data.getVehicleTypeName(), data.getVehType(),
                            data.getVehTypeName(), data.getVepColor(), data.getVepColorName(),
                            data.getVepPlateNoColor(), data.getVepPlateNoColorName(), data.getPeccancyTypeID(),
                            data.getPeccancyTypeName(), data.getFactoryType(), data.getGenDT(), data.getInOrgID(),
                            data.getInOrgName(), data.getOutOrgID(), data.getOutOrgName(), data.getPeccancyOrgID(),
                            data.getPeccancyOrgName(), data.getGenCause(), data.getAxleCount(), data.getTonnage(),
                            data.getSeating(), data.getVideoName(), data.getVideoList(), data.getPhotoName(),
                            data.getPhotoList(), data.getOperType(), data.getUserID(),
                            data.getAxleCountName(), data.getVehicleID(), data.getYListID(),
                            data.getNameType(), data.getOwnerAddress(), data.getOwnerType(),
                            data.getOwnerTypeName(), data.getPostalcode(), data.getTeletePhone(),
                            data.getMobilePhone(), data.getOwner(), data.getPeccancyDescription(),
                            data.getHistoryInfo());

            db = dataBaseHelper.getWritableDatabase();
            db.execSQL(sql);
            Cursor cursor = db.rawQuery(
                    "select last_insert_rowid() from JC_BlackList", null);
            cursor.moveToFirst();
            db.close();
            if (data.getNameType() == 0) {
                uuid = data.getBlackListID();
            } else if (data.getNameType() == 1) {
                uuid = data.getVehicleID();
            } else if (data.getNameType() == 2) {
                uuid = data.getYListID();
            }
        } catch (Exception e) {
            uuid = "";
            e.getStackTrace();
        }
        return uuid;
    }

    public String updateBlackList(JCBlackListData data) {
        String uuid = "";
        if (data.getOperType() == 2) {
            String sql = String.format(
                    "UPDATE %s SET BlackListID='%s', CardNo='%s', VepPlateNo='%s', " +
                            "VehicleTypeID='%s',VehicleTypeName='%s', VehType='%s'," +
                            " VehTypeName='%s', VepColor='%s', VepColorName='%s'," +
                            "VepPlateNoColor='%s',VepPlateNoColorName='%s'," +
                            " PeccancyTypeID='%s', PeccancyTypeName='%s',FactoryType='%s', " +
                            " GenDT='%s', InOrgID='%s', InOrgName='%s', OutOrgID='%s'," +
                            " OutOrgName='%s',PeccancyOrgID='%s', PeccancyOrgName='%s'," +
                            "GenCause='%s',AxleCount='%s', Tonnage='%s', Seating='%s', " +
                            "videoName='%s', videoList='%s',photoName='%s', photoList='%s'," +
                            "OperType='%s',userID='%s'," +
                            "AxleCountName='%s',VehicleID='%s',YListID='%s',NameType='%s'," +
                            "OwnerAddress='%s',OwnerType='%s',OwnerTypeName='%s'," +
                            "Postalcode='%s',TeletePhone='%s',MobilePhone='%s',Owner='%s'," +
                            "PeccancyDescription='%s',HistoryInfo='%s'",
                    tablename, data.getBlackListID(), data.getCardNo(), data.getVepPlateNo(),
                    data.getVehicleTypeID(), data.getVehicleTypeName(), data.getVehType(),
                    data.getVehTypeName(), data.getVepColor(), data.getVepColorName(),
                    data.getVepPlateNoColor(), data.getVepPlateNoColorName(), data.getPeccancyTypeID(),
                    data.getPeccancyTypeName(), data.getFactoryType(), data.getGenDT(), data.getInOrgID(),
                    data.getInOrgName(), data.getOutOrgID(), data.getOutOrgName(), data.getPeccancyOrgID(),
                    data.getPeccancyOrgName(), data.getGenCause(), data.getAxleCount(), data.getTonnage(),
                    data.getSeating(), data.getVideoName(), data.getVideoList(), data.getPhotoName(),
                    data.getPhotoList(), data.getOperType(), data.getUserID(), data.getAxleCountName(),
                    data.getVehicleID(), data.getYListID(),
                    data.getNameType(), data.getOwnerAddress(), data.getOwnerType(),
                    data.getOwnerTypeName(), data.getPostalcode(), data.getTeletePhone(),
                    data.getMobilePhone(), data.getOwner(), data.getPeccancyDescription(),
                    data.getHistoryInfo());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
            if (data.getNameType() == 0) {
                uuid = data.getBlackListID();
            } else if (data.getNameType() == 1) {
                uuid = data.getVehicleID();
            } else if (data.getNameType() == 2) {
                uuid = data.getYListID();
            }

        } else {
            uuid = inertBlackList(data);
        }
        return uuid;
    }

    public int getCount(int id) {
        String sql = String.format("SELECT * FROM %s WHERE BlackListID = " + id + "",
                tablename);
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql, null);
        int num = cur.getCount();
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return num;
    }

    public List<JCBlackListData> getBlackList(String userId, String keyword) {
        ArrayList<JCBlackListData> list = new ArrayList<JCBlackListData>();
        JCBlackListData data;
        String col[] = {"BlackListID", "CardNo", "VepPlateNo", "VehicleTypeID",
                "VehicleTypeName", "VehType", "VehTypeName", "VepColor", "VepColorName",
                "VepPlateNoColor", "VepPlateNoColorName", "PeccancyTypeID", "PeccancyTypeName",
                "FactoryType", "GenDT", "InOrgID", "InOrgName", "OutOrgID", "OutOrgName",
                "PeccancyOrgID", "PeccancyOrgName", "GenCause",
                "AxleCount", "Tonnage", "Seating", "videoName", "videoList",
                "photoName", "photoList", "OperType", "userID", "AxleCountName",
                "VehicleID", "YListID", "NameType", "OwnerAddress", "OwnerType",
                "OwnerTypeName", "Postalcode", "TeletePhone", "MobilePhone",
                "Owner", "PeccancyDescription", "HistoryInfo"};
        db = dataBaseHelper.getReadableDatabase();
        Cursor cur;
        if (keyword.equals("")) {
            cur = db.query(tablename, col, "userID= ?",
                    new String[]{userId}, null, null, "GenDT desc");
        } else {
            cur = db.query(tablename, col, "userID= ? and VepPlateNo like ?",
                    new String[]{userId, "%" + keyword + "%"}, null, null, "GenDT desc");
        }

        if (cur.moveToFirst()) {
            for (int i = 0; i < cur.getCount(); i++) {
                data = new JCBlackListData();
                data.setBlackListID(cur.getString(0));
                data.setCardNo(cur.getString(1));
                data.setVepPlateNo(cur.getString(2));

                data.setVehicleTypeID(cur.getInt(3));
                data.setVehicleTypeName(cur.getString(4));

                data.setVehType(cur.getInt(5));
                data.setVehTypeName(cur.getString(6));

                data.setVepColor(cur.getInt(7));
                data.setVepColorName(cur.getString(8));

                data.setVepPlateNoColor(cur.getInt(9));
                data.setVepPlateNoColorName(cur.getString(10));

                data.setPeccancyTypeID(cur.getInt(11));
                data.setPeccancyTypeName(cur.getString(12));

                data.setFactoryType(cur.getString(13));
                data.setGenDT(cur.getString(14));

                data.setInOrgID(cur.getInt(15));
                data.setInOrgName(cur.getString(16));

                data.setOutOrgID(cur.getInt(17));
                data.setOutOrgName(cur.getString(18));

                data.setPeccancyOrgID(cur.getInt(19));
                data.setPeccancyOrgName(cur.getString(20));

                data.setGenCause(cur.getString(21));
                data.setAxleCount(cur.getInt(22));
                data.setTonnage(cur.getString(23));
                data.setSeating(cur.getInt(24));

                data.setVideoName(cur.getString(25));
                data.setVideoList(cur.getString(26));
                data.setPhotoName(cur.getString(27));
                data.setPhotoList(cur.getString(28));
                data.setOperType(cur.getInt(29));
                data.setUserID(cur.getString(30));
                data.setAxleCountName(cur.getString(31));

                data.setVehicleID(cur.getString(32));
                data.setYListID(cur.getString(33));
                data.setNameType(cur.getInt(34));
                data.setOwnerAddress(cur.getString(35));
                data.setOwnerType(cur.getInt(36));
                data.setOwnerTypeName(cur.getString(37));
                data.setPostalcode(cur.getString(38));
                data.setTeletePhone(cur.getString(39));
                data.setMobilePhone(cur.getString(40));
                data.setOwner(cur.getString(41));

                data.setPeccancyDescription(cur.getString(42));
                data.setHistoryInfo(cur.getString(43));

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
        }
    }

}
