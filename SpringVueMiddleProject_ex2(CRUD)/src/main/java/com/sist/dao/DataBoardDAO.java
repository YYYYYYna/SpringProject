package com.sist.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
/*
 *    ������ �޸� �Ҵ� 
 *    ------------------------ ��ɺ��� �и�
 *    => @Component : AOP,MainClass,Open Api => �Ϲ� Ŭ���� 
 *    => @Repository : DAO (����� �����ͺ��̽� ����)
 *    => @Service : BI => DAO������ ���� (��� ����) => 
 *                  BoardDAO / ReplyDAO => ��ɺ��� Ŭ���� ����� (����)
 *    => @Controller : Model (��û ó�� => ����) => ������ ���� 
 *        => @ResponseBody
 *    => @RestController : Model (��û ó�� => ����) => ���� ó���� �����͸� ���� 
 *        ==== �ٸ� ���α׷��� ���� (�ڹٽ�ũ��Ʈ) => JSON
 *        Front / Back 
 *    => @ControllerAdvice : Controller���� �����߻��� ����ó�� (����)
 *    => @RestControllerAdvice : RestController���� �����߻��� ����ó�� (����)
 *    => @Configuration : XML�� ��� Ŭ���������� �ڹٷ� ���� 
 */
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class DataBoardDAO {
  @Autowired
  private DataBoardMapper mapper;
  
  public List<DataBoardVO> databoardListData(int start,int end)
  {
	  return mapper.databoardListData(start, end);
  }
  public int databoardTotalPage()
  {
	  return mapper.databoardTotalPage();
  }
  public void databoardInsert(DataBoardVO vo)
  {
	  mapper.databoardInsert(vo);
  }
  
  public DataBoardVO databoardDetailData(int no)
  {
	  mapper.hitIncrement(no);
	  return mapper.databoardDetailData(no);
  }
}