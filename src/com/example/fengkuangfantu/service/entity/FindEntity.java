package com.example.fengkuangfantu.service.entity;

public class FindEntity extends BaseEntity {
	
	private int id = 0;
	private String name="";
	private String cover="";
	private String defaultCover="";
	private boolean isImageShow = false;
	
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
	
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public String getDefaultCover() {
		return defaultCover;
	}
	public void setDefaultCover(String defaultCover) {
		this.defaultCover = defaultCover;
	}
	
	public boolean getIsImageShow() {
		return isImageShow;
	}
	public void setIsImageShow(boolean isImageShow) {
		this.isImageShow = isImageShow;
	}

}
