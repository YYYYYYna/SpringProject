package com.sist.dao;
import java.util.*;
import org.mybatis.spring.support.SqlSessionDaoSupport;

public class EmpDAO extends SqlSessionDaoSupport{
	public List<EmpVO> empListData()
	{
		return getSqlSession().selectList("empListData");
		//getSqlSession() 세션을 자동으로 열고닫아주고 commit도 자동으로 해줌
	}
	
	public EmpVO empDetailData(int empno)
	{
		return getSqlSession().selectOne("empDetailData",empno);
	}
	
}
