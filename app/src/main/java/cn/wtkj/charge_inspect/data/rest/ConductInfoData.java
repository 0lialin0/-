package cn.wtkj.charge_inspect.data.rest;

import java.io.File;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.NameRollXiafaData;
import cn.wtkj.charge_inspect.data.net.DataRequester;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import retrofit.Callback;


/**
 * Created by lxg on 2015/11/9.
 */
public interface ConductInfoData {

    //黑名单上传
    void nameRoll(Map<String, String> loginMap, List<String> fileNames, List<File> files,
                  final DataRequester.DataCallBack<ResponeData> callBack);

    //增收上传
    void sendIncrement(Map<String, String> map, Callback<ResponeData> callback);

    //下发名单查询
    void selNameXiafa(Map<String, String> map, Callback<NameRollXiafaData> callback);
}
