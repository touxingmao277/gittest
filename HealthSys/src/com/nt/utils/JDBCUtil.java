package com.nt.utils;

import java.sql.*;

public class JDBCUtil {//java链接数据库工具
    Connection con = null;
    ResultSet rs = null;
    //1、配置资源
    //配置jar包 -- 项目结构 -- libraries或modules--依赖

    //2、加载驱动
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();//ClassNotFound
        }
    }

    //3、建立链接，获取链接对象
    public Connection getConnection(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthsys?characterEncoding=UTF-8&serverTimezone=GMT%2b8","root","");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //4、执行查询sql
    public ResultSet querySql(String sql,Object[] params){
        try {
            con = getConnection();
            //预加载sql语句，利用连接对象，创建执行sql语句的接口
            PreparedStatement pst = con.prepareStatement(sql);
            //判断sql是否有参数
            if(params != null){
                //将sql所需参数注入到sql语句中
                for(int i = 0;i < params.length; i++){
                    //处理sql所需参数
                    pst.setObject(i+1,params[i]);
                }
            }
            //执行sql -- 查询结果集
            rs = pst.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    //4、执行新增、修改、删除sql
    public int updateSql(String sql,Object[] params){
        int count = 0;
        try {
            con = getConnection();
            //预加载sql语句，利用连接对象，创建执行sql语句的接口
            PreparedStatement pst = con.prepareStatement(sql);
            //判断sql是否有参数
            if(params != null){
                //将sql所需参数注入到sql语句中
                for(int i = 0;i < params.length; i++){
                    //处理sql所需参数
                    pst.setObject(i+1,params[i]);
                }
            }
            //执行sql -- 查询结果集
            count = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }


    //5、关闭链接流
    public void closeCon(){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
