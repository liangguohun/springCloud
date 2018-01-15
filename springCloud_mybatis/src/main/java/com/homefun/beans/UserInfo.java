package com.homefun.beans;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
	private Integer id;
    private String userName;
    private String xiaoMing;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getXiaoMing() {
		return xiaoMing;
	}
	public void setXiaoMing(String xiaoMing) {
		this.xiaoMing = xiaoMing;
	}
	
}
