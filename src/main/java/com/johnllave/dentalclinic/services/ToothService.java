package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.entity.Tooth;

import java.util.Set;


public interface ToothService {
	
	Set<Tooth> getTooth();

	Tooth getToothById(String id);
	
}
