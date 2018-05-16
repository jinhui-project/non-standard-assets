package com.jinhui.model;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * <p>Title:Menus</p>
 * <p>Description:菜单表VO类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 金汇金融有限公司</p>
 * @author 谷一帅- 75423426@qq.com
 * @version v1.0 2017-02-03
 */
public class Menus implements Serializable {
	
	/**类的版本号*/
	private static final long serialVersionUID = 2037409579583488L;

	private Long id;	/** 菜单关系 */	private  Long pid;	/** 菜单名称 */	private  String name;	/** 语言包 */	private  String language;	/** 图标 */	private  String icon;	/** 菜单对应的权限 */	private  String slug;	/** 菜单链接地址 */	private  String url;	/** 描述 */	private  String description;	/** 排序 */	private  Integer sort;	/** 状态 */	private  Integer status;	/**  */	private Date createdAt;	/**  */	private Date updatedAt;
	
	private String token;
	
	private List<Menus> node;	public  Long getId() {	    return this.id;	}	public void setId( Long id) {	    this.id=id;	}	public  Long getPid() {	    return this.pid;	}	public void setPid( Long pid) {	    this.pid=pid;	}	public  String getName() {	    return this.name;	}	public void setName( String name) {	    this.name=name;	}	public  String getLanguage() {	    return this.language;	}	public void setLanguage( String language) {	    this.language=language;	}	public  String getIcon() {	    return this.icon;	}	public void setIcon( String icon) {	    this.icon=icon;	}	public  String getSlug() {	    return this.slug;	}	public void setSlug( String slug) {	    this.slug=slug;	}	public  String getUrl() {	    return this.url;	}	public void setUrl( String url) {	    this.url=url;	}	public  String getDescription() {	    return this.description;	}	public void setDescription( String description) {	    this.description=description;	}	public  Integer getSort() {	    return this.sort;	}	public void setSort( Integer sort) {	    this.sort=sort;	}	public  Integer getStatus() {	    return this.status;	}	public void setStatus( Integer status) {	    this.status=status;	}	public java.util.Date getCreatedAt() {	    return this.createdAt;	}	public void setCreatedAt(java.util.Date createdAt) {	    this.createdAt=createdAt;	}	public java.util.Date getUpdatedAt() {	    return this.updatedAt;	}	public void setUpdatedAt(java.util.Date updatedAt) {	    this.updatedAt=updatedAt;	}
	public List<Menus> getNode() {
		return node;
	}
	public void setNode(List<Menus> node) {
		this.node = node;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
}