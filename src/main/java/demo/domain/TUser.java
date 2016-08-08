package demo.domain;

import java.io.Serializable;
import java.util.Date;

public class TUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5825369915084576597L;
	private int id;
	private String name;
	private int age;
	private Date createTime;
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
