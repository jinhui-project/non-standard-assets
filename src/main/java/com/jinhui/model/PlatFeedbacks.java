package com.jinhui.model;

public class PlatFeedbacks {
	private String token;
	
    private Integer id;

    private String content;

    private Integer platId;

    private String createAt;

    private String updateAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPlatId() {
        return platId;
    }

    public void setPlatId(Integer platId) {
        this.platId = platId;
    }

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(String updateAt) {
		this.updateAt = updateAt;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

   
}