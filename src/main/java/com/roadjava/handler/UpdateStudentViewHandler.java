package com.roadjava.handler;

import com.roadjava.entity.StudentDO;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.MainView;
import com.roadjava.student.view.ext.UpdateStudentView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudentViewHandler implements ActionListener {
    private UpdateStudentView updateStudentView;
    private MainView mainView;

    public UpdateStudentViewHandler(UpdateStudentView updateStudentView,MainView mainView) {
        this.updateStudentView = updateStudentView;
        this.mainView = mainView;
    }

    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("修改".equals(text)){
            StudentService studentService = new StudentServiceImpl();
            StudentDO studentDO = updateStudentView.buildUpdateStudentDO();//添加的数据进类
            boolean addResulkt =  studentService.update(studentDO);//更新数据库数据
            if(addResulkt){
                //重新加载
                mainView.reloadTable();
            }else {
                JOptionPane.showMessageDialog(updateStudentView,"修改失败");
            }
        }
        updateStudentView.dispose();
    }
}
