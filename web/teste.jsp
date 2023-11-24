<%-- 
    Document   : teste
    Created on : 12 de nov. de 2023, 11:25:27
    Author     : Antonio Marcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.entidade.UsuarioDAO" %>
<%@page import="br.com.modelo.UsuarioModelo" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String nome = "";
            String email = "";
            String senha = "";
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            UsuarioModelo usuarioModelo = new UsuarioModelo();
            UsuarioModelo usuarioModelo1 = new UsuarioModelo();
            
            usuarioModelo.setId(2);
            
            usuarioModelo1 = usuarioDAO.usuarioBuscarPorId(usuarioModelo);
            
            nome = usuarioModelo1.getNome();
            email = usuarioModelo1.getEmail();
            senha = usuarioModelo1.getSenha();
        %>
        <h1><%=nome%></h1>
        <h1><%=email%></h1>
        <h1><%=senha%></h1>
    </body>
</html>
