package com.jinhui.service.menus;

import java.util.List;

import com.jinhui.model.Menus;

public interface MenusService {

	void addMenus(Menus record)throws Exception;;

	void delIdMenus(Long id)throws Exception;;

	void updateNotNullMenus(Menus record)throws Exception;;

	Menus queryByIdMenus(Long id);

	List<Menus> queryByListMenus(Menus menus);
	
	List<Menus> queryByPidListMenus(Long id);

}
