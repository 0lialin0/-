package cn.wtkj.charge_inspect.views.activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ArticleDetail;
import cn.wtkj.charge_inspect.views.Adapter.ArticleAttachmentListAdapter;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener3;


/**
 * Created by ghj on 2016/9/19.
 */
public class ArticleAttachmentActivity extends AppCompatActivity implements View.OnClickListener,OnItemClickListener3 {

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

    @Bind(R.id.laws_attachment_list)
    RecyclerView lawsAttachmentList;

    List<ArticleDetail.MData.info.files> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_attachment_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        fileList =  (List<ArticleDetail.MData.info.files>)intent.getExtras().get("fileList");

        initToolBar();
        initView();
    }

    private void initToolBar() {
        tvTitle.setText(R.string.information_attachment);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    public void initView() {
        ivMore.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);

        ArticleAttachmentListAdapter adapter = new ArticleAttachmentListAdapter(this, fileList);
        lawsAttachmentList.setLayoutManager(new LinearLayoutManager(this));
        lawsAttachmentList.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
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
    public void onItemClick(String name, int id) {
        ArticleDetail.MData.info.files attachment = fileList.get(id);

        String url = attachment.getUrl();

        String fileName = attachment.getFileName()+"."+attachment.getFileExtension();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));

        //设置通知栏标题
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("附件下载");

        request.setAllowedOverRoaming(false);

        String filePath =  "/稽查APP/资料附件/";

        //设置文件存放目录
        request.setDestinationInExternalPublicDir(filePath, fileName);
        DownloadManager downManager = (DownloadManager)ArticleAttachmentActivity.this.getSystemService(ArticleAttachmentActivity.this.DOWNLOAD_SERVICE);
        long downloadId = downManager.enqueue(request);
    }
}
