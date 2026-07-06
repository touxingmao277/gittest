package com.nt.utils;

import com.nt.bean.UserInfo;
import com.nt.dao.UserDao;
import com.nt.views.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Test{
    UserDao userDao = new UserDao();

    public Test(){
        //创建 JFrame 实例
        JFrame frame = new JFrame("健康体检中心-登录页面");
        // 设置默认的关闭操作，这样当用户关闭窗口时程序会退出
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口x、y轴坐标位置，设置窗口大小宽、高
        frame.setBounds(600, 250, 850, 540);
        //清空窗口默认布局方式
        frame.getContentPane().setLayout(null);

        //图片logo
        JLabel jlabel = new JLabel(new ImageIcon(Test.class.getResource("/images/loginBck.png")));
        jlabel.setBounds(0,0,850,540);
        jlabel.setOpaque(false); // 设置背景标签为透明。如果使用面板，同样设置setOpaque(false)。

        //账号标签
        JLabel userNameLabel = new JLabel();
        userNameLabel.setText("账号:");
        userNameLabel.setFont(new Font("宋体",Font.BOLD,24));
        userNameLabel.setBounds(180,90,100,100);
        frame.getContentPane().add(userNameLabel);
        //账号输入框
        JTextField jTextField = new JTextField();
        jTextField.setBounds(280,120,480,40);
        frame.getContentPane().add(jTextField);

        //密码标签
        JLabel passwordLabel = new JLabel();
        passwordLabel.setText("密码:");
        passwordLabel.setFont(new Font("宋体",Font.BOLD,24));
        passwordLabel.setBounds(180,180,100,100);
        frame.getContentPane().add(passwordLabel);

        //密码输入框
        JPasswordField jPasswordField = new JPasswordField();
        jPasswordField.setBounds(280,210,480,40);
        frame.getContentPane().add(jPasswordField);

        //登录按钮
        JButton jButton = new JButton("登录");
        jButton.setBounds(320,300,260,50);
        frame.getContentPane().add(jButton);

        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String username = jTextField.getText();
                    String password = jPasswordField.getText();
                    UserInfo userInfo = userDao.login(username,password);
                    if(userInfo == null){
                        System.out.println("账号或密码输入有误！");
                    }else{
                        if(userInfo.getRole() == 0){
                            System.out.println("登录成功");
                        }else{
                            JLabel label = new JLabel();
                            JDialog dialog = new JDialog(frame, "Dialog");
                            dialog.setSize(220, 150);                    // 设置对话框大小
                            dialog.setLocation(650, 300);                        // 设置对话框位置
                            dialog.setLayout(new FlowLayout());                       // 设置布局管理器
                            // 设置对话框为模态
                            dialog.setModal(true);
                            dialog.add(label);
                            // 否则修改标签的内容
                            label.setText("您无权登录！");
                            // 显示对话框
                            dialog.setVisible(true);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });


        frame.getContentPane().add(jlabel);
        // 显示窗口
        frame.setVisible(true);
        frame.setResizable(true);
    }

    public static void main(String[] args) {
        new Test();
    }
}
