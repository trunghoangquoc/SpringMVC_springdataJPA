package com.laptrinhjavaweb.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/*khi khai báo 1 Entity mới thì phải vào persistence.xmlkhai báo Entity*/
@Entity
@Table(name = "user")//để biết đang mapping với table trong database
public class UserEntity extends BaseEntity {

	@Column(name = "username")
	private String userName;

	@Column(name = "password")
	private String password;

	@Column(name = "fullname")
	private String fullName;

	@Column
	private Integer status;
    
	//many to many relationship in JPA //tạo ra những khóa ngoại vào bảng user_role 
	//bỏ bên roleEmtity cũng dc
	//@ManyToMany(fetch = FetchType.LAZY) <=> @ManyToMany() 
	//:ko khai báo thì mặc định là Lazy(ưu tiên sử dụng LAZY)
	//@ManyToMany(fetch = FetchType.EAGER) khi lấy user lên thì sẽ get cái role lên 
	// -> khi lấy user lên thì sẽ get cái role lên . mà 1 user có nhiều role
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userid"), 
								  inverseJoinColumns = @JoinColumn(name = "roleid"))
	private List<RoleEntity> roles = new ArrayList<>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

}
