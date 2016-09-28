package cn.wtkj.charge_inspect.mvp.presenter;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.wtkj.charge_inspect.data.bean.ConstAllData;
import cn.wtkj.charge_inspect.data.bean.LoginRespone;
import cn.wtkj.charge_inspect.data.bean.ViewOrganizationData;
import cn.wtkj.charge_inspect.data.dataBase.ConstAllDb;
import cn.wtkj.charge_inspect.data.dataBase.OrganizationDb;
import cn.wtkj.charge_inspect.data.rest.LoginData;
import cn.wtkj.charge_inspect.data.rest.LoginDataImpl;
import cn.wtkj.charge_inspect.data.net.ResponeData;
import cn.wtkj.charge_inspect.mvp.MvpBasePresenter;
import cn.wtkj.charge_inspect.mvp.views.LoginView;
import cn.wtkj.charge_inspect.util.NetUtils;
import cn.wtkj.charge_inspect.util.Setting;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by lxg on 2015/11/5.
 */
public class LoginPresenterImpl extends MvpBasePresenter<LoginView> implements LoginPresenter {
    private static final String TAG = "LoginPresenterImpl";
    private Context context;
    protected static final int MSG_INBOX = 1;
    private LoginData loginData;
    private static final String USER_NOT_EXIST = "用户不存在，请联系管理人员！";
    private String phone;
    private String imsi;
    private static final String NO_READ_PHONE = "000000000000000";
    private boolean isSend = false;
    private String SHARE_APP_TAG = "share";
    private ConstAllDb constAllDb;
    private OrganizationDb organizationDb;
    private Map<String, String> map;
    private SharedPreferences setting;

    public LoginPresenterImpl(Context context) {
        this.context = context;
        loginData = new LoginDataImpl();
        constAllDb = new ConstAllDb(context);
        organizationDb=new OrganizationDb(context);
    }


    @Override
    public void login(final String user, String passWord) {
        getView().showLoding();
        map = new HashMap<>();
        map.put("USERID", user);
        map.put("PASSWORD", passWord);
        map.put("IS_GETUSER", "1");


        /*LoginButData loginButData=new LoginButData();
        loginButData.setUser(user);
        loginButData.setPwd(passWord);
        loginButData.setType("1");*/
        //loginMap.put("json", ResponeUtils.dataToJson(loginButData));

        loginData.login(map, new Callback<LoginRespone>() {
            @Override
            public void success(LoginRespone responeData, Response response) {

                if (responeData.getData().getState() == responeData.SUCCESS) {
                    Setting.USERID = user;
                    Setting.NAME = responeData.getData().getInfo().getNAME();
                    Setting.ORGLEVEL = responeData.getData().getInfo().getORGLEVEL();
                    Setting.OPRID = responeData.getData().getInfo().getOPRID();
                    Setting.ORGID = responeData.getData().getInfo().getORGID();
                    Setting.MOBILEPHONE = responeData.getData().getInfo().getMOBILEPHONE();
                    Setting.EMAIL = responeData.getData().getInfo().getEMAIL();
                    Setting.ORGANIZATION_NAME = responeData.getData().getInfo().getORGANIZATION_NAME();
                    Setting.ORGANIZATION_SHORTNAME = responeData.getData().getInfo().getORGANIZATION_SHORTNAME();
                    checkUpdate();
                } else {
                    getView().showMes(responeData.getData().getInfo().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                getView().hideLoging();
                getView().showMes(ResponeData.NET_ERROR);
            }
        });
    }

    @Override
    public void checkUpdate() {
        setting = context.getSharedPreferences(SHARE_APP_TAG, 0);
        Boolean user_first = setting.getBoolean("FIRST", true);
        if (user_first) {//第一次
            if (new NetUtils().isThereInternetConnection(context)) {
                setting.edit().putBoolean("FIRST", false).commit();
                checkConstAll();
            } else {
                getView().hideLoging();
                getView().showMes("当前无网络更新数据，请检查网络情况！");
            }
        }else{
            getView().hideLoging();
            getView().nextView();
        }

    }

    //检查用户数据是否需要更新
    private void checkConstAll() {
        loginData.updateConstAll(new Callback<ConstAllData>() {
            @Override
            public void success(ConstAllData constAllData, Response response) {
                if (constAllData.getMData().getState() == constAllData.SUCCESS) {
                    List<ConstAllData.MData.info> data = constAllData.getMData().getInfo();
                    for (int i = 0; i < data.size(); i++) {
                        constAllDb.updateConst(data.get(i));
                    }
                    checkOrganization();
                }else{
                    getView().showMes(constAllData.getMData().getInfo().toString());
                }


            }

            @Override
            public void failure(RetrofitError error) {
                setting.edit().putBoolean("FIRST", true).commit();
                getView().hideLoging();
                getView().showMes(ResponeData.NET_ERROR);
            }
        });


    }

    private void checkOrganization() {
        map = new HashMap<>();
        map.put("USERID", Setting.USERID);
        map.put("ORGID", Setting.ORGID + "");
        map.put("ORGLEVEL", Setting.ORGLEVEL);

        loginData.updateOrg(map, new Callback<ViewOrganizationData>() {
            @Override
            public void success(ViewOrganizationData viewOrganizationData, Response response) {
                if (viewOrganizationData.getMData().getState() == viewOrganizationData.SUCCESS) {
                    List<ViewOrganizationData.MData.info> data = viewOrganizationData.getMData().getInfo();
                    for (int i = 0; i < data.size(); i++){
                        organizationDb.updateOrg(data.get(i));
                    }
                    getView().hideLoging();
                    getView().nextView();
                }else{
                    getView().showMes(viewOrganizationData.getMData().getInfo().toString());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                setting.edit().putBoolean("FIRST", true).commit();
                getView().hideLoging();
                getView().showMes(ResponeData.NET_ERROR);
            }
        });
    }

}
