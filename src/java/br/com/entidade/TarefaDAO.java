/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.modelo.TarefaModelo;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio Marcos
 */
public class TarefaDAO extends Conector{
    public void tarefaInserir(TarefaModelo tarefaModelo){
        try {
            abrirBanco();
            
            String query = "INSERT INTO tarefa (id, nome, descricao, categoria_id, prazo_id, usuario_id) values(null, ?, ?, ?, ?, ?)";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1,tarefaModelo.getNome());
            pst.setString(2,tarefaModelo.getDescricao());
            pst.setInt(3,tarefaModelo.getCategoriaId());
            pst.setInt(4,tarefaModelo.getPrazoId());
            pst.setInt(5,tarefaModelo.getUserId());
            
            pst.execute();
        
            fecharBanco();            
           
        } catch (SQLException e) {
            System.out.println("Erro Cat: " + e.getMessage());            
        }
    }
    
    public List<TarefaModelo> tarefaObterTodasUsuario(int user_id) {
            List<TarefaModelo> tarefas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT tarefa.id AS id, tarefa.nome AS nome, tarefa.descricao AS descricao, tarefa.categoria_id AS categoria_id, tarefa.prazo_id AS prazo_id, tarefa.usuario_id AS usuario_id, prazo.tempo AS pnome FROM tarefa INNER JOIN prazo ON tarefa.prazo_id = prazo.id WHERE tarefa.usuario_id = ? ";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setInt(1, user_id);
            pst.execute();
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                int categoriaId = rs.getInt("categoria_id");
                int prazoId = rs.getInt("prazo_id");
                int userId = rs.getInt("usuario_id");
                String pNome = rs.getString("pnome");
                
                TarefaModelo tarefaModelo = new TarefaModelo();
                
                tarefaModelo.setId(id);
                tarefaModelo.setNome(nome);
                tarefaModelo.setDescricao(descricao);
                tarefaModelo.setCategoriaId(categoriaId);
                tarefaModelo.setPrazoId(prazoId);
                tarefaModelo.setPNome(pNome);
                
                tarefas.add(tarefaModelo);                
            }
            
            fecharBanco();                        
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
        }
        
        return tarefas;
    }
    
    public List<TarefaModelo> tarefaObterTodasUsuarioCategoria(int user_id, int cat_id) {
            List<TarefaModelo> tarefas = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT tarefa.id AS id, tarefa.nome AS nome, tarefa.descricao AS descricao, tarefa.categoria_id AS categoria_id, tarefa.prazo_id AS prazo_id, tarefa.usuario_id AS usuario_id, prazo.tempo AS pnome FROM tarefa INNER JOIN prazo ON tarefa.prazo_id = prazo.id WHERE tarefa.usuario_id = ? AND tarefa.categoria_id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setInt(1, user_id);
            pst.setInt(2, cat_id);
            pst.execute();
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                int categoriaId = rs.getInt("categoria_id");
                int prazoId = rs.getInt("prazo_id");
                int userId = rs.getInt("usuario_id");
                String pNome = rs.getString("pnome");
                
                TarefaModelo tarefaModelo = new TarefaModelo();
                
                tarefaModelo.setId(id);
                tarefaModelo.setNome(nome);
                tarefaModelo.setDescricao(descricao);
                tarefaModelo.setCategoriaId(categoriaId);
                tarefaModelo.setPrazoId(prazoId);
                tarefaModelo.setPNome(pNome);
                
                tarefas.add(tarefaModelo);                
            }
            
            fecharBanco();                        
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
        }
        
        return tarefas;
    }
    
    public boolean tarefaDeletar(int id) {
        
        try {
            abrirBanco();
            
            String query = "DELETE FROM tarefa WHERE id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
                        
            pst.setInt(1,id);
            pst.execute();                        
            
            fecharBanco();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
            return false;
        }
        
    }
    
    public boolean tarefaEditar(TarefaModelo tarefaModelo) {
        
        try {
            abrirBanco();
            
            String query = "UPDATE tarefa SET nome = ?, descricao = ?, categoria_id = ?, prazo_id = ? WHERE id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1,tarefaModelo.getNome());
            pst.setString(2,tarefaModelo.getDescricao());
            pst.setInt(3,tarefaModelo.getCategoriaId());
            pst.setInt(4,tarefaModelo.getPrazoId());
            pst.setInt(5,tarefaModelo.getId());
            
            pst.execute();                      
            
            fecharBanco();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
            return false;
        }        
    }
    
    public boolean tarefaDeletarPorUsuario(int id) {
        
        try {
            abrirBanco();
            
            String query = "DELETE FROM tarefa WHERE usuario_id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
                        
            pst.setInt(1,id);
            pst.execute();                        
            
            fecharBanco();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
            return false;
        }
        
    }
    
    public boolean tarefaDeletarPorCategoria(int id) {
        
        try {
            abrirBanco();
            
            String query = "DELETE FROM tarefa WHERE categoria_id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
                        
            pst.setInt(1,id);
            pst.execute();                        
            
            fecharBanco();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
            return false;
        }
        
    }
    
    public boolean tarefaDeletarPorPrazo(int id) {
        
        try {
            abrirBanco();
            
            String query = "DELETE FROM tarefa WHERE prazo_id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
                        
            pst.setInt(1,id);
            pst.execute();                        
            
            fecharBanco();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
            return false;
        }
        
    }
}
