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
    private static final String ip = "rm-bp1w73wc8hf58i8pyto.mysql.rds.aliyuncs.com"; // 主机名
    private static final String port = "3306"; // sqlserver 端口号 例如 mysql 的默认端口号为 3306
    private static final String user = "sa";// 用户名
    private static final String password = "Qq2477934060";// 密码
    //注册msql驱动
    static{
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    // jdbc:mysql://rm-bp1w73wc8hf58i8pyto.mysql.rds.aliyuncs.com:3306/yimaitongdb;
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
