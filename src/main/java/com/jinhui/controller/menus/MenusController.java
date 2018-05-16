package com.jinhui.controller.menus;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jinhui.model.BaseObject;
import com.jinhui.model.Menus;
import com.jinhui.service.menus.MenusService;

/**
 * 菜单Controller
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class MenusController {
	
	Logger logger = LoggerFactory.getLogger(MenusController.class);
	@Autowired
	private MenusService menusService;
	
	
	/**
	 * 左边菜单栏加载
	 * @return
	 */
	@RequestMapping(value="/initMenus",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public String initMenus(){
		Menus menus = new Menus();
		List<Menus> listObj = new ArrayList<Menus>();
		menus.setPid((long) 0);
		List<Menus> list =  menusService.queryByListMenus(menus);
		for(Menus m : list){
			List<Menus> listNode =  menusService.queryByPidListMenus(m.getId());
			m.setNode(listNode);
			listObj.add(m);
		}
		
		return JSON.toJSONString(listObj);
		
	}
	
	/**
	 * 菜单保存
	 * @return
	 */
	@RequestMapping(value="/saveMenus",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public BaseObject saveMenus(Menus menus){
		BaseObject obj = new BaseObject();
		if( null == menus.getId()){//添加
			try {
				menusService.addMenus(menus);
			} catch (Exception e) {
				logger.error("【菜单保存】addMenus异常："+e);
				obj.setCode(1);
				obj.setMessage("菜单保存失败");
				return obj;
			}
		}else{//修改
			try {
				menusService.updateNotNullMenus(menus);
			} catch (Exception e) {
				logger.error("【菜单保存】updateNotNullMenus异常："+e);
				obj.setCode(1);
				obj.setMessage("菜单保存失败");
				return obj;
			}
		}
		obj.setCode(0);
		obj.setMessage("菜单保存成功");
		return obj;
	}
	
	/**
	 * 删除菜单
	 * @param menus
	 * @return
	 */
	@RequestMapping(value="/delMenus",produces={"text/html;charset=UTF-8;","application/json;charset=UTF-8;"})
	@ResponseBody
	public BaseObject delMenus(Menus menus){
		BaseObject obj = new BaseObject();
		try {
			menusService.delIdMenus(menus.getId());
		} catch (Exception e) {
			logger.error("【菜单删除】delIdMenus异常："+e);
			obj.setCode(1);
			obj.setMessage("菜单删除失败");
			return obj;
		}
		obj.setCode(0);
		obj.setMessage("菜单删除成功");
		return obj;
	}

}
