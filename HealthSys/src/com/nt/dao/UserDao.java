package com.nt.dao;

import com.nt.bean.UserInfo;
import com.nt.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {//数据访问层
    JDBCUtil jdbcUtil = new JDBCUtil();

    public UserInfo login(String username,String password) throws SQLException {
        String sql = "select * from userinfo where tel = ? and pwd = ?";
        Object[] params = {username,password};
        ResultSet rs = jdbcUtil.querySql(sql,params);
        UserInfo userInfo = null;
        if(rs.next()){
            userInfo = new UserInfo(rs.getString("tel"),rs.getString("pwd"),
                    rs.getString("uname"),rs.getString("sex"),
                    rs.getDate("birthday"),rs.getInt("role"));
        }
        jdbcUtil.closeCon();
        return userInfo;
    }

}
