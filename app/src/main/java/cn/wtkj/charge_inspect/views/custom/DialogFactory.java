package cn.wtkj.charge_inspect.views.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import java.lang.ref.WeakReference;

import cn.wtkj.charge_inspect.R;

/**
 * Created by Administrator on 2016/1/5.
 */
public class DialogFactory {

    private static final String TAG = "DialogFactory";

    private DialogFactory() {
    }

    public static DialogFactory getDialogFactory() {
        return SingletonHolder.mDialogFactory;
    }

    public void showMsgDilog(String msg, Context context, final OnCilckSure onCilckSure) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onCilckSure.sure();
            }
        });
        dialog.show();
    }

    public AlertDialog showSendMessage(Context context, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("提示");
        View view = LayoutInflater.from(context).inflate(R.layout.diloag_send_message, null);
        TextView tvMes = (TextView) view.findViewById(R.id.dilog_text_msg);
        dialog.setView(view);
        dialog.setCancelable(false);
        AlertDialog alertDialog = dialog.show();
        CountDown countDown = new CountDown(60 * 1000, 1000, tvMes, alertDialog, msg);
        countDown.start();
        return alertDialog;
    }

    private static class SingletonHolder {
        private static DialogFactory mDialogFactory = new DialogFactory();
    }

    public interface OnCilckSure {
        void sure();
    }

    private static class CountDown extends CountDownTimer {
        private WeakReference<TextView> tvMsg;
        private WeakReference<AlertDialog> dialog;
        private String msg;

        public CountDown(long millisInFuture, long countDownInterval, TextView textView,
                         AlertDialog dialog, String msg) {
            super(millisInFuture, countDownInterval);
            tvMsg = new WeakReference<>(textView);
            this.dialog = new WeakReference<>(dialog);
            this.msg = msg;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (null != tvMsg.get()) {
                tvMsg.get().setText(String.format(msg, millisUntilFinished / 1000));
            }
        }

        @Override
        public void onFinish() {
            if (this.dialog.get() != null) {
                this.dialog.get().dismiss();
            }
        }
    }
}
