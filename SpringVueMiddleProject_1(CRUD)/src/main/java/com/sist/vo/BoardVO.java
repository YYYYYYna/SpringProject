package com.sist.vo;
import java.util.*;
import lombok.Data;
/*
 * CREATE TABLE vueBoard(
no number,
name VARCHAR2(51) CONSTRAINT vb_name_nn not null,
subject VARCHAR2(2000) CONSTRAINT vb_subject_nn not null,
content CLOB CONSTRAINT vb_content_nn not null,
pwd VARCHAR2(10)CONSTRAINT vb_pwd_nn not null,
regdate DATE DEFAULT SYSDATE,
hit NUMBER DEFAULT 0,
CONSTRAINT vb_no_pk PRIMARY KEY(no)
);
CREATE SEQUENCE vb_no_seq
start with 1
INCREMENT by 1
nocache
nocycle;
 */
@Data
public class BoardVO {
	private int no,hit;
	private String name,subject,content,pwd,dbday;
	private Date regdate;
}
