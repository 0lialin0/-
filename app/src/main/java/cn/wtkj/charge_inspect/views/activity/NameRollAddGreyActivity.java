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
import java.util.List;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
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

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/21.
 */
public class NameRollAddGreyActivity extends MvpBaseActivity<NameRollAddPresenter> implements
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
    @Bind(R.id.tv_weizhang_time)
    TextView tvWeizhangTime;
    @Bind(R.id.tv_car_color)
    TextView tvCarColor;
    @Bind(R.id.tv_car_body_color)
    TextView tvCarBodyColor;
    @Bind(R.id.tv_car_type)
    TextView tvCarType;
    @Bind(R.id.tv_veh_type)
    TextView tvVehType;
    @Bind(R.id.tv_weizhang_type)
    TextView tvWeizhangType;
    @Bind(R.id.tv_weizhang_addr)
    TextView tvWeizhangAddr;

    @Bind(R.id.ed_car_num)
    EditText edCarNum;
    @Bind(R.id.ed_factory_type)
    EditText edFactoryType;

    @Bind(R.id.ed_zhoushou)
    TextView edZhoushou;
    @Bind(R.id.ed_dunshuo)
    EditText edDunshuo;
    @Bind(R.id.ed_remark)
    EditText edRemark;
    @Bind(R.id.ed_seating)
    EditText edSeating;
    @Bind(R.id.ed_cardNo)
    EditText edCardNo;
    @Bind(R.id.ed_owner)
    EditText edOwner;
    @Bind(R.id.tv_owner_type)
    TextView tvOwnerType;
    @Bind(R.id.ed_grey_addr)
    EditText edGreAddr;
    @Bind(R.id.ed_grey_postal)
    EditText edGreyPostal;
    @Bind(R.id.ed_grey_phone)
    EditText edGreyPhone;
    @Bind(R.id.ed_grey_mobile_phone)
    EditText edGreyMobilePhone;
    @Bind(R.id.ed_grey_history_info)
    EditText edGreyHistoryInfo;


    @Bind(R.id.ll_seating)
    LinearLayout llSeating;
    @Bind(R.id.ll_zhoushu)
    LinearLayout llZhoushu;
    @Bind(R.id.ll_dunshuo)
    LinearLayout llDunshuo;

    private List<File> files;
    private ProgressDialog progressDialog;
    private AlertDialog alter;

    private boolean state = true;

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

    private int ownerType; //所有者类型ID
    private String ownerTypeName; //所有者类型

    private int peccancyOrgID; //违章地点ID
    private String peccancyOrgName; //违章地点

    private String name_title = "添加灰名单";

    private DropDownMenu dropDownMenu;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;
    private DropDownMenu dropDownMenu4;
    private DropDownMenu dropDownMenu5;

    private DropDownKeyValue downKeyValue;
    private DropDownKeyValue downKeyValue2;
    private DropDownKeyValue downKeyValue3;
    private DropDownOrgMenu dropDownOrgMenu;

    @Override
    protected NameRollAddPresenter createPresenter() {
        return new NameRollAddPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_roll_add_grey);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(name_title);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
    }


    public void initView() {
        myphoto.setFragment(this);
        myphoto.setOnClickAddImgListener(this);
        files = new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        setView();
    }

    @Override
    public void setView() {
        JCBlackListData data = (JCBlackListData) getIntent().getSerializableExtra(
                NameRollManageActivity.DATA_TAG);
        setDropDown();
        showView();
        if (data != null) {
            showViewData(data);
            type = 2;
            name_title = "修改灰名单";
            tvTitle.setText(name_title);
        } else {
            type = 1;
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
        edZhoushou.setText(zhoushuo.get(0).getValue());


        //所有者类型
        List<KeyValueData> owerType = new ArrayList<>();
        owerType.add(new KeyValueData("0", "个人"));
        owerType.add(new KeyValueData("1", "单位"));
        downKeyValue3 = new DropDownKeyValue(this, owerType);
        downKeyValue3.setId(1);
        downKeyValue3.setOnItemClickListener(this);
        ownerTypeName = owerType.get(0).getValue();
        ownerType = Integer.valueOf(owerType.get(0).getId());
        tvOwnerType.setText(owerType.get(0).getValue());
    }


    @Override
    public void showView() {
        tvWeizhangTime.setText(ResponeUtils.getTime());

        //1：站址, 3：车牌颜色, 4：车身颜色, 5：车型类别, 6：车辆类别, 7：违章类型

        //车牌颜色
        List<ConstAllData.MData.info> colorList = presenter.getConstByType(3);
        dropDownMenu = new DropDownMenu(this, colorList);
        dropDownMenu.setOnItemClickListener(this);
        if (colorList.size() > 0) {
            vepPlateNoColor = colorList.get(0).getCode();
            vepPlateNoColorName = colorList.get(0).getName();
            tvCarColor.setText(colorList.get(0).getName());
        }

        //车身颜色
        List<ConstAllData.MData.info> colorbodyList = presenter.getConstByType(4);
        dropDownMenu2 = new DropDownMenu(this, colorbodyList);
        dropDownMenu2.setOnItemClickListener(this);
        if (colorbodyList.size() > 0) {
            vepColor = colorbodyList.get(0).getCode();
            vepColorName = colorbodyList.get(0).getName();
            tvCarBodyColor.setText(colorbodyList.get(0).getName());
        }

        //车型类别
        List<ConstAllData.MData.info> vehTtypList = presenter.getConstByType(5);
        dropDownMenu3 = new DropDownMenu(this, vehTtypList);
        dropDownMenu3.setOnItemClickListener(this);
        if (vehTtypList.size() > 0) {
            vehType = vehTtypList.get(0).getCode();
            vehTypeName = vehTtypList.get(0).getName();
            tvVehType.setText(vehTtypList.get(0).getName());
        }

        //车辆类别
        List<ConstAllData.MData.info> carTypeList = presenter.getConstByType(6);
        dropDownMenu4 = new DropDownMenu(this, carTypeList);
        dropDownMenu4.setOnItemClickListener(this);
        if (carTypeList.size() > 0) {
            vehicleTypeID = carTypeList.get(0).getCode();
            vehicleTypeName = carTypeList.get(0).getName();
            tvCarType.setText(carTypeList.get(0).getName());
        }

        //违章类型
        List<ConstAllData.MData.info> weiList = presenter.getConstByType(7);
        dropDownMenu5 = new DropDownMenu(this, weiList);
        dropDownMenu5.setOnItemClickListener(this);
        if (weiList.size() > 0) {
            peccancyTypeID = weiList.get(0).getCode();
            peccancyTypeName = weiList.get(0).getName();
            tvWeizhangType.setText(weiList.get(0).getName());
        }

        //违章地点
        List<ViewOrganizationData.MData.info> infos = presenter.getOrg(Setting.ORGID, Setting.ORGLEVEL);
        if (infos.size() > 0) {
            dropDownOrgMenu = new DropDownOrgMenu(this, infos);
            dropDownOrgMenu.setId(infos.get(0).getOrgCode());
            dropDownOrgMenu.setOnItemClickListener(this);
            peccancyOrgID = infos.get(0).getOrgCode();
            peccancyOrgName = infos.get(0).getShortName();
            tvWeizhangAddr.setText(infos.get(0).getShortName());
        }
    }


    //显示值在页面上
    private void showViewData(JCBlackListData data) {
        if (type == 2) {
            ivPhone.setVisibility(View.VISIBLE);
            ivMore.setVisibility(View.VISIBLE);
        }
        edCarNum.setText(data.getVepPlateNo());
        vepPlateNoColor = data.getVepPlateNoColor();
        vepPlateNoColorName = data.getVepPlateNoColorName();
        tvCarColor.setText(data.getVepPlateNoColorName());

        vepColor = data.getVepColor();
        vepColorName = data.getVepColorName();
        tvCarBodyColor.setText(data.getVepColorName());

        vehType = data.getVehType();
        if (vehType == 11 || vehType == 12 || vehType == 13 || vehType == 14 || vehType == 15) {
            llZhoushu.setVisibility(View.VISIBLE);
            llDunshuo.setVisibility(View.VISIBLE);
            llSeating.setVisibility(View.GONE);
            state = false;
        }
        vehTypeName = data.getVehTypeName();
        tvVehType.setText(data.getVehTypeName());

        vehicleTypeID = data.getVehicleTypeID();
        vehicleTypeName = data.getVehicleTypeName();
        tvCarType.setText(data.getVehicleTypeName());

        peccancyTypeID = data.getPeccancyTypeID();
        peccancyTypeName = data.getPeccancyTypeName();
        tvWeizhangType.setText(data.getPeccancyTypeName());

        axleCountName = data.getAxleCountName();
        axleCount = data.getAxleCount();
        edZhoushou.setText(data.getAxleCountName());

        peccancyOrgID = data.getPeccancyOrgID();
        peccancyOrgName = data.getPeccancyOrgName();
        tvWeizhangAddr.setText(data.getPeccancyOrgName());

        edFactoryType.setText(data.getFactoryType());
        edSeating.setText(data.getSeating() + "");
        edDunshuo.setText(data.getTonnage());
        edCardNo.setText(data.getCardNo());
        tvWeizhangTime.setText(data.getGenDT());
        edRemark.setText(data.getPeccancyDescription());

        edOwner.setText(data.getOwner());

        ownerTypeName = data.getOwnerTypeName();
        ownerType = data.getOwnerType();
        tvOwnerType.setText(data.getOwnerTypeName());

        edGreAddr.setText(data.getOwnerAddress());
        edGreyPostal.setText(data.getPostalcode());
        edGreyPhone.setText(data.getTeletePhone());
        edGreyMobilePhone.setText(data.getMobilePhone());
        edGreyHistoryInfo.setText(data.getHistoryInfo());
        uuid = data.getVehicleID();
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

    @OnClick({R.id.iv_left, R.id.comit_button, R.id.tv_weizhang_time, R.id.rl_car_color,
            R.id.rl_car_body_color, R.id.rl_veh_type, R.id.rl_car_type, R.id.rl_weizhang_type,
            R.id.rl_zhoushuo, R.id.rl_weizhang_addr, R.id.rl_owner_type})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.tv_weizhang_time:
                DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(this);
                dateTimePickerDialog.dateTimePicKDialog(tvWeizhangTime, 0);
                break;
            case R.id.rl_car_color:
                dropDownMenu.setDownValue(tvCarColor, "");
                break;
            case R.id.rl_car_body_color:
                dropDownMenu2.setDownValue(tvCarBodyColor, "");
                break;
            case R.id.rl_veh_type:
                dropDownMenu3.setDownValue(tvVehType, "");
                break;
            case R.id.rl_car_type:
                dropDownMenu4.setDownValue(tvCarType, "");
                break;
            case R.id.rl_weizhang_type:
                dropDownMenu5.setDownValue(tvWeizhangType, "");
                break;

            case R.id.rl_zhoushuo:
                downKeyValue.setDownValue(edZhoushou, "");
                break;
            case R.id.rl_weizhang_addr:
                dropDownOrgMenu.setDownValue(tvWeizhangAddr, "");
                break;
            case R.id.rl_owner_type:
                downKeyValue3.setDownValue(tvOwnerType, "");
                break;
            case R.id.comit_button:
                if (TextUtils.isEmpty(edCarNum.getText())) {
                    showToast("车牌号不能为空！");
                    return;
                }
                if (state) {
                    if (TextUtils.isEmpty(edSeating.getText())) {
                        showToast("座位号不能为空！");
                        return;
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
        data.setVehicleID(uuid);
        data.setBlackListID("");
        data.setYListID("");

        data.setUserID(Setting.USERID);
        data.setOperType(type);//1：新增 2：修改

        data.setVepPlateNo(edCarNum.getText().toString());
        data.setGenDT(tvWeizhangTime.getText().toString());
        data.setVehicleTypeID(vehicleTypeID);
        data.setVehicleTypeName(vehicleTypeName);
        data.setVehType(vehType);
        data.setVehTypeName(vehTypeName);

        data.setVepColor(vepColor);
        data.setVepColorName(vepColorName);
        data.setVepPlateNoColor(vepPlateNoColor);
        data.setVepPlateNoColorName(vepPlateNoColorName);

        data.setPeccancyTypeID(peccancyTypeID);
        data.setPeccancyTypeName(peccancyTypeName);
        data.setPeccancyOrgID(peccancyOrgID);
        data.setPeccancyOrgName(peccancyOrgName);

        data.setAxleCount(axleCount);
        data.setAxleCountName(axleCountName);
        if (!edDunshuo.getText().toString().replaceAll(" ", "").equals("")) {
            data.setTonnage(edDunshuo.getText().toString());
        } else {
            data.setTonnage("0");
        }

        if (!TextUtils.isEmpty(edSeating.getText())) {
            data.setSeating(Integer.valueOf(edSeating.getText().toString()));
        }

        if (!TextUtils.isEmpty(edFactoryType.getText())) {
            data.setFactoryType(edFactoryType.getText().toString());
        } else {
            data.setFactoryType("");
        }

        if (!TextUtils.isEmpty(edCardNo.getText())) {
            data.setCardNo(edCardNo.getText().toString());
        } else {
            data.setCardNo("");
        }

        if (!TextUtils.isEmpty(edRemark.getText())) {
            data.setPeccancyDescription(edRemark.getText().toString());
        } else {
            data.setPeccancyDescription("");
        }

        if (!TextUtils.isEmpty(edOwner.getText())) {
            data.setOwner(edOwner.getText().toString());
        } else {
            data.setOwner("");
        }

        data.setNameType(1);

        data.setOwnerType(ownerType);
        data.setOwnerTypeName(ownerTypeName);

        if (!TextUtils.isEmpty(edGreAddr.getText())) {
            data.setOwnerAddress(edGreAddr.getText().toString());
        } else {
            data.setOwnerAddress("");
        }

        if (!TextUtils.isEmpty(edGreyPostal.getText())) {
            data.setPostalcode(edGreyPostal.getText().toString());
        } else {
            data.setPostalcode("");
        }

        if (!TextUtils.isEmpty(edGreyPhone.getText())) {
            data.setTeletePhone(edGreyPhone.getText().toString());
        } else {
            data.setTeletePhone("");
        }

        if (!TextUtils.isEmpty(edGreyMobilePhone.getText())) {
            data.setMobilePhone(edGreyMobilePhone.getText().toString());
        } else {
            data.setMobilePhone("");
        }

        if (!TextUtils.isEmpty(edGreyHistoryInfo.getText())) {
            data.setHistoryInfo(edGreyHistoryInfo.getText().toString());
        } else {
            data.setHistoryInfo("");
        }

        data.setNameType(1);
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
            case 111://违章地点
                peccancyOrgID = code;
                peccancyOrgName = name;
                break;

        }
    }

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
