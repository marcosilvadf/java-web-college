/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.entidade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Antonio Marcos
 */
public class Conector {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void abrirBanco() throws SQLException {
        try {            
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver JDBC explicitamente
            String url = "jdbc:mysql://localhost/agendaum";
            String user = "root";
            String senha = "";
            con = DriverManager.getConnection(url, user, senha);
            System.out.println("Conectado ao banco de dados ");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    public void fecharBanco() {
        try {
            if (pst != null) {
                pst.close();
                System.out.println("Execução da Query fechada\n");
            }
            if (con != null) {
                con.close(); // fechando a conexão com o banco
                System.out.println("Conexão fechada\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}