package com.myschool.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author :huangjinyang
 * @Date :2018-5-2
 * @Time :10:31:41
 * @Description :
 */
public class User extends BasePo implements Serializable {

    /**
     * id
     */
	private Integer id;

    /**
     * name
     */
	private String name;

    /**
     * sex
     */
	private String sex;

    /**
     * age
     */
	private Integer age;
		
	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}
	public void setSex(String value) {
		this.sex = value;
	}
	public String getSex() {
		return this.sex;
	}
	public void setAge(Integer value) {
		this.age = value;
	}
	public Integer getAge() {
		return this.age;
	}
}