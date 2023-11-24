<%-- 
    Document   : registrar
    Created on : 12 de nov. de 2023, 12:38:03
    Author     : Antonio Marcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="../default/header.jsp" %>
    </head>
    <body>
        <%
            String id="";
            String nome="";
            String email="";
            String senha="";
            
            boolean edit = false;
            if(request.getAttribute("id") != null){
                id = (String) request.getAttribute("id").toString();
                nome = (String) request.getAttribute("nome").toString();
                email = (String) request.getAttribute("email").toString();
                senha = (String) request.getAttribute("senha").toString();
                edit = true;
            }
        %>
        <section class="vh-100 bg-dark">
            <% if(session != null && session.getAttribute("mensagem") != null) {%>
                    <div class="alert alert-primary" role="alert">
                        <%= session.getAttribute("mensagem") %>
                    </div>
                <%}%>
                <%session.removeAttribute("mensagem");%>
            <div class="container py-5 h-100">
              <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                  <div class="card shadow-2-strong" style="border-radius: 1rem;">
                      <form action="/AgendaUm/usuario.do" method="POST" class="card-body p-5 text-center">
                        <% if(edit) {%>
                        <input type="hidden" name="id" value="<%=id%>"/>
                        <%}%>  
                      <h3 class="mb-5"><%= edit ? "Editar" : "Cadastrar" %></h3>
                      
                        <% if(edit) {%>
                            <input type="hidden" name="act" value="update"/>                            
                        <%} else {%>
                            <input type="hidden" name="act" value="create"/>                        
                        <%}%>
                        
                      <div class="form-outline mb-4">
                          <input name="nome" type="text" id="typeNome" class="form-control form-control-lg" value="<%= edit ? nome : "" %>" required />
                        <label class="form-label" for="typeNome">Nome</label>
                      </div>
                      <div class="form-outline mb-4">
                          <input name="email" type="email" id="typeEmailX-2" class="form-control form-control-lg" <%= edit ? "disabled" : "" %> value="<%= edit ? email : "" %>" required />
                        <label class="form-label" for="typeEmailX-2">Email</label>
                      </div>

                    <div class="form-outline mb-4">
                        <input name="senha" type="password" id="typePasswordX-2" minlength="4" class="form-control form-control-lg" value="<%= edit ? senha : "" %>" required />
                        <label class="form-label" for="typePasswordX-2">Senha</label>
                    </div>                     

                      <button class="btn btn-primary btn-lg btn-block" type="submit"><%= edit ? "Salvar" : "Cadastrar" %></button><br>
                      <% if(!edit) {%>
                      <a href="/AgendaUm/usuario/login.jsp">Já possui cadastro? Clique aqui!</a>
                    <%}%>   
                    </form>                      
                  </div>
                </div>
              </div>
            </div>
        </section>
    </body>
</html>
