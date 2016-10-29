package cn.wtkj.charge_inspect.views.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.dataBase.PhotoVideoDb;
import cn.wtkj.charge_inspect.views.activity.BigImageActivity;
import cn.wtkj.charge_inspect.views.activity.TestBasicVideo;
import cn.wtkj.charge_inspect.views.custom.videocapture.VideoRecordActivity;

/**
 * Created by lxg on 2015/9/18.
 */
public class MyPhotos extends FrameLayout implements PhotoAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private static List<Bitmap> mList;
    private List<File> files;
    private List<PhotoVideoData> pvDataList = new ArrayList<>();
    private Context mContext;
    private static PhotoAdapter adapter;
    public static final int RESULT_PHOTO = 0;
    public static final int RESULT_FILE = 1;
    public static final int VIDEO_WITH_CAMERA = 2;
    public static final int VIDEO_FILE = 3;
    private Activity fragment;
    private File tempFile;
    private static LinearLayoutManager manager;
    public boolean isEnabled = true;
    private int nameType = 0; // 0 黑名单，1 灰名单，2 黄名单，3 绿通

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public int getNameType() {
        return nameType;
    }

    public void setNameType(int nameType) {
        this.nameType = nameType;
    }

    public List<PhotoVideoData> getPvDataList() {
        return pvDataList;
    }

    public void setPvDataList(List<PhotoVideoData> pvDataList) {
        this.pvDataList = pvDataList;
    }

    //自定义的弹出框类
    SelectPicPopupWindow menuWindow;
    private static String TAG = "MyPhotos";
    private OnClickAddImgListener onClickAddImgListener;

    public MyPhotos(Context context) {
        super(context);
    }

    public MyPhotos(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPhotos(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.custom_photo_layout,
                this, true);
        mContext = context;
        initView();
    }

    public void setFragment(Activity fragment) {
        this.fragment = fragment;
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.photo_list);
        manager = new FullyLinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mList = new ArrayList<>();
        files = new ArrayList<>();
        recyclerView.setLayoutManager(manager);
        adapter = new PhotoAdapter(mContext, mList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    //为弹出窗口实现监听类
    private OnClickListener itemsOnClick = new OnClickListener() {

        public void onClick(View v) {
            menuWindow.dismiss();
            switch (v.getId()) {
                case R.id.btn_take_photo:
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    tempFile = new File(Environment.getExternalStorageDirectory(),
                            getPhotoFileName());
                    createParent(tempFile);
                    Uri uri = Uri.fromFile(tempFile);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    fragment.startActivityForResult(intent, RESULT_PHOTO);
                    break;
                case R.id.btn_pick_photo:
                    Intent intentPickPhoto = new Intent(Intent.ACTION_PICK);
                    intentPickPhoto.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    fragment.startActivityForResult(intentPickPhoto, RESULT_FILE);
                    break;
                case R.id.btn_take_video:
                   /* Intent intentTakeVideo =  new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    intentTakeVideo.putExtra (MediaStore.EXTRA_DURATION_LIMIT,30); //设置视频录制的画质
                    intentTakeVideo.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
                    //startActivityForResult (intent, VIDEO_WITH_CAMERA);
                    fragment.startActivityForResult(intentTakeVideo, VIDEO_WITH_CAMERA);*/
                    Intent intentTakeVideo =  new Intent();
                    intentTakeVideo.setClass(mContext, VideoRecordActivity.class);
                    fragment.startActivityForResult(intentTakeVideo, VIDEO_WITH_CAMERA);
                    break;
                case R.id.btn_pick_video:
                    Intent intentPickVideo = new Intent(Intent.ACTION_PICK);
                    intentPickVideo.setDataAndType(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                            "video/*");
                    fragment.startActivityForResult(intentPickVideo, VIDEO_FILE);
                    break;
                default:
                    break;
            }
        }
    };

    private void createParent(File file) {
        File parent = new File(file.getParent());
        if (!parent.exists()) {
            parent.mkdir();
        }
    }

    @Override
    public void onItemClick() {
        if (isEnabled) {
            menuWindow = new SelectPicPopupWindow((Activity) mContext, itemsOnClick, nameType);
            menuWindow.showAtLocation(recyclerView,
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            if (null != onClickAddImgListener) {
                onClickAddImgListener.OnClickAddImg();
            }
        }
    }

    @Override
    public void onItemClick(int id) {
         /*
        Intent intentBigImage = new Intent();
        intentBigImage.setClass(mContext, BigImageActivity.class);

        Bundle bundle = new Bundle();
       bundle.putSerializable("imageList", (Serializable) photoVideoDataList);
        ;
        intentBigImage.putExtras(bundle);
        context.startActivity(intentBigImage);*/
    }

    @Override
    public void onDeletePics(int id) {
        if (isEnabled) {
            File file = files.get(id);
            if (file.exists()) {
                file.delete();
            }
            if (id < mList.size()) {
                mList.remove(id);
                files.remove(id);
                adapter.notifyDataSetChanged();
            }

            if (id < pvDataList.size()){
              /*  int pvId = pvDataList.get(id).getPvId();
                PhotoVideoDb photoVideoDb = new PhotoVideoDb(mContext);
                photoVideoDb.delByPvId(pvId);*/
            }
        }
    }

    public static void notify(Bitmap bitmap) {
        mList.add(bitmap);
        adapter.notifyItemInserted(mList.size());
        manager.scrollToPosition(mList.size());
    }

    public static void notifyString(Bitmap bitmap) {
        notify(bitmap);
    }

    public void clear() {
        files.clear();
        mList.clear();
        adapter.notifyDataSetChanged();
    }

    public void getGlide(String url) {
        Glide.with(mContext)
                .load(url)
                .asBitmap()
                .fitCenter()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        //img = resource;
                        notifyString(resource);
                    }
                });
    }

    public File setImage(int requestCode, int resultCode, Intent data) {
        File img = null;
        if (requestCode == MyPhotos.RESULT_PHOTO && resultCode == -1) {
            img = getPicFile(tempFile.getPath());
        } else if (requestCode == MyPhotos.RESULT_FILE) {
            if (data != null) {
                String path = getPhotoPathByLocalUri(mContext, data);
                if (path == null) {
                    img = getPicFile(data.getData().getPath());
                } else {
                    img = getPicFile(path);
                }
            }
        }else if (requestCode == MyPhotos.VIDEO_WITH_CAMERA) {
            if (data != null) {
                String videoFilePath = data.getStringExtra("videoFilePath");
                Log.e(TAG, "onActivityResult: " + videoFilePath);
                img =   getVideoFile(videoFilePath);
            }
        }else if (requestCode == MyPhotos.VIDEO_FILE) {
            if (data != null) {
                String videoFilePath = data.getData().getPath();
                Log.e(TAG, "onActivityResult: " + videoFilePath);
                img =   getVideoFile(videoFilePath);
            }
        }
        return img;
    }

    public File addImage(String path){
        File img = null;
        img = getPicFile(path);
        return img;
    }

    //从uri中获取Bitmap,并保存成图片
    private File getPicFile(String path) {
        int readPicture = readPictureDegree(path);//查看是否被旋转
        File img = null;//创建临时文件，便于删除试用
        Bitmap bitmap = getBitmap(path);//对图片分辨率进行压缩
        if (readPicture > 0) {
            bitmap = rotaingImageView(readPicture, bitmap);
        }
        if (bitmap != null) {
            img = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
            createParent(img);
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(img));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
                files.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
            notify(bitmap);
        }
        return img;
    }

    /**
     * 获取视屏文件，生成视屏缩略图
     * @param videoFilePath
     * @return
     */
    public File getVideoFile(String videoFilePath)
    {
        Bitmap bitmap = getVideoThumbnail(videoFilePath);

        if (bitmap != null) {
            File img = null;//创建临时文件，便于删除试用
            img = new File(Environment.getExternalStorageDirectory(), getPhotoFileName());
            createParent(img);
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(img));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                bos.flush();
                bos.close();
                files.add(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
            notify(bitmap);
        }

        File videoFile =   new File(videoFilePath);
        return videoFile;
    }

    /**
     * 获取视屏缩略图
     * @param filePath
     * @return
     */
    public Bitmap getVideoThumbnail(String filePath) {
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(filePath);
            bitmap = retriever.getFrameAtTime();
        }
        catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            try {
                retriever.release();
            }
            catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    private Bitmap getBitmap(String path) {
        //先解析图片边框的大小
        BitmapFactory.Options ops = new BitmapFactory.Options();
        ops.inJustDecodeBounds = true;
        Bitmap bm = BitmapFactory.decodeFile(path, ops);
        ops.inSampleSize = 1;
        int oHeight = ops.outHeight;
        int oWidth = ops.outWidth;
        //控制压缩比
        int contentHeight = 500;
        int contentWidth = 500;
        if (((float) oHeight / contentHeight) < ((float) oWidth / contentWidth)) {
            ops.inSampleSize = (int) Math.ceil((float) oWidth / contentWidth);
        } else {
            ops.inSampleSize = (int) Math.ceil((float) oHeight / contentHeight);
        }
        ops.inJustDecodeBounds = false;
        bm = BitmapFactory.decodeFile(path, ops);
        return bm;
    }

    public String getPhotoPathByLocalUri(Context context, Intent data) {
        String picturePath;
        try {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return picturePath;
    }

    public int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "拍照旋转角度:" + degree);
        return degree;
    }

    /**
     * 图片旋转
     *
     * @param angle
     * @param bitmap
     * @return
     */
    private Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /*private String compressHeadPhoto(final Bitmap bm) {
        String path = getPhotoFileName();
        File rotateFile = new File(path);
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 70, new FileOutputStream(
                    rotateFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return path;
    }*/

    public String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy_MMdd_HHmm_ss");
        return mContext.getPackageName() + "/" + dateFormat.format(date) + ".jpg";
    }

    public void setOnClickAddImgListener(OnClickAddImgListener onClickAddImgListener) {
        this.onClickAddImgListener = onClickAddImgListener;
    }

    public interface OnClickAddImgListener {
        void OnClickAddImg();
    }
}
