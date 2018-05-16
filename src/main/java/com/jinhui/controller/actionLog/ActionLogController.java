package com.jinhui.controller.actionLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jinhui.service.actionLog.ActionLogService;

/**
 * 
 * @author jinhui
 *
 */
@Controller
@RequestMapping("/admin")
public class ActionLogController {
	@Autowired
	private ActionLogService actionLogService;
	
	@RequestMapping("/queryInfo")
	@ResponseBody
	public ModelAndView  queryInfo(){
		ModelMap m = null;

		return new ModelAndView("log",m);
		
	}

}
