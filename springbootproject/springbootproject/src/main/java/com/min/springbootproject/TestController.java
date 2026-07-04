package com.min.springbootproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class TestController {

	@RequestMapping("/irshad")
	public String fileHandler() {
		return "Just for Testing by irshad! All is Okk!!!";
	}
}
