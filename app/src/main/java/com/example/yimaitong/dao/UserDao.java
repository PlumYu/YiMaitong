package com.example.yimaitong.dao;

import com.example.yimaitong.bean.User;
import com.example.yimaitong.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public static User login (String name, String password) {
        Connection conn = DBUtil.getConnection();
        String sql = "select * from t_user where name=? and password=?";
        User user = null;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            // bean d导入
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
            }
            DBUtil.release(rs, null, ps, conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static User findname(String name){
        Connection conn = DBUtil.getConnection();
        String sql = "select * from t_user where name=?";
        User user = null;
        PreparedStatement ps = null;
            try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            // bean d导入
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
            }
            DBUtil.release(rs, null, ps, conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    public static boolean register(String name, String password, String phone) {
        Connection conn = DBUtil.getConnection();
        System.out.println("?????");
        String sql = "insert into t_user(name,password,phone) values(?,?,?)";
        PreparedStatement ps = null;
        int count = 0;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,password);
            ps.setString(3,phone);
            count = ps.executeUpdate();
            DBUtil.release(null,null,ps,conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(count == 0){
            return false;
        }else{
            return true;
        }
    }

    public static boolean EitUser(String name, String password){
        Connection conn = DBUtil.getConnection();
        String sql = "update t_user set password = ? where name = ?";
        int count = 0;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,password);
            ps.setString(2,name);
            count = ps.executeUpdate();
            DBUtil.release(null,null,ps,conn);
            // bean d导入

        }catch (Exception e){
            e.printStackTrace();
        }
        if(count != 0){
            return true;
        }else{
            return false;
        }
    }
}
