package com.roadjava.student.view.ext;

import com.roadjava.entity.StudentDO;
import com.roadjava.handler.UpdateStudentViewHandler;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.MainView;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentView extends JDialog {
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel idLable = new JLabel("学生编号",JLabel.RIGHT);
    JTextField idText = new JTextField();
    JLabel nameLable = new JLabel("姓名:",JLabel.RIGHT);
    JTextField nameText = new JTextField();
    JLabel noLable = new JLabel("学号:",JLabel.RIGHT);
    JTextField noText = new JTextField();
    JLabel homeLable = new JLabel("家乡:",JLabel.RIGHT);
    JTextField homeText = new JTextField();
    JLabel cnLable = new JLabel("语文成绩:",JLabel.RIGHT);
    JTextField cnText = new JTextField();
    JLabel mathLable = new JLabel("数学成绩:",JLabel.RIGHT);
    JTextField mathText = new JTextField();
    JLabel enLable = new JLabel("英语成绩::",JLabel.RIGHT);
    JTextField enText = new JTextField();

    JButton updateBtn = new JButton("修改");
    UpdateStudentViewHandler updateStudentViewHandler;
    public   UpdateStudentView(MainView mainView, int selectedStudentId){
        super(mainView,"修改学生",true);
        updateStudentViewHandler = new UpdateStudentViewHandler(this,mainView);
        StudentService studentService =new StudentServiceImpl();
        StudentDO selectedStu = studentService.getbyId(selectedStudentId);
        idLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLable);
        idText.setPreferredSize(new Dimension(200,30));
        idText.setEnabled(false);
        idText.setText(selectedStudentId+"");
        //设置Id不可编辑
        jPanel.add(idText);
        nameLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLable);
        nameText.setText(selectedStu.getName());
        nameText.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameText);

        noLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(noLable);
        noText.setText(selectedStu.getNo());
        noText.setPreferredSize(new Dimension(200,30));
        jPanel.add(noText);

        cnLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(cnLable);
        cnText.setText(String.valueOf(selectedStu.getCnScore()));
        cnText.setPreferredSize(new Dimension(200,30));
        jPanel.add(cnText);

        mathLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(mathLable);
        mathText.setText(String.valueOf(selectedStu.getMathScore()));
        mathText.setPreferredSize(new Dimension(200,30));
        jPanel.add(mathText);

        enLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(enLable);
        enText.setText(String.valueOf(selectedStu.getEnScore()));
        enText.setPreferredSize(new Dimension(200,30));
        jPanel.add(enText);

        homeLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(homeLable);
        homeText.setPreferredSize(new Dimension(200,30));
        homeText.setText(selectedStu.getHomeTown());
        jPanel.add(homeText);

        updateBtn.addActionListener(updateStudentViewHandler);
        jPanel.add(updateBtn);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        setSize(350,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    //获取修改后的学生对象
    public StudentDO buildUpdateStudentDO(){

        StudentDO studentDO = new StudentDO();
        studentDO.setId(Integer.valueOf(idText.getText()));
        studentDO.setName(nameText.getText());
        studentDO.setNo(noText.getText());
        studentDO.setHomeTown(homeText.getText());
        studentDO.setCnScore(Double.valueOf(cnText.getText()));
        studentDO.setEnScore(Double.valueOf(enText.getText()));
        studentDO.setMathScore(Double.valueOf(mathText.getText()));
        return studentDO;
    }
}
