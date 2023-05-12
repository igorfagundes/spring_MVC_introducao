package br.com.thecode.regescweb.dto;

import java.math.BigDecimal;

import br.com.thecode.regescweb.models.Professor;
import br.com.thecode.regescweb.models.StatusProfessor;
import jakarta.annotation.Nonnull;




// É uma classe DTO (Data Transfer Object)
public class RequisicaoFormProfessor {
    @Nonnull
    private String nome; // em caso de erro, NotBlank.requisicaoNovoProfessor.nome
    @Nonnull
    
    private BigDecimal salario;
    private StatusProfessor statusProfessor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public StatusProfessor getStatusProfessor() {
        return statusProfessor;
    }

    public void setStatusProfessor(StatusProfessor statusProfessor) {
        this.statusProfessor = statusProfessor;
    }


    public Professor toProfessor() {
        Professor professor = new Professor();
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatusProfessor(this.statusProfessor);

        return professor;
    }


    public Professor toProfessor(Professor professor) {
        professor.setNome(this.nome);
        professor.setSalario(this.salario);
        professor.setStatusProfessor(this.statusProfessor);
        return professor;
    }

    public void fromProfessor(Professor professor) {
        this.nome = professor.getNome();
        this.salario = professor.getSalario();
        this.statusProfessor = professor.getStatusProfessor();
    }

    @Override
    public String toString() {
        return "RequisicaoNovoProfessor{" +
               "nome='" + nome + '\'' +
               ", salario=" + salario +
               ", statusProfessor=" + statusProfessor +
               '}';
    }
}