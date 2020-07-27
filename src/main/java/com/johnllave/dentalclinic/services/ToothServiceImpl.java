package com.johnllave.dentalclinic.services;

import java.util.HashSet;
import java.util.Set;

import com.johnllave.dentalclinic.dto.ToothDto;
import com.johnllave.dentalclinic.entity.Tooth;
import com.johnllave.dentalclinic.mapper.CycleAvoidingMappingContext;
import com.johnllave.dentalclinic.mapper.ToothMapper;
import com.johnllave.dentalclinic.repository.ToothRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ToothServiceImpl implements ToothService {

	private final ToothRepository toothRepository;
	private final ToothMapper toothMapper;

	@Autowired
	public ToothServiceImpl(ToothRepository toothRepository, ToothMapper toothMapper) {
		this.toothRepository = toothRepository;
		this.toothMapper = toothMapper;
	}

	@Override
	public Set<ToothDto> getTeeth() {

		Set<ToothDto> teeth = new HashSet<>();

		toothRepository.findAll().forEach(tooth -> teeth.add(toothMapper.teethToTeethDto(tooth, new CycleAvoidingMappingContext())));
		return teeth;
	}


	@Override
	public Tooth getToothById(String id) {
		return toothRepository.findById(Long.parseLong(id)).orElseGet(null);
	}
}
