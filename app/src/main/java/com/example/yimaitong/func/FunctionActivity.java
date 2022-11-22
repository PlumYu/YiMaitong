package com.example.yimaitong.func;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.yimaitong.R;
import com.example.yimaitong.util.ToastUtil;
import java.util.regex.Pattern;

public class FunctionActivity extends AppCompatActivity {

    String INR_str;
    private EditText mEtPt_user;
    private EditText mEtPt_avg;
    private EditText mEtISI;
    private Button mBtnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function);
        AlertDialog.Builder builder = new AlertDialog.Builder(FunctionActivity.this);

        if(savedInstanceState!=null){
            String tempdate = savedInstanceState.getString("show_text");
            builder.setMessage("INR值为" + tempdate);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            //    显示出该对话框
            builder.show();
        }

        mEtPt_user = findViewById(R.id.et_PT_user);
        mEtPt_avg = findViewById(R.id.et_PT_avg);
        mEtISI = findViewById(R.id.et_ISI);
        mBtnCalculate = findViewById(R.id.btn_calculate);

        mBtnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = mEtPt_user.getText().toString();
                String str2 = mEtPt_avg.getText().toString();
                String str3 = mEtISI.getText().toString();
                if(isDouble(str1) && isDouble(str2) && isDouble(str3) ) {
                    Double ptUser = Double.parseDouble(str1);
                    Double ptAvg = Double.parseDouble(str2);
                    Double ISI = Double.parseDouble(str3);

                    Double INR_Dou = Math.pow((ptUser / ptAvg), ISI);
                    INR_str = Double.toString(INR_Dou);
                    //    通过AlertDialog.Builder这个类来实例化我们的一个AlertDialog的对象
                    builder.setMessage("INR值为" +INR_str);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    //    显示出该对话框
                    builder.show();
                }else {
                    ToastUtil.ShowMsg(getApplicationContext(),"请检查以上值是否输入正确！");
                }
            }
        });
    }
    @Override
    protected void  onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("show_text",INR_str);
    }

    //判断字符串是否为double型
    public static boolean isDouble(String s) {
        Pattern pattern = Pattern.compile("[+-]?\\d+(.\\d+)?");
        return pattern.matcher(s).matches();
    }
}