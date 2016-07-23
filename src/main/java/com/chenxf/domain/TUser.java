package com.chenxf.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="t_user")
public class TUser {
	
	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="user_name")
	@Length(min=4,max=10)
	private String userName;
	
	@Column (name="passwd")
	@Pattern(regexp="\\w{6,18}")
	private String passwd;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String toString(){
		return userName;
	}
}
