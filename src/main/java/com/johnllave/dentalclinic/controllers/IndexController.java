package com.johnllave.dentalclinic.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({ "/", "index", "index.html" })
		public String indexPage(Model model) {

		System.out.println("plus 1 visited.");
		return "index";
	}

	@RequestMapping("/calendar")
	public String calendarPage(Model model) {

		return "calendar";
	}

	@RequestMapping("/data")
	public String dataAnalysisPage(Model model) {

		return "data";
	}

	@RequestMapping("/setting")
	public String settingsPage(Model model) {

		return "setting";
	}


}
