package com.catalogo.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.SQLException;

import com.catalogo.dao.TituloDAO;

@WebServlet("/excluir")
public class ExcluirTituloServlet extends HttpServlet {
    private TituloDAO dao;

    @Override
    public void init() throws ServletException {
        try {
            dao = new TituloDAO();
        } catch (SQLException e) {
            throw new ServletException("Erro ao inicializar o DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            dao.excluir(id);
            response.sendRedirect("listar");
        } catch (SQLException e) {
            throw new ServletException("Erro ao excluir título", e);
        }
    }
}