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

    //static final String END_POINT = "http://192.162.123.40:8080";// 收费稽查测试服务地
    static final String END_POINT = "http://220.178.67.242:8554/appws";//收费稽查线上地址


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

    //获取业务联系单  OK
    @POST("/restApi?businessId=daily.contact")
    void getBusiness(Callback<ViewOrganizationData> callback);
}
