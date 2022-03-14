package br.com.project.register.repositories;

import br.com.project.register.entities.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostumerRepository extends JpaRepository<Costumer, Long> {
}
