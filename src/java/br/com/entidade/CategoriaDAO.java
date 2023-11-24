/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import br.com.modelo.CategoriaModelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio Marcos
 */
public class CategoriaDAO extends Conector{
    public void categoriaInserir(String nome, int id){
        try {
            abrirBanco();
            
            String query = "INSERT INTO categoria (id, nome, usuario_id) values(null, ?, ?)";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1,nome);
            pst.setInt(2,id);                                  
            
            pst.execute();
        
            fecharBanco();            
           
        } catch (SQLException e) {
            System.out.println("Erro Cat: " + e.getMessage());            
        }
    }
    
    public List<CategoriaModelo> categoriaObterTodasUsuario(int user_id) {
            List<CategoriaModelo> categorias = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM categoria WHERE usuario_id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setInt(1, user_id);
            pst.execute();
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int usuario_id = rs.getInt("usuario_id");
                
                CategoriaModelo catModel = new CategoriaModelo();
                
                catModel.setId(id);
                catModel.setNome(nome);
                catModel.setUsuario_id(usuario_id);
                
                categorias.add(catModel);                
            }
            
            fecharBanco();                        
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
        }
        
        return categorias;
    }
    
    public boolean categoriaEditar(CategoriaModelo categoriaModelo) {
        
        try {
            abrirBanco();
            
            String query = "UPDATE categoria SET nome = ? WHERE id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1,categoriaModelo.getNome());
            pst.setInt(2,categoriaModelo.getId());
            pst.execute();                      
            
            fecharBanco();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
            return false;
        }        
    }
    
    public boolean categoriaDeletar(int id) {
        
        try {
            abrirBanco();
            
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefaDAO.tarefaDeletarPorCategoria(id);
            
            String query = "DELETE FROM categoria WHERE id = ?";
            
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
    
    public boolean categoriaDeletarPorUsuario(int id) {
        
        try {
            abrirBanco();
            
            String query = "DELETE FROM categoria WHERE usuario_id = ?";
            
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
