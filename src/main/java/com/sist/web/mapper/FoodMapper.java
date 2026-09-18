package com.sist.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.FoodVO;

@Mapper
@Repository
public interface FoodMapper {
/*
	<select id="foodListData" resultType="com.sist.web.vo.FoodVO" parameterType="hashmap">
		SELECT no,poster,name,score,type,theme
		FROM food
		<choose>
			<when test="search!=null and search!=''">
				WHERE address LIKE CONCAT('%',#{search},'%')
			</when>
		</choose>
		ORDER BY no ASC
		OFFSET #{start} ROWS FETCH NEXT 12 ROWS ONLY
	</select>
	<!-- trim / if / choose~when 
		 where / foreach
	-->
	*/
	public List<FoodVO> foodListData(Map map);
	/*
	<select id="foodListTotalPage" resultType="int" parameterType="string">
		SELECT CEIL(COUNT(*)/12.0)
		FROM food
		<choose>
			<when test="search!=null and search!=''">
				WHERE address LIKE CONCAT('%',#{search},'%')
			</when>
		</choose>		
	</select>
 */
	public int foodListRowCount(String search);
}
