package com.sist.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.FoodMapper;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodService {
	private final FoodMapper fMapper;
	
	public List<FoodVO> foodListData(Map map){
		List<FoodVO> list=fMapper.foodListData(map);
		for(FoodVO vo:list) {
			String[] temp=vo.getTheme().split(",");
			vo.setTheme(temp[0]+","+temp[1]);
		}
		return list;
	}
	public int foodListRowCount(String search) {
		return fMapper.foodListRowCount(search);
	}
}
