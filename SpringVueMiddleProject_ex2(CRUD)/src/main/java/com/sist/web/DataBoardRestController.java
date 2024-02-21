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
// Vue와 데이터 통신 
// Vue / React ==> Router => 데이터 통신(송수신) => 화면 변경 => Spring
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
	   System.out.println("접근 완료!!");
	   // C:\springDev\springStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringVueMiddleProject_2
	   String result="";
	   try
	   {
		   String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		   path=path.replace("\\", File.separator);// 운영체제의 호환 
		   // Hosting => AWS(리눅스)
		   File dir=new File(path);
		   if(!dir.exists())
		   {
			   dir.mkdir();
		   }
		
		   List<MultipartFile> list=vo.getFiles();//임시 저장
		   if(list==null) // 업로드가 없는 상태
		   {
			   vo.setFilename("");
			   vo.setFilesize("");
			   vo.setFilecount(0);
		   }
		   else //업로드가 있는 상태 
		   {
			   String filename="";
			   String filesize="";
			   for(MultipartFile mf:list)
			   {
				   String name=mf.getOriginalFilename();
				   File file=new File(path+name);
				   mf.transferTo(file);//  업로드
				   
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
    	   a={"sabun":1,name:"홍길동"};
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
			//=>다운로드 창을 보여준다
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			//서버에서 파일을 읽어온다
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			//여기서부터는 클라이언트 복사 영역(다운로드 폴더)
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer,0,1024))!=-1)
			{
				//i는 읽은 바이트 수
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
               // 파일이 존재하지 않으면 예외 처리
               throw new FileNotFoundException("File not found: " + file.getPath());
           }
           
           // 이미지인지 확인
           String contentType = Files.probeContentType(file.toPath());
           if (contentType == null || !contentType.startsWith("image")) {
               // 이미지가 아니면 예외 처리
               throw new IOException("File is not an image: " + file.getPath());
           }

           // 이미지 출력 설정
           response.setContentType(contentType);
           response.setHeader("Content-Disposition", "inline; filename=" + URLEncoder.encode(fn, "UTF-8"));

           // 이미지를 클라이언트로 전송
           try (InputStream in = new FileInputStream(file);
                OutputStream out = response.getOutputStream()) {
               byte[] buffer = new byte[1024];
               int bytesRead;
               while ((bytesRead = in.read(buffer)) != -1) {
                   out.write(buffer, 0, bytesRead);
               }
           }
       } catch (Exception ex) {
           // 예외 처리
           ex.printStackTrace();
       }
   }

}




