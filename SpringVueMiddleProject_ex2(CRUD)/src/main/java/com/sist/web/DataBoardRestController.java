package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*; 
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
// Vue�� ������ ��� 
// Vue / React ==> Router => ������ ���(�ۼ���) => ȭ�� ���� => Spring
@RestController
public class DataBoardRestController {
   @Autowired
   private DataBoardDAO dao;
   
   @GetMapping(value="databoard/list_vue.do",produces = "text/plain;charset=UTF-8")
   public String databoard_list(int page) throws Exception
   {
	   int rowSize=10;
	   int start=(rowSize*page)-(rowSize-1);
	   int end=rowSize*page;
	   List<DataBoardVO> list=dao.databoardListData(start, end);
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(list);
	   return json;
   }
   @GetMapping(value="databoard/page_vue.do",produces = "text/plain;charset=UTF-8")
   public String databoard_page(int page) throws Exception
   {
	   int totalpage=dao.databoardTotalPage();
	   Map map=new HashedMap();
	   map.put("curpage", page);
	   map.put("totalpage", totalpage);
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(map);// {curpage:1,totalpage:10}
	   return json;
   }
   
   @PostMapping(value="databoard/insert_vue.do",produces = "text/plain;charset=UTF-8")
   public String databoard_insert(DataBoardVO vo,HttpServletRequest request)
   {
	   System.out.println("���� �Ϸ�!!");
	   // C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringVueMiddleProject_2
	   String result="";
	   try
	   {
		   String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		   path=path.replace("\\", File.separator);// �ü���� ȣȯ 
		   // Hosting => AWS(������)
		   File dir=new File(path);
		   if(!dir.exists())
		   {
			   dir.mkdir();
		   }
		
		   List<MultipartFile> list=vo.getFiles();//�ӽ� ����
		   if(list==null) // ���ε尡 ���� ����
		   {
			   vo.setFilename("");
			   vo.setFilesize("");
			   vo.setFilecount(0);
		   }
		   else //���ε尡 �ִ� ���� 
		   {
			   String filename="";
			   String filesize="";
			   for(MultipartFile mf:list)
			   {
				   String name=mf.getOriginalFilename();
				   File file=new File(path+name);
				   mf.transferTo(file);//  ���ε�
				   
				   filename+=name+",";// a.jpg,b.jpg,
				   filesize+=file.length()+",";
			   }
			   filename=filename.substring(0,filename.lastIndexOf(","));
			   filesize=filesize.substring(0,filesize.lastIndexOf(","));
			   vo.setFilename(filename);
			   vo.setFilesize(filesize);
			   vo.setFilecount(list.size());
		   }
		   dao.databoardInsert(vo);
		   result="yes";
	   }catch(Exception ex)
	   {
		   result=ex.getMessage();   
	   }
	   return result;
   }
   
   @GetMapping(value = "databoard/detail_vue.do",produces = "text/plain;charset=UTF-8")
   public String databoard_detail(int no) throws Exception
   {
	   /*
	    *   no,name,subject,content,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,"
    	   +"hit,filename,filesize,filecount
    	   
    	   response.data={"no":1,name:"",subject:""...filename:""}
    	   a={"sabun":1,name:"ȫ�浿"};
    	   a.sabun a.name
	    */
	   DataBoardVO vo=dao.databoardDetailData(no);
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(vo);
	   return json;
   }
   
   @GetMapping(value = "databoard/download.do")
	public void databoard_download(String fn, HttpServletRequest request, HttpServletResponse response)
	{
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		path=path.replace("\\", File.separator);
		try {
			File file=new File(path+fn);
			response.setHeader("Content-Disposition", "attachement;filename="+URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			//=>�ٿ�ε� â�� �����ش�
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			//�������� ������ �о�´�
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			//���⼭���ʹ� Ŭ���̾�Ʈ ���� ����(�ٿ�ε� ����)
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer,0,1024))!=-1)
			{
				//i�� ���� ����Ʈ ��
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		}catch(Exception ex) {}
	}
   
   @GetMapping(value = "databoard/view_image.do")
   public void databoard_view_image(String fn, HttpServletRequest request, HttpServletResponse response) {
       String path = request.getSession().getServletContext().getRealPath("/") + "upload\\";
       path = path.replace("\\", File.separator);
       try {
           File file = new File(path + fn);
           if (!file.exists() || !file.isFile()) {
               // ������ �������� ������ ���� ó��
               throw new FileNotFoundException("File not found: " + file.getPath());
           }
           
           // �̹������� Ȯ��
           String contentType = Files.probeContentType(file.toPath());
           if (contentType == null || !contentType.startsWith("image")) {
               // �̹����� �ƴϸ� ���� ó��
               throw new IOException("File is not an image: " + file.getPath());
           }

           // �̹��� ��� ����
           response.setContentType(contentType);
           response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(fn, "UTF-8"));

           // �̹����� Ŭ���̾�Ʈ�� ����
           try (InputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream()) {
               byte[] buffer = new byte[1024];
               int bytesRead;
               while ((bytesRead = in.read(buffer)) != -1) {
                   out.write(buffer, 0, bytesRead);
               }
           }
       } catch (Exception ex) {
           // ���� ó��
           ex.printStackTrace();
       }
   }

}




