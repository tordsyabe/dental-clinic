package com.johnllave.dentalclinic.services;

import java.util.HashSet;
import java.util.Set;

import com.johnllave.dentalclinic.entity.Tooth;
import com.johnllave.dentalclinic.repository.ToothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeethServiceImpl implements ToothService {

	private final ToothRepository toothRepository;

	@Autowired
	public TeethServiceImpl(ToothRepository toothRepository) {
		this.toothRepository = toothRepository;
	}

	@Override
	public Set<Tooth> getTooth() {

		Set<Tooth> teeth = new HashSet<>();

		toothRepository.findAll().forEach(tooth -> teeth.add(tooth));
		return teeth;
	}


	@Override
	public Tooth getToothById(String id) {
		return toothRepository.findById(Long.parseLong(id)).orElseGet(null);
	}
}
