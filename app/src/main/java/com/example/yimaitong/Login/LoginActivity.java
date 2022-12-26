package com.example.yimaitong.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yimaitong.R;
import com.example.yimaitong.bean.User;
import com.example.yimaitong.dao.UserDao;
import com.example.yimaitong.func.FunctionActivity;
import com.example.yimaitong.util.DBUtil;
import com.example.yimaitong.util.ToastUtil;

import java.sql.Connection;

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
private static final int SUCCESS = 1;
    private static final int FAIL = 2;
    public void LoginTest(String name, String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                User user;
                Connection con = DBUtil.getConnection();
                user = UserDao.login(name, password);
                Message message = handler.obtainMessage();
                if(user != null){
                    if(user.getName().equals(name) && user.getPassword().equals(password)){
                        message.what = SUCCESS;
                        message.obj = "登录成功";
                    }
                }else{
                    message.what = FAIL;
                    message.obj = "账号或密码错误";
                }
                handler.sendMessage(message);
                try {
                    DBUtil.release(null,null,null,con);
                } catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case SUCCESS:
                    String tips1 = (String) msg.obj;
                    ToastUtil.ShowMsg(LoginActivity.this,tips1);
                    Intent intent = new Intent(LoginActivity.this, FunctionActivity.class);
                    startActivity(intent);
                    break;
                case FAIL:
                    String tips2 = (String) msg.obj;
                    ToastUtil.ShowMsg(LoginActivity.this, tips2);
                    break;
            }
        }
    };
    public void onClick(View v)
    {
        //需要获取用户名和密码
        String username = mEtUser.getText().toString();
        String userpassword = mEtPassword.getText().toString();
        //弹出内容设置
        if(username != null && userpassword != null)    LoginTest(username, userpassword);
        else    ToastUtil.ShowMsg(LoginActivity.this,"某项输入为空，请检查！");
    }
}