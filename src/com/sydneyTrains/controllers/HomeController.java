package com.sydneyTrains.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sydneyTrains.service.HomeServiceImpl;

import net.sf.json.JSONObject;

@Controller
public class HomeController {
	
	@Autowired
	HomeServiceImpl homeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("title", "Home");
		return modelAndView;
	}

	
	
	@RequestMapping(value = "/loadYears", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getYearList() {
		String yearList = null;
		try {
			yearList = homeService.getYearList();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println(yearList);
		return yearList.toString();
	}
	
	
	
	@RequestMapping(value = "/yearlyData", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getefaulterData(@RequestParam String year) {
		
		System.out.println("Year in controller :: "+year);
		
		JSONObject obj = null;
		try {
			obj = homeService.getefaulterData(year);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return obj.toString();
	}
	
}
