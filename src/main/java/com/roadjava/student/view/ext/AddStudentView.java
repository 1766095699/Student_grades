package com.roadjava.student.view.ext;

import com.roadjava.entity.StudentDO;
import com.roadjava.handler.AddStudentViewHandler;
import com.roadjava.student.view.MainView;

import javax.swing.*;
import java.awt.*;



public class AddStudentView extends JDialog {//设置UI
    JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
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
    JLabel enLable = new JLabel("英语成绩:",JLabel.RIGHT);
    JTextField enText = new JTextField();

    JButton addBtn = new JButton("添加");

    AddStudentViewHandler addStudentViewHandler;
    public  AddStudentView(MainView mainView){
        super(mainView,"添加学生",true);
        //UI布局
        addStudentViewHandler =  new AddStudentViewHandler(this,mainView);//第一个参数传的是当前界面,第二个参数传的是返回的主页面
        nameLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLable);
        nameText.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameText);

        noLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(noLable);
        noText.setPreferredSize(new Dimension(200,30));
        jPanel.add(noText);

        homeLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(homeLable);
        homeText.setPreferredSize(new Dimension(200,30));
        jPanel.add(homeText);

        cnLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(cnLable);
        cnText.setPreferredSize(new Dimension(200,
                30));
        jPanel.add(cnText);

        mathLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(mathLable);
        mathText.setPreferredSize(new Dimension(200,30));
        jPanel.add(mathText);

        enLable.setPreferredSize(new Dimension(80,30));
        jPanel.add(enLable);
        enText.setPreferredSize(new Dimension(200,30));
        jPanel.add(enText);

        addBtn.addActionListener(addStudentViewHandler);//把Handler传入添加按钮(Handler就是用来控制事件的)
        jPanel.add(addBtn);
        Container contentPane = getContentPane();
        contentPane.add(jPanel);
        JTextField enText = new JTextField();
        setSize(350,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public StudentDO buildStudentDO(){//设置要添加的学生信息
        StudentDO studentDO = new StudentDO();
        studentDO.setName(nameText.getText());
        studentDO.setNo(noText.getText());
        studentDO.setHomeTown(homeText.getText());
        studentDO.setCnScore(Double.valueOf(cnText.getText()));
        studentDO.setEnScore(Double.valueOf(enText.getText()));
        studentDO.setMathScore(Double.valueOf(mathText.getText()));
        return studentDO;

    }

}
