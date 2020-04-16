package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.dto.VisitDto;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.services.PatientService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.Set;

@Controller
public class PatientController {

	private final PatientService patientService;

	private final PatientMapper patientMapper;

	@Autowired
	public PatientController(PatientService patientService, PatientMapper patientMapper) {

		this.patientService = patientService;
		this.patientMapper = patientMapper;
	}



	@GetMapping("/patient/list")
	public String showPatients(Model model) {

		model.addAttribute("patients", patientService.getPatients());
		model.addAttribute("lastName", Comparator.comparing((PatientDto patient) -> patient.getLastName()));

		return "patient/list";
	}

	@GetMapping("/patient/new")
	public String createPatient(Model model) {

		model.addAttribute("patient", new PatientDto());

		return "patient/details";
	}

	@PostMapping("patient")
	public String savePatient(@ModelAttribute PatientDto patientDto) {

		PatientDto savedPatient = patientService.savePatient(patientDto);

		return "redirect:/patient/details/" + savedPatient.getId();
	}

	@GetMapping("/patient/details/{id}")
	public String showPatientById(@PathVariable String id, Model model) {

		model.addAttribute("patient", patientService.getPatientById(Long.parseLong(id)));
		model.addAttribute("visitsByDate", Comparator.comparing(Visit::getDate).reversed());

		return "patient/details";
	}

	@GetMapping("/patient/procedures/{id}")
	public String showPatientProcedures(@PathVariable String id, Model model) {

		model.addAttribute("patient", patientService.getPatientById(Long.parseLong(id)));
		model.addAttribute("procedureDto", new ProcedureDto());

		return "patient/procedures";
	}

}
