package com.sist.di;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDAO {
	private CommensDAO comm;
	private Connection conn;
	private PreparedStatement ps;
	
	//������������ �̷��� Ŭ������ü�� setDI�� �ѱ���� ����
	public void setComm(CommensDAO comm) {
		this.comm = comm;
	}
	public void init()
	{
		System.out.println("====�μ�����====");
	}
	public List<DeptVO> deptListData()
	{
		List<DeptVO> list=new ArrayList<DeptVO>();
		try {
			conn=comm.getConnection();
			String sql="SELECT deptno, dname, loc "
					+ "FROM dept ";
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				DeptVO vo=new DeptVO();
				vo.setDeptno(rs.getInt(1));
				vo.setDname(rs.getString(2));
				vo.setLoc(rs.getString(3));
				list.add(vo);
			}
		}catch(Exception ex)
		{
			
		}finally {
			comm.disConnection(conn, ps);
		}
		return list;
	}
}
