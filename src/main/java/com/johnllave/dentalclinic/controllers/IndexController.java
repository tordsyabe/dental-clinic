package com.johnllave.dentalclinic.controllers;


import com.johnllave.dentalclinic.dto.DentalProcedureCategoryDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.dto.ToothDto;
import com.johnllave.dentalclinic.services.DentalProcedureCategoryService;
import com.johnllave.dentalclinic.services.ToothService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Comparator;

@Controller
public class IndexController {

	private final DentalProcedureCategoryService dentalProcedureCategoryService;
	private final ToothService toothService;

	public IndexController(DentalProcedureCategoryService dentalProcedureCategoryService, ToothService toothService) {
		this.dentalProcedureCategoryService = dentalProcedureCategoryService;
		this.toothService = toothService;
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
		model.addAttribute("dentalCategoryDto", new DentalProcedureCategoryDto());
		model.addAttribute("teeth", toothService.getTeeth());
		model.addAttribute("toothNumber", Comparator.comparing(ToothDto::getNumber));

		return "settings";
	}


}
