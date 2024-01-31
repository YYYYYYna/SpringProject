package com.sist.dao;
import java.util.*;


import com.sist.vo.MoimVO;

import java.sql.*;

public class MoimDAO {
	
	//오라클 연결 아마...
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@211.238.142.102:1521:XE";
	public MoimDAO() {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception ex) {}
	}
	
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
			// 오라클로 전송하는 명령어 => conn hr/happy
		}catch(Exception ex) {}
	}
	// 오라클 닫기
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			// OuputStream / BufferedReader
			if(conn!=null) conn.close();
			// Socket
		}catch(Exception ex) {}
	}
	
	//여기서부터 데이터 넣기... 아마...
	//sql부터 만들어야함!!!
	final String sql_insert="INSERT INTO moimList(rno,img,loc,type,num,center, "
			+ "time,addr1,call,cost,food,method,page,test1,test2,content, "
			+ "beam,pc,printer,mic) "
			+ " VALUES(ml_rno_seq.NEXTVAL, "
			+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	final String sql_selectAll="SELECT * FROM moimList";
	public boolean insert(MoimVO vo) {
		getConnection();
		try {
			ps=conn.prepareStatement(sql_insert);
			ps.setString(1,vo.getImg());
			ps.setString(2,vo.getLoc());
			ps.setString(3, vo.getType());
			ps.setString(4,vo.getNum());
			ps.setString(5,vo.getCenter());
			ps.setString(6,vo.getTime());
			ps.setString(7,vo.getAddr1());
			ps.setString(8,vo.getCall());
			ps.setString(9,vo.getCost());
			ps.setString(10,vo.getFood());
			ps.setString(11,vo.getMethod());
			ps.setString(12,vo.getPage());
			ps.setString(13,vo.getTest1());
			ps.setString(14,vo.getTest2());
			ps.setString(15,vo.getContent());
			ps.setString(16,vo.getBeam());
			ps.setString(17,vo.getPc());
			ps.setString(18,vo.getPrinter());
			ps.setString(19,vo.getMic());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			disConnection();
		}
		return true;
	}
	
	public ArrayList<MoimVO> selectAll(MoimVO vo) {
		ArrayList<MoimVO> datas=new ArrayList<MoimVO>();
		getConnection();
		try {
			ps=conn.prepareStatement(sql_selectAll);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				MoimVO data=new MoimVO();
				data.setRno(rs.getInt("rno"));
				data.setImg(rs.getString("img"));
				data.setLoc(rs.getString("loc"));
				data.setType(rs.getString("type"));
				data.setNum(rs.getString("num"));
				data.setCenter(rs.getString("center"));
				data.setTime(rs.getString("time"));
				data.setAddr1(rs.getString("addr1"));
				data.setCall(rs.getString("call"));
				data.setCost(rs.getString("cost"));
				data.setFood(rs.getString("food"));
				data.setMethod(rs.getString("method"));
				data.setPage(rs.getString("page"));
				data.setTest1(rs.getString("test1"));
				data.setTest2(rs.getString("test2"));
				data.setContent(rs.getString("content"));
				data.setBeam(rs.getString("beam"));
				data.setPc(rs.getString("pc"));
				data.setPrinter(rs.getString("printer"));
				data.setMic(rs.getString("mic"));
				datas.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			disConnection();
		}
		return datas;
	}
}
