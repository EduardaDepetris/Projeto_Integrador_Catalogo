package com.catalogo.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.catalogo.dao.TituloDAO;
import com.catalogo.model.Titulo;

import java.sql.SQLException;
import java.util.List;

@WebServlet("/listar")
public class ListarTitulosServlet extends HttpServlet {
    private TituloDAO dao;

    public ListarTitulosServlet() {
        try {
            dao = new TituloDAO();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inicializar TituloDAO", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tipo = request.getParameter("tipo"); // livro, filme, serie
        List<Titulo> titulos = null;

        try {
            if (tipo != null && !tipo.isEmpty()) {
                titulos = dao.listarPorTipo(tipo);
            } else {
                titulos = dao.listarTodos();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("titulos", titulos);
        RequestDispatcher rd = request.getRequestDispatcher("listaTitulos.jsp");
        rd.forward(request, response);
    }
}

