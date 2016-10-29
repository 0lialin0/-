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
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementAddPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementListPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.IncrementListPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;
import cn.wtkj.charge_inspect.util.Convert;
import cn.wtkj.charge_inspect.util.CustomDialog;
import cn.wtkj.charge_inspect.views.Adapter.GreenRecordListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.IncrementListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener2;
import cn.wtkj.charge_inspect.views.custom.RecyClerRefresh;

import static cn.wtkj.charge_inspect.views.custom.ShowToast.show;

/**
 * Created by ghj on 2016/9/20.
 */
public class IncrementListActivity extends MvpBaseActivity<IncrementListPresenter> implements
        IncrementListView, SwipeRefreshLayout.OnRefreshListener,
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

    private IncrementListAdapter adapter;
    private List<JCEscapeBookData> mList;
    public static final String DATA_TAG = "DataInfo";
    private ProgressDialog progressDialog;
    private String keyword = "";


    @Override
    protected IncrementListPresenter createPresenter() {
        return new IncrementListPresenterImpl();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        adapter = null;
        setContentView(R.layout.activity_increment_list);
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
        setContentView(R.layout.activity_increment_list);
        ButterKnife.bind(this);
        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.increment_name);
        mToolbar.setTitle("");
        ivPhone.setVisibility(View.GONE);
        ivMore.setImageResource(R.drawable.increment_add);
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
    public void showList(String[] items, int[] imgs) {

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
        //this.finish();
        onRefresh();
        adapter.notifyDataSetChanged();
    }


    @Override
    public void showMes(String msg) {
        show(this, msg, Toast.LENGTH_LONG);
    }

    @Override
    public void setList(List<JCEscapeBookData> list) {
        if (list.size() > 0) {
            if (adapter == null) {
                mList = list;
                adapter = new IncrementListAdapter(this, list);
                shedRecy.setAdapter(adapter);
                adapter.setOnItemClickListener(this);
            } else {
                mList.addAll(list);
                adapter.notifyDataSetChanged();
            }
        } else {
            llContent.setVisibility(View.GONE);
            llContentKong.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.iv_left, R.id.iv_more, R.id.iv_search_del})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_left:
                this.finish();
                break;
            case R.id.iv_more:
                Intent intent = new Intent(this, IncrementAddActivity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.iv_search_del:
                etInput.setText("");
                keyword = "";
                ivSearchDel.setVisibility(View.GONE);
                onRefresh();
                break;

        }
    }


    @Override
    public void onItemClick(int code, int type, String name) {
        if (name.equals("del")) {
            //showMes("删除");
            showConfirm(code);

        } else if (name.equals("edit")) {
            Intent intent = new Intent(this, IncrementAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            bundle.putString("editOrlook", "edit");
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (name.equals("submit")) {
            //showMes("提交");
            showSubmit(code);
        } else if (name.equals("look")) {
            Intent intent = new Intent(this, IncrementAddActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(DATA_TAG, mList.get(code));
            bundle.putString("editOrlook", "look");
            intent.putExtras(bundle);
            startActivity(intent);
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
                presenter.deleteById(mList.get(code).getEscapeBookID());
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
