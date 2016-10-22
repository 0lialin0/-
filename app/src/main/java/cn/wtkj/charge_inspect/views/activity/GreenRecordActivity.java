package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.JCGreenChannelRecData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.GreenRecordPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.util.Setting;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownKeyValue;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;
import cn.wtkj.charge_inspect.views.custom.DropDownOrgMenu;
import cn.wtkj.charge_inspect.views.custom.MyPhotos;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/18.
 */
public class GreenRecordActivity extends MvpBaseActivity<GreenRecordPresenter> implements
        GreenRecordView, MyPhotos.OnClickAddImgListener, View.OnClickListener,
        OnItemClickListener3, OnItemClickListener {

    @Bind(R.id.aty_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.aty_tvTitle)
    TextView tvTitle;
    @Bind(R.id.iv_left)
    ImageView ivLeft;
    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.myphoto)
    MyPhotos myphoto;
    @Bind(R.id.iv_phone)
    ImageView ivPhone;

    @Bind(R.id.ed_car_num)
    EditText edCarNum;
    @Bind(R.id.tv_car_type)
    TextView tvCarType;
    @Bind(R.id.ed_factory_type)
    EditText edFactoryType;
    @Bind(R.id.ed_capacity)
    EditText edCapacity;
    @Bind(R.id.tv_axleCount)
    TextView tvAxleCount;
    @Bind(R.id.ed_tonnage)
    EditText edTonnage;
    @Bind(R.id.tv_reportOrgID)
    TextView tvReportOrgID;
    @Bind(R.id.tv_check_time)
    TextView tvCheckTime;
    @Bind(R.id.tv_InStationID)
    TextView tvInStationID;
    @Bind(R.id.tv_LaneID)
    TextView tvLaneID;
    @Bind(R.id.ed_OprName)
    EditText edOprName;
    @Bind(R.id.ed_Shiftman)
    EditText edShiftman;
    @Bind(R.id.tv_green_type)
    TextView tvGreenType;
    @Bind(R.id.rg_IsEnjoy)
    RadioGroup rgIsEnjoy;
    @Bind(R.id.rb_IsEnjoy_yes)
    RadioButton rbIsEnjoyyes;
    @Bind(R.id.rb_IsEnjoy_no)
    RadioButton rbIsEnjoyno;
    @Bind(R.id.ed_FreeMoney)
    EditText edFreeMoney;
    @Bind(R.id.ed_EscapeMoney)
    EditText edEscapeMoney;
    @Bind(R.id.ed_GoodsName)
    EditText edGoodsName;
    @Bind(R.id.ed_MixGoodsName)
    EditText edMixGoodsName;
    @Bind(R.id.ed_Remark)
    EditText edRemark;

    @Bind(R.id.ll_FreeMoney)
    LinearLayout llFreeMoney;
    @Bind(R.id.ll_EscapeMoney)
    LinearLayout llEscapeMoney;

    private int vehicleTypeID; //车辆类别ID
    private String vehicleTypeIDName; //车辆类别
    private int axleCount; //轴数
    private String axleCountName;//轴数
    private int reportOrgID;//上报单位ID
    private String reportOrgLevel;//上报单位
    private int inStationID;//入口站址ID
    private String inStationName;//入口站址
    private int laneID;//出口车道ID
    private String laneName;//出口车道
    private int isMix;//绿通类型
    private String isMixName;//绿通类型
    private int isEnjoy = 0;//是否减免

    private List<File> files;
    private ProgressDialog progressDialog;
    private AlertDialog alter;
    private DropDownKeyValue downKeyValue;
    private DropDownKeyValue downKeyValue2;
    private DropDownKeyValue downKeyValue3;
    private DropDownMenu dropDownMenu;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;
    private DropDownOrgMenu dropDownOrgMenu;

    private JCGreenChannelRecData data;
    private int type = 1;
    private String increment_title = "添加绿通档案";
    private String uuid;

    @Override
    protected GreenRecordPresenter createPresenter() {
        return new GreenRecordPresenterImpl();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_record);
        ButterKnife.bind(this);
        presenter.attachContextIntent(this, this.getIntent());
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(increment_title);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ivPhone.setVisibility(View.GONE);
    }


    public void initView() {
        progressDialog = new ProgressDialog(this);

        tvCheckTime.setText(ResponeUtils.getTime());
        myphoto.setNameType(3);
        myphoto.setFragment(this);
        myphoto.setOnClickAddImgListener(this);
        files = new ArrayList<>();

        rgIsEnjoy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbIsEnjoyyes.getId()) {
                    isEnjoy = 1;
                    llFreeMoney.setVisibility(View.VISIBLE);
                    llEscapeMoney.setVisibility(View.GONE);
                } else {
                    isEnjoy = 0;
                    llFreeMoney.setVisibility(View.GONE);
                    llEscapeMoney.setVisibility(View.VISIBLE);
                }
            }
        });

        setView();

    }

    @Override
    public void setView() {
        JCGreenChannelRecData data = (JCGreenChannelRecData) getIntent().getSerializableExtra(
                GreenRecordListActivity.DATA_TAG);
        setDropDown();
        if (data != null) {
            showViewData(data);
            type = 2;
            increment_title = "修改绿通档案";
            tvTitle.setText(increment_title);
        } else {
            type = 1;
        }
    }


    @Override
    public void setDropDown() {
        //车辆类别
        List<KeyValueData> vehType = new ArrayList<>();
        vehType.add(new KeyValueData("5", "小型货车"));
        vehType.add(new KeyValueData("11", "中型货车"));
        vehType.add(new KeyValueData("1", "重型货车"));
        downKeyValue3 = new DropDownKeyValue(this, vehType);
        downKeyValue3.setId(1);
        downKeyValue3.setOnItemClickListener(this);
        vehicleTypeID = Integer.valueOf(vehType.get(0).getId());
        vehicleTypeIDName = vehType.get(0).getValue();
        tvCarType.setText(vehType.get(0).getValue());

        //绿通类型
        List<KeyValueData> type = new ArrayList<>();
        type.add(new KeyValueData("111", "正常"));
        type.add(new KeyValueData("111", "混装"));
        type.add(new KeyValueData("111", "不合法装载"));
        downKeyValue2 = new DropDownKeyValue(this, type);
        downKeyValue2.setId(1);
        downKeyValue2.setOnItemClickListener(this);
        isMix = 0;
        isMixName = type.get(0).getValue();
        tvGreenType.setText(type.get(0).getValue());


        //轴数
        List<KeyValueData> zhoushuo = new ArrayList<>();
        zhoushuo.add(new KeyValueData("2", "2轴"));
        zhoushuo.add(new KeyValueData("3", "3轴"));
        zhoushuo.add(new KeyValueData("4", "4轴"));
        zhoushuo.add(new KeyValueData("5", "5轴"));
        zhoushuo.add(new KeyValueData("6", "6轴"));
        zhoushuo.add(new KeyValueData("7", "7轴"));
        downKeyValue = new DropDownKeyValue(this, zhoushuo);
        downKeyValue.setId(1);
        downKeyValue.setOnItemClickListener(this);
        axleCountName = zhoushuo.get(0).getValue();
        axleCount = Integer.valueOf(zhoushuo.get(0).getId());
        tvAxleCount.setText(zhoushuo.get(0).getValue());
        showView();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        File file = myphoto.setImage(requestCode, resultCode, data);
        if (file != null) {
            files.add(file);
        }
    }


    @Override
    public void showView() {
        //1：站址, 2：车道， 6：车辆类别

        //站址
        List<ConstAllData.MData.info> dlType = presenter.getConstByType(1);
        dropDownMenu = new DropDownMenu(this, dlType);
        dropDownMenu.setOnItemClickListener(this);
        if (dlType.size() > 0) {
            inStationID = dlType.get(0).getCode();
            inStationName = dlType.get(0).getName();
            tvInStationID.setText(dlType.get(0).getName());
        }


        //车道
        List<ConstAllData.MData.info> lane = presenter.getConstByType(2);
        dropDownMenu2 = new DropDownMenu(this, lane);
        dropDownMenu2.setOnItemClickListener(this);
        if (lane.size() > 0) {
            laneID = lane.get(0).getCode();
            laneName = lane.get(0).getName();
            tvLaneID.setText(lane.get(0).getName());
        }

        //车辆类别
        /*List<ConstAllData.MData.info> cartype = presenter.getConstByType(6);
        dropDownMenu3 = new DropDownMenu(this, cartype);
        dropDownMenu3.setOnItemClickListener(this);
        if (cartype.size() > 0) {
            vehicleTypeID = cartype.get(0).getCode();
            vehicleTypeIDName = cartype.get(0).getName();
            tvCarType.setText(cartype.get(0).getName());
        }*/

        //上报单位
        List<ViewOrganizationData.MData.info> infos = presenter.getOrg(Setting.ORGID, Setting.ORGLEVEL);
        if (infos.size() > 0) {
            dropDownOrgMenu = new DropDownOrgMenu(this, infos);
            dropDownOrgMenu.setId(1);
            dropDownOrgMenu.setOnItemClickListener(this);
            reportOrgID = infos.get(0).getOrgCode();
            reportOrgLevel = infos.get(0).getShortName();
            tvReportOrgID.setText(infos.get(0).getShortName());
        }
    }


    //显示值在页面上
    private void showViewData(JCGreenChannelRecData data) {
        edCarNum.setText(data.getVehPlateNo());
        vehicleTypeIDName = data.getVehicleTypeIDName();
        vehicleTypeID = data.getVehicleTypeID();
        tvCarType.setText(vehicleTypeIDName);
        edFactoryType.setText(data.getFactoryType());
        edCapacity.setText(data.getCapacity());

        axleCount = data.getAxleCount();
        axleCountName = data.getAxleCountName();
        tvAxleCount.setText(axleCountName);

        edTonnage.setText(data.getTonnage());

        reportOrgID = data.getReportOrgID();
        reportOrgLevel = data.getReportOrgLevel();
        tvReportOrgID.setText(reportOrgLevel);
        tvCheckTime.setText(data.getCheckDate());

        inStationID = data.getInStationID();
        inStationName = data.getInStationName();
        tvInStationID.setText(inStationName);

        laneID = data.getLaneID();
        laneName = data.getLaneName();
        tvLaneID.setText(laneName);

        edOprName.setText(data.getOprName());
        edShiftman.setText(data.getShiftman());

        isMix = data.getIsMix();
        isMixName = data.getIsMixName();
        tvGreenType.setText(isMixName);

        edGoodsName.setText(data.getGoodsName());
        edMixGoodsName.setText(data.getMixGoodsName());

        isEnjoy = data.getIsEnjoy();
        if (isEnjoy == 0) {
            rbIsEnjoyno.setChecked(true);
            rbIsEnjoyyes.setChecked(false);
            llFreeMoney.setVisibility(View.GONE);
            llEscapeMoney.setVisibility(View.VISIBLE);
        } else {
            rbIsEnjoyno.setChecked(false);
            rbIsEnjoyyes.setChecked(true);
            llFreeMoney.setVisibility(View.VISIBLE);
            llEscapeMoney.setVisibility(View.GONE);
        }
        edFreeMoney.setText(data.getFreeMoney());
        edEscapeMoney.setText(data.getEscapeMoney());
        edRemark.setText(data.getRemark());

        uuid = data.getGCListID();
        List<PhotoVideoData> list = presenter.getListPvById(uuid);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                myphoto.getGlide(list.get(i).getPhotoUrl());
            }
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
        intent.setClass(this, GreenRecordListActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMes(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }


    @Override
    public void OnClickAddImg() {

    }

    @OnClick({R.id.iv_more, R.id.iv_left, R.id.iv_phone, R.id.tv_check_time, R.id.rl_car_type
            , R.id.rl_green_type, R.id.rl_axleCount, R.id.rl_reportOrgID, R.id.rl_InStationID,
            R.id.rl_LaneID, R.id.comit_button})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_more:
                Intent intent = new Intent();
                intent.setClass(this, GreenRecordListActivity.class);
                this.startActivity(intent);
                break;
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.tv_check_time:
                DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(this);
                dateTimePickerDialog.dateTimePicKDialog(tvCheckTime, 0);
                break;
            case R.id.rl_InStationID:
                dropDownMenu.setDownValue(tvInStationID, "");
                break;
            case R.id.rl_LaneID:
                dropDownMenu2.setDownValue(tvLaneID, "");
                break;
            case R.id.rl_green_type:
                downKeyValue2.setDownValue(tvGreenType, "");
                break;
            case R.id.rl_car_type:
                downKeyValue3.setDownValue(tvCarType, "");
                break;
            case R.id.rl_axleCount:
                downKeyValue.setDownValue(tvAxleCount, "");
                break;
            case R.id.rl_reportOrgID:
                dropDownOrgMenu.setDownValue(tvReportOrgID, "");
                break;
            case R.id.comit_button:
                if (TextUtils.isEmpty(edCarNum.getText())) {
                    showMes("车牌号不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edCapacity.getText())) {
                    showMes("有效容积不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edTonnage.getText())) {
                    showMes("吨位(吨)不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edOprName.getText())) {
                    showMes("收费员不能为空！");
                    return;
                }
                if (TextUtils.isEmpty(edShiftman.getText())) {
                    showMes("班长不能为空！");
                    return;
                }

                if (isEnjoy == 0) {
                    if (TextUtils.isEmpty(edEscapeMoney.getText())) {
                        showMes("增收金额不能为空！");
                        return;
                    }
                } else {
                    if (TextUtils.isEmpty(edFreeMoney.getText())) {
                        showMes("减免金额不能为空！");
                        return;
                    }
                }

                if (TextUtils.isEmpty(edGoodsName.getText())) {
                    showMes("货物名称不能为空！");
                    return;
                }

                getShowData();
                presenter.startPresenter(files, data);
                break;
        }
    }

    public void getShowData() {
        data = new JCGreenChannelRecData();
        if (type == 1) {
            uuid = UUID.randomUUID().toString();
        }
        data.setUserID(Setting.USERID);
        data.setOperType(type);//1：新增 2：修改
        data.setGCListID(uuid);
        data.setVehPlateNo(edCarNum.getText().toString());
        data.setVehicleTypeID(vehicleTypeID);
        data.setVehicleTypeIDName(vehicleTypeIDName);
        if (!TextUtils.isEmpty(edFactoryType.getText())) {
            data.setFactoryType(edFactoryType.getText().toString());
        } else {
            data.setFactoryType("");
        }
        data.setCapacity(edCapacity.getText().toString());
        data.setAxleCount(axleCount);
        data.setAxleCountName(axleCountName);
        data.setTonnage(edTonnage.getText().toString());
        data.setReportOrgID(reportOrgID);
        data.setReportOrgLevel(reportOrgLevel);
        data.setCheckDate(tvCheckTime.getText().toString());
        data.setInStationID(inStationID);
        data.setInStationName(inStationName);
        data.setLaneID(laneID);
        data.setLaneName(laneName);
        data.setOprName(edOprName.getText().toString());
        data.setShiftman(edShiftman.getText().toString());
        data.setIsMix(isMix);
        data.setIsMixName(isMixName);
        data.setGoodsName(edGoodsName.getText().toString());
        if (!TextUtils.isEmpty(edMixGoodsName.getText())) {
            data.setMixGoodsName(edMixGoodsName.getText().toString());
        } else {
            data.setMixGoodsName("");
        }
        data.setIsEnjoy(isEnjoy);
        if (!TextUtils.isEmpty(edFreeMoney.getText())) {
            data.setFreeMoney(edFreeMoney.getText().toString());
        } else {
            data.setFreeMoney("");
        }
        if (!TextUtils.isEmpty(edEscapeMoney.getText())) {
            data.setEscapeMoney(edEscapeMoney.getText().toString());
        } else {
            data.setEscapeMoney("");
        }
        if (!TextUtils.isEmpty(edRemark.getText())) {
            data.setRemark(edRemark.getText().toString());
        } else {
            data.setRemark("");
        }

    }

    //轴数
    @Override
    public void onItemClick(String name, int id) {
        if (id == 111) {
            if (name.equals("正常")) {
                isMix = 0;
            } else if (name.equals("混装")) {
                isMix = 1;
            } else if (name.equals("不合法装载")) {
                isMix = 2;
            }
            isMixName = name;
        } else if (id == 1 || id == 11 || id == 5) {
            vehicleTypeID = id;
            vehicleTypeIDName = name;
        } else {
            axleCount = id;
            axleCountName = name;
        }

    }

    @Override
    public void onItemClick(int code, int type, String name) {
        switch (type) {
            case 1:
                inStationID = code;
                inStationName = name;
                break;
            case 2:
                laneID = code;
                laneName = name;
                break;
          /*  case 6:
                vehicleTypeID = code;
                vehicleTypeIDName = name;
                break;*/
            case 111://上报单位
                reportOrgID = code;
                reportOrgLevel = name;
                break;
        }
    }
}
