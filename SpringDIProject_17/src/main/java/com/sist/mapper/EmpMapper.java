package com.sist.mapper;
import java.util.*;

import com.sist.dao.EmpVO;
public interface EmpMapper {
	
	//<select id="empAllData" resultMap="empMap">
	//id = method��
	//resultType,resultMap = return��
	//parameterType = �Ű�����
	public List<EmpVO> empAllData();
	public EmpVO empDetailData(int empno);
}
