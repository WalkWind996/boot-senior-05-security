package com.walkwind.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 聪明
 */
@Controller
public class KungfuController {
	private final String PREFIX = "pages/";

	@GetMapping("/")
	public String index() {
		return "welcome";
	}

	@GetMapping("/userLogin")
	public String loginPage() {
		return PREFIX+"login";
	}

	@GetMapping("/level1/{path}")
	public String level1(@PathVariable("path")String path) {
		return PREFIX+"level1/"+path;
	}

	@GetMapping("/level2/{path}")
	public String level2(@PathVariable("path")String path) {
		return PREFIX+"level2/"+path;
	}

	@GetMapping("/level3/{path}")
	public String level3(@PathVariable("path")String path) {
		return PREFIX+"level3/"+path;
	}


}
