package com.sist.web;
import com.sist.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MoimController {
	@Autowired
	private MoimDAO dao;
}
