package com.johnllave.dentalclinic.dto;

import java.time.Period;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import com.johnllave.dentalclinic.entity.Complaint;
import com.johnllave.dentalclinic.entity.Procedure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PatientDto {
	
	private Long id;

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

	private int age;

	private Set<Complaint> complaints = new HashSet<>();

	private Set<Procedure> procedures  = new HashSet<>();

}
