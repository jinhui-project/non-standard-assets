package com.jinhui.mapper.menus;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jinhui.model.Menus;

/**
 * 菜单
 * 
 * @author jinhui
 *
 */
public interface MenusMapper {

	void addMenus(@Param("record") Menus record);

	void delIdMenus(@Param("id") Long id);

	void updateNotNullMenus(@Param("record") Menus record);

	Menus queryByIdMenus(@Param("id") Long id);

	List<Menus> queryByListMenus(Menus menus);
	
	List<Menus> queryByPidListMenus(@Param("id") Long id);

}
