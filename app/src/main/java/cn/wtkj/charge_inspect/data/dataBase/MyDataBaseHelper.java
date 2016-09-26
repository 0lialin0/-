package cn.wtkj.charge_inspect.data.dataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ChargeInspect.db";
    private static final int DATABASE_VERSION = 1;
    public static final String EM_CONST = "EM_Const";
    public static final String VIEW_ORGANIZATION = "View_Organization";
    public static final String JC_ESCAPEBOOK = "JC_EscapeBook";

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String constSql = String
                .format("Create TABLE %s(%s int, %s VARCHAR(120), %s int)",
                        EM_CONST, "code", "name", "type");
        db.execSQL(constSql);


        String organizationSql = String
                .format("Create TABLE %s(%s INTEGER PRIMARY KEY, %s VARCHAR(120), %s VARCHAR(120), " +
                                "%s VARCHAR(120), %s VARCHAR(120), %s VARCHAR(120), %s int, %s int, " +
                                "%s VARCHAR(120), %s int, %s int)",
                        VIEW_ORGANIZATION, "OrgCode", "OrgLevel", "Name", "ShortName", "Viewcode",
                        "EffectTime", "Lvs", "Leaf", "Businesslineid", "Status", "Orgversion");
        db.execSQL(organizationSql);

        String escapebookSql = String
                .format("Create TABLE %s(%s INTEGER PRIMARY KEY, %s int, %s int, %s VARCHAR(120)," +
                                " %s int,%s VARCHAR(120),%s VARCHAR(120),%s int, %s int,  %s VARCHAR(120)," +
                                "%s VARCHAR(120), %s VARCHAR(220), %s VARCHAR(120),  %s VARCHAR(120),%s int, " +
                                "%s VARCHAR(120), %s int, %s VARCHAR(120), %s VARCHAR(120), %s VARCHAR(120),  " +
                                "%s VARCHAR(120), %s int, %s int, %s int,%s VARCHAR(120), %s VARCHAR(120), " +
                                "%s VARCHAR(120), %s int, %s VARCHAR(120))",
                        JC_ESCAPEBOOK, "EscapeBookID", "ShiftID", "PeccancyTypeID", "FindDT",
                        "OrgID", "OprID", "OprName", "InDecision", "OutDecision", "RealityMoney",
                        "EscapeMoney", "Monitor", "VehPlate", "Remark", "IsUpLoad", "ListInfo",
                        "IsDeleted", "LastOprUser", "LastOprDT", "CreateUserID", "CreateDT", "CreateFlag",
                        "InStationID", "AxleNumber", "Weight", "MoneyBefore", "MoneyAfter",
                        "IsChecked", "OrgLevel");
        db.execSQL(escapebookSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (exits("Error", db)) {
            db.execSQL("DROP TABLE Error");
        }
        if (exits("Config", db)) {
            db.execSQL("DROP TABLE Config");
        }
        if (exits("RoadCategory", db)) {
            db.execSQL("DROP TABLE RoadCategory");
        }
        if (exits("RoadInfo", db)) {
            db.execSQL("DROP TABLE RoadInfo");
        }
        if (exits("TrafficInfo", db)) {
            db.execSQL("DROP TABLE TrafficInfo");
        }
        if (!exits(EM_CONST, db)) {
            String constSql = String
                    .format("Create TABLE %s(%s INTEGER PRIMARY KEY, %s VARCHAR(120), %s VARCHAR(120))",
                            "code", "name", "type");
            db.execSQL(constSql);
        }


        if (!exits(VIEW_ORGANIZATION, db)) {
            String vehtypeSql = String
                    .format("Create TABLE %s(%s INTEGER PRIMARY KEY, %s VARCHAR(120), %s VARCHAR(120), " +
                                    "%s VARCHAR(120), %s VARCHAR(120), %s VARCHAR(120), %s int, %s int, " +
                                    "%s VARCHAR(120), %s int, %s int, %s VARCHAR(120))",
                            "OrgCode", "OrgLevel", "Name", "ShortName", "Viewcode",
                            "EffectTime", "Lvs", "Leaf", "Businesslineid", "Status", "Orgversion", "Orgvc");
            db.execSQL(vehtypeSql);
        }

        if (!exits(JC_ESCAPEBOOK, db)) {
            String escapebookSql = String
                    .format("Create TABLE %s(%s INTEGER PRIMARY KEY, %s int, %s int, %s VARCHAR(120)," +
                                    " %s int,%s VARCHAR(120),%s VARCHAR(120),%s int, %s int,  %s VARCHAR(120)," +
                                    "%s VARCHAR(120), %s VARCHAR(220), %s VARCHAR(120),  %s VARCHAR(120),%s int, " +
                                    "%s VARCHAR(120), %s int, %s VARCHAR(120), %s VARCHAR(120), %s VARCHAR(120),  " +
                                    "%s VARCHAR(120), %s int, %s int, %s int,%s VARCHAR(120), %s VARCHAR(120), " +
                                    "%s VARCHAR(120), %s int, %s VARCHAR(120))",
                            "EscapeBookID", "ShiftID", "PeccancyTypeID", "FindDT",
                            "OrgID", "OprID", "OprName", "InDecision", "OutDecision", "RealityMoney",
                            "EscapeMoney", "Monitor", "VehPlate", "Remark", "IsUpLoad", "ListInfo",
                            "IsDeleted", "LastOprUser", "LastOprDT", "CreateUserID", "CreateDT", "CreateFlag",
                            "InStationID", "AxleNumber", "Weight", "MoneyBefore", "MoneyAfter",
                            "IsChecked", "OrgLevel");
            db.execSQL(escapebookSql);
        }


    }

    public boolean exits(String table, SQLiteDatabase db) {
        boolean exits = false;
        String sql = "select * from sqlite_master where name=" + "'" + table
                + "'";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.getCount() != 0) {
            exits = true;
        }
        return exits;
    }
}
