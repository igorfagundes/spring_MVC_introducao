package br.com.thecode.regescweb.dto;

import java.math.BigDecimal;

import br.com.thecode.regescweb.model.Professor;
import br.com.thecode.regescweb.model.StatusProfessor;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//classe DTO(Data Transfer Object)
public class RequisicaoFormProfessor {
    @NotBlank
    @NotNull
    private String nome;
    private StatusProfessor statusProfessor;
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal salario;
//getters and setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }
    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }
    public BigDecimal getSalario() {
        return salario;
    }
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
    public Professor toProfessor(){
        Professor professor = new Professor(nome, salario, statusProfessor);
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatusProfessor(this.statusProfessor);
        return professor;
    }
    public void fromProfessor(Professor professor){
        this.nome = professor.getNome();
        this.salario = professor.getSalario();
        this.statusProfessor = professor.getStatusProfessor();
    }
//to string
    @Override
    public String toString() {
        return "RequisicaoNovoProfessor [nome=" + nome + ", statusProfessor=" + statusProfessor + ", salario=" + salario
                + "]";
    }
    public Professor toProfessor(Professor professor){
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatusProfessor(this.statusProfessor);

        return professor;

    }

    
    
}
