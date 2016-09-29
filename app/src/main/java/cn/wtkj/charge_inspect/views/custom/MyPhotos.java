package cn.wtkj.charge_inspect.views.custom;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
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


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.views.activity.TestBasicVideo;
import cn.wtkj.charge_inspect.views.custom.videocapture.VideoRecordActivity;

/**
 * Created by lxg on 2015/9/18.
 */
public class MyPhotos extends FrameLayout implements PhotoAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private List<Bitmap> mList;
    private List<File> files;
    private Context mContext;
    private PhotoAdapter adapter;
    public static final int RESULT_PHOTO = 0;
    public static final int RESULT_FILE = 1;
    public static final int VIDEO_WITH_CAMERA = 2;
    private Activity fragment;
    private File tempFile;
    private LinearLayoutManager manager;
    public boolean isEnabled = true;
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
                    Intent intent1 = new Intent(Intent.ACTION_PICK);
                    intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            "image/*");
                    fragment.startActivityForResult(intent1, RESULT_FILE);
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
            menuWindow = new SelectPicPopupWindow((Activity) mContext, itemsOnClick);
            menuWindow.showAtLocation(recyclerView,
                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
            if (null != onClickAddImgListener) {
                onClickAddImgListener.OnClickAddImg();
            }
        }
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
        }
    }

    public void notify(Bitmap bitmap) {
        mList.add(bitmap);
        adapter.notifyItemInserted(mList.size());
        manager.scrollToPosition(mList.size());
    }

    public void clear() {
        files.clear();
        mList.clear();
        adapter.notifyDataSetChanged();
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
                Uri uri = data.getData();
                Log.e(TAG, "onActivityResult: " + uri.toString());
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
                "'IMG'_yyyyMMdd_HHmmss");
        return mContext.getPackageName() + "/" + dateFormat.format(date) + ".jpg";
    }

    public void setOnClickAddImgListener(OnClickAddImgListener onClickAddImgListener) {
        this.onClickAddImgListener = onClickAddImgListener;
    }

    public interface OnClickAddImgListener {
        void OnClickAddImg();
    }
}
