package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.BlackListData;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.KeyValueData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.data.dataBase.BlackListDb;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.data.dataBase.OrganizationDb;
import cn.wtkj.charge_inspect.data.dataBase.PhotoVideoDb;
import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoData;
import cn.wtkj.charge_inspect.data.rest.ConductInfoDataImpl;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.NameRollAddView;
import cn.wtkj.charge_inspect.util.SysUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


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
    private OrganizationDb organizationDb;
    private BlackListDb blackListDb;
    private PhotoVideoDb photoVideoDb;
    private boolean isNumber = true;//控制上传频率
    public BlackListData.MData.info infoData;
    private Map<String, String> map;
    private ConductInfoData conductInfoData;

    public NameRollAddPresenterImpl(Context context) {
        this.context = context;
        nameRollAddData = new ConductInfoDataImpl(context);
        constAllDb = new ConstAllDb(context);
        organizationDb = new OrganizationDb(context);
        blackListDb = new BlackListDb(context);
        photoVideoDb = new PhotoVideoDb(context);
        conductInfoData = new ConductInfoDataImpl(context);
    }

    @Override
    public void startPresenter(List<File> fileList, JCBlackListData data) {
        List<File> newFileList = new ArrayList<>();
        if (fileList.size() > 0){
            for (int i=0; i< fileList.size(); i++){
                String oldFilePath = fileList.get(i).getPath();
                String oldFileName = fileList.get(i).getName();
                String newFilePath = getFileName(data,oldFileName);

                if (SysUtils.copyFile(oldFilePath, newFilePath)) {
                    File file = new File(newFilePath);
                    newFileList.add(file);
                }
            }
        }

        if (isNumber) {
            getView().showLoding();
            String uuid = blackListDb.updateBlackList(data);
            if (uuid!="" && fileList.size()>0) {
                photoVideoDb.insertListPvd(newFileList, uuid, data.getNameType());
            }
            getView().himeDialog();
            getView().nextView();
        }
        isNumber = !isNumber;//控制上传时间间隔
    }

    /*此处需改为拍照时间，否则只有一个*/
    public String getFileName(JCBlackListData data, String fileName){
        int nameType = data.getNameType();

        String filePath = Environment.getExternalStorageDirectory() + "/稽查APP";

       // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("_yyyyMMdd_HHmmss");
       // String addTime = simpleDateFormat.format(new Date());
      //  String addTime = fileName;
        if (nameType ==0){
            filePath += "/黑名单/";
           // filePath += data.getVepPlateNo()+data.getPeccancyTypeName()+data.getGenDT();

            filePath += data.getVepPlateNo()+data.getPeccancyTypeName()+fileName;
        }else if (nameType == 1){
            filePath += "/灰名单/";
           // filePath += data.getVepPlateNo()+data.getPeccancyTypeName()+data.getGenDT();
            filePath += data.getVepPlateNo()+data.getPeccancyTypeName()+fileName;
        }else {

            filePath += "/黄名单/";
            filePath += data.getVepPlateNo()+data.getPeccancyTypeName()+fileName;
        }

        return filePath;
    }

    @Override
    public List<KeyValueData> setDropDown() {
        List<KeyValueData> zhoushuo = new ArrayList<>();
        zhoushuo.add(new KeyValueData("2", "2轴"));
        zhoushuo.add(new KeyValueData("3", "3轴"));
        zhoushuo.add(new KeyValueData("4", "4轴"));
        zhoushuo.add(new KeyValueData("5", "5轴"));
        zhoushuo.add(new KeyValueData("6", "6轴"));
        zhoushuo.add(new KeyValueData("7", "7轴"));
        return zhoushuo;
    }

    @Override
    public List<ViewOrganizationData.MData.info> getOrg(int orgId, String orgLevel) {
        List<ViewOrganizationData.MData.info> list = organizationDb.getCheckUnit(orgId, orgLevel);
        return list;
    }

    @Override
    public List<ConstAllData.MData.info> getConstByType(int type) {
        List<ConstAllData.MData.info> list_type = constAllDb.getConstList(type);
        return list_type;
    }

    public List<PhotoVideoData> getPvList(String uuid,int type) {
        List<PhotoVideoData> list= photoVideoDb.getPv(uuid,type,-1);
        return list;
    }

    @Override
    public void getBlackData(String id, int type) {
        BlackListData blackListData = new BlackListData();
        BlackListData.MData mData = blackListData.new MData();
        infoData = mData.new info();
        map = new HashMap<>();
        if (type == 0) {
            map.put("BLACKLISTID", id);
        } else if (type == 1) {
            map.put("VEHICLEID", id);
        } else if (type == 2) {
            map.put("YLISTID", id);
        }
        conductInfoData.sendXiafaInfo(map, type, new Callback<BlackListData>() {
            @Override
            public void success(BlackListData data, Response response) {
                if (data.getMData().getState() == data.SUCCESS) {
                    infoData = data.getMData().getInfo();
                    getView().showViewXiafaData(infoData);
                } else {
                    getView().showToast(data.getMData().getInfo().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                getView().showToast(ResponeData.NET_ERROR);
            }
        });

    }

}
