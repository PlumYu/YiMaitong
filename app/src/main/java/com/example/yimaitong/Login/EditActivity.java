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
import com.example.yimaitong.util.DBUtil;
import com.example.yimaitong.util.ToastUtil;

import java.sql.Connection;
import java.util.regex.Pattern;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    private Button mBtnConfirm;
    private Button mBtnCancel;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private EditText mEtConfirmPassword;
    private EditText mEtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mBtnConfirm = findViewById(R.id.btn_confirm);
        mBtnCancel = findViewById(R.id.btn_cancel);
        mEtUsername = findViewById(R.id.re_u);
        mEtPhone = findViewById(R.id.re_p);
        mEtPassword = findViewById(R.id.re_ps);
        mEtConfirmPassword = findViewById(R.id.re_cps);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(EditActivity.this, LoginActivity.class);
                startActivity(intent);
                ToastUtil.ShowMsg(getApplicationContext(), "取消成功！");
            }
        });
        mBtnConfirm.setOnClickListener(this);
    }
    public void EditerTest(String name, String phone, String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Connection con = DBUtil.getConnection();
                Message message = handler.obtainMessage();
                User user = UserDao.findname(name);
                if(user != null){
                    if(user.getPhone().equals(phone)){
                        boolean EditSuccess = UserDao.EitUser(name, password);
                        if(EditSuccess){
                            message.what = 4;
                            message.obj = "修改成功！";
                        }else{
                            message.what = 3;
                            message.obj = "修改失败，请重试！";
                        }
                    }else{
                        message.what = 2;
                        message.obj = "手机号码错误，请重试！";
                    }
                }else{
                    message.what = 1;
                    message.obj = "查找失败，该用户不存在！";
                }
                handler.sendMessage(message);
            }
        }).start();
    }
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            System.out.println("----------------------------");
            System.out.println(msg.obj);
            System.out.println("____________________________");
            switch(msg.what){
                case 1:
                case 2:
                case 3:
                    String tip = (String) msg.obj;
                    ToastUtil.ShowMsg(EditActivity.this,tip);
                    break;
                case 4:
                    String tip3 = (String) msg.obj;
                    ToastUtil.ShowMsg(EditActivity.this,tip3);
                    Intent intent = new Intent(EditActivity.this, LoginActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v)
    {
        //需要获取用户名和密码
        String username = mEtUsername.getText().toString();
        String userpassword = mEtPassword.getText().toString();
        String userconfirmpassword = mEtConfirmPassword.getText().toString();
        String userphone = mEtPhone.getText().toString();
        //弹出内容设置
        String fail = "两次密码输入不相同，请重试";
        String ErPhone = "手机号格式错误";
        String Emptry = "某项输入为空或包含字符空格，请检查！";
        Pattern pattern = Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
        boolean user = username.length() == 0 || username.contains(" ");
        boolean pas = userpassword.length() == 0 || userpassword.contains(" ");
        boolean cpas = userconfirmpassword.length() == 0 || userconfirmpassword.contains(" ");
        boolean up = userphone.length() == 0 || userphone.contains(" ");
        if(user || pas || cpas || up) {
            ToastUtil.ShowMsg(EditActivity.this,Emptry);
        }else if(userpassword.length() < 6){
            ToastUtil.ShowMsg(EditActivity.this,"密码长度必须大于 6 个字符！");
        }else if(!userpassword.equals(userconfirmpassword)){
            ToastUtil.ShowMsg(EditActivity.this,fail);
        }else if(!pattern.matcher(userphone).matches()) {
            ToastUtil.ShowMsg(EditActivity.this,ErPhone);
        }else{
            EditerTest(username, userphone, userpassword);
        }
    }
}