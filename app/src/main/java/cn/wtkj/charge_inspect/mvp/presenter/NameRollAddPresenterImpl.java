package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.NameRollAddDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.NameRollAddView;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by lxg on 2015/11/5.
 */
public class NameRollAddPresenterImpl extends MvpBasePresenter<NameRollAddView> implements
        NameRollAddPresenter, OnItemClickListener {



    private Context context;
    List<File> files;
    List<String> fileName;
    private NameRollAddDataImpl nameRollAddData;

    public NameRollAddPresenterImpl(Context context) {
        this.context = context;
        nameRollAddData=new NameRollAddDataImpl(context);
    }

    @Override
    public void startPresenter(List<File> fileList) {
        int fileIndex = 0;
        files = new ArrayList<>();
        fileName = new ArrayList<>();
        for (int i = 0; i < fileList.size(); i++) {
            File file = fileList.get(i);
            if (file.exists()) {
                fileName.add(String.format("PicFile[%d]", fileIndex++));
                files.add(file);
            }
        }
        Map<String, String> map= new HashMap<>();
        map.put("txtPointx", "aaaaa");
        map.put("txtPointy", "bbbbb");
        map.put("gatherOn", "ccccc");
        map.put("gpsType", "ddddd");
        nameRollAddData.nameRoll(map,fileName,files,new DataRequester.DataCallBack<ResponeData>(){

            @Override
            public void success(ResponeData responeData) {
                getView().himeDialog();
            }

            @Override
            public void error() {
                getView().himeDialog();
                getView().showToast(ResponeData.NET_ERROR);
            }
        });
    }


    @Override
    public void onItemClick(String tags) {

    }
}
