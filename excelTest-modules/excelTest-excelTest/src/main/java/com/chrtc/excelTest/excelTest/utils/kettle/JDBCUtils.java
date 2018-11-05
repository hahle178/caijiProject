package com.chrtc.excelTest.excelTest.utils.kettle;

import com.mysql.jdbc.PreparedStatement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JDBCUtils {

    private static Connection conn;

    /*
     * 获取数据库连接
     */
    public static Connection getConnection(String DBDRIVER,String  DBURL,String DBUSER,String DBPASSWORD) {
        try {
            Class.forName(DBDRIVER);// 注册驱动
            conn = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD);// 获得连接对象
        } catch (ClassNotFoundException e) {// 捕获驱动类无法找到异常
            e.printStackTrace();
        } catch (SQLException e) {// 捕获SQL异常
            e.printStackTrace();
        }
        return conn;
    }
}
