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
        <%@include file="../default/menu.jsp" %>
        <section class="vh-100 bg-dark">
            <% if(session != null && session.getAttribute("nomeUsuario") != null) {%>                    
                <%} else {%>
                <script>
                    window.location.href = "/AgendaUm";
                </script>
                <%}%>
            <div class="container py-5 h-100">
              <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                  <div class="card shadow-2-strong px-4" style="border-radius: 1rem;">
                      <h1><%=session.getAttribute("nomeUsuario")%></h1>
                      <h4 class="mb-5"><%=session.getAttribute("emailUsuario")%></h4>
                      <a class="btn btn-primary btn-lg btn-block" href="/AgendaUm/usuario.do?act=edit">Editar Perfil</a><br><!-- comment -->
                      <button class="btn btn-primary btn-lg btn-block" onclick="apagarPerfil()">Deletar</button><br>
                  </div>
                </div>
              </div>
            </div>
        </section>
        <script>
            function apagarPerfil() {
                if(confirm("Tem certeza que deseja apagar seu perfil?")){
                    window.location.href = "/AgendaUm/usuario.do?act=delete";
                }
            }
        </script>
    </body>
</html>
