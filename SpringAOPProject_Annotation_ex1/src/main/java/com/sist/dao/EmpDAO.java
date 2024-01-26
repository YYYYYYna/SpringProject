package com.sist.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDAO {
	private Connection conn;
	private PreparedStatement ps;
	private MyDataSource ds;
	
	@Autowired
	public EmpDAO(MyDataSource ds)
	{
		this.ds=ds;
		try {
			Class.forName(ds.getDriver());
		}catch(Exception ex) {}
	}
	
	//AOP로 Before 만드는 부분
	public void getConnection()
	{
		try {
			conn=DriverManager.getConnection(ds.getUrl(),ds.getUsername(),ds.getPassword());
		}catch(Exception ex) {}
	}
	
	//AOP로 After 만드는 부분
	public void disConnection()
	{
		try {
			if(ps!=null)ps.close();
			if(conn!=null)conn.close();
		}catch(Exception ex) {}
	}
	
	//전체목록
	public List<EmpVO> empListData()
	{
		List<EmpVO> list=new ArrayList<EmpVO>();
		try {
			// Around 시작부분
			String sql="SELECT empno, ename, job, TO_CHAR(hiredate, 'YYYY-MM-DD'), sal FROM emp";
		         ps=conn.prepareStatement(sql);
		         ResultSet rs=ps.executeQuery();
		         while(rs.next())
		         {
		            EmpVO vo=new EmpVO();
		            vo.setEmpno(rs.getInt(1));
		            vo.setEname(rs.getString(2));
		            vo.setJob(rs.getString(3));
		            vo.setDbday(rs.getString(4));
		            vo.setSal(rs.getInt(5));
		            list.add(vo);
		         }
		         rs.close();
		      // Around 종료 부분
		}catch(Exception ex) {
			// After-Throwing 부분
	         ex.printStackTrace();
		}
		finally {
			// after => disConnection
		}
		return list; // => After-Returning
	}
	// 상세보기
	   public EmpVO empDetailData(int empno)
	   {
	      EmpVO vo=new EmpVO();
	      try
	      {
	         String sql="SELECT empno,ename,job,TO_CHAR(hiredate, 'YYYY-MM-DD'),sal FROM emp WHERE empno="+empno;
	         ps=conn.prepareStatement(sql);
	         ResultSet rs=ps.executeQuery();
	         rs.next();
	         vo.setEmpno(rs.getInt(1));
	         vo.setEname(rs.getString(2));
	         vo.setJob(rs.getString(3));
	         vo.setDbday(rs.getString(4));
	         vo.setSal(rs.getInt(5));
	         rs.close();
	      }catch(Exception ex)
	      {
	         ex.printStackTrace();
	      }
	      finally
	      {
	         
	      }
	      return vo;
	   }
	}
