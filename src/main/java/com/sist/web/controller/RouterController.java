package com.sist.web.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;
/*
 * 	  1. Dockerfile
 * 			Spring
 *            |
 *           jar
 *           
 * 	  2. docker-compose.yml
 *    3. nginx.conf
 *    4. Jenkinsfile
 */
@Controller
@RequiredArgsConstructor
public class RouterController {
	private final FoodService fService;
	
	@GetMapping("/main")
	public String main_page(Model model) {
		model.addAttribute("main_html","main/home");
		return "main/main";
	}
	
	@RequestMapping("/food/list")
	public String food_list(
			@RequestParam(value = "search",required = false) String search,
			@RequestParam(value = "page",required = false) String page,
			Model model) {
		if(page==null) page="1";
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		map.put("search", search);
		map.put("start", (curpage*12)-12);
		List<FoodVO> list=fService.foodListData(map);
		int count=fService.foodListRowCount(search);
		int totalpage=(int)Math.ceil(count/12.0);
		final int BLOCK=10;
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endpage>totalpage) endpage=totalpage;
		model.addAttribute("list",list);
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startpage",startpage);
		model.addAttribute("endpage",endpage);
		model.addAttribute("count",count);
		model.addAttribute("main_html","food/list");
		return "main/main";
	}
}
