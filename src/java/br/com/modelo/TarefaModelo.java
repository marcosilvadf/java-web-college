/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.modelo;

/**
 *
 * @author Antonio Marcos
 */
public class TarefaModelo {
    private int id;
    private String nome;
    private String descricao;
    private int categoriaId;
    private int prazoId;
    private int userId;    
    private String pNome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getPrazoId() {
        return prazoId;
    }

    public void setPrazoId(int prazoId) {
        this.prazoId = prazoId;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getPNome() {
        return pNome;
    }

    public void setPNome(String pNome) {
        this.pNome = pNome;
    }
}
