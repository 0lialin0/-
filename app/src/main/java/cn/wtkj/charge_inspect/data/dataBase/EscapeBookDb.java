package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;

/**
 * Created by ghj on 2016/9/22.
 */
public class EscapeBookDb {
    private SQLiteDatabase db;
    private MyDataBaseHelper dataBaseHelper;

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
                            "MoneyBefore,MoneyAfter,IsChecked,OrgLevel)  " +
                            "VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                            "'%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s'," +
                            "'%s','%s','%s')",
                            MyDataBaseHelper.VIEW_ORGANIZATION, data.getEscapeBookID(),
                            data.getShiftID(),data.getPeccancyTypeID(),data.getFindDT(),
                            data.getOrgID(),data.getOprID(),data.getOprName(),data.getInDecision(),
                            data.getOutDecision(),data.getRealityMoney(),data.getEscapeMoney(),
                            data.getMonitor(),data.getVehPlate(),data.getRemark(),data.getIsUpLoad(),
                            data.getListInfo(),data.getIsDeleted(),data.getLastOprUser(),
                            data.getLastOprDT(),data.getCreateUserID(),data.getCreateDT(),
                            data.getCreateFlag(),data.getInStationID(),data.getAxleNumber(),
                            data.getWeight(),data.getMoneyBefore(),data.getMoneyAfter(),
                            data.getIsChecked(),data.getOrgLevel());

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


}
