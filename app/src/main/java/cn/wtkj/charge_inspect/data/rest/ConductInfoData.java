package cn.wtkj.charge_inspect.data.rest;

import java.io.File;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.BlackListData;
import cn.wtkj.charge_inspect.data.bean.JCBlackListData;
import cn.wtkj.charge_inspect.data.bean.NameRollXiafaData;
import cn.wtkj.charge_inspect.data.bean.OutListData;
import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import retrofit.Callback;


/**
 * Created by lxg on 2015/11/9.
 */
public interface ConductInfoData {

    //黑名单上传
    void nameRoll(Map<String, String> map, List<String> fileNames, List<File> files,
                  final DataRequester.DataCallBack<ResponeData> callBack);

    //增收上传
    void sendIncrement(Map<String, String> map, Callback<ResponeData> callback);

    //下发名单查询
    void selNameXiafa(Map<String, String> map, Callback<NameRollXiafaData> callback);

    //下发黑名单处理
    void sendXiafaHandle(Map<String, String> map, Callback<ResponeData> callback);

    //下发名单详情
    void sendXiafaInfo(Map<String, String> map,int type, Callback<BlackListData> callback);


    //绿通档案
    void greenRecord(Map<String, String> map, List<String> fileNames, List<File> files,
                  final DataRequester.DataCallBack<ResponeData> callBack);

    //流水查询
    void outListSel(Map<String, String> map, Callback<OutListData> callback);
}
