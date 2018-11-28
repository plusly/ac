package com.atguigu.ac.sc.entity;

public class MemberCert {
    private Integer id;

    private String accttype;

    private Integer certid;
    
	public MemberCert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberCert(Integer id, String accttype, Integer certid) {
		super();
		this.id = id;
		this.accttype = accttype;
		this.certid = certid;
	}

    @Override
	public String toString() {
		return "MemberCert [id=" + id + ", accttype=" + accttype + ", certid=" + certid + "]";
	}
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype == null ? null : accttype.trim();
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }
}