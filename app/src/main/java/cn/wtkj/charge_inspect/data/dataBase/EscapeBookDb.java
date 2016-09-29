package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;

/**
 * Created by ghj on 2016/9/22.
 */
public class EscapeBookDb {
    private SQLiteDatabase db;
    private MyDataBaseHelper dataBaseHelper;
    private String tablename=MyDataBaseHelper.JC_ESCAPEBOOK;

    public EscapeBookDb(Context context) {
        dataBaseHelper = new MyDataBaseHelper(context);
    }

    public void inertEscapBook(JCEscapeBookData data) {
        try {
            String sql = String
                    .format("INSERT INTO %s(EscapeBookID,ShiftID,PeccancyTypeID,FindDT,OrgID,OprID," +
                            "OprName,InDecision,OutDecision,RealityMoney,EscapeMoney,Monitor," +
                            "VehPlate,Remark,IsUpLoad,ListInfo,IsDeleted,LastOprUser,LastOprDT," +
                            "CreateUserID,CreateDT,CreateFlag,InStationID,AxleNumber,Weight," +
                            "MoneyBefore,MoneyAfter,IsChecked,OrgLevel,OperType,userID,ShiftName," +
                            "PeccancyTypeName, UnitName,InDecisionName,OutDecisionName," +
                            "InStationName,AxleNumberName)  " +
                            "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                            tablename,data.getEscapeBookID(),
                            data.getShiftID(),data.getPeccancyTypeID(),data.getFindDT(),
                            data.getOrgID(),data.getOprID(),data.getOprName(),data.getInDecision(),
                            data.getOutDecision(),data.getRealityMoney(),data.getEscapeMoney(),
                            data.getMonitor(),data.getVehPlate(),data.getRemark(),data.getIsUpLoad(),
                            data.getListInfo(),data.getIsDeleted(),data.getLastOprUser(),
                            data.getLastOprDT(),data.getCreateUserID(),data.getCreateDT(),
                            data.getCreateFlag(),data.getInStationID(),data.getAxleNumber(),
                            data.getWeight(),data.getMoneyBefore(),data.getMoneyAfter(),
                            data.getIsChecked(),data.getOrgLevel(),data.getOperType(),data.getUserID(),
                            data.getShiftName(),data.getPeccancyTypeName(),data.getUnitName(),
                            data.getInDecisionName(),data.getOutDecisionName(),data.getInStationName(),
                            data.getAxleNumberName());

            db = dataBaseHelper.getWritableDatabase();
            db.execSQL(sql);
            Cursor cursor = db.rawQuery(
                    "select last_insert_rowid() from JC_EscapeBook", null);
            cursor.moveToFirst();
            db.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void updateEscapBook(JCEscapeBookData data) {
        if (data.getOperType()==2) {
            String sql = String.format(
                    "UPDATE %s SET ShiftID='%s',PeccancyTypeID='%s',FindDT='%s'," +
                            "OrgID='%s',OprID='%s',OprName='%s',InDecision='%s'," +
                            "OutDecision='%s',RealityMoney='%s',EscapeMoney='%s',Monitor='%s'," +
                            "VehPlate='%s',Remark='%s',IsUpLoad='%s',ListInfo='%s'," +
                            "IsDeleted='%s',LastOprUser='%s',LastOprDT='%s',CreateUserID='%s'," +
                            "CreateDT='%s',CreateFlag='%s',InStationID='%s',AxleNumber='%s'," +
                            "Weight='%s',MoneyBefore='%s',MoneyAfter='%s',IsChecked='%s'," +
                            "OrgLevel='%s',OperType='%s',userID='%s',ShiftName='%s',PeccancyTypeName='%s'," +
                            "UnitName='%s',InDecisionName='%s',OutDecisionName='%s',InStationName='%s'," +
                            "AxleNumberName='%s'",
                    tablename,data.getShiftID(),
                    data.getPeccancyTypeID(),data.getFindDT(),
                    data.getOrgID(),data.getOprID(),data.getOprName(),data.getInDecision(),
                    data.getOutDecision(),data.getRealityMoney(),data.getEscapeMoney(),
                    data.getMonitor(),data.getVehPlate(),data.getRemark(),data.getIsUpLoad(),
                    data.getListInfo(),data.getIsDeleted(),data.getLastOprUser(),
                    data.getLastOprDT(),data.getCreateUserID(),data.getCreateDT(),
                    data.getCreateFlag(),data.getInStationID(),data.getAxleNumber(),
                    data.getWeight(),data.getMoneyBefore(),data.getMoneyAfter(),
                    data.getIsChecked(),data.getOrgLevel(),data.getOperType(),data.getUserID(),
                    data.getShiftName(),data.getPeccancyTypeName(),data.getUnitName(),
                    data.getInDecisionName(),data.getOutDecisionName(),data.getInStationName(),
                    data.getAxleNumberName());
            SQLiteDatabase database = DatabaseManager.getInstance().openDatabase();
            database.execSQL(sql);
            DatabaseManager.getInstance().closeDatabase();
        } else {
            inertEscapBook(data);
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

    public List<JCEscapeBookData> getEscapeBook(String userId) {
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
