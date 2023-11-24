<%-- 
    Document   : menu
    Created on : 12 de nov. de 2023, 14:48:43
    Author     : Antonio Marcos
--%>
<%@page import="jakarta.servlet.http.HttpSession" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
    <a class="navbar-brand" href="/AgendaUm/index.jsp">Agenda Um</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/AgendaUm/index.jsp">Início</a>
            </li> <!-- Faltava esta tag de fechamento -->
            <li class="nav-item dropdown">
                
                <% if(session != null && session.getAttribute("nomeUsuario") != null) {%>
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Perfil
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/AgendaUm/usuario/perfil.jsp">Perfil: <%= session.getAttribute("nomeUsuario") %></a>
                        <a class="dropdown-item" href="/AgendaUm/usuario.do?act=logoff">Sair</a>
                    </div>
                <%} else { %>
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Login
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="/AgendaUm/usuario/login.jsp">Entrar</a>
                        <a class="dropdown-item" href="/AgendaUm/usuario/registrar.jsp">Cadastrar</a>
                    </div>
                <%} %>                
            </li>
        </ul>
    </div>
</nav>