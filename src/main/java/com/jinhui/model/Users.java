package com.jinhui.model;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
/**
 * <p>Title:Users</p>
 * <p>Description:用户表VO类</p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2016-12-12
 */
public class Users extends QueryEntity  implements Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 1962456059463680L;
	

	/** 主键d */
	private  Integer id;
	/** 机构id */
	private  Integer gid;
	/** 帐号 */
	private  String username;
	/** 密码 */
	private  String password;
	/** 机构名称 */
	private  String gname;
	/** 权限串  默认000，0是没有权限，1是有权限，第一位0资产方，第二位0资金方，第三位0平台方*/
	private  String power;
	/** 姓名 */
	private  String name;
	/** 性别代码 */
	private  Integer gender;
	/** 手机号码 */
	private  String mobile;
	/** 电话 */
	private  String tel;
	/** 邮箱 */
	private  String email;
	/** 身份证号 */
	private  String idNo;
	/** 出生日期 */
	private  Long birthday;
	/** 部门 */
	private  String department;
	/** 职务 */
	private  String position;
	/** 名片路径 */
	private  String cardPath;
	/** 联系地址 */
	private  String address;
	/** 创建人id */
	private  Long creatorId;
	/** 创建时间 */
	private  Long createTime;
	/** 修改人id */
	private  Long editorId;
	/** 修改时间 */
	private  Long editorTime;
	/** 审核人id */
	private  Long auditorId;
	/** 审核时间 */
	private  Long auditTime;
	/** 审核状态0未激活1激活2注销 */
	private  Integer auditStatus;
	
	
	/** 第一次密码 */
	private String pwd;
	/**
	 * 图形验证码，
	 */
	private String code;
	/**
	 * 手机验证码，
	 */
	private String phoneCode;
	
	private String token;
	

	/** 创建时间 */
	private  String createTimeStr;

	/** 修改时间 */
	private  String editorTimeStr;

	private List<Fileinfos> fileinfos;
	
	private Organization organization;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGid() {
		return gid;
	}

	public void setGid(Integer gid) {
		this.gid = gid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCardPath() {
		return cardPath;
	}

	public void setCardPath(String cardPath) {
		this.cardPath = cardPath;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getEditorId() {
		return editorId;
	}

	public void setEditorId(Long editorId) {
		this.editorId = editorId;
	}

	public Long getEditorTime() {
		return editorTime;
	}

	public void setEditorTime(Long editorTime) {
		this.editorTime = editorTime;
	}

	public Long getAuditorId() {
		return auditorId;
	}

	public void setAuditorId(Long auditorId) {
		this.auditorId = auditorId;
	}

	public Long getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Long auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(String phoneCode) {
		this.phoneCode = phoneCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getEditorTimeStr() {
		return editorTimeStr;
	}

	public void setEditorTimeStr(String editorTimeStr) {
		this.editorTimeStr = editorTimeStr;
	}

	public List<Fileinfos> getFileinfos() {
		return fileinfos;
	}

	public void setFileinfos(List<Fileinfos> fileinfos) {
		this.fileinfos = fileinfos;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", gid=" + gid +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", gname='" + gname + '\'' +
				", power='" + power + '\'' +
				", name='" + name + '\'' +
				", gender=" + gender +
				", mobile='" + mobile + '\'' +
				", tel='" + tel + '\'' +
				", email='" + email + '\'' +
				", idNo='" + idNo + '\'' +
				", birthday=" + birthday +
				", department='" + department + '\'' +
				", position='" + position + '\'' +
				", cardPath='" + cardPath + '\'' +
				", address='" + address + '\'' +
				", creatorId=" + creatorId +
				", createTime=" + createTime +
				", editorId=" + editorId +
				", editorTime=" + editorTime +
				", auditorId=" + auditorId +
				", auditTime=" + auditTime +
				", auditStatus=" + auditStatus +
				", pwd='" + pwd + '\'' +
				", code='" + code + '\'' +
				", phoneCode='" + phoneCode + '\'' +
				", token='" + token + '\'' +
				", createTimeStr='" + createTimeStr + '\'' +
				", editorTimeStr='" + editorTimeStr + '\'' +
				", fileinfos=" + fileinfos +
				", organization=" + organization +
				'}';
	}
}