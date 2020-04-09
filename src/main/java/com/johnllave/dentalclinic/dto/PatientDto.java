package com.johnllave.dentalclinic.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

import com.johnllave.dentalclinic.entity.Visit;

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
	private String firstName;
	private String lastName;
	private String image;
	private String birthDate;
	private Integer age;
	private String gender;
	private String phone;
	private String email;
	private String address;
	private String city;
	private String province;

	private Set<Visit> visits = new HashSet<>();

}
