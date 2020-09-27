package com.roadjava.student.view;

import com.roadjava.handler.LoginHandler;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.URL;
import java.awt.event.*;


public class LoginView extends JFrame {
    JLabel nameLabel = new JLabel("乐之者java swing项目",JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    JLabel userNameLabel = new JLabel("用户名:");
    JTextField userTxt = new JTextField();
    JLabel passwordLabel = new JLabel("密码:");
    JPasswordField passwordTxt = new JPasswordField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");
    JLabel author = new JLabel("By LJH",JLabel.SOUTH_EAST);
    LoginHandler loginHandler;//设置登陆按钮的点击事件
    public LoginView(){
        super("乐之者java学生成绩管理");

        loginHandler = new LoginHandler(this);

        Container contentPane = getContentPane();
        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
        author.setFont(new Font("华文行楷",Font.PLAIN,40));
        Font centerFont = new Font("楷体",Font.PLAIN,15);
        nameLabel.setPreferredSize(new Dimension(0,80 ));
        author.setPreferredSize(new Dimension(0,40));
        userNameLabel.setFont(centerFont);
        userTxt.setPreferredSize(new Dimension(200,25));
        passwordLabel.setFont(centerFont);
        passwordTxt.setPreferredSize(new Dimension(200,25));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
        //把组件加入面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userTxt);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordTxt);
        centerPanel.add(loginBtn);
        loginBtn.addActionListener(loginHandler);
        loginBtn.addKeyListener(loginHandler);
        centerPanel.add(resetBtn);
        resetBtn.addActionListener(loginHandler);
        LayoutCenter();


        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);
        contentPane.add(author,BorderLayout.SOUTH);


        //设置loginBtn为默认按钮
        getRootPane().setDefaultButton(loginBtn);
        //自定义图标
        URL imgurl = LoginView.class.getClassLoader().getResource("xiaoji.png");
        setIconImage(new ImageIcon(imgurl).getImage());
        setSize(600,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void LayoutCenter() {
        //弹簧布局
        //布局userNameLabel
        Spring childWidth = Spring.sum(Spring .sum(Spring.width(userNameLabel),Spring.width(userTxt)),Spring.constant(20));
        int offsetX = childWidth.getValue()/2;

        //userNameLabel
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        //usertxt
        springLayout.putConstraint(SpringLayout.WEST,userTxt,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,20,SpringLayout.NORTH,centerPanel);

        //pwdLabel
        springLayout.putConstraint(SpringLayout.NORTH,passwordLabel,20,SpringLayout.SOUTH,userNameLabel);
        springLayout.putConstraint(SpringLayout.EAST,passwordLabel,0,SpringLayout.EAST,userNameLabel);

        //pwdtxt
        springLayout.putConstraint(SpringLayout.WEST,passwordTxt,20,SpringLayout.EAST,passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH,passwordTxt,0,SpringLayout.NORTH,passwordLabel);

        //loginbtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,80,SpringLayout.WEST,passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,passwordLabel);

        //resetBtn
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,20,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
    }//布局

    public static void main(String[] args) {
        new LoginView();
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public void setUserTxt(JTextField userTxt) {
        this.userTxt = userTxt;
    }

    public void setPasswordTxt(JPasswordField passwordTxt) {
        this.passwordTxt = passwordTxt;
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JPasswordField getPasswordTxt() {
        return passwordTxt;
    }
}
