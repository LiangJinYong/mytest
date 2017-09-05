package com.app.consumer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.consumer.dao.VersionCheckDao;
import com.google.gson.Gson;

@Controller
@RequestMapping("/consumer")
public class VersionCheckController {
	
	@Autowired
	private VersionCheckDao versionCheckDao;

	@RequestMapping("/versionCheck")
	@ResponseBody
	public String versionCheck(HttpServletRequest request) {
		Map<String, String[]> paramrMap = request.getParameterMap();
		Gson gson = new Gson();
		List<String> list = new ArrayList<String>();
		list.add("uuu");
		list.add("iii");
		list.add("ppp");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oneInt", 123);
		map.put("twoString", "Ron");
		map.put("threeBoolean", true);
		map.put("fourDouble", 66.77);
		map.put("fiveList", list);
		String json = gson.toJson(map);
		
		List allList = versionCheckDao.findAll();
		
		String json2 = gson.toJson(allList);
		return json2;
	}
}
