package com.roadjava.student.view;

import com.roadjava.handler.MainViewHandler;
import com.roadjava.req.StudentRequest;
import com.roadjava.res.TableDTO;
import com.roadjava.service.StudentService;
import com.roadjava.service.impl.StudentServiceImpl;
import com.roadjava.student.view.ext.MainViewTabel;
import com.roadjava.student.view.ext.MainViewTabelModel;
import util.DimensionUtil;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class MainView extends JFrame {
    JPanel  northPanel =new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("增加");
    JButton updateBtn = new JButton("修改");
    JButton delBtn = new JButton("删除");
    JTextField searchText = new JTextField(15);
    JButton searchBtn = new JButton("查询");

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");
    MainViewTabel mainViewTabel = new MainViewTabel();
    private int pagenow = 1;
    private  int pagesize = 10;
    MainViewHandler mainViewHandler;

    public MainView(){
        super("乐之者java学生成绩管理");
        Container contentPane = getContentPane();

        mainViewHandler = new MainViewHandler(this);

        LayoutNorth(contentPane);

        //设置中间的Jtable
        layoutCenter(contentPane);

        LayoutSouth(contentPane);

        URL imgurl = LoginView.class.getClassLoader().getResource("xiaoji.png");
        setIconImage(new ImageIcon(imgurl).getImage());
        //根据屏幕设置主界面大小
        setBounds(DimensionUtil.getBounds());
        //完全铺满屏幕
       setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }

    //设置表头等属性,然后自定义设置模式,最后把table放进JScrollPane并放入总panel
    private void layoutCenter(Container contentPane) {
        TableDTO tableDTO = getTableDTO();
        Vector<Vector<Object>> data = tableDTO.getData();
        MainViewTabelModel mainViewTabelModel = MainViewTabelModel.assembleModel(data);
        mainViewTabel.setModel(mainViewTabelModel);
        mainViewTabel.renderRule();
        JScrollPane jScrollPane = new JScrollPane(mainViewTabel);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(tableDTO.getTotalCount());
    }

    private TableDTO getTableDTO() {//当出现换页,查询等操作的时候都要重新刷新显示列表
        StudentService studentService = new StudentServiceImpl();
        StudentRequest request = new StudentRequest();
        request.setPagenow(pagenow);
        request.setPagesize(pagesize);
        request.setSearchKey(searchText.getText().toString().trim());
        return studentService.retriveStudents(request);
    }//当出现换页,查询操作的时候都要重新刷新显示列表
    //设置上一页和下一页按钮是否可见
    public void showPreNext(int totalcount){
        int sumpage=0;
        if(totalcount%pagesize==0){
            sumpage = totalcount/pagesize;
        }else{
            sumpage=totalcount/pagesize+1;
        }
        if(pagenow==sumpage){
            nextBtn.setVisible(false);
        }else{
            nextBtn.setVisible(true);
        }
        if(pagenow==1){
            preBtn.setVisible(false);
        }
        else {
            preBtn.setVisible(true);
        }
    }

    private void LayoutSouth(Container contentPane) {
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);

    }

    private void LayoutNorth(Container contentPane) {
        //添加监听事件
        addBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchBtn);
        northPanel.add(searchText);
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new MainView();
    }
    public void setpagenow(int pagenow){
        this.pagenow = pagenow;
    }
    public int getpagenow(){
        return pagenow;
    }

    public void reloadTable() {
        TableDTO tableDTO = getTableDTO();//tableDTO中存着刚从数据库中取出的数据
        Vector<Vector<Object>> data = tableDTO.getData();//获取数据
        //直接更新数据
        MainViewTabelModel.updateModel(data);//用新数据更新列表
        mainViewTabel.renderRule();//重新渲染每一格
        showPreNext(tableDTO.getTotalCount());//是否显示下一页的按钮
    }//重新加载列表

    public int[] getSelectedStudentIds(){//得到点击的行
        int []selectedRows = mainViewTabel.getSelectedRows();//得到可以当前有的行数
        int []ids = new int[selectedRows.length];
        for (int i = 0; i <selectedRows.length ; i++) {
            int rowIndex = selectedRows[i];//这是当前行的序列号不是id;
            Object idobj = mainViewTabel.getValueAt(rowIndex,0);
            ids[i] = Integer.valueOf(idobj.toString());
        }
        return ids;

    }
}
