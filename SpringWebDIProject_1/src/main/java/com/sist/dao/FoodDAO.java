package com.sist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.mapper.FoodMapper;

/*
 *   ������̼� : ������ 
 *   ========
 *   1. �޸� �Ҵ� ��û (������ ������̼�) => Ŭ������ ����
 *      @Component
 *          |
 *     -----------------------------------
 *     |           |        |            |   @ControllerAdvice @RestControllerAdvice
 *   @Repository @Service @Controller  @RestController
 *     => DAO    => BI      => Model     => Vue/React
 *   2. DI(����)
 *      @Autowired : �ڵ� ����
 *      @Inject
 *   3. AOP:������ : @Aspect => @Before , @After...
 */

@Repository
public class FoodDAO {
	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData (int start, int end)
	{
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
}
