package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementAddPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementAddPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.IncrementAddView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.util.Setting;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener2;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownKeyValue;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/21.
 */
public class IncrementAddActivity extends MvpBaseActivity<IncrementAddPresenter> implements
        IncrementAddView, View.OnClickListener, OnItemClickListener, OnItemClickListener3 {

    @Bind(R.id.aty_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.aty_tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.iv_phone)
    ImageView ivPhone;

    @Bind(R.id.ed_check_unit)
    TextView edCheckUnit;
    @Bind(R.id.tv_check_time)
    TextView tvCheckTime;
    @Bind(R.id.tv_classes)
    TextView tvClasses;
    @Bind(R.id.tv_increment_type)
    TextView tvIncrementType;
    @Bind(R.id.tv_entrance_loca)
    TextView tvEntranceLoca;
    @Bind(R.id.tv_entrance_type)
    TextView tvEntranceType;
    @Bind(R.id.tv_exit_type)
    TextView tvExitType;

    @Bind(R.id.ed_car_num)
    EditText edCarNum;
    @Bind(R.id.ed_charge_num)
    EditText edChargeNum;
    @Bind(R.id.ed_charge_name)
    EditText edChargeName;
    @Bind(R.id.ed_shi_money)
    EditText edShiMoney;
    @Bind(R.id.ed_zeng_money)
    EditText edZengMoney;
    @Bind(R.id.ed_class_name)
    EditText edClassName;
    @Bind(R.id.ed_zhoushou)
    TextView edZhoushou;
    @Bind(R.id.ed_dunshuo)
    EditText edDunshuo;

    @Bind(R.id.ed_remark)
    EditText edRemark;

    @Bind(R.id.ll_zhoushu)
    LinearLayout llZhoushu;
    @Bind(R.id.ll_dunshuo)
    LinearLayout llDunshuo;

    private DropDownKeyValue downKeyValue;
    private DropDownKeyValue downKeyValue2;
    private DropDownKeyValue downKeyValue3;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;
    private DropDownMenu dropDownMenu4;
    private DropDownMenu dropDownMenu5;

    List<ConstAllData.MData.info> entranList = new ArrayList<>();
    private JCEscapeBookData data;
    private int shiftID;
    private String shiftName;
    private int axleNumber;
    private String axleNumberName;
    private int peccancyTypeID;
    private String peccancyTypeName;
    private int inStationID;
    private String inStationName;
    private int inDecision;
    private String inDecisionName;
    private int outDecision;
    private String outDecisionName;
    private ProgressDialog progressDialog;
    private ConstAllDb constAllDb;
    private String shortName;
    private int type = 1;
    private String increment_title = "添加增收";
    private String uuid;

    @Override
    protected IncrementAddPresenter createPresenter() {
        return new IncrementAddPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_increment_add);
        ButterKnife.bind(this);
        presenter.attachContextIntent(this, this.getIntent());
        presenter.startPresenter();
        constAllDb = new ConstAllDb(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(increment_title);
        mToolbar.setTitle("");

        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
    }

    public void initView() {
        setView();
        progressDialog = new ProgressDialog(this);
    }

    //班次
    @Override
    public void setDropDown(List<KeyValueData> classes, List<KeyValueData> zhoushuo) {
        //班次
        downKeyValue = new DropDownKeyValue(this, classes);
        downKeyValue.setId(1);
        downKeyValue.setOnItemClickListener(this);
        shiftID = 1;
        shiftName = classes.get(0).getValue();
        tvClasses.setText(classes.get(0).getValue());

        //轴数
        downKeyValue2 = new DropDownKeyValue(this, zhoushuo);
        downKeyValue2.setId(1);
        downKeyValue2.setOnItemClickListener(this);
        axleNumberName = zhoushuo.get(0).getValue();
        axleNumber = Integer.valueOf(zhoushuo.get(0).getId());
        edZhoushou.setText(zhoushuo.get(0).getValue());

        //查处单位
        List<KeyValueData> unit = presenter.getOrgShortName(Setting.ORGID, Setting.ORGLEVEL);
        if (unit.size() > 0) {
            downKeyValue3 = new DropDownKeyValue(this, unit);
            downKeyValue3.setId(1);
            downKeyValue3.setOnItemClickListener(this);
            shortName = unit.get(0).getValue();
            edCheckUnit.setText(unit.get(0).getValue());
        }

    }

    //如果是修改页面传来的数据的时候，把页面的值赋上
    @Override
    public void setView() {
        JCEscapeBookData data = (JCEscapeBookData) getIntent().getSerializableExtra(
                IncrementListActivity.DATA_TAG);
        showView();
        if (data != null) {
            showViewData(data);
            type = 2;
            increment_title = "修改增收";
            tvTitle.setText(increment_title);
        } else {
            type = 1;
        }

    }


    //显示值在页面上
    private void showViewData(JCEscapeBookData data) {
        if (type == 2) {
            ivPhone.setVisibility(View.VISIBLE);
            ivMore.setVisibility(View.VISIBLE);
        }
        edCarNum.setText(data.getVehPlate());
        //String shortname = presenter.getOrgShortName(data.getOrgID(), data.getOrgLevel());
        edCheckUnit.setText(data.getUnitName());
        tvCheckTime.setText(data.getFindDT());
        edChargeNum.setText(data.getOprID());
        edChargeName.setText(data.getOprName());
        tvClasses.setText(data.getShiftName());
        edClassName.setText(data.getMonitor());
        //String zsType = constAllDb.getConstName(data.getPeccancyTypeID(), 9);
        tvIncrementType.setText(data.getPeccancyTypeName());
        //String rkzz = constAllDb.getConstName(data.getInStationID(), 1);
        tvEntranceLoca.setText(data.getInStationName());
        //String rkpz = constAllDb.getConstName(data.getInDecision(), 5);
        tvEntranceType.setText(data.getInDecisionName());
        //String ckpz = constAllDb.getConstName(data.getOutDecision(), 5);
        tvExitType.setText(data.getOutDecisionName());
        edShiMoney.setText(data.getRealityMoney());
        edZengMoney.setText(data.getEscapeMoney());
        edZhoushou.setText(data.getAxleNumberName());
        edDunshuo.setText(data.getWeight());
        edRemark.setText(data.getRemark());

        shiftID = data.getShiftID();
        shiftName = data.getShiftName();
        axleNumber = data.getAxleNumber();
        axleNumberName = data.getAxleNumberName();
        peccancyTypeID = data.getPeccancyTypeID();
        peccancyTypeName = data.getPeccancyTypeName();
        inStationID = data.getInStationID();
        inStationName = data.getInStationName();
        inDecision = data.getInDecision();
        inDecisionName = data.getInDecisionName();
        outDecision = data.getOutDecision();
        outDecisionName = data.getOutDecisionName();
        shortName = data.getUnitName();
        uuid = data.getEscapeBookID();
    }


    @Override
    public void showView() {

        tvCheckTime.setText(ResponeUtils.getTime());

        //1：站址, 5：车型类别, 9：堵漏增收信息,

        //堵漏增收信息
        List<ConstAllData.MData.info> dlType = presenter.getConstByType(9);
        dropDownMenu2 = new DropDownMenu(this, dlType);
        //dropDownMenu2.setId(1);
        dropDownMenu2.setOnItemClickListener(this);
        if (dlType.size() > 0) {
            peccancyTypeID = dlType.get(0).getCode();
            peccancyTypeName = dlType.get(0).getName();
            tvIncrementType.setText(dlType.get(0).getName());
        }


        //入口站址(站址)
        List<ConstAllData.MData.info> rkLoca = presenter.getConstByType(1);
        dropDownMenu3 = new DropDownMenu(this, rkLoca);
        //dropDownMenu3.setId(1);
        dropDownMenu3.setOnItemClickListener(this);
        if (rkLoca.size() > 0) {
            inStationID = rkLoca.get(0).getCode();
            inStationName = rkLoca.get(0).getName();
            tvEntranceLoca.setText(rkLoca.get(0).getName());
        }


        //入口判型(车型类别)
        entranList = presenter.getConstByType(5);
        dropDownMenu4 = new DropDownMenu(this, entranList);
        //dropDownMenu4.setId(1);
        dropDownMenu4.setOnItemClickListener(this);
        if (entranList.size() > 0) {
            inDecision = entranList.get(0).getCode();
            inDecisionName = entranList.get(0).getName();
            tvEntranceType.setText(entranList.get(0).getName());
        }
        showExitList(0, 4);

    }


    public void showExitList(int start, int end) {
        //出口判型
        if (entranList.size() > 0) {
            List<ConstAllData.MData.info> exitList = new ArrayList<>();
            ConstAllData data = new ConstAllData();
            ConstAllData.MData mData = data.new MData();
            for (int i = 0; i < entranList.size(); i++) {
                if (entranList.get(i).getCode() >= start && entranList.get(i).getCode() <= end) {
                    ConstAllData.MData.info info = mData.new info();
                    info.setName(entranList.get(i).getName());
                    info.setCode(entranList.get(i).getCode());
                    info.setType(55);
                    exitList.add(info);
                }
            }
            dropDownMenu5 = new DropDownMenu(this, exitList);
            //dropDownMenu5.setId(1);
            dropDownMenu5.setOnItemClickListener(this);
            outDecision = exitList.get(0).getCode();
            outDecisionName = exitList.get(0).getName();
            tvExitType.setText(exitList.get(0).getName());
        }

    }

    @Override
    public void showLoding() {
        progressDialog.setMessage("正在保存，请等待..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideLoging() {
        progressDialog.hide();
    }

    @Override
    public void nextView() {
        this.finish();
        Intent intent = new Intent();
        intent.setClass(this, IncrementListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMes(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }


    @OnClick({R.id.iv_left, R.id.tv_check_time, R.id.rl_classes, R.id.rl_zhoushuo,
            R.id.rl_increment_type, R.id.rl_entrance_loca,
            R.id.rl_entrance_type, R.id.rl_exit_type, R.id.comit_button, R.id.rl_check_unit})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.tv_check_time:
                DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(this);
                dateTimePickerDialog.dateTimePicKDialog(tvCheckTime, 0);
                break;
            case R.id.rl_classes:
                downKeyValue.setDownValue(tvClasses, "");
                break;
            case R.id.rl_zhoushuo:
                downKeyValue2.setDownValue(edZhoushou, "");
                break;
            case R.id.rl_check_unit:
                downKeyValue3.setDownValue(edCheckUnit, "");
                break;
            case R.id.rl_increment_type:
                dropDownMenu2.setDownValue(tvIncrementType, "");
                break;
            case R.id.rl_entrance_loca:
                dropDownMenu3.setDownValue(tvEntranceLoca, "");
                break;
            case R.id.rl_entrance_type:
                dropDownMenu4.setDownValue(tvEntranceType, "");
                break;
            case R.id.rl_exit_type:
                dropDownMenu5.setDownValue(tvExitType, "");
                break;
            case R.id.comit_button:
                if (TextUtils.isEmpty(edCarNum.getText())) {
                    showMes("车牌号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edChargeNum.getText())) {
                    showMes("收费员工号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edChargeName.getText())) {
                    showMes("收费员姓名不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edShiMoney.getText())) {
                    showMes("实收金额不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edZengMoney.getText())) {
                    showMes("增收金额不能为空！");
                    return;
                }
                getShowData();
                presenter.submitData(data);
                break;
        }
    }

    public void getShowData() {
        data = new JCEscapeBookData();
        if (type == 1) {
            uuid = UUID.randomUUID().toString();
        }
        data.setEscapeBookID(uuid);
        data.setUnitName(shortName);
        data.setUserID(Setting.USERID);
        data.setOperType(type);//1：新增 2：修改
        data.setOrgID(Setting.ORGID);
        data.setOrgLevel(Setting.ORGLEVEL);

        data.setVehPlate(edCarNum.getText().toString());
        data.setFindDT(tvCheckTime.getText().toString());
        data.setOprID(edChargeNum.getText().toString());
        data.setOprName(edChargeName.getText().toString());
        data.setShiftID(shiftID);
        data.setShiftName(shiftName);
        if (!TextUtils.isEmpty(edClassName.getText())) {
            data.setMonitor(edClassName.getText().toString());
        }
        data.setPeccancyTypeID(peccancyTypeID);
        data.setPeccancyTypeName(peccancyTypeName);
        data.setInStationID(inStationID);
        data.setInStationName(inStationName);
        data.setInDecision(inDecision);
        data.setInDecisionName(inDecisionName);
        data.setOutDecision(outDecision);
        data.setOutDecisionName(outDecisionName);

        data.setRealityMoney(edShiMoney.getText() + "");
        data.setEscapeMoney(edZengMoney.getText() + "");

           /* if (!edZhoushou.getText().toString().replaceAll(" ", "").equals("")) {
                data.setAxleNumber(Integer.valueOf(edZhoushou.getText().toString()));
            }*/
        data.setAxleNumber(axleNumber);
        data.setAxleNumberName(axleNumberName);
        if (!edDunshuo.getText().toString().replaceAll(" ", "").equals("")) {
            data.setWeight(edDunshuo.getText().toString());
        } else {
            data.setWeight("0");
        }

        if (!TextUtils.isEmpty(edRemark.getText())) {
            data.setRemark(edRemark.getText().toString());
        }


    }

    @Override
    public void onItemClick(int code, int type, String name) {
        switch (type) {
            case 5:
                if (code == 0 || code == 1 || code == 2 || code == 3 || code == 4) {
                    showExitList(0, 4);
                } else if (code == 11 || code == 12 || code == 13 || code == 14 || code == 15) {
                    showExitList(4, 9);
                    llZhoushu.setVisibility(View.VISIBLE);
                    llDunshuo.setVisibility(View.VISIBLE);
                }
                inDecision = code;
                inDecisionName = name;
                break;
            case 55: //出口判型
                outDecision = code;
                outDecisionName = name;
                break;
            case 9:
                peccancyTypeID = code;
                peccancyTypeName = name;
                break;
            case 1:
                inStationID = code;
                inStationName = name;
                break;
        }
    }


    //班次,轴数
    @Override
    public void onItemClick(String name, int id) {
        if (name.equals("早班") || name.equals("中班") || name.equals("晚班")) {
            shiftID = id;
            shiftName = name;
        } else if (id == 88) {
            shortName = name;
        } else {
            axleNumber = id;
            axleNumberName = name;
        }
    }
}