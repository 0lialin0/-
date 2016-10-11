package cn.wtkj.charge_inspect.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;


import cn.wtkj.charge_inspect.R;


public class CustomDialog extends Dialog {
    private Button positiveButton, negativeButton;
    private TextView msgHint;

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog_show);
        setCustomDialog();
        show();

    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialog, null);
        msgHint = (TextView) mView.findViewById(R.id.title);
        positiveButton = (Button) mView.findViewById(R.id.positiveButton);
        negativeButton = (Button) mView.findViewById(R.id.negativeButton);
        super.setContentView(mView);
    }
 public  void setText(String msg){
        msgHint.setText(msg);
  }
    public  void setPositiveText(String text){
        positiveButton.setText(text);
    }
    public  void setNegativeText(String text){
        negativeButton.setText(text);
    }
    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }
    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeButton.setOnClickListener(listener);
    }
}