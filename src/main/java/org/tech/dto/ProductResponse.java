package org.tech.dto;

public class ProductResponse {

    private Integer pid;
    private String pname;
    private Double pprice;

    private Integer cid;
    private String cname;


    public ProductResponse() {
    }

    public ProductResponse(Integer pid, String pname, Double pprice, Integer cid, String cname) {
        this.pid = pid;
        this.pname = pname;
        this.pprice = pprice;
        this.cid = cid;
        this.cname = cname;
    }

    // Getters and Setters
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getPprice() {
        return pprice;
    }

    public void setPprice(Double pprice) {
        this.pprice = pprice;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
