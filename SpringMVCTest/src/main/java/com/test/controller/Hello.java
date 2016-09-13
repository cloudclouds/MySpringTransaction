package com.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/hello")
public class Hello {
	
  @RequestMapping(value = "/index", method = RequestMethod.GET)
   public ModelAndView hello()
   {
	  ModelAndView modelAndView=new ModelAndView();
	  modelAndView.setViewName("index");
	  modelAndView.addObject("hello", "helloworld");
	  return modelAndView;
   }
  
  @RequestMapping(value = "/index2", method = RequestMethod.GET)
  @ResponseBody
  public  List<Map<String, Object>> getConfig() 
  {
	  //List<Map<String, Object>>
	  List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
	  Map<String, Object> result = new TreeMap<String, Object>();
	  result.put("code", "A00000");
	  result.put("hello", "helloworld2");
	  resultList.add(result);
	  return resultList;
  }
}
