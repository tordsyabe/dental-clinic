package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Complaint;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.johnllave.dentalclinic.mapper.PatientMapper;
import com.johnllave.dentalclinic.services.PatientService;

import java.util.Comparator;

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
		System.out.println(patientService.getPatients().toString());
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

		Patient patient = patientService.getPatientById(Long.parseLong(id));


		model.addAttribute("patient", patientMapper.patientToPatientDto(patient));
		model.addAttribute("procedureDate", Comparator.comparing(Procedure::getDate).reversed());
		model.addAttribute("complaintDate", Comparator.comparing((Complaint complaint) -> complaint.getDate()).reversed());

		return "patient/details";
	}

	@GetMapping("/patient/procedures/{id}")
	public String showPatientProcedures(@PathVariable String id, Model model) {

		Patient patient = patientService.getPatientById(Long.parseLong(id));

		model.addAttribute("patient", patientMapper.patientToPatientDto(patient));
		model.addAttribute("procedureDto", new ProcedureDto());

		System.out.println(patientMapper.patientToPatientDto(patient).toString());

		return "patient/procedures";
	}

}
