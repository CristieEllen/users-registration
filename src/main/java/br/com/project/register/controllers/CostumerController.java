package br.com.project.register.controllers;

import br.com.project.register.dto.CostumerDto;
import br.com.project.register.repositories.CostumerRepository;
import br.com.project.register.services.Costumerservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/costumers")
public class CostumerController {

    @Autowired
    private Costumerservice service;

    @GetMapping
    public Page<CostumerDto> findAllCostumer(Pageable pageable){
        return service.findAllCostumer(pageable);
    }

    @GetMapping(value = "/{id}")
    public CostumerDto findByIdCostumer(@PathVariable Long id){
        return service.findByIdCostumer(id);
    }
}
