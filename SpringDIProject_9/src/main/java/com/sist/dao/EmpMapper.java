package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.*;
public interface EmpMapper {
	
	/*
	 * 둘이 같은데 mapper가 사라지면서 이렇게 진행하게됨
	 * <resultMap>
	 *    <result property="dvo.dname" column="dname">
	 * </resultMap>
	 * 
	 * dvo.setDname(rs.getString("dname"))
	 */
	@Results({
		@Result(property="dvo.dname",column="dname"),
		@Result(property="dvo.loc",column="loc")
	})
	@Select("SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD') as dbday,sal,dname,loc "
			+ "FROM emp JOIN dept "
			+ "ON emp.deptno=dept.deptno ")
	public List<EmpVO> empDeptJoinData();

}
