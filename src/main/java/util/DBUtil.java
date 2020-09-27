package util;

import java.sql.*;
import java.util.Properties;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
public class DBUtil  {
    private static final String URL ="jdbc:mysql://localhost:3306/student_gui";//数据库的地址
    private static final String USERNAME = "root";//数据库账号
    private static final String PWD = "root";//数据库密码
    private static final String DRIVER ="com.mysql.jdbc.Driver";

    static {
        try {
            //加载静态代码块
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {//获取数据库连接
        try {
           return  DriverManager.getConnection(URL, USERNAME, PWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭连接
    public static void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //关闭PrepareStatement和Resultset(可以写在一起)
    public static void closeps(PreparedStatement ps){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRs(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    }

