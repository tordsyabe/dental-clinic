package com.johnllave.dentalclinic.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.johnllave.dentalclinic.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
public class PatientDto extends BaseDto{

	@NotEmpty
	@Size(min = 2, max = 20)
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 20)
	private String lastName;


	private String image = null;

	@NotEmpty
	private String birthDate;

	@NotEmpty
	private String gender;

	private String phone;
	private String email;
	private String address;
	private String city;
	private String province;

	private String dateCreated;
	private String dateUpdated;


	private int age;

	@JsonIgnoreProperties("patientDto")
	private List<ComplaintDto> complaintsDto = new ArrayList<>();

	@JsonIgnoreProperties("patientDto")
	private List<ProcedureDto> proceduresDto  = new ArrayList<>();

	@JsonIgnoreProperties("patientDto")
	private List<AllergyDto> allergiesDto = new ArrayList<>();

	@JsonIgnoreProperties("patientDto")
	private List<MedicalHistoryDto> medicalHistoriesDto = new ArrayList<>();

	@JsonIgnoreProperties("patientDto")
	private List<MissingToothDto> missingTeethDto = new ArrayList<>();

	@JsonIgnoreProperties("patientDto")
	private List<PatientDocumentDto> patientDocumentsDto = new ArrayList<>();

}
