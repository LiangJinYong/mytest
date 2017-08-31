package com.inter.consumer.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inter.consumer.service.PhysicalDistributionListService;

@RestController
@RequestMapping("/consumer")
public class PhysicalDistributionListController {

	@Autowired
	private PhysicalDistributionListService physicalDistributionListService;

	public void setPhysicalDistributionListService(PhysicalDistributionListService physicalDistributionListService) {
		this.physicalDistributionListService = physicalDistributionListService;
	}
	
	@RequestMapping("/physicalDistributionList")
	public Map<String, Object> physicalDistributionList(HttpServletRequest request) {
		
		Map<String, Object> result = physicalDistributionListService.physicalDistributionList(request);
		
		return result;
	}
}
