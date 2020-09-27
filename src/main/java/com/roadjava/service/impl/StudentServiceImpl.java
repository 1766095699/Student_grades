package com.roadjava.service.impl;

import com.roadjava.entity.StudentDO;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StudentService;
import util.DBUtil;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class StudentServiceImpl implements StudentService {

    public TableDTO retriveStudents(StudentRequest request) {//返回TableDTO对象,其中有列表的数据和一些方法
        StringBuilder sql = new StringBuilder();
        sql.append("select * from student ");
        if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){//如果查询内容为空或者只有空格
            sql.append("where name like '%"+request.getSearchKey().trim()+"%'");//sql中的like语句实现关键字搜索
        }
        sql.append(" order by id desc limit ").append(request.getStart()).append(",").append(request.getPagesize());//limit控制获取数据的去间
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TableDTO tableDTO = new TableDTO();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();//rs中存储从数据库中查询到的数据(如果只是增添,修改，删除的话，就是直接获取全部数据)
            tableDTO.setData(fillData(rs));//传入表中的数据到tableTao这个对象
            sql.setLength(0);//字符串清零
            //下面的代码都是在查询count数
            sql.append("select count(*) from student");
            if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
                sql.append("where name like '%"+request.getSearchKey().trim()+"%'");
            }
            ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt(1);
                tableDTO.setTotalCount(count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closeConn(conn);
            DBUtil.closeps(ps);
        }
        return tableDTO;
    }

    public boolean add(StudentDO studentDO) {
        StringBuilder sql = new StringBuilder();
        sql.append(" insert into student(name,no,home_town,cn_score,en_score,math_score) ");
        sql.append(" values(?,?,?,?,?,?) ");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setObject(1,studentDO.getName());
            ps.setObject(2,studentDO.getNo());
            ps.setObject(3,studentDO.getHomeTown());
            ps.setObject(4,studentDO.getCnScore());
            ps.setObject(5,studentDO.getEnScore());
            ps.setObject(6,studentDO.getMathScore());
            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(conn);
            DBUtil.closeps(ps);
        }
        return false;
    }

    public StudentDO getbyId(int selectedStudentId) {
        StringBuilder sql = new StringBuilder("select * from student where id = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        StudentDO studentDO = new StudentDO();
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            ps.setInt(1,selectedStudentId);
            rs = ps.executeQuery();
            while (rs.next()){
                //处理查出的每一条记录
                Vector<Object>oneRecord = new Vector<Object>();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String no = rs.getString("no");
                String home_town = rs.getString("home_town");
                Double cnScore = rs.getDouble( "cn_score");
                Double enScore = rs.getDouble("en_score");
                Double mathScore = rs.getDouble("math_score");
                studentDO.setName(name);
                studentDO.setNo(no);
                studentDO.setHomeTown(home_town);
                studentDO.setCnScore(cnScore);
                studentDO.setMathScore(mathScore);
                studentDO.setEnScore(enScore);
                return studentDO;
            }

           return studentDO;
            } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeRs(rs);
            DBUtil.closeConn(conn);
            DBUtil.closeps(ps);
        }
        return studentDO;
    }

    public boolean update(StudentDO studentDO) {
        StringBuilder sql = new StringBuilder();
        sql.append(" update student set name = ?,no=?,home_town=?,cn_score=?,en_score=?,math_score = ?");
        sql.append(" where id = ?");
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());;
            ps.setString(1,studentDO.getName());
            ps.setString(2,studentDO.getNo());
            ps.setString(3,studentDO.getHomeTown());
            ps.setDouble(4,studentDO.getCnScore());
            ps.setDouble(5,studentDO.getEnScore());
            ps.setDouble(5,studentDO.getEnScore());
            ps.setDouble(6,studentDO.getMathScore());
            ps.setInt(7,studentDO.getId());
            return ps.executeUpdate()==1;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                DBUtil.closeConn(conn);
                DBUtil.closeps(ps);
        }
        return false;
    }

    public boolean delete(int[] selectedStudentIds) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from student where id in (");
        int lenth =selectedStudentIds.length;
        for(int i=0;i<lenth;i++){
            if(i== lenth-1)
                sql.append(" ?)");
            else
                sql.append(" ?,");
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtil.getConn();
            ps = conn.prepareStatement(sql.toString());
            for(int i = 0 ;i<lenth;i++){
                ps.setInt(i+1,selectedStudentIds[i]);
            }
            return ps.executeUpdate() == lenth;//这里返回值是删除的数量

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>>data = new Vector<Vector<Object>>();//data是个二重容器
        while (rs.next()){//(rs应该也是类似的二重容器，通过next来逐行查找数据)
            //处理查出的每一条记录用Onerecord存,最后放入data中
            Vector<Object>oneRecord = new Vector<Object>();//装单条数据
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String no = rs.getString("no");
            String home_town = rs.getString("home_town");
            Double cnScore = rs.getDouble("cn_score");
            Double enScore = rs.getDouble("en_score");
            Double mathScore = rs.getDouble("math_score");
            Double totalScore = cnScore+enScore+mathScore;
            oneRecord.addElement(id);
            oneRecord.addElement(name);
            oneRecord.addElement(no);
            oneRecord.addElement(home_town);
            oneRecord.addElement(cnScore);
            oneRecord.addElement(mathScore);
            oneRecord.addElement(totalScore);
            data.addElement(oneRecord);
        }
        return data;//返回查找到的数据集
    }
}
