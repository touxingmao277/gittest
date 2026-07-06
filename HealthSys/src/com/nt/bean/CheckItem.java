package com.nt.bean;

public class CheckItem {
    private String cid;
    private String codes;
    private String cname;
    private String unit;
    private String ranges;

    public CheckItem() {
    }

    public CheckItem(String cid, String codes, String cname, String unit, String ranges) {
        this.cid = cid;
        this.codes = codes;
        this.cname = cname;
        this.unit = unit;
        this.ranges = ranges;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRanges() {
        return ranges;
    }

    public void setRanges(String ranges) {
        this.ranges = ranges;
    }
}
