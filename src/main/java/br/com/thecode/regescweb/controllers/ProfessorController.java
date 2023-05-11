package br.com.thecode.regescweb.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.thecode.regescweb.models.Professor;

import br.com.thecode.regescweb.repositoryes.ProfessorRepository;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

    
    
    @GetMapping("/professores")
    public ModelAndView index(){
        
        List<Professor> professores = this.professorRepository.findAll();
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

}
