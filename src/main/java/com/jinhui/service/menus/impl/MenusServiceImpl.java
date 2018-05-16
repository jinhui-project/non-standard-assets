package com.jinhui.service.menus.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinhui.mapper.menus.MenusMapper;
import com.jinhui.model.Menus;
import com.jinhui.service.menus.MenusService;
@Service
public class MenusServiceImpl implements MenusService {

	Logger logger = LoggerFactory.getLogger(MenusServiceImpl.class);

	@Autowired
	private MenusMapper menusMapper;

	/**
	 * 添加
	 */
	public void addMenus(Menus record) throws Exception {
		menusMapper.addMenus(record);

	}

	/**
	 * 删除
	 */
	public void delIdMenus(Long id) throws Exception {
		menusMapper.delIdMenus(id);

	}

	/**
	 * 非空字段修改
	 */
	public void updateNotNullMenus(Menus record) throws Exception {
		menusMapper.updateNotNullMenus(record);

	}

	/**
	 * 查询实体
	 */
	public Menus queryByIdMenus(Long id) {
		return menusMapper.queryByIdMenus(id);
	}

	/**
	 * 查询集合
	 */
	public List<Menus> queryByListMenus(Menus menus) {
		return menusMapper.queryByListMenus(menus);
	}

	@Override
	public List<Menus> queryByPidListMenus(Long id) {
		return menusMapper.queryByPidListMenus(id);
	}

}
