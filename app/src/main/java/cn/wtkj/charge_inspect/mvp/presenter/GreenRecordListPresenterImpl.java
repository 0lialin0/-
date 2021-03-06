package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.android.volley.VolleyError;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.JCEscapeBookData;
import cn.wtkj.charge_inspect.data.bean.JCGreenChannelRecData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.dataBase.GreenChannelDb;
import cn.wtkj.charge_inspect.data.dataBase.PhotoVideoDb;
import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordListView;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.util.ImageFactory;
import cn.wtkj.charge_inspect.util.ResponeUtils;
import cn.wtkj.charge_inspect.util.Setting;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by lxg on 2015/11/5.
 */
public class GreenRecordListPresenterImpl extends MvpBasePresenter<GreenRecordListView> implements
        GreenRecordListPresenter {


    private Context context;
    private GreenChannelDb greenChannelDb;
    private Map<String, String> map;
    private ConductInfoData conductInfoData;
    private PhotoVideoDb photoVideoDb;
    private List<File> files;
    private List<String> fileName;

    @Override
    public void attachContextIntent(Context context) {
        this.context = context;
        greenChannelDb = new GreenChannelDb(context);
        conductInfoData = new ConductInfoDataImpl(context);
        photoVideoDb = new PhotoVideoDb(context);
    }


    @Override
    public void startPresenter(String keyword) {
        List<JCGreenChannelRecData> list = greenChannelDb.getGreenChannelList(Setting.USERID, keyword);
        /*if (list.size() > 0) {
            getView().setList(list);
            getView().hideLoging();
        } else {
            getView().hideLoging();
            getView().showMes("暂无数据");
        }*/

        getView().setList(list);
        getView().hideLoging();
    }


    @Override
    public void deleteById(String id) {
        greenChannelDb.delData(id);
        photoVideoDb.delData(id, 3);
        getView().nextView();
    }

    @Override
    public void sendData(final JCGreenChannelRecData data) {
        data.setOperType(1);
        files = new ArrayList<>();
        fileName = new ArrayList<>();
        map = new HashMap<>();
        if (data.getIsMix() == 1 || data.getIsMix() == 2) {
            List<PhotoVideoData> pvList = getPvList(data.getGCListID());

            if (pvList.size() > 0) {
                for (int i = 0; i < pvList.size(); i++) {
                    PhotoVideoData pvData = pvList.get(i);
                    String filePath = pvData.getFileUrl();

                    if (pvData.getFileType() == 0) {
                        ImageFactory imageFactory = new ImageFactory();
                        String outfilePath = Environment.getExternalStorageDirectory() + "/tmp/" + pvData.getFileName();

                        try {
                            imageFactory.compressAndGenImage(filePath, outfilePath, 3000, false);
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
        }
        map.put("json", ResponeUtils.dataToJson(data));

        getView().showLoding();

        conductInfoData.greenRecord(map, fileName, files, new DataRequester.DataCallBack<ResponeData>() {
            @Override
            public void success(ResponeData responeData) {

                getView().hideDialog();
                if (responeData.getData().getState() == responeData.SUCCESS) {
                    deleteById(data.getGCListID());
                } else {
                    getView().showMes(responeData.getMsg());
                }
            }

            @Override
            public void error(VolleyError error) {
                getView().hideDialog();
                getView().showMes(ResponeData.NET_ERROR);
            }
        });
    }


    private List<PhotoVideoData> getPvList(String uuid) {
        List<PhotoVideoData> list = photoVideoDb.getPv(uuid, 3, -1);
        return list;
    }

}
