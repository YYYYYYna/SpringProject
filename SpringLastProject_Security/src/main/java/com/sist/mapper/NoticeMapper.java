package com.sist.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface NoticeMapper {
	//footer���� 
	//�Խñ� �ǽð����� ���
	//hit�� desc�� �ȸ����� �ձ� ������ ���������� �ذ��ؾ���
	@Select("SELECT no,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,rownum "
		      + "FROM(SELECT no,subject,regdate "
		      + "FROM projectNotice ORDER BY hit DESC) WHERE rownum<=7")
		public List<NoticeVO> noticeTop7();
	
	//�ǽð� ���� ��ȸ ����
	@Select("SELECT fno,name,rownum "
		      + "FROM(SELECT fno,name "
		      + "FROM food_menu_house ORDER BY hit DESC) WHERE rownum<=7")
		public List<FoodVO> foodTop7();

	//�߰�,����,����,���
}
