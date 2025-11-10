<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.catalogo.model.Titulo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    List<Titulo> titulos = (List<Titulo>) request.getAttribute("titulos");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>
        <c:choose>
            <c:when test="${param.tipo == 'livro'}">Livros</c:when>
            <c:when test="${param.tipo == 'filme'}">Filmes</c:when>
            <c:when test="${param.tipo == 'serie'}">Séries</c:when>
            <c:otherwise>Catálogo</c:otherwise>
        </c:choose>
    </title>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="style/style.css">
</head>
<body>

    <header class="topbar">   
        <div class="logo">
            <a href="listar"><img src="images/logo.png" width="60" alt="Logo do site"/></a>
        </div>
        
        <nav class="menu">
            <a href="listar?tipo=livro">Livros</a>
            <a href="listar?tipo=filme">Filmes</a>
            <a href="listar?tipo=serie">Séries</a>
        </nav>
    </header>

    <main>
        <section class="search">
            <button class="filtro">🔍 Filtro</button>

            <form id="search-form" action="buscar" method="get" class="barra-busca">
                <input type="text" name="q" placeholder="Buscar por título ou autor/diretor" />
                <button type="submit" class="buscar">Buscar</button>
            </form>

            <a href="adicionar" class="adicionar">Adicionar</a>
        </section>

        <section class="catalogo">
            <c:if test="${empty titulos}">
                <p>Nenhum título encontrado.</p>
            </c:if>

            <c:forEach var="t" items="${titulos}">
                <a href="detalhes?id=${t.id}" class="catalog-link">
                    <div class="catalog">
                        <c:choose>
                            <c:when test="${t.tipo == 'livro'}">
                            </c:when>
                            <c:when test="${t.tipo == 'filme'}">
                            </c:when>
                            <c:when test="${t.tipo == 'serie'}">
                            </c:when>
                        </c:choose>

                        <div class="title">${t.titulo}</div>
                        <div>${t.anoLancamento}</div>
                        <div>${t.autorDiretor}</div>
                        <br>
                        <div>Gêneros:</div>
                        <div><i>${empty t.genero ? 'Gênero não informado' : t.genero}</i></div>
                    </div>
                </a>
            </c:forEach>
        </section>

        <div class="voltar">
            <a href="listar">Voltar</a>
        </div>

    </main>

    <footer>
        <p>Site desenvolvido por Eduarda Depetris. 2025</p>
    </footer>
</body>
</html>