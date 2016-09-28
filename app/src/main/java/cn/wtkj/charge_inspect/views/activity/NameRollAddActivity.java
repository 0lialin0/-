package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollAddPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollAddPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.NameRollAddView;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.custom.DateTimePickerDialog;
import cn.wtkj.charge_inspect.views.custom.DropDownMenu;
import cn.wtkj.charge_inspect.views.custom.MyPhotos;
import cn.wtkj.charge_inspect.views.custom.PhotoAdapter;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/21.
 */
public class NameRollAddActivity extends MvpBaseActivity<NameRollAddPresenter> implements
        NameRollAddView, MyPhotos.OnClickAddImgListener, View.OnClickListener,
        OnItemClickListener {

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
    @Bind(R.id.tv_qujian_start)
    TextView tvQujianStart;
    @Bind(R.id.tv_qujian_end)
    TextView tvQujianEnd;

    private List<File> files;
    private ProgressDialog progressDialog;
    private AlertDialog alter;

    private DropDownMenu dropDownMenu;
    private DropDownMenu dropDownMenu2;
    private DropDownMenu dropDownMenu3;
    private DropDownMenu dropDownMenu4;
    private DropDownMenu dropDownMenu5;
    private DropDownMenu dropDownMenu6;
    private DropDownMenu dropDownMenu7;

    @Override
    protected NameRollAddPresenter createPresenter() {
        return new NameRollAddPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_roll_add);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.name_roll_title);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }


    public void initView() {
        myphoto.setFragment(this);
        myphoto.setOnClickAddImgListener(this);
        files = new ArrayList<>();
        progressDialog = new ProgressDialog(this);

        showView();
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
            //peccancyTypeID = dlType.get(0).getCode();
            tvCarColor.setText(colorList.get(0).getName());
        }

        //车身颜色
        List<ConstAllData.MData.info> colorbodyList = presenter.getConstByType(4);
        dropDownMenu2 = new DropDownMenu(this, colorbodyList);
        dropDownMenu2.setOnItemClickListener(this);
        if (colorbodyList.size() > 0) {
            //peccancyTypeID = dlType.get(0).getCode();
            tvCarBodyColor.setText(colorbodyList.get(0).getName());
        }

        //车型类别
        List<ConstAllData.MData.info> vehTtypList = presenter.getConstByType(5);
        dropDownMenu3 = new DropDownMenu(this, vehTtypList);
        dropDownMenu3.setOnItemClickListener(this);
        if (vehTtypList.size() > 0) {
            //peccancyTypeID = dlType.get(0).getCode();
            tvVehType.setText(vehTtypList.get(0).getName());
        }

        //车辆类别
        List<ConstAllData.MData.info> carTypeList = presenter.getConstByType(6);
        dropDownMenu4 = new DropDownMenu(this, carTypeList);
        dropDownMenu4.setOnItemClickListener(this);
        if (carTypeList.size() > 0) {
            //peccancyTypeID = dlType.get(0).getCode();
            tvCarType.setText(carTypeList.get(0).getName());
        }

        //违章类型
        List<ConstAllData.MData.info> weiList = presenter.getConstByType(7);
        dropDownMenu5 = new DropDownMenu(this, weiList);
        dropDownMenu5.setOnItemClickListener(this);
        if (weiList.size() > 0) {
            //peccancyTypeID = dlType.get(0).getCode();
            tvWeizhangType.setText(weiList.get(0).getName());
        }

        //行驶区间--开始
        List<ConstAllData.MData.info> qujianList = presenter.getConstByType(1);
        dropDownMenu6 = new DropDownMenu(this, qujianList);
        dropDownMenu5.setOnItemClickListener(this);
        if (qujianList.size() > 0) {
            //peccancyTypeID = dlType.get(0).getCode();
            tvQujianStart.setText(qujianList.get(0).getName());
        }

        //行驶区间--结束
        List<ConstAllData.MData.info> qujianEndList = presenter.getConstByType(1);
        dropDownMenu7 = new DropDownMenu(this, qujianList);
        dropDownMenu7.setOnItemClickListener(this);
        if (qujianEndList.size() > 0) {
            //peccancyTypeID = dlType.get(0).getCode();
            tvQujianEnd.setText(qujianEndList.get(0).getName());
        }
    }

    @Override
    public void showLoding() {
        progressDialog.setMessage("正在停止，请等待..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void himeDialog() {
        progressDialog.hide();
    }

    @Override
    public void nextView(String phone) {

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
            R.id.tv_qujian_start, R.id.tv_qujian_end})
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
            case R.id.tv_qujian_start:
                dropDownMenu6.setDownValue(tvQujianStart, "");
                break;
            case R.id.tv_qujian_end:
                dropDownMenu7.setDownValue(tvQujianEnd, "");
                break;
            case R.id.comit_button:
                presenter.startPresenter(files);
                break;
        }
    }

    @Override
    public void onItemClick(int code, int type, String name) {

    }
}
