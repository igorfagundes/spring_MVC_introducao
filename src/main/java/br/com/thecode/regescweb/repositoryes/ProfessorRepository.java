package br.com.thecode.regescweb.repositoryes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thecode.regescweb.models.Professor;
@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    
}
