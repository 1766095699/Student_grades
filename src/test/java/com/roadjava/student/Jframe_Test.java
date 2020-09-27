package com.roadjava.student;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Jframe_Test {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("学生成绩管理系统");
        //设置icon
        URL res = Jframe_Test.class.getClassLoader().getResource("xiaoji.png");
        Image image = new ImageIcon(res).getImage();
        jFrame.setIconImage(image);
        //设置窗体大小
        jFrame.setSize(600,400);
        //不可拉伸
        jFrame.setResizable(false);
        //居中
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}