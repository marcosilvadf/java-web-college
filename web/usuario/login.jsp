<%-- 
    Document   : login
    Created on : 12 de nov. de 2023, 14:46:32
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

                      <h3 class="mb-5">Entrar</h3>
                        
                      <input type="hidden" name="act" value="login"/>
                      <div class="form-outline mb-4">
                          <input name="email" type="email" id="typeEmailX-2" class="form-control form-control-lg" required />
                        <label class="form-label" for="typeEmailX-2">Email</label>
                      </div>

                      <div class="form-outline mb-4">
                          <input name="senha" type="password" id="typePasswordX-2" minlength="4" class="form-control form-control-lg" required />
                        <label class="form-label" for="typePasswordX-2">Senha</label>
                      </div>

                      <button class="btn btn-primary btn-lg btn-block" type="submit">Entrar</button><br>
                      <a href="/AgendaUm/usuario/registrar.jsp">Não tem cadastro? Clique aqui!</a>
                    </form>                      
                  </div>
                </div>
              </div>
            </div>
        </section>
    </body>
</html>
