package com.example.yimaitong.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
    // 接口服务器
    private static final String databaseName = "yimaitongdb"; // 数据库名称
    private static final String ip = ""; // 主机名
    private static final String port = "3306"; // 端口号 例如 mysql 的默认端口号为 3306
    private static final String user = "";// 用户名
    private static final String password = "";// 密码
    //注册msql驱动
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private static String url = "jdbc:mysql://" + ip + ":" + port + "/" + databaseName;

    public static Connection getConnection(){
        Connection conn = null;
        try{
            System.out.println(url);
            conn = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void release(ResultSet rs, Statement st, PreparedStatement ps, Connection conn){
        try {
            if(rs != null){
                rs.close();
            }
            if(st != null){
                st.close();
            }
            if(ps != null) {
                ps.close();
            }
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
