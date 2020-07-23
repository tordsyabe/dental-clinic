package com.johnllave.dentalclinic.controllers;

import com.johnllave.dentalclinic.dto.ComplaintDto;
import com.johnllave.dentalclinic.dto.PatientDto;
import com.johnllave.dentalclinic.dto.ProcedureDto;
import com.johnllave.dentalclinic.entity.Complaint;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Procedure;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
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
		model.addAttribute("lastName", Comparator.comparing((PatientDto patientDto) -> patientDto.getLastName()));
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

		return "redirect:/patient/details/" + savedPatient.getUuid();
	}

	@GetMapping("/patient/details/{id}")
	public String showPatientById(@PathVariable String id, Model model) {

		model.addAttribute("patient", patientService.getPatientById(id));
		model.addAttribute("procedureDate", Comparator.comparing(ProcedureDto::getDateCreated).reversed());
		model.addAttribute("complaintDate", Comparator.comparing((ComplaintDto complaintDto) -> complaintDto.getDateCreated()).reversed());


		return "patient/details";
	}

	@GetMapping("/patient/procedures/{id}")
	public String showPatientProcedures(@PathVariable String id, Model model) {

		PatientDto patientDto = patientService.getPatientById(id);

		model.addAttribute("patient", patientDto);
		model.addAttribute("procedureDate", Comparator.comparing(ProcedureDto::getDateCreated).reversed());
		model.addAttribute("procedureDto", new ProcedureDto());

		return "patient/procedures";
	}

	@GetMapping("/patient/documents/{id}")
	public String showPatientDocuments(@PathVariable String id, Model model) {

		PatientDto patientDto = patientService.getPatientById(id);

		model.addAttribute("patient", patientDto);

		return "patient/documents";
	}

	@GetMapping("/patient/bills/{id}")
	public String showPatientBills(@PathVariable String id, Model model) {

		PatientDto patientDto = patientService.getPatientById(id);

		model.addAttribute("patient", patientDto);

		return "patient/bills";
	}

}
