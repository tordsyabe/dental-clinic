package com.johnllave.dentalclinic.controllers;


import com.johnllave.dentalclinic.services.DentalProcedureCategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private final DentalProcedureCategoryService dentalProcedureCategoryService;

	public IndexController(DentalProcedureCategoryService dentalProcedureCategoryService) {
		this.dentalProcedureCategoryService = dentalProcedureCategoryService;
	}

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

	@RequestMapping("/settings")
	public String settingsPage(Model model) {

		model.addAttribute("dentalCategories", dentalProcedureCategoryService.getCategories());

		return "settings";
	}


}
