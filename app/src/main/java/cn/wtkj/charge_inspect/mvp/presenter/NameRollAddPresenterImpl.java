package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.NameRollAddView;


/**
 * Created by lxg on 2015/11/5.
 */
public class NameRollAddPresenterImpl extends MvpBasePresenter<NameRollAddView> implements
        NameRollAddPresenter {



    private Context context;
    List<File> files;
    List<String> fileName;
    private ConductInfoDataImpl nameRollAddData;
    private ConstAllDb constAllDb;

    public NameRollAddPresenterImpl(Context context) {
        this.context = context;
        nameRollAddData=new ConductInfoDataImpl(context);
        constAllDb = new ConstAllDb(context);
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
    public List<ConstAllData.MData.info> getConstByType(int type) {
        List<ConstAllData.MData.info> list_type=constAllDb.getConstList(type);
        return list_type;
    }


}
