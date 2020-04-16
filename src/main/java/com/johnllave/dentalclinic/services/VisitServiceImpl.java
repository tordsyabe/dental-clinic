package com.johnllave.dentalclinic.services;

import com.johnllave.dentalclinic.dto.VisitDto;
import com.johnllave.dentalclinic.entity.Patient;
import com.johnllave.dentalclinic.entity.Visit;
import com.johnllave.dentalclinic.mapper.VisitMapper;
import com.johnllave.dentalclinic.repository.VisitRepository;

public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final VisitMapper visitMapper;

    public VisitServiceImpl(VisitRepository visitRepository, VisitMapper visitMapper) {
        this.visitRepository = visitRepository;
        this.visitMapper = visitMapper;
    }

    @Override
    public VisitDto saveVisit(VisitDto visitDto) {

        Visit visit = visitMapper.visitDtoToVisit(visitDto);

       Visit savedVisit = visitRepository.save(visit);

        return  visitMapper.visitToVisitDto(savedVisit);
    }
}
