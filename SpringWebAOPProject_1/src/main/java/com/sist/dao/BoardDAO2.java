package com.sist.dao;
import java.util.*;
import java.sql.*;
public class BoardDAO2 {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:@localhost:1521:XE";
	
	public BoardDAO2()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception ex) {}
	}
	
	public void disConnection()
	{
		try {
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}catch(Exception ex) {}
	}
	//위의 모든 과정을 SqlSessionFactoryBean이 해줌
	
	/*
	 * [트랜젝션 프로그램의 주요 내용]
	 * 입고 commit 했는데 재고가 error가 나면 데이터가
	 * 입고와 재고량이 일지하지 않음.. 그럼 큰 문제가 생김
	 * 이부분을 방지하기 위해 입고/재고가 둘다 성공시 commit하고
	 * 하나라도 실패하면 rollback하도록 코드를 짜는거임
	 */
	
	//답변하기
	public void boardReplyInsert(int pno,BoardVO vo)
	                             //pno는 답변대상
	{
		try {
			//연결
			getConnection();
			conn.setAutoCommit(false); //@Around로 실행가능 : 핵심코드위아래니까
			String sql="SELECT group_id, group_step, group_tab FROM springReplyBoard "
					+ "WHERE no="+pno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.close();
			
			int gi=rs.getInt(1);
			int gs=rs.getInt(2);
			int gt=rs.getInt(3);
			rs.close();
			ps.close();
			//셋중 하나가 오류나면 rollback이 되도록 설정
			//Update
			 sql="UPDATE springReplyBoard SET "
			            +"group_step=group_step+1 "
			            +"WHERE group_id=? AND group_step>?";
			 ps=conn.prepareStatement(sql);
			 ps.setInt(1, gi);
			 ps.setInt(2, gs);
			 ps.executeUpdate();
			 ps.close();
			
			 //Insert
			 sql="INSERT INTO springReplyBoard VALUES( "
			 		+ "sr_no_seq.nextval,?,?,?,?,SYSDATE,0, "
			 		+ "?,?,?,?,0)";
			 ps=conn.prepareStatement(sql);
			 ps.setString(1, vo.getName());
			 ps.setString(2, vo.getSubject());
			 ps.setString(3, vo.getContent());
			 ps.setString(4, vo.getPwd());
			 ps.setInt(5, gi);
			 ps.setInt(6, gs+1);
			 ps.setInt(7, gt+1);
			 ps.setInt(8, pno);
			 ps.executeUpdate();
			 ps.close();
			 
			 //Update
			 sql="UPDATE springReplyBoard SET "
			 		+ "depth=depth+1 "
			 		+ "WHERE no="+pno;
			 ps=conn.prepareStatement(sql);
			 ps.executeUpdate();
			 ps.close();
			
			conn.commit();
		}catch(Exception ex) {
			try {
				conn.rollback(); //@AfterThrowing으로 실행가능
				ex.printStackTrace();
			}catch(Exception e) {}
		}finally {
			try {
				conn.setAutoCommit(true); //@After로 샐행가능 conn이 전역변수이기 때문에 꼭 true로 바꿔줘야함
			}catch(Exception e) {}
		}
	}
	
	//삭제하기
}
