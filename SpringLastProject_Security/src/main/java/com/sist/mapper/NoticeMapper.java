package com.sist.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface NoticeMapper {
	//footer에서 
	//게시글 실시간순위 출력
	//hit로 desc가 안먹힐수 잇기 때문에 서브쿼리로 해결해야함
	@Select("SELECT no,subject,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,rownum "
		      + "FROM(SELECT no,subject,regdate "
		      + "FROM projectNotice ORDER BY hit DESC) WHERE rownum<=7")
		public List<NoticeVO> noticeTop7();
	
	//실시간 가게 조회 순위
	@Select("SELECT fno,name,rownum "
		      + "FROM(SELECT fno,name "
		      + "FROM food_menu_house ORDER BY hit DESC) WHERE rownum<=7")
		public List<FoodVO> foodTop7();

	//추가,수정,삭제,목록
}
