package com.catalogo.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.SQLException;

import com.catalogo.dao.GeneroDAO;
import com.catalogo.dao.TituloDAO;
import com.catalogo.model.Titulo;

@WebServlet("/editar")
public class EditarTituloServlet extends HttpServlet {
    private TituloDAO tituloDAO;

    @Override
    public void init() throws ServletException {
        try {
            tituloDAO = new TituloDAO();
        } catch (SQLException e) {
            throw new ServletException("Erro ao inicializar o DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Titulo titulo = tituloDAO.buscarPorId(id);
            GeneroDAO generoDAO = new GeneroDAO();
            request.setAttribute("titulo", titulo);
            request.setAttribute("generos", generoDAO.listarTodos());
            RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao carregar título para edição", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String tipo = request.getParameter("tipo");
        String autorDiretor = request.getParameter("autorDiretor");
        int anoLancamento = Integer.parseInt(request.getParameter("anoLancamento"));
        String[] generosSelecionados = request.getParameterValues("genero");
        String sinopse = request.getParameter("sinopse");

        try {
            tituloDAO.atualizar(id, titulo, tipo, autorDiretor, anoLancamento, generosSelecionados, sinopse);
            response.sendRedirect("detalhes?id=" + id);
        } catch (SQLException e) {
            throw new ServletException("Erro ao atualizar título", e);
        }
    }
}