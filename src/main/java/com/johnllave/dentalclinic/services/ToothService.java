package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.ToothDto;
import com.johnllave.dentalclinic.entity.Tooth;

import java.util.Set;


public interface ToothService {
	
	Set<ToothDto> getTeeth();

	Tooth getToothById(String id);
	
}
