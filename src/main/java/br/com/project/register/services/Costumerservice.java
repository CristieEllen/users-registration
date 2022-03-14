package br.com.project.register.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.register.dto.CostumerDto;
import br.com.project.register.entities.Costumer;
import br.com.project.register.repositories.CostumerRepository;

@Service
public class Costumerservice {

    @Autowired
    private CostumerRepository repository;

    @Transactional()
    public Page<CostumerDto> findAllCostumer(Pageable pageable){
        Page<Costumer> resultCostumer = repository.findAll(pageable);
        Page<CostumerDto> costumerDto = resultCostumer.map(x -> new CostumerDto(x));
        return costumerDto;
    }

    @Transactional()
    public CostumerDto findByIdCostumer(Long id){
        Costumer resultCostumer = repository.findById(id).get();
        CostumerDto costumerDto = new CostumerDto(resultCostumer);
        return costumerDto;
    }

}
