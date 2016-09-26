package cn.wtkj.charge_inspect.views.activity;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementAddPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementAddPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.IncrementAddView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownKeyValue;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/21.
 */
public class IncrementAddActivity extends MvpBaseActivity<IncrementAddPresenter> implements
        IncrementAddView, View.OnClickListener, OnItemClickListener {

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

    @Bind(R.id.ll_zhoushu)
    LinearLayout llZhoushu;
    @Bind(R.id.ll_dunshuo)
    LinearLayout llDunshuo;

    private DropDownKeyValue downKeyValue;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;
    private DropDownMenu dropDownMenu4;
    private DropDownMenu dropDownMenu5;

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
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.increment_add_name);
        mToolbar.setTitle("");
        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
    }

    public void initView() {
        showView();
    }

    @Override
    public void setClasses(List<KeyValueData> list) {
        downKeyValue = new DropDownKeyValue(this, list);
        downKeyValue.setId("1");
        tvClasses.setText(list.get(0).getValue());
    }

    @Override
    public void setView(String unit) {
        edCheckUnit.setText(unit);
        tvCheckTime.setText(ResponeUtils.getTime());
    }


    @Override
    public void showView() {
        //1：站址, 5：车型类别, 9：堵漏增收信息,
        List<ConstAllData.MData.info> dlType = presenter.getConstByType(9);
        dropDownMenu2 = new DropDownMenu(this, dlType);
        dropDownMenu2.setId(1);
        dropDownMenu2.setOnItemClickListener(this);
        tvIncrementType.setText(dlType.get(0).getName());

        //入口站址
        List<ConstAllData.MData.info> rkLoca = presenter.getConstByType(1);
        dropDownMenu3 = new DropDownMenu(this, rkLoca);
        dropDownMenu3.setId(1);
        dropDownMenu3.setOnItemClickListener(this);
        tvEntranceLoca.setText(rkLoca.get(0).getName());

        //入口判型
        List<ConstAllData.MData.info> entranList = presenter.getConstByType(5);
        dropDownMenu4 = new DropDownMenu(this, entranList);
        dropDownMenu4.setId(1);
        dropDownMenu4.setOnItemClickListener(this);
        tvEntranceType.setText(entranList.get(0).getName());


        //出口判型
        List<ConstAllData.MData.info> exitList = new ArrayList<>();
        for (int i = 0; i < entranList.size(); i++) {
            if (i < 4) {
                //exitList.add(new KeyValueData(entranList.get(i).getId(), entranList.get(i).getValue()));
            }
        }
        dropDownMenu5 = new DropDownMenu(this, exitList);
        dropDownMenu5.setId(1);
        dropDownMenu5.setOnItemClickListener(this);
        tvExitType.setText(exitList.get(0).getName());
    }


    @Override
    public void showLoding() {

    }

    @Override
    public void hideLoging() {

    }

    @Override
    public void nextView(String phone) {

    }

    @Override
    public void showMes(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }


    @OnClick({R.id.tv_check_time, R.id.tv_classes, R.id.tv_increment_type, R.id.tv_entrance_loca,
            R.id.tv_entrance_type, R.id.tv_exit_type, R.id.comit_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_check_time:
                DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(this);
                dateTimePickerDialog.dateTimePicKDialog(tvCheckTime, 0);
                break;
            case R.id.tv_classes:
                downKeyValue.setDownValue(tvClasses, "");
                break;
            case R.id.tv_increment_type:
                dropDownMenu2.setDownValue(tvIncrementType, "");
                break;
            case R.id.tv_entrance_loca:
                dropDownMenu3.setDownValue(tvEntranceLoca, "");
                break;
            case R.id.tv_entrance_type:
                dropDownMenu4.setDownValue(tvEntranceType, "");
                break;
            case R.id.tv_exit_type:
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
                break;
        }
    }

    @Override
    public void onItemClick(int code,int type,String name) {
        switch (type) {
            case 1:
                //tvClasses.setText("早班");
                break;
        }
    }
}