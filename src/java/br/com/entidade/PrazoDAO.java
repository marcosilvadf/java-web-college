/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import br.com.modelo.PrazoModelo;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author Antonio Marcos
 */
public class PrazoDAO extends Conector{
    public void prazoInserir(String tempo, int usuarioId) {
        try{
            abrirBanco();
            String query = "INSERT INTO prazo (id, tempo, usuario_id) VALUES(null, ?, ?)";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1, tempo);
            pst.setInt(2, usuarioId);
            
            pst.execute();
            
            fecharBanco();
        }
        catch(SQLException e){
                System.out.println("Erro Prazo: "+e.getMessage());
        }
    }
    
    public List<PrazoModelo> prazoObterTodasUsuario(int user_id) {
            List<PrazoModelo> categorias = new ArrayList<>();
        try {
            abrirBanco();
            String query = "SELECT * FROM prazo WHERE usuario_id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setInt(1, user_id);
            pst.execute();
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String tempo = rs.getString("tempo");
                int usuario_id = rs.getInt("usuario_id");
                
                PrazoModelo prazoModelo = new PrazoModelo();
                
                prazoModelo.setId(id);
                prazoModelo.setTempo(tempo);
                prazoModelo.setUsuarioId(usuario_id);
                
                categorias.add(prazoModelo);                
            }
            
            fecharBanco();                        
        } catch (SQLException e) {
            System.out.println("Erro prazo lista: " + e.getMessage());            
        }
        
        return categorias;
    }
    
    public boolean prazoEditar(PrazoModelo prazoModelo) {
        
        try {
            abrirBanco();
            
            String query = "UPDATE prazo SET tempo = ? WHERE id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1,prazoModelo.getTempo());
            pst.setInt(2,prazoModelo.getId());
            pst.execute();                      
            
            fecharBanco();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
            return false;
        }        
    }
    
    public boolean prazoDeletar(int id) {
        
        try {
            abrirBanco();
            
            TarefaDAO tarefaDAO = new TarefaDAO();
            tarefaDAO.tarefaDeletarPorPrazo(id);
            
            String query = "DELETE FROM prazo WHERE id = ?";
            
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
    
    public boolean prazoDeletarPorUsuario(int id) {
        
        try {
            abrirBanco();
            
            String query = "DELETE FROM prazo WHERE usuario_id = ?";
            
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
