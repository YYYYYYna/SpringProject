package com.sist.dao;
import java.util.*;
import lombok.Data;
@Data // => getter setter를 자동으로 만들어줌 => lombok
public class EmpVO {
   private int empno,sal,deptno;
   private String ename,job,dbday;
   private Date hiredate;
}