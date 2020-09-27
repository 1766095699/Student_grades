package com.roadjava.handler;

import com.roadjava.entity.StudentDO;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.MainView;
import com.roadjava.student.view.ext.AddStudentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentViewHandler implements ActionListener {

    AddStudentView addStudentView;
    private MainView mainView;

    public AddStudentViewHandler(AddStudentView addStudentView, MainView mainView) {
        this.addStudentView = addStudentView;
        this.mainView = mainView;
    }

    public void actionPerformed(ActionEvent e) {//点击添加按钮后的事件逻辑
        JButton jButton = (JButton) e.getSource();//得到按钮的名字
        String text = jButton.getText();///
        if("添加".equals(text)){
            StudentService studentService = new StudentServiceImpl();
            StudentDO studentDO = addStudentView.buildStudentDO();//获取添加的数据
            boolean addResulkt =  studentService.add(studentDO);//把数据添加进数据库
            if(addResulkt){
                //重新加载
                mainView.reloadTable();
            }else {
                JOptionPane.showMessageDialog(addStudentView,"添加失败");
            }
        }
        addStudentView.dispose();//销毁add界面
    }


}
