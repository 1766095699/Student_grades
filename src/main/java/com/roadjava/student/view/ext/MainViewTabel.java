package com.roadjava.student.view.ext;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTabel extends JTable {
    public MainViewTabel(){
        //设置表头
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);
        //设置表格体
        setFont(new Font(null,Font.PLAIN,16));
        setForeground(Color.black);
        setGridColor(Color.black);
        setRowHeight(30);
        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
//渲染行列
    public void renderRule(){
        Vector<String>columns = MainViewTabelModel.getColumns();//这里的getColumns是自定义函数
        MainViewCellRender render = new MainViewCellRender();
        for(int i=0;i<columns.size();i++){
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(render);//设置这一列下来的每一格
            if(i==0){
                column.setPreferredWidth(50);
                column.setMaxWidth(50);
                column.setResizable(false);
            }
        }
    }

}
