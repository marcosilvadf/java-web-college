<%-- 
    Document   : lista
    Created on : 13 de nov. de 2023, 19:21:55
    Author     : Antonio Marcos
--%>
<%@page import="br.com.entidade.CategoriaDAO" %>
<%@page import="br.com.modelo.CategoriaModelo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Integer userId = 0;
    if(session != null && session.getAttribute("idUsuario") != null) {
        userId = Integer.parseInt(session.getAttribute("idUsuario").toString());
    } else {
        session.setAttribute("mensagem", "Você precisa estar conectado para realizar esta ação!");
        response.sendRedirect("/AgendaUm/usuario/login.jsp");
    }
%>

<!DOCTYPE html>
<html>
    <head>        
        <title>JSP Page</title>
        <%@include file="../default/header.jsp" %>
    </head>
    <body>
        <%@include file="../default/menu.jsp" %>
        <% if(session != null && session.getAttribute("mensagem") != null) {%>
                    <div class="alert alert-primary" role="alert">
                        <%= session.getAttribute("mensagem") %>
                    </div>
                <%}%>
                <%session.removeAttribute("mensagem");%>
                <svg onclick="criarCategoria()" style="width: 35px; height: 35px; margin: 25px; cursor: pointer" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64zM200 344V280H136c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V168c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H248v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z"/></svg>
        <ul class="list-group">    
        <% 
            CategoriaDAO categoriaDAO = new CategoriaDAO();
            List<CategoriaModelo> categorias = categoriaDAO.categoriaObterTodasUsuario(userId);

            StringBuilder html = new StringBuilder();        
            
            for (int i = 0; i < categorias.size(); i++) {
                CategoriaModelo categoria = categorias.get(i);%>
                
                <li class="list-group-item">
                    <%=categoria.getNome()%>
                    <svg onclick="editarCategoria(<%=categoria.getId()%>, '<%=categoria.getNome()%>')" style="width: 25px; height: 25px; margin-left: 25px; cursor: pointer" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M471.6 21.7c-21.9-21.9-57.3-21.9-79.2 0L362.3 51.7l97.9 97.9 30.1-30.1c21.9-21.9 21.9-57.3 0-79.2L471.6 21.7zm-299.2 220c-6.1 6.1-10.8 13.6-13.5 21.9l-29.6 88.8c-2.9 8.6-.6 18.1 5.8 24.6s15.9 8.7 24.6 5.8l88.8-29.6c8.2-2.7 15.7-7.4 21.9-13.5L437.7 172.3 339.7 74.3 172.4 241.7zM96 64C43 64 0 107 0 160V416c0 53 43 96 96 96H352c53 0 96-43 96-96V320c0-17.7-14.3-32-32-32s-32 14.3-32 32v96c0 17.7-14.3 32-32 32H96c-17.7 0-32-14.3-32-32V160c0-17.7 14.3-32 32-32h96c17.7 0 32-14.3 32-32s-14.3-32-32-32H96z"/></svg>
                    <svg onclick="deleteCategoria(<%=categoria.getId()%>, '<%=categoria.getNome()%>')" style="width: 25px; height: 25px; margin-left: 25px; cursor: pointer" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z"/></svg>
                </li>    
                
            <%}%>
        </ul>
        <div class="modal" id="edit" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Editar Categoria</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="act" value="login"/>
                <div class="form-outline mb-4">
                    <input type="hidden" value="" id="id_input" >
                    <label class="form-label" for="nome_input">Novo nome:</label>
                    <input type="text" id="nome_input" class="form-control form-control-lg" />                    
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="updateCategoria()">Salvar</button>
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
            </div>
          </div>
        </div>
      </div>
        
        <div class="modal" id="create" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Criar Nova Categoria</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
                <input type="hidden" name="act" value="login"/>
                <div class="form-outline mb-4">
                    <input type="hidden" value="" id="id_input" >
                    <label class="form-label" for="nome_input_create">Nome:</label>
                    <input type="text" id="nome_input_create" class="form-control form-control-lg" />                    
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="createCategoria()">Salvar</button>
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
            </div>
          </div>
        </div>
      </div>
    </body>
    <script>
        function criarCategoria(){
            $('#create').modal('show');
        }
        
        function deleteCategoria(id, nome){
            if(confirm('Tem certeza que deseja remover a categoria: '+nome+'?')){
                window.location.href = '/AgendaUm/categoria.do?act=delete&id='+id;
            }
        }
        
        function editarCategoria(id, nome){
            $('#nome_input').val(nome);
            $('#id_input').val(id);
            
            $('#edit').modal('show');                        
        }
        
        function updateCategoria(){
            var id = $('#id_input').val();
            var nome = $('#nome_input').val();
            window.location.href = '/AgendaUm/categoria.do?act=edit&id='+id+'&nome='+nome;
        }
        
        function createCategoria(){
            var nome = $('#nome_input_create').val();
            if(nome == ''){
                alert('O nome não deve estar vazio!');
                return false;
            }
            window.location.href = '/AgendaUm/categoria.do?act=create&nome='+nome;
        }
    </script>
</html>
