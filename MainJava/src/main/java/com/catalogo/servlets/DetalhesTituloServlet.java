package com.catalogo.servlets;

import com.catalogo.dao.TituloDAO;
import com.catalogo.model.Titulo;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/detalhes")
public class DetalhesTituloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("listar");
            return;
        }

        try {
            int id = Integer.parseInt(idParam);
            TituloDAO dao = new TituloDAO();
            Titulo titulo = dao.buscarPorId(id);

            if (titulo != null) {
                request.setAttribute("titulo", titulo);
                RequestDispatcher dispatcher = request.getRequestDispatcher("detalhes.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("listar");
            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("listar");
        }
    }
}