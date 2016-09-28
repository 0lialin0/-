package cn.wtkj.charge_inspect.data;

import java.util.concurrent.TimeUnit;

import cn.wtkj.charge_inspect.data.net.DangerousApi;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Administrator on 2016/1/28.
 */
public class SeApiManager {

    public static DangerousApi apiMangerAdapter() {

        RestAdapter dangerousAdapter = new RestAdapter.Builder()
                .setEndpoint(DangerousApi.END_POINT)
                .build();
        return dangerousAdapter.create(DangerousApi.class);
    }

//    public static DangerousApi apiMangerAdapter(boolean why) {
//        RestAdapter dangerousAdapter = new RestAdapter.Builder()
//                .setEndpoint("http://113.240.255.154:8881/dgtest")
////                .setEndpoint("http://172.16.3.35:8080/hnwxpys-web")
////                .setEndpoint("http://172.16.3.137:8080/hnwxpys-web")
//                .build();
//        return dangerousAdapter.create(DangerousApi.class);
//    }
}
