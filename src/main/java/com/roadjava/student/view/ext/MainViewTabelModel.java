package com.roadjava.student.view.ext;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTabelModel extends DefaultTableModel {
    static Vector<String>columns = new Vector<String>();
    static {
        columns.addElement("编号");
        columns.addElement("姓名");
        columns.addElement("学号");
        columns.addElement("家乡");
        columns.addElement("语文");
        columns.addElement("数学");
        columns.addElement("英语");
        columns.addElement("总分");
    }
    private MainViewTabelModel(){//初始化列表的标题头
        super(null,columns);
    }

    public static MainViewTabelModel mainViewTabelModel = new MainViewTabelModel();
    public static MainViewTabelModel assembleModel (Vector<Vector<Object>> data){
        mainViewTabelModel.setDataVector(data,columns);//设置表格的数据
        return mainViewTabelModel;//返回table
    }

    public static void updateModel(Vector<Vector<Object>> data){//更新数据
            mainViewTabelModel.setDataVector(data,columns);
    }
    public static Vector<String> getColumns(){
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
       return false;
    }
}
