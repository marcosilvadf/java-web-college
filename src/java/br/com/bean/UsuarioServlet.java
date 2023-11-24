/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.bean;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import br.com.modelo.UsuarioModelo;
import br.com.entidade.UsuarioDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Antonio Marcos
 */
public class UsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String acao = request.getParameter("act");                      
            
            int id;
            String nome;
            String email;
            String senha;
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            HttpSession session = request.getSession();
            
            switch (acao) {
                case "create":
                    nome = request.getParameter("nome");
                    email = request.getParameter("email");
                    senha = request.getParameter("senha");

                    UsuarioModelo usuarioModelo = new UsuarioModelo(nome, email, senha);
                    
                    int resultado = usuarioDAO.usuarioInserir(usuarioModelo);

                    session.setAttribute("idUsuario", resultado);
                    session.setAttribute("nomeUsuario", nome);
                    session.setAttribute("emailUsuario", email);
                    session.setAttribute("senhaUsuario", senha);
                        
                    response.sendRedirect("index.jsp");
                    break;
                    
                case "login":
                    email = request.getParameter("email");
                    senha = request.getParameter("senha");
                    
                    UsuarioModelo usuarioModeloLoginResultado;
                    UsuarioModelo usuarioModeloLogin = new UsuarioModelo("", email, senha);                    
                    
                    usuarioModeloLoginResultado = usuarioDAO.usuarioBuscarPorEmailSenha(usuarioModeloLogin);
                    
                    session = request.getSession();
                    
                    if(usuarioModeloLoginResultado.getNome() != null){                        

                        session.setAttribute("idUsuario", String.valueOf(usuarioModeloLoginResultado.getId()));
                        session.setAttribute("nomeUsuario", usuarioModeloLoginResultado.getNome());
                        session.setAttribute("emailUsuario", usuarioModeloLoginResultado.getEmail());
                        session.setAttribute("senhaUsuario", usuarioModeloLoginResultado.getSenha());
                        
                        response.sendRedirect("index.jsp");
                    } else {
                        session.setAttribute("mensagem", "E-mail e/ou senha incorreto!");
                        response.sendRedirect("usuario/login.jsp");
                    }
                    break;
                    
                case "logoff":
                    session = request.getSession();
                    session.invalidate();                                        
                        
                    response.sendRedirect("index.jsp");
                    break;
                    
                case "edit":
                    session = request.getSession();
                    id = Integer.parseInt(session.getAttribute("idUsuario").toString());
                    UsuarioModelo usuarioModeloShow = new UsuarioModelo(id, "", "", "");
                    UsuarioModelo usuarioModeloShowRetorno = new UsuarioModelo();
                            
                    usuarioModeloShowRetorno = usuarioDAO.usuarioBuscarPorId(usuarioModeloShow);
                    
                    request.setAttribute("id", usuarioModeloShowRetorno.getId());
                    request.setAttribute("nome", usuarioModeloShowRetorno.getNome());
                    request.setAttribute("email", usuarioModeloShowRetorno.getEmail());
                    request.setAttribute("senha", usuarioModeloShowRetorno.getSenha());
                    
                    RequestDispatcher lista = request.getRequestDispatcher("usuario/registrar.jsp");
                    lista.forward(request, response);                    
                    break;
                    
                case "update":
                    nome = request.getParameter("nome");
                    senha = request.getParameter("senha");
                    id = Integer.parseInt(request.getParameter("id"));
                    
                    UsuarioModelo usuarioModeloUpdate = new UsuarioModelo(id, nome, "", senha);
                    
                    boolean resultadoUpdate = usuarioDAO.usuarioEditar(usuarioModeloUpdate);
                    
                    session.setAttribute("nomeUsuario", nome);
                    
                    response.sendRedirect("usuario/perfil.jsp");
                    
                    break;
                    
                case "delete":
                    id = Integer.parseInt(session.getAttribute("idUsuario").toString());                    
                    usuarioDAO.usuarioDeletar(id);
                    response.sendRedirect("index.jsp");
                    session.invalidate();
                    break;
                    
                default:
                    throw new AssertionError();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
