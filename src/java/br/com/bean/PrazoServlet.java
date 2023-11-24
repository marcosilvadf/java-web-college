/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package br.com.bean;

import br.com.entidade.PrazoDAO;
import br.com.modelo.PrazoModelo;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Antonio Marcos
 */
public class PrazoServlet extends HttpServlet {

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
            String act = request.getParameter("act");
            PrazoDAO prazoDAO = new PrazoDAO();
            PrazoModelo prazoModelo = new PrazoModelo();
            HttpSession session = request.getSession();
            boolean resultado;
            
            int id;
            String nome;
            
            switch (act) {
                case "create":                    
                    nome = request.getParameter("nome");
                    if(session != null && session.getAttribute("idUsuario") != null) {                        
                     int userId = Integer.parseInt(session.getAttribute("idUsuario").toString());
                     prazoDAO.prazoInserir(nome, userId);                    
                     response.sendRedirect("prazo/lista.jsp");      
                    } else {
                        session.setAttribute("mensagem", "Você precisa estar conectado para realizar esta ação!");
                        response.sendRedirect("usuario/login.jsp");
                    }                                         
                    break;
                    
                case "all":
                    
                    break;
                    
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    resultado = prazoDAO.prazoDeletar(id);
                    if(resultado){
                        session.setAttribute("mensagem", "Sucesso!");
                        response.sendRedirect("prazo/lista.jsp");
                    } else {
                        session.setAttribute("mensagem", "Houve um erro!");
                        response.sendRedirect("prazo/lista.jsp");
                    }
                    break;
                    
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    nome = request.getParameter("nome");
                    prazoModelo.setId(id);
                    prazoModelo.setTempo(nome);
                    resultado = prazoDAO.prazoEditar(prazoModelo);
                    if(resultado){
                        session.setAttribute("mensagem", "Sucesso!");
                        response.sendRedirect("prazo/lista.jsp");
                    } else {
                        session.setAttribute("mensagem", "Houve um erro!");
                        response.sendRedirect("categoria/lista.jsp");
                    }
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
