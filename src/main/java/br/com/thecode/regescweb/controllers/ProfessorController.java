package br.com.thecode.regescweb.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.thecode.regescweb.dto.RequisicaoNovoProfessor;
import br.com.thecode.regescweb.model.Professor;
import br.com.thecode.regescweb.model.StatusProfessor;
import br.com.thecode.regescweb.repositories.ProfessorRepository;

import jakarta.validation.Valid;

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
    
    @GetMapping("/professores/new")
    public ModelAndView nNew(){
        ModelAndView mv = new ModelAndView("professores/new");
        mv.addObject("requisicaoNovoProfessor", new RequisicaoNovoProfessor());
        mv.addObject("statusProfessor", StatusProfessor.values());

        return mv;
    }
    //web parameter tampering
    @PostMapping("/professores")
    public ModelAndView create(@Valid RequisicaoNovoProfessor requisicao, BindingResult result){
        if(result.hasErrors()){
            System.out.println("Contem erros");

            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("statusProfessor", StatusProfessor.values());
            return mv;
        }else{
        Professor professor = requisicao.toProfessor();
        professorRepository.save(professor);
        }
        return new ModelAndView("redirect:/professores");
        
    }
}
