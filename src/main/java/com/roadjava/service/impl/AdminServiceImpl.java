package com.roadjava.service.impl;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

    public class AdminServiceImpl implements AdminService {
        public boolean validateAdmin(AdminDO adminDO) {
        String username = adminDO.getUser();
        String password = adminDO.getPwd();

        //用sql语句依照user_name查找信息
        String sql = "select pwd from manager where user_name = ?";//manager表里装着用户名和密码
        Connection conn =null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;//存获取到的表数据
        try {
            conn = DBUtil.getConn();//获取数据库连接
            if (conn==null)
                return false;
           ps = conn.prepareStatement(sql);//预执行sql语句
            ps.setString(1,adminDO.getUser());//第一个参数表示第一个问号,第二个参数是替换的信息
             resultSet = ps.executeQuery();//执行sql语句
            while (resultSet.next()){//表中逐行查找数据
                String pwd = resultSet.getString(1);
                if(adminDO.getPwd().equals(pwd)){//输入的密码和表中的密码相同返回ture
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭连接和数据流
            DBUtil.closeConn(conn);
            DBUtil.closeps(ps);
            DBUtil.closeRs(resultSet);
        }
        return false;
    }
}
