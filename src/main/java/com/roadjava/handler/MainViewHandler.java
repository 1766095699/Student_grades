package com.roadjava.handler;

import com.roadjava.entity.StudentDO;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.MainView;
import com.roadjava.student.view.ext.AddStudentView;
import com.roadjava.student.view.ext.UpdateStudentView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainViewHandler implements ActionListener {
    private MainView mainView;

    public MainViewHandler(MainView mainView) {
        this.mainView = mainView;
    }

    public void actionPerformed(ActionEvent e) {
        JButton jButton  = (JButton) e.getSource();
        String string = jButton.getText();

        if("增加".equals(string)){////点击增加添加界面
            new AddStudentView(mainView);
        }


        else if("修改".equals(string)){
            int[] selectedStudentIds = mainView.getSelectedStudentIds();
            if(selectedStudentIds.length!=1){
                JOptionPane.showMessageDialog(mainView,"一次只能修改一个");
                return;
            }
            if(selectedStudentIds.length==0){
                JOptionPane.showMessageDialog(mainView,"请选择要修改的选项");
                return;
            }
            new UpdateStudentView(mainView,selectedStudentIds[0]);//更新界面

        }


        else if("删除".equals(string)){
            int[] selectedStudentIds = mainView.getSelectedStudentIds();//得到选中行的id
            if(selectedStudentIds.length==0){
                JOptionPane.showMessageDialog(mainView,"请选择要删除的行");
            }
            int option = JOptionPane.showConfirmDialog(mainView,"你确认要删除选中的行吗","确认删除",JOptionPane.YES_NO_CANCEL_OPTION);
            if(option == JOptionPane.YES_OPTION)
            {
                //执行删除
                StudentService studentService = new StudentServiceImpl();
                boolean res = studentService.delete(selectedStudentIds);
                if(res){
                    //删除成功
                    mainView.reloadTable();
                }else{
                    JOptionPane.showMessageDialog(mainView,"删除失败");
                }
            }
        }else if("查询".equals(string)){
            mainView.setpagenow(1);
            mainView.reloadTable();
        }else if("上一页".equals(string)){
            mainView.setpagenow(mainView.getpagenow()-1);
            mainView.reloadTable();
        }else if("下一页".equals(string)){
            mainView.setpagenow(mainView.getpagenow()+1);
            mainView.reloadTable();
        }
    }
}
