package br.com.thecode.regescweb.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.thecode.regescweb.model.Professor;
import br.com.thecode.regescweb.model.StatusProfessor;
import br.com.thecode.regescweb.repositories.ProfessorRepository;
import jakarta.transaction.Transactional;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

/* 
//um metodo para criar dependencia
    public void ProfessorRepository(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }
    */
    @GetMapping("/professores")
    public ModelAndView index(){
        List<Professor> professores = this.professorRepository.findAll();
    
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }
    
    @GetMapping("/professor/new")
    public ModelAndView nNew(){
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("statusProfessor", StatusProfessor.values());
        
        return mv;
    }
    //web parameter tampering
    @PostMapping("/professores")
    public String create(Professor professor){
        System.out.println("");
        System.out.println(professor);
        System.out.println("");
        professorRepository.save(professor);
        return "redirect:/professores";
    }
}
