package com.catalogo.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.catalogo.dao.GeneroDAO;
import com.catalogo.dao.TituloDAO;
import com.catalogo.model.Titulo;
import java.sql.SQLException;


@WebServlet("/adicionar")
public class AdicionarTituloServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String titulo = request.getParameter("titulo");
        String tipo = request.getParameter("tipo");
        String autorDiretor = request.getParameter("autorDiretor");
        String anoLancamentoStr = request.getParameter("anoLancamento");
        String[] generosSelecionados = request.getParameterValues("genero");
        String genero = "";
        if (generosSelecionados != null && generosSelecionados.length > 0) {
            genero = String.join(", ", generosSelecionados);
        }

        String sinopse = request.getParameter("sinopse");
        System.out.println("Sinopse recebida: " + sinopse);

        int anoLancamento = 0;
        if (anoLancamentoStr != null && !anoLancamentoStr.isEmpty()) {
            anoLancamento = Integer.parseInt(anoLancamentoStr);
        }

        Titulo novoTitulo = new Titulo();
        novoTitulo.setTitulo(titulo);
        novoTitulo.setTipo(tipo);
        novoTitulo.setAutorDiretor(autorDiretor);
        novoTitulo.setAnoLancamento(anoLancamento);
        novoTitulo.setGenero(genero);
        novoTitulo.setSinopse(sinopse);

        try {
            dao.adicionar(novoTitulo, generosSelecionados);
            response.sendRedirect("listar");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao adicionar título: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GeneroDAO generoDAO = new GeneroDAO();
            request.setAttribute("generos", generoDAO.listarTodos());
            RequestDispatcher rd = request.getRequestDispatcher("adicionar.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erro ao carregar gêneros", e);
        }
    }

    
}
