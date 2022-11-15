package com.example.yimaitong.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yimaitong.R;
import com.example.yimaitong.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    private Button mBtnConfirm;
    private Button mBtnCancel;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mEtConfirmPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        //找到控件
        mBtnConfirm = findViewById(R.id.btn_confirm);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mEtUsername = findViewById(R.id.re_1);
        mEtPassword = findViewById(R.id.re_2);
        mEtConfirmPassword = findViewById(R.id.re_3);
        //实现跳转，方法1
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                ToastUtil.ShowMsg(getApplicationContext(), "取消成功！");
            }
        });
        //匹配的对应的用户名和密码
        mBtnConfirm.setOnClickListener(this);
    }

    public void onClick(View v)
    {
        //需要获取用户名和密码
        String username = mEtUsername.getText().toString();
        String userpassword = mEtPassword.getText().toString();
        String userconfirmpassword = mEtConfirmPassword.getText().toString();
        //弹出内容设置
        String ok = "注册成功";
        String exc = "用户名错误，请重试！";
        String fail = "两次密码输入不相同，请重试";
        Intent intent = null;

        //假设正确的密码
        if (username.equals("Yunjige") && userpassword.equals(userconfirmpassword)) {
            // toast普通版
            //Toast.makeText(getApplicationContext(),ok,Toast.LENGTH_SHORT).show();
            //用封装好的类
            ToastUtil.ShowMsg(getApplicationContext(), ok);
            //如果正确进行跳转
            intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        else if (!username.equals("Yunjige"))
        {
            //不正确,弹出登录失败toast
            //提升版，居中显示
//            Toast toastCenter = Toast.makeText(getApplicationContext(),fail,Toast.LENGTH_SHORT);
//            toastCenter.setGravity(Gravity.CENTER,0,0);
//            toastCenter.show();
            ToastUtil.ShowMsg(getApplicationContext(), exc);
        }
        else
        {
            ToastUtil.ShowMsg(getApplicationContext(), fail);
        }
    }
}