package com.nt.utils;

import com.nt.bean.UserInfo;
import com.nt.dao.UserDao;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class Demo {
    JTable table = null;
    UserDao userDao = new UserDao();
    DefaultTableModel model = null;

    public Demo(){
        JFrame jf=new JFrame("检查项管理");
        jf.setBounds(550, 200, 600, 429);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        //Border border,
        //                        String title,
        //                        int titleJustification,
        //                        int titlePosition,
        //                        Font titleFont,
        //                        Color titleColor
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "检查项管理",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.setBounds(20, 30, 550, 300);
        jf.getContentPane().add(panel);

        //表头栏数据
        String[] title={"账号","密码","姓名","出生日期"};
        /*具体的各栏行记录 先用空的二位数组占位*/
        String[][] datas={};
        /*实例化控件对象title表头、datas数据*/
        model = new DefaultTableModel(datas,title);
        //将数据放入table表格中
        table = new JTable(model);

//        try {
//            putDatas();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //定义可滚动面板
        JScrollPane jscrollpane = new JScrollPane();
        jscrollpane.setBounds(20, 22, 510, 260);
        //在滚动面板中添加表格
        jscrollpane.setViewportView(table);

        panel.add(jscrollpane);
        jf.getContentPane().add(panel);

        jf.setVisible(true);
        jf.setResizable(true);
    }

    private void putDatas() throws SQLException {
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        model.setRowCount(0);
//        List<UserInfo> list = userDao.selectAllCheckItem();
//        for (UserInfo userInfo : list){
//            Vector rowData = new Vector();
//            rowData.add(userInfo.getTel());
//            rowData.add(userInfo.getPwd());
//            rowData.add(userInfo.getUname());
//            rowData.add(userInfo.getBirthday());
//            model.addRow(rowData);
//        }
    }

    public static void main(String[] args) {
        new Demo();
    }

}
