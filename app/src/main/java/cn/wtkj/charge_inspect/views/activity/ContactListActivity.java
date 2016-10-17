package cn.wtkj.charge_inspect.views.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ContactListData;
import cn.wtkj.charge_inspect.data.bean.SortModel;
import cn.wtkj.charge_inspect.mvp.MvpBaseActivity;
import cn.wtkj.charge_inspect.mvp.presenter.ContactListPresenter;
import cn.wtkj.charge_inspect.mvp.presenter.ContactListPresenterImpl;
import cn.wtkj.charge_inspect.mvp.views.ContactListView;
import cn.wtkj.charge_inspect.views.Adapter.ContactListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;
import cn.wtkj.charge_inspect.views.Adapter.SortAdapter;
import cn.wtkj.charge_inspect.views.custom.AlertDialogType;
import cn.wtkj.charge_inspect.views.custom.CharacterParser;
import cn.wtkj.charge_inspect.views.custom.PinyinComparator;
import cn.wtkj.charge_inspect.views.custom.SideBar;

/**
 * Created by ghj on 2016/9/29.
 */
public class ContactListActivity extends MvpBaseActivity<ContactListPresenter> implements ContactListView,View.OnClickListener,OnItemClickListener3 {

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

    @Bind(R.id.laws_contact_list)
    ListView lawsContactList;

    private SideBar sideBar;
    private TextView dialog;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    private List<SortModel> sourceDateList;
    private ContactListData contactListData;
    private ContactListAdapter adapter;
    private AlertDialogType alertDialogType;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;

    @Override
    protected ContactListPresenter createPresenter() {
        return new ContactListPresenterImpl(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);
        initToolBar();

        characterParser = CharacterParser.getInstance();

        pinyinComparator = new PinyinComparator();
        sideBar = (SideBar) findViewById(R.id.sidebar);
        dialog = (TextView) findViewById(R.id.dialog);
        sideBar.setTextView(dialog);

        alertDialogType = new AlertDialogType(this);
        //alertDialogType.s(this);
        presenter.getContactList();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.bus_title);
        mToolbar.setTitle("");

        ivPhone.setVisibility(View.GONE);
        ivMore.setVisibility(View.GONE);
        setSupportActionBar(mToolbar);
    }


    @OnClick({R.id.iv_left})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left:
                this.finish();
                break;

        }
    }

    @Override
    public void showList(ContactListData contactListData) {
        this.contactListData = contactListData;


        //实例化汉字转拼音类

        sourceDateList = filledData(this.contactListData.getMData().getInfo());

        // 根据a-z进行排序源数据
        Collections.sort(sourceDateList, pinyinComparator);

        adapter = new ContactListAdapter(this, sourceDateList);
       // lawsContactList.setLayoutManager(new LinearLayoutManager(this));
        lawsContactList.setAdapter(adapter);

        /**
         * 为右边添加触摸事件
         */
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    lawsContactList.setSelection(position);
                   // lawsContactList.getLayoutManager().smoothScrollToPosition(lawsContactList, null, position);
                   // lawsContactList.scrollToPosition(position);
                }
            }
        });
    }


    @Override
    public void showMes(String msg) {

    }

    /**
     * 为ListView填充数据
     * @param contactListData
     * @return
     */
    private List<SortModel> filledData( List<ContactListData.MData.info> contactListData){
        List<SortModel> mSortList = new ArrayList<SortModel>();

        for(int i=0; i<contactListData.size(); i++){
            SortModel sortModel = new SortModel();
            sortModel.setContactData(contactListData.get(i));
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(contactListData.get(i).getOrgName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                sortModel.setSortLetters(sortString.toUpperCase());
            }else{
                sortModel.setSortLetters("#");
            }

            mSortList.add(sortModel);
        }
        return mSortList;

    }

    @Override
    public void onItemClick(String name, int id) {

    }
}
