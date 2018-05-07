package com.myschool.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author :huangjinyang
 * @Date :2018-4-9
 * @Time :20:26:22
 * @Description :
 */
public class Note extends BasePo implements Serializable {

    /**
     * 记录Id
     */
	private Long id;

    /**
     * 用户ID
     */
	private Integer puid;

    /**
     * 记录类型
     */
	private String type;

    /**
     * 标题
     */
	private String title;

    /**
     * 内容主题
     */
	private String context;

    /**
     * 该记录指代的时间节点
     */
	private Date noteTime;
		
	public void setId(Long value) {
		this.id = value;
	}
	public Long getId() {
		return this.id;
	}
	public void setPuid(Integer value) {
		this.puid = value;
	}
	public Integer getPuid() {
		return this.puid;
	}
	public void setType(String value) {
		this.type = value;
	}
	public String getType() {
		return this.type;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	public String getTitle() {
		return this.title;
	}
	public void setContext(String value) {
		this.context = value;
	}
	public String getContext() {
		return this.context;
	}
	public void setNoteTime(Date value) {
		this.noteTime = value;
	}
	public Date getNoteTime() {
		return this.noteTime;
	}

}