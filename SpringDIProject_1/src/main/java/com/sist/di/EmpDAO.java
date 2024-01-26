package com.sist.di;
import java.util.*;
import java.sql.*;
public class EmpDAO {
	private CommensDAO comm;
	private Connection conn;
	private PreparedStatement ps;
    
	//스프링에서는 이렇게 클래스자체를 setDI로 넘길수가 잇음
	public void setComm(CommensDAO comm) {
		this.comm = comm;
	}
	public void init()
	{
		System.out.println("====사원정보====");
	}
	public List<EmpVO> empListData() 
	{
		List<EmpVO> list=new ArrayList<EmpVO>();
		try {
			conn=comm.getConnection();
			String sql="SELECT empno,ename,job,hiredate "
					+ "FROM emp ";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				list.add(vo);				
			}
			rs.close();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return list;
	}
	
}
