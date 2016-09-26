package cn.wtkj.charge_inspect.data.net;

import java.util.Map;


import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.LoginRespone;
import cn.wtkj.charge_inspect.data.bean.Version;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import retrofit.http.QueryMap;

public interface DangerousApi {

    static final String END_POINT = "http://192.162.123.40:8080";// 收费稽查测试服务地
    static final String TRAFFIC = "http://113.240.255.154:8088/wttech.tiss-test/api";//收费稽查线上地址

    //验证imsi   OK
    @POST("/transportAppService/VerifyIMSI.do")
    void verifyIMSI(@QueryMap Map<String, String> imsi, Callback<ResponeData> callBack);

    //发送短信   OK
    @POST("/transportAppService/GetVerifyCode.do")
    void sendVerifyCode(@Body Map<String, String> phoneNum, Callback<ResponeData> callBack);

    //绑定手机号和imsi  OK
    @POST("/transportAppService/BindIMSI.do")
    void bindIMSI(@Body Map<String, String> querys, Callback<ResponeData> callBack);

    //获取危险品名称  OK
    @POST("/transportAppService/GetDGName.do")
    void getDGName(@Body Map<String, String> unCode, Callback<ResponeData> callBack);

    //停止运输  OK
    @POST("/transportAppService/CompleteTransport.do")
    void completeTransport(@QueryMap Map<String, String> map, Callback<ResponeData> callback);

    //每十秒调用GPS一次  OK
    @POST("/transportAppService/UpdateGPS.do")
    void updateGPS(@QueryMap Map<String, String> mapUpdateGPS, Callback<ResponeData> callback);


    //获取事故状态  OK
    @GET("/transportAppService/GetSosState.do")
    void getSosState(@Query("phoneNum") String phoneNum, Callback<ResponeData> callback);

    //获取版本号  OK
    @GET("/dangerousapp/getAppVersion.do")
    void getAppVersion(@Query("appType") String appType, Callback<Version> callback);


    //带密码的登陆接口  OK
    @GET("/restApi?businessId=pass.check")
    void login2(@Query("USERID") String user,
               @Query("PASSWORD") String pwd,
               Callback<ResponeData> callback);

    //带密码的登陆接口  OK
    @POST("/restApi?businessId=pass.check")
    void login(@QueryMap Map<String, String> map,Callback<LoginRespone> callback);

    //获取基础数据  OK
    @POST("/restApi?businessId=const.all")
    void getConstAll(Callback<ConstAllData> callback);

    //获取基础数据  OK
    @POST("/restApi?businessId=const.organization")
    void getOrgan(@QueryMap Map<String, String> map,Callback<ViewOrganizationData> callback);
}
