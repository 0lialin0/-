package cn.wtkj.charge_inspect.data.bean;

import java.io.Serializable;

/**
 * Created by ghj on 2016/9/20.
 * 稽查逃费台账---增收
 */
public class JCEscapeBookData implements Serializable {

    private String EscapeBookID;
    private int ShiftID;
    private int PeccancyTypeID;
    private String FindDT;
    private int OrgID;
    private String OprID;
    private String OprName;
    private int InDecision;
    private int OutDecision;
    private String RealityMoney;
    private String EscapeMoney;
    private String Monitor;
    private String VehPlate;
    private String Remark;
    private int IsUpLoad;
    private String ListInfo;
    private int IsDeleted;
    private String LastOprUser;
    private String LastOprDT;
    private String CreateUserID;
    private String CreateDT;
    private int CreateFlag;
    private int InStationID;
    private int AxleNumber;
    private String Weight;
    private String MoneyBefore;
    private String MoneyAfter;
    private int IsChecked;
    private String OrgLevel;


    public String getLastOprUser() {
        return LastOprUser;
    }

    public void setLastOprUser(String lastOprUser) {
        LastOprUser = lastOprUser;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getIsUpLoad() {
        return IsUpLoad;
    }

    public void setIsUpLoad(int isUpLoad) {
        IsUpLoad = isUpLoad;
    }

    public String getListInfo() {
        return ListInfo;
    }

    public void setListInfo(String listInfo) {
        ListInfo = listInfo;
    }

    public int getIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        IsDeleted = isDeleted;
    }

    public String getEscapeBookID() {
        return EscapeBookID;
    }

    public void setEscapeBookID(String escapeBookID) {
        EscapeBookID = escapeBookID;
    }

    public int getShiftID() {
        return ShiftID;
    }

    public void setShiftID(int shiftID) {
        ShiftID = shiftID;
    }

    public int getPeccancyTypeID() {
        return PeccancyTypeID;
    }

    public void setPeccancyTypeID(int peccancyTypeID) {
        PeccancyTypeID = peccancyTypeID;
    }

    public String getFindDT() {
        return FindDT;
    }

    public void setFindDT(String findDT) {
        FindDT = findDT;
    }

    public int getOrgID() {
        return OrgID;
    }

    public void setOrgID(int orgID) {
        OrgID = orgID;
    }

    public String getOprID() {
        return OprID;
    }

    public void setOprID(String oprID) {
        OprID = oprID;
    }

    public String getOprName() {
        return OprName;
    }

    public void setOprName(String oprName) {
        OprName = oprName;
    }

    public int getInDecision() {
        return InDecision;
    }

    public void setInDecision(int inDecision) {
        InDecision = inDecision;
    }

    public int getOutDecision() {
        return OutDecision;
    }

    public void setOutDecision(int outDecision) {
        OutDecision = outDecision;
    }

    public String getRealityMoney() {
        return RealityMoney;
    }

    public void setRealityMoney(String realityMoney) {
        RealityMoney = realityMoney;
    }

    public String getEscapeMoney() {
        return EscapeMoney;
    }

    public void setEscapeMoney(String escapeMoney) {
        EscapeMoney = escapeMoney;
    }

    public String getMonitor() {
        return Monitor;
    }

    public void setMonitor(String monitor) {
        Monitor = monitor;
    }

    public String getVehPlate() {
        return VehPlate;
    }

    public void setVehPlate(String vehPlate) {
        VehPlate = vehPlate;
    }

    public String getLastOprDT() {
        return LastOprDT;
    }

    public void setLastOprDT(String lastOprDT) {
        LastOprDT = lastOprDT;
    }

    public String getCreateUserID() {
        return CreateUserID;
    }

    public void setCreateUserID(String createUserID) {
        CreateUserID = createUserID;
    }

    public String getCreateDT() {
        return CreateDT;
    }

    public void setCreateDT(String createDT) {
        CreateDT = createDT;
    }

    public int getCreateFlag() {
        return CreateFlag;
    }

    public void setCreateFlag(int createFlag) {
        CreateFlag = createFlag;
    }

    public int getInStationID() {
        return InStationID;
    }

    public void setInStationID(int inStationID) {
        InStationID = inStationID;
    }

    public int getAxleNumber() {
        return AxleNumber;
    }

    public void setAxleNumber(int axleNumber) {
        AxleNumber = axleNumber;
    }

    public String getWeight() {
        return Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getMoneyBefore() {
        return MoneyBefore;
    }

    public void setMoneyBefore(String moneyBefore) {
        MoneyBefore = moneyBefore;
    }

    public String getMoneyAfter() {
        return MoneyAfter;
    }

    public void setMoneyAfter(String moneyAfter) {
        MoneyAfter = moneyAfter;
    }

    public int getIsChecked() {
        return IsChecked;
    }

    public void setIsChecked(int isChecked) {
        IsChecked = isChecked;
    }

    public String getOrgLevel() {
        return OrgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        OrgLevel = orgLevel;
    }
}
