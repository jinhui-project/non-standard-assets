package com.jinhui.model;

public class RecordLog {
	private String token;
	
    private Integer id;

    private Integer aid;

    private Integer stepId;

    private String fileStr;

    private Integer createAt;

    private Integer updateAt;
    
    private Integer recordGid;

    private Integer wfType;
    
    private Integer wfStatus;
    
    private String materials;
    
    private String dueReports;
    
    private Integer passResult;
    
    private String passContent;
    
    
    /**
     * 是否更新备案状态
     */
    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getFileStr() {
        return fileStr;
    }

    public void setFileStr(String fileStr) {
        this.fileStr = fileStr == null ? null : fileStr.trim();
    }

    public Integer getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Integer createAt) {
        this.createAt = createAt;
    }

    public Integer getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Integer updateAt) {
        this.updateAt = updateAt;
    }

	public Integer getRecordGid() {
		return recordGid;
	}

	public void setRecordGid(Integer recordGid) {
		this.recordGid = recordGid;
	}

	public Integer getWfType() {
		return wfType;
	}

	public void setWfType(Integer wfType) {
		this.wfType = wfType;
	}

	public Integer getWfStatus() {
		return wfStatus;
	}

	public void setWfStatus(Integer wfStatus) {
		this.wfStatus = wfStatus;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMaterials() {
		return materials;
	}

	public void setMaterials(String materials) {
		this.materials = materials;
	}

	public String getDueReports() {
		return dueReports;
	}

	public void setDueReports(String dueReports) {
		this.dueReports = dueReports;
	}

	public Integer getPassResult() {
		return passResult;
	}

	public void setPassResult(Integer passResult) {
		this.passResult = passResult;
	}

	public String getPassContent() {
		return passContent;
	}

	public void setPassContent(String passContent) {
		this.passContent = passContent;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
	
	
    
}