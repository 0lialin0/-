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
    private String tablename=MyDataBaseHelper.JC_BLACKLIST;

    public BlackListDb(Context context) {
        dataBaseHelper = new MyDataBaseHelper(context);
    }

    public void inertBlackList(JCBlackListData data) {
        try {
            String sql = String
                    .format("INSERT INTO %s(BlackListID, CardNo, VepPlateNo, VehicleTypeID," +
                            "VehicleTypeName, VehType, VehTypeName, VepColor, VepColorName," +
                            "VepPlateNoColor,VepPlateNoColorName, PeccancyTypeID, " +
                            "PeccancyTypeName,FactoryType,  GenDT, InOrgID, InOrgName," +
                            " OutOrgID, OutOrgName,PeccancyOrgID, PeccancyOrgName," +
                            "GenCause,AxleCount, Tonnage, Seating, videoName, videoList," +
                            "photoName, photoList,OperType,userID,AxleCountName)  " +
                            "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                            "'%s','%s','%s','%s','%s','%s')",
                            tablename,data.getBlackListID(),data.getCardNo(),data.getVepPlateNo(),
                            data.getVehicleTypeID(),data.getVehicleTypeName(),data.getVehType(),
                            data.getVehTypeName(),data.getVepColor(),data.getVepColorName(),
                            data.getVepPlateNoColor(),data.getVepPlateNoColorName(),data.getPeccancyTypeID(),
                            data.getPeccancyTypeName(),data.getFactoryType(),data.getGenDT(),data.getInOrgID(),
                            data.getInOrgName(),data.getOutOrgID(),data.getOutOrgName(),data.getPeccancyOrgID(),
                            data.getPeccancyOrgName(),data.getGenCause(),data.getAxleCount(),data.getTonnage(),
                            data.getSeating(),data.getVideoName(),data.getVideoList(),data.getPhotoName(),
                            data.getPhotoList(),data.getOperType(),data.getUserID(),data.getAxleCountName());

            db = dataBaseHelper.getWritableDatabase();
            db.execSQL(sql);
            Cursor cursor = db.rawQuery(
                    "select last_insert_rowid() from JC_BlackList", null);
            cursor.moveToFirst();
            db.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateBlackList(JCBlackListData data) {
        if (data.getOperType()==2) {
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
                            "OperType='%s',userID='%s'" +
                            "AxleCountName='%s'",
                    tablename,data.getBlackListID(),data.getCardNo(),data.getVepPlateNo(),
                    data.getVehicleTypeID(),data.getVehicleTypeName(),data.getVehType(),
                    data.getVehTypeName(),data.getVepColor(),data.getVepColorName(),
                    data.getVepPlateNoColor(),data.getVepPlateNoColorName(),data.getPeccancyTypeID(),
                    data.getPeccancyTypeName(),data.getFactoryType(),data.getGenDT(),data.getInOrgID(),
                    data.getInOrgName(),data.getOutOrgID(),data.getOutOrgName(),data.getPeccancyOrgID(),
                    data.getPeccancyOrgName(),data.getGenCause(),data.getAxleCount(),data.getTonnage(),
                    data.getSeating(),data.getVideoName(),data.getVideoList(),data.getPhotoName(),
                    data.getPhotoList(),data.getOperType(),data.getUserID(),data.getAxleCountName());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
        } else {
            inertBlackList(data);
        }
    }

    public int getCount(int id) {
        String sql = String.format("SELECT * FROM %s WHERE EscapeBookID = "+id+"",
                tablename);
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql, null);
        int num = cur.getCount();
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return num;
    }

    public List<JCEscapeBookData> getBlackList(String userId) {
        ArrayList<JCEscapeBookData> list = new ArrayList<JCEscapeBookData>();
        JCEscapeBookData data;
        String col[] = {"EscapeBookID","ShiftName", "PeccancyTypeName", "FindDT",
                "UnitName","OrgLevel",
                "OprID", "OprName", "InDecisionName", "OutDecisionName", "RealityMoney",
                "EscapeMoney","Monitor","VehPlate", "Remark", "InStationName",
                "AxleNumber","Weight", "PeccancyTypeID"};
        db = dataBaseHelper.getReadableDatabase();
        Cursor cur = db.query(tablename, col, "userID= ?",
                new String[]{userId}, null, null, "FindDT desc");
        if (cur.moveToFirst()) {
            for (int i = 0; i < cur.getCount(); i++) {
                data = new JCEscapeBookData();
                data.setEscapeBookID(cur.getString(0));
                data.setShiftName(cur.getString(1));
                data.setPeccancyTypeName(cur.getString(2));
                data.setFindDT(cur.getString(3));
                data.setUnitName(cur.getString(4));
                data.setOrgLevel(cur.getString(5));
                data.setOprID(cur.getString(6));
                data.setOprName(cur.getString(7));
                data.setInDecisionName(cur.getString(8));
                data.setOutDecisionName(cur.getString(9));
                data.setRealityMoney(cur.getString(10));
                data.setEscapeMoney(cur.getString(11));
                data.setMonitor(cur.getString(12));
                data.setVehPlate(cur.getString(13));
                data.setRemark(cur.getString(14));
                data.setInStationName(cur.getString(15));
                data.setAxleNumber(cur.getInt(16));
                data.setWeight(cur.getString(17));
                data.setPeccancyTypeID(cur.getInt(18));

                list.add(data);
                cur.moveToNext();
            }
        }
        cur.close();
        db.close();

        return list;
    }

}
