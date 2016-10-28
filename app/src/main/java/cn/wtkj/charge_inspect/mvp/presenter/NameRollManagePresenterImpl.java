package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.JCGreenChannelRecData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.dataBase.BlackListDb;
import cn.wtkj.charge_inspect.data.dataBase.EscapeBookDb;
import cn.wtkj.charge_inspect.data.dataBase.PhotoVideoDb;
import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.IncrementListView;
import cn.wtkj.charge_inspect.mvp.views.NameRollManageView;
import cn.wtkj.charge_inspect.util.ImageFactory;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.util.Setting;


/**
 * Created by lxg on 2015/11/5.
 */
public class NameRollManagePresenterImpl extends MvpBasePresenter<NameRollManageView> implements
        NameRollManagePresenter {


    private Context context;
    private Intent intent;
    private BlackListDb listDb;
    private Map<String, String> map;
    private ConductInfoData conductInfoData;
    private PhotoVideoDb photoVideoDb;
    private List<File> files;
    private List<String> fileName;
    private ConductInfoDataImpl nameRollAddData;

    @Override
    public void attachContextIntent(Context context) {
        this.context = context;
        listDb = new BlackListDb(context);
        conductInfoData = new ConductInfoDataImpl(context);
        photoVideoDb = new PhotoVideoDb(context);
        nameRollAddData = new ConductInfoDataImpl(context);
    }


    @Override
    public void startPresenter(String keyword) {
        List<JCBlackListData> list = listDb.getBlackList(Setting.USERID, keyword);
        if (list.size() > 0) {
            getView().setList(list);
            getView().hideLoging();
        } else {
            getView().hideLoging();
            getView().showMes("暂无数据");
        }

        getView().hideLoging();

    }

    @Override
    public void deleteById(String id, int type) {
        listDb.delData(id, type);
        photoVideoDb.delData(id, type);
        getView().nextView();
    }

    @Override
    public void sendData(final JCBlackListData data) {

        List<PhotoVideoData> pvList = getPvList(data.getBlackListID(), data.getNameType());
        map = new HashMap<>();

        if (!checkPhoto(data, pvList)){
            return;
        }

        files = new ArrayList<>();
        fileName = new ArrayList<>();
        if (pvList.size() > 0) {
            for (int i = 0; i < pvList.size(); i++) {
                PhotoVideoData pvData = pvList.get(i);
                String filePath = pvData.getFileUrl();

                if (pvData.getFileType() == 0) {
                    ImageFactory imageFactory = new ImageFactory();
                    String outfilePath = Environment.getExternalStorageDirectory() + "/tmp/" + pvData.getFileName();

                    try {
                        imageFactory.compressAndGenImage(filePath, outfilePath, 100, false);
                        filePath = outfilePath;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                File file = new File(filePath);
                if (file.exists()) {
                    fileName.add(pvList.get(i).getFileName());
                    files.add(file);
                }
            }
        }

        getView().showLoding();
        data.setBusinessId("black.blackAct");
        map.put("json", ResponeUtils.dataToJson(data));
        nameRollAddData.nameRoll(map, fileName, files,data.getNameType(),
                new DataRequester.DataCallBack<ResponeData>() {

            @Override
            public void success(ResponeData responeData) {
                getView().hideDialog();
                if (responeData.getData().getState() == responeData.SUCCESS) {
                    String id = "";
                    int type = data.getNameType();
                    if (type == 1) {
                        id = data.getVehicleID();
                    } else if (type == 2) {
                        id = data.getYListID();
                    }else{
                        id = data.getBlackListID();
                    }
                    deleteById(id, type);
                } else {
                    getView().showMes(responeData.getMsg());
                }

            }

            @Override
            public void error() {
                getView().hideDialog();
                getView().showMes(ResponeData.NET_ERROR);
            }
        });
    }

    private Boolean checkPhoto(JCBlackListData data, List<PhotoVideoData> pvList){
        int photoSize = pvList.size();

        /* 黑名单 */
        if (data.getNameType() == 0 && photoSize ==0){
            getView().showMes("黑名单，至少要上传一张照片");
            return  false;
        }

        return true;
    }

    private List<PhotoVideoData> getPvList(String uuid, int type) {
        List<PhotoVideoData> list = photoVideoDb.getPv(uuid, type, -1);
        return list;
    }

}
