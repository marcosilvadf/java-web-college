/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;
import br.com.modelo.UsuarioModelo;
import br.com.entidade.CategoriaDAO;
import br.com.entidade.PrazoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Antonio Marcos
 */
public class UsuarioDAO extends Conector{
    public int usuarioInserir(UsuarioModelo usuarioModelo) {
        try {
            abrirBanco();
            
            String query = "INSERT INTO usuario (id, nome, email, senha) values(null, ?, ?, ?)";
            
            pst = (PreparedStatement) con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            pst.setString(1,usuarioModelo.getNome());
            pst.setString(2,usuarioModelo.getEmail());
            pst.setString(3,usuarioModelo.getSenha());                                    
            
            pst.executeUpdate();
        
            // Obtenha as chaves geradas
            ResultSet generatedKeys = pst.getGeneratedKeys();
            int idInserido = 0;
            // Verifique se as chaves foram geradas
            if (generatedKeys.next()) {
                idInserido = generatedKeys.getInt(1);

                // Feche o ResultSet e a conexão
                generatedKeys.close();
                fecharBanco();
                
                CategoriaDAO cat = new CategoriaDAO();
                PrazoDAO prazoDAO = new PrazoDAO();
                
                cat.categoriaInserir("A fazer", idInserido);
                cat.categoriaInserir("Em progresso", idInserido);
                cat.categoriaInserir("Concluida", idInserido);
                
                prazoDAO.prazoInserir("1 hora", idInserido);
                prazoDAO.prazoInserir("1 dia", idInserido);
                prazoDAO.prazoInserir("1 semana", idInserido);
                prazoDAO.prazoInserir("1 mes", idInserido);
                // Retorne o ID inserido
            }
            return idInserido;
           
        } catch (SQLException e) {
            System.out.println("Erro User: " + e.getMessage());
            return 0;
        }
    }
    
    public UsuarioModelo usuarioBuscarPorId(UsuarioModelo usuarioModelo) {
        UsuarioModelo usuarioModeloRetorno = new UsuarioModelo();
        try {
            abrirBanco();
            
            String query = "SELECT * FROM usuario WHERE id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setInt(1,usuarioModelo.getId());
            pst.execute();
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                usuarioModeloRetorno.setId(rs.getInt("id"));
                usuarioModeloRetorno.setNome(rs.getString("nome"));
                usuarioModeloRetorno.setEmail(rs.getString("email"));
                usuarioModeloRetorno.setSenha(rs.getString("senha"));
            }
            
            fecharBanco();                        
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
        }
        return usuarioModeloRetorno;
    }
    
    public UsuarioModelo usuarioBuscarPorEmailSenha(UsuarioModelo usuarioModelo) {
        UsuarioModelo usuarioModeloRetorno = new UsuarioModelo();
        try {
            abrirBanco();
            
            String query = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1,usuarioModelo.getEmail());
            pst.setString(2,usuarioModelo.getSenha());
            pst.execute();
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()) {
                usuarioModeloRetorno.setId(rs.getInt("id"));
                usuarioModeloRetorno.setNome(rs.getString("nome"));
                usuarioModeloRetorno.setEmail(rs.getString("email"));
                usuarioModeloRetorno.setSenha(rs.getString("senha"));
            }
            
            fecharBanco();                        
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
        }
        return usuarioModeloRetorno;
    }
    
    public boolean usuarioEditar(UsuarioModelo usuarioModelo) {
        
        try {
            abrirBanco();
            
            String query = "UPDATE usuario SET nome = ?, senha = ? WHERE id = ?";
            
            pst=(PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1,usuarioModelo.getNome());
            pst.setString(2,usuarioModelo.getSenha());
            pst.setInt(3,usuarioModelo.getId());
            pst.execute();                      
            
            fecharBanco();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());            
            return false;
        }        
    }
    
    public boolean usuarioDeletar(int id) {
        
        try {
            abrirBanco();
            
            CategoriaDAO cat = new CategoriaDAO();
            PrazoDAO prazoDAO = new PrazoDAO();
            TarefaDAO tarefaDAO = new TarefaDAO();
            
            tarefaDAO.tarefaDeletarPorUsuario(id);
            cat.categoriaDeletarPorUsuario(id);            
            prazoDAO.prazoDeletarPorUsuario(id);
            
            String query = "DELETE FROM usuario WHERE id = ?";
            
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
