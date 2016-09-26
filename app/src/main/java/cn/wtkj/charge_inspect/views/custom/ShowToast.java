package cn.wtkj.charge_inspect.views.custom;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/1/19.
 */
public class ShowToast {
    public static void show(Context context, String msg, int time) {
        Toast toast = Toast.makeText(context, msg, time);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
