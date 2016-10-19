package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCGreenChannelRecData;

/**
 * Created by ghj on 2016/9/22.
 */
public class GreenChannelDb {
    private SQLiteDatabase db;
    private MyDataBaseHelper dataBaseHelper;
    private String tablename = MyDataBaseHelper.JC_GREENCHANNEL;

    public GreenChannelDb(Context context) {
        dataBaseHelper = new MyDataBaseHelper(context);
    }

    public String inertGreenChannelList(JCGreenChannelRecData data) {
        String uuid = "";
        try {
            String sql = String
                    .format("INSERT INTO %s( GCListID, CardNo, VepPlateNo, VehicleTypeID," +
                                    "VehicleTypeName, FactoryType, Capacity, AxleCount, AxleCountName," +
                                    "Tonnage,ReportOrgID, ReportOrgLevel, CheckDate,InStationID, " +
                                    " InStationName, LaneID, LaneName,OprName, Shiftman,IsMix, " +
                                    "IsMixName,GoodsName,MixGoodsName, IsEnjoy, FreeMoney," +
                                    " EscapeMoney, Remark,OperType, userID)  " +
                                    "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                                    "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                                    "'%s','%s','%s')",
                            tablename, data.getGCListID(), data.getCardNo(), data.getVehPlateNo(),
                            data.getVehicleTypeID(), data.getVehicleTypeIDName(), data.getFactoryType(),
                            data.getCapacity(), data.getAxleCount(), data.getAxleCountName(), data.getTonnage(),
                            data.getReportOrgID(), data.getReportOrgLevel(), data.getCheckDate(), data.getInStationID(),
                            data.getInStationName(), data.getLaneID(), data.getLaneName(), data.getOprName(),
                            data.getShiftman(), data.getIsMix(), data.getIsMixName(), data.getGoodsName(),
                            data.getMixGoodsName(), data.getIsEnjoy(), data.getFreeMoney(), data.getEscapeMoney(),
                            data.getRemark(), data.getOperType(), data.getUserID());

            db = dataBaseHelper.getWritableDatabase();
            db.execSQL(sql);
            Cursor cursor = db.rawQuery(
                    "select last_insert_rowid() from JC_GreenChannel", null);
            cursor.moveToFirst();
            db.close();
            uuid=data.getGCListID();
        } catch (Exception e) {
            uuid = "";
            e.getStackTrace();
        }
        return uuid;
    }

    public String updateGreenChannelList(JCGreenChannelRecData data) {
        String uuid = "";
        if (getCount(data.getGCListID()) > 0) {
            String sql = String.format(
                    "UPDATE %s SET  GCListID='%s', CardNo='%s', VepPlateNo='%s'," +
                            " VehicleTypeID='%s',VehicleTypeName='%s', FactoryType='%s', " +
                            "Capacity='%s', AxleCount='%s', AxleCountName='%s',Tonnage='%s'," +
                            "ReportOrgID='%s', ReportOrgLevel='%s', CheckDate='%s'," +
                            "InStationID='%s',  InStationName='%s', LaneID='%s', " +
                            "LaneName='%s',OprName='%s', Shiftman='%s',IsMix='%s', " +
                            "IsMixName='%s',GoodsName='%s',MixGoodsName='%s', IsEnjoy='%s'," +
                            " FreeMoney='%s', EscapeMoney='%s', Remark='%s',OperType='%s'," +
                            " userID='%s'",
                    tablename, data.getGCListID(), data.getCardNo(), data.getVehPlateNo(),
                    data.getVehicleTypeID(), data.getVehicleTypeIDName(), data.getFactoryType(),
                    data.getCapacity(), data.getAxleCount(), data.getAxleCountName(), data.getTonnage(),
                    data.getReportOrgID(), data.getReportOrgLevel(), data.getCheckDate(), data.getInStationID(),
                    data.getInStationName(), data.getLaneID(), data.getLaneName(), data.getOprName(),
                    data.getShiftman(), data.getIsMix(), data.getIsMixName(), data.getGoodsName(),
                    data.getMixGoodsName(), data.getIsEnjoy(), data.getFreeMoney(), data.getEscapeMoney(),
                    data.getRemark(), data.getOperType(), data.getUserID());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
            uuid=data.getGCListID();
        } else {
            uuid = inertGreenChannelList(data);
        }
        return uuid;
    }

    public int getCount(String id) {
        String sql = String.format("SELECT * FROM %s WHERE GCListID = '" + id + "'",
                tablename);
        SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
        Cursor cur = database.rawQuery(sql, null);
        int num = cur.getCount();
        cur.close();
        DatabaseManager.getInstance().closeDatabase();
        return num;
    }

    public List<JCGreenChannelRecData> getGreenChannelList(String userId, String keyword) {
        ArrayList<JCGreenChannelRecData> list = new ArrayList<JCGreenChannelRecData>();
        JCGreenChannelRecData data;
        String col[] = {"GCListID", "CardNo", "VepPlateNo", "VehicleTypeID",
                "VehicleTypeName", "FactoryType", "Capacity", "AxleCount", "AxleCountName",
                "Tonnage", "ReportOrgID", "ReportOrgLevel", "CheckDate",
                "InStationID", "InStationName", "LaneID", "LaneName",
                "OprName", "Shiftman",
                "IsMix", "IsMixName", "GoodsName",
                "MixGoodsName", "IsEnjoy", "FreeMoney", "EscapeMoney", "Remark",
                "OperType", "userID"};
        db = dataBaseHelper.getReadableDatabase();
        Cursor cur;
        if (keyword.equals("")) {
            cur = db.query(tablename, col, "userID= ?",
                    new String[]{userId}, null, null, "CheckDate desc");
        } else {
            cur = db.query(tablename, col, "userID= ? and VepPlateNo like ?",
                    new String[]{userId, "%" + keyword + "%"}, null, null, "CheckDate desc");
        }

        if (cur.moveToFirst()) {
            for (int i = 0; i < cur.getCount(); i++) {
                data = new JCGreenChannelRecData();
                data.setGCListID(cur.getString(0));
                data.setCardNo(cur.getString(1));
                data.setVehPlateNo(cur.getString(2));

                data.setVehicleTypeID(cur.getInt(3));
                data.setVehicleTypeIDName(cur.getString(4));

                data.setFactoryType(cur.getString(5));
                data.setCapacity(cur.getString(6));

                data.setAxleCount(cur.getInt(7));
                data.setAxleCountName(cur.getString(8));

                data.setTonnage(cur.getString(9));
                data.setReportOrgID(cur.getInt(10));
                data.setReportOrgLevel(cur.getString(11));

                data.setCheckDate(cur.getString(12));

                data.setInStationID(cur.getInt(13));
                data.setInStationName(cur.getString(14));

                data.setLaneID(cur.getInt(15));
                data.setLaneName(cur.getString(16));

                data.setOprName(cur.getString(17));
                data.setShiftman(cur.getString(18));

                data.setIsMix(cur.getInt(19));
                data.setIsMixName(cur.getString(20));

                data.setGoodsName(cur.getString(21));
                data.setMixGoodsName(cur.getString(22));
                data.setIsEnjoy(cur.getInt(23));
                data.setFreeMoney(cur.getString(24));
                data.setEscapeMoney(cur.getString(25));

                data.setRemark(cur.getString(26));
                data.setOperType(cur.getInt(27));
                data.setUserID(cur.getString(28));

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
        database.delete(tablename, "GCListID=?", new String[]{id});
        DatabaseManager.getInstance().closeDatabase();

    }

}
