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
    private OrganizationDb organizationDb;
    private BlackListDb blackListDb;
    private PhotoVideoDb photoVideoDb;
    private boolean isNumber = true;//控制上传频率

    public NameRollAddPresenterImpl(Context context) {
        this.context = context;
        nameRollAddData = new ConductInfoDataImpl(context);
        constAllDb = new ConstAllDb(context);
        organizationDb = new OrganizationDb(context);
        blackListDb = new BlackListDb(context);
        photoVideoDb = new PhotoVideoDb(context);
    }

    @Override
    public void startPresenter(List<File> fileList, JCBlackListData data) {

        if (fileList.size() > 0){
            for (int i=0; i< fileList.size(); i++){
                String oldFilePath = fileList.get(i).getPath();
                String newFilePath = getFileName(data);

                if (copyFile(oldFilePath, newFilePath)) {

                }
            }
        }

        if (isNumber) {
            getView().showLoding();
            String uuid = blackListDb.updateBlackList(data);
            if (uuid!="" && fileList.size()>0) {
                photoVideoDb.insertListPvd(fileList, uuid, data.getNameType());
            }
            getView().himeDialog();
            getView().nextView();
        }
        isNumber = !isNumber;//控制上传时间间隔
    }

    public String getFileName(JCBlackListData data){
        int nameType = data.getNameType();

        String filePath = Environment.getExternalStorageDirectory() + "/稽查APP";

        if (nameType ==0){
            filePath += "/黑名单/";
            filePath += data.getVepPlateNo()+data.getPeccancyTypeName()+data.getGenDT();
        }else if (nameType == 1){
            filePath += "/灰名单/";
            filePath += data.getVepPlateNo()+data.getPeccancyTypeName()+data.getGenDT();
        }else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmm");
            String addTime = simpleDateFormat.format(new Date());
            filePath += "/黄名单/";
            filePath += data.getVepPlateNo()+data.getPeccancyTypeName()+addTime;
        }
        filePath += ".jpg";
        return filePath;
    }
    /**
     * 复制单个文件
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public Boolean copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                //目标目录
                File targetDir = new File(newPath);
                //创建目录
                if(!targetDir.exists())
                {
                    targetDir.getParentFile().mkdirs();
                    targetDir.createNewFile();
                }

                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1024];
                int length;
                while ( (byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();

                return true;
            }
        }
        catch (Exception e) {
            System.out.println("复制单个文件操作出错:"+e.getMessage());
            e.printStackTrace();
        }

        return false;
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


}
