<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.catalogo.dao.TituloDAO, com.catalogo.model.Titulo" %>
<%
    String idParam = request.getParameter("id");
    Titulo titulo = null;

    if (idParam != null) {
        int id = Integer.parseInt(idParam);
        TituloDAO dao = new TituloDAO();
        for (Titulo t : dao.listarTodos()) {
            if (t.getId() == id) {
                titulo = t;
                break;
            }
        }
    }
%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title><%= titulo.getTitulo() %></title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
    <header class="topbar">
        <div class="logo">
            <a href="listar"><img src="images/logo.png" width="60" alt="Logo"/></a>
        </div>

        <nav class="menu">
            <p><%= titulo.getTitulo() %></p>
        </nav>
    </header>

    <main>

        <div class="editar">
            <a href="editar?id=<%= titulo.getId() %>">Editar</a>
        </div>
        <div id="detalhes-conteudo" class="detalhes">
                <h1><%= titulo.getTitulo() %></h1>
                <div class="detalhes-info">
                    <p><b>Autor/Diretor:</b> <%= titulo.getAutorDiretor() %></p>
                    <br>
                    <p><b>Ano:</b> <%= titulo.getAnoLancamento() %></p>
                    <br>
                    <p><b>Gênero:</b> <%= titulo.getGenero() %></p>
                    <br>
                    <p><b>Sinopse:</b> <%= titulo.getSinopse() %></p>
                </div>
        </div>
        
        <div class="voltar">
            <a href="listar">Voltar</a>
        </div>
    </main>

    <footer>
        <p>Site desenvolvido por Eduarda Depetris. 2025</p>
    </footer>
</body>
</html>
