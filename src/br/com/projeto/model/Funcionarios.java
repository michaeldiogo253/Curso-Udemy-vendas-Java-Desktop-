/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

/**
 *
 * @author michael
 */
public class Funcionarios extends Clientes {
    String senha;
    String cargo;
    String nivel_Acesso; 

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getNivel_Acesso() {
        return nivel_Acesso;
    }

    public void setNivel_Acesso(String nivel_Acesso) {
        this.nivel_Acesso = nivel_Acesso;
    }
    
    
    
    
}
