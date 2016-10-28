package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.wtkj.charge_inspect.R;
import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.JCGreenChannelRecData;
import cn.wtkj.charge_inspect.data.bean.PhotoVideoData;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.data.dataBase.GreenChannelDb;
import cn.wtkj.charge_inspect.data.dataBase.OrganizationDb;
import cn.wtkj.charge_inspect.data.dataBase.PhotoVideoDb;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.GreenRecordView;
import cn.wtkj.charge_inspect.mvp.views.MainView;
import cn.wtkj.charge_inspect.util.SysUtils;
import cn.wtkj.charge_inspect.views.Adapter.OnItemClickListener;


/**
 * Created by lxg on 2015/11/5.
 */
public class GreenRecordPresenterImpl extends MvpBasePresenter<GreenRecordView> implements
        GreenRecordPresenter {


    private Context context;
    private Intent intent;
    private ConstAllDb constAllDb;
    private OrganizationDb organizationDb;
    private GreenChannelDb greenChannelDb;
    private PhotoVideoDb photoVideoDb;
    private boolean isNumber = true;//控制上传频率
    List<File> files;
    List<String> fileName;

    @Override
    public void attachContextIntent(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
        constAllDb = new ConstAllDb(context);
        organizationDb = new OrganizationDb(context);
        greenChannelDb = new GreenChannelDb(context);
        photoVideoDb = new PhotoVideoDb(context);
    }

    @Override
    public List<ViewOrganizationData.MData.info> getOrg(int orgId, String orgLevel) {
        List<ViewOrganizationData.MData.info> list = organizationDb.getCheckUnit(orgId, orgLevel);
        return list;
    }

    @Override
    public void startPresenter(List<File> fileList, JCGreenChannelRecData data) {
        if (!checkPhoto(data, fileList)){
            return;
        }

        if (fileList.size() > 0){
            for (int i=0; i< fileList.size(); i++){
                String oldFilePath = fileList.get(i).getPath();
                String oldFileName = fileList.get(i).getName();
                String newFilePath = getFileName(data, oldFileName);

                if (SysUtils.copyFile(oldFilePath, newFilePath)) {

                }
            }
        }

        if (isNumber) {
            getView().showLoding();
            String uuid = greenChannelDb.updateGreenChannelList(data);
            if (uuid != "" && fileList.size() > 0) {
                photoVideoDb.insertListPvd(fileList, uuid, 3);
            }
            getView().hideLoging();
            getView().nextView();
        }
        isNumber = !isNumber;//控制上传时间间隔
    }

    public String getFileName(JCGreenChannelRecData data, String fileName){

        String filePath = Environment.getExternalStorageDirectory() + "/稽查APP";
        int isMix = data.getIsMix();

        filePath += "/绿通/";
        filePath += isMix+"_"+data.getInStationID()+"_"+data.getVehPlateNo()+data.getGoodsName()+fileName;

        return filePath;
    }

    @Override
    public List<ConstAllData.MData.info> getConstByType(int type) {
        List<ConstAllData.MData.info> list_type = constAllDb.getConstList(type);
        return list_type;
    }

    @Override
    public List<PhotoVideoData> getListPvById(String uuid) {
        List<PhotoVideoData> datas=photoVideoDb.getPv(uuid,3,-1);
        return datas;
    }

    private Boolean checkPhoto(JCGreenChannelRecData data, List<File> fileList){
        int fileSize = fileList.size();
        int photoSize = 0;

        if (fileSize > 0){
            for (int i = 0; i < fileSize; i++) {
                File file = fileList.get(i);

                if (file.exists() && !fileList.get(i).getName().endsWith(".mp4")) {
                    photoSize ++;
                }
            }
        }


        if (photoSize > 8){
            getView().showMes("照片数量不能大于8张");
            return  false;
        }

        /* 不减免 */
        if (data.getIsEnjoy()  == 0 && photoSize < 4){
            getView().showMes("不减免，照片数量不能小于4张");
            return  false;
        }

        return true;

    }
}
