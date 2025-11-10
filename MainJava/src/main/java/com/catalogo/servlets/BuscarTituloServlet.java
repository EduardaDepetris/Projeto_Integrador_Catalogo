package com.catalogo.servlets;

import com.catalogo.dao.TituloDAO;
import com.catalogo.model.Titulo;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/buscar")
public class BuscarTituloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String termoBusca = request.getParameter("q");

        if (termoBusca == null || termoBusca.trim().isEmpty()) {
            response.sendRedirect("listar");
            return;
        }

        try {
            TituloDAO dao = new TituloDAO();
            List<Titulo> resultados = dao.buscarPorTituloOuAutor(termoBusca.trim());

            request.setAttribute("titulos", resultados);
            RequestDispatcher dispatcher = request.getRequestDispatcher("listaTitulos.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Erro ao buscar títulos", e);
        }
    }
}