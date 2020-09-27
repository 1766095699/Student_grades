package com.roadjava.handler;

import com.roadjava.entity.AdminDO;
import com.roadjava.service.AdminService;
import com.roadjava.service.impl.AdminServiceImpl;
import com.roadjava.student.view.LoginView;
import com.roadjava.student.view.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER ==  e.getKeyCode()){
            login();
        }
    }

    private LoginView loginView;
    public LoginHandler(LoginView loginView){
        this.loginView=loginView;
    }
    public void actionPerformed(ActionEvent e) {//点击事件
        JButton jButton = (JButton) e.getSource();
        String text = jButton.getText();
        if("重置".equals(text)){
            loginView.getUserTxt().setText("");
            loginView.getPasswordTxt().setText("");
        }
        else if("登录".equals(text)){
            login();
        }

    }

    private void login() {
        String user = loginView.getUserTxt().getText();//得到输入框中的用户名

        //设置输入框为空时候的提示效果
        if("".equals(user.trim())){
            JOptionPane.showMessageDialog(loginView,"用户名不能为空");
            return ;
        }
        char[] chars = loginView.getPasswordTxt().getPassword();
        if (chars==null)
        {
            JOptionPane.showMessageDialog(loginView,"密码不能为空");
            return;
        }


        String pwd = new String(chars);
        AdminService adminService = new AdminServiceImpl();
        AdminDO adminDO = new AdminDO();
        adminDO.setUser(user);
        adminDO.setPwd(pwd);
        boolean flag = adminService.validateAdmin(adminDO);//判断是否存在该用户
        if(flag){//如果登陆成功,则销毁原来的登陆界面,new一个新界面
            new MainView();
            loginView.dispose();
        }
        else {
            JOptionPane.showMessageDialog(loginView,"用户名密码错误");//如果登陆失败，弹出dialog
        }
        System.out.println(user+pwd);
        System.out.println("登录");
    }
}
