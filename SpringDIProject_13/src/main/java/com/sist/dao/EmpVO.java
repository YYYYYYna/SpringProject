package com.sist.dao;
import java.util.*;
import lombok.Data;
@Data // => getter setter�� �ڵ����� ������� => lombok
public class EmpVO {
   private int empno,sal,deptno;
   private String ename,job,dbday;
   private Date hiredate;
}