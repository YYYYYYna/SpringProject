package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.*;
public interface EmpMapper {
	
	/*
	 * ���� ������ mapper�� ������鼭 �̷��� �����ϰԵ�
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
