package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
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
    private int isEnjoy=0;//是否减免

    private List<File> files;
    private ProgressDialog progressDialog;
    private AlertDialog alter;
    private DropDownKeyValue downKeyValue;
    private DropDownKeyValue downKeyValue2;
    private DropDownMenu dropDownMenu;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;
    private DropDownOrgMenu dropDownOrgMenu;

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
        presenter.startPresenter();
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.green_name);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ivPhone.setVisibility(View.GONE);
    }


    public void initView() {
        tvCheckTime.setText(ResponeUtils.getTime());

        myphoto.setFragment(this);
        myphoto.setOnClickAddImgListener(this);
        files = new ArrayList<>();

        rgIsEnjoy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbIsEnjoyyes.getId()) {
                    isEnjoy=1;
                    llFreeMoney.setVisibility(View.VISIBLE);
                    llEscapeMoney.setVisibility(View.GONE);
                } else {
                    isEnjoy=0;
                    llFreeMoney.setVisibility(View.GONE);
                    llEscapeMoney.setVisibility(View.VISIBLE);
                }
            }
        });

        List<KeyValueData> type = new ArrayList<>();
        type.add(new KeyValueData("111", "正常"));
        type.add(new KeyValueData("111", "混装"));
        type.add(new KeyValueData("111", "不合法装载"));
        downKeyValue2 = new DropDownKeyValue(this, type);
        downKeyValue2.setId(1);
        downKeyValue2.setOnItemClickListener(this);
        isMix=Integer.valueOf(type.get(0).getId());
        isMixName=type.get(0).getValue();
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
        List<ConstAllData.MData.info> cartype = presenter.getConstByType(6);
        dropDownMenu3 = new DropDownMenu(this, cartype);
        dropDownMenu3.setOnItemClickListener(this);
        if (cartype.size() > 0) {
            vehicleTypeID = cartype.get(0).getCode();
            vehicleTypeIDName = cartype.get(0).getName();
            tvCarType.setText(cartype.get(0).getName());
        }

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

    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoging() {

    }

    @Override
    public void nextView(String phone) {
        myphoto.clear();
    }

    @Override
    public void showMes(String msg) {

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
            case R.id.rl_car_type:
                dropDownMenu3.setDownValue(tvCarType, "");
                break;
            case R.id.rl_green_type:
                downKeyValue2.setDownValue(tvGreenType, "");
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
                    showMes("有效容积(%)不能为空！");
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
                if (TextUtils.isEmpty(edGoodsName.getText())) {
                    showMes("货物名称不能为空！");
                    return;
                }
                break;
        }
    }

    //轴数
    @Override
    public void onItemClick(String name, int id) {
        if(id==111){
            if(name.equals("正常")){
                isMix=0;
            }else if(name.equals("混装")){
                isMix=1;
            }else if(name.equals("不合法装载")){
                isMix=2;
            }
            isMixName=name;
        }else{
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
            case 6:
                vehicleTypeID = code;
                vehicleTypeIDName = name;
                break;
            case 111://上报单位
                reportOrgID = code;
                reportOrgLevel = name;
                break;
        }
    }
}
