package com.nt.views;

import com.nt.bean.CheckItem;
import com.nt.dao.CheckItemDao;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.sql.SQLException;
import java.util.Vector;

public class IndexFrame{
    CheckItemDao checkItemDao = new CheckItemDao();
    JTable jTable = null;
    CheckItem checkItem = null;
    //代码输入框
    JTextField dmTextField = null;
    //检查项输入框
    //单位输入框
    //参考范围输入框

    public IndexFrame(){
        //创建 JFrame 实例
        JFrame frame = new JFrame("健康体检中心-主页面");
        // 设置默认的关闭操作，这样当用户关闭窗口时程序会退出
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口x、y轴坐标位置，设置窗口大小宽、高
        frame.setBounds(500, 200, 600, 500);
        frame.getContentPane().setLayout(null);

        //创建面板
        JPanel jPanel = new JPanel();
        jPanel.setBounds(20, 30, 550, 420);
        jPanel.setLayout(null);
        //创建带标题的边框
        TitledBorder titledBorder = new TitledBorder(new TitledBorder("检查项管理"),"检查项管理",TitledBorder.LEFT,TitledBorder.TOP,null,new Color(255, 0, 0));
        //给面板设置边框
        jPanel.setBorder(titledBorder);

        //表格上方的按钮-- 新增
        JButton addButton = new JButton("新增");
        addButton.setBounds(20,0,60,30);
        frame.getContentPane().add(addButton);
        //新增按钮点击事件
        addButton.addActionListener(e->{
            openAddPanel();
        });

        //表格上方的按钮-- 新增、删除
        JButton jButton = new JButton("删除");
        jButton.setBounds(80,0,60,30);
        frame.getContentPane().add(jButton);
        //删除按钮点击事件
        jButton.addActionListener(e->{
            int flag = JOptionPane.showConfirmDialog(frame,"确认删除么？");
            if(flag == 0){
                if(checkItem == null){
                    JOptionPane.showMessageDialog(frame,"请选择删除行！","删除提示",2);
                }else{
                    String cid = checkItem.getCid();
                    int count = checkItemDao.deleteCheckItemByCid(cid);
                    if(count == 0){
                        JOptionPane.showMessageDialog(frame,"删除失败！","删除提示",0);
                    }else{
                        //刷新表格数据
                        pushCheckItemDatas();
                    }
                }
            }
        });

        //创建表格
        jTable = new JTable();
        jTable.setBounds(20,20,510,150);
        //可滚动的面板
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setBounds(20,20,510,150);

        //修改内容
        JLabel jLabel = new JLabel("代码:");
        jLabel.setFont(new Font("宋体",Font.BOLD,18));
        jLabel.setBounds(20,200,50,50);

        dmTextField = new JTextField();
        dmTextField.setBounds(90,210,100,30);

        JButton jButton1 = new JButton("修改");
        jButton1.setBounds(220,350,80,40);
        //修改按钮点击事件
        jButton1.addActionListener(e->{
            if(checkItem == null){
                JOptionPane.showMessageDialog(frame,"请选择编辑行！","编辑提示",2);
            }else{
                String cid = checkItem.getCid();
                String dm = dmTextField.getText();

                int count = checkItemDao.editCheckItem(cid,dm);
                if(count == 0){
                    JOptionPane.showMessageDialog(frame,"编辑失败！","编辑提示",0);
                }else{
                    //刷新表格数据
                    pushCheckItemDatas();
                }
            }
        });

        jPanel.add(jLabel);
        jPanel.add(dmTextField);
        jPanel.add(jButton1);

        //定义列头数组
        String[] columnNames = {"id","代码","检查项名称","单位","参考范围"};
        //所有数据
        Object[][] datas = {};

        //创建 表格中数据模型
        DefaultTableModel model = new DefaultTableModel(datas,columnNames);
        //将模型数据注入到表格中
        jTable.setModel(model);
        //将表格第一列宽度设置为0，目的是隐藏id
        DefaultTableColumnModel dcm = (DefaultTableColumnModel) jTable.getColumnModel();
        dcm.getColumn(0).setMaxWidth(0);
        dcm.getColumn(0).setMinWidth(0);

        //表格添加点击事件 -- 点击哪一行，获取哪一行数据
        jTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable.getSelectedRow() != -1) {
                //获取选中行 的 下标
                int index = jTable.getSelectedRow();
                //利用下标，从model模型中，获取数据
                Vector vector = (Vector) model.getDataVector().elementAt(index);
                checkItem = new CheckItem((String)vector.get(0),(String)vector.get(1),(String)vector.get(2),(String)vector.get(3),(String)vector.get(4));
                dmTextField.setText((String)vector.get(1));
            }
        });

        //向表格中的model，加入所有检查项数据
        pushCheckItemDatas();
        //将可滚动面板注入表格
        jScrollPane.setViewportView(jTable);
        //将边框注入到面板中
        jPanel.add(jScrollPane);

        frame.getContentPane().add(jPanel);

        frame.setVisible(true);
    }

    public void pushCheckItemDatas(){
        try {
            //获取表格中模板
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            //设置从第几行开始
            model.setRowCount(0);

            //从数据库中获取数据
            List<CheckItem> list = checkItemDao.selectAllCheckItem();
            //将数据库查询数据，添加到模板中
            for (CheckItem checkItem:list) {
                Vector rowData = new Vector();
                rowData.add(checkItem.getCid());
                rowData.add(checkItem.getCodes());
                rowData.add(checkItem.getCname());
                rowData.add(checkItem.getUnit());
                rowData.add(checkItem.getRanges());
                model.addRow(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void openAddPanel(){
        //创建 JFrame 实例
        JFrame frame = new JFrame("添加检查项");
        // 设置默认的关闭操作，这样当用户关闭窗口时程序会退出
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口x、y轴坐标位置，设置窗口大小宽、高
        frame.setBounds(500, 200, 400, 200);
        frame.getContentPane().setLayout(null);

        //新增内容
        JLabel jLabel = new JLabel("代码:");
        jLabel.setFont(new Font("宋体",Font.BOLD,18));
        jLabel.setBounds(20,20,50,50);
        frame.getContentPane().add(jLabel);

        JTextField dmTextField = new JTextField();
        dmTextField.setBounds(90,30,100,30);
        frame.getContentPane().add(dmTextField);

        JButton jButton1 = new JButton("确认");
        jButton1.setBounds(140,120,80,40);
        frame.getContentPane().add(jButton1);
        //确定按钮点击事件
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //获取代码输入的内容
                String codes = dmTextField.getText();
                //获取检查项名称、单位、参考范围
                if (codes.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "请填写完整信息！", "新增提示", 2);
                } else {
                    String dm = dmTextField.getText();
                    int count = checkItemDao.addCheckItem(dm);
                    if (count == 0) {
                        JOptionPane.showMessageDialog(frame, "新增失败！", "新增提示", 0);
                    } else {
                        //新增成功、关闭窗口
                        frame.dispose();
                        //刷新数据
                        pushCheckItemDatas();
                    }
                }
            }
        });
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new IndexFrame();
    }
}
