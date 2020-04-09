package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.services.PatientService;

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



	@RequestMapping("/patient/list")
	public String showPatients(Model model) {


		model.addAttribute("patients", patientService.getPatients());

		return "patient/list";
	}

	@RequestMapping("/patient/new")
	public String createPatient(Model model) {

		model.addAttribute("patient", new PatientDto());

		return "patient/details";
	}

	@PostMapping
	@RequestMapping("patient")
	public String savePatient(@ModelAttribute PatientDto patientDto) {

		PatientDto savePatient = patientService.savePatient(patientDto);

		return "redirect:/patient/details/" + savePatient.getId();
	}

	@RequestMapping("/patient/details/{id}")
	public String showPatientById(@PathVariable String id, Model model) {

		Patient patient = patientService.getPatientById(Long.parseLong(id));

		PatientDto patientDto =  patientMapper.patientToPatientDto(patient);

		System.out.println("FROM showPatientById()");
		System.out.println(patientDto.toString());

		model.addAttribute("patient", patientDto);

		model.addAttribute("patient", patientService.getPatientById(Long.parseLong(id)));

		return "patient/details";
	}

	@RequestMapping("/patient/procedures/{id}")
	public String showPatientProcedures(@PathVariable String id, Model model) {

		Patient patient = patientService.getPatientById(Long.parseLong(id));

 		PatientDto patientDto =  patientMapper.patientToPatientDto(patient);

 		System.out.println("FROM showPatientProcedures()");
 		System.out.println(patientDto.toString());

		model.addAttribute("patient", patientDto);

		return "patient/procedures";
	}

}
