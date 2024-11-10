package org.jsp.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ControllerClass {
	
	@GetMapping("/")
	@ResponseBody
	public String printHello() {
		return "Hello World";
	}

}
