package br.com.thecode.regescweb.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.thecode.regescweb.dto.RequisicaoFormProfessor;
import br.com.thecode.regescweb.model.Professor;
import br.com.thecode.regescweb.model.StatusProfessor;
import br.com.thecode.regescweb.repositories.ProfessorRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository professorRepository;

/* 
//um metodo para criar dependencia
    public void ProfessorRepository(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }
    */
    @GetMapping("")
    public ModelAndView index(){
        List<Professor> professores = this.professorRepository.findAll();
    
        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);

        return mv;
    }
    
    @GetMapping("/new")
    public ModelAndView nNew(RequisicaoFormProfessor requisicao){
        ModelAndView mv = new ModelAndView("professores/new");
        //mv.addObject("requisicaoNovoProfessor", new RequisicaoNovoProfessor());
        mv.addObject("statusProfessor", StatusProfessor.values());

        return mv;
    }
    //web parameter tampering
    @PostMapping("")
    public ModelAndView create(@Valid RequisicaoFormProfessor requisicao, BindingResult result){
        if(result.hasErrors()){
            System.out.println("Contem erros");

            ModelAndView mv = new ModelAndView("professores/new");
            mv.addObject("statusProfessor", StatusProfessor.values());
            return mv;
        }else{
        Professor professor = requisicao.toProfessor();
        professorRepository.save(professor);
            
        return new ModelAndView("redirect:/professores/" + professor.getId());
    }
        
    }
    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id){
        System.out.println("***** ID:" + id + " *****");
        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){
            Professor professor = optional.get();
            ModelAndView mv = new ModelAndView("professores/show");
            mv.addObject("professor", professor);
            return mv;
            
        }else{
            System.out.println("******* Não achou o ID de professor ID: " + id + " *******");
            return new ModelAndView("redirect:/professores");
        }
        

    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id, RequisicaoFormProfessor requisicao){
        Optional<Professor> optional = this.professorRepository.findById(id);

        if(optional.isPresent()){
            Professor professor = optional.get();
            requisicao.fromProfessor(professor);
            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", professor.getId());
            mv.addObject("statusProfessor", StatusProfessor.values());
            return mv;
        }else{
            System.out.println("******* Não achou o ID de professor ID: " + id + " *******");
            return new ModelAndView("redirect:/professores" );
        }
    }
    @PostMapping("/{id}")
    public ModelAndView update(@PathVariable Long id, @Valid RequisicaoFormProfessor requisicao, BindingResult result){
        if(result.hasErrors()){
            //System.out.println("Contem erros");

            ModelAndView mv = new ModelAndView("professores/edit");
            mv.addObject("professorId", id);
            mv.addObject("statusProfessor", StatusProfessor.values());
            return mv;
        }else{
            Optional<Professor> op = this.professorRepository.findById(id);
            if(op.isPresent()){
                Professor professor = requisicao.toProfessor(op.get());
                Professor professor2 = requisicao.toProfessor(professor);

                this.professorRepository.save(professor);
                this.professorRepository.save(professor2);
                System.out.println(professor);
                System.out.println(professor2);


                return new ModelAndView("redirect:/professores" + professor.getId());
            }else{

            }
            System.out.println("****** NÃO ACHOU O ID DO PROFESSOR ID : #" + id + " ******");
            
            return new ModelAndView("redirect:/professores" + id);
    }
        

    }
}
