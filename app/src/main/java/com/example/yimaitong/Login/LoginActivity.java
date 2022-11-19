package com.example.yimaitong.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yimaitong.R;
import com.example.yimaitong.func.FunctionActivity;
import com.example.yimaitong.util.ToastUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    private Button mBtnRegister;
    private Button mBtnLogin;
    private EditText mEtUser;
    private EditText mEtPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //找到控件
        mBtnLogin = findViewById(R.id.btn_login);
        mEtUser = findViewById(R.id.et_1);
        mEtPassword = findViewById(R.id.et_2);
        mBtnRegister = findViewById(R.id.btn_register);
        //注册新用户
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        //匹配的对应的用户名和密码
        mBtnLogin.setOnClickListener(this);
    }
    public void onClick(View v)
    {
        //需要获取用户名和密码
        String username = mEtUser.getText().toString();
        String userpassword = mEtPassword.getText().toString();
        //弹出内容设置
        String ok = "登陆成功";
        String fail = "密码或账号错误请重试";
        Intent intent = null;

        //假设正确的密码
        if(username.equals("YiMaitong") && userpassword.equals("123456"))
        {
            // toast普通版
            //Toast.makeText(getApplicationContext(),ok,Toast.LENGTH_SHORT).show();
            //用封装好的类
            ToastUtil.ShowMsg(getApplicationContext(),ok);
            //如果正确进行跳转
            intent = new Intent(LoginActivity.this, FunctionActivity.class);
            startActivity(intent);
        }
        else
        {
            //不正确,弹出登录失败toast
            //提升版，居中显示
//            Toast toastCenter = Toast.makeText(getApplicationContext(),fail,Toast.LENGTH_SHORT);
//            toastCenter.setGravity(Gravity.CENTER,0,0);
//            toastCenter.show();
            ToastUtil.ShowMsg(getApplicationContext(),fail);
        }
    }
}