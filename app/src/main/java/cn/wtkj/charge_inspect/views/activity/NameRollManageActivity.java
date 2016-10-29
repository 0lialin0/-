package cn.wtkj.charge_inspect.views.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollManagePresenter;
import cn.wtkj.charge_inspect.mvp.presenter.NameRollManagePresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.NameRollManageView;
import cn.wtkj.charge_inspect.util.Convert;
import cn.wtkj.charge_inspect.util.CustomDialog;
import cn.wtkj.charge_inspect.views.Adapter.IncrementListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.NameRollManageListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.custom.AlertDialogType;
import cn.wtkj.charge_inspect.views.custom.RecyClerRefresh;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/29.
 */
public class NameRollManageActivity extends MvpBaseActivity<NameRollManagePresenter> implements
        NameRollManageView, SwipeRefreshLayout.OnRefreshListener,
        RecyClerRefresh.RefreshData, View.OnClickListener, OnItemClickListener {

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
    @Bind(R.id.search_et_input)
    EditText etInput;
    @Bind(R.id.iv_search_img)
    ImageView ivSearchImg;
    @Bind(R.id.iv_search_del)
    ImageView ivSearchDel;

    @Bind(R.id.shed_list_refresh)
    SwipeRefreshLayout shedRefresh;
    @Bind(R.id.shed_list_recy)
    RecyClerRefresh shedRecy;
    @Bind(R.id.ll_content)
    LinearLayout llContent;
    @Bind(R.id.ll_content_kong)
    LinearLayout llContentKong;


    private AlertDialogType alertDialogType;
    private String keyword = "";
    private List<JCBlackListData> mList;
    private NameRollManageListAdapter adapter;
    public static final String DATA_TAG = "DataInfo";
    private ProgressDialog progressDialog;

    @Override
    protected NameRollManagePresenter createPresenter() {
        return new NameRollManagePresenterImpl();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        adapter = null;
        setContentView(R.layout.activity_name_roll_manage);
        ButterKnife.bind(this);
        initToolBar();
        handleIntent(intent);
    }
    private void handleIntent(Intent intent){
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_roll_manage);
        ButterKnife.bind(this);
        initToolBar();
        Intent intent = getIntent();
        handleIntent(intent);
    }

    private void initToolBar() {
        tvTitle.setText(R.string.name_roll_manage_title);
        mToolbar.setTitle("");
        ivPhone.setVisibility(View.GONE);
        ivMore.setImageResource(R.drawable.name_add_img);
        setSupportActionBar(mToolbar);
    }

    public void initView() {
        progressDialog = new ProgressDialog(this);
        // 设置下拉组件动画偏移量
        shedRefresh.setProgressViewOffset(false,
                Convert.dip2px(this.getApplicationContext(), -30),
                Convert.dip2px(this.getApplicationContext(), 24));
        shedRecy.setLayoutManager(new LinearLayoutManager(this));
        shedRefresh.setOnRefreshListener(this);
        shedRefresh.setRefreshing(true);// 显示动画
        presenter.attachContextIntent(this);
        presenter.startPresenter("");
        shedRecy.setRefreshData(this);


        alertDialogType = new AlertDialogType(this);
        alertDialogType.setOnItemClickListener(this);

        etInput.addTextChangedListener(new EditChangedListener());
        etInput.setOnClickListener(this);
        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    notifyStartSearching(etInput.getText().toString());
                }
                return true;
            }
        });

        ivSearchDel.setOnClickListener(this);
    }

    @Override
    public void showList() {

    }

    @Override
    public void setList(List<JCBlackListData> list) {
        if (list.size() > 0) {
            if (adapter == null) {
                mList = list;
                adapter = new NameRollManageListAdapter(this, list);
                shedRecy.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
            } else {
                mList.addAll(list);
                adapter.notifyDataSetChanged();
            }
        }else{
            llContent.setVisibility(View.GONE);
            llContentKong.setVisibility(View.VISIBLE);
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
        shedRefresh.setRefreshing(false);
    }

    @Override
    public void hideDialog() {
        progressDialog.hide();
    }

    @Override
    public void nextView() {
        onRefresh();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMes(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }

    @OnClick({R.id.iv_left, R.id.iv_more, R.id.ll_xiafa_btn})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.iv_more:
                alertDialogType.setAlertDialog("");
                break;
            case R.id.ll_xiafa_btn:
                Intent intent = new Intent(this, NameRollXiafaActivity.class);
                this.startActivity(intent);
                break;
            case R.id.iv_search_del:
                etInput.setText("");
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (mList != null) {
            mList.clear();
        }
        presenter.startPresenter(keyword);
    }

    @Override
    public void onRefreshData() {
        onRefresh();
    }

    @Override
    public void onItemClick(int code, int type, String name) {
        Intent intent = new Intent();
        intent.setClass(this, NameRollAddActivity.class);

        int nameType = -1;
        String nameTitle = "";

        if (name.equals("black")) {
            nameType = 0;
            nameTitle = "添加黑名单";
        } else if (name.equals("grey")) {
            nameType = 1;
            nameTitle = "添加灰名单";
        } else if (name.equals("yellow")) {
            nameType = 2;
            nameTitle = "添加黄名单";
        } else if (name.equals("del")) {
            showConfirm(code);
        } else if (name.equals("edit")) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            intent.putExtras(bundle);
            nameType = mList.get(code).getNameType();
        } else if (name.equals("submit")) {
            //showMes("提交");
            showSubmit(code);
        } /*else if (name.equals("showImageList")){


        }*/

        /* 如果是编辑或者新增名单 */
        if (nameType != -1) {
            switch (nameType) {
                case 0:
                    nameTitle = "添加黑名单";
                    break;
                case 1:
                    nameTitle = "添加灰名单";
                    break;
                case 2:
                    nameTitle = "添加黄名单";
                    break;
            }

            intent.putExtra("nameType", nameType + "");
            intent.putExtra("nameTitle", nameTitle);
            this.startActivity(intent);
            //this.finish();
        }

    }


    public void showConfirm(final int code) {
        final CustomDialog showDialog = new CustomDialog(this, "");
        showDialog.setText("是否确认要删除此条信息");
        showDialog.setNegativeText("确定");
        showDialog.setPositiveText("取消");
        showDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog.dismiss();

                presenter.deleteById(mList.get(code).getBlackListID(), mList.get(code).getNameType());
            }
        });
        showDialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog.dismiss();
            }
        });
    }


    public void showSubmit(final int code) {
        final CustomDialog showDialog = new CustomDialog(this, "userInfo");
        showDialog.setText("是否确认要提交此条信息");
        showDialog.setNegativeText("确定");
        showDialog.setPositiveText("取消");
        showDialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog.dismiss();
                presenter.sendData(mList.get(code));
            }
        });
        showDialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog.dismiss();
            }
        });
    }

    /**
     * 通知监听者 进行搜索操作
     *
     * @param text
     */
    private void notifyStartSearching(String text) {
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        keyword = text.replaceAll(" ", "");
        if (mList != null) {
            mList.clear();
        }
        presenter.startPresenter(keyword);

    }

    /**
     * 输入文字改变 监听
     */
    private class EditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!"".equals(charSequence.toString())) {
                ivSearchDel.setVisibility(View.VISIBLE);
                ivSearchImg.setVisibility(View.GONE);
            } else {
                ivSearchDel.setVisibility(View.GONE);
                ivSearchImg.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}
