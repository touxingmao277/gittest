package com.nt.dao;

import com.nt.bean.CheckItem;
import com.nt.bean.UserInfo;
import com.nt.utils.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckItemDao {
    JDBCUtil jdbcUtil = new JDBCUtil();

    public List<CheckItem> selectAllCheckItem() throws SQLException {
        String sql = "select * from checkitem";
        Object[] params = {};
        ResultSet rs = jdbcUtil.querySql(sql,params);
        List<CheckItem> list = new ArrayList<CheckItem>();
        while(rs.next()){
            CheckItem checkItem = new CheckItem(
                    rs.getString("cid"),rs.getString("codes"),
                    rs.getString("cname"),rs.getString("unit"),
                    rs.getString("ranges"));
            list.add(checkItem);
        }
        jdbcUtil.closeCon();
        return list;
    }

    public int deleteCheckItemByCid(String cid){
        String sql = "delete from checkitem where cid = ?";
        Object[] params = {cid};
        int count = jdbcUtil.updateSql(sql,params);
        jdbcUtil.closeCon();
        return count;
    }

    public int editCheckItem(String cid,String codes){
        String sql = "update checkitem set codes = ? where cid = ?";
        Object[] params = {codes,cid};
        int count = jdbcUtil.updateSql(sql,params);
        jdbcUtil.closeCon();
        return count;
    }

    public int addCheckItem(String codes){
        String sql = "insert into checkitem (cid,codes,cname,unit,ranges) value (?,?,?,?,?)";
        //生成全宇宙唯一的字符串
        String cid = UUID.randomUUID().toString();
        Object[] params = {cid,codes,"","",""};
        int count = jdbcUtil.updateSql(sql,params);
        jdbcUtil.closeCon();
        return count;
    }

}
