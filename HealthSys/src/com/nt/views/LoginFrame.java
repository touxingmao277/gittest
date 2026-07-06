package com.nt.views;

import com.nt.bean.UserInfo;
import com.nt.dao.UserDao;
import com.nt.utils.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame {
    UserDao userDao = new UserDao();

    public LoginFrame(){
        //创建 JFrame 实例
        JFrame frame = new JFrame("健康体检中心-登录页面");
        // 设置默认的关闭操作，这样当用户关闭窗口时程序会退出
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗口x、y轴坐标位置，设置窗口大小宽、高
        frame.setBounds(500, 200, 850, 540);
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
                //创建对话框
                JDialog jDialog = new JDialog(frame,"提示框");
                jDialog.setBounds(560,260,300,150);
                try {
                    String username = jTextField.getText();
                    String password = jPasswordField.getText();
                    UserInfo userInfo = userDao.login(username,password);
                    if(userInfo == null){
                        //设置提示信息
                        JLabel message = new JLabel("账号或密码输入有误！");
                        message.setLocation(90,0);
                        jDialog.add(message);
                        //模态对话框
                        jDialog.setModal(true);
                        jDialog.setVisible(true);
                    }else{
                        if(userInfo.getRole() == 0){
                            //关闭当前窗口
                            frame.dispose();
                            //打开主页窗口
                            new IndexFrame();
                        }else{
                            //设置提示信息
                            JLabel message = new JLabel("您无权登录！");
                            message.setLocation(90,0);
                            jDialog.add(message);
                            //模态对话框
                            jDialog.setModal(true);
                            jDialog.setVisible(true);
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
        new LoginFrame();
    }
}
