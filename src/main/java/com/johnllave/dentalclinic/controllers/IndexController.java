package com.johnllave.dentalclinic.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.johnllave.dentalclinic.services.TeethService;

@Controller
public class IndexController {

	private final TeethService teethService;

	public IndexController(TeethService teethService) {
		this.teethService = teethService;
	}

	@RequestMapping({ "/", "index", "index.html" })
	public String indexPage(Model model) {

//		model.addAttribute("teeth", teethService.getTeeth());

		return "index";
	}


}
