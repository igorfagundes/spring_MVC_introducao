package br.com.thecode.regescweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloController {
//MODELO DE BAIXO NIVEL SERVLET 
    @GetMapping("/hello-servlet")
    public String hello(HttpServletRequest request){
        request.setAttribute("nome", "igor");
        return "hello";
    }
//MODELO USANDO MODEL
    @GetMapping("/hello-model")
    public String hello(Model model){
        model.addAttribute("nome", "fagundes");
        return "hello";
    }
//MODELO USANDO MODELANDVIEW
@GetMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView("hello");//arquivo html a ser renderizado
        mv.addObject("nome", "pontes");
        return mv;
    }

}
