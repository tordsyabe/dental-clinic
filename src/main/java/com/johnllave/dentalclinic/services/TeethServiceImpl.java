package com.johnllave.dentalclinic.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnllave.dentalclinic.entity.Teeth;
import com.johnllave.dentalclinic.repository.TeethRepository;

@Service
public class TeethServiceImpl implements TeethService {

	private final TeethRepository teethRepository;

	@Autowired
	public TeethServiceImpl(TeethRepository teethRepository) {
		this.teethRepository = teethRepository;
	}

	@Override
	public Set<Teeth> getTeeth() {

		Set<Teeth> teeth = new HashSet<>();

		teethRepository.findAll().forEach(tooth -> teeth.add(tooth));
		return teeth;
	}


	@Override
	public Teeth getTeethById(String id) {
		return teethRepository.findById(Long.parseLong(id)).orElseGet(null);
	}
}
