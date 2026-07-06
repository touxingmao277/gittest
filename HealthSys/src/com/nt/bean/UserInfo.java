package com.nt.bean;

import java.sql.Date;

public class UserInfo {//与数据库列名保持一致
    private String tel;
    private String pwd;
    private String uname;
    private String sex;
    /*
    * java.util.Date  年月日 时分秒
    * java.sql.Date   年月日
    * */
    private Date birthday;
    private int role;

    public UserInfo() {
    }

    public UserInfo(String tel, String pwd, String uname, String sex, Date birthday, int role) {
        this.tel = tel;
        this.pwd = pwd;
        this.uname = uname;
        this.sex = sex;
        this.birthday = birthday;
        this.role = role;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
