package br.com.thecode.regescweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.thecode.regescweb.model.Professor;


public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
}
