package cn.wtkj.charge_inspect.util;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.wtkj.charge_inspect.data.net.DangerousApi;


/**
 * Created by lxg on 2015/9/8.
 */
public class ResponeUtils {
    private static final String STATUS = "成功";

    public static boolean isStatusAvailable(String status){
        return STATUS.equals(status);
    }


    public static String getUrl(String method, Map<String, String> map){
        StringBuffer sb = new StringBuffer();
        String key;
        String value;
        sb.append(DangerousApi.END_POINT).append(method);

        Set<String> mSet = map.keySet();
        Iterator<String> mIterator = mSet.iterator();
        while (mIterator.hasNext()) {
            key = mIterator.next();
            value = map.get(key);
            sb.append("&");
            sb.append(key);
            sb.append("=");
            sb.append(value);
        }
        return sb.toString();
    }

    public static String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }

    public static String getTimeMinute(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(System.currentTimeMillis());
    }

    public static String dataToJson(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
