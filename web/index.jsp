<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->

<%@page import="br.com.entidade.CategoriaDAO" %>
<%@page import="br.com.modelo.CategoriaModelo" %>
<%@page import="br.com.entidade.PrazoDAO" %>
<%@page import="br.com.modelo.PrazoModelo" %>
<%@page import="br.com.entidade.TarefaDAO" %>
<%@page import="br.com.modelo.TarefaModelo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<% 
    Integer userId = 0;
    if(session != null && session.getAttribute("idUsuario") != null) {
        userId = Integer.parseInt(session.getAttribute("idUsuario").toString());
    } else {
        session.setAttribute("mensagem", "Você precisa estar conectado para realizar esta ação!");
        response.sendRedirect("usuario/login.jsp");
    }
    CategoriaDAO categoriaDAO = new CategoriaDAO();
    PrazoDAO prazoDAO = new PrazoDAO();
    List<CategoriaModelo> categorias = categoriaDAO.categoriaObterTodasUsuario(userId);
    List<PrazoModelo> prazos = prazoDAO.prazoObterTodasUsuario(userId);
    
    StringBuilder html = new StringBuilder();        
    
    categorias.forEach(categoria -> html.append("<a href='/AgendaUm/index.jsp?fill="+categoria.getId()+"'><hr>"+categoria.getNome()+"</a>"));
    String resultHtml = html.toString();
    
    StringBuilder htmlOption = new StringBuilder();        
    
    categorias.forEach(categoria -> htmlOption.append("<option value='"+categoria.getId()+"'>"+categoria.getNome()+"</option>"));
    String htmlOptionResult = htmlOption.toString();
    
    StringBuilder htmlOptionPrazo = new StringBuilder();        
    
    prazos.forEach(prazo -> htmlOptionPrazo.append("<option value='"+prazo.getId()+"'>"+prazo.getTempo()+"</option>"));
    String htmlOptionResultPrazo = htmlOptionPrazo.toString();
    
    
    TarefaDAO tarefaDAO = new TarefaDAO();
    
    List<TarefaModelo> tarefas = new ArrayList<>();
    StringBuilder htmlTarefa = new StringBuilder();
    String htmlTarefaResult = new String();
    
    if(request.getParameter("fill") != null){
        int fill = Integer.parseInt(request.getParameter("fill"));
        tarefas = tarefaDAO.tarefaObterTodasUsuarioCategoria(userId, fill);
        htmlTarefa = new StringBuilder();
        for (TarefaModelo tarefa : tarefas) {
            htmlTarefa.append("<div class='col-3 my-3'>");
            htmlTarefa.append("<div class='card'>");
            htmlTarefa.append("<div class='card-body'>");
            htmlTarefa.append("<h5 class='card-title'>" + tarefa.getNome() + "</h5>");
            htmlTarefa.append("<h6 class='card-subtitle mb-2 text-muted'>" + tarefa.getPNome() + "</h6>");
            htmlTarefa.append("<p class='card-text'>" + tarefa.getDescricao() + "</p>");
            htmlTarefa.append("<a href='#' onclick='editar("+tarefa.getId()+", `"+tarefa.getNome()+"`, `"+tarefa.getDescricao()+"`, `"+tarefa.getCategoriaId()+"`, `"+tarefa.getPrazoId()+"`)' class='card-link'>Editar</a>");
            htmlTarefa.append("<a href='#' onclick='deletar("+tarefa.getId()+", `"+tarefa.getNome()+"?`)' class='card-link'>Deletar</a>");
            htmlTarefa.append("</div>");
            htmlTarefa.append("</div>");
            htmlTarefa.append("</div>");
        }
        htmlTarefaResult = htmlTarefa.toString();        
    } else {
        tarefas = tarefaDAO.tarefaObterTodasUsuario(userId);
        htmlTarefa = new StringBuilder();
        for (TarefaModelo tarefa : tarefas) {
            htmlTarefa.append("<div class='col-3 my-3'>");
            htmlTarefa.append("<div class='card'>");
            htmlTarefa.append("<div class='card-body'>");
            htmlTarefa.append("<h5 class='card-title'>" + tarefa.getNome() + "</h5>");
            htmlTarefa.append("<h6 class='card-subtitle mb-2 text-muted'>" + tarefa.getPNome() + "</h6>");
            htmlTarefa.append("<p class='card-text'>" + tarefa.getDescricao() + "</p>");
            htmlTarefa.append("<a href='#' onclick='editar("+tarefa.getId()+", `"+tarefa.getNome()+"`, `"+tarefa.getDescricao()+"`, `"+tarefa.getCategoriaId()+"`, `"+tarefa.getPrazoId()+"`)' class='card-link'>Editar</a>");
            htmlTarefa.append("<a href='#' onclick='deletar("+tarefa.getId()+", `"+tarefa.getNome()+"?`)' class='card-link'>Deletar</a>");
            htmlTarefa.append("</div>");
            htmlTarefa.append("</div>");
            htmlTarefa.append("</div>");
        }
        htmlTarefaResult = htmlTarefa.toString();
    }        
    
%>
<html>
    <head>
        <title>Início</title>
        <%@include file="default/header.jsp" %>
    </head>
    <body>
        <%@include file="default/menu.jsp" %>                
    <div class="row justify-content-around m-0 py-3">
        <div class="col-3 border bg-light" style="min-height: 80vh">
            <button class="border mt-5" onclick="$('#create').modal('show')">+ Adicionar card</button>
            
            <hr>
            
            <div class="border px-2" style="max-height: 150px; height: 150px; overflow-y: auto">
                <div class="row pt-2" style="justify-content: center; align-items: center">
                    <span class="col-8">Suas categorias</span>
                    <svg onclick="window.location.href = '/AgendaUm/categoria/lista.jsp'" class="col-2 mt-1" style="cursor: pointer" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M40 48C26.7 48 16 58.7 16 72v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V72c0-13.3-10.7-24-24-24H40zM192 64c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zm0 160c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zm0 160c-17.7 0-32 14.3-32 32s14.3 32 32 32H480c17.7 0 32-14.3 32-32s-14.3-32-32-32H192zM16 232v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V232c0-13.3-10.7-24-24-24H40c-13.3 0-24 10.7-24 24zM40 368c-13.3 0-24 10.7-24 24v48c0 13.3 10.7 24 24 24H88c13.3 0 24-10.7 24-24V392c0-13.3-10.7-24-24-24H40z"/></svg>
                </div>
                <%=html%>
            </div>
        </div>
        
        <div class="row col-8 border bg-light" style="min-height: 80vh">
            <%= htmlTarefaResult %>
        </div>
    </div>
        <div class="modal" id="create" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Criar card</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
                <form action="/AgendaUm/tarefa.do" method="POST" id="criarForm">
                <input type="hidden" name="act" value="create"/>
                <div class="form-outline mb-4">
                    <label class="form-label" for="nome_input">Nome:</label>
                    <input type="text" name="nome_input" id="nome_input" class="form-control form-control-lg" required />                    
                </div>
                <div class="form-outline mb-4">
                    <label class="form-label" for="descricao_input">Descrição</label>
                    <input type="text" name="descricao_input" id="descricao_input" class="form-control form-control-lg" maxlength="100" required />                    
                </div>
                <div class="form-outline mb-4">
                    <label class="form-label" for="categoria_input">Selecione uma categoria</label>
                    <select name="categoria_input" class="form-control form-control-lg" required>
                        <%=htmlOptionResult%>
                    </select>
                </div>
                <div class="form-outline mb-4 row">                    
                    <div class="col-10">
                        <label class="form-label" for="prazo_input">Selecione um prazo</label>
                        <select name="prazo_input" class="form-control form-control-lg" required>
                            <%=htmlOptionResultPrazo%>
                        </select>                                                
                    </div>
                        <svg style="width: 60px; height: 60px; cursor: pointer" onclick="location.href = '/AgendaUm/prazo/lista.jsp'" class="col-2 mt-4" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                    </svg>
                </div>
                <button class="btn btn-primary btn-lg btn-block" type="submit">Criar</button><br>
                </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
            </div>
          </div>
        </div>
      </div>
                        
        <div class="modal" id="edit" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Editar card</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
                <form action="/AgendaUm/tarefa.do" method="POST">
                <input type="hidden" name="act" value="edit"/>
                <input type="hidden" id="id_input_edit" name="id_input_edit" />
                <div class="form-outline mb-4">
                    <label class="form-label" for="nome_input_edit">Nome:</label>
                    <input type="text" name="nome_input_edit" id="nome_input_edit" class="form-control form-control-lg" required />                    
                </div>
                <div class="form-outline mb-4">
                    <label class="form-label" for="descricao_input_edit">Descrição</label>
                    <input type="text" name="descricao_input_edit" id="descricao_input_edit" class="form-control form-control-lg" maxlength="100" required />                    
                </div>
                <div class="form-outline mb-4">
                    <label class="form-label" for="categoria_input_edit">Selecione uma categoria</label>
                    <select name="categoria_input_edit" id="categoria_input_edit" class="form-control form-control-lg" required>
                        <%=htmlOptionResult%>
                    </select>
                </div>
                <div class="form-outline mb-4 row">                    
                    <div class="col-10">
                        <label class="form-label" for="prazo_input_edit">Selecione um prazo</label>
                        <select name="prazo_input_edit" id="prazo_input_edit" class="form-control form-control-lg" required>
                            <%=htmlOptionResultPrazo%>
                        </select>                                                
                    </div>
                        <svg style="width: 60px; height: 60px; cursor: pointer" onclick="location.href = '/AgendaUm/prazo/lista.jsp'" class="col-2 mt-4" xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-plus" viewBox="0 0 16 16">
                        <path d="M8 4a.5.5 0 0 1 .5.5v3h3a.5.5 0 0 1 0 1h-3v3a.5.5 0 0 1-1 0v-3h-3a.5.5 0 0 1 0-1h3v-3A.5.5 0 0 1 8 4z"/>
                    </svg>
                </div>
                <button class="btn btn-primary btn-lg btn-block" type="submit">Salvar</button><br>
                </form>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
            </div>
          </div>
        </div>
      </div>
      <script>
          function deletar(id, nome) {
              if(confirm('Tem certeza que deseja apagar o registro: '+nome)){
                  location.href = '/AgendaUm/tarefa.do?act=delete&id='+id;
              }
          }
          
          function editar(id, nome, descricao, categoriaId, prazoId){
              $('#id_input_edit').val(id);
              $('#nome_input_edit').val(nome);
              $('#descricao_input_edit').val(descricao);
              $('#categoria_input_edit').val(categoriaId);
              $('#prazo_input_edit').val(prazoId);
              
              $('#edit').modal('show');
          }
      </script>
    </body>
</html>