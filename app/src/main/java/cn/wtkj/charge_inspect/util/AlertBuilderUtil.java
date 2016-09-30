package cn.wtkj.charge_inspect.util;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;

public class AlertBuilderUtil {

	//Android系统版本9
	public static final int GINGERBREAD = 9;
	//Android系统版本11
	public static final int HONEYCOMB = 11;

	@SuppressLint("NewApi")
	public static AlertDialog.Builder getBuilder(Context context)
	{

		int currentAndroidVersion=Build.VERSION.SDK_INT;
		AlertDialog.Builder builder=null;
		if(currentAndroidVersion>=AlertBuilderUtil.HONEYCOMB)
		{
			builder=
					new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
		}else
		{
			builder=new AlertDialog.Builder(context);
		}
		return builder;
	}
	
}
