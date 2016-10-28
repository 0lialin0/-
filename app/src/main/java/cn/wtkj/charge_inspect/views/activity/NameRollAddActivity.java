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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.BlackListData;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollAddPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollAddPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.NameRollAddView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.util.Setting;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownKeyValue;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;
import cn.wtkj.charge_inspect.views.custom.DropDownOrgMenu;
import cn.wtkj.charge_inspect.views.custom.MyPhotos;
import cn.wtkj.charge_inspect.views.custom.PhotoAdapter;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/21.
 */
public class NameRollAddActivity extends MvpBaseActivity<NameRollAddPresenter> implements
        NameRollAddView, MyPhotos.OnClickAddImgListener, View.OnClickListener,
        OnItemClickListener, OnItemClickListener3 {

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
    @Bind(R.id.myphoto)
    MyPhotos myphoto;
    @Bind(R.id.comit_button)
    TextView comitButton;
    @Bind(R.id.ll_myphotos)
    LinearLayout llMyphotos;
    @Bind(R.id.tv_photo_title)
    TextView tvPhotoTitle;

    //   车辆信息
    @Bind(R.id.VepPlateNo)
    EditText VepPlateNo;
    @Bind(R.id.VepPlateNoColorName)
    TextView VepPlateNoColorName;
    @Bind(R.id.FactoryType)
    EditText FactoryType;
    @Bind(R.id.VepColorName)
    TextView VepColorName;
    @Bind(R.id.VehTypeName)
    TextView VehTypeName;
    @Bind(R.id.VehicleTypeName)
    TextView VehicleTypeName;
    @Bind(R.id.Seating)
    EditText Seating;
    @Bind(R.id.AxleCountName)
    TextView AxleCountName;
    @Bind(R.id.Tonnage)
    EditText Tonnage;

    //   违章信息
    @Bind(R.id.CardNo)
    EditText CardNo;
    @Bind(R.id.GenDT)
    TextView GenDT;
    @Bind(R.id.PeccancyTypeName)
    TextView PeccancyTypeName;
    @Bind(R.id.PeccancyOrgName)
    TextView PeccancyOrgName;
    @Bind(R.id.InOrgName)
    TextView InOrgName;
    @Bind(R.id.OutOrgName)
    TextView OutOrgName;
    @Bind(R.id.GenCause)
    EditText GenCause;


    // 车辆所有者信息
    @Bind(R.id.Owner)
    EditText Owner;
    @Bind(R.id.OwnerTypeName)
    TextView OwnerTypeName;
    @Bind(R.id.OwnerAddress)
    EditText OwnerAddress;
    @Bind(R.id.Postalcode)
    EditText Postalcode;
    @Bind(R.id.TeletePhone)
    EditText TeletePhone;
    @Bind(R.id.MobilePhone)
    EditText MobilePhone;
    @Bind(R.id.HistoryInfo)
    EditText HistoryInfo;

    @Bind(R.id.car_owner_info)
    LinearLayout llCarOwnerInfo;
    @Bind(R.id.peccancy_info)
    LinearLayout llPeccancyInfo;
    @Bind(R.id.car_info)
    LinearLayout llCarInfo;
    @Bind(R.id.ll_seating)
    LinearLayout llSeating;


    @Bind(R.id.ll_zhoushu)
    LinearLayout llZhoushu;
    @Bind(R.id.ll_dunshuo)
    LinearLayout llDunshuo;

    private List<File> files;
    private ProgressDialog progressDialog;
    private AlertDialog alter;

    private int axleCount;
    private String axleCountName;
    private JCBlackListData data;
    private int type = 1;
    private String uuid;

    private int vehicleTypeID; //车辆类别ID
    private String vehicleTypeName; //车辆类别

    private int vehType; //车型类别ID
    private String vehTypeName; //车型类别

    private int vepColor; //车身颜色ID
    private String vepColorName; //车身颜色

    private int vepPlateNoColor;//车牌颜色ID
    private String vepPlateNoColorName;//车牌颜色

    private int peccancyTypeID; //违章类型ID
    private String peccancyTypeName; //违章类型

    private int peccancyOrgID; //违章地点ID
    private String peccancyOrgName; //违章地点

    private int inOrgID; //始行驶区间ID
    private String inOrgName; //始行驶区间

    private int outOrgID;//结行驶区间ID
    private String outOrgName;//结行驶区间

    private int ownerType; //所有者类型ID
    private String ownerTypeName; //所有者类型

    private boolean state = true;
    private List<DropDownMenu> dropDownMenuList = new ArrayList<>();
    private List<Integer> dataMapIdList = new ArrayList<>();
    private List<String> dataMapNameList = new ArrayList<>();
    private List<List<ConstAllData.MData.info>> dataMapList = new ArrayList<>();
    private DropDownKeyValue downKeyValue;
    private DropDownKeyValue downKeyValueOwner;
    private DropDownOrgMenu dropDownOrgMenu;

    private int nameType = 0;
    private String nameTitle = "添加黑名单";

    @Override
    protected NameRollAddPresenter createPresenter() {
        return new NameRollAddPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        nameType = Integer.valueOf(intent.getStringExtra("nameType"));
        nameTitle = intent.getStringExtra("nameTitle");

        setContentView(R.layout.activity_name_roll_add);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(nameTitle);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
    }


    public void initView() {
        if (nameType == 0) {
            llCarOwnerInfo.setVisibility(View.GONE);
        } else if (nameType == 2) {
            llCarOwnerInfo.setVisibility(View.GONE);
            llPeccancyInfo.setVisibility(View.GONE);
        }
        if(nameType==2){
            tvPhotoTitle.setText("上传照片");
        }
        myphoto.setNameType(nameType);
        myphoto.setFragment(this);
        myphoto.setOnClickAddImgListener(this);
        files = new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        setView();
    }


    @Override
    public void setView() {
        setDropDown();
        showView();

        String outlist = getIntent().getStringExtra("type");
        if (outlist != null) {
            type = 1;
            if (outlist.equals("xiafa")) {
               // BlackListData.MData.info blackListData = (BlackListData.MData.info) getIntent().getSerializableExtra(
                //        NameRollXiafaActivity.DATA_TAG);
                //showViewXiafaData(blackListData);
                String id=getIntent().getStringExtra("id");
                presenter.getBlackData(id,nameType);
                comitButton.setVisibility(View.GONE);
                llMyphotos.setVisibility(View.GONE);
            } else {
                OutListData.MData.info info = (OutListData.MData.info) getIntent().getSerializableExtra(
                        OutListSelShowActivity.DATA_TAG);
                showViewDataByOutList(info);
            }

        } else {
            JCBlackListData data = (JCBlackListData) getIntent().getSerializableExtra(
                    NameRollManageActivity.DATA_TAG);
            if (data != null) {
                showViewData(data);
                type = 2;
                nameTitle = "修改黑名单";
                tvTitle.setText(nameTitle);
            } else {
                type = 1;
            }
        }

    }

    @Override
    public void setDropDown() {
        //轴数
        List<KeyValueData> zhoushuo = presenter.setDropDown();
        downKeyValue = new DropDownKeyValue(this, zhoushuo);
        downKeyValue.setId(1);
        downKeyValue.setOnItemClickListener(this);
        axleCountName = zhoushuo.get(0).getValue();
        axleCount = Integer.valueOf(zhoushuo.get(0).getId());
        AxleCountName.setText(zhoushuo.get(0).getValue());

        //所有者类型
        List<KeyValueData> owerType = new ArrayList<>();
        owerType.add(new KeyValueData("0", "个人"));
        owerType.add(new KeyValueData("1", "单位"));
        downKeyValueOwner = new DropDownKeyValue(this, owerType);
        downKeyValueOwner.setId(1);
        downKeyValueOwner.setOnItemClickListener(this);

        ownerTypeName = owerType.get(0).getValue();
        ownerType = Integer.valueOf(owerType.get(0).getId());
        OwnerTypeName.setText(owerType.get(0).getValue());

    }

    @Override
    public void showView() {
        GenDT.setText(ResponeUtils.getTime());

        //1：站址, 3：车牌颜色, 4：车身颜色, 5：车型类别, 6：车辆类别, 7：违章类型

        dataMapIdList = Arrays.asList(1, 3, 4, 5, 6, 7);
        dataMapNameList = Arrays.asList("", "VepPlateNoColorName", "VepColorName", "VehTypeName", "VehicleTypeName", "PeccancyTypeName");

        for (int i = 0; i < dataMapIdList.size(); i++) {
            List<ConstAllData.MData.info> dataMap = presenter.getConstByType(dataMapIdList.get(i));

            DropDownMenu dropDownMenu = new DropDownMenu(this, dataMap);
            dropDownMenu.setOnItemClickListener(this);
            dropDownMenuList.add(dropDownMenu);
            dataMapList.add(dataMap);

            if (dataMap.size() > 0) {
                String defaultName = dataMap.get(0).getName();
                int defaultCode = dataMap.get(0).getCode();

                switch (i) {
                    case 0:
                        InOrgName.setText(defaultName);
                        inOrgID = defaultCode;
                        inOrgName = defaultName;
                        OutOrgName.setText(defaultName);
                        outOrgID = defaultCode;
                        outOrgName = defaultName;
                        break;
                    case 1:
                        VepPlateNoColorName.setText(defaultName);
                        vepPlateNoColor = defaultCode;
                        vepPlateNoColorName = defaultName;
                        break;
                    case 2:
                        VepColorName.setText(defaultName);
                        vepColor = defaultCode;
                        vepColorName = defaultName;
                        break;
                    case 3:
                        VehTypeName.setText(defaultName);
                        vehType = defaultCode;
                        vehTypeName = defaultName;
                        break;
                    case 4:
                        VehicleTypeName.setText(defaultName);
                        vehicleTypeID = defaultCode;
                        vehicleTypeName = defaultName;
                        break;
                    case 5:
                        PeccancyTypeName.setText(defaultName);
                        peccancyTypeID = defaultCode;
                        peccancyTypeName = defaultName;
                        break;
                }
            }
        }


        //违章地点
        List<ViewOrganizationData.MData.info> infos = presenter.getOrg(Setting.ORGID, Setting.ORGLEVEL);
        if (infos.size() > 0) {
            dropDownOrgMenu = new DropDownOrgMenu(this, infos);
            dropDownOrgMenu.setId(infos.get(0).getOrgCode());
            dropDownOrgMenu.setOnItemClickListener(this);
            peccancyOrgID = infos.get(0).getOrgCode();
            peccancyOrgName = infos.get(0).getShortName();
            PeccancyOrgName.setText(infos.get(0).getShortName());
        }
    }

    //显示流水信息内容在页面上
    private void showViewDataByOutList(OutListData.MData.info info) {
        VepPlateNo.setText(info.getVehplateNo());

        vehType = info.getVehType();
        if (vehType == 11 || vehType == 12 || vehType == 13 || vehType == 14 || vehType == 15) {
            llZhoushu.setVisibility(View.VISIBLE);
            llDunshuo.setVisibility(View.VISIBLE);
            llSeating.setVisibility(View.GONE);
            state = false;
        }
        vehTypeName = info.getVehTypeName();
        VehTypeName.setText(vehTypeName);

        axleCountName = info.getAxleNumber() + "轴";
        axleCount = info.getAxleNumber();
        AxleCountName.setText(axleCountName);
        CardNo.setText(info.getCardNo() + "");


    }

    //显示下发名单详情内容
    @Override
    public void showViewXiafaData(BlackListData.MData.info data) {
        VepPlateNo.setText(data.getVepPlateNo());
        vepPlateNoColor = data.getVepPlateNoColor();
        vepPlateNoColorName = data.getVepPlateNoColorName();
        VepPlateNoColorName.setText(data.getVepPlateNoColorName());

        vepColor = data.getVepColor();
        vepColorName = data.getVepColorName();
        VepColorName.setText(data.getVepColorName());

        vehType = data.getVehType();
        if (vehType == 11 || vehType == 12 || vehType == 13 || vehType == 14 || vehType == 15) {
            llZhoushu.setVisibility(View.VISIBLE);
            llDunshuo.setVisibility(View.VISIBLE);
            llSeating.setVisibility(View.GONE);
            state = false;
        }
        vehTypeName = data.getVehTypeName();
        VehicleTypeName.setText(data.getVehTypeName());

        vehicleTypeID = data.getVehicleTypeID();
        vehicleTypeName = data.getVehicleTypeName();
        VehicleTypeName.setText(data.getVehicleTypeName());

        peccancyTypeID = data.getPeccancyTypeID();
        peccancyTypeName = data.getPeccancyTypeName();
        PeccancyTypeName.setText(data.getPeccancyTypeName());

        inOrgID = data.getInOrgID();
        inOrgName = data.getInOrgName();
        PeccancyTypeName.setText(data.getInOrgName());

        outOrgID = data.getOutOrgID();
        outOrgName = data.getOutOrgName();
        OutOrgName.setText(data.getOutOrgName());

        axleCountName = data.getAxleCount() + "轴";
        axleCount = data.getAxleCount();
        AxleCountName.setText(data.getAxleCountName());

        peccancyOrgID = data.getPeccancyOrgID();
        peccancyOrgName = data.getPeccancyOrgName();
        PeccancyOrgName.setText(data.getPeccancyOrgName());

        FactoryType.setText(data.getFactoryType());
        Seating.setText(data.getSeating() + "");
        Tonnage.setText(data.getTonnage());
        CardNo.setText(data.getCardNo());
        GenDT.setText(data.getGenDT());
        GenCause.setText(data.getGenCause());

        /* 车辆所有者信息 */
        Owner.setText(data.getOwner());
        if (data.getOwnerType() == 0) {
            ownerTypeName = "个人";
        } else {
            ownerTypeName = "单位";
        }
        ownerType = data.getOwnerType();
        OwnerTypeName.setText(data.getOwnerTypeName());
        OwnerAddress.setText(data.getOwnerAddress());
        Postalcode.setText(data.getPostalcode());
        TeletePhone.setText(data.getTeletePhone());
        MobilePhone.setText(data.getMobilePhone());
        HistoryInfo.setText(data.getHistoryInfo());

        uuid = data.getBlackListID();


       /* List<PhotoVideoData> list = presenter.getPvList(uuid, nameType);
        if (list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                files.add(new File(list.get(i).getFileUrl()));
                myphoto.getGlide(list.get(i).getFileUrl());
            }

            myphoto.setFiles(files);
        }*/
    }

    //显示值在页面上
    private void showViewData(JCBlackListData data) {
        if (type == 2) {
            ivPhone.setVisibility(View.VISIBLE);
            ivMore.setVisibility(View.VISIBLE);
        }

        VepPlateNo.setText(data.getVepPlateNo());
        vepPlateNoColor = data.getVepPlateNoColor();
        vepPlateNoColorName = data.getVepPlateNoColorName();
        VepPlateNoColorName.setText(data.getVepPlateNoColorName());

        vepColor = data.getVepColor();
        vepColorName = data.getVepColorName();
        VepColorName.setText(data.getVepColorName());

        vehType = data.getVehType();
        if (vehType == 11 || vehType == 12 || vehType == 13 || vehType == 14 || vehType == 15) {
            llZhoushu.setVisibility(View.VISIBLE);
            llDunshuo.setVisibility(View.VISIBLE);
            llSeating.setVisibility(View.GONE);
            state = false;
        }
        vehTypeName = data.getVehTypeName();
        VehicleTypeName.setText(data.getVehTypeName());

        vehicleTypeID = data.getVehicleTypeID();
        vehicleTypeName = data.getVehicleTypeName();
        VehicleTypeName.setText(data.getVehicleTypeName());

        peccancyTypeID = data.getPeccancyTypeID();
        peccancyTypeName = data.getPeccancyTypeName();
        PeccancyTypeName.setText(data.getPeccancyTypeName());

        inOrgID = data.getInOrgID();
        inOrgName = data.getInOrgName();
        PeccancyTypeName.setText(data.getInOrgName());

        outOrgID = data.getOutOrgID();
        outOrgName = data.getOutOrgName();
        OutOrgName.setText(data.getOutOrgName());

        axleCountName = data.getAxleCountName();
        axleCount = data.getAxleCount();
        AxleCountName.setText(data.getAxleCountName());

        peccancyOrgID = data.getPeccancyOrgID();
        peccancyOrgName = data.getPeccancyOrgName();
        PeccancyOrgName.setText(data.getPeccancyOrgName());

        FactoryType.setText(data.getFactoryType());
        Seating.setText(data.getSeating() + "");
        Tonnage.setText(data.getTonnage());
        CardNo.setText(data.getCardNo());
        GenDT.setText(data.getGenDT());
        GenCause.setText(data.getGenCause());

        /* 车辆所有者信息 */
        Owner.setText(data.getOwner());
        ownerTypeName = data.getOwnerTypeName();
        ownerType = data.getOwnerType();
        OwnerTypeName.setText(data.getOwnerTypeName());
        OwnerAddress.setText(data.getOwnerAddress());
        Postalcode.setText(data.getPostalcode());
        TeletePhone.setText(data.getTeletePhone());
        MobilePhone.setText(data.getMobilePhone());
        HistoryInfo.setText(data.getHistoryInfo());

        uuid = data.getBlackListID();
        List<PhotoVideoData> list = presenter.getPvList(uuid, nameType);
        if (list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                files.add(new File(list.get(i).getFileUrl()));
                myphoto.getGlide(list.get(i).getFileUrl());
            }

            myphoto.setFiles(files);
        }
    }

    @Override
    public void showLoding() {
        progressDialog.setMessage("正在保存，请等待..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void himeDialog() {
        progressDialog.hide();
    }

    @Override
    public void nextView() {
        this.finish();
        Intent intent = new Intent();
        intent.setClass(this, NameRollManageActivity.class);
        startActivity(intent);
    }

    @Override
    public void showToast(String msg) {
        show(this, msg, Toast.LENGTH_SHORT);
    }

    @Override
    public void OnClickAddImg() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = myphoto.setImage(requestCode, resultCode, data);
        if (file != null) {
            files.add(file);
        }
    }

    @OnClick({R.id.iv_left, R.id.comit_button, R.id.GenDT, R.id.rl_car_color,
            R.id.rl_car_body_color, R.id.rl_veh_type, R.id.rl_car_type, R.id.rl_weizhang_type,
            R.id.InOrgName, R.id.OutOrgName, R.id.rl_zhoushuo, R.id.rl_weizhang_addr})
    @Override
    public void onClick(View view) {
        //1：站址, 3：车牌颜色, 4：车身颜色, 5：车型类别, 6：车辆类别, 7：违章类型

        switch (view.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.GenDT:
                DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(this);
                dateTimePickerDialog.dateTimePicKDialog(GenDT, 0);
                break;
            case R.id.rl_car_color:
                dropDownMenuList.get(1).setDownValue(VepPlateNoColorName, "");
                break;
            case R.id.rl_car_body_color:
                dropDownMenuList.get(2).setDownValue(VepColorName, "");
                break;
            case R.id.rl_car_type:
                dropDownMenuList.get(4).setDownValue(VehicleTypeName, "");
                break;
            case R.id.rl_veh_type:
                dropDownMenuList.get(3).setDownValue(VehTypeName, "");
                break;
            case R.id.rl_weizhang_type:
                dropDownMenuList.get(5).setDownValue(PeccancyTypeName, "");
                break;
            case R.id.InOrgName:
                dropDownMenuList.get(0).setDownValue(InOrgName, "");
                break;
            case R.id.OutOrgName:
                dropDownMenuList.get(0).setDownValue(OutOrgName, "");
                break;

            case R.id.rl_zhoushuo:
                downKeyValue.setDownValue(AxleCountName, "");
                break;
            case R.id.rl_weizhang_addr:
                dropDownOrgMenu.setDownValue(PeccancyOrgName, "");
                break;
            case R.id.rl_owner_type:
                downKeyValueOwner.setDownValue(OwnerTypeName, "");
                break;
            case R.id.comit_button:
                if (nameType == 0 || nameType == 1) {
                    if (TextUtils.isEmpty(VepPlateNo.getText())) {
                        showToast("车牌号不能为空！");
                        return;
                    }
                    if (state) {
                        if (TextUtils.isEmpty(Seating.getText())) {
                            showToast("座位号不能为空！");
                            return;
                        }
                    }
                }

                getShowData();
                presenter.startPresenter(files, data);
                break;

        }
    }


    public void getShowData() {
        data = new JCBlackListData();
        if (type == 1) {
            uuid = UUID.randomUUID().toString();
        }

        if (nameType == 0) {
            data.setBlackListID(uuid);
        } else if (nameType == 1) {
            data.setVehicleID(uuid);
        } else {
            data.setYListID(uuid);
        }

        data.setUserID(Setting.USERID);
        data.setOperType(type);//1：新增 2：修改

        /* 车辆信息 */
        data.setVepPlateNo(VepPlateNo.getText().toString());
        data.setVepPlateNoColor(vepPlateNoColor);
        data.setVepPlateNoColorName(vepPlateNoColorName);

        if (!TextUtils.isEmpty(FactoryType.getText())) {
            data.setFactoryType(FactoryType.getText().toString());
        } else {
            data.setFactoryType("");
        }
        data.setVepColor(vepColor);
        data.setVepColorName(vepColorName);

        data.setVehicleTypeID(vehicleTypeID);
        data.setVehicleTypeName(vehicleTypeName);
        data.setVehType(vehType);
        data.setVehTypeName(vehTypeName);
        data.setAxleCount(axleCount);
        data.setAxleCountName(axleCountName);
        if (!Tonnage.getText().toString().replaceAll(" ", "").equals("")) {
            data.setTonnage(Tonnage.getText().toString());
        } else {
            data.setTonnage("0");
        }

        if (!TextUtils.isEmpty(Seating.getText())) {
            data.setSeating(Integer.valueOf(Seating.getText().toString()));
        }

        /* 违章信息 */
        data.setPeccancyTypeID(peccancyTypeID);
        data.setPeccancyTypeName(peccancyTypeName);
        data.setGenDT(GenDT.getText().toString());
        data.setPeccancyOrgID(peccancyOrgID);
        data.setPeccancyOrgName(peccancyOrgName);
        data.setInOrgID(inOrgID);
        data.setInOrgName(inOrgName);
        data.setOutOrgName(outOrgName);
        data.setOutOrgID(outOrgID);

        if (!TextUtils.isEmpty(CardNo.getText())) {
            data.setCardNo(CardNo.getText().toString());
        } else {
            data.setCardNo("");
        }

        if (!TextUtils.isEmpty(GenCause.getText())) {
            data.setGenCause(GenCause.getText().toString());
        } else {
            data.setGenCause("");
        }

         /* 车辆所有者信息*/
        if (!TextUtils.isEmpty(Owner.getText())) {
            data.setOwner(Owner.getText().toString());
        } else {
            data.setOwner("");
        }

        data.setOwnerType(ownerType);
        data.setOwnerTypeName(ownerTypeName);
        if (!TextUtils.isEmpty(OwnerAddress.getText())) {
            data.setOwnerAddress(OwnerAddress.getText().toString());
        } else {
            data.setOwnerAddress("");
        }

        if (!TextUtils.isEmpty(Postalcode.getText())) {
            data.setPostalcode(Postalcode.getText().toString());
        } else {
            data.setPostalcode("");
        }

        if (!TextUtils.isEmpty(TeletePhone.getText())) {
            data.setTeletePhone(TeletePhone.getText().toString());
        } else {
            data.setTeletePhone("");
        }

        if (!TextUtils.isEmpty(MobilePhone.getText())) {
            data.setMobilePhone(MobilePhone.getText().toString());
        } else {
            data.setMobilePhone("");
        }

        if (!TextUtils.isEmpty(HistoryInfo.getText())) {
            data.setHistoryInfo(HistoryInfo.getText().toString());
        } else {
            data.setHistoryInfo("");
        }

        data.setNameType(nameType);
    }


    @Override
    public void onItemClick(int code, int type, String name) {
        switch (type) {
            case 5://车型类别
                if (code == 0 || code == 1 || code == 2 || code == 3 || code == 4) {
                    llZhoushu.setVisibility(View.GONE);
                    llDunshuo.setVisibility(View.GONE);
                    llSeating.setVisibility(View.VISIBLE);
                    state = true;
                } else if (code == 11 || code == 12 || code == 13 || code == 14 || code == 15) {
                    llZhoushu.setVisibility(View.VISIBLE);
                    llDunshuo.setVisibility(View.VISIBLE);
                    llSeating.setVisibility(View.GONE);
                    state = false;
                }
                vehType = code;
                vehTypeName = name;
                break;
            case 3:
                vepPlateNoColor = code;
                vepPlateNoColorName = name;
                break;
            case 4:
                vepColor = code;
                vepColorName = name;
                break;
            case 6:
                vehicleTypeID = code;
                vehicleTypeName = name;
                break;
            case 7:
                peccancyTypeID = code;
                peccancyTypeName = name;
                break;
            case 1:
                inOrgID = code;
                inOrgName = name;
                break;
            case 111://违章地点
                peccancyOrgID = code;
                peccancyOrgName = name;
                break;

        }
    }

    //轴数
    @Override
    public void onItemClick(String name, int id) {
        if (name.equals("个人") || name.equals("单位")) {
            ownerTypeName = name;
            ownerType = id;
        } else {
            axleCount = id;
            axleCountName = name;
        }
    }
}
