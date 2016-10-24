package cn.wtkj.charge_inspect.views.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.util.SysUtils;
import cn.wtkj.charge_inspect.views.custom.ImageCycleView;
import cn.wtkj.charge_inspect.views.custom.photoview.PhotoView;


/**
 * Created by lcl on 2016/9/1.
 */
public class BigImageActivity extends Activity {
    @Bind(R.id.aty_tvTitle)
    TextView tvTitle;
    private ImageCycleView mImageCycleView;
    private List<PhotoVideoData> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        //从Intent当中根据key取得value
        imageList =  (List<PhotoVideoData>)intent.getExtras().get("imageList");

        setContentView(R.layout.activity_big_image);
        ButterKnife.bind(this);
        initView();
    }

    public void initView(){
        tvTitle.setText(R.string.name_roll_manage_title);
        mImageCycleView = (ImageCycleView) findViewById(R.id.cycle_viewpager_content);
        mImageCycleView.setAutoCycle(false);

        List<ImageCycleView.ImageInfo> list=new ArrayList<ImageCycleView.ImageInfo>();

        int albumSize =  imageList.size();

        for (int i = 0; i < albumSize; i++) {
            String text = i+1 + "/" + albumSize;
            list.add(new ImageCycleView.ImageInfo(imageList.get(i), text, ""));
        }

        mImageCycleView.loadData(list, new ImageCycleView.LoadImageCallBack() {
            @Override
            public ImageView loadAndDisplay(ImageCycleView.ImageInfo imageInfo) {
                PhotoVideoData photoVideoData = (PhotoVideoData)imageInfo.image;

                PhotoView imageView = new PhotoView(BigImageActivity.this);
                Bitmap bitmap = SysUtils.getLocalBitmap(photoVideoData.getFileUrl());
                imageView.setImageBitmap(bitmap);
                return imageView;
            }
        });
    }
}
