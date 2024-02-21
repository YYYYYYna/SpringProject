package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface RecipeMapper {
	    //�ǽð� ������ ��ȸ ���� (main)
		@Select("SELECT no,title,poster,rownum "
			  + "FROM(SELECT no,title,poster "
			  + "FROM recipe ORDER BY hit DESC) WHERE rownum<=12")
		public List<RecipeVO> recipeHome12();
		
		//�ǽð� ���� ��ȸ ���� (main)
		@Select("SELECT cno,chef,poster,rownum "
					  + "FROM(SELECT cno,chef,poster "
					  + "FROM chef ORDER BY mem_cont7 DESC) WHERE rownum<=12")
		public List<ChefVO> chefHome12();
				
	    //������ ������
		@Select("SELECT COUNT(*) FROM recipe "
				+ "WHERE NO IN(SELECT NO FROM recipe "
				+ "intersect SELECT NO FROM RECIPEDETAIL) ")
		public int recipeCount();
		
		@Select("SELECT no,title,poster,num "
				+ "FROM (SELECT no,title,poster,rownum as num "
				+ "FROM (SELECT /*+ INDEX_ASC(recipe recipe_no_pk) */no,title,poster "
				+ "FROM recipe WHERE no IN (SELECT no FROM recipe "
				+ "intersect SELECT no FROM RECIPEDETAIL))) "
				+ "WHERE num BETWEEN #{start} AND #{end} ")
		public List<RecipeVO> recipeListData(@Param("start")int start, @Param("end")int end);
		
		@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
				+ "WHERE NO IN(SELECT NO FROM recipe "
				+ "intersect SELECT NO FROM RECIPEDETAIL) ")
		public int recipeTotalpage();
		
	    //������ �󼼺���
				
	    //���� ������
		@Select("SELECT cno,chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7,num "
				+ "FROM (SELECT cno,chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7,rownum as num "
				+ "FROM (SELECT /*+ INDEX_ASC(chef chef_cno_pk) */cno,chef,poster,mem_cont1,mem_cont2,mem_cont3,mem_cont7 "
				+ "FROM chef)) "
				+ "WHERE num BETWEEN #{start} AND #{end} ")
		public List<ChefVO> chefListData(@Param("start")int start, @Param("end")int end);
		
		@Select("SELECT CEIL(COUNT(*)/20.0) FROM chef")
		public int chefTotalpage();
		
		// ������ ������ 
		@Select("SELECT no,title,poster,chef,num "
			   +"FROM (SELECT no,title,poster,chef,rownum as num "
			   +"FROM (SELECT no,title,poster,chef "
			   +"FROM recipe WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}))) "
			   +"WHERE num BETWEEN #{start} AND #{end}")
		public List<RecipeVO> chefDetailData(Map map);
		
		@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
			   +"WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno})")
		public int chefDetailTotalPage(int cno);
		
		// ������ ������
		@Select("SELECT no,title,poster,chef,num "
			   +"FROM (SELECT no,title,poster,chef,rownum as num "
			   +"FROM (SELECT no,title,poster,chef "
			   +"FROM recipe WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}) AND title LIKE '%'||#{ss}||'%')) "
					   +"WHERE num BETWEEN #{start} AND #{end}")
				public List<RecipeVO> chefFindData(Map map);
				
				@Select("SELECT CEIL(COUNT(*)/20.0) FROM recipe "
					   +"WHERE chef=(SELECT chef FROM chef WHERE cno=#{cno}) AND title LIKE '%'||#{ss}||'%' ")
				public int chefFIndTotalPage(Map map);

		//�󼼺���
	    @Select("SELECT * FROM recipeDetail "
	    		+ "WHERE no=#{no} ")
	    public RecipeDetailVO recipeDetailData(int no);
	    
	    //��� Ŭ���� ��� �Ǹű� ��������
	    @Select("SELECT goods_poster,goods_name,goods_price "
	    		+ "FROM goods_all "
	    		+ "WHERE goods_name LIKE '%'||#{goods_name}||'%' ")
	    public List<GoodsVO> reipeGoodsData(String goods_name);
}
